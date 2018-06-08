/*标签颜色随机获取*/
jQuery(function(){
    var myArr=['#eaa435','#6ba86f','#82c9c2','#a874b8'];
    jQuery(".content figure").each(function(){
        var colorBg=myArr[GetRandomNum(0,4)];
        var labelBox=jQuery(this).find("span[lang='label']").css('backgroundColor',colorBg);
        var colorCur=jQuery(this).find("span[lang='label']").css('backgroundColor');
        jQuery(this).find(".label i").css({borderColor: 'transparent '+colorCur+' transparent transparent'});
        console.log(colorCur);
    })
    
    function GetRandomNum(Min,Max){   
        var Range = Max - Min;   
        var Rand = Math.random();   
        return(Min + Math.round(Rand * Range));   
    }
});

/*下载弹出框*/
/*jQuery(function(){
    jQuery("#openApp").click(function(){
        jQuery(".downloadCon").show().addClass("animated fadeInLeft").removeClass("fadeOutRightBig");
        jQuery(".filter").show();
    });
    jQuery(".filter").click(function(){
        jQuery(".downloadCon").addClass("animated fadeOutRightBig",function(){
            jQuery(".downloadCon,.filter").hide();
        }).removeClass("fadeInLeft");
    });
});*/

/*调用app下载*/
/*(function () {
    if (navigator.userAgent.match(/(iPhone|iPod|iPad);?/i)) {
        var loadDateTime = new Date();
        window.setTimeout(function() {
            var timeOutDateTime = new Date();
            if (timeOutDateTime - loadDateTime < 5000) {
                window.location = "要跳转的页面URL";
            } else {
                window.close();
            }
        },25);
    window.location = " apps custom url schemes ";
    }else if (navigator.userAgent.match(/android/i)) {
        var state = null;
        try {
          state = window.open("apps custom url schemes ", '_blank');
        } catch(e) {}
        if (state) {
          window.close();
        } else {
          window.location = "要跳转的页面URL";
        }
    }
});*/

/*打开下载链接*/
(function () {
    $(function(){
        $("#iosLoad").click(function(){ //<a href="goto://"></a> 通过Scheme打开app应用
            var the_href=$("#iosLoad").attr("href");//获得下载链接
            if(ua.match(/MicroMessenger/i)=="micromessenger") { //是否微信打开
                $(".box-bg").show();//微信打开出浮层，微信暂不支持Scheme打开非企鹅应用
            }else { 
                window.location.href=the_href; //打开某手机上的某个app应用
                setTimeout(function(){
                    window.location.href="https://itunes.apple.com/us/app/id.......";//如果超时就跳转到app下载页
                },500);
            }
        });
    });
});