package diary.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import diary.beans.DiaryInfoBeans;
import diary.beans.UserInfoBeans;
import diary.model.DiaryModel;

@WebServlet("/diary/list")
public class DiaryListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Session
        HttpSession session = request.getSession(false);
        UserInfoBeans userInfo = (UserInfoBeans)session.getAttribute("userInfo");

        // Get Diary List
        DiaryModel diaryModel = new DiaryModel();
        List<DiaryInfoBeans> diaryList = diaryModel.getList(userInfo.getClassId());
        request.setAttribute("diaryList", diaryList);

        // jsp
        RequestDispatcher rd = request.getRequestDispatcher("../WEB-INF/jsp/list_student.jsp");
        rd.forward(request, response);
    }
}
