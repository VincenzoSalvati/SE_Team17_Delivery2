<%@ page import="java.sql.*" %>
<%@ page import="JAVA.*" %>
<%@ page import = "JAVA.tester.*" %>

<%
    String jsonResult;
    MySqlDbConnection db = MySqlDbConnection.getInstance();

    int activity = 0;
    int week = 0;
    int specifications = 0;
    if (request.getParameter("activity") != null && request.getParameter("week") != null && request.getParameter("specifications") != null) {
        activity = Integer.parseInt(request.getParameter("activity"));
        week = Integer.parseInt(request.getParameter("week"));
        specifications = Integer.parseInt(request.getParameter("specifications"));
    }

    AssignMaintainerBrowseServiceJSP service = new AssignMaintainerBrowseServiceJSP();
    response.setContentType("application/json");
    response.setHeader("Access-Control-Allow-Origin", "*");

%>
<%= service.getAssignMaintainerBrowseToJSONJSP(db, activity, week, specifications) %>
