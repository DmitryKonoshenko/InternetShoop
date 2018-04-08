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
                        <h4>Просмотр статистики:
                            <a href="${contextRoot}/manage/statistic" class="submit-button1122">статистика</a>
                        </h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%--@elvariable id="orderDetails" type="java.util.List"--%>
    <div class="form-container">
        <div class="panel panel-primary">
            <div class="col-lg-12 col-md-6 mb-3">
                <div class="card">
                    <div class="m-2">
                        <div class="panel-heading">
                            <div class="panel-heading">
                                <h4>История заказов:</h4>
                                <div class="m-2">
                                    <div class="row">
                                        <div class="col-lg-2">
                                            <a href="${contextRoot}/manage/orders" class="submit-button1122">За
                                                неделю</a>
                                        </div>
                                        <div class="col-lg-2">
                                            <a href="${contextRoot}/manage/orders/month" class="submit-button1122">За
                                                месяц</a>
                                        </div>
                                    </div>
                                    <br>

                                    <form id="orderDate" modelAttribute="user" action="${contextRoot}/manage/orders"
                                          method="POST"
                                          cssClass="form-horizontal">
                                        <div class="row">
                                            <div class="col-lg-2 m-2">
                                                <p>Дата с: <input type="date" name="calendar1"></p>
                                            </div>
                                            <div class="col-lg-2 m-2">
                                                <p>Дата по: <input type="date" name="calendar2"></p>
                                            </div>
                                            <div class="col-lg-2 m-2">
                                                <button type="submit" name="_eventId_billing" class="submit-button1122">
                                                    Найти заказы
                                                </button>
                                                <input type="hidden" name="${_csrf.parameterName}"
                                                       value="${_csrf.token}"/>
                                            </div>
                                        </div>
                                    </form>

                                </div>
                                <br>
                            </div>
                            <c:if test="${not empty orderDetails}">
                            <table class="table table-condensed">
                                <thead>
                                <tr>
                                    <td><strong>ID заказа</strong></td>
                                    <td class="text-center"><strong>Дата</strong></td>
                                    <td class="text-center"><strong>Оплата</strong></td>
                                    <td class="text-center"><strong>Доставка</strong></td>
                                    <td class="text-center"><strong>Стоимость</strong></td>
                                    <td class="text-center"><strong>Скидка</strong></td>
                                    <td class="text-center"><strong>Доставлен</strong></td>
                                    <td class="text-center"><strong>Отгружен</strong></td>
                                    <td class="text-center"><strong>Покупатель</strong></td>
                                    <td class="text-center"><strong></strong></td>
                                </tr>
                                </thead>
                                <tbody>
                                <!-- foreach ($order->lineItems as $line) or some such thing here -->
                                <c:forEach items="${orderDetails}" var="orderDetail">
                                    <tr>
                                        <td>${orderDetail.id}</td>
                                        <td class="text-center">${orderDetail.orderDate}</td>
                                        <td class="text-center">
                                            <c:if test="${orderDetail.isPay()}">Картой</c:if>
                                            <c:if test="${not orderDetail.isPay()}">Наличными</c:if></td>
                                        <td class="text-center">
                                            <c:if test="${orderDetail.isDelivery()}">Курьером</c:if>
                                            <c:if test="${not orderDetail.isDelivery()}">Самовывоз</c:if>
                                        </td>
                                        <td class="text-center">&#8381;${orderDetail.orderTotal}</td>
                                        <td class="text-center">${orderDetail.discount}%</td>
                                        <td class="text-center">
                                            <c:if test="${orderDetail.isDelivery}">Да</c:if>
                                            <c:if test="${not orderDetail.isDelivery}">Нет</c:if>
                                        </td>
                                        <td class="text-center">
                                            <c:if test="${orderDetail.isShippedOrder()}">Да</c:if>
                                            <c:if test="${not orderDetail.isShippedOrder()}">Нет</c:if>
                                        </td>
                                        <td class="text-center">${orderDetail.user.firstName} ${orderDetail.user.lastName}</td>
                                        <td class="text-center"><a
                                                href="${contextRoot}/manage/${orderDetail.id}/orderChange"
                                                class="submit-button1122">Детали</a></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>