<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>�P��ӫ~��ƺ޲z</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>�P��ӫ~��ƺ޲z</h3><h4>( MVC )</h4></td></tr>
</table>


<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href="listAllMerch.jsp">��ܥ������</a></li>
  <br>
  
  <li>
    <FORM METHOD="post" ACTION="merch.do" >
        <b>��J�ӫ~�s��:</b>
        <input type="text" name="merchID">
        <input type="hidden" name="action" value="findByPrimaryKey">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="merchSvc" scope="page" class="merch.model.MerchService" />
   
  <li>
     <FORM METHOD="post" ACTION="merch.do" >
       <b>��ܩP��ӫ~�s��:</b>
       <select size="1" name="merchID">
         <c:forEach var="merchVO" items="${merchSvc.all}" > 
          <option value="${merchVO.merchID}">${merchVO.merchID}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="findByPrimaryKey">
       <input type="submit" value="�e�X">
    </FORM>
  </li>

</ul>


<h3>�P��ӫ~�޲z</h3>

<ul>
  <li><a href='addMerch.jsp'>Add</a>�s�W�P��ӫ~</li>
</ul>

</body>
</html>