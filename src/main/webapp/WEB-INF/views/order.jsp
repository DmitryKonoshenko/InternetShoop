<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../views/flows/shared/flows-header.jsp" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<%@include file="../views/shared/flows-header.jsp" %>
<div class="container">

    <div class="row">


        <div class="col-md-4">


            <h4>Выберете адрес доставки</h4>
            <hr/>

            <div class="row">
                <c:forEach items="${addresses}" var="address">
                    <div class="form-container1122">
                        <div class="cols-xs-12">
                            <h3>${address.addressLineOne}</h3>
                            <h3>${address.addressLineTwo}</h3>
                            <h4>${address.city} - ${address.postalCode}</h4>
                            <h4>${address.state} - ${address.country}</h4>
                            <hr/>
                            <div class="text-center">
                                <a href="${contextRoot}/order/${address.id}/select"
                                   class="submit-button1122">Выбрать</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>


        </div>


        <div class="col-md-8">

            <div class="form-container1122">

                <div class="panel panel-primary">

                    <div class="panel-heading">
                        <h4>Введите адрес доставки</h4>
                    </div>

                    <div class="panel-body">

                        <sf:form
                                method="POST"
                                modelAttribute="shipping"
                                class="form-horizontal"
                                id="billingForm"
                        >


                            <div class="form-group">
                                <label class="control-label col-md-4" for="addressLineOne">Введите первую строку
                                    адреса</label>
                                <div class="col-md-8">
                                    <sf:input type="text" path="addressLineOne" class="form-control"
                                              placeholder="адрес"/>
                                    <sf:errors path="addressLineOne" cssClass="help-block" element="em"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4" for="addressLineTwo">Введите вторую строку
                                    адреса</label>
                                <div class="col-md-8">
                                    <sf:input type="text" path="addressLineTwo" class="form-control"
                                              placeholder="адрес"/>
                                    <sf:errors path="addressLineTwo" cssClass="help-block" element="em"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4" for="city">Введите город</label>
                                <div class="col-md-8">
                                    <sf:input type="text" path="city" class="form-control"
                                              placeholder="Город"/>
                                    <sf:errors path="city" cssClass="help-block" element="em"/>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="control-label col-md-4" for="state">Введите область</label>
                                <div class="col-md-8">
                                    <sf:input type="text" path="state" class="form-control"
                                              placeholder="область"/>
                                    <sf:errors path="state" cssClass="help-block" element="em"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4" for="country">Введите страну</label>
                                <div class="col-md-8">
                                    <sf:input type="text" path="country" class="form-control"
                                              placeholder="страна"/>
                                    <sf:errors path="country" cssClass="help-block" element="em"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4" for="postalCode">Введите почтовый индекс</label>
                                <div class="col-md-8">
                                    <sf:input type="text" path="postalCode" class="form-control"
                                              placeholder="индекс"/>
                                    <sf:errors path="postalCode" cssClass="help-block" element="em"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" name="submit" id="submit" value="Сохранить"
                                           class="submit-button1122">
                                </div>
                            </div>


                        </sf:form>


                    </div>
                </div>


            </div>


        </div>


    </div>

</div>
<%@include file="../views/flows/shared/flows-footer.jsp" %>