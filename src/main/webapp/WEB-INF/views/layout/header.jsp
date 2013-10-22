<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<header style="width: 100%; height: 65px; background-color: #69AF04; text-align: center;">
	<h2 style="color: #ffffff; float: left; margin: 15px">
        Hi
        <sec:authorize access="hasRole('ROLE_USER')">
            <sec:authentication property="principal.username" />!
        </sec:authorize>
        <sec:authorize access="!hasRole('ROLE_USER')">
            Dude!
        </sec:authorize>
        It's <a href="/">The Most Passionate Chat</a> which you have ever seen!
        <sec:authorize access="hasRole('ROLE_USER')">
            Add fuel to the fire!!!
        </sec:authorize>
        <sec:authorize access="!hasRole('ROLE_USER')">
            Let's fast login !!!
        </sec:authorize>
    </h2>

    <div style="float: right; margin: 10px">
        <sec:authorize access="hasRole('ROLE_USER')">
            <a href="/user-cabinet/">User cabinet</a>
            </br>
            <a href="<c:url value='/j_spring_security_logout' />" >Logout</a>
        </sec:authorize>
        <sec:authorize access="!hasRole('ROLE_USER')">
            <a href="/login">Log in</a></br>
            <a href="/register">Sign up</a>
        </sec:authorize>
    </div>
</header>