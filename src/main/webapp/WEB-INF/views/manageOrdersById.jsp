<%--@elvariable id="orderDetail" type="com.divanxan.internetshop.dto.OrderDetail"--%>
<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div class="content">
    <c:if test="${not empty message}">
        <div class="col-xs-12">
            <div class="alert alert-danger">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                    ${message}
            </div>
        </div>
    </c:if>
    <div class="form-container">
        <div class="panel panel-primary">
            <div class="col-lg-12 col-md-6 mb-1">
                <div class="card">
                    <div class="m-2">
                        <div class="panel-body">
                            <div class="text-left">
                                <div class="row">
                                    <div class="col-lg-5 m-2">
                                        <h4>Номер заказа: ${orderDetail.id}</h4>
                                        <h6>Дата заказа:${orderDetail.orderDate}</h6>
                                        <h6>Оплата заказа:
                                            <c:if test="${orderDetail.isPay()}">Картой</c:if>
                                            <c:if test="${not orderDetail.isPay()}">Наличными</c:if>
                                        </h6>
                                        <h6>Доставка заказа:
                                            <c:if test="${orderDetail.delivery}">Курьером</c:if>
                                            <c:if test="${not orderDetail.delivery}">Самовывоз</c:if>
                                        </h6>
                                        <h6>Общая стоимость заказа: &#8381; ${orderDetail.orderTotal}</h6>
                                    </div>
                                    <div class="col-lg-5 m-2 right">
                                        <form action="${contextRoot}/manage/${orderDetail.id}/orderChange" method="post">
                                            <div class="col-md-8">
                                                <label class="form-title1122">Доставлен:</label>
                                                <label class="radio-inline">
                                                    <input type="radio" name="delivered" id="no" value="no" required
                                                           autofocus>Нет
                                                </label>
                                                <label class="radio-inline">
                                                    <input type="radio" name="delivered" id="yes" value="yes">Да
                                                </label>
                                            </div>
                                            <div class="col-md-8">
                                                <label class="form-title1122">Отгружен:</label>
                                                <label class="radio-inline">
                                                    <input type="radio" name="shipped" id="not" value="not" required
                                                           autofocus>Нет
                                                </label>
                                                <label class="radio-inline">
                                                    <input type="radio" name="shipped" id="yess" value="yess">Да
                                                </label>
                                            </div>
                                            <br/>
                                            <div class="form-group">
                                                <div class="col-md-offset-4 col-md-8">
                                                    <button type="submit" name="_eventId_billing"
                                                            class="submit-button1122">
                                                        Изменить
                                                    </button>
                                                    <input type="hidden" name="${_csrf.parameterName}"
                                                           value="${_csrf.token}"/>
                                                    <input type="hidden" name="orderId" value="${orderDetail.id}"/>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <h4>Доставлен ли:
                                    <c:if test="${orderDetail.isDelivery}">Да</c:if>
                                    <c:if test="${not orderDetail.isDelivery}">Нет</c:if>
                                </h4>
                                <h4>Отгружен ли:
                                    <c:if test="${orderDetail.isShippedOrder()}">Да</c:if>
                                    <c:if test="${not orderDetail.isShippedOrder()}">Нет</c:if>
                                </h4>
                                <div class="row">
                                    <div class="col-lg-5 m-2">
                                        <div class="panel-heading">
                                            <h4>Адресс покупателя:</h4>
                                        </div>
                                        <div class="panel-body">
                                            <div class="text-left">
                                                <h6>${orderDetail.shipping.addressLineOne}</h6>
                                                <h6>${orderDetail.shipping.addressLineTwo}</h6>
                                                <h6>${orderDetail.shipping.city}
                                                    - ${orderDetail.shipping.postalCode}</h6>
                                                <h6>${orderDetail.shipping.state} - ${orderDetail.shipping.country}</h6>
                                            </div>
                                        </div>
                                    </div>
                                    <c:if test="${not empty orderDetail.billing}">
                                        <div class="col-lg-5 m-2 right">
                                            <div class="panel-heading">
                                                <h4>Адресс доставки:</h4>
                                            </div>
                                            <div class="panel-body">
                                                <div class="text-left">
                                                    <h6>${orderDetail.billing.addressLineOne}</h6>
                                                    <h6>${orderDetail.billing.addressLineTwo}</h6>
                                                    <h6>${orderDetail.billing.city}
                                                        - ${orderDetail.billing.postalCode}</h6>
                                                    <h6>${orderDetail.billing.state}
                                                        - ${orderDetail.billing.country}</h6>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
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
                            <h6>Скидка: ${orderDetail.discount}%</h6>
                            <h6>Общая стоимость заказа: ${orderDetail.orderTotal}</h6>
                        </div>
                        <div class="panel-footer">
                            <a href="${contextRoot}/manage/orders"
                               class="submit-button1122">Вернуться</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</div>