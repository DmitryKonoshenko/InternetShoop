<%--@elvariable id="_csrf" type="org.springframework.web.bind.MissingServletRequestParameterException"--%>
<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url var="css" value="/resources/css"/>
<spring:url var="js" value="/resources/js"/>
<spring:url var="images" value="/resources/images"/>
<spring:url var="ion" value="/resources/ionic"/>

<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>

    <title>Электроник ${title}</title>

    <script>
        window.menu = '${title}';

        window.contextRoot = '${contextRoot}';
    </script>

    <%-- Bootstrap core CSS --%>
    <link href="${css}/bootstrap.css" rel="stylesheet">
    <%-- Ionic --%>
    <link href="${css}/open-iconic-bootstrap.css" rel="stylesheet">
    <%-- Bootstrap core CSS --%>
    <link href="${css}/bootstrap-grid.css" rel="stylesheet">
    <link href="${css}/shop-homepage.css" rel="stylesheet">


    <%-- Bootstrap DataTables --%>
    <link href="${css}/dataTables.bootstrap.css" rel="stylesheet">
    <link href="${css}/datatables.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${css}/shop-homepage.css" rel="stylesheet">

    <%-- Custom CSS --%>
    <link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>

<div class="wrapper">

    <%-- Navigation --%>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <div class="container">
            <div class="navbar-header">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${contextRoot}/home">На главную</a>
                </div>
            </div>
        </div>
    </nav>

    <%-- Page Content --%>
    <div class="content">

        <%-- Если ввели не правильные занчения логина или пароля --%>
        <c:if test="${not empty message}">
            <div class="row">
                <div class="alert alert-danger">
                    ${message}
                </div>
            </div>
        </c:if>
            <%-- Если вы вышли с аккаунта - будет выведено --%>
        <c:if test="${not empty logout}">
            <div class="row">
                <div class="alert alert-success">
                        ${logout}
                </div>
            </div>
        </c:if>

        <div class="form-container1122">

            <div class="panel panel-primary">

                <div class="form-title1122">
                    <h4>Login</h4>
                </div>

                <div class="panel-body">
                    <form action="${contextRoot}/login" method="POST" class="form-horizontal" id="loginForm">


                        <div class="form-group">
                            <label class="form-title1122" for="username">Email: </label>
                            <div class="col-md-8">
                                <input type="text" name="username" id="username" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="form-title1122" for="password">Password: </label>
                            <div class="col-md-8">
                                <input type="password" name="password" id="password" class="form-control"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="form-title1122">
                                <input type="submit" value="Login" class="submit-button1122">
                                <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                            </div>
                        </div>

                    </form>
                </div>
                <div class="panel-footer">
                    <div class="text-right">
                        Новый пользователь? - <a href="${contextRoot}/register">регистрация тут!</a>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <%-- Footer --%>
    <%@include file="./shared/footer.jsp" %>

    <%-- jQuery --%>
    <script src="${js}/jquery.js"></script>

    <script src="${js}/jquery.validate.js"></script>

    <%-- Bootstrap Core JavaScript --%>
    <script src="${js}/bootstrap.min.js"></script>
    <script src="${js}/bootstrap.bundle.js"></script>

    <%-- DataTable Plugin --%>
    <script src="${js}/jquery.dataTables.js"></script>
    <%--<script src="${js}/bootstrap.bundle.min.js"></script>--%>
    <script src="${js}/bootstrap.js"></script>


    <%-- DataTable Bootstrap Script --%>
    <%--<script src="${js}/bootbox.min.js"></script>--%>

    <%-- Self coded javascript --%>
    <script src="${js}/myapp.js" charset="utf-8"></script>

</div>

</body>

</html>
