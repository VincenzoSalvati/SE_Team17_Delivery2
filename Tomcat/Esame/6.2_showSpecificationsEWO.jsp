<%@ page import="java.sql.*" %>
<%@ page import="JAVA.*" %>

<%
    String jsonResult;
    MySqlDbConnection db = MySqlDbConnection.getInstance();

    int idSpec = 0;

    if (request.getParameter("activity") != null) {
        idSpec = Integer.parseInt(request.getParameter("activity"));
    }

    ShowSpecificationsEWOBrowseServiceJSP service = new ShowSpecificationsEWOBrowseServiceJSP();
    response.setContentType("application/json");
    response.setHeader("Access-Control-Allow-Origin", "*");
%>
<%= service.getSkills(db) %>
