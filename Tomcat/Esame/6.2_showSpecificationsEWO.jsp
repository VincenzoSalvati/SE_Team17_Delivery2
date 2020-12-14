<%@ page import="java.sql.*" %>
<%@ page import="JAVA.*" %>
<%@ include file="config.jsp" %>
<%
    String jsonResult;
    MySqlDbConnection db = new MySqlDbConnection();
    db.setDbUser(dbUser);
    db.setDbPassword(dbPassword);
    db.setDbName(dbName);

    int idSpec = 0;

    if (request.getParameter("activity") != null) {
        idSpec = Integer.parseInt(request.getParameter("activity"));
    }

    ShowSpecificationsEWOBrowseServiceJSP service = new ShowSpecificationsEWOBrowseServiceJSP();
    response.setContentType("application/json");
    response.setHeader("Access-Control-Allow-Origin", "*");
%>
<%= service.getSingleSkill(db, idSpec) %>