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
                    <h4>Регистрация</h4>
                </div>

                <div class="panel-body">


                    <form class="form-horizontal" action="${contextRoot}/userr/newName" method="POST">

                        <div class="form-group">
                            <label class="form-title1122">Имя</label>
                            <div class="col-md-8">
                                <input type="text" name="firstName" placeholder="Иван"/>
                                <%--<sf:errors path="firstName" cssClass="help-block" element="em"/>--%>
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="submit-button1122">
                                    Далее<span class="oi oi-chevron-right"></span>
                                </input>
                                <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                            </div>


                        </div>

                    </form>

                </div>


            </div>
        </div>

    </div>
    <!-- //////////////////////////////////////////////////// -->

</div>