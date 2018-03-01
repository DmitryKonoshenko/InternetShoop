$(function () {
    switch (menu) {
        case 'О нас':
            $('#about').addClass('active');
            break;
        case 'Наши контакты':
            $('#contact').addClass('active');
            break;
        case 'Наши продукты':
            $('#listProduct').addClass('active');
            break;
        case 'Manage Products':
            $('#manageProduct').addClass('active');
            break;
        case 'User Cart':
            $('#userCart').addClass('active');
            break;
        default:
            if (menu === "На главную") break;
            $('#home').addClass('active');
            $('#a_' + menu).addClass('active');
            break
    }

// для работы с csrf токенами
    var token = $('meta[name="_csrf"]').attr('content');
    var header = $('meta[name="_csrf_header"]').attr('content');

    if (token.length > 0 && header.length > 0) {
        //назначить токени хедер для ajax запроса
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        })
    }


//code for jquery dataTable

//from page.jsp <table id="productListTable" class="table table-striped table-borderd">


    const $table = $('#productListTable');
// выполнить только в случае наличия этой таблицы
    if ($table.length) {
        // console.log('Inside the table!');

        var jsonUrl = '';
        if (window.categoryId === '') {
            jsonUrl = window.contextRoot + '/json/data/all/products';
        }
        else {
            jsonUrl = window.contextRoot + '/json/data/category/' + window.categoryId + '/products';
        }

        $table.DataTable({
            lengthMenu: [[3, 5, 10, -1],
                ['3', '5', '10', 'Все']],
            pageLength: 5,

            ajax: {
                url: jsonUrl,
                dataSrc: ''
            },
            columns: [
                {
                    data: 'code',
                    mRender: function (data, type, row) {
                        return '<img src="' + window.contextRoot + '/resources/images/' + data + '.jpg" class="dataTableImg"/>';
                    }
                },
                {
                    data: 'name'
                },
                {
                    data: 'brand'
                },
                {
                    data: 'unitPrice',
                    mRender: function (data, type, row) {
                        return '&#8381; ' + data
                    }
                },
                {
                    data: 'quantity',
                    mRender: function (data, type, row) {
                        if (data < 1) {
                            return '<span style="color:red">Отсутсвует на складе!</span>';
                        }
                        return data;
                    }
                },
                {
                    data: 'id',
                    bSortable: false,
                    mRender: function (data, type, row) {
                        var str = '';
                        str += '<a href="' + window.contextRoot + '/show/' + data + '/product" class="btn btn-primary"><span class="oi oi-eye"/></a>';

                        return str;
                    }
                },
                {
                    data: 'id',
                    bSortable: false,
                    mRender: function (data, type, row) {
                        var str = '';

                        if (row.quantity < 1) {
                            if (userRole == 'ADMIN') {
                                str += '<a href="' + window.contextRoot + '/manage/' + data + '/product" class="btn btn-warning"><span class="oi oi-wrench"></span></a>';
                            }
                            else {
                                str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="oi oi-cart"/></a>';
                                str += '<button type="button" onclick="buttonClickt()" id="b1" class="btn btn-primary"><span class="oi oi-heart"/></button>';
                            }
                        }
                        else {
                            // если пользователь админ - то он увидит кнопку настройки продукта
                            if (userRole == 'ADMIN') {
                                str += '<a href="' + window.contextRoot + '/manage/' + data + '/product" class="btn btn-warning"><span class="oi oi-wrench"></span></a>';
                            }
                            else {
                                str += '<a href="' + window.contextRoot + '/cart/add/' + data + '/product" class="btn btn-success"><span class="oi oi-cart"></span></a>';
                            }
                        }
                        return str;
                    }
                }
            ]

        });
    }

//убрать сообщение после 3-х секунд
    var $alert = $('.alert');

    if ($alert.lang) {
        setTimeout(function () {
            $alert.fadeOut('slow');
        }, 3000)
    }

    //-----------------------
    $('.switch input[type="checkbox"]').on('change', function () {

        var checkbox = $(this);
        var checked = checkbox.prop('checked');
        var dMsg = (checked) ? 'Вы хотите активировать товар?' :
            'Вы хотите деактивировать товар?';
        var value = checkbox.prop('value');
        bootbox.confirm({
            size: 'medium',
            title: 'Активация и деактивация продукта',
            message: dMsg,
            callback: function (confirmed) {
                if (confirmed) {
                    console.log(value);
                    bootbox.alert({
                        size: 'medium',
                        title: 'Информация',
                        message: 'Вы совершили операцию на товаре ' + value
                    });
                }
                else {
                    checkbox.prop('checked', !checked)
                }
            }
        });
    });

// --------------------
//  данные для админа
// --------------------

    const $adminProductsTable = $('#adminProductsTable');
