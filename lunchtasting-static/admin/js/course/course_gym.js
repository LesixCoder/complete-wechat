jQuery(function () {
	
	//添加健身房课程关联
	$("#js-course-gym-add").click(function(){ 
		if (confirm("确定关联课程健身房?")) {
			var courseId = $("#js-courseId").val();
			var gymId = $("#js-gymId").val();
			
			if (courseId == '' || courseId == null) {
				alert("参数错误！");
				return;
			}
			if (gymId == '' || gymId == null) {
				alert("参数错误！");
				return;
			}
			
			$.post("/course/gym/doAdd", {
				course_id: courseId,gym_id:gymId
			}, function (data) {
				var code = data['code'];
				if (code == 100) {
					alert('添加成功');
					location.href = '/course/gym/add?course_id='+courseId;
				} else {
					alert(data['message']);
				}
			});
		}
	});
	
	//课程健身房关联删除
	$(".inline-block").click(function(){ 
		if (confirm("确定删除关联课程健身房?")) {
			var courseGymId = $(this).attr("js-attr-courseGymId");
			$.post("/course/gym/doRemove", {
				cg_id: courseGymId
			}, function (data) {
				var code = data['code'];
				if (code == 100) {
					$(".tr-courseGym"+courseGymId).hide();
				} else {
					alert(data['message']);
				}
			});
		}
	}); 

});