<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../views/shared/flows-header.jsp" %>
<div class="container">

    <c:if test="${not empty message}">
        <div class="col-xs-12">

            <div class="alert alert-info">

                <button type="button" class="close" data-dismiss="alert">&times;</button>

                    ${message}

            </div>
        </div>
    </c:if>

    <div class="row">
        <%--  Все товары в корзине --%>

        <div class="col-md-6">

            <div class="row">
                <div class="form-container1122">
                    <c:forEach items="${checkoutModel.cartLines}" var="cartLine">
                        <div class="col-xs-12">

                            <div>
                                <h3>Название - ${cartLine.product.name}</h3>
                                <hr/>
                                <h5>Количество - ${cartLine.productCount}</h5>
                                <h5>Цена - &#8381; ${cartLine.buyingPrice}/-</h5>
                            </div>
                            <hr/>
                            <div class="text-right">
                                <h3>Итого - &#8381; ${cartLine.total}/-</h3>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>


        <div class="col-md-6">
            <div class="row">
                <div class="form-container1122">

                    <form action="${contextRoot}/order/payment" method="post">

                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">
                                    Детали оплаты
                                </h3>
                            </div>
                            <div class="panel-body">

                                <div class="form-group">
                                    <label for="cardNumber">
                                        Номер карты</label>
                                    <div class="input-group">
                                        <%--добавь если хочешь введенные значения required autofocus--%>
                                        <input type="text" class="form-control" name="cardNumber" id="cardNumber"
                                               placeholder="1111-1111-1111-1111"
                                               />
                                        <span class="input-group-addon"><span
                                                class="oi ou-lock"></span></span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-7 col-md-7">
                                        <div class="form-group">
                                            <label for="expityMonth">Срок действия</label>
                                            <br/>
                                            <div class="col-xs-6 col-lg-6 pl-ziro">
                                                <input type="text" class="form-control" name="expityMonth" id="expityMonth"
                                                       placeholder="MM" />
                                            </div>
                                            <div class="col-xs-6 col-lg-6 pl-ziro">
                                                <input type="text" class="form-control" name="expityYear" id="expityYear" placeholder="YY"
                                                       /></div>
                                        </div>
                                    </div>
                                    <div class="col-xs-5 col-md-5 pull-right">
                                        <div class="form-group">
                                            <label for="cvCode">
                                                CV CODE</label>
                                            <input type="password" class="form-control" name="cvCode" id="cvCode" placeholder="CV"
                                                   />
                                        </div>
                                    </div>
                                </div>
                                <%--</form>--%>
                            </div>
                        </div>
                        <ul class="nav nav-pills nav-stacked">
                            <li class="active"><a href="#"><span
                                    class="badge pull-right"> &#8381; ${checkoutModel.checkoutTotal}/-</span>Общая цена</a>
                            </li>
                        </ul>

                        <label class="form-title1122">Выберете способ оплаты</label>
                        <div class="col-md-8">
                            <label class="radio-inline">
                                <input type="radio" name="isPayByCArt" id="cart" value="cart" checked>Картой
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isPayByCArt" id="cash" value="cash">При получении
                            </label>
                        </div>

                        <label class="form-title1122">Выберете способ доставки</label>
                        <div class="col-md-8">
                            <label class="radio-inline">
                                <input type="radio" name="delivery" id="byMail" value="byMail" checked>Доставка по адресу
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="delivery" id="yourself" value="yourself">Самовывоз
                            </label>
                        </div>

                        <br/>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <button type="submit" name="_eventId_billing" class="submit-button1122">
                                    Заказать
                                </button>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </div>

                        </div>
                    </form>


                </div>
            </div>
        </div>

    </div>
</div>
<%@include file="../views/flows/shared/flows-footer.jsp" %>