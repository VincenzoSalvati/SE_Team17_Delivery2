<%@ page import="java.sql.*" %>
<%@ page import="JAVA.*" %>

<%
    MySqlDbConnection db = MySqlDbConnection.getInstance();

    int week = 0;
    int specifications = 0;
    String day = "";

    if (request.getParameter("week") != null && request.getParameter("specifications") != null && request.getParameter("day") != null) {
        week = Integer.parseInt(request.getParameter("week"));
        specifications = Integer.parseInt(request.getParameter("specifications"));
        day = request.getParameter("day");
    }

    AssignEWOBrowseServiceJSP service = new AssignEWOBrowseServiceJSP();

    response.setContentType("application/json");
    response.setHeader("Access-Control-Allow-Origin", "*");
%>

<%= service.getAssignEWOBrowseToJSONJSP(db, week, specifications, day) %>
