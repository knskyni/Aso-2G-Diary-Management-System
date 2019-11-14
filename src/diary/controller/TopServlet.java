package diary.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import diary.beans.UserInfoBeans;

@WebServlet("/top")
public class TopServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Session
        HttpSession session = request.getSession(false);
        UserInfoBeans userInfo = (UserInfoBeans)session.getAttribute("userInfo");

         String jspPath = (userInfo.getType().equals("student")) ? "WEB-INF/jsp/top_student.jsp" : "WEB-INF/jsp/top_teacher.jsp";

        // jsp
        RequestDispatcher rd = request.getRequestDispatcher(jspPath);
        rd.forward(request, response);
    }
}
