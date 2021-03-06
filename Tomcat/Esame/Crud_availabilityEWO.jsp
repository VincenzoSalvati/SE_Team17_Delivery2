<%@ page import="java.sql.*" %>
<%@ page import="JAVA.*" %>

<%
    MySqlDbConnection db = MySqlDbConnection.getInstance();

    int week = 0;
    int id_maint = 0;
	String day = "";
    int avail_8to9 = 0;
    int avail_9to10 = 0;
    int avail_10to11 = 0;
    int avail_11to12 = 0;
    int avail_14to15 = 0;
    int avail_15to16 = 0;
    int avail_16to17 = 0;

    if (request.getParameter("week") != null && request.getParameter("id_maint") != null && request.getParameter("day") != null && request.getParameter("avail_8to9") != null && request.getParameter("avail_9to10") != null && request.getParameter("avail_10to11") != null && request.getParameter("avail_11to12") != null && request.getParameter("avail_14to15") != null && request.getParameter("avail_15to16") != null && request.getParameter("avail_16to17") != null) {
    	week= Integer.parseInt(request.getParameter("week"));
        id_maint = Integer.parseInt(request.getParameter("id_maint"));
        day = request.getParameter("day");
        avail_8to9 = Integer.parseInt(request.getParameter("avail_8to9"));
        avail_9to10 = Integer.parseInt(request.getParameter("avail_9to10"));
        avail_10to11 = Integer.parseInt(request.getParameter("avail_10to11"));
        avail_11to12 = Integer.parseInt(request.getParameter("avail_11to12"));
        avail_14to15 = Integer.parseInt(request.getParameter("avail_14to15"));
        avail_15to16 = Integer.parseInt(request.getParameter("avail_15to16"));
        avail_16to17 = Integer.parseInt(request.getParameter("avail_16to17"));
    }

    AvailabilityEWO avail = new AvailabilityEWO(db);

    avail.setWeek(week);
    avail.setIdMaint(id_maint);
    avail.setDay(day);
    avail.setAvail_8to9(avail_8to9);
    avail.setAvail_9to10(avail_9to10);
    avail.setAvail_10to11(avail_10to11);
    avail.setAvail_11to12(avail_11to12);
    avail.setAvail_14to15(avail_14to15);
    avail.setAvail_15to16(avail_15to16);
    avail.setAvail_16to17(avail_16to17);
    avail.update();

    response.setContentType("application/json");
    response.setHeader("Access-Control-Allow-Origin", "*");
%>

<%= avail.getResults() %> 