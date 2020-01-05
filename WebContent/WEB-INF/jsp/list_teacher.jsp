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
                ようこそ、<%= userInfo.getLastName() %> <%= userInfo.getFirstName() %>先生
            </span>
        </div>
    </nav>

    <div class="container">
        <div class="row">
            <div class="col">
                <table id="diary" class="table table-striped m-0">
                    <thead>
                        <tr>
                            <th>クラス</th>
                            <th>日付</th>
                            <th>記入者</th>
                            <th>登録日</th>
                            <th>最終更新日</th>
                            <th>チェックした担任の先生</th>
                            <th scope="col">閲覧</th>
                            <th scope="col">操作</th>
                        </tr>
                    </thead>
                    <tbody>
                    <% if(diaryList != null) { %>
                        <% for(DiaryInfoBeans diaryInfo : diaryList) { %>
                        <tr>
                            <td class="noswap"><%= diaryInfo.getClassName() %></td>
                            <td class="noswap"><%= sdf.format(diaryInfo.getDate()) %></td>
                            <td class="noswap"><%= diaryInfo.getUserName() %></td>
                            <td class="noswap"><%= sdfTime.format(diaryInfo.getRegistTime()).replaceAll(" ", "<br>") %></td>
                            <td class="noswap"><%= diaryInfo.getUpdateTime() == null ? "" : sdfTime.format(diaryInfo.getUpdateTime()).replaceAll(" ", "<br>") %></td>
                            <td class="noswap"><%= diaryInfo.getTeacherName() == null ? "入力待ち" : diaryInfo.getTeacherName() %></td>
                            <td class="noswap"><button type="button" class="btn btn-outline-success btn-sm" onclick="location.href='./view?id=<%= diaryInfo.getDiaryId() %>'">表示する</button></td>
                            <td class="noswap">
                                <button type="button" class="btn btn-outline-info btn-sm" onclick="location.href='./update/input?id=<%= diaryInfo.getDiaryId() %>'">編集</button>
                                <button type="button" class="btn btn-outline-danger btn-sm" onclick="location.href='./delete/confirm?id=<%= diaryInfo.getDiaryId() %>'">削除</button>
                            </td>
                        </tr>
                        <% } %>
                    <% } else { %>
                        <tr>
                            <td colspan="7">日誌はまだ登録されていません。</td>
                        </tr>
                    <% } %>
                    </tbody>
                </table>
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
            'language': {
                "url": "http://cdn.datatables.net/plug-ins/f2c75b7247b/i18n/Japanese.json"
            },
            'autoWidth': false
        });
    });
    </script>
</body>
</html>
