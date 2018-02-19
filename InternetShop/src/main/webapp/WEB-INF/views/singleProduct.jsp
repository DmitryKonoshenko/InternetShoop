<jsp:useBean id="product" scope="request" type="com.divanxan.internetshop.dto.Product"/>
<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8"%>

<div class="container">

    <div class="row">

        <div class="col-xs-12">

            <oi class ="breadcrumb">

                <li><a href="${contextRoot}/home">На главную</a></li>
                <li><a href="${contextRoot}/show/all/products">-> Наши товары</a></li>
                <li class="active">-> ${product.name}</li>

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


            <c:choose>
                <c:when test="${product.quantity<1}">
                    <a href="javasript:void(0)" class="btn btn-success disabled"><strike>
                        <span class="oi oi-cart">Добавить в корзину</span></strike></a>
                </c:when>
                <c:otherwise>

                    <a href="${contextRoot}/cart/add/product" class="btn btn-success">
                        <span class="oi oi-cart">Добавить в корзину</span></a>

                </c:otherwise>
            </c:choose>


            <a href="${contextRoot}/show/all/products" class="btn btn-primary">Вернуться</a>
        </div>

    </div>

</div>
