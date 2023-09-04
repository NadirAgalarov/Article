<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <a href="<%=request.getContextPath()%>/user/article" class="navbar-brand">Articles</a>
        </div>
        <c:set var = "userName" scope = "session" value = "${userName}"/>
        <c:if test="${userName != null}">
            <ul class="navbar-nav navbar-collapse justify-content-end">
                <li><h4 style="color: white">${userName}</h4></li>
                <li><a href="<%=request.getContextPath()%>/logout" class="nav-link">Logout</a></li>
            </ul>
        </c:if>
    </nav>
</header>