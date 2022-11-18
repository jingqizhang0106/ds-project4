package ds.project4task2;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.Document;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

@WebServlet(name = "DashboardServlet", value = "/dashboard")
public class DashboardServlet extends HttpServlet {
    private MongoService mongoService;

    public void init() {
        mongoService = new MongoService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    //    request.getSession().setAttribute("allLogs", logsInfo);
        try {
            request.getRequestDispatcher("/upload.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }


    }



    public void destroy() {
    }
}