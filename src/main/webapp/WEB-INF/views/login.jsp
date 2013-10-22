<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Login</h2>

<c:if test="${not empty userNotFound}">
    <jsp:include page="/WEB-INF/views/includes/form-error.jsp">
        <jsp:param name="form.error.text" value="Looks like you forgot your credentials" />
    </jsp:include>
</c:if>

<form method="POST" action="<c:url value='j_spring_security_check' />" >
    <table style="margin-left: 42%">
        <tr>
            <td><label for="j_username">Login</label></td>
            <td><input type="text" name="j_username" id="j_username" /></td>
        </tr>
        <tr>
            <td><label for="j_password">Password</label></td>
            <td><input type="password" name="j_password" id="j_password" /></td>
        </tr>
        <tr>
            <td><input type="submit" value="Go forward"/></td>
        </tr>
    </table>
</form>