<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@include file="../views/shared/flows-header.jsp" %>


    <!-- Page Content -->

    <div class="content">
        <div class="container">
            <div class="alert alert-success">
                <h3 class="text-center">Ваш заказ оформлен!</h3>
            </div>
            <div class="invoice-title">
                <h2>Номер</h2>
                <h3 class="pull-right">Закза # ${orderDetail.id}</h3>
            </div>


                    <hr>

                    <div class="row">
                        <address>
                            <strong>Олачен:</strong><br>
                            ${orderDetail.user.firstName} ${orderDetail.user.lastName}<br>
                            ${orderDetail.billing.addressLineOne}<br>
                            ${orderDetail.billing.addressLineTwo}<br>
                            ${orderDetail.billing.city} - ${orderDetail.billing.postalCode}<br>
                            ${orderDetail.billing.state} - ${orderDetail.billing.country}
                        </address>

                    <hr>

                        <address>
                            <strong>Доставить:</strong><br>
                            ${orderDetail.user.firstName} ${orderDetail.user.lastName}<br>
                            ${orderDetail.shipping.addressLineOne}<br>
                            ${orderDetail.shipping.addressLineTwo}<br>
                            ${orderDetail.shipping.city} - ${orderDetail.shipping.postalCode}<br>
                            ${orderDetail.shipping.state} - ${orderDetail.shipping.country}
                        </address>
                    </div>


            <div class="row">
                        <address>
                            <strong>Метод оплаты:</strong><br>
                            <c:if test="${orderDetail.isPay()}">Картой</c:if>
                            <c:if test="${not orderDetail.isPay()}">Наличными</c:if>
                            <br>
                            ${orderDetail.user.email}
                        </address>
                <hr>
                        <address>
                            <strong>Дата заказа:</strong><br>
                            ${orderDetail.orderDate}<br><br>
                        </address>
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
                <a href="${contextRoot}/show/all/products" class="btn btn-lg btn-warning">Продолжить покупки</a>
            </div>
        </div>

<%@include file="../views/flows/shared/flows-footer.jsp" %>