<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>药品入库</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.mobile.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/easyloader.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>
<script>
var date=new Date();
var time=date.getFullYear()+"-0"+(date.getMonth())+"-"+date.getDate();
$(function() {
	$('#dg').datagrid({       
		pageNumber: 1,
	    url: "${pageContext.request.contextPath}/showBuy",
	    rownumbers:true,
	    fitColumns:true,
	    singleSelect:true,
	    height:650,
	    toolbar:[
	 			{
	 				text:'入库日期：<input id="timeselect" name="timehidden">'
	 			},
	 			{
	 				text:'供货单位：<input id="supplierselect" name="supplierhidden">'
	 			},
	 			{
					iconCls : 'icon-add',
					text : '入库登记',
					id:'addtool',
					handler : function() {
						$('#addbtn').show();
						$('#editbtn').hide();
						$('#win').window('open');
						$('#form').form('disableValidation');
					}
				},
				{
					iconCls : 'icon-edit',
					text : '修改记录',
					id:'edittool',
					handler : function() {	
					
					}
				},
				{
					iconCls : 'icon-remove',
					text : '删除记录',
					id:'deletetool',
					handler : function() {
						
					}
				}
	 			],
	    columns:[[    
	              {field:'id',title:'编号',width:100} 
	    ]]
	});

    //时间
    $('#timeselect').datebox({    
    	value:time,
    	width:120,
    	onSelect: function(date){
    		time=$('#timeselect').datebox('getValue');
    	}
    });  
    //供货单位
    $('#supplierselect').combobox({
    	url : '${pageContext.request.contextPath}/supplierBuy',
		valueField : 'supplierId',
		textField : 'supplierName',
		prompt : '请选择供应商'
	    });  
})
</script>
</head>
<body>
	<table id="dg"></table>
	<input id="timehidden" type="hidden">
	<div id="win" class="easyui-window" title="药物入库信息" closed="true" style="width: 950px; height: 400px; padding: 5px;">
		<br />
		<form id="form" method="post">
			<div style="margin-left: 30px">
				<div style="diapaly: inline">
					 <input name="nearMissSn" hidden/>
					 <label style="padding-right: 25px">查询药品：</label>
					 <input id="nearMissType" required="true" data-options="panelHeight:'auto'" name="nearMissTypeSn" />
					<label style="padding-right: 25px">事件名称：</label>
					 <input name="eventName" class="easyui-textbox easyui-validatebox" data-options="required:true,missingMessage:'请输入未遂事件名称'"> 
					 <label style="padding-right: 25px">发生日期：</label> 
					 <input name="happenDate" class="easyui-datebox" data-options="required:true,missingMessage:'请输入未遂事件发生日期'" />
					 <br/><br/>
					 <label style="padding-right: 25px">发生地点：</label> 
					 <input name="happenLocation" class="easyui-textbox easyui-validatebox" data-options="required:true,missingMessage:'请输入发生地点'" /> 
					 <label style="padding-right: 25px">风险等级：</label> 
					 <select name="riskLevel" class="easyui-combobox" data-options="panelHeight:'auto'" style="width: 175px">
						<option value="一般风险">一般风险</option>
						<option value="中等风险">中等风险</option>
						<option value="重大风险">重大风险</option>
					 </select> 
					 <label style="padding-right: 25px">发生区队：</label> 
					 <input value="${sessionScope['person'].department.departmentName}" readonly="true" iconCls="icon-lock" class="easyui-textbox" data-options="panelHeight:'auto'"/>					 
					 <br/>
				</div><br />
				<div id="tt" class="easyui-tabs" style="width:830px;height:200px;">   
				    <div title="事件过程 "  style="padding:20px;"> 
				      <input class="easyui-textbox" multiline="true" style="width:760px;height:120px" name="eventProcess"/>
				    </div>   
				    <div title="风险后果"   style="overflow:auto;padding:20px;">
				      <input class="easyui-textbox" name="riskResult" multiline="true" style="width:760px;height:120px" />
				    </div>   
				    <div title="防范措施" style="padding:20px;">   
				      <input class="easyui-textbox"  name="preventMeasure" multiline="true" style="width:760px;height:120px"/>
				    </div>   
				    <div title="原因分析" style="padding:20px;">   
				      <input class="easyui-textbox"  name="reasonAnalysis" multiline="true" style="width:760px;height:120px" />
				    </div>  
				</div> <br />
				<div id="dlg-buttons" style="text-align:center">
					<a id="addbtn" class="easyui-linkbutton" iconCls="icon-ok">确认登记</a>
					<a id="editbtn" class="easyui-linkbutton" iconCls="icon-ok">确认修改</a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>