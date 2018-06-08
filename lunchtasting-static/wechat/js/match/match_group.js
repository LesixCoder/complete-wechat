jQuery(function() {
	
	//加入队伍
	$('#js-group-add').click(function(event) {
		var matchOrderId = $(this).attr("js-attr-orderId");
		var groupUserId = $(this).attr("js-attr-groupUserId");
		var inviteUserId = $(this).attr("js-attr-inviteId");
		var matchId = $(this).attr("js-attr-matchId");
		
		//alert(matchOrderId + "///" + groupUserId + "///" + inviteUserId);
		
		//判断当前用户是否已经组队
		if(groupUserId != null && groupUserId != ''){
			alert('你已经有队伍了！到处勾搭可不好哦！');
			return;
		}
		
		//判断是否报名，没报名先报名
		if(matchOrderId == null || matchOrderId == ''){
			
		}
		
		$.post("/match/group/add",{match_id:matchId,invite_id:inviteUserId},function(result){
			var data = JSON.parse(result);
			if(data.code == 100){
				alert('恭喜你，组队成功。开启梦幻之旅吧！');
			}else if(data.code == 1){
				location.href = '/match/paoxiaogou';
			}else{
				alert(data.message);
			}
		});
		
	});
});