<%--<jsp:useBean id="product" scope="request" type="com.divanxan.internetshop.dto.Product"/>--%>
<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8"%>
<div class="container">

    <div class="row">

        <div class="col-xs-12">

            <oi class ="breadcrumb">

                <li><a href="${contextRoot}/home">На главную</a></li>
                <li><a href="${contextRoot}/show/all/products">-> Наши товары:</a></li>
                <li class="active">${product.name}</li>

            </oi>

        </div>

    </div>

    <div class="row">
        <!-- Product image -->
        <div class="col-xs-12 col-sm-4">
<div class="thumbnail">

    <img src="${images}/${product.code}.jpg" class="dataSingleImg"/>

</div>
        </div>
        <!-- Product description -->
        <div class="col-xs-12 col-sm-8">
            <h3>${product.name}</h3>
            <hr/>

            <p>${product.description}</p>
            <hr/>

            <h4>Стоимость товара: <strong> &#8381; ${product.unitPrice} /-</strong></h4>
            <hr/>

                <c:choose>
                    <c:when test="${product.quantity<1}">
                        <h6>Количество: <span color="red">Нет на складе!</span></h6>
                    </c:when>
                    <c:otherwise>
                        <h6>Количество: ${product.quantity}</h6>
                    </c:otherwise>
                </c:choose>

<security:authorize access="hasAuthority('USER') or isAnonymous()">
            <c:choose>
                <c:when test="${product.quantity<1}">
                    <a href="javascript:void(0)" class="btn btn-success disabled">
                        <span class="oi oi-cart">Добавить в корзину</span></a>
                    <%--TODO доделать заглушку для инкремента желаемого товара--%>
                    <button type="button" name="likeProduct" value="${product.id}" onclick="buttonClickt()" id="b1" class="btn btn-primary">
                        <span class="oi oi-heart">
                        Кликните тут и мы закажем данный товар
                    </span></button>
                </c:when>
                <c:otherwise>
                    <%--@elvariable id="product" type="com.divanxan.internetshop.dto.Product"--%>

                    <a href="${contextRoot}/cart/add/${product.id}/product" class="btn btn-success">
                        <span class="oi oi-cart">Добавить в корзину</span></a>

                </c:otherwise>
            </c:choose>
</security:authorize>
<security:authorize access="hasAuthority('ADMIN')">
    <%--@elvariable id="product" type="com.divanxan.internetshop.dto.Product"--%>
    <a href="${contextRoot}/manage/${product.id}/product" class="btn btn-warning">
        <span class="oi oi-wrench">Редактировать</span></a>
</security:authorize>

            <a href="${contextRoot}/show/all/products" class="btn btn-primary">Вернуться</a>
        </div>

    </div>

</div>
