package diary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import diary.beans.DiaryInfoBeans;
import diary.model.DiaryModel;

@WebServlet("/diary/new/execute")
public class DiaryRegistExecuteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Session
        HttpSession session = request.getSession(false);
        DiaryInfoBeans registDiaryInfo = (DiaryInfoBeans)session.getAttribute("registDiaryInfo");
        if(registDiaryInfo == null) {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return;
        }

        // Execute
        DiaryModel diaryModel = new DiaryModel();
        boolean result = diaryModel.insert(registDiaryInfo);

        if(!result) {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return;
        }

        response.sendRedirect("complete");
    }
}
