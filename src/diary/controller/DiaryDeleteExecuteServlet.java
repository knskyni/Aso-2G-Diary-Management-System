package diary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import diary.beans.DiaryInfoBeans;
import diary.beans.UserInfoBeans;
import diary.model.DiaryModel;
import diary.util.UserUtil;

@WebServlet("/diary/delete/execute")
public class DiaryDeleteExecuteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Session
        HttpSession session = request.getSession(false);
        UserInfoBeans userInfo = (UserInfoBeans)session.getAttribute("userInfo");
        DiaryInfoBeans deleteDiaryInfo = (DiaryInfoBeans)session.getAttribute("deleteDiaryInfo");
        if(deleteDiaryInfo == null) {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return;
        }

        // Execute
        DiaryModel diaryModel = new DiaryModel();

        if(UserUtil.isTeacher(userInfo)) {
            if(!diaryModel.teacherDelete(deleteDiaryInfo)) {
                response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
                return;
            }
        } else {
            if(!diaryModel.delete(deleteDiaryInfo)) {
                response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
                return;
            }
        }

        response.sendRedirect("complete");
    }
}
