$(function() {
    //初始化
    $('.main').onepage_scroll({
        sectionContainer: 'section',
        easing: "ease",
        animationTime: 1000,
        //pagination: true,
        updateURL: false,
        beforeMove: function(index) {
            $('.main section').eq(index - 1).find('.item').addClass('fade-in-up');
            setTimeout(function() {
                $('.main section').eq(index - 1).find('.item').removeClass('fade-in-up');
            }, 3000);
        },
        afterMove: function(index) {},
        loop: false,
        keyboard: true,
        responsiveFallback: false,
        direction: "vertical"
    }); 
    $('#weixin, #apply, #download').click(function() {
        $('.nav, .main').addClass('vague');
        $('#' + $(this).attr('data-prefix') + '_blank').show();
        $('#' + $(this).attr('data-prefix') + '_main').addClass('show');
    });
    $('.tips-close, .tips-blank').click(function() {
        $('.nav, .main').removeClass('vague');
        $('#' + $(this).attr('data-prefix') + '_blank').hide();
        $('#' + $(this).attr('data-prefix') + '_main').removeClass('show');
    });
    $('#about').click(function() {
        $('.main').moveTo(5);
    });
    $('.nav-title').click(function() {
        $('.main').moveTo(1);
    });
    $(".main .page-c,.main .page-d,.main .page-e,.main .page-f").css('visibility','visible');
});
