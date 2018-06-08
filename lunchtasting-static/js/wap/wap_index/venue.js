$(function () {
    /*课程查看更多及评论*/
    var cut = 0;
    var figL = $(".venue-course figure").length - 2;
    $(".readMore").find('em').text(figL);
    $(".venue-course figure:gt(1)").hide();
    $(".readMore").on('click', function () {
        if (cut == 0) {
            $(".venue-course figure:gt(1)").show();
            $(".readMore").html("收起<i></i>");
            $(".readMore").find('i').addClass('moreDown').removeClass('moreUp');
            cut = 1;
        } else if (cut == 1) {
            $(".venue-course figure:gt(1)").hide();
            $(".readMore").html("更多<em>" + figL + "</em>个课程<i></i>");
            $(".readMore").find('i').addClass('moreUp').removeClass('moreDown');
            cut = 0;
        }
    });
    $(".venue-comment figure:gt(1)").hide();
    $(".venue-comment figure > figcaption > p > span").css('display', 'inline');
    /*评论数量*/
    /*var comL=$(".venue-comment >figure").length;
    $(".venue-comment > h3 > i").text(comL);*/

    /*不支持退款显示同步*/
    /* if($(".body-details > p[lang='p']").empty()){
         console.log('empty');
     }else{
         console.log("noEmpty");
     }*/
    /*最下面*/
    /*Initialize Swiper*/
    var swiper = new Swiper('.swiper-container', {
        scrollbar: '.swiper-scrollbar',
        scrollbarHide: true,
        slidesPerView: 'auto',
        spaceBetween: 10,
        grabCursor: true,
        // Disable preloading of all images
        preloadImages: false,
        // Enable lazy loading
        lazyLoading: true,
        lazyLoadingInPrevNext: true,
        lazyLoadingInPrevNextAmount: 4,
    });
    /*图片浏览初始化*/
    $(function() {
        var viewer = ImageViewer();
        $('.gallery-items').click(function() {
            var imgSrc = this.src,
            highResolutionImage = $(this).data('high-res-img');
            viewer.show(imgSrc, highResolutionImage);
        });
    });
    /*$('.swiper-container').viewer({
        navbar: false,//显示缩略图导航
        title: false,//显示当前图片的标题（现实 alt 属性及图片尺寸）
        toolbar: false,//显示工具栏
        tooltip: false,//显示缩放百分比
        movable: true,//图片是否可移动
        zoomable: true,//图片是否可缩放
        rotatable: false,//图片是否可旋转
        scalable: false,//图片是否可翻转
        transition: false,//使用 CSS3 过度
        fullscreen: true,//播放时是否全屏
        minZoomRatio: 0.1,//最小缩放比例
        maxZoomRatio: 5//最大缩放比例
    });*/
    /*星级评价*/
    $(".courseR").starRating({
        starSize: 18, //星星的尺寸，单位像素
        readOnly: true //是否为只读状态
    });
    $(".venueR").starRating({
        starSize: 15, //星星的尺寸，单位像素
        readOnly: true //是否为只读状态
    });
});
