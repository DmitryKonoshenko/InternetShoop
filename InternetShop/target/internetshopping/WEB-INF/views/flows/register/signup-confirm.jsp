<%--@elvariable id="registerModel" type="com.divanxan.internetshop.model.RegisterModel"--%>
<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../shared/flows-header.jsp" %>
<!-- Page Content -->
<div class="content">

    <div class="row">

        <div class="form-container1122">

            <div class="panel panel-primary">

                <div class="panel-heading">
                    <h4>Персональные данные</h4>
                </div>

                <div class="panel-body">
                    <div class="text-center">
                        <h4>${registerModel.user.firstName} ${registerModel.user.lastName}</h4>

                        <h5>Email: ${registerModel.user.email}</h5>

                        <h5>Телефон: ${registerModel.user.contactNumber}</h5>
                    </div>
                </div>

                <div class="panel-footer">
                    <a href="${flowExecutionUrl}&_eventId_personal" class="submit-button1122">Изменить</a>
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
                        <h4>${registerModel.billing.addressLineOne}</h4>
                        <h4>${registerModel.billing.addressLineTwo}</h4>
                        <h4>${registerModel.billing.city} - ${registerModel.billing.postalCode}</h4>
                        <h4>${registerModel.billing.state} - ${registerModel.billing.country}</h4>
                    </div>
                </div>

                <div class="panel-footer">

                    <a href="${flowExecutionUrl}&_eventId_billing" class="submit-button1122">Изменить</a>

                </div>

            </div>
            <div class="text-right">

                <a href="${flowExecutionUrl}&_eventId_submit" class="submit-button1122">Подтвердить</a>


            </div>

        </div>

    </div>

</div>

<%@include file="../shared/flows-footer.jsp" %>

