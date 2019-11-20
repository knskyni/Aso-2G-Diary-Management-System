<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="diary.beans.DiaryInfoBeans" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    DiaryInfoBeans deleteDiaryInfo = (DiaryInfoBeans)session.getAttribute("deleteDiaryInfo");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>確認</title>
</head>
<body>
    <table>
        <tbody>
            <tr>
                <th>日付</th>
                <td><%= sdf.format(deleteDiaryInfo.getDate()) %></td>
            </tr>
            <tr>
                <th>良かったこと</th>
                <td><%= deleteDiaryInfo.getGoodComment() %></td>
            </tr>
            <tr>
                <th>気になったこと</th>
                <td><%= deleteDiaryInfo.getBadComment() %></td>
            </tr>
            <tr>
                <th>いろいろ</th>
                <td><%= deleteDiaryInfo.getAboutComment() %></td>
            </tr>
        </tbody>
    </table>

    <a href="javascript:history.back();">戻る</a> | <a href="execute">削除</a>
</body>
</html>