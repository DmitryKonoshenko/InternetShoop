<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div class="container">

    <c:if test="${not empty message}">
        <div class="col-xs-12">

            <div class="alert alert-danger">

                <button type="button" class="close" data-dismiss="alert">&times;</button>

                    ${message}

            </div>
        </div>
    </c:if>
    <div class="row">
        <div class="form-container">
            <div class="panel panel-primary">
                <a href="${contextRoot}/manage/statistic" class="submit-button1122">Просмотр статистики</a>
            </div>
        </div>

        <%--@elvariable id="orderDetails" type="java.util.List"--%>
        <c:forEach items="${orderDetails}" var="orderDetail">
            <div class="form-container1122">

                <div class="panel panel-primary">

                    <div class="panel-heading">
                        <h4>История заказов</h4>
                    </div>

                    <div class="panel-body">
                        <div class="text-left">
                            <h4>Номер заказа: ${orderDetail.id}</h4>
                            <h4>Дата заказа:${orderDetail.orderDate}</h4>
                            <h4>Оплата заказа:
                                <c:if test="${orderDetail.isPay()}">Картой</c:if>
                                <c:if test="${not orderDetail.isPay()}">Наличными</c:if>
                            </h4>
                            <h4>Доставка заказа:
                                <c:if test="${orderDetail.delivery}">Курьером</c:if>
                                <c:if test="${not orderDetail.delivery}">Самовывоз</c:if>
                            </h4>
                            <h4>Общая стоимость заказа:${orderDetail.orderTotal}</h4>

                            <h4>Доставлен ли:
                                <c:if test="${orderDetail.isDelivery}">Да</c:if>
                                <c:if test="${not orderDetail.isDelivery}">Нет</c:if>
                            </h4>
                            <h4>Отгружен ли:
                                    <c:if test="${orderDetail.isShippedOrder()}">Да</c:if>
                                    <c:if test="${not orderDetail.isShippedOrder()}">Нет</c:if>
                            </h4>

                            <div class="panel-heading">
                            <h4>Адресс покупателя:</h4>
                            </div>
                            <div class="panel-body">
                                <div class="text-left">
                                    <h6>${orderDetail.shipping.addressLineOne}</h6>
                                    <h6>${orderDetail.shipping.addressLineTwo}</h6>
                                    <h6>${orderDetail.shipping.city} - ${orderDetail.shipping.postalCode}</h6>
                                    <h6>${orderDetail.shipping.state} - ${orderDetail.shipping.country}</h6>
                                </div>
                            </div>
                            <c:if test="${not empty orderDetail.billing}">
                            <div class="panel-heading">
                                <h4>Адресс доставки:</h4>
                            </div>
                            <div class="panel-body">
                                <div class="text-left">
                                    <h6>${orderDetail.billing.addressLineOne}</h6>
                                    <h6>${orderDetail.billing.addressLineTwo}</h6>
                                    <h6>${orderDetail.billing.city} - ${orderDetail.billing.postalCode}</h6>
                                    <h6>${orderDetail.billing.state} - ${orderDetail.billing.country}</h6>
                                </div>
                            </div>
                            </c:if>
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
                <div class="panel-footer">

                    <a href="${contextRoot}/manage/${orderDetail.id}/orderChange" class="submit-button1122">Изменить</a>

                </div>
            </div>
        </c:forEach>
    </div>
</div>