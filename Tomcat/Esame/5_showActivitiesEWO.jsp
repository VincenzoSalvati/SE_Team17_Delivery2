<%@ page import="java.sql.*" %>
<%@ page import="JAVA.*" %>
<%@ include file="config.jsp" %>

<%
    String jsonResult;

    MySqlDbConnection db = new MySqlDbConnection();
    db.setDbUser(dbUser);
    db.setDbPassword(dbPassword);
    db.setDbName(dbName);

    int week = 0;
    if (request.getParameter("week") != null) {
        week = Integer.parseInt(request.getParameter("week"));
    }

    ShowActivitiesEWOBrowseServiceJSP service = new ShowActivitiesEWOBrowseServiceJSP();

    response.setContentType("application/json");
    response.setHeader("Access-Control-Allow-Origin", "*");
%>

<%= service.getShowActivitiesEWOBrowseToJSONJSP(db, week) %>