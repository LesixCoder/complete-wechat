jQuery(function () {
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
			jQuery('.wrapBox_iphone').height(view().h+"px");
		}else{
			jQuery('.wrapBox_iphone').css('height','auto');
		}
	}
	heightAuto();

	function mapHeight(){
		var scale = 976/1336;
		jQuery('#myMap').height(view().h * scale + 30 + "px");
	}
	mapHeight();
});