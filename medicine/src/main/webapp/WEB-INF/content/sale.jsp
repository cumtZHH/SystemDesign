<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>销售录入</title>
<link href="css/sale.css" rel="stylesheet">
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.mobile.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/easyloader.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>

<script>
		function menuHandler(item){
			$('#log').prepend('<p>Click Item: '+item.name+'</p>');
		}
		$(function(){
			$(document).bind('contextmenu',function(e){
				e.preventDefault();
				$('#mm').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
			});
		});
	</script>
</head>
<body>
<!--框框  -->
<div style="margin:20px 0;"></div>
	<div id="log" class="easyui-panel" title="销售录入" style="height:200px;padding:10px;width:860px;">
		<div class="topbar">
			<form action="queryMedicine.action" method="post">
				查询药品：<input class="easyui-textbox" name="medicine_id" type="text"/><input class="easyui-linkbutton" type="submit" value="查询"/><i>&nbsp;条码</i>
			</form><br/>
			<s:iterator value="#request.mdinformation" id="mdinfo" status="st">
				<form action="addItem" method="post">
				<b>药品ID：</b><s:property value="#mdinfo.medicine_id"/>&nbsp;&nbsp;<input name="medicine_id" type="hidden" value="<s:property value="#mdinfo.medicine_id"/>">
				<b>药品名：</b><s:property value="#mdinfo.medicineName"/>&nbsp;&nbsp;<input name="medicineName" type="hidden" value="<s:property value="#mdinfo.medicineName"/>">
				<b>药品价格：</b><s:property value="#mdinfo.price"/>&nbsp;&nbsp;<input name="price" type="hidden" value="<s:property value="#mdinfo.price"/>">
				<b>库存：</b><s:property value="#mdinfo.store"/>&nbsp;&nbsp;<input name="store" type="hidden" value="<s:property value="#mdinfo.store"/>">
				<b>销售数量：</b><input type="text" name="saleRecordNumber" value="1"/>
				<input type="submit" value="添加"/>
				</form>
        	</s:iterator>
       </div>
	</div>
	<!--销售记录显示  -->
    <div>
        <table id="table" class="easyui-datagrid" title="销售单" style="width:860px;height:250px">
<!--         			data-options="singleSelect:true,collapsible:true,url:'datagrid_data1.json',method:'get'"
 -->		<thead>
			<tr>
				<th data-options="field:'itemid',width:100,align:'center'">序号</th>
				<th data-options="field:'productid',width:160,align:'center'">药品ID</th>
				<th data-options="field:'listprice',width:250,align:'center'">药品名</th>
				<th data-options="field:'unitcost',width:100,align:'center'">单价</th>
				<th data-options="field:'attr1',width:100,align:'center'">数量</th>
				<th data-options="field:'number',width:100,align:'center'">小计</th>
			</tr>
		 </thead>
		 <tbody>
			 <s:iterator value="#session.sale" id="sl" status="st">
        		<tr>
        			<td><s:property value="#st.count"/></td>
        			<td><s:property value="#sl.medicine_id"/></td>
        			<td><s:property value="#sl.medicineName"/></td>
        			<td><s:property value="#sl.price"/></td>
        			<td><s:property value="#sl.saleRecordNumber"/></td>
        			<td><s:property value="#sl.subTotal"/></td>
        		</tr>
        	</s:iterator>
		</tbody>
		<tr>
		<td><b>总计</b></td>
		<td> </td><td> </td><td> </td><td> </td>
		<td><b><s:property value="#session.ptotal"/></b></td>
		</tr>
	</table>
	<div class="footbar">
		<form action="addSale" method="post">
		<input type="hidden" name="saleRecordTotalPrice" value="<s:property value="#session.ptotal"/>"/>
		<input class="easyui-linkbutton fr" type="submit" value="确认"/>
	    </form>
	</div>
    </div>
</div>
</body>
</html>