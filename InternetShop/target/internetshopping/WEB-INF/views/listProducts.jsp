<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<div class="container">
    <div class="row">

        <div class="col-lg-3">
            <h1 class="my-4">Электроник</h1>
            <%--<%@include file="./shared/sidebar.jsp"%>--%>
            <jsp:include page="./shared/sidebar.jsp"/>
        </div>

        <!-- Display actual products-->
        <div class="col-md-9">
            <div class="row">
                <div class="col-lg-12">
                    <%--<jsp:useBean id="userClickAllProducts" scope="request" type="java.lang.Boolean"/>--%>

                    <c:if test="${userClickAllProducts == true}">

                        <script>
                            window.categoryId = '';
                        </script>

                        <ol class="breadcrumb">

                            <li><a href="${contextRoot}/home">На главную </a></li>
                            <li class="active"><a>-> Наши продукты </a></li>

                        </ol>
                    </c:if>

                    <%--<jsp:useBean id="userClickCategoryProducts" scope="request" type="java.lang.Boolean"/>--%>

                    <c:if test="${userClickCategoryProducts == true}">
                        <script>
                            window.categoryId = '${category.id}';
                        </script>


                        <jsp:useBean id="category" scope="request" type="com.divanxan.internetshop.dto.Category"/>
                        <ol class="breadcrumb">

                            <li><a href="${contextRoot}/home">На главную </a></li>
                            <li class="active"><a>-> Категория: </a></li>
                            <li class="active">${category.name}</li>

                        </ol>
                    </c:if>


                </div>


            </div>

            <div class="row">

                <div class="col-xs-12">


                    <table id="productListTable" class="table table-striped table-border">

                        <thead>

                        <tr>

                            <th></th>
                            <th>Название</th>
                            <th>Производитель</th>
                            <th>Цена</th>
                            <th>Количество</th>
                            <th></th>
                            <th></th>

                        </tr>

                        </thead>

                        <tfoot>

                        <tr>

                            <th></th>
                            <th>Название</th>
                            <th>Производитель</th>
                            <th>Цена</th>
                            <th>Количество</th>
                            <th></th>
                            <th></th>

                        </tr>

                        </tfoot>
                    </table>

                </div>
            </div>


        </div>


    </div>


</div>