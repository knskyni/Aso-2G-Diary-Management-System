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
import diary.util.StringUtil;
import diary.util.UserUtil;

@WebServlet("/diary/list")
public class DiaryListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Session
        HttpSession session = request.getSession(false);
        UserInfoBeans userInfo = (UserInfoBeans)session.getAttribute("userInfo");

        // Get Diary List
        DiaryModel diaryModel = new DiaryModel();
        List<DiaryInfoBeans> diaryList;

        String jsp = "";

        // 生徒・教員
        if(UserUtil.isTeacher(userInfo)) {

            // Get Form Parameter
            String classIdString = request.getParameter("class_id");
            int classId;

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
            jsp = "../WEB-INF/jsp/list_teacher.jsp";

        } else {
            diaryList = diaryModel.getList(userInfo.getClassId());
            request.setAttribute("diaryList", diaryList);
            jsp = "../WEB-INF/jsp/list_student.jsp";
        }

        // jsp
        RequestDispatcher rd = request.getRequestDispatcher(jsp);
        rd.forward(request, response);
    }
}
