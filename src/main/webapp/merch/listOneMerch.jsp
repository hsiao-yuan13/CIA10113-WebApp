<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="merch.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  MerchVO merchVO = (MerchVO)request.getAttribute("merchVO");
%>

<html>
<head>
<title>�P��ӫ~��Ƭd�ߵ��G</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�P��ӫ~��Ƭd�ߵ��G</h3>
		 <h4><a href="select_page.jsp">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�ӫ~�s��</th>
		<th>�ӫ~�W��</th>
		<th>�ӫ~�Ϥ�</th>
		<th>�ӫ~����</th>
		<th>�ӫ~����</th>
		<th>�ӫ~���A</th>
	</tr>
	<tr>
		<td>${merchVO.merchID}</td>
		<td>${merchVO.merchName}</td>
		<c:if test="${merchVO.merchImg==null}">
				<td>�L�Ϥ�</td>
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