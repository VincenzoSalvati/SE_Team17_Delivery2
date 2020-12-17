<%@ page import="java.sql.*" %>
<%@ page import="JAVA.*" %>

<%
    MySqlDbConnection db = MySqlDbConnection.getInstance();

    int specifications = 0;
    int maintainer = 0;


    if (request.getParameter("specifications") != null && request.getParameter("maintainer") != null ) {
        specifications = Integer.parseInt(request.getParameter("specifications"));
        maintainer = Integer.parseInt(request.getParameter("maintainer"));
    }

    InProgress inProg = new InProgress(db);

    inProg.setSpecifications(specifications);    
    inProg.setMaintainer(maintainer);
    inProg.setStateSpecif("NotStarted");
    inProg.setStateDep("Sent");
    inProg.setStateMaint("Sent");

    inProg.insert();

    response.setContentType("application/json");
    response.setHeader("Access-Control-Allow-Origin", "*");
%>

<%= inProg.getResults() %>
