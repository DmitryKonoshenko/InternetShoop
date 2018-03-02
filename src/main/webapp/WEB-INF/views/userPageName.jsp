<%--@elvariable id="_csrf" type="org.springframework.web.bind.MissingServletRequestParameterException"--%>
<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%-- Page Content --%>
<div class="content">

    <%-- //////////////////////////////////////////////////// --%>
    <div class="row">

        <div class="form-container1122">

            <div class="panel panel-primary">

                <div class="form-title1122">
                    <h4>Изменение персональных данных</h4>
                </div>

                <div class="panel-body">
                    <div class="form-title1122">
                        <h6>Введите новые значение в те поля, которые требуют изменения</h6>
                    </div>
                    <%--@elvariable id="user" type="com.divanxan.internetshop.dto.User"--%>
                    <form id="userForm" modelAttribute="user" action="${contextRoot}/user/show" method="POST"
                          cssClass="form-horizontal">
                        <%--<form class="form-horizontal" action="${contextRoot}/user/newName" method="POST">--%>

                        <div class="form-group">
                            <label class="form-title1122">Имя</label>
                            <div class="col-md-8">
                                <input type="text" name="firstName" class="form-control"
                                       placeholder="Иван"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="form-title1122">Фамилия</label>
                            <div class="col-md-8">
                                <input type="text" name="lastName" class="form-control"
                                       placeholder="Пупкин"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="form-title1122">Email</label>
                            <div class="col-md-8">
                                <input type="text" name="email" class="form-control"
                                       placeholder="abc@zyx.com"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="form-title1122">Телефон</label>
                            <div class="col-md-8">
                                <input type="text" name="contactNumber" class="form-control"
                                       placeholder="XXXXXXXXXX" maxlength="12"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <button type="submit" name="_eventId_billing" class="submit-button1122">
                                    Изменить
                                </button>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </div>

                        </div>
                    </form>
                </div>


            </div>
        </div>

    </div>
    <!-- //////////////////////////////////////////////////// -->

</div>