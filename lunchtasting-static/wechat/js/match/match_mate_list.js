jQuery(function() {
    //自适应宽高
    window.onload = function() {
        jQuery(".marry-list").each(function() {
            var hei = jQuery(this).children("figcaption").height();
            jQuery(this).find("img").css({
                'height': hei
            });
        })
    }
    
//    //评分初始化
//    jQuery(".ratyli").ratyli({
//        disable: true,
//        full:"<i class='fa fa-heart'></i>",
//        empty:"<i class='fa fa-heart-o'></i>",
//    });
    
	$('.js-group-invite').click(function(event) {
		var inviteUserId = $(this).attr("js-attr-userId");
		var matchId = $(this).attr("js-attr-matchId");
		
		$.post("/match/mate/invite",{match_id:matchId,invite_id:inviteUserId},function(result){
			var data = JSON.parse(result);
			if(data.code == 100){
				alert('已经邀请啦，静候佳音吧！');
			}else{
				alert(data.message);
			}
		});
		
	});
});