<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日誌登録ページ</title>
</head>
<body>
    <form action="confirm" method="POST">
        <label>日付</label><br>
        <input type="date" name="date"><br>
        <label>よかったこと</label><br>
        <textarea name="good_comment"></textarea><br>
        <label>気になったこと</label><br>
        <textarea name="bad_comment"></textarea><br>
        <label>ひとこと</label><br>
        <textarea name="about_comment"></textarea><br>
        <input type="submit" value="送信">
    </form>
</body>
</html>