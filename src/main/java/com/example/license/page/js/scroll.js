
//pagetopのボタンを出したり消したりするスクリプト
$(function(){
    var scroll = $('.scroll');
    var scrollShow = $('.scroll-show');
    $(scroll).hide();
    $(window).scroll(function(){
        if($(this).scrollTop() >= 300) {
            $(scroll).fadeIn(500).addClass(scrollShow);
        } else {
            $(scroll).fadeOut(500).removeClass(scrollShow);
        }
    });
});

//スムーススクロールのスクリプト
$(function(){
    $('a[href^="#"]').click(function(){
        var href = $(this).attr('href');
        var target = href == '#' ? 0 : $(href).offset().top;
        $('body,html').animate({scrollTop:target},500);
        return false;
    });
});

$(function () {
    // ハンバーガーメニューのクリックイベント
    $('.toggle_btn').on('click', function () {
        if ($('#header').hasClass('open')) {
            $('#header').removeClass('open');
        } else {
            $('#header').addClass('open');
        }
    });

    // #maskのエリアをクリックした時にメニューを閉じる
    $('#mask').on('click', function () {
        $('#header').removeClass('open');
    });

    // リンクをクリックした時にメニューを閉じる
    $('#navi a').on('click', function () {
        $('#header').removeClass('open');
    });
});
