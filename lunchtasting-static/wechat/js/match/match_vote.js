jQuery(function () {
    //    //列表切换
    //    function voteList() {
    //        jQuery('.box-btnG a').on('click', function() {
    //            jQuery(this).addClass('active').siblings('a').removeClass('active');
    //            if (jQuery(this).hasClass('btnG1')) {
    //                jQuery('.list1').removeClass('none');
    //                jQuery('.list2').addClass('none');
    //            } else if (jQuery(this).hasClass('btnG2')) {
    //                jQuery('.list1').addClass('none');
    //                jQuery('.list2').removeClass('none');
    //            }
    //        })
    //    }
    //    voteList();
    function view() {
		return {
			w: document.documentElement.clientWidth,
			h: document.documentElement.clientHeight
		};
	}
	function heightAuto(){
		let wrapHeight = document.querySelector('.wrapBox').clientHeight;
		let domHeight = document.documentElement.clientHeight;
		if(wrapHeight < domHeight){
			jQuery('.wrapBox-phone').height(view().h+"px");
		}else{
			jQuery('.wrapBox-phone').css('height','auto');
		}
	}
	heightAuto();

    //投票说明
    jQuery('.header-explain').on('click', function () {
        //jQuery('.explainBox').fadeToggle('100');
        jQuery('.explainBox').show();
        jQuery('.filter').show();
        jQuery(document.body).css('overflow-y','hidden');
    });
    jQuery('.eClose,.filter').on('click', function () {
        jQuery('.explainBox').fadeToggle('50', function () {
            jQuery('.filter').hide();
            jQuery(document.body).css('overflow-y','auto');
        });
    })

    //投票
    // jQuery('.voteBtn').on('click', function() { //点击投票
    // 	var orderId = $(this).attr("js-attr-orderId");
    // 	var type = $(this).attr("js-attr-type");
    //     if(jQuery(this).hasClass('active'))return;
    //     $.post("/match/order/vote/do", {
    //         order_id: orderId
    //     }, function (result) {
    //         var data = JSON.parse(result);
    //         if (data.code == 100) {
    //         	//修改投票样式  type 1 列表页面   2详情页面
    //         	if(type == 1){
    //                 jQuery('#'+orderId).addClass('active').text('已投');
    //                 var num = parseInt(jQuery('#'+orderId).siblings('span').text());
    //                 num = num+1;
    //                 jQuery('#'+orderId).siblings('span').text(num);
    //         	}else{
    //                 jQuery('.voteBtn').addClass('active').text('已投');
    //                 var lum = parseInt(jQuery('.voteBtn').siblings('span').text());
    //                 lum = lum+1;
    //                 jQuery('.voteBtn').siblings('span').text(lum);
    //         	}
    //         } else {
    //             alert(data.message);
    //         }
    //     });
    // })
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
                    //$.proxy(_this.methods.voteCompute());
                    //console.log(_this.vote_status1);
                    if(_this.vote_status1 == 1) $.proxy(_this.async.voteFn(me));
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
                    }else if (countArr.length == 4) {
                        // ele.parents('.figure-l-fig').find('.stone-big-num').html(countArr[0]);
                        // ele.parents('.figure-l-fig').find('.stone-big-num').html(countArr[1]);
                        // ele.parents('.figure-l-fig').find('.stone-big-num').html(countArr[2]);
                        _this.vote_parent1.find('.stone-big-num').html(countArr[0].toString()+countArr[1].toString());
                        _this.vote_parent1.find('.stone-mid-num').html(countArr[2]);
                        _this.vote_parent1.find('.stone-small-num').html(countArr[3]);
                    }else{
                        return
                    }
                    if(_this.vote_status1 == 0) jQuery(me).addClass('vote-end').html('<i>已投</i>');
                    else if(_this.vote_status1 == 1) jQuery(me).removeClass('vote-end').html('<i>投票</i>');
                }
            }
        },
        async: function () {
            var _this = this;
            return {
                voteFn: function(me){
                    var count = parseInt(_this.vote_parent1.find('.vote-count').html());
                    count +=1;
                   _this.vote_parent1.find('.vote-count').html(count);
                   jQuery(me).attr('js-status','0');
                   _this.voteBtn.trigger("render");
                   var imgBox = jQuery(me).parents('article').find('.figure-r-span');
                   //console.log(imgBox.length);
                   imgBox.prepend('<a href="javascript:;"><img src="../../../../../../lunchtasting-static/wechat/images/vote/per.jpg" alt=""></a>')
                   imgBox.children(':gt(8)').css('display','none');
                }
            }
        }
    }
    vote.init();
});