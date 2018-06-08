jQuery(function() {
    //列表切换
    function scoreList() {
        jQuery('.box-btnG a').on('click', function() {
            jQuery(this).addClass('active').siblings('a').removeClass('active');
            if (jQuery(this).hasClass('btnG1')) {
                jQuery('.list1').removeClass('none');
                jQuery('.list2').addClass('none');
            } else if (jQuery(this).hasClass('btnG2')) {
                jQuery('.list1').addClass('none');
                jQuery('.list2').removeClass('none');
            }
        })
    }
    scoreList();
});