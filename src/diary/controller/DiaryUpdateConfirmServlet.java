package diary.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import diary.beans.DiaryInfoBeans;
import diary.beans.UserInfoBeans;
import diary.util.StringUtil;
import diary.util.UserUtil;

@WebServlet("/diary/update/confirm")
public class DiaryUpdateConfirmServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // セッション取得
        HttpSession session = request.getSession(false);
        UserInfoBeans userInfo = (UserInfoBeans)session.getAttribute("userInfo");
        DiaryInfoBeans updateDiaryInfo = (DiaryInfoBeans)session.getAttribute("updateDiaryInfo");

        // 準備
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String jsp;

        if(UserUtil.isTeacher(userInfo)) {

            // Formから値を取得
            String teacherComment = request.getParameter("teacher_comment");

            // 空チェック
            if(StringUtil.isEmpty(teacherComment)) {
                response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
                return;
            }

            // 文字数チェック
            if(teacherComment.length() > 1024) {
                response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
                return;
            }

            // 更新内容格納
            updateDiaryInfo.setTeacherId(userInfo.getUserId());
            updateDiaryInfo.setTeacherComment(teacherComment);

            // JSP先
            jsp = "../../WEB-INF/jsp/update_confirm_teacher.jsp";

        } else {

            // Get Form Parameter
            String date = request.getParameter("date");
            String goodComment = request.getParameter("good_comment");
            String badComment = request.getParameter("bad_comment");
            String aboutComment = request.getParameter("about_comment");

            // When not entered
            if(StringUtil.isEmpty(goodComment) || StringUtil.isEmpty(badComment) || StringUtil.isEmpty(aboutComment)) {
                response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
                return;
            }

            // When the number of characters is exceeded
            if(goodComment.length() > 512 || badComment.length() > 512 || aboutComment.length() > 512) {
                response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
                return;
            }

            // Beans
            updateDiaryInfo.setClassId(userInfo.getClassId());

            try {
                updateDiaryInfo.setDate(sdf.parse(date));
            } catch(ParseException e) {
                updateDiaryInfo.setDate(new Date());
            }

            updateDiaryInfo.setUserId(userInfo.getUserId());
            updateDiaryInfo.setGoodComment(goodComment);
            updateDiaryInfo.setBadComment(badComment);
            updateDiaryInfo.setAboutComment(aboutComment);

            // JSP先
            jsp = "../../WEB-INF/jsp/update_confirm_student.jsp";

        }

        // Session Attribute
        session.setAttribute("updateDiaryInfo", updateDiaryInfo);

        // JSP
        RequestDispatcher rd = request.getRequestDispatcher(jsp);
        rd.forward(request, response);
    }
}
