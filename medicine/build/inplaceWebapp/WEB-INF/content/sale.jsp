<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>销售录入</title>
<link href="css/sale.css" rel="stylesheet">
</head>
<body>
<div>
	<div class="topbar">
		<form action="queryMedicine.action" method="post">
			查询药品：<input name="medicine_id" type="text"/><input type="submit" value="查询"/><i>&nbsp;条码</i>
		</form><br/>
		<s:iterator value="#request.mdinformation" id="mdinfo" status="st">
		<form action="addItem" method="post">
			<b>药品ID：</b><s:property value="#mdinfo.medicine_id"/>&nbsp;&nbsp;
			<b>药品名：</b><s:property value="#mdinfo.medicineName"/>&nbsp;&nbsp;
			<b>药品价格：</b><s:property value="#mdinfo.price"/>&nbsp;&nbsp;
			<b>销售数量：</b><input type="text" name="store" value="1"/>
			<input type="submit" value="添加"/>
		</form>
        </s:iterator>
        </div>
        <div>
        <tr>
        		<td>序号</td>
        		<td>药品ID</td>
        		<td>药品名</td>
        		<td>单价</td>
        		<td>数量</td>
        	</tr>
        <%
    List saleList= (List) session.getAttribute("sale");
        if (saleList==null||saleList.size()==0){}else{
      for(int t = 0; t < saleList.size(); t++){
    %>
        	<tr>
        		<td>t+1</td>
        		<td>saleList.get(t).medicine_id</td>
        		<td>saleList.get(t).medicineName"</td>
        		<td>saleList.get(t).price"</td>
        		<td>saleList.get(t).saleRecordNumber"</td>
        	</tr>
        <%} }%>
        </div>
</div>
</body>