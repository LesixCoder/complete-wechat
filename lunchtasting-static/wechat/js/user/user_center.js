jQuery(function () {

    //评分初始化
    jQuery(".ratyli").ratyli({
        disable: true,
        full: "<i class='fa fa-heart'></i>",
        empty: "<i class='fa fa-heart-o'></i>",
    });

    //标签选择
    let labelDiv = {
        dom: jQuery(".label-div"),
        selBox: function () {

        }
    }

    jQuery(".person-edit .label-div a").each(function (index, element) {
        jQuery(this).click(function () {
            if (jQuery(".person-edit .label-div a[lang='checked']").length >= 3) {
                return false;
            }
            if (jQuery(this).attr('lang') == 'empty') {
                jQuery(this).addClass('color' + (index + 1)).attr('lang', 'checked');
            } else if (jQuery(this).attr('lang') == 'checked') {
                jQuery(this).removeClass('color' + (index + 1)).attr('lang', 'empty');
            }
        })
    })

    //重选
    jQuery(".person-edit .label-reset").on('click', function () {
        jQuery(".person-edit .label-div a").each(function (index, element) {
            jQuery(this).removeClass('color' + (index + 1)).attr('lang', 'empty');
        });
    });

    //编辑个人资料
    jQuery(".header-edit,.cancelBtn").click(function () {
        jQuery('.person-edit').slideToggle('fast');
    });
    
    //修改个人资料
    $('.convertBtn').click(function (event) {
        var signature = jQuery('#declaration').val(); //参赛宣言;
        var hobby = jQuery('#hobby').val(); //兴趣爱好
        var feeling = jQuery('.infor-box input:radio:checked').val(); //情感状况
        var tags = getLabel(); //标签

        $.post("/user/info/update", {
            signature: signature,
            hobby: hobby,
            feeling: feeling,
            tags: tags
        }, function (result) {
            var data = JSON.parse(result);
            if (data.code == 100) {
                location.href = "/user/center";
            } else {
                alert(data.message);
                //$('.person-edit').hide();
            }
        });
    });
    
    //回复
    var parentId,parentUserId;
    jQuery('.replyCancel').on('click', function () {
        jQuery('.replyBox textarea').val('');
        parentId = '';
        parentUserId = '';
        jQuery('.replyBox').hide();
    });
    
    //回复留言
    jQuery('.replyB').click('on', function () {
    	parentUserId = $(this).attr("js-attr-parentUserId");
    	parentId = $(this).attr("js-attr-parentId");
        jQuery('.replyBox').show();
    });
    
    //直接留言
    jQuery('.replyC').click('on', function () {
        jQuery('.replyBox').show();
    });
    
    $('.replyBtn').click(function(event) {
    	let textVal = $.trim(jQuery('.replyBox textarea').val());
    	var userId = jQuery('#js-userId').val();
    	
		$.post("/user/comment/add",{user_id:userId,parent_user_id:parentUserId
				,parent_id:parentId,content:textVal},function(result){
			
			var code = result['code'];
			if(code == 100){
				textVal = '';
		        jQuery('.replyBox').hide();
		        
		        var commentId = result['data']['comment_id'];
		        var content = result['data']['content'];
		        var srcUserId = result['data']['src_user_id'];
		        var srcName = result['data']['src_name'];
		        var pUserId = result['data']['parent_user_id'];
		        var pName = result['data']['parent_name'];
		        
		        if(pUserId == null || pUserId == '' 
		        		|| typeof(pUserId) == "undefined"){
			        jQuery('.msg-box').prepend("<li><p><span><i>"+srcName+"</i>："+content+"</span>" +
			        		"	<a class='replyBA' href='javascript:void(0)' " +
			        		"	js-attr-parentUserId='"+srcUserId+"' js-attr-parentId='"+commentId+"'>回复</a></p></li>");
		        }else{
			        jQuery('.msg-box').prepend("<li><p><span><i>"+srcName+"</i>回复<i>"+pName+"</i>："+content+"</span>" +
			        		"	<a class='replyBA' href='javascript:void(0)' " +
			        		"	js-attr-parentUserId='"+srcUserId+"' js-attr-parentId='"+commentId+"'>回复</a></p></li>");
		        }
		        
		        jQuery(".replyBA").click('on', function () {
		        	parentUserId = $(this).attr("js-attr-parentUserId");
		        	parentId = $(this).attr("js-attr-parentId");
		        	jQuery('.replyBox textarea').val('');
		            jQuery('.replyBox').show();
		        });

		        
			}else{
				alert(result['message']);
			}
		});
		
	});
    

});


//选择标签
function getLabel() {
    var label = "";
    jQuery(".person-edit .label-div a[lang='checked']").each(function (index, ele) {
        label += $(this).text() + ",";
    });
    //去掉最后一个逗号
    if (label.length > 0) {
        label = label.substr(0, label.length - 1);
    }
    return label;
}
