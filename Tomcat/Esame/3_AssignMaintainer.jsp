<%@ page import="java.sql.*" %>
<%@ page import="JAVA.*" %>

<%
    MySqlDbConnection db = MySqlDbConnection.getInstance();

    int specifications = 0;

    if (request.getParameter("specifications") != null) {
        specifications = Integer.parseInt(request.getParameter("specifications"));
    }

    AssignMaintainerBrowseServiceJSP service = new AssignMaintainerBrowseServiceJSP();

    response.setContentType("application/json");
    response.setHeader("Access-Control-Allow-Origin", "*");
%>

<%= service.getAssignMaintainerBrowseToJSONJSP(db, specifications) %>
