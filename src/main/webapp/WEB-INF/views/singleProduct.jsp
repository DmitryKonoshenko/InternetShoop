<%--<jsp:useBean id="product" scope="request" type="com.divanxan.internetshop.dto.Product"/>--%>
<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%--@elvariable id="product" type="com.divanxan.internetshop.dto.Product"--%>
<c:set var="productD" value="${product.productDis}"/>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <oi class="breadcrumb">
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
            <h4>Стоимость товара: <strong> &#8381; ${product.unitPrice}</strong></h4>
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
                <c:if test="${product.quantity>0}">
                    <%--@elvariable id="product" type="com.divanxan.internetshop.dto.Product"--%>
                    <a href="${contextRoot}/cart/add/${product.id}/product" class="btn btn-success">
                        <span class="oi oi-cart">Добавить в корзину</span></a>
                </c:if>
            </security:authorize>
            <security:authorize access="hasAuthority('ADMIN')">
                <%--@elvariable id="product" type="com.divanxan.internetshop.dto.Product"--%>
                <a href="${contextRoot}/manage/${product.id}/product" class="btn btn-warning">
                    <span class="oi oi-wrench">Редактировать</span></a>
            </security:authorize>
            <c:if test="${productD==null}">
                <a href="${contextRoot}/show/all/products" class="btn btn-primary">Вернуться</a>
            </c:if>
        </div>
    </div>
    <security:authorize access="hasAuthority('USER') or isAnonymous()">
        <c:if test="${productD!=null}"><%--@elvariable id="newPrice" type="java.math.BigDecimal"--%>
            <hr>
            <div class="row">
                <h4>C данным товаром вы можете приобрести:</h4>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-4">
                    <div class="thumbnail">

                        <a href="${contextRoot}/show/${productD.id}/product"><img src="${images}/${productD.code}.jpg"
                                                                                  class="dataSingleImgSmall"/></a>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-8">
                    <h3>${productD.name}</h3>
                    <hr/>
                    <p>${productD.description}</p>
                    <hr/>
                    <h4>Стоимость товара: <strong>&#8381;<s> ${productD.unitPrice}</s>/${newPrice}</strong></h4>
                    <h4> Cо скидкой ${product.discount}%!</h4>
                    <hr/>
                    <c:choose>
                        <c:when test="${productD.quantity<1}">
                            <h6>Количество: <span style="color:red">Нет на складе!</span></h6>
                        </c:when>
                        <c:otherwise>
                            <h6>Количество: ${productD.quantity}</h6>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${product.quantity>0}">
                        <%--@elvariable id="product" type="com.divanxan.internetshop.dto.Product"--%>
                        <a href="${contextRoot}/cart/add/${product.id}/products" class="btn btn-success">
                            <span class="oi oi-cart">Добавить в корзину оба товара</span></a>
                        <a href="${contextRoot}/show/all/products" class="btn btn-primary">Вернуться</a>
                    </c:if>
                </div>
            </div
        </c:if>
    </security:authorize>
    <security:authorize access="hasAuthority('ADMIN')">
        <c:if test="${productD!=null}"><%--@elvariable id="newPrice" type="java.math.BigDecimal"--%>
            <hr>
            <div class="row">
                <h4>C данным товаром вы можете приобрести:</h4>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-4">
                    <div class="thumbnail">
                        <a href="${contextRoot}/show/${productD.id}/product"><img src="${images}/${productD.code}.jpg"
                                                                                  class="dataSingleImgSmall"/></a>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-8">
                    <h3>${productD.name}</h3>
                    <hr/>
                    <p>${productD.description}</p>
                    <hr/>
                    <h4>Стоимость товара: <strong>&#8381;<s> ${productD.unitPrice}</s>/${newPrice}</strong></h4>
                    <h4> Cо скидкой ${product.discount}%!</h4>
                    <hr/>
                    <c:choose>
                        <c:when test="${productD.quantity<1}">
                            <h6>Количество: <span style="color:red">Нет на складе!</span></h6>
                        </c:when>
                        <c:otherwise>
                            <h6>Количество: ${productD.quantity}</h6>
                        </c:otherwise>
                    </c:choose>
                    <a href="${contextRoot}/manage/${product.id}/product" class="btn btn-warning">
                        <span class="oi oi-wrench">Редактировать</span></a>
                    <a href="${contextRoot}/show/all/products" class="btn btn-primary">Вернуться</a>
                </div>
            </div
        </c:if>
    </security:authorize>
</div>
