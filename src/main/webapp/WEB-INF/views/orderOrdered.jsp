<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Электроник</title>

    <script>

        window.contextRoot = '${contextRoot}'

    </script>

    <!-- Bootstrap Core CSS -->
    <link href="${css}/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap Readable Theme -->
    <link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">


    <!-- Bootstrap DataTables -->
    <link href="${css}/dataTables.bootstrap.css" rel="stylesheet">


    <!-- Custom CSS -->
    <link href="${css}/myapp.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div class="wrapper">

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a class="navbar-brand" href="${contextRoot}/home">Магазин элетроники Электроник</a>
            </div>
        </div>
    </nav>


    <!-- Page Content -->

    <div class="content">
        <div class="container">
            <div class="alert alert-success">
                <h3 class="text-center">Ваш заказ оформлен!</h3>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="invoice-title">
                        <h2>Номер</h2><h3 class="pull-right">Закза # ${orderDetail.id}</h3>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-xs-6">
                            <address>
                                <strong>Олачен:</strong><br>
                                ${orderDetail.user.firstName} ${orderDetail.user.lastName}<br>
                                ${orderDetail.billing.addressLineOne}<br>
                                ${orderDetail.billing.addressLineTwo}<br>
                                ${orderDetail.billing.city} - ${orderDetail.billing.postalCode}<br>
                                ${orderDetail.billing.state} - ${orderDetail.billing.country}
                            </address>
                        </div>
                        <div class="col-xs-6 text-right">
                            <address>
                                <strong>Доставить:</strong><br>
                                ${orderDetail.user.firstName} ${orderDetail.user.lastName}<br>
                                ${orderDetail.shipping.addressLineOne}<br>
                                ${orderDetail.shipping.addressLineTwo}<br>
                                ${orderDetail.shipping.city} - ${orderDetail.shipping.postalCode}<br>
                                ${orderDetail.shipping.state} - ${orderDetail.shipping.country}
                            </address>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-6">
                            <address>
                                <strong>Payment Method:</strong><br>
                                Card Payment <br>
                                ${orderDetail.user.email}
                            </address>
                        </div>
                        <div class="col-xs-6 text-right">
                            <address>
                                <strong>Order Date:</strong><br>
                                ${orderDetail.orderDate}<br><br>
                            </address>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title"><strong>Детали заказа</strong></h3>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-condensed">
                                    <thead>
                                    <tr>
                                        <td><strong>Товар</strong></td>
                                        <td class="text-center"><strong>Цена</strong></td>
                                        <td class="text-center"><strong>Количество</strong></td>
                                        <td class="text-right"><strong>Общая цена</strong></td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <!-- foreach ($order->lineItems as $line) or some such thing here -->
                                    <c:forEach items="${orderDetail.orderItems}" var="orderItem">
                                        <tr>
                                            <td>${orderItem.product.name}</td>
                                            <td class="text-center">&#8381; ${orderItem.buyingPrice}</td>
                                            <td class="text-center">${orderItem.productCount}</td>
                                            <td class="text-right">&#8381; ${orderItem.total}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="text-center">
                <a href="${contextRoot}/show/all/products" class="btn btn-lg btn-warning">Continue Shopping</a>
            </div>
        </div>

<%@include file="../views/flows/shared/flows-footer.jsp" %>