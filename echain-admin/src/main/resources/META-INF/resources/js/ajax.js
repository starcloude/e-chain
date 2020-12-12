/**
 * 公共JS 对象
 *
 * @author 作者B
 * @time 2014-01-17
 */

var Ajax = {
    // 定义普通的ajax请求
    // url,参数,是否开启遮罩,请求方法(POST/GET/),成功回调函数,失败回调函数
    doAjax : function(url, params, method, suc, err) {
        if (method != "POST" && method != "GET" && method != "DELETE"
            && method != "PUT") {
            alert("执行失败,method不合法");
            return;
        }
        $.ajax({
            url : url,
            type : method,
            data : params,
            dataType : "json",
            beforeSend : function(xhr) {
                xhr.setRequestHeader('response-type', 'json');
            },// 标识为ajax请求
            success : function(response) {
                if (response["success"] == false) {// 处理失败
                    if (typeof (err) == "function") {
                        err(response["msg"], response);
                    } else {
                    	console.log(response["msg"]);
                    }
                } else {// 处理成功
                    if (typeof (suc) == "function") {
                        suc(response["msg"], response.result,response);
                    } else {
                        console.log(response["msg"]);
                    }
                }
            },
            error : function(jqXHR, textStatus, errorThrown) {
                if (typeof (err) == "function") {
                    err("请求失败,httpStatus:" + textStatus);
                } else {
                    alert("执行失败," + textStatus);
                }
            }
        });
    },

    Post : function(url, params, suc, err) {
        this.doAjax(url, params, "POST", suc, err);
    },

    Get : function(url, params, suc, err) {
        this.doAjax(url, params, "GET", suc, err);
    },

    Delete : function(url, params, suc, err) {
        this.doAjax(url, params, "DELETE", suc, err);
    },

    Put : function(url, params, suc, err) {
        this.doAjax(url, params, "PUT", suc, err);
    },

    //表单提交
    PostForm : function(formId,url,suc,err){
        if(url==null || url=="" || url==undefined){
            url = $("#"+formId).attr("action");
        }
        var data = $("#"+formId).serialize();
        var method = $("#"+formId).attr("method");
        this.doAjax(url,data,method,suc,err);
    },

    /**
     * 此方式只适用于返回HTML的情况
     */
    PostForHtml: function(url,params,suc,err){
        $.ajax({
            url:url,type:"POST",data:params,
            success:function(response){
                suc(response);
            },
            error:function(jqXHR,textStatus,errorThrown){
                if (typeof (err) == "function") {
                    err("请求失败,httpStatus:"+textStatus);
                }
            }
        });
    },
    /**
     * 此方式只适用于返回HTML的情况
     */
    GetForHtml: function(url,params,suc,err){
        $.ajax({
            url:url,type:"get",data:params,
            success:function(response){
                suc(response);
            },
            error:function(jqXHR,textStatus,errorThrown){
                if (typeof (err) == "function") {
                    err("请求失败,httpStatus:"+textStatus);
                }
            }
        });
    }
};
//form to json
$.fn.serializeJson=function(ignoreNull){
    ignoreNull=ignoreNull||false;
    var serializeObj={};
    $(this.serializeArray()).each(function(){
        if(ignoreNull){
            if(this.value.trim()!=""){
                serializeObj[this.name]=this.value.trim();
            }
        }else{
            serializeObj[this.name]=this.value.trim();
        }
    });
    return serializeObj;
};

renderTemplate = function(template, obj) {
    return template.replace(/[{]{2}([^}]+)[}]{2}/g, function($0, $1) {
        //obj[$1]==undefined
        //return obj[$1] || '';
        var val = obj[$1];
        return (val == undefined || val == null) ? "":val;
    });
};