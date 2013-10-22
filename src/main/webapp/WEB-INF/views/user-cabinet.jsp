<%@ page import="static com.ibukanov.chat.util.MvcUtil.getUncapitalizedSimpleClassName" %>
<%@ page import="com.ibukanov.chat.form.UserForm" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>User Cabinet</h2>

<h4>There are your current credentials</h4>
<label>Full Name: ${fullName}</label></br>
<label>Nick Name: ${nickname}</label></br>
<label>Email: ${email}</label></br>
<label>Login: ${login}</label></br>

<h4>It's place to change some your credentials</h4>

<c:if test="${not empty duplicateNickname}">
    <jsp:include page="/WEB-INF/views/includes/form-error.jsp">
        <jsp:param name="form.error.text" value="This nickname already registered. Type another one." />
    </jsp:include>
</c:if>

<form:form method="post" action="/user-cabinet/" commandName="<%= getUncapitalizedSimpleClassName(UserForm.class) %>">
    <table style="margin-left: 42%">
        <tr>
            <td><form:label path="nickname">Nick Name</form:label></td>
            <td><form:input path="nickname" /></td>
        </tr>
        <tr>
            <td><form:label path="password">Password</form:label></td>
            <td><form:password path="password" /></td>
        </tr>
        <tr>
            <td><input type="submit" value="Change"/></td>
        </tr>
    </table>
</form:form>