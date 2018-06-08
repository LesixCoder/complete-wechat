jQuery(window).load(function() {
    var options = {
        thumbBox: '.thumbBox',
        spinner: '.spinner',
        imgSrc: ''
    }
    var cropper = jQuery('.imageBox').cropbox(options);
    jQuery('#upload-file').on('change', function() {
        var reader = new FileReader();
        reader.onload = function(e) {
            options.imgSrc = e.target.result;
            cropper = jQuery('.imageBox').cropbox(options);
        }
        reader.readAsDataURL(this.files[0]);
        //this.files = [];
    })
    jQuery('#btnZoomIn').on('click', function() {
        cropper.zoomIn();
    })
    jQuery('#btnZoomOut').on('click', function() {
        cropper.zoomOut();
    })
    jQuery('#btnCrop').on('click', function() {
        var val = jQuery('.imageBox').attr('style');
        if(val == -1 || val == undefined){
            return alert("请选择一张图片");
        }
        var img = cropper.getDataURL();
        jQuery('#imgEditBox').html('');
        jQuery('#imgEditBox').append('<img src="' + img + '" alt="点击图片上传" >');
        jQuery('.imgLoad').hide();
//        jQuery.ajax({
//            url: '',
//            data: {
//                'imgSrc':img
//            },
//            type: 'post',
//            dataType: 'json',
//            success: function(data) {
//                jQuery('.imgLoad').hide();
//            },
//            error: function() {
//                alert("服务器连接失败！");
//            }
//        });
    })
});