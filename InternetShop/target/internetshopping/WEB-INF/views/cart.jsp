<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<div class="container">

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
                        <td data-th="Price">&#8381; ${cartLine.total}</td>
                        <td data-th="Quantity">
                            <input type="number" class="form-control text-center" value="${cartLine.productCount}">
                        </td>
                        <td data-th="Subtotal" class="text-center">&#8381; ${cartLine.total}</td>
                        <td class="actions" data-th="">
                            <button class="btn btn-info btn-sm"><span class="oi oi-reload"></span></button>
                            <button class="btn btn-danger btn-sm"><span class="oi oi-trash"></span></button>
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
                    <td><a href="#" class="btn btn-success btn-block">Оформить покупку <span class="oi oi-right"></span></a>
                    </td>
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