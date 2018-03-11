<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="availableCount" value="${userModel.cart.cartLines}" />
<div class="container">
    <c:if test="${not empty message}">
        <div class="col-xs-12">

            <div class="alert alert-info">

                <button type="button" class="close" data-dismiss="alert">&times;</button>

                    ${message}

            </div>
        </div>
    </c:if>
    <c:choose>

        <%--@elvariable id="cartLines" type="java.util.List"--%>
        <c:when test="${not empty cartLines}">
            <%--@elvariable id="userModel" type="com.divanxan.internetshop.model.UserModel"--%>
            <table id="cart" class="table table-hover table-condensed">
                <thead>
                <tr>
                    <th style="width:50%">Товар</th>
                    <th style="width:10%">Цена</th>
                    <th style="width:8%">Количество</th>
                    <th style="width:22%" class="text-center">Общая цена</th>
                    <th style="width:10%"></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${cartLines}" var="cartLine">
                    <c:if test="${cartLine.productCount == 0}">
                        <c:set var="availableCount" value="${0}"/>
                    </c:if>
                    <tr>
                        <td data-th="Product">
                            <div class="row">
                                <div class="col-sm-2 hidden-xs"><img src="${images}/${cartLine.product.code}.jpg"
                                                                     alt="${cartLine.product.name}"
                                                                     class="img-responsive cartImg"/></div>
                                <div class="col-sm-12">
                                    <h4 class="text-right">${cartLine.product.name}
                                    <c:if test="${cartLine.available == false}">
                                        <strong class="unavailable">(Не доступен)</strong>
                                    </c:if>
                                    </h4>
                                    <p>Производитель - ${cartLine.product.brand} </p>
                                    <p>Описание - ${cartLine.product.description} </p>
                                </div>
                            </div>
                        </td>
                        <td data-th="Price">&#8381; ${cartLine.buyingPrice}</td>
                        <td data-th="Quantity">
                            <%-- Тут задаем максимальное значение и минимальное тоже и id --%>
                            <input type="number" id="count_${cartLine.id}" min="1" max="5" class="form-control text-center" value="${cartLine.productCount}">
                        </td>
                        <td data-th="Subtotal" class="text-center">&#8381; ${cartLine.total}</td>
                        <td class="actions" data-th="">
                            <c:if test="${cartLine.available == true}">
                                <%-- Тут изменим кнопку обновления type="button"  name="refreshCart" value="${cartLine.id}"--%>
                            <button type="button" name="refreshCart" value="${cartLine.id}" class="btn btn-info btn-sm"><span class="oi oi-reload"></span></button>
                            </c:if>
                            <a href="${contextRoot}/cart/${cartLine.id}/delete" class="btn btn-danger btn-sm"><span class="oi oi-trash"></span></a>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
                <tfoot>
                <tr class="visible-xs">
                    <td class="text-center"><strong>Всего &#8381; ${userModel.cart.grandTotal}</strong></td>
                </tr>
                <tr>
                    <td><a href="${contextRoot}/show/all/products" class="btn btn-warning"><span class="oi oi--left"></span> Продолжить покупки</a></td>
                    <td colspan="2" class="hidden-xs"></td>
                    <td class="hidden-xs text-center"><strong>Всего &#8381; ${userModel.cart.grandTotal}</strong></td>
                    <security:authorize access="isAuthenticated()">
                    <c:choose>
                    <c:when test="${availableCount != 0}">
                    <td><a href="${contextRoot}/cart/checkProducts" class="btn btn-success btn-block">Оформить покупку <span class="oi oi-right"></span></a>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </td>
                        </c:when>
                        <c:otherwise>
                    <td><a href="javascript:void(0)" class="btn btn-success btn-block disabled">Оформить покупку <span class="oi oi-right"></span></a>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </td>
                    </c:otherwise>
                    </c:choose>
                    </security:authorize>
                    <security:authorize access="isAnonymous()">
                        <td><a href="${contextRoot}/login" class="btn btn-success btn-block">Войти<span class="oi oi-right"></span></a>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </td>
                        <td><a href="${contextRoot}/register" class="btn btn-success btn-block">Зарегестироваться<span class="oi oi-right"></span></a>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </td>
                    </security:authorize>
                </tr>
                </tfoot>
            </table>
        </c:when>

        <c:otherwise>
            <div class="jumbotron">
                <div class="text-center">
                    <h1>Ваша корзина пуста</h1>
                </div>
            </div>

        </c:otherwise>
    </c:choose>
</div>