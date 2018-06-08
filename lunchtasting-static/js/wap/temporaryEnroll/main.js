jQuery(function(){
    /*弹出层*/
    var navBox=jQuery("#navBox");
    var joinR=document.querySelector(".joinR");
    var cut=0;
    joinR.onclick=function(event){
        if(cut==0){
            jQuery("#navBox").fadeIn('fast');
            jQuery(".filter").fadeIn('fast');
            cut=1;
        }else if(cut==1){
            jQuery("#navBox").fadeOut('fast');
            jQuery(".filter").fadeOut('fast');
            cut=0;
        }
    };
    var filter=document.querySelector('#filter');
    filter.addEventListener('touchstart',function(){
        jQuery("#navBox").fadeOut('fast');
        jQuery(".filter").fadeOut('fast');
        cut=0;
    });
    filter.addEventListener('touchmove',function(){
        jQuery("#navBox").fadeOut('fast');
        jQuery(".filter").fadeOut('fast');
        cut=0;
    });
});