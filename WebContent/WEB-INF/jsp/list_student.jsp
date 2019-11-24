<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! @SuppressWarnings("unchecked") %>
<%@ page import="java.util.List" %>
<%@ page import="diary.beans.DiaryInfoBeans" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    List<DiaryInfoBeans> diaryList = (List<DiaryInfoBeans>)request.getAttribute("diaryList");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List</title>
</head>
<body>
    <% if(diaryList != null) { %>
    <table>
        <tbody>
            <tr>
                <th>日付</th>
                <th>記入者</th>
                <th>登録日</th>
                <th>最終更新日</th>
                <th>チェックした担任の先生</th>
                <th>操作</th>
            </tr>
            <% for(DiaryInfoBeans diaryInfo : diaryList) { %>
            <tr>
                <td><%= sdf.format(diaryInfo.getDate()) %></td>
                <td><%= diaryInfo.getUserName() %></td>
                <td><%= sdfTime.format(diaryInfo.getRegistTime()) %></td>
                <td><%= diaryInfo.getUpdateTime() == null ? "" : sdfTime.format(diaryInfo.getUpdateTime()) %></td>
                <td><%= diaryInfo.getTeacherName() == null ? "入力待ち" : diaryInfo.getTeacherName() %></td>
                <td><a href="update/input?id=<%= diaryInfo.getDiaryId() %>">編集</a> | <a href="delete/confirm?id=<%= diaryInfo.getDiaryId() %>">削除</a></td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <% } else { %>
    <span style="color: red;">日誌はまだ記録されていません。</span>
    <% } %>
</body>
</html>