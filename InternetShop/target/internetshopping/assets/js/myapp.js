$(document).ready(function() {

    var docHeight = $(window).height();
    var footerHeight = $('.footer').height();
    var contentHeight = $('.content').height();
    var footerTop = $('.footer').position().top + footerHeight;

    if (footerTop < docHeight) {
        $('.content').css('min-height', (docHeight - contentHeight + 97) + 'px');
    }
});