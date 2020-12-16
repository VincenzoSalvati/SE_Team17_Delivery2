<%@ page import="java.sql.*" %>
<%@ page import="JAVA.*" %>

<%
    MySqlDbConnection db = MySqlDbConnection.getInstance();

    int id = 0;
    int estimate_tr = 0;
    String int_des = "0";
    int week = 0;
    int ewo = 0;

    if (request.getParameter("id") != null && request.getParameter("estimate_tr") != null && request.getParameter("week") != null && request.getParameter("ewo") != null) {
        id = Integer.parseInt(request.getParameter("id"));
        estimate_tr = Integer.parseInt(request.getParameter("estimate_tr"));
        week = Integer.parseInt(request.getParameter("week"));
        ewo = Integer.parseInt(request.getParameter("ewo"));
    } else if (request.getParameter("id") != null && request.getParameter("int_des") != null) {
        id = Integer.parseInt(request.getParameter("id"));
        int_des = request.getParameter("int_des");
    }

    SpecificationsEWO se = new SpecificationsEWO(db, id);

    if (estimate_tr != 0 && int_des == "0") {
        se.setNew_estimated_rt(estimate_tr);
        se.setWeek(week);
        se.setEwo(ewo);
        se.updateEstimateTr();
    } else if (estimate_tr == 0 && int_des != "0") {
        se.setNew_int_des(int_des);
        se.updateIntDes();
    }

    response.setContentType("application/json");
    response.setHeader("Access-Control-Allow-Origin", "*");
%>

<%= se.getDbOperationMessageEstimateTr() %>
