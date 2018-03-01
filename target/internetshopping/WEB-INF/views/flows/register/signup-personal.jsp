<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@include file="../shared/flows-header.jsp" %>
<!-- Page Content -->
<div class="content">

    <!-- //////////////////////////////////////////////////// -->
    <div class="row">

        <div class="form-container1122">

            <div class="panel panel-primary">

                <div class="form-title1122">
                    <h4>Регистрация</h4>
                </div>

                <div class="panel-body">

                    <sf:form
                            method="POST"
                            modelAttribute="user"
                            class="form-horizontal"
                            id="registerForm"
                    >
                    <div class="form-group">
                        <label class="form-title1122">Имя</label>
                        <div class="col-md-8">
                            <sf:input type="text" path="firstName" class="form-control"
                                      placeholder="Иван"/>
                            <sf:errors path="firstName" cssClass="help-block" element="em"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="form-title1122">Фамилия</label>
                        <div class="col-md-8">
                            <sf:input type="text" path="lastName" class="form-control"
                                      placeholder="Пупкин"/>
                                <sf:errors path="lastName" cssClass="help-block" element="em"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="form-title1122">Email</label>
                        <div class="col-md-8">
                            <sf:input type="text" path="email" class="form-control"
                                      placeholder="abc@zyx.com"/>
                                <sf:errors path="email" cssClass="help-block" element="em"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="form-title1122">Телефон</label>
                        <div class="col-md-8">
                            <sf:input type="text" path="contactNumber" class="form-control"
                                      placeholder="XXXXXXXXXX" maxlength="12"/>
                                <sf:errors path="contactNumber" cssClass="help-block" element="em"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="form-title1122">Пароль</label>
                        <div class="col-md-8">
                            <sf:input type="password" path="password" class="form-control"
                                      placeholder="Пароль"/>
                                <sf:errors path="password" cssClass="help-block" element="em"/>
                        </div>
                    </div>

                        <div class="form-group">
                            <label class="form-title1122">Ведите пароль еще раз</label>
                            <div class="col-md-8">
                                <sf:input type="password" path="confirmPassword" class="form-control"
                                          placeholder="Повторите пароль"/>
                                <sf:errors path="confirmPassword" cssClass="help-block" element="em"/>
                            </div>
                        </div>


                    <div class="form-group">
                        <label class="form-title1122">Выберете цель регитрации</label>
                        <div class="col-md-8">
                            <label class="radio-inline">
                                <sf:radiobutton path="role" value="USER" checked="checked"/>Покупатель
                            </label>
                            <label class="radio-inline">
                                <sf:radiobutton path="role" value="SUPPLIER"/>Поставщик
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <button type="submit" name="_eventId_billing" class="submit-button1122">
                                Далее - Адрес <span class="oi oi-chevron-right"></span>
                            </button>
                        </div>


                        </sf:form>


                    </div>


                </div>


            </div>


        </div>
        <!-- //////////////////////////////////////////////////// -->

    </div>
    <%@include file="../shared/flows-footer.jsp" %>

