<%@ page import="java.sql.*" %>
<%@ page import="JAVA.*" %>
<%@ page import = "JAVA.tester.*" %>

<%
    String jsonResult;

    MySqlDbConnection db = MySqlDbConnection.getInstance();

    int week = 0;
    if (request.getParameter("week") != null) {
        week = Integer.parseInt(request.getParameter("week"));
    }

    ShowActivitiesEWOCountBrowseServiceJSP service = new ShowActivitiesEWOCountBrowseServiceJSP();

    response.setContentType("application/json");
    response.setHeader("Access-Control-Allow-Origin", "*");
%>

<%= service.getShowActivitiesEWOCountBrowseToJSONJSP(db, week) %>
