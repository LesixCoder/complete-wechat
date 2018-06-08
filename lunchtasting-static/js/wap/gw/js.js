/**
 * Created by dodo on 2015/8/26.
 */

var index = {
    resizeId: 0,

    init: function() {
        var self = this;
        self.resizeHandler();
        $(window).resize(self.resizeHandler);
    },
    resizeHandler: function() {
        var self = this;
        clearTimeout(self.resizeId);
        self.resizeId = setTimeout(function() {
            var windowWidth = $(window).width();
            var windowHeight = $(window).height();
            var flag = location.href.indexOf('extension') > -1;
            var defHeight = (flag ? 797 : 670);
            windowHeight = parseFloat(windowHeight) > defHeight ? windowHeight : defHeight;

            $('.secondPage').css('height', windowHeight + 'px');

        }, 350);
    }
}
index.init();

/**
 * Added by LiJuanjuan
 * Removd in this file from index2.html by SunYanhui
 * 待优化...
 */
var index2 = {
    init: function() {
        var self = this;
        self.t1 = new TimelineLite();
        self.t2 = new TimelineLite();
        self.t3 = new TimelineLite();
        self.activeIndex = 0;

        var t1Interval = 0.7,
            t2Interval = 0.7,
            t3Interval = 0.7;

        self.t1.from('#firstPageArea', t1Interval, { left: 220, opacity: 0 }, 't1');
        self.t2.from('#secondPageArea', t2Interval, { left: 220, opacity: 0 }, 't2');
        self.t3.from('#thirdPageArea', t2Interval, { left: 220, opacity: 0 }, 't3');

        self.t1.play();
        self.t2.pause();
        self.t3.pause();

        var fireEvent = true;
        var newDelta, oldDelta, eventTimeout;
        newDelta = oldDelta = eventTimeout = null;

        $('body').on('mousewheel', function(event, delta) {
            if (!fireEvent) return;
            newDelta = delta;
            if (oldDelta !== null && oldDelta * newDelta > 0) {
                fireEvent = false;
                clearTimeout(eventTimeout);
                eventTimeout = setTimeout(function() {
                    fireEvent = true;
                    if (delta > 0) {
                        if (self.activeIndex == 0) return;
                        self.activeIndex--;
                    } else {
                        if (self.activeIndex == 3) return;
                        self.activeIndex++;
                        self.activeIndex = Math.min(self.activeIndex, 3);
                    }
                    self.moveTo(self.activeIndex);
                }, 300);
            };
            oldDelta = newDelta;
        });

        $('#dots').bind('click', function(event) {
            var target = event.target,
                index = Number($(target).attr('index'));
            if (index) {
                self.activeIndex = index - 1;
                self.moveTo(index - 1);
            }

        });

        $('#retureTop').bind('click', function() {
            $('#fullpage').css({ 'top': 0 });
            $('#dot1')
                .addClass('current')
                .siblings()
                .removeClass('current');
            self.activeIndex = 0;
            $(this).prop('hidden', true);
        });
        self.resize();
        $(window).resize(self.resize.bind(self));

    },
    moveTo: function(index) {
        var self = this;
        var tIndex = index + 1;
        var timeline = self['t' + tIndex];
        var winHeight = $(window).height();

        if (timeline) {
            timeline.seek(0);
            timeline.pause();
        }

        self.activeIndex = index;
        $('#dot' + (index + 1))
            .addClass('current')
            .siblings()
            .removeClass('current');

        var moveTopLen = winHeight * index;
        if (self.activeIndex === 3) {
            moveTopLen = winHeight * 2 + 119;
        }

        $('#fullpage').animate({ top: '-' + moveTopLen }, 'slow', function(event) {
            if (tIndex !== 1) {
                if ($('#retureTop').prop('hidden')) {
                    $('#retureTop').prop('hidden', false);
                }
            } else {
                $('#retureTop').prop('hidden', true);
            }

            if (timeline) {
                timeline.play();
            }
        });
    },
    resize: function() {
        var winHeight = $(window).height(),
            self = this;
        $('#page1, #page2, #page3').height(winHeight);
        $('#fullpage').css({ top: -winHeight * self.activeIndex });
    }

};

$('#buy').click(function() {
    index2.moveTo(0);
});

index2.init();

/*实时监听宽度*/
jQuery(function(){
    window.addEventListener('resize', function(e){
        var dmW=$(".group-dm").width();
        $(".group-dm").height(dmW);
    }, false);
    var dmW=$(".group-dm").width();
    $(".group-dm").height(dmW);
});
