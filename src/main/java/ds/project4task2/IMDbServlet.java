package ds.project4task2;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "IMDbServlet", value = "/search")
public class IMDbServlet extends HttpServlet {
    private IMDbModel model;

    public void init() {
        model = new IMDbModel();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");

        String jsonResponse = model.fetchInfo(request);

        // send back Json response
        PrintWriter out = response.getWriter();
        out.println(jsonResponse);
        out.close();
    }


    public void destroy() {
    }
}