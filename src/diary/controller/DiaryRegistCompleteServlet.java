package diary.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/diary/new/complete")
public class DiaryRegistCompleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Attribute
        request.setAttribute("item", "日誌登録");

        // JSP
        RequestDispatcher rd = request.getRequestDispatcher("../../WEB-INF/jsp/complete.jsp");
        rd.forward(request, response);
    }
}
