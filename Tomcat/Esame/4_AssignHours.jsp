<%@ page import="java.sql.*" %>
<%@ page import="JAVA.*" %>

<%
    MySqlDbConnection db = MySqlDbConnection.getInstance();

    int week = 0;
    int specifications = 0;
    int maintainer = 0;
    String day = "";

    if (request.getParameter("week") != null && request.getParameter("specifications") != null && request.getParameter("maintainer") != null && request.getParameter("day") != null) {
        week = Integer.parseInt(request.getParameter("week"));
        specifications = Integer.parseInt(request.getParameter("specifications"));
        maintainer = Integer.parseInt(request.getParameter("maintainer"));
        day = request.getParameter("day");
    }

    AssignHoursBrowseServiceJSP service = new AssignHoursBrowseServiceJSP();

    response.setContentType("application/json");
    response.setHeader("Access-Control-Allow-Origin", "*");
%>

<%= service.getAssignHoursBrowseToJSONJSP(db, week, specifications, maintainer, day) %>
