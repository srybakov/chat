<%@ page import="com.ibukanov.chat.form.RegisterForm" %>
<%@ page import="static com.ibukanov.chat.util.MvcUtil.getUncapitalizedSimpleClassName" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h2>Registration</h2>

<c:if test="${not empty duplicateEmail}">
    <jsp:include page="/WEB-INF/views/includes/form-error.jsp">
        <jsp:param name="form.error.text" value="This user already registered. Type another one." />
    </jsp:include>
</c:if>

<form:form method="post" action="/register/" commandName="<%= getUncapitalizedSimpleClassName(RegisterForm.class) %>">
    <table style="margin-left: 42%">
        <tr>
            <td><form:label path="fullName">Full Name</form:label></td>
            <td><form:input path="fullName" /></td>
        </tr>
        <tr>
            <td><form:label path="nickname">Nick Name</form:label></td>
            <td><form:input path="nickname" /></td>
        </tr>
        <tr>
            <td><form:label path="email">Email</form:label></td>
            <td><form:input path="email" /></td>
        </tr>
        <tr>
            <td><form:label path="login">Login</form:label></td>
            <td><form:input path="login" /></td>
        </tr>
        <tr>
            <td><form:label path="password">Password</form:label></td>
            <td><form:password path="password" /></td>
        </tr>
        <tr>
            <td><input type="submit" value="Register"/></td>
        </tr>
    </table>
</form:form>