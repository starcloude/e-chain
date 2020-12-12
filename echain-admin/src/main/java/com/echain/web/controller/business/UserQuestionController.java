package com.echain.web.controller.business;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echain.common.beans.PageJsonResult;
import com.echain.common.beans.PageQuery;
import com.echain.domain.business.user.UserQuestion;
import com.echain.domain.sys.SysOperateLog;
import com.echain.domain.sys.SysUser;
import com.echain.service.business.UserQuestionService;
import com.echain.web.controller.AbsSupperController;

@RestController
@RequestMapping(value = "/api/business/user/question", method = { RequestMethod.POST, RequestMethod.GET })
public class UserQuestionController extends AbsSupperController {

	@Autowired
	private UserQuestionService userQuestionService;

	private final String promitionNameSpace = "vuser_question:";
	
	@RequiresPermissions(promitionNameSpace+"view")
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public PageJsonResult query(UserQuestion entity,PageQuery pageQuery) {
		if (entity == null) {
			entity = new UserQuestion();
		}
		SysUser user = getLoginUser();
		operateLoger.info(new SysOperateLog(user, "用户密码问题管理", "index"));
		Page<UserQuestion> iPageQuery = new Page<UserQuestion>(pageQuery.getPageNo(), pageQuery.getPageSize());
		IPage<UserQuestion> page = userQuestionService.select(iPageQuery, entity);
		return toPageJson(page);
	}
}
