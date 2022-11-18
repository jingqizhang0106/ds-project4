package ds.project4task2;


import ds.project4task2.models.Log;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.Document;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "DashboardServlet", value = "/dashboard")
public class DashboardServlet extends HttpServlet {
    private MongoService mongoService;

    public void init() {
        mongoService = new MongoService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {


        List<Log> logs=mongoService.getAllLogs();
        request.getSession().setAttribute("allLogs", logs);
        request.getSession().setAttribute("avgResponseTime", getAvgResponseTime(logs));
        request.getSession().setAttribute("Top5FrequentDeviceType", getTopKFrequentDeviceType(logs,5));
        request.getSession().setAttribute("Top10FrequentSearchTerm", getTopKFrequentSearchTerm(logs,10));

        try {
            request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }


    }

    private List<String> getTopKFrequentSearchTerm(List<Log> logs, int k) {
        Map<String, Integer> countMap = new HashMap<>();
        for(Log log : logs) {
            countMap.put(log.getDeviceType(), countMap.getOrDefault(log.getSearchTerm(), 0)+1);
        }
        //Sort by values and pick only top k elements
        List<String> result =
                countMap.entrySet().stream()
                        .sorted((c1, c2) -> c2.getValue().compareTo(c1.getValue()))
                        .limit(k)
                        .map(i -> i.getKey())
                        .collect(Collectors.toList());
        return result;
    }


    private List<String> getTopKFrequentDeviceType(List<Log> logs, int k) {
        Map<String, Integer> countMap = new HashMap<>();
        for(Log log : logs) {
            countMap.put(log.getDeviceType(), countMap.getOrDefault(log.getDeviceType(), 0)+1);
        }
        //Sort by values and pick only top k elements
        List<String> result =
                countMap.entrySet().stream()
                        .sorted((c1, c2) -> c2.getValue().compareTo(c1.getValue()))
                        .limit(k)
                        .map(i -> i.getKey())
                        .collect(Collectors.toList());
        return result;
    }


    private double getAvgResponseTime(List<Log> logs) {
        Double avgResponseTime = logs
                .stream()
                .collect(Collectors.averagingLong(log -> log.getApiResponseTime()));
        return avgResponseTime;
    }


    public void destroy() {
    }
}