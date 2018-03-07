<%@ page contentType="text/html; UTF-8"
         pageEncoding="UTF-8"%>
<spring:url var="images" value="/resources/images"/>
<div class="container">

    <div class="row">

        <div class="col-lg-3">
            <h1 class="my-4">Электроник</h1>
           <%@include file="./shared/sidebar.jsp"%>
        </div>
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">

            <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner" role="listbox">
                    <div class="carousel-item active">
                        <a href="${contextRoot}/show/category/1/products"><img class="slide-image" src="${images}/banner1.jpg"
                                                                               alt=""></a>
                    </div>
                    <div class="carousel-item">
                        <a href="${contextRoot}/show/category/2/products"><img class="slide-image" src="${images}/banner2.jpg"
                             alt=""></a>
                    </div>
                    <div class="carousel-item">
                        <a href="${contextRoot}/show/category/3/products"><img class="slide-image" src="${images}/banner3.jpg"
                             alt=""></a>
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>

            <div class="row">

                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card h-100">
                        <a href="${contextRoot}/show/2/product"><img class="card-img-top" src="${images}/PRDQWE123TVS.jpg" alt=""></a>
                        <div class="card-body">
                            <h4 class="card-title">
                                <a href="${contextRoot}/show/2/product">32LJ519U</a>
                            </h4>
                            <h5>&#8381;15190.0</h5>
                            <p class="card-text">LG 32LJ519U - это отличный HD-телевизор для всей семьи, который успешно совместит в себе все функции, присущие полноценному развлекательному медиацентру!</p>
                        </div>
                        <div class="card-footer">
                            <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
                        </div>
                    </div>
                </div>

                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card h-100">
                        <a href="${contextRoot}/show/4/product"><img class="card-img-top" src="${images}/PRDABC123PIL.jpg" alt=""></a>
                        <div class="card-body">
                            <h4 class="card-title">
                                <a href="${contextRoot}/show/4/product">FC 8760</a>
                            </h4>
                            <h5>&#8381;10070.0</h5>
                            <p class="card-text">Благодаря технологии PowerCyclone новый пылесос Philips помогает делать уборку безо всяких усилий!</p>
                        </div>
                        <div class="card-footer">
                            <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
                        </div>
                    </div>
                </div>

                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card h-100">
                        <a href="${contextRoot}/show/7/product"><img class="card-img-top" src="${images}/PRDABC123FEN.jpg" alt=""></a>
                        <div class="card-body">
                            <h4 class="card-title">
                                <a href="${contextRoot}/show/7/product">D3010</a>
                            </h4>
                            <h5>&#8381;1700.0</h5>
                            <p class="card-text">Отличный и надежный фен!</p>
                        </div>
                        <div class="card-footer">
                            <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
                        </div>
                    </div>
                </div>

            </div>
            <!-- /.row -->

        </div>
        <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

</div>