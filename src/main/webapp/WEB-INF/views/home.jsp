<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h2>Passionate Chat</h2>

<label>Show last</label>
<select id ="count" name="count">
    <option ${param.count == 50 ? 'selected' : ''} value="50">50</option>
    <option ${param.count == 100 ? 'selected' : ''} value="100">100</option>
    <option ${param.count == 200 ? 'selected' : ''} value="200">200</option>
    <option ${param.count == 0 ? 'selected' : ''} value="0">all</option>
</select>
<label>records</label>

<p style="background: #69AF04; margin-left: 25%; margin-right: 25%; text-align: left; color: white; padding: 5px;
          border: 20px red solid;"
   id="chat"></p>

<sec:authorize access="hasRole('ROLE_USER')">
    <label for="message">Send Message</label>
    </br>
    <textarea style="width: 50%; height: 50px;" id="message" name="message" ></textarea>
    </br>
    <input type="button" onclick="saveRecord()" value="Send"/>
</sec:authorize>

<script type="text/javascript" src="../static/js/view/home.js"></script>