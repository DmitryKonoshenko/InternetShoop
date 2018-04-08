<%--@elvariable id="_csrf" type="org.springframework.web.bind.MissingServletRequestParameterException"--%>
<%--@elvariable id="userAddress" type="com.divanxan.internetshop.dto.Address"--%>
<%--@elvariable id="userData" type="com.divanxan.internetshop.dto.User"--%>
<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Page Content --%>
<div class="content">
    <div class="col-lg-12">
        <c:if test="${not empty message}">
            <div class="col-xs-12">
                <div class="alert alert-info">
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
                            <div class="panel-heading">
                                <h4>Персональные данные:</h4>
                            </div>
                            <div class="panel-body">
                                <div class="text-left">
                                    <h4>${userData.firstName} ${userData.lastName}</h4>
                                    <h5>Email: ${userData.email}</h5>
                                    <h5>Телефон: ${userData.contactNumber}</h5>
                                </div>
                            </div>
                            <br>
                            <div class="col-lg-2 col-md-6 mb-1">
                                <div class="panel-footer">
                                    <a href="${contextRoot}/user/userName" class="submit-button1122">Изменить</a>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <div class="form-container">
            <div class="panel panel-primary">
                <div class="col-lg-12 col-md-6 mb-3">
                    <div class="card">
                        <div class="m-2">
                            <div class="panel-heading">
                                <h4>Изменить пароль:
                                    <a href="${contextRoot}/user/userPassword"
                                       class="submit-button1122">Изменить</a>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </h4>
                            </div>
                            <br>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--@elvariable id="addresses" type="java.util.List"--%>
        <c:forEach items="${addresses}" var="userAddress">
            <div class="form-container">
                <div class="panel panel-primary">
                    <div class="col-lg-12 col-md-6 mb-1">
                        <div class="card">
                            <div class="m-2">
                                <div class="panel-heading">
                                    <h4>Адрес:</h4>
                                </div>
                                <div class="panel-body">
                                    <div class="text-left">
                                        <h4>${userAddress.addressLineOne}</h4>
                                        <h4>${userAddress.addressLineTwo}</h4>
                                        <h4>${userAddress.city} - ${userAddress.postalCode}</h4>
                                        <h4>${userAddress.state} - ${userAddress.country}</h4>
                                    </div>
                                </div>
                                <br>
                                <div class="col-lg-2 col-md-6 mb-1">
                                    <div class="panel-footer">
                                        <a href="${contextRoot}/user/${userAddress.id}/userAddress"
                                           class="submit-button1122">Изменить</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <br>
        </c:forEach>
        <%--@elvariable id="orderDetails" type="java.util.List"--%>
        <c:if test="${not empty orderDetails}">
        <div class="form-container">
            <div class="panel panel-primary">
                <div class="col-lg-12 col-md-6 mb-3">
                    <div class="card">
                        <div class="m-2">
                            <div class="panel-heading">
                                <div class="panel-heading">
                                    <h4>История заказов:</h4>
                                </div>
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
                                            <td class="text-center"><a
                                                    href="${contextRoot}/user/${orderDetail.id}/orderDetail"
                                                    class="submit-button1122">Детали</a></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </c:if>
    </div>
</div>