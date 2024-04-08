<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="merch.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  MerchVO merchVO = (MerchVO)request.getAttribute("merchVO");
%>

<html>
<head>
<title>周邊商品資料查詢結果</title>

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
	width: 600px;
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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>周邊商品資料查詢結果</h3>
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
	</tr>
</table>

</body>
</html>