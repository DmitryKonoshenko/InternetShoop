<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../shared/flows-header.jsp" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div class="container">


    <div class="row">
        <div class="form-container1122">
            <div class="panel panel-primary">

                <div class="form-title1122">
                    <h4>Регистрация - Адрес</h4>
                </div>

                <div class="panel-body">

                    <%--@elvariable id="billing" type="com.divanxan.internetshop.dto.Address"--%>
                    <sf:form
                            method="POST"
                            modelAttribute="billing"
                            class="form-horizontal"
                            id="billingForm"
                    >


                        <div class="form-group">
                            <label class="form-title1122" for="addressLineOne">Введите первую строку адреса</label>
                            <div class="col-md-8">
                                <sf:input type="text" path="addressLineOne" class="form-control"
                                          placeholder="адрес"/>
                                    <sf:errors path="addressLineOne" cssClass="help-block" element="em"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="form-title1122" for="addressLineTwo">Введите вторую строку адреса</label>
                            <div class="col-md-8">
                                <sf:input type="text" path="addressLineTwo" class="form-control"
                                          placeholder="адрес"/>
                                    <sf:errors path="addressLineTwo" cssClass="help-block" element="em"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="form-title1122" for="city">Введите город</label>
                            <div class="col-md-8">
                                <sf:input type="text" path="city" class="form-control"
                                          placeholder="город"/>
                                    <sf:errors path="city" cssClass="help-block" element="em"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="form-title1122" for="postalCode">Введите почтовый индекс</label>
                            <div class="col-md-8">
                                <sf:input type="text" path="postalCode" class="form-control"
                                          placeholder="индекс"/>
                                    <sf:errors path="postalCode" cssClass="help-block" element="em"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="form-title1122" for="state">Введите область</label>
                            <div class="col-md-8">
                                <sf:input type="text" path="state" class="form-control"
                                          placeholder="область"/>
                                    <sf:errors path="state" cssClass="help-block" element="em"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="form-title1122" for="country">Введите страну</label>
                            <div class="col-md-8">
                                <sf:input type="text" path="country" class="form-control"
                                          placeholder="страна"/>
                                    <sf:errors path="country" cssClass="help-block" element="em"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="form-title1122">
                                <button type="submit" name="_eventId_personal" class="submit-button1122">
                                    <span class="oi oi-chevron-left"></span>Назад - Персональные данные
                                </button>
                                <button type="submit" name="_eventId_confirm" class="submit-button1122">
                                    Далее - Подтвердить<span class="oi oi-chevron-right"></span>
                                </button>
                            </div>
                        </div>
                    </sf:form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../shared/flows-footer.jsp" %>

