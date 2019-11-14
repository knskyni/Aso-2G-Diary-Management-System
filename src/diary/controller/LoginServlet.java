package diary.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import diary.beans.UserInfoBeans;
import diary.model.LoginModel;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Messages
        HashMap<String, String> msg = new HashMap<String, String>();
        msg.put("logout", "ログアウトしました。");
        msg.put("not_logged", "ログインしていません。");

        // Get Message
        String state = request.getParameter("state");
        if(state != null && !state.isEmpty()) {
            request.setAttribute("errorMsg", msg.get(state));
        }

        // jsp
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get Form Parameter
        String userId = request.getParameter("userid");
        String password = request.getParameter("password");

        // Login Process
        LoginModel loginModel = new LoginModel();
        UserInfoBeans userInfoBeans = loginModel.login(userId, password);

        // If user is not found
        if(userInfoBeans == null) {
            request.setAttribute("errorMsg", "ユーザー名またはパスワードが間違っています。");
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
            rd.forward(request, response);
            return;
        }

        // Session
        HttpSession session = request.getSession(true);
        session.setAttribute("userInfoBeans", userInfoBeans);

        response.sendRedirect("top");
    }
}
