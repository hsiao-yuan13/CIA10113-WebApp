<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="merch.model.*" %>

<%MerchVO merchVO = (MerchVO)request.getAttribute("merchVO");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>周邊商品資料新增</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>
</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>周邊商品資料新增</h3></td><td>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="merch.do" name="form1" enctype="multipart/form-data">
<table>
	
	
	
	
	<tr>
		<td>周邊商品名稱:</td>
		<td><input type="TEXT" name="merchName" value="<%= (merchVO==null)? "" : merchVO.getMerchName()%>" size="45"/></td>
	</tr>
	<tr>
		<td>周邊商品圖片:</td>
		<td><input type="FILE" name="merchImg" value="<%= (merchVO==null)? "" : merchVO.getMerchImg()%>"/></td>
	</tr>
	<tr>
		<td>周邊商品介紹:</td>
		<td><input type="TEXT" name="merchInfo" value="<%= (merchVO==null)? "" : merchVO.getMerchInfo()%>" size="45"/></td>
	</tr>
	<tr>
		<td>周邊商品價格:</td>
		<td><input type="NUMBER" name="merchPrice"  value="<%= (merchVO==null)? "" : merchVO.getMerchPrice()%>" size="45"/></td>
	</tr>
	<tr>
		<td>周邊商品狀態:</td>
		<td><select name="merchStatus" required>
				<option value="上架" selected>上架</option>
				<option value="下架">下架</option>
			</select>
		</td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>


</body>
</html>