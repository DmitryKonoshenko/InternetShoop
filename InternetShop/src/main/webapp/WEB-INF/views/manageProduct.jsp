<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div class="container">
    <div class="row">

        <c:if test="${not empty message}">
            <div class="col-xs-12">

                <div class="alert alert-success alert-dismissible">

                    <button type="button" class="close" data-dismiss="alert">&times;</button>

                        ${message}

                </div>

            </div>
        </c:if>

        <div class="form-container1122">

            <div class="panel panel-primary">
                <div class="form-title1122g">
                    <h4>Редактирование товаров</h4>
                </div>
                <div class="panel-body">
                    <!-- Form Elements -->
                    <sf:form class="form-horizontal" modelAttribute="product"
                             action="${contextRoot}/manage/product"
                             method="POST"
                             enctype="multipart/form-data"
                    >

                    <div class="form-group">
                        <label class="form-title1122" for="name">Введите название товара</label>
                        <div class="col-md-8">
                            <sf:input type="text" path="name" id="name" placeholder="Название товара"
                                      class="form-field1122"/>
                            <sf:errors path="name" cssClass="help-block" element="em"/>
                        </div>

                        <div class="form-group">
                            <label class="form-title1122" for="brand">Введите название бренда</label>
                            <div class="col-md-8">
                                <sf:input type="text" path="brand" id="brand" placeholder="Название бренада"
                                          class="form-field1122"/>
                                <sf:errors path="brand" cssClass="help-block" element="em"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="form-title1122" for="description">Введите описание товара</label>
                            <div class="col-md-8">
                                <sf:textarea path="description" id="description" rows="4"
                                             placeholder="Не то, чтобы обязательно писать... Можно скопировать"
                                             class="form-field1122"/>
                                <sf:errors path="description" cssClass="help-block" element="em"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="form-title1122" for="unitPrice">Введите цену товара</label>
                            <div class="col-md-8">
                                <sf:input type="number" path="unitPrice" id="unitPrice"
                                          placeholder="Цена в рублях" class="form-field1122"/>
                                <sf:errors path="unitPrice" cssClass="help-block" element="em"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="form-title1122" for="quantity">Введите количество</label>
                            <div class="col-md-8">
                                <sf:input type="number" path="quantity" id="quantity"
                                          placeholder="Доступное количество товара"
                                          class="form-field1122"/>
                            </div>
                        </div>
                        <!-- File for image upload-->
                        <div class="form-group">
                            <label class="form-title1122" for="file">Загрузите изображение товара</label>
                            <div class="col-md-8">
                                <sf:input type="file" path="file" id="file" class="form-field1122"/>
                                <sf:errors path="file" cssClass="help-block" element="em"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="form-title1122" for="categoryId">Выбирете категорию: </label>
                            <div class="col-md-8">
                                <sf:select class="form-field1122" id="categoryId" path="categoryId"
                                           items="${categories}"
                                           itemLabel="name"
                                           itemValue="id"
                                />
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" name="submit" id="submit" value="Сохранить"
                                       class="submit-button1122"/>
                                <!-- Hidden fields for products -->
                                <sf:hidden path="id"/>
                                <sf:hidden path="code"/>
                                <sf:hidden path="supplierId"/>
                                <sf:hidden path="active"/>
                                <sf:hidden path="purchases"/>
                                <sf:hidden path="views"/>

                            </div>
                        </div>

                        </sf:form>
                    </div>
                </div>

            </div>

        </div>
    </div>
    <div class="row">



        <div class="form-container1122">
            <div class="form-title1122g">
                <h3>Avalible Products</h3>
            </div>
            <div style="overflow:auto">
                <table id="adminProductsTable" class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>&#160</th>
                        <th>Name</th>
                        <th>Brand</th>
                        <th>Quantity</th>
                        <th>Unit Price</th>
                        <th>Active</th>
                        <th>Edit</th>
                    </tr>
                    </thead>
                    <%-- см myapp.js  adminProductsTable--%>
                    <tfoot>
                    <tr>
                        <th>Id</th>
                        <th>&#160</th>
                        <th>Name</th>
                        <th>Brand</th>
                        <th>Quantity</th>
                        <th>Unit Price</th>
                        <th>Active</th>
                        <th>Edit</th>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</div>

<%--<form class="form-container1122">--%>
<%--<div class="form-title1122"><h2>Редактировать товары</h2></div>--%>

<%--&lt;%&ndash;@elvariable id="product" type="com.divanxan.internetshop.dto.Product"&ndash;%&gt;--%>
<%--<sf:form class="form-horizontal" modelAttribute="product"--%>
<%--action="${contextRoot}/manage/product"--%>
<%--method="POST"--%>
<%-->--%>

<%--<div class="form-group">--%>
<%--<label class="form-title1122" for="name">Введите имя товара</label>--%>
<%--<div class="col-md-8">--%>
<%--<sf:input type="text" path="name" id="name" placeholder="Название товара" class="form-field1122"/>--%>
<%--<br/>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="form-group">--%>
<%--<label class="form-title1122" for="brand">Введите название производителя</label>--%>
<%--<div class="col-md-8">--%>
<%--<sf:input type="text" path="brand" id="brand" placeholder="Название бренда" class="form-field1122"/>--%>
<%--<br/>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="form-group">--%>
<%--<label class="form-title1122" for="description">Введите описание товара</label>--%>
<%--<div class="col-md-8">--%>
<%--<sf:textarea path="description" id="description" rows="4" placeholder="Описание товара"--%>
<%--class="form-field1122"/>--%>
<%--<br/>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="form-group">--%>
<%--<div class="form-title1122" for="unitPrice">Введите цену товара</div>--%>
<%--<div class="col-md-8">--%>
<%--<sf:input type="number" path="unitPrice" id="unitPrice" placeholder="Цена" class="form-field1122"/>--%>
<%--<br/>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="form-group">--%>
<%--<div class="form-title1122" for="quantity">Введите количество товара</div>--%>
<%--<div class="col-md-8">--%>
<%--<sf:input type="number" path="quantity" id="quantity" placeholder="Количество"--%>
<%--class="form-field1122"/>--%>
<%--<br/>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="form-group">--%>
<%--<div class="form-title1122" for="categoryId">Выберите категорию</div>--%>
<%--<div class="col-md-8">--%>
<%--<sf:select class="form-field1122" id="categoryId" path="categoryId"--%>
<%--items="${categories}"--%>
<%--itemLabel="name"--%>
<%--itemValue="id"--%>
<%--/>--%>
<%--<sf:hidden path="id"/>--%>
<%--<sf:hidden path="code"/>--%>
<%--<sf:hidden path="supplierId"/>--%>
<%--<sf:hidden path="active"/>--%>
<%--<sf:hidden path="purchases"/>--%>
<%--<sf:hidden path="views"/>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="form-group">--%>
<%--<div class="submit-container1122">--%>

<%--<input type="submit" name="submit" value="Save" class="btn btn-primary"/>--%>

<%--</div>--%>
<%--</div>--%>

<%--</sf:form>--%>
<%--</form>--%>
<%--</div>--%>