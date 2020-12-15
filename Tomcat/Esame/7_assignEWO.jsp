<%@ page import="java.sql.*" %>
<%@ page import="JAVA.*" %>
<%@ page import = "JAVA.tester.*" %>

<%
    String jsonResult;
    MySqlDbConnection db = MySqlDbConnection.getInstance();

    int activity = 0;
    int week = 0;
    int specifications = 0;
    String day = "";

    if (request.getParameter("activity") != null && request.getParameter("week") != null && request.getParameter("specifications") != null && request.getParameter("day") != null) {
        activity = Integer.parseInt(request.getParameter("activity"));
        week = Integer.parseInt(request.getParameter("week"));
        specifications = Integer.parseInt(request.getParameter("specifications"));
        day = request.getParameter("day");
    }

    AssignEWOBrowseServiceJSP service = new AssignEWOBrowseServiceJSP();
    response.setContentType("application/json");
    response.setHeader("Access-Control-Allow-Origin", "*");

%>
<%= service.getAssignEWOBrowseToJSONJSP(db, activity, week, specifications, day) %>
