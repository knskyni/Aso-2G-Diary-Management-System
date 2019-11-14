<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
    <p>ようこそ！</p>
    <form action="" method="POST">
        <table>
            <tbody>
                <tr>
                    <th>ユーザー名</th>
                    <td><input type="text" name="userid"></td>
                </tr>
                <tr>
                    <th>パスワード</th>
                    <td><input type="password" name="password"></td>
                </tr>
            </tbody>
        </table>
        <% if(errorMsg != null) { %>
        <span style="color: red;"><%= errorMsg %></span><br>
        <% } %>
        <input type="submit" value="ログイン">
    </form>
</body>
</html>