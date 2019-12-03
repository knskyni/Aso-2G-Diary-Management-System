package diary.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diary.beans.DiaryInfoBeans;
import diary.model.DiaryModel;
import diary.util.StringUtil;

@WebServlet("/teacher/diary/list")
public class TeacherDiaryListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get Form Parameter
        String classIdString = request.getParameter("class_id");
        int classId;

        // Get Diary List
        DiaryModel diaryModel = new DiaryModel();
        List<DiaryInfoBeans> diaryList;

        if(StringUtil.isEmpty(classIdString)) {
            diaryList = diaryModel.getList();
        } else {

            try {
                classId = Integer.parseInt(classIdString);
            } catch(NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            diaryList = diaryModel.getList(classId);
        }
        request.setAttribute("diaryList", diaryList);

        // jsp
        RequestDispatcher rd = request.getRequestDispatcher("../../WEB-INF/jsp/list_teacher.jsp");
        rd.forward(request, response);
    }
}
