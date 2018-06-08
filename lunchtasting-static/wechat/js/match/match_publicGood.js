jQuery(function(){
    var num = jQuery('.box-person span').text();
    var arr = new Array();
    var arr = num.split('');
    arr.forEach(function(value, index, array){
        console.log(value);
        var i = document.createElement('i');
        i.innerHTML = value
        jQuery('.box-person span').append(i);
    })
    var tmp=$('.box-person span').children().clone();
    $('.box-person span').html('').append(tmp);
});
