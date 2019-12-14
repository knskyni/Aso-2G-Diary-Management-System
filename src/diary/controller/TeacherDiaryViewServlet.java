package diary.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diary.beans.DiaryInfoBeans;
import diary.model.DiaryModel;

@WebServlet("/teacher/diary/view")
public class TeacherDiaryViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get Parameter
        int diaryId = 0;
        try {
            diaryId = Integer.parseInt(request.getParameter("id"));
        } catch(NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Get Diary
        DiaryModel diaryModel = new DiaryModel();
        DiaryInfoBeans diaryInfo = diaryModel.getDiary(diaryId);

        // Check
        if(diaryInfo == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Set Attribute
        request.setAttribute("diaryInfo", diaryInfo);

        // JSP
        RequestDispatcher rd = request.getRequestDispatcher("../../WEB-INF/jsp/diary_view_teacher.jsp");
        rd.forward(request, response);
    }
}
