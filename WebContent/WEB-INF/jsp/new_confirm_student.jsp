<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="diary.beans.DiaryInfoBeans" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="diary.util.HTML" %>
<%
    DiaryInfoBeans registDiaryInfo = (DiaryInfoBeans)session.getAttribute("registDiaryInfo");
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
                <td><%= sdf.format(registDiaryInfo.getDate()) %></td>
            </tr>
            <tr>
                <th>良かったこと</th>
                <td><%= HTML.sanitize(registDiaryInfo.getGoodComment()) %></td>
            </tr>
            <tr>
                <th>気になったこと</th>
                <td><%= HTML.sanitize(registDiaryInfo.getBadComment()) %></td>
            </tr>
            <tr>
                <th>いろいろ</th>
                <td><%= HTML.sanitize(registDiaryInfo.getAboutComment()) %></td>
            </tr>
        </tbody>
    </table>

    <a href="javascript:history.back();">戻る</a> | <a href="execute">送信</a>
</body>
</html>