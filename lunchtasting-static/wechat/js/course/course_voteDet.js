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

    //投票说明
    jQuery('.header-explain').on('click', function () {
        //jQuery('.explainBox').fadeToggle('100');
        jQuery('.explainBox').show();
        jQuery('.filter').show();
        jQuery(document.body).css('overflow-y', 'hidden');
    });
    jQuery('.eClose,.filter').on('click', function () {
        jQuery('.explainBox').fadeToggle('50', function () {
            jQuery('.filter').hide();
            jQuery(document.body).css('overflow-y', 'auto');
        });
    })

    var vote = {
        init: function () {
            var _this = this;
            _this.bind();
            _this.render();
            _this.methods = _this.methods();
            _this.async = _this.async();
            _this.voteCut = true;
        },
        bind: function () {
            var _this = this;
            _this.voteBtn = $('.voteBtn');
        },
        render: function () {
            var _this = this;
            window.addEventListener("load", function () {
                _this.voteBtn.trigger("render");
            });
            _this.voteBtn.on({
                render: function (e) {
                    e.stopPropagation();
                    me = this;
                    _this.vote_parent1 = jQuery(this).parents('.figure-l-fig');
                    _this.vote_count1 = _this.vote_parent1.find('.vote-count').html();
                    _this.vote_status1 = jQuery(this).attr('js-status');
                    $.proxy(_this.methods.voteCompute(_this.vote_count1, me));
                },
                touchend: function (e) {
                    e.stopPropagation();
                    me = this;
                    _this.vote_parent1 = jQuery(this).parents('.figure-l-fig');
                    _this.vote_count1 = _this.vote_parent1.find('.vote-count').html();
                    _this.vote_status1 = jQuery(this).attr('js-status');
                    _this.vote_uid = jQuery(this).attr('js-uid');
                    //$.proxy(_this.methods.voteCompute());
                    //console.log(_this.vote_status1);
                    if (_this.vote_status1 == 0) $.proxy(_this.async.voteFn(me));
                }
            });
        },
        methods: function () {
            var _this = this;
            return {
                render: function () {

                },
                voteCompute: function (ele, me) {
                    countArr = ele.split('');
                    if (countArr.length == 1) {
                        _this.vote_parent1.find('.stone-big-num').html(0);
                        _this.vote_parent1.find('.stone-mid-num').html(0);
                        _this.vote_parent1.find('.stone-small-num').html(countArr[0]);
                    } else if (countArr.length == 2) {
                        // ele.parents('.figure-l-fig').find('.stone-big-num').html(0);
                        // ele.parents('.figure-l-fig').find('.stone-big-num').html(countArr[0]);
                        // ele.parents('.figure-l-fig').find('.stone-big-num').html(countArr[1]);
                        _this.vote_parent1.find('.stone-big-num').html(0);
                        _this.vote_parent1.find('.stone-mid-num').html(countArr[0]);
                        _this.vote_parent1.find('.stone-small-num').html(countArr[1]);
                    } else if (countArr.length == 3) {
                        // ele.parents('.figure-l-fig').find('.stone-big-num').html(countArr[0]);
                        // ele.parents('.figure-l-fig').find('.stone-big-num').html(countArr[1]);
                        // ele.parents('.figure-l-fig').find('.stone-big-num').html(countArr[2]);
                        _this.vote_parent1.find('.stone-big-num').html(countArr[0]);
                        _this.vote_parent1.find('.stone-mid-num').html(countArr[1]);
                        _this.vote_parent1.find('.stone-small-num').html(countArr[2]);
                    } else if (countArr.length == 4) {
                        // ele.parents('.figure-l-fig').find('.stone-big-num').html(countArr[0]);
                        // ele.parents('.figure-l-fig').find('.stone-big-num').html(countArr[1]);
                        // ele.parents('.figure-l-fig').find('.stone-big-num').html(countArr[2]);
                        _this.vote_parent1.find('.stone-big-num').html(countArr[0].toString() + countArr[1].toString());
                        _this.vote_parent1.find('.stone-mid-num').html(countArr[2]);
                        _this.vote_parent1.find('.stone-small-num').html(countArr[3]);
                    } else {
                        return
                    }
                    if (_this.vote_status1 == 1) jQuery(me).addClass('vote-end').html('<i>已投</i>');
                    else if (_this.vote_status1 == 0) jQuery(me).removeClass('vote-end').html('<i>投票</i>');
                }
            }
        },
        async: function () {
            var _this = this;
            return {
                voteFn: function (me) {
                    var data = {
                        'order_id': _this.vote_uid
                    }
                    console.log(data)
                    jQuery.post("/course/vote/add", data, function (msg) {
                        var code = msg['code'];
                        var err = msg['message'];
                        if (code == 100) {
                            var parent = jQuery('.voteBtn').parents('.figure-l-fig');
                            var count = parseInt(parent.find('.vote-count').html());
                            count += 1;
                            parent.find('.vote-count').html(count);
                            jQuery('.voteBtn').attr('js-status', '1');
                            jQuery('.voteBtn').addClass('vote-end').html('<i>已投</i>');
                            jQuery('.voteBtn').trigger("render");
                        } else {
                            console.log(err);
                        }
                    }).fail(function () {
                        //alert('投票失败');
                    })
                }
            }
        }
    }
    vote.init();
});