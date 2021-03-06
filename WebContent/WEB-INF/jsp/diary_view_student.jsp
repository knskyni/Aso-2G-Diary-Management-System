<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="diary.beans.UserInfoBeans" %>
<%@ page import="diary.beans.DiaryInfoBeans" %>
<%@ page import="diary.util.HTML" %>
<%
    UserInfoBeans userInfo = (UserInfoBeans)session.getAttribute("userInfo");
    DiaryInfoBeans diaryInfo = (DiaryInfoBeans)request.getAttribute("diaryInfo");
%>
<!DOCTYPE html>
<html lang="ja" dir="ltr">

<head>
    <meta charset="utf-8">
    <title></title>

    <!-- meta -->
    <meta name="viewport" content="width=device-width,initial-scale=1.0">

    <!-- css -->
    <link rel="stylesheet" href="../css/master.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css"
        integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
</head>

<body class="w-100">

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand font-weight-bold" href="<%= request.getContextPath() + "/top" %>">
            <i class="far fa-sticky-note fa-lg"></i>
            クラス日誌管理システム
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="<%= request.getContextPath() + "/logout" %>">ログアウト</a>
                </li>
            </ul>
            <span class="navbar-text">
                ようこそ、<%= userInfo.getClassName() %> <%= userInfo.getLastName() %> <%= userInfo.getFirstName() %>さん！
            </span>
        </div>
    </nav>

    <div class="container">
        <div class="row">
            <div class="col">
                <h2>日誌</h2>

                <h5 class="mt-5">学生記入欄</h5>
                <table class="table table-hover">
                    <tbody>
                        <tr>
                            <th scope="row">学科</th>
                            <td><%= HTML.sanitize(diaryInfo.getClassName()) %></td>
                        </tr>
                        <tr>
                            <th scope="row">名前</th>
                            <td><%= HTML.sanitize(diaryInfo.getUserName()) %></td>
                        </tr>
                        <tr>
                            <th scope="row">よかったこと</th>
                            <td><%= HTML.sanitize(diaryInfo.getGoodComment()) %></td>
                        </tr>
                        <tr>
                            <th scope="row">気になったこと</th>
                            <td><%= HTML.sanitize(diaryInfo.getBadComment()) %></td>
                        </tr>
                        <tr>
                            <th scope="row">ひとこと</th>
                            <td><%= HTML.sanitize(diaryInfo.getAboutComment()) %></td>
                        </tr>
                    </tbody>
                </table>

                <h5 class="mt-5">担任記入欄</h5>
                <table class="table table-hover">
                    <tbody>
                        <tr>
                            <th scope="row">担任名</th>
                            <td><%= HTML.sanitize(diaryInfo.getTeacherName()) %></td>
                        </tr>
                        <tr>
                            <th scope="row">担任コメント</th>
                            <td><%= HTML.sanitize(diaryInfo.getTeacherComment()) %></td>
                        </tr>
                    </tbody>
                </table>
                <div class="form-group text-center">
                    <button class="btn btn-secondary mb-2 px-5" type="button" onclick="history.back()">戻る</button>
                <% if(userInfo.getUserId().equals(diaryInfo.getUserId())) { %>
                    <% if(diaryInfo.getTeacherComment() == null) { %>
                    <button class="btn btn-primary mb-2 ml-2 px-5" type="button" onclick="location.href='./update/input?id=<%= diaryInfo.getDiaryId() %>'">編集する</button>
                    <% } %>
                <% } %>
                </div>
            </div>
        </div>
    </div>

    <!-- javascript -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="https://kit.fontawesome.com/1ad7240794.js"></script>
</body>

</html>