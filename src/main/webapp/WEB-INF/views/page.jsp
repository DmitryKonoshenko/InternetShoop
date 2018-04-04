<%--@elvariable id="_csrf" type="org.springframework.web.server.session.HeaderWebSessionIdResolver"--%>
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
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />

    <%-- Метатеги для корректной работы ajax (добавляем токены)--%>
    <meta name="_csrf" content="${_csrf.token}">
    <meta name="_csrf_header" content="${_csrf.headerName}">
    
    <title>Электроник ${title}</title>

    <script>
        window.menu = '${title}';

        window.contextRoot='${contextRoot}';
    </script>

    <!-- Bootstrap core CSS -->
    <link href="${css}/bootstrap.css" rel="stylesheet">
    <!-- Ionic -->
    <link href="${css}/open-iconic-bootstrap.css" rel="stylesheet">
    <!-- Bootstrap core CSS -->
    <link href="${css}/bootstrap-grid.css" rel="stylesheet">
    <link href="${css}/shop-homepage.css" rel="stylesheet">


    <!-- Bootstrap DataTables -->
    <link href="${css}/dataTables.bootstrap.css" rel="stylesheet">
    <link href="${css}/datatables.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${css}/shop-homepage.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${css}/myapp.css" rel="stylesheet">


</head>

<body>

<div class="wrapper">

    <!-- Navigation -->
    <%@include file="./shared/navbar.jsp" %>

    <!-- Page Content -->
    <div class="content">
        <!--Loading the home container-->
        <c:if test="${userClickHome == true}">
            <%@include file="home.jsp" %>
        </c:if>
        <!--Loading about-->
        <c:if test="${userClickAbout == true}">
            <%@include file="about.jsp" %>
        </c:if>
        <!--Loading contact-->
        <c:if test="${userClickContact == true}">
            <%@include file="contact.jsp" %>
        </c:if>
        <!--Loading many product-->
        <c:if test="${userClickAllProducts == true or userClickCategoryProducts == true}">
            <%@include file="listProducts.jsp" %>
        </c:if>
        <!--Loading one product-->
        <c:if test="${userClickShowProduct == true}">
            <%@include file="singleProduct.jsp" %>
        </c:if>
        <!--Loading redactor-->
        <c:if test="${userClickManageProducts == true}">
            <%@include file="manageProduct.jsp" %>
        </c:if>
        <c:if test="${userClickManageOrders == true}">
            <%@include file="manageOrders.jsp" %>
        </c:if>
        <c:if test="${userClickManageOrdersId == true}">
            <%@include file="manageOrdersById.jsp" %>
        </c:if>
        <c:if test="${userClickShowCart == true}">
            <%@include file="cart.jsp" %>
        </c:if>
        <c:if test="${userClickShowUser == true}">
            <%@include file="userPage.jsp" %>
        </c:if>
        <c:if test="${userClickShowUserName == true}">
            <%@include file="userPageName.jsp" %>
        </c:if>
        <c:if test="${userClickShowUserPassword == true}">
            <%@include file="userChangePassword.jsp" %>
        </c:if>
        <c:if test="${userClickShowUserAddress == true}">
            <%@include file="userPageAddress" %>
        </c:if>
        <c:if test="${userClickStatistic == true}">
            <%@include file="Statistic.jsp" %>
        </c:if>

    </div>

    <!-- Footer -->
    <%--<%@include file="./shared/footer.jsp" %>--%>

    <!-- jQuery -->
    <script src="${js}/jquery.js"></script>

    <script src="${js}/jquery.validate.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${js}/bootstrap.min.js"></script>
    <script src="${js}/bootstrap.bundle.js"></script>

    <!-- DataTable Plugin -->
    <script src="${js}/jquery.dataTables.js"></script>
    <%--<script src="${js}/bootstrap.bundle.min.js"></script>--%>
    <script src="${js}/bootstrap.js"></script>

    <!-- DataTable Bootstrap Script -->
    <script src="${js}/dataTables.bootstrap.js"></script>
    <script src="${js}/datatables.js"></script>

    <!-- Bootbox -->
    <script src="${js}/bootbox.min.js"></script>

    <!-- DataTable Bootstrap Script -->
    <%--<script src="${js}/bootbox.min.js"></script>--%>

    <!-- Self coded javascript -->
    <script src="${js}/myapp.js" charset="utf-8"></script>

</div>

</body>

</html>
