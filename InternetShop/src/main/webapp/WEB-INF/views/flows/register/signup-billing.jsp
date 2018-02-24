<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../shared/flows-header.jsp" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div class="container">


    <div class="row">
        <div class="form-container1122">
            <div class="panel panel-primary">

                <div class="form-title1122">
                    <h4>Sign Up - Address</h4>
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
                            <label class="form-title1122" for="addressLineOne">Address Line One</label>
                            <div class="col-md-8">
                                <sf:input type="text" path="addressLineOne" class="form-control"
                                          placeholder="Enter Address Line One"/>
                                    <sf:errors path="addressLineOne" cssClass="help-block" element="em"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="form-title1122" for="addressLineTwo">Address Line Two</label>
                            <div class="col-md-8">
                                <sf:input type="text" path="addressLineTwo" class="form-control"
                                          placeholder="Enter Address Line Two"/>
                                    <sf:errors path="addressLineTwo" cssClass="help-block" element="em"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="form-title1122" for="city">City</label>
                            <div class="col-md-8">
                                <sf:input type="text" path="city" class="form-control"
                                          placeholder="Enter City Name"/>
                                    <sf:errors path="city" cssClass="help-block" element="em"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="form-title1122" for="postalCode">Postal Code</label>
                            <div class="col-md-8">
                                <sf:input type="text" path="postalCode" class="form-control"
                                          placeholder="XXXXXX"/>
                                    <sf:errors path="postalCode" cssClass="help-block" element="em"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="form-title1122" for="state">State</label>
                            <div class="col-md-8">
                                <sf:input type="text" path="state" class="form-control"
                                          placeholder="Enter State Name"/>
                                    <sf:errors path="state" cssClass="help-block" element="em"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="form-title1122" for="country">Country</label>
                            <div class="col-md-8">
                                <sf:input type="text" path="country" class="form-control"
                                          placeholder="Enter Country Name"/>
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

