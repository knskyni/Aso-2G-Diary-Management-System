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
import diary.util.UserUtil;

@WebServlet("/diary/update/complete")
public class DiaryUpdateCompleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // セッション取得
        HttpSession session = request.getSession(false);
        UserInfoBeans userInfo = (UserInfoBeans)session.getAttribute("userInfo");

        // Attribute
        if(UserUtil.isTeacher(userInfo)) {
            request.setAttribute("item", "担任コメント更新");
        } else {
            request.setAttribute("item", "日誌更新");
        }

        // JSP
        RequestDispatcher rd = request.getRequestDispatcher("../../WEB-INF/jsp/complete.jsp");
        rd.forward(request, response);
    }
}