// выполнить только в случае наличия этой таблицы
    if ($adminProductsTable.length) {

        var jsonUrl = window.contextRoot + '/json/data/admin/all/products';


        $adminProductsTable.DataTable({
            lengthMenu: [[10, 30, 50, -1],
                ['10', '30', '50', 'Все']],
            pageLength: 30,

            ajax: {
                url: jsonUrl,
                dataSrc: ''
            },
            columns: [
                {
                    data: 'id'
                },
                {
                    data: 'code',
                    mRender: function (data, type, row) {
                        return '<img src="' + window.contextRoot + '/resources/images/' + data + '.jpg" class="adminDataTableImg"/>';
                    }
                },
                {
                    data: 'name'
                },
                {
                    data: 'brand'
                },
                {
                    data: 'quantity',
                    mRender: function (data, type, row) {
                        if (data < 1) {
                            return '<span style="color:red">Отсутсвует на складе!</span>';
                        }
                        return data;
                    }
                },
                {
                    data: 'unitPrice',
                    mRender: function (data, type, row) {
                        return '&#8381; ' + data
                    }
                },
                {
                    data: 'active',
                    bSortable: false,
                    mRender: function (data, type, row) {

                        var str = '';

                        str += '<label class="switch">';
                        if (data) {
                            str += '<input type="checkbox" checked="checked" value="' + row.id + '"/>';
                        }
                        else {
                            str += '<input type="checkbox" value="' + row.id + '"/>';
                        }
                        str += '<div class="slider"></div></label>';

                        return str;
                    }
                },
                {
                    data: 'id',
                    bSortable: false,
                    mRender: function (data, type, row) {
                        var str = '';

                        str += '<a href="' + window.contextRoot + '/manage/' + data + '/product" class="btn btn-warning">';
                        str += '<span class="oi oi-wrench"></span></a>';

                        return str;

                    }
                }
            ],
            initComplete: function () {

                var api = this.api();
                api.$('.switch input[type="checkbox"]').on('change', function () {

                    var checkbox = $(this);
                    var checked = checkbox.prop('checked');
                    var dMsg = (checked) ? 'Вы хотите активировать товар?' :
                        'Вы хотите деактивировать товар?';
                    var value = checkbox.prop('value');
                    bootbox.confirm({
                        size: 'medium',
                        title: 'Активация и деактивация продукта',
                        message: dMsg,
                        callback: function (confirmed) {
                            if (confirmed) {
                                console.log(value);

                                var activationUrl = window.contextRoot + '/manage/product/' + value + '/activation';

                                $.post(activationUrl, function (data) {
                                    bootbox.alert({
                                        size: 'medium',
                                        title: 'Информация',
                                        message: data
                                    });

                                });
                            }
                            else {
                                checkbox.prop('checked', !checked)
                            }
                        }
                    });
                });

            }

        });
    }

//---------------------

    // jQuery Validation Code


    // validating the product form element


    var $categoryForm = $('#categoryForm');

    if ($categoryForm.length) {

        $categoryForm.validate({
                rules: {
                    name: {
                        required: true,
                        minlength: 3
                    },
                    description: {
                        required: true,
                        minlength: 3
                    }
                },
                messages: {
                    name: {
                        required: 'Please enter product name!',
                        minlength: 'Please enter atleast five characters'
                    },
                    description: {
                        required: 'Please enter product name!',
                        minlength: 'Please enter atleast five characters'
                    }
                },
                errorElement: "em",
                errorPlacement: function (error, element) {
                    errorPlacement(error, element);
                }
            }
        );

    }

    // валидация при вводе логина и ппароля----------------
    var $loginForm = $('#loginForm');

    if ($loginForm.length) {

        $loginForm.validate({
                rules: {
                    username: {
                        required: true,
                        email: true

                    },
                    password: {
                        required: true
                    }
                },
                messages: {
                    username: {
                        required: 'Пожалуйста, введите имя пользователя!',
                        email: 'Пожалуйста введите правильный адрес электронной почты!'
                    },
                    password: {
                        required: 'Пожалуйста, введите пароль!'
                    }
                },
                errorElement: "em",
                errorPlacement: function (error, element) {
                    // Add the 'help-block' class to the error element
                    error.addClass("help-block");

                    // add the error label after the input element
                    error.insertAfter(element);
                }
            }
        );

    }

    // обработка нажатия кнопки обновить в корзине------------------
    $('button[name="refreshCart"]').click(function () {
        //получим id строки корзины
        var cartLineId = $(this).attr('value');
        var countElement = $('#count_'+cartLineId);
        var originalCount = countElement.attr('value');
        var currentCount = countElement.val();

        // работает только когда занчение поменялось
        if(currentCount !== originalCount){
          /*  console.log("current count: " + currentCount);
           console.log("current count: " + originalCount); */
            if(currentCount < 1 || currentCount > 5) {

                countElement.val(originalCount);
                bootbox.alert({
                    message: 'Количество должно быть не меньше 1 и не более 5!',
                    size: 'medium',
                    title: 'Ошибка'
                });
            }
            else {
                // use the window.location.href property to send the request to the server
                var updateUrl = window.contextRoot + '/cart/' + cartLineId + '/update?count=' + currentCount;
                // передадим значение в контроллер
                window.location.href = updateUrl;
            }

        }

    });



    //------------------

});

// обработка нажатия кнопки обновить поставить лайкос отсутствующему товару------------------
$('button[name="likeProduct"]').click(function () {
    var productId = $(this).attr('value');
    document.getElementById('b1').disabled = true;

});
function buttonClickt(row) {

    document.getElementById('b1').disabled = true;
}


//code for footer
$(document).ready(function () {

    var docHeight = $(window).height();
    var footerHeight = $('.footer').height();
    var contentHeight = $('.content').height();
    var footerTop = $('.footer').position().top + footerHeight;

    if (footerTop < docHeight + 40) {
        $('.content').css('min-height', (docHeight - contentHeight + 600) + 'px');
    }
});




