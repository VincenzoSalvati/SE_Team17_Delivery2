<%@ page import="java.sql.*" %>
<%@ page import="JAVA.*" %>

<%
    MySqlDbConnection db = MySqlDbConnection.getInstance();

    ShowSpecificationsEWOBrowseServiceJSP service = new ShowSpecificationsEWOBrowseServiceJSP();

    response.setContentType("application/json");
    response.setHeader("Access-Control-Allow-Origin", "*");
%>

<%= service.getSkills(db) %>
