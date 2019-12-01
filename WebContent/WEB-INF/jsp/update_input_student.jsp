<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="diary.beans.DiaryInfoBeans" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="diary.util.HTML" %>
<%
    DiaryInfoBeans updateDiaryInfo = (DiaryInfoBeans)session.getAttribute("updateDiaryInfo");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日誌登録ページ</title>
</head>
<body>
    <form action="confirm" method="POST">
        <label>日付</label><br>
        <input type="date" name="date" value="<%= sdf.format(updateDiaryInfo.getDate()) %>"><br>
        <label>よかったこと</label><br>
        <textarea name="good_comment"><%= HTML.sanitize(updateDiaryInfo.getGoodComment()) %></textarea><br>
        <label>気になったこと</label><br>
        <textarea name="bad_comment"><%= HTML.sanitize(updateDiaryInfo.getBadComment()) %></textarea><br>
        <label>ひとこと</label><br>
        <textarea name="about_comment"><%= HTML.sanitize(updateDiaryInfo.getAboutComment()) %></textarea><br>
        <input type="submit" value="送信">
    </form>
</body>
</html>