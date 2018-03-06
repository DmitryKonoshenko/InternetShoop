<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div class="container">

    <div class="form-container1122">
        <div class="panel panel-primary">
            <a href="${contextRoot}/manage/orders" class="submit-button1122">Вернутся</a>
        </div>
    </div>

    <div class="form-container1122">

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


    <div class="form-container1122">

        <div class="panel panel-primary">

            <div class="panel-heading">
                <h4>Топ 10 заказов</h4>
            </div>


            <table class="table table-condensed">
                <thead>
                <tr>
                    <td><strong>Товар</strong></td>
                    <td><strong>Бренд</strong></td>
                    <td class="text-center"><strong>Цена</strong></td>
                    <td class="text-center"><strong>Количество раз куплено</strong></td>
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
                    </tr>
                </c:forEach>
                </tbody>
            </table>


        </div>
    </div>
    <div class="form-container1122">

        <div class="panel panel-primary">

            <div class="panel-heading">
                <h4>Топ 10 покупателей</h4>
            </div>


            <table class="table table-condensed">
                <thead>
                <tr>
                    <td><strong>Имя</strong></td>
                    <td><strong>Контактный номер</strong></td>
                    <td class="text-center"><strong>Потратил за месяц</strong></td>
                </tr>
                </thead>
                <tbody>

                <%--@elvariable id="listUsers" type="java.util.Map"--%>
                <c:forEach items="${listUsers}" var="user">
                    <tr>
                        <td>${user.key.firstName} ${user.key.lastName}</td>
                        <td>${user.key.contactNumber}</td>
                        <td class="text-center">&#8381; ${user.value}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>


        </div>
    </div>
    <div class="form-container1122">
        <div class="panel panel-primary">
            <a href="${contextRoot}/manage/orders" class="submit-button1122">Вернутся</a>
        </div>
    </div>

</div>