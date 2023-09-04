<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Articles</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div style="text-align: center;  padding: 20px">
<a style="background-color:cadetblue "  href="<%=request.getContextPath()%>/user/article" class="btn btn-success">All Article</a>
<a style="background-color: cadetblue"  href="<%=request.getContextPath()%>/user/article/actions?action=my-article" class="btn btn-success">My Article </a>
<a style="background-color: green" href="<%=request.getContextPath()%>/user/article/actions?action=new" class="btn btn-success">Add Article</a>
</div>
<br>
<hr>
<div class="container">
    <h2 style="text-align: center">Articles</h2>
    <br>

        <c:forEach var="article" items="${articles}">

           <h3 style="margin-left: 40px"><u><c:out value="${article.title}" /></u></h3>

           <div  style=" padding:20px;margin:20px;border-color: black;border-width: 1px;border-style: groove">
               <p> <c:out value="${article.article}" /></p>
           </div>

           <h5 style="margin: 20px"> <c:out value="${article.createdAt.toLocalDate()}" />
               <c:set var = "userName" scope = "session" value = "${userName}"/>
               <c:if test="${userName == article.userName}">
                   <div style="float: right;">
                       <a href="<%=request.getContextPath()%>/user/article/actions?action=edit&id=<c:out value='${article.id}'/>" class="btn btn-success" >Edit</a>
                       &nbsp;&nbsp;&nbsp;&nbsp;
                       <a href="<%=request.getContextPath()%>/user/article/actions?action=delete&id=<c:out value='${article.id}'/>" class="btn btn-success" >Delete</a>
                   </div>
               </c:if>
           </h5>
            <hr>

        </c:forEach>


</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>