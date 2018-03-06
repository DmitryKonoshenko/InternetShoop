<%--@elvariable id="_csrf" type="org.springframework.web.bind.MissingServletRequestParameterException"--%>
<%--@elvariable id="userAddress" type="com.divanxan.internetshop.dto.Address"--%>
<%--@elvariable id="userData" type="com.divanxan.internetshop.dto.User"--%>
<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Page Content --%>
<div class="content">

    <c:if test="${not empty message}">
        <div class="col-xs-12">

            <div class="alert alert-info">

                <button type="button" class="close" data-dismiss="alert">&times;</button>

                    ${message}

            </div>
        </div>
    </c:if>

    <div class="row">

        <div class="form-container1122">

            <div class="panel panel-primary">

                <div class="panel-heading">
                    <h4>Персональные данные</h4>
                </div>

                <div class="panel-body">
                    <div class="text-center">
                        <h4>${userData.firstName} ${userData.lastName}</h4>

                        <h5>Email: ${userData.email}</h5>

                        <h5>Телефон: ${userData.contactNumber}</h5>
                    </div>
                </div>

                <div class="panel-footer">
                    <a href="${contextRoot}/user/userName" class="submit-button1122">Изменить</a>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </div>

            </div>


        </div>
        <%--@elvariable id="addresses" type="java.util.List"--%>
        <c:forEach items="${addresses}" var="userAddress">
        <div class="form-container1122">

            <div class="panel panel-primary">

                <div class="panel-heading">
                    <h4>Адрес</h4>
                </div>

                <div class="panel-body">
                    <div class="text-center">
                        <h4>${userAddress.addressLineOne}</h4>
                        <h4>${userAddress.addressLineTwo}</h4>
                        <h4>${userAddress.city} - ${userAddress.postalCode}</h4>
                        <h4>${userAddress.state} - ${userAddress.country}</h4>
                    </div>
                </div>

                <div class="panel-footer">

                    <a href="${contextRoot}/user/${userAddress.id}/userAddress" class="submit-button1122">Изменить</a>

                </div>

            </div>


        </div>
        </c:forEach>

        <div class="form-container1122">

            <div class="panel panel-primary">

                <div class="panel-heading">
                    <h4>Изменить пароль</h4>
                </div>

                <div class="panel-footer">
                    <a href="${contextRoot}/user/userPassword" class="submit-button1122">Изменить</a>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </div>

            </div>
            <div class="text-right">
                <a href="${contextRoot}/home" class="submit-button1122">Вернуться</a>
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
                        <div class="text-center">
                            <h4>Номер заказа: ${orderDetail.id}</h4>
                            <h4>Дата заказа:${orderDetail.orderDate}</h4>
                            <h4>Оплата заказа:
                                <c:if test="${orderDetail.isPay()}">Картой</c:if>
                                <c:if test="${not orderDetail.isPay()}">Наличными</c:if>
                            </h4>
                            <h4>доставка заказа:
                                <c:if test="${orderDetail.getIsDelivery()}">Курьером</c:if>
                                <c:if test="${not orderDetail.getIsDelivery()}">Самовывоз</c:if>
                            </h4>
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
        </c:forEach>



    </div>

</div>