<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div class="container">
    <div class="row">



        <div class="col-md-6">
            <div class="row">
                <div class="form-container1122">

                    <form action="${contextRoot}/manage/orders" method="post">

                        <label class="form-title1122">Доставлен:</label>
                        <div class="col-md-8">
                            <label class="radio-inline">
                                <input type="radio" name="delivered" id="no" value="no" required autofocus>Нет
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="delivered" id="yes" value="yes">Да
                            </label>
                        </div>

                        <label class="form-title1122">Отгружен</label>
                        <div class="col-md-8">
                            <label class="radio-inline">
                                <input type="radio" name="shipped" id="not" value="not" required autofocus>Нет
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="shipped" id="yess" value="yess">Да
                            </label>
                        </div>

                        <br/>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <button type="submit" name="_eventId_billing" class="submit-button1122">
                                    Заказать
                                </button>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input type="hidden" name="orderId" value="${orderDetail.id}"/>
                            </div>

                        </div>
                    </form>


                </div>
            </div>
        </div>



    </div>
</div>