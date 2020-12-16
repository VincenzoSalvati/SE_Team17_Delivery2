<%@ page import="java.sql.*" %>
<%@ page import="JAVA.*" %>

<%
    MySqlDbConnection db = MySqlDbConnection.getInstance();

    int specifications = 0;
    String stringSkills = "";

    if (request.getParameter("specifications") != null && request.getParameter("stringSkills") != null) {
        specifications = Integer.parseInt(request.getParameter("specifications"));
        stringSkills = request.getParameter("stringSkills");
    }

    ShowSpecificationsEWOBrowseServiceJSP service = new ShowSpecificationsEWOBrowseServiceJSP();

    service.insertInNeed(db, specifications, stringSkills);

    response.setContentType("application/json");
    response.setHeader("Access-Control-Allow-Origin", "*");
%>

<%= service.getSkills(db) %>
