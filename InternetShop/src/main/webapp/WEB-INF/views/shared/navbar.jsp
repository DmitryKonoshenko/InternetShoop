<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8"%>
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
                    <a class="nav-link" href="${contextRoot}/show/all/products">Наши продукты</a>
                </li>
                <li id="contact">
                    <a class="nav-link" href="${contextRoot}/contact">Наши контакты</a>
                </li>
                <li id="manageProduct">
                    <a class="nav-link" href="${contextRoot}/manage/product">Редактировать</a>
                </li>
            </ul>
        </div>
    </div>
</nav>