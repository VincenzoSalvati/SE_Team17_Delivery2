<%@ page import="java.sql.*" %>
<%@ page import="JAVA.*" %>

<%
    MySqlDbConnection db = MySqlDbConnection.getInstance();

    int activity = 0;
    int week = 0;

    if (request.getParameter("activity") != null && request.getParameter("week") != null) {
        activity = Integer.parseInt(request.getParameter("activity"));
        week = Integer.parseInt(request.getParameter("week"));
    }

    ShowSpecificationsBrowseServiceJSP service = new ShowSpecificationsBrowseServiceJSP();

    response.setContentType("application/json");
    response.setHeader("Access-Control-Allow-Origin", "*");
%>

<%= service.getShowSpecificationsBrowseToJSONJSP(db, activity, week) %>
