$(function() {

    //    $(function() {
    //        /*导航跳转效果插件*/
    //        $('.nav').singlePageNav({
    //            offset: 70,
    //        });
    //        /*小屏幕导航点击关闭菜单*/
    //        $('.navbar-collapse a').click(function() {
    //            $('.navbar-collapse').collapse('hide');
    //        });
    //        new WOW().init();
    //    })

    /*初始化全屏滚动*/
    $(function() {
        $('#dowebok').fullpage({
            'navigation': true,
            navigationColor: "#29b6a8",
            menu: '#menu', //绑定菜单，设定的相关属性与 anchors 的值对应后，菜单可以控制滚动
            anchors: ['page1', 'page2', 'page6', 'page3', 'page4', 'page5'], //定义锚链接
            resize: true, //字体是否随着窗口缩放而缩放
            autoScrolling: true, //是否使用插件的滚动方式，如果选择 false，则会出现浏览器自带的滚动条
            keyboardScrolling: true, //是否使用键盘方向键导航
            onLeave: function(anchorLink, index) {
                if (index == 1) {
                    $(".selfNav").removeClass('navbar-fixed-top').addClass('navbar-fixed-bottom');
                } else {
                    $(".selfNav").removeClass('navbar-fixed-bottom').addClass('navbar-fixed-top');
                }
            }
        });
    });

    /*导航栏显示隐藏*/
    var $navtip = $("#fp-nav ui>li:first").children('a');
    /*滚轮滚动监听*/
    /*window.addEventListener('wheel',function(){
        //$("body").addClass('fp-viewing-page1');
        if ($("body").hasClass('fp-viewing-page1')) {
            $(".selfNav").show();
        }else{
            $(".selfNav").hide();
        }
    });*/
    /*单机滚动监听*/
    /*$("#fp-nav").addEventListener('onclick',function(){
        alert(111);
        //$("body").addClass('fp-viewing-page1');
        if ($("body").hasClass('fp-viewing-page1')) {
            $(".selfNav").show();
        }else{
            $(".selfNav").hide();
        }
    });*/
    /*二维码显示*/
    $(function() {
        $('#qrcode').mouseenter(function() {
            $(this).addClass('dow-weixin-after').removeClass('dow-weixin-none');
        }).mouseleave(function() {
            $(this).addClass('dow-weixin-none').removeClass('dow-weixin-after');
        });
    });
    /***相册***/
    $(function() {
        var showcase = $("#showcase");
        showcase.Cloud9Carousel({
            yOrigin: 42,
            yRadius: 80,
            //xOrigin: 1000,
            xRadius: 450,
            mirror: {
                gap: 12,
                height: 0.2,
                opacity: 0.5
            },
            itemClass: "card",
            autoPlay: 0,
            buttonLeft: $(".nav-show.left"),
            buttonRight: $(".nav-show.right"),
            bringToFront: true,
            onLoaded: function() {
                showcase.css('visibility', 'visible');
                showcase.css('display', 'none');
                showcase.fadeIn(1500);
            },
            onRendered: function() {
                $('#showcase .card').each(function() {
                    if ($(this).css('z-index') == 100) {
                        $(this).addClass('className').find('img').css({
                            'filter': 'brightness(1)',
                            '-webkit-filter': 'brightness(1)',
                            '-moz-filter': 'brightness(1)',
                            '-o-filter': 'brightness(1)'
                        }).end().siblings('.card').removeClass('className').find('img').css({
                            'filter': 'brightness(.5)',
                            '-webkit-filter': 'brightness(.5)',
                            '-moz-filter': 'brightness(.5)',
                            '-o-filter': 'brightness(.5)'
                        })
                    } else {

                    }
                })
            }
        });

        //
        // Simulate physical button click effect
        //
        $('.nav-show').click(function(e) {
            var b = $(e.target).addClass('down')
            setTimeout(function() {
                b.removeClass('down')
            }, 80);
        })

        $(document).keydown(function(e) {
            switch (e.keyCode) {
                /* left arrow */
                case 37:
                    $('.nav-show.left').click()
                    break

                    /* right arrow */
                case 39:
                    $('.nav-show.right').click()
            }
        })
    })

});