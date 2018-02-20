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
        default:
            if (menu === "На главную") break;
            $('#home').addClass('active');
            $('#a_' + menu).addClass('active');
            break
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
                            str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="oi oi-cart"/></a>';
                            str += '<button type="button" onclick="buttonClickt()" id="b1" class="btn btn-primary"><span class="oi oi-heart"/></button>';

                        }
                        else {
                            str += '<a href="' + window.contextRoot + '/cart/add/' + data + '/product" class="btn btn-success"><span class="oi oi-cart"></span></a>';
                        }
                        return str;
                    }
                }
            ]

        });
    }

//убрать сообщение после 3-х секунд
    var $alert = $('.alert');

    if($alert.lang){
        setTimeout( function () {
            $alert.fadeOut('slow');
        }, 3000)
    }

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




