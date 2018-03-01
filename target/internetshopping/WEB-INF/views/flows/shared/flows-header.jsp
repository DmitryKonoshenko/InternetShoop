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
  <%@include file="flows-navbar.jsp"%>