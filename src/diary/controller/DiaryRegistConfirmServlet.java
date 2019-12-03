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

@WebServlet("/diary/new/confirm")
public class DiaryRegistConfirmServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        // Session
        HttpSession session = request.getSession(false);
        UserInfoBeans userInfo = (UserInfoBeans)session.getAttribute("userInfo");

        // Prepare
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Beans
        DiaryInfoBeans registDiaryInfo = new DiaryInfoBeans();
        registDiaryInfo.setClassId(userInfo.getClassId());

        try {
            registDiaryInfo.setDate(sdf.parse(date));
        } catch(ParseException e) {
            registDiaryInfo.setDate(new Date());
        }

        registDiaryInfo.setUserId(userInfo.getUserId());
        registDiaryInfo.setGoodComment(goodComment);
        registDiaryInfo.setBadComment(badComment);
        registDiaryInfo.setAboutComment(aboutComment);

        // Session Attribute
        session.setAttribute("registDiaryInfo", registDiaryInfo);

        // JSP
        RequestDispatcher rd = request.getRequestDispatcher("../../WEB-INF/jsp/new_confirm_student.jsp");
        rd.forward(request, response);
    }
}
