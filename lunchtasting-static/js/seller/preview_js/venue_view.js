$(function() {
    /*星级评价*/
    var that = this;
    var jRateN=$("#jRateN").text();
    var jRateNS=parseFloat(jRateN)-parseInt(jRateN);
    if(jRateNS<0.5){
        jRateNS=0;
    }else if(jRateNS>=0.5){
        jRateNS=0.5
    };
    jRateN=parseInt(jRateN)+jRateNS;
    /*课程评价*/
    $("#jRate").jRate({
        startColor: "#f99526", //开始颜色
        endColor: "#f99526", //结束颜色
        rating: jRateN, //初始值
        strokeColor: 'black', //描边颜色
        width: 15, //宽度
        height: 15, //高度
        shape: 'STAR', //评分图案:STAR, RECTANGLE, SQUARE, CIRCLE, RHOMBUS, TRIANGLE。
        count: 5, //计数
        backgroundColor: '#ccc', //背景颜色
        shapeGap: '0px', //间隙
        min: 0, //最大最小值
        max: 5,
        precision: 0, //精确度
        readOnly: true, //是否只读
    });
    /*评论评价*/
    $(".jRatePer").jRate({
        startColor: "#f99526", //开始颜色
        endColor: "#f99526", //结束颜色
        rating: 4, //初始值
        strokeColor: 'black', //描边颜色
        width: 15, //宽度
        height: 15, //高度
        shape: 'STAR', //评分图案:STAR, RECTANGLE, SQUARE, CIRCLE, RHOMBUS, TRIANGLE。
        count: 5, //计数
        backgroundColor: '#ccc', //背景颜色
        shapeGap: '0px', //间隙
        min: 0, //最大最小值
        max: 5,
        precision: 0, //精确度
        readOnly: true, //是否只读
    });
    
    
    /*课程查看更多及评论*/
    var cut=0;
    var figL=$(".venue-course figure").length-2;
    $(".readMore").find('em').text(figL);
    $(".venue-course figure:gt(1)").hide();
    $(".readMore").on('click',function(){
        if(cut==0){
            $(".venue-course figure:gt(1)").show();
            $(".readMore").html("收起<i></i>");
            $(".readMore").find('i').addClass('moreDown').removeClass('moreUp');
            cut=1;
        }else if(cut==1){
            $(".venue-course figure:gt(1)").hide();
            $(".readMore").html("更多<em>"+figL+"</em>个课程<i></i>");
            $(".readMore").find('i').addClass('moreUp').removeClass('moreDown');
            cut=0;
        }
    });
    $(".venue-comment figure:gt(1)").hide();
    $(".venue-comment figure > figcaption > p > span").css('display','inline');
    /*评论数量*/
    var comL=$(".venue-comment >figure").length;
    $(".venue-comment > h3 > i").text(comL);
    
    
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
        lazyLoadingInPrevNext : true,
        lazyLoadingInPrevNextAmount : 4,
    });
    
    /*图片浏览初始化*/
    $('.swiper-container,.venue-comment').viewer({
        navbar: false,//显示缩略图导航
        title: false,//显示当前图片的标题（现实 alt 属性及图片尺寸）
        toolbar: false,//显示工具栏
        tooltip: false,//显示缩放百分比
        movable: true,//图片是否可移动
        zoomable: true,//图片是否可缩放
        rotatable: false,//图片是否可旋转
        scalable: false,//图片是否可翻转
        transition: false,//使用 CSS3 过度
        fullscreen: false,//播放时是否全屏
        minZoomRatio: 0.1,//最小缩放比例
        maxZoomRatio: 5//最大缩放比例
    });
});

    
