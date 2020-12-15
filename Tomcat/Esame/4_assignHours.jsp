<%@ page import="java.sql.*" %>
<%@ page import="JAVA.*" %>
<%@ page import="JAVA.tester.*" %>

<%
    String jsonResult;
    MySqlDbConnection db = MySqlDbConnection.getInstance();

    int activity = 0;
    int week = 0;
    int specifications = 0;
    int maintainer = 0;
    String day = "";

    if (request.getParameter("activity") != null && request.getParameter("week") != null && request.getParameter("specifications") != null && request.getParameter("maintainer") != null && request.getParameter("day") != null) {
        activity = Integer.parseInt(request.getParameter("activity"));
        week = Integer.parseInt(request.getParameter("week"));
        specifications = Integer.parseInt(request.getParameter("specifications"));
        maintainer = Integer.parseInt(request.getParameter("maintainer"));
        day = request.getParameter("day");
    }

    AssignHoursBrowseServiceJSP service = new AssignHoursBrowseServiceJSP();
    response.setContentType("application/json");
    response.setHeader("Access-Control-Allow-Origin", "*");

%>
<%= service.getAssignHoursBrowseToJSONJSP(db, activity, week, specifications, maintainer, day) %>
