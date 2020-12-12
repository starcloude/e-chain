package com.echain.web.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.echain.common.beans.JsonResult;
import com.echain.common.enums.business.DBFileTypeEnum;
import com.echain.domain.business.DBFile;
import com.echain.domain.business.user.User;
import com.echain.service.business.FileService;
import com.echain.service.business.UserService;
import com.echain.web.shiro.ShiroSession;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;

@SuppressWarnings("restriction")
@Slf4j
@RestController
@RequestMapping("/api/file/")
public class FileController extends AbsSupperController {

	@Autowired
	private FileService fileService;
	
	@Resource
	private UserService userService;

	/**
	 * 显示或下载图片
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@GetMapping(value = "d/{id}")
	public JsonResult down(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {
		if (StringUtils.isBlank(id)) {
			return JsonResult.Error("错误的参数");
		}
		try {
			DBFile file = fileService.getWithCache(id);
			if (file == null) {
				return JsonResult.Error("文件不存在!");
			}
			String fileExt = getFileExt(file.getName());
			String mediaType = null;
			if (StringUtils.isNotBlank(fileExt)) {
				mediaType = imgMediaType.get(fileExt.toLowerCase());
			}
			// 图片直接展示
			if (file.getType() == DBFileTypeEnum.IMAGE.getCode() && StringUtils.isNotBlank(mediaType)) {
				byte[] img = file.getData();
				response.setContentType(mediaType);
				OutputStream os = response.getOutputStream();
				os.write(img);
				os.flush();
				os.close();
			} else {
				// 文件下载
				// 1、设置response 响应头
				response.reset();
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/x-download");
				response.setHeader("Content-Disposition",
						"attachment;fileName=" + URLEncoder.encode(file.getName(), "UTF-8"));
				// 2、 读取文件--输入流
				InputStream input = new ByteArrayInputStream(file.getData());
				// 3、 写出文件--输出流
				OutputStream out = response.getOutputStream();
				byte[] buff = new byte[1024];
				int index = 0;
				// 4、执行 写出操作
				while ((index = input.read(buff)) != -1) {
					out.write(buff, 0, index);
					out.flush();
				}
				IOUtils.closeQuietly(out);
				IOUtils.closeQuietly(input);
			}
		} catch (Exception ex) {
			log.error("下载图片发生异常! {} , {}", id, ex);
			return JsonResult.Error("生成图片异常,请联系管理员!");
		}
		return null;
	}

	/**
	 * 上传
	 * @param file
	 * @return
	 */
	@PostMapping(value = "u")
	public JsonResult upload(MultipartFile file) {
		return multipartFile2DB(file);
	}
	
	/**
	 * base64上传
	 * @param content
	 * @param name
	 * @return
	 */
	@PostMapping(value = "u64")
	public JsonResult uploadBase64(String content,String name) {
		if (StringUtils.isAnyBlank(content,name)) {
			return JsonResult.Error("文件为空~");
		}
        MultipartFile file =base64MutipartFile(content);
		return multipartFile2DB(file, name);
	}

	/**
	 * 上传,并落库
	 * @param file
	 * @return
	 */
	private JsonResult multipartFile2DB(MultipartFile file) {
		return multipartFile2DB(file,file.getOriginalFilename());
	}
	
	/**
	 * 上传并落库
	 * @param file
	 * @param name
	 * @return
	 */
	private JsonResult multipartFile2DB(MultipartFile file,String name) {
		if (file == null || file.isEmpty()) {
			return JsonResult.Error("文件为空~");
		}
		if (StringUtils.isBlank(name)) {
			return JsonResult.Error("文件名不允许为空~");
		}
		User user = getLoginUser();
		DBFile dbFile = new DBFile();
		dbFile.setType(isImage(name) ? DBFileTypeEnum.IMAGE.getCode() : DBFileTypeEnum.FILE.getCode());
		if (name.length() > 100) {
			name = name.substring(name.length() - 100);
		}
		dbFile.setName(name);
		if(user == null) {
			user = userService.get(Long.parseLong(ShiroSession.get("registerUserId")));
		}
		dbFile.setCreatePin(user.getAccount());
		try {
			dbFile.setData(file.getBytes());
			fileService.save(dbFile);
			return JsonResult.OK(dbFile.getId());
		} catch (Exception ex) {
			log.error("文件上传发生异常~{} ", name, ex);
			return JsonResult.Error("文件上传发生异常,请联系管理员!");
		}
	}
	
	private MultipartFile base64MutipartFile(String imgStr){
        try {
            String [] baseStr = imgStr.split(",");
			BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] b =  new byte[0];
            b = base64Decoder.decodeBuffer(baseStr[1]);
            for(int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            return new BASE64DecodedMultipartFile(b,baseStr[0]) ;
        }catch (Exception e){
            log.error("base64 文件转换发生异常!",e);
            return null;
        }
    }
	
	class BASE64DecodedMultipartFile implements MultipartFile {
	    private final  byte[] imgContent;
	    private final  String header;

	    public BASE64DecodedMultipartFile(byte[] imgContent, String header) {
	        this.imgContent = imgContent;
	        this.header = header.split(";")[0];
	    }

	    @Override
	    public String getName() {
	        return System.currentTimeMillis() + Math.random() + "." + header.split("/")[1];
	    }
	    @Override
	    public String getOriginalFilename() {
	        return System.currentTimeMillis() + (int)Math.random() * 10000 + "." + header.split("/")[1];
	    }

	    @Override
	    public String getContentType() {
	        return header.split(":")[1];
	    }

	    @Override
	    public boolean isEmpty() {
	        return imgContent == null || imgContent.length ==0;
	    }

	    @Override
	    public long getSize() {
	        return imgContent.length;
	    }

	    @Override
	    public byte[] getBytes() throws IOException {
	        return imgContent;
	    }

	    @Override
	    public InputStream getInputStream() throws IOException {
	        return new ByteArrayInputStream(imgContent);
	    }

	    @Override
	    public void transferTo(File file) throws IOException, IllegalStateException {
	    	FileOutputStream fos = new FileOutputStream(file);
	    	fos.write(imgContent);
	    	fos.close();
	    }
	}
}
