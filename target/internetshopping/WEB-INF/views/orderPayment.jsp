<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../views/flows/shared/flows-header.jsp" %>
<div class="container">

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
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                Детали оплаты
                            </h3>
                        </div>
                        <div class="panel-body">
                            <%--<form role="form">--%>
                                <div class="form-group">
                                    <label for="cardNumber">
                                        Номер карты</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="cardNumber"
                                               placeholder="1111-1111-1111-1111"
                                               required autofocus/>
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
                                                <input type="text" class="form-control" id="expityMonth"
                                                       placeholder="MM" required/>
                                            </div>
                                            <div class="col-xs-6 col-lg-6 pl-ziro">
                                                <input type="text" class="form-control" id="expityYear" placeholder="YY"
                                                       required/></div>
                                        </div>
                                    </div>
                                    <div class="col-xs-5 col-md-5 pull-right">
                                        <div class="form-group">
                                            <label for="cvCode">
                                                CV CODE</label>
                                            <input type="password" class="form-control" id="cvCode" placeholder="CV"
                                                   required/>
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
                        <form id="userForm" modelAttribute="checkoutModel" action="${contextRoot}/order/payment"
                              method="POST"
                              cssClass="form-horizontal">
                            <label class="radio-inline">
                                <input type="radio" name="isPayByCArt"  autocomplete="off" checked="checked">Картой
                                <input type="radio" name="isPayByCArt"  autocomplete="off">При получении
                            </label>
                        </form>
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
                </div>
            </div>
        </div>

    </div>
</div>
<%@include file="../views/flows/shared/flows-footer.jsp" %>