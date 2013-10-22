<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8">
        <script type="text/javascript" src="/resources/js/jquery-2.0.3.min.js"></script>
        <title><tiles:insertAttribute name="title" ignore="true" /></title>
    </head>
    <body>
	    <tiles:insertAttribute name="header" />
        <div style="text-align: center;">
	        <tiles:insertAttribute name="body" />
        </div>
	    <tiles:insertAttribute name="footer" />
    </body>
</html>