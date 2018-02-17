<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="container">
    <div class="row">

        <!-- Display sidebar-->
        <!--TODO Разобраться почему не получается сделать инклюд-->
        <div class="col-lg-3">
            <h1 class="my-4">Электроник</h1>
            <div class="list-group">
                <c:forEach items="${categories}" var="category">
                    <a href="${contextRoot}/show/category/${category.id}/products" class="list-group-item" id="a_${category.name}">${category.name}</a>
                </c:forEach>
            </div>
        </div>
        
        <!-- Display actual products-->
        <div class="col-md-9">
            <div class="row">
                <div class="col-lg-12">
                    <%--<jsp:useBean id="userClickAllProducts" scope="request" type="java.lang.Boolean"/>--%>

                    <c:if test="${userClickAllProducts == true}">
                    <ol class="breadcrumb">

                        <li><a href="${contextRoot}/home">На главную </a></li>
                        <li class="active"><a>/ Наши продукты </a></li>

                    </ol>
                    </c:if>

                    <%--<jsp:useBean id="userClickCategoryProducts" scope="request" type="java.lang.Boolean"/>--%>

                    <c:if test="${userClickCategoryProducts == true}">
                        <jsp:useBean id="category" scope="request" type="com.divanxan.internetshop.dto.Category"/>

                        <ol class="breadcrumb">

                            <li><a href="${contextRoot}/home">На главную </a></li>

                            <li class="active"><a>/ Категория: </a></li>

                            <li class="active"> ${category.name}</li>

                        </ol>
                    </c:if>
                </div>
            </div>
        </div>


    </div>
</div>