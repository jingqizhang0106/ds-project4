<%@ page import="ds.project4task2.models.Log" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Dashboard</title>
    <meta name="description" content="Showing 3 Operation Analytics and full logs">
    <link href="bootstrap.css" rel="stylesheet">
</head>
<body>
<h1>Operation Analytics</h1><br>
<h3><b> 1. Average Response Time: </b></h3>
<p> <%= request.getSession().getAttribute("avgResponseTime")+" ms" %></p>
<br>
<h3> 2. Top 10 Frequent Search Term: </h3>
<table class="table table-striped">
    <thead>
    <tr>
        <th>Rank </th>
        <th>Search Term</th>
    </tr>
    </thead>
    <tbody>
    <% List<String> topSearch = (List<String>) request.getSession().getAttribute("Top10FrequentSearchTerm");%>
    <% int i=1; %>
    <% for(String searchTerm:topSearch) { %>
    <tr>
        <td><%= i++ %></td>
        <td><%= searchTerm %></td>
        <% } %>
    </tr>
    </tbody>
</table>
<br>
<h3> 3. Top 5 Frequent Device Type: </h3>
<table class="table table-striped">
    <thead>
    <tr>
        <th>Rank </th>
        <th>Device Type</th>
    </tr>
    </thead>
    <tbody>
    <% List<String> topDevice = (List<String>) request.getSession().getAttribute("Top5FrequentDeviceType");%>
    <% i=1;%>
    <% for(String device:topDevice) { %>
    <tr>
        <td><%= i++ %></td>
        <td><%= device %></td>
        <% } %>
    </tr>
    </tbody>
</table>
<br>
<br>
<h1> Formatted Full Log Table </h1><br>
<table class="table table-striped">
    <thead>
    <tr>
        <th>Timestamp</th>
        <th>Status</th>
        <th>Request IP</th>
        <th>Search Term</th>
        <th>Movie Title</th>
        <th>API Response Time</th>
        <th>Device Type</th>
    </tr>
    </thead>
    <% List<Log> logs = (List<Log>) request.getSession().getAttribute("fullLogs"); %>
    <tbody>
        <% for(Log log: logs) {%>
            <tr>
                <td><%= log.getTimeStamp()%></td>
                <td><%= log.getStatus()%></td>
                <td><%= log.getRequestIP()%></td>
                <td><%= log.getSearchTerm()%></td>
                <td><%= log.getResult().getTitle()%></td>
                <td><%= log.getApiResponseTime()%></td>
                <td><%= log.getDeviceType()%></td>
            </tr>
        <% } %>
    </tbody>
</table>


</body>
</html>
