/*二维码*/
$(function(){
    $(".qrCode").mouseenter(function(){
        $(".qrCodeBox").fadeIn(500);
    }).mouseleave(function(){
        $(".qrCodeBox").fadeOut(300);
    });
});

/*监听页面变化动态高度*/
$(function(){
    window.addEventListener('resize', function(e){
        var winH=$(window).height();
        $("#contain").height(winH);
    }, false);
    var winH=$(window).height();
    $("#contain").height(winH);
});

/*滚动监听导航栏变色*/
/*$(window).scroll(function () {
    if($(document).scrollTop()>20){
        $("#mynav").css('background','rgba(0,0,0,.8)');
    }else{
        $("#mynav").css('background','rgba(0,0,0,1)');
    }
});*/

 