<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div class="content">
    <div class="form-container">
        <div class="panel panel-primary">
            <div class="col-lg-12 col-md-6 mb-3">
                <div class="card">
                    <div class="m-2">
                        <div class="panel panel-primary">
                            <a href="${contextRoot}/manage/orders" class="submit-button1122">Вернутся</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-container">
            <div class="panel panel-primary">
                <div class="col-lg-12 col-md-6 mb-3">
                    <div class="card">
                        <div class="m-2">
                            <div class="panel panel-primary">
                                <table class="table table-condensed">
                                    <thead>
                                    <tr>
                                        <td><strong>Выручка за месяц</strong></td>
                                        <td class="text-center"><strong>Выручка за неделю</strong></td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>&#8381; ${cashByMonth}</td>
                                        <td class="text-center">&#8381;${cashByWeek}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-container">
            <div class="panel panel-primary">
                <div class="col-lg-12 col-md-6 mb-3">
                    <div class="card">
                        <div class="m-2">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <h4>Топ 10 продуктов</h4>
                                </div>
                                <table class="table table-condensed">
                                    <thead>
                                    <tr>
                                        <td><strong>Товар</strong></td>
                                        <td><strong>Бренд</strong></td>
                                        <td class="text-center"><strong>Цена</strong></td>
                                        <td class="text-center"><strong>Количество раз куплено</strong></td>
                                        <td class="text-center"><strong>Просмотров</strong></td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <!-- foreach ($order->lineItems as $line) or some such thing here -->
                                    <c:forEach items="${listProducts}" var="product">
                                        <tr>
                                            <td>${product.name}</td>
                                            <td>${product.brand}</td>
                                            <td class="text-center">&#8381; ${product.unitPrice}</td>
                                            <td class="text-center">${product.purchases}</td>
                                            <td class="text-center">${product.views}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-container">
                <div class="panel panel-primary">
                    <div class="col-lg-12 col-md-6 mb-3">
                        <div class="card">
                            <div class="m-2">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h4>Топ 10 покупателей</h4>
                                    </div>
                                    <table class="table table-condensed">
                                        <thead>
                                        <tr>
                                            <td><strong>Имя</strong></td>
                                            <td><strong>Контактный номер</strong></td>
                                            <td><strong>Email</strong></td>
                                            <td class="text-center"><strong>Потратил за месяц</strong></td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%--@elvariable id="listUsers" type="java.util.Map"--%>
                                        <c:forEach items="${listUsers}" var="user">
                                            <tr>
                                                <td>${user.value.firstName} ${user.value.lastName}</td>
                                                <td>${user.value.contactNumber}</td>
                                                <td>${user.value.email}</td>
                                                <td class="text-center">&#8381; ${user.key}</td>
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
            <div class="form-container">
                <div class="panel panel-primary">
                    <div class="col-lg-12 col-md-6 mb-3">
                        <div class="card">
                            <div class="m-2">
                                <div class="panel panel-primary">
                                    <a href="${contextRoot}/manage/orders" class="submit-button1122">Вернутся</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
