<%@ page import="ds.project4task2.models.Log" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.security.Timestamp" %>
<%@ page import="ds.project4task2.models.SearchResult" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Dashboard</title>
    <meta name="description" content="Creating a Zebra table with Twitter Bootstrap. Learn with example of a Zebra Table with Twitter Bootstrap.">
    <link href="bootstrap.css" rel="stylesheet">
</head>
<body>
<h1 Full Log Table />
<table class="table table-striped">
    <thead>
    <tr>
        <th>TimeStamp</th>
        <th>LogID</th>
        <th>Device Type</th>
        <th>Search Term</th>
        <th>API Response Time</th>
        <th>Title</th>
    </tr>
    </thead>
    <% List<Log> logs = (List<Log>) request.getSession().getAttribute("allLogs");
//        Log l1 = Log.builder().appId("1111").deviceType("huawei").searchTerm("zhanlang").apiResponseTime(10).timeStamp(new Date()).result(new SearchResult("Zhanlang","wujing","a")).build();
//        Log l2 = Log.builder().appId("2222").deviceType("huawei2").searchTerm("zhanlang2").apiResponseTime(102).timeStamp(new Date()).result(new SearchResult("Zhanlang2","wujing","b")).build();
//        logs.add(l1);
//        logs.add(l2);
    %>
    <tbody>
        <% for(Log log: logs) {%>
            <tr>

                <td><%= log.getTimeStamp()%></td>
                <td><%= log.getAppId()%></td>
                <td><%= log.getDeviceType()%></td>
                <td><%= log.getSearchTerm()%></td>
                <td><%= log.getApiResponseTime()%></td>
                <td><%= log.getResult().getTitle()%></td>
            </tr>
        <% } %>


    </tbody>
</table>
</body>
</html>
