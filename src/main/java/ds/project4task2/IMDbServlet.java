package ds.project4task2;


import java.io.*;

import com.google.gson.Gson;
import ds.project4task2.models.SearchResult;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "IMDbServlet", value = "/search")
public class IMDbServlet extends HttpServlet {
    private IMDbModel model;

    public void init() {
        model = new IMDbModel();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");

        // fetch movie info in Json format
        String movieName = request.getParameter("movie");

        long startTime = System.currentTimeMillis();
        String jsonResponse = model.fetchInfo(movieName);
        long endTime = System.currentTimeMillis();
      //  request.getHeader("User-Agent")
        writeMongoDB( movieName,jsonResponse, endTime-startTime);

        // send back Json response
        PrintWriter out = response.getWriter();
        out.println(jsonResponse);
        out.close();
    }

    private void writeMongoDB(String movieName, String jsonResponse, long respondTime) {
        MongoService mongoService=new MongoService();
        Gson gson = new Gson();
        SearchResult searchResult = gson.fromJson(jsonResponse, SearchResult.class);
        mongoService.writeLog( movieName, searchResult.title, searchResult.description, searchResult.image,  respondTime);
    }


    public void destroy() {
    }
}