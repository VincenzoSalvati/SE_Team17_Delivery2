<%@ page import="java.sql.*" %>
<%@ page import="JAVA.*" %>

<%
    MySqlDbConnection db = MySqlDbConnection.getInstance();

    int week = 0;

    if (request.getParameter("week") != null) {
        week = Integer.parseInt(request.getParameter("week"));
    }

    ShowActivitiesBrowseServiceJSP service = new ShowActivitiesBrowseServiceJSP();

    response.setContentType("application/json");
    response.setHeader("Access-Control-Allow-Origin", "*");
%>

<%= service.getShowActivitiesBrowseToJSONJSP(db, week) %>
