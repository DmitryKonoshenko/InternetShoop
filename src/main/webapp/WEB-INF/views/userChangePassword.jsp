<%--@elvariable id="_csrf" type="org.springframework.web.bind.MissingServletRequestParameterException"--%>
<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%-- Page Content --%>
<div class="content">
    <div class="row">
        <div class="form-container1122">
            <div class="panel panel-primary">
                <div class="form-title1122">
                    <h4>Изменение пароля</h4>
                </div>
                <div class="panel-body">
                    <%--@elvariable id="user" type="com.divanxan.internetshop.dto.User"--%>
                    <form id="userForm" modelAttribute="user" action="${contextRoot}/user/show" method="POST"
                          cssClass="form-horizontal">
                        <div class="form-group">
                            <label class="form-title1122">Введите новый пароль</label>
                            <div class="col-md-8">
                                <input type="password" name="password1" class="form-control"
                                       placeholder="******"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="form-title1122">Введите новый пароль еще раз</label>
                            <div class="col-md-8">
                                <input type="password" name="password2" class="form-control"
                                       placeholder="******"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="form-title1122">Введите текущий пароль</label>
                            <div class="col-md-8">
                                <input type="password" name="password3" class="form-control"
                                       placeholder="******"/>
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
</div>