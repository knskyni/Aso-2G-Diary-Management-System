<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String item = (String)request.getAttribute("item");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%= item %>完了</title>
</head>
<body>
<h2><%= item %>完了</h2>
<p>
    <%= item %>が完了しました。<br>
    <a href="../list">一覧</a>
</p>
</body>
</html>