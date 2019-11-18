<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="diary.beans.UserInfoBeans" %>
<%
    UserInfoBeans userInfo = (UserInfoBeans)session.getAttribute("userInfo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Top</title>
</head>
<body>
    <p>
        名前: <%= userInfo.getLastName() %> <%= userInfo.getFirstName() %>先生<br>

        <a href="teacher/diary/list">一覧</a> | <a href="logout">ログアウト</a>
    </p>
</body>
</html>