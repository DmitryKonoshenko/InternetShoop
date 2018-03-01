<%--@elvariable id="_csrf" type="org.springframework.web.bind.MissingServletRequestParameterException"--%>
<%--@elvariable id="userAddress" type="com.divanxan.internetshop.dto.Address"--%>
<%--@elvariable id="userData" type="com.divanxan.internetshop.dto.User"--%>
<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Page Content --%>
<div class="content">

    <div class="row">

        <div class="form-container1122">

            <div class="panel panel-primary">

                <div class="panel-heading">
                    <h4>Персональные данные</h4>
                </div>

                <div class="panel-body">
                    <div class="text-center">
                        <h4>${userData.firstName} ${userData.lastName}</h4>

                        <h5>Email: ${userData.email}</h5>

                        <h5>Телефон: ${userData.contactNumber}</h5>
                    </div>
                </div>

                <div class="panel-footer">
                    <a href="${contextRoot}/userr/userName" class="submit-button1122">Изменить</a>
                    <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                </div>

            </div>


        </div>

        <div class="form-container1122">

            <div class="panel panel-primary">

                <div class="panel-heading">
                    <h4>Адрес</h4>
                </div>

                <div class="panel-body">
                    <div class="text-center">
                        <h4>${userAddress.addressLineOne}</h4>
                        <h4>${userAddress.addressLineTwo}</h4>
                        <h4>${userAddress.city} - ${userAddress.postalCode}</h4>
                        <h4>${userAddress.state} - ${userAddress.country}</h4>
                    </div>
                </div>

                <div class="panel-footer">

                    <a href="${flowExecutionUrl}&_eventId_billing" class="submit-button1122">Изменить</a>

                </div>

            </div>
            <div class="text-right">

                <a href="${contextRoot}/home" class="submit-button1122">Подтвердить</a>


            </div>

        </div>

    </div>

</div>