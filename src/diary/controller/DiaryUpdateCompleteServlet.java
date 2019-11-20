package diary.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/diary/update/complete")
public class DiaryUpdateCompleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Attribute
        request.setAttribute("item", "日誌更新");

        // JSP
        RequestDispatcher rd = request.getRequestDispatcher("../../WEB-INF/jsp/complete.jsp");
        rd.forward(request, response);
    }
}
