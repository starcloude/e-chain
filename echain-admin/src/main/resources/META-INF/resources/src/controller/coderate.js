var codeRateHtml = "<div class=\"layui-fluid\" style=\"margin:0px; padding:0px;\">\n" + 
"<div class=\"layui-row layui-col-space15\">\n" + 
"  <div class=\"layui-col-md12\">\n" + 
"    <div class=\"layui-card\">\n" + 
"      <div class=\"layui-card-body\">\n" + 
"        <table class=\"layui-hide\" id=\"codeRateTable\" lay-filter=\"codeRateTable\"></table>\n" + 
"      </div>\n" + 
"    </div>\n" + 
"  </div>\n" + 
"</div>\n" + 
"</div>";

function showCodeRate(userId,userType){
	openDlg({title:"比例设置",content:codeRateHtml},function(layero,index){
		table.render({
		  	elem: '#codeRateTable'
		  	,url:'/api/order/codeRate/query'
		 	 ,method:'post'
		  	,where: {userId:userId,userType:userType}
		 	,response:{
		  		countName:'total'
		  		,dataName:'result'
		 	}
		  	,request: {
		  		pageName: 'pageNo' //页码的参数名称，默认：page
		  		,limitName: 'pageSize' //每页数据量的参数名，默认：limit
			}
		  	,cols: [[
		  		{minWidth:200, title: '支付类型',templet:function(d){return renderStatus(d.type,dict.codeType,true)}}
		    	,{minWidth:100, title: '比例',templet:function(d){return "<input class='layui-input' maxlength='6' value='"+d.rate+"'  id='rt_"+d.type+"'>"}}
		    	,{width:80, title: '操作',templet:function(d){
		    		//有权限,就显示按钮
		    		if(hasPermission('gm:rate:edit') ||hasPermission('qr:rate:edit')){
		    			return "<a class=\"layui-btn layui-btn-xs layui-btn-danger\" onclick='saveCodeRate("+JSON.stringify(d)+")' >确认</a>";
		    		}
		    		return "";
		    	}}
		  	]]
		  	,page: false
		  	,id:"tbl_code_rate_table"
			});
	});
}

function saveCodeRate(data){
	var rate = $("#rt_"+data.type).val();
	var postData = {userId:data.userId,id:data.id,userType:data.userType,type:data.type,rate:rate};
	Ajax.Post("/api/order/codeRate/save",postData,function(msg){
  		success('操作成功');
  	},function(msg,rs){
  		error(msg);
  	});
}