<%@ page import="java.sql.*" %>
<%@ page import="JAVA.*" %>
<%@ include file="config.jsp" %>
<%
    String jsonResult;
    MySqlDbConnection db = new MySqlDbConnection();
    db.setDbUser(dbUser);
    db.setDbPassword(dbPassword);
    db.setDbName(dbName);

    int ids = 0;
    String ss = "";

    if (request.getParameter("idS") != null && request.getParameter("skillSelected") != null) {
        ids = Integer.parseInt(request.getParameter("idS"));
        ss = request.getParameter("skillSelected");
    }

    ShowSpecificationsEWOBrowseServiceJSP service = new ShowSpecificationsEWOBrowseServiceJSP();
    service.insertInNeed(db, ids, ss);

    response.setContentType("application/json");
    response.setHeader("Access-Control-Allow-Origin", "*");
%>
<%= service.getSingleSkill(db, ids) %>