//是否有权限
function hasPermission(code,yes,no){
	var has = layui.setter.hasPermission(code);
	if(has && typeof (yes) == "function"){yes();}
	if(!has && typeof (no) == "function"){no();}
	return has;
}
  
//根路径
function apiRoot(){
	return layui.setter.api_root;
}
  
//没权限直接跳转
function checkPromision(code){
	hasPermission(code,null,function(){
		location.hash = '/errors/403';
	});
}

function warn(msg,callBack){
	if(typeof (msg) == "function"){callBack = msg;msg = "";}
	layer.alert(msg||"警告",{icon: 7},function(idx){execCallBack(idx,callBack);});
}

function success(msg,callBack){
	if(typeof (msg) == "function"){callBack = msg;msg = "";}
	layer.msg(msg||"操作成功",{icon: 1,time:1500},callBack);
}

function error(msg,callBack){
	if(typeof (msg) == "function"){callBack = msg;msg = "";}
	layer.alert(msg||"操作失败",{icon: 2},function(idx){execCallBack(idx,callBack);});
}

function execCallBack(index,callBack){
	layer.close(index); 
	if(typeof (callBack) == "function"){callBack();}
}

/**
 * render status;
 * @param status
 * @param statusMap
 * @param pure
 * @returns
 */
function renderStatus(status,statusMap,pure){
	var s = statusMap[status]; 
	s = s||{};
	pure = pure || false;
	//纯文本
	if(pure){
		return s.name||"未知";
	}
	return "<span style='border-radius:3px;padding:3px 5px;background-color:"+(s.color||"rgb(0, 150, 136)")+";color:#FFF;'>"+(s.name||"未知")+"</span>";
}

/**
 * YN 定义
 */
var ynMap = {
	0:{color:"rgb(0, 150, 136)",name:"有效"},
	1:{color:"#827f7a",name:"无效"}
};

/**
 * render Yn
 */
function renderYn(yn,pure){
	return renderStatus(yn,ynMap,pure);
}

/**
 * 加载字典
 * @param codes
 * @param callBack
 * @returns
 */
function loadDict(codes,callBack){
	Ajax.Post(apiRoot()+"/api/common/get",{codes:codes},function(msg,rst){
		var dictMap = {};
		 $.each(rst, function (key, value) {
			var obj = {}; 
			 $.each(value,function(iKey,iValue){
				 var obji = {};
				 if(typeof(iValue) == 'string'){
					 obji["name"] = iValue;
				 }else{
					 obji = iValue;
				 }
				 obj[iKey] = obji;
			 });
			 dictMap[key]  = obj;
         });
		 if(typeof (callBack) == "function"){callBack(dictMap);}
	});
}

/** 获取HTML
 * @param varHtml
 * @param scriptId
 * @returns
 */
function getHtmlWithCache(varHtml,scriptId){
	if(!varHtml){
		varHtml = scriptId.innerHTML;
	}
	return varHtml;
}

//渲染字典里到select
function renderSelect(ele,dict,defaultValue){
	$.each(dict,function(key,value){
		ele.append("<option value='"+key+"' "+ (defaultValue!=null && key == defaultValue ? "selected":"" ) +">"+(value.name||value)+"</option>");
	});
}

//基础弹窗
const _dlgDetaultConfig = {
		type:1
		,title: 'title'
		,content: "content"
		,shadeClose:true
		,area:['480px','385px']
		,offset:'60px'
};

/**
 * 通用弹窗
 * @param option
 * @param success
 * @param yes
 * @returns
 */
function openDlg(option,success,yes){
	var cfg = {..._dlgDetaultConfig,...option,
		yes:function(index,layero){
			if(typeof (yes) == "function"){yes(layero,index);return;}
			if(typeof (option.yes) == "function"){option.yes(index,layero);}
		},
		success:function(layero,index){
			layero.find(".layui-layer-btn").css("padding-top", "0px");
			if(typeof (success) == "function"){success(layero,index);return;}
			if(typeof (option.success) == "function"){option.success(layero,index);}
		}
	};
	
	layer.open(cfg);
}

/**
* 日期格式化
*/
function renderDate(date,format){
	return date == null ? "":layui.util.toDateString(date, format||'yyyy-MM-dd HH:mm:ss');
}

/**
* confirm
**/
function confirm(context,yesfunction,yesBtn,noBtn){
	layer.confirm(context, {
	  btn: [yesBtn||'确认',noBtn||'取消']
	}, 
	function(index){
		if(typeof (yesfunction) == "function"){
			yesfunction(index);
		}
	});
}


var HtmlUtil = {
        /*1.用浏览器内部转换器实现html编码（转义）*/
        htmlEncode:function (html){
            //1.首先动态创建一个容器标签元素，如DIV
            var temp = document.createElement ("div");
            //2.然后将要转换的字符串设置为这个元素的innerText或者textContent
            (temp.textContent != undefined ) ? (temp.textContent = html) : (temp.innerText = html);
            //3.最后返回这个元素的innerHTML，即得到经过HTML编码转换的字符串了
            var output = temp.innerHTML;
            temp = null;
            return output;
        },
        /*2.用浏览器内部转换器实现html解码（反转义）*/
        htmlDecode:function (text){
            //1.首先动态创建一个容器标签元素，如DIV
            var temp = document.createElement("div");
            //2.然后将要转换的字符串设置为这个元素的innerHTML(ie，火狐，google都支持)
            temp.innerHTML = text;
            //3.最后返回这个元素的innerText或者textContent，即得到经过HTML解码的字符串了。
            var output = temp.innerText || temp.textContent;
            temp = null;
            return output;
        },
        /*3.用正则表达式实现html编码（转义）*/
        htmlEncodeByRegExp:function (str){
             var temp = "";
             if(str.length == 0) return "";
             temp = str.replace(/&/g,"&");
             temp = temp.replace(/</g,"<");
             temp = temp.replace(/>/g,">");
             temp = temp.replace(/\s/g," ");
             temp = temp.replace(/\'/g,"'");
             temp = temp.replace(/\"/g,"\"");
             return temp;
        },
        /*4.用正则表达式实现html解码（反转义）*/
        htmlDecodeByRegExp:function (str){
             var temp = "";
             if(str.length == 0) return "";
             temp = str.replace(/&/g,"&");
             temp = temp.replace(/</g,"<");
             temp = temp.replace(/>/g,">");
             temp = temp.replace(/ /g," ");
             temp = temp.replace(/'/g,"\'");
             temp = temp.replace(/"/g,"\"");
             return temp;
        },
        /*5.用正则表达式实现html编码（转义）（另一种写法）*/
        html2Escape:function(sHtml) {
             return sHtml.replace(/[<>&"]/g,function(c){return {'<':'<','>':'>','&':'&','"':'"'}[c];});
        },
        /*6.用正则表达式实现html解码（反转义）（另一种写法）*/
        escape2Html:function (str) {
             var arrEntities={'lt':'<','gt':'>','nbsp':' ','amp':'&','quot':'"'};
             return str.replace(/&(lt|gt|nbsp|amp|quot);/ig,function(all,t){return arrEntities[t];});
        }
    };