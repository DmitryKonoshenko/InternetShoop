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
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <div class="container">

                    <div class="navbar-header">
                        <a class="navbar-brand" href="${contextRoot}/home">На главную</a>
                    </div>

        </div>
    </nav>

    <!-- Page Content -->
    <div class="content">
       <div class="container">
           <div class="row">
                   <div class="col-lg-12">

                   <div class="jumbotron">

                       <h1>${errorTitle}</h1>
                       <hr/>

                       <blockquote style="word-wrap: break-word">

                           ${errorDescription}

                       </blockquote>

                   </div>

               </div>
           </div>
       </div>
    </div>

</div>

</body>

</html>