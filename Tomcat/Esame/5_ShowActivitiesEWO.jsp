<%@ page import="java.sql.*" %>
<%@ page import="JAVA.*" %>

<%
    MySqlDbConnection db = MySqlDbConnection.getInstance();

    int week = 0;
    
    if (request.getParameter("week") != null) {
        week = Integer.parseInt(request.getParameter("week"));
    }

    ShowActivitiesEWOBrowseServiceJSP service = new ShowActivitiesEWOBrowseServiceJSP();

    response.setContentType("application/json");
    response.setHeader("Access-Control-Allow-Origin", "*");
%>

<%= service.getShowActivitiesEWOBrowseToJSONJSP(db, week) %>
