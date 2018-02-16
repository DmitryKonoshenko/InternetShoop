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
        default:
            $('#home').addClass('active');
            $('#a_'+menu).addClass('active');
            break
    }
});

$(document).ready(function () {

    var docHeight = $(window).height();
    var footerHeight = $('.footer').height();
    var contentHeight = $('.content').height();
    var footerTop = $('.footer').position().top + footerHeight;

    if (footerTop < docHeight) {
        $('.content').css('min-height', (docHeight - contentHeight + 515) + 'px');
    }
});