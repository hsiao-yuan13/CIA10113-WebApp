<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>周邊商品資料管理</title>

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
   <tr><td><h3>周邊商品資料管理</h3><h4>( MVC )</h4></td></tr>
</table>


<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href="listAllMerch.jsp">顯示全部資料</a></li>
  <br>
  
  <li>
    <FORM METHOD="post" ACTION="merch.do" >
        <b>輸入商品編號:</b>
        <input type="text" name="merchID">
        <input type="hidden" name="action" value="findByPrimaryKey">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="merchSvc" scope="page" class="merch.model.MerchService" />
   
  <li>
     <FORM METHOD="post" ACTION="merch.do" >
       <b>選擇周邊商品編號:</b>
       <select size="1" name="merchID">
         <c:forEach var="merchVO" items="${merchSvc.all}" > 
          <option value="${merchVO.merchID}">${merchVO.merchID}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="findByPrimaryKey">
       <input type="submit" value="送出">
    </FORM>
  </li>

</ul>


<h3>周邊商品管理</h3>

<ul>
  <li><a href='addMerch.jsp'>Add</a>新增周邊商品</li>
</ul>

</body>
</html>