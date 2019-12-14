<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="diary.beans.UserInfoBeans" %>
<%@ page import="diary.beans.DiaryInfoBeans" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="diary.util.HTML" %>
<%
    UserInfoBeans userInfo = (UserInfoBeans)session.getAttribute("userInfo");
    DiaryInfoBeans updateDiaryInfo = (DiaryInfoBeans)session.getAttribute("updateDiaryInfo");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>
<!DOCTYPE html>
<html lang="ja" dir="ltr">

<head>
    <meta charset="utf-8">
    <title></title>

    <!-- meta -->
    <meta name="viewport" content="width=device-width,initial-scale=1.0">

    <!-- css -->
    <link rel="stylesheet" href="../../css/master.css">
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
                <h2>日誌修正 :: 確認画面</h2>
                <p>こちらで登録してよろしいですか？</p>
                <table class="table table-hover">
                    <tbody>
                        <tr>
                            <th scope="row">日付</th>
                            <td><%= sdf.format(updateDiaryInfo.getDate()) %></td>
                        </tr>
                        <tr>
                            <th scope="row">よかったこと</th>
                            <td><%= HTML.sanitize(updateDiaryInfo.getGoodComment()) %></td>
                        </tr>
                        <tr>
                            <th scope="row">気になったこと</th>
                            <td><%= HTML.sanitize(updateDiaryInfo.getBadComment()) %></td>
                        </tr>
                        <tr>
                            <th scope="row">ひとこと</th>
                            <td><%= HTML.sanitize(updateDiaryInfo.getAboutComment()) %></td>
                        </tr>
                    </tbody>
                </table>
                <div class="form-group text-center">
                    <button class="btn btn-secondary mb-2 px-5" type="button" onclick="history.back()">戻る</button>
                    <button class="btn btn-primary mb-2 ml-2 px-5" type="button" onclick="location.href='execute'">送信</button>
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