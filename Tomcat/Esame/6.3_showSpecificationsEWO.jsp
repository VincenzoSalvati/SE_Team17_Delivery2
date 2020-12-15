<%@ page import="java.sql.*" %>
<%@ page import="JAVA.*" %>

<%
    String jsonResult;
    MySqlDbConnection db = MySqlDbConnection.getInstance();

    int ids = 0;
    String ss = "";

    if (request.getParameter("idS") != null && request.getParameter("skillSelected") != null) {
        ids = Integer.parseInt(request.getParameter("idS"));
        ss = request.getParameter("skillSelected");
    }

    ShowSpecificationsEWOBrowseServiceJSP service = new ShowSpecificationsEWOBrowseServiceJSP();
    service.insertInNeed(db, ids, ss);

    response.setContentType("application/json");
    response.setHeader("Access-Control-Allow-Origin", "*");
%>
<%= service.getSkills(db) %>
