<%--@elvariable id="userModel" type="com.divanxan.internetshop.model.UserModel"--%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="${contextRoot}/home">Магазин электроники Электроник</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li id="nav-item active">
                    <a class="nav-link" href="${contextRoot}/home">На главную</a>
                </li>
                <li id="about">
                    <a class="nav-link" href="${contextRoot}/about">О магазине</a>
                </li>
                <li id="listProduct">
                    <a class="nav-link" href="${contextRoot}/show/all/products">Продукты</a>
                </li>
                <li id="contact">
                    <a class="nav-link" href="${contextRoot}/contact">Контакты</a>
                </li>
                <security:authorize access="hasAuthority('ADMIN')">
                    <li id="manageProduct">
                        <a class="nav-link" href="${contextRoot}/manage/product">Редактировать</a>
                    </li>
                    <li id="manageProduct">
                        <a class="nav-link" href="${contextRoot}/manage/orders">Заказы</a>
                    </li>
                </security:authorize>
                <security:authorize access="isAnonymous()">
                    <li id="register">
                        <a class="nav-link" href="${contextRoot}/register">Регистрация</a>
                    </li>
                    <li id="login">
                        <a class="nav-link" href="${contextRoot}/login">Войти</a>
                    </li>
                </security:authorize>

                <security:authorize access="isAuthenticated()">
                    <li class="dropdown" id="userCart">
                        <a class="nav-link  dropdown-toggle"
                           href="javascript:void(0)"
                           id="dropdownMenu1"
                           data-toggle="dropdown">
                                ${userModel.fullName}
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <security:authorize access="hasAuthority('USER')">
                                <li>
                                    <a href="${contextRoot}/cart/show">
                                    <span class="oi oi-cart">
                                        <span class="badge">${userModel.cart.cartLines}</span>
                                        - &#8381; ${userModel.cart.grandTotal}
                                    </a>
                                </li>
                                <li role="separator" class="dropdown-divider"></li>
                                <li>
                                    <a href="${contextRoot}/user/show">
                                        Личный кабинет
                                    </a>
                                </li>
                                <li role="separator" class="dropdown-divider"></li>
                            </security:authorize>
                            <li>
                                    <%-- Изменил /logout на /perform-logout из-за CSRF--%>
                                <a href="${contextRoot}/perform-logout">Выйти</a>
                            </li>
                        </ul>
                    </li>
                </security:authorize>
            </ul>
        </div>
    </div>
</nav>
<%-- Данно еполе нам необходимо для добавления нужных кнопок для редактирования продуктов --%>
<script>
    window.userRole = '${userModel.role}'
</script>