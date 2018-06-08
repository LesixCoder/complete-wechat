jQuery(function () {
    function view() {
        return {
            w: document.documentElement.clientWidth,
            h: document.documentElement.clientHeight
        };
    }

    function heightAuto() {
        let wrapHeight = document.querySelector('.wrapBox').clientHeight;
        let domHeight = document.documentElement.clientHeight;
        if (wrapHeight < domHeight) {
            jQuery('.wrapBox-phone').height(view().h + "px");
        } else {
            jQuery('.wrapBox-phone').css('height', 'auto');
        }
    }
    heightAuto();

    var convert = {
        init: function () {
            var _this = this;
            _this.bind();
            _this.render();
            _this.methods = _this.methods();
            _this.async = _this.async();
        },
        bind: function () {
            var _this = this;
            _this.convertBtn = $('#convert_btn');
        },
        render: function () {
            var _this = this;
            window.addEventListener("load", function () {
               // _this.convertBtn.trigger("render");
            });
            _this.convertBtn.on({
                touchend: function (e) {
                    e.stopPropagation();
                    _this.gift_id = jQuery(this).siblings('#giftCouponId').val();
                    _this.gift_status = jQuery(this).attr('js-convert');
                    if (_this.gift_status == 1) $.proxy(_this.async.convertFn());
                }
            });
        },
        methods: function () {
            var _this = this;
            return {
                
            }
        },
        async: function () {
            var _this = this;
            return {
                convertFn: function () {
                    var data = {
                        'id': _this.gift_id
                    }
                    jQuery.post("/gift_coupon/doExchange", data, function (msg) {
                        var code = msg['code'];
                        var err = msg['message'];
                        if (code == 100) {
                            jQuery('#convert_btn').removeClass('bgcolor-brown').addClass('bgcolor-ccc').html('已兑换');
                            jQuery('#convert_btn').attr('js-convert', '0');
                            window.location.href = "/gift_coupon/exchange/list";
                        } else {
                            alert("礼品劵已兑换完了哦，下次早点来！");
                        }
                    }).fail(function () {
                        
                    })
                }
            }
        }
    }
    convert.init();
});