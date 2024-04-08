<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="merch.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    MerchService merchSvc = new MerchService();
    List<MerchVO> list = merchSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有周邊商品資料</title>

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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有周邊商品資料</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>商品編號</th>
		<th>商品名稱</th>
		<th>商品圖片</th>
		<th>商品介紹</th>
		<th>商品價格</th>
		<th>商品狀態</th>

	</tr>

	<c:forEach var="merchVO" items="${list}">
		
		<tr>
			<td>${merchVO.merchID}</td>
			<td>${merchVO.merchName}</td>
			<c:if test="${merchVO.merchImg==null}">
					<td>無圖片</td>
				</c:if>
				<c:if test="${merchVO.merchImg!=null}">
					<td><img src="<%=request.getContextPath()%>/ShowPic?memberId=${memVO.memberId}"></td>
			</c:if>
			<td>${merchVO.merchInfo}</td>
			<td>${merchVO.merchPrice}</td>
			<td>${merchVO.merchStatus}</td> 
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>merch.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="merchID"  value="${merchVO.merchID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>

		</tr>
	</c:forEach>
</table>

</body>
</html>