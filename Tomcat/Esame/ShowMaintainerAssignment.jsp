<%@ page import="java.sql.*" %>
<%@ page import="JAVA.*" %>

<%
    MySqlDbConnection db = MySqlDbConnection.getInstance();

    int id_maint = 0;
    
    if (request.getParameter("id_maint") != null) {
        id_maint = Integer.parseInt(request.getParameter("id_maint"));
    }

    ShowMaintainerBrowseService service = new ShowMaintainerBrowseService();

    response.setContentType("application/json");
    response.setHeader("Access-Control-Allow-Origin", "*");
%>

<%= service.getShowMaintainerBrowseToJSONJSP(db, id_maint) %>
