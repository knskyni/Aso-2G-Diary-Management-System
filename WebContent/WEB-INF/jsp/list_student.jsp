<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! @SuppressWarnings("unchecked") %>
<%@ page import="java.util.List" %>
<%@ page import="diary.beans.UserInfoBeans" %>
<%@ page import="diary.beans.DiaryInfoBeans" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    UserInfoBeans userInfo = (UserInfoBeans)session.getAttribute("userInfo");
    List<DiaryInfoBeans> diaryList = (List<DiaryInfoBeans>)request.getAttribute("diaryList");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
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
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
</head>
<body class="w-100">

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand font-weight-bold" href="<%= request.getContextPath() + "/top" %>">
            <i class="far fa-sticky-note fa-lg"></i>
            クラス日誌管理システム
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
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
                <button type="submit" class="btn btn-outline-danger btn-lg btn-block mb-5" onclick="location.href='./new/input'">日誌を登録する</button>
                <table id="diary" class="table table-striped m-0">
                    <thead>
                        <tr>
                            <th scope="col">日付</th>
                            <th scope="col">記入者</th>
                            <th scope="col">登録日</th>
                            <th scope="col">最終更新日</th>
                            <th scope="col">担任チェック</th>
                            <th scope="col">操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(DiaryInfoBeans diaryInfo : diaryList) { %>
                        <tr>
                            <td><%= sdf.format(diaryInfo.getDate()) %></td>
                            <td><%= diaryInfo.getUserName() %></td>
                            <td><%= sdfTime.format(diaryInfo.getRegistTime()) %></td>
                            <td><%= diaryInfo.getUpdateTime() == null ? "" : sdfTime.format(diaryInfo.getUpdateTime()) %></td>
                            <td><%= diaryInfo.getTeacherComment() == null ? "未" : "済" %></td>
                            <td>
                            <% if(userInfo.getUserId().equals(diaryInfo.getUserId())) { %>
                                <% if(diaryInfo.getTeacherComment() == null) {%>
                                <button type="button" class="btn btn-outline-info" onclick="location.href='./update/input?id=<%= diaryInfo.getDiaryId() %>'">編集</button>
                                <button type="button" class="btn btn-outline-danger" onclick="location.href='./delete/confirm?id=<%= diaryInfo.getDiaryId() %>'">削除</button>
                                <% } else { %>
                                担任チェック後は<br>編集できません
                                <% } %>
                            <% } else {%>
                                他人の日誌は<br>操作できません
                            <% } %>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                <div class="alert alert-warning mt-5" role="alert">
                    <h4 class="alert-heading">注意</h4>
                    <li>担任チェックが「済」になっている日誌の編集・削除はできません。</li>
                    <li>他人の日誌は編集・削除できません。</li>
                </div>
            </div>
        </div>
    </div>

    <!-- javascript -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="https://kit.fontawesome.com/1ad7240794.js"></script>

    <script type="text/javascript">
    $(document).ready(function(){
        $('#diary').DataTable({
            "language": {
                "url": "http://cdn.datatables.net/plug-ins/f2c75b7247b/i18n/Japanese.json"
            },
        });
    });
    </script>
</body>
</html>
