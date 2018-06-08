//所有的页面都要加载util.js，来保证页面统计函数生效
//<-- 统计来访页面的渠道号 （并更新当前页面安卓APK包的链接）-->
var channel = getURLParameter("channel");
if(channel != null) {
    apkURL = "http://cdnfile.itis6am.com/"+channel+"/qcrl.apk";
    $(".download-icons-android").attr("href",apkURL);
    $.ajax({
        type: "GET",
        url: "ajaxChannelVisitLog?channel="+channel
    });
}

//如果是微信浏览器，将IOS下载链接指向腾讯应用宝
if(is_weixinBrowser()){
    $(".download-icons-ios").attr("href","http://a.app.qq.com/o/simple.jsp?pkgname=com.itis6am.app.android.mandaring");
}

/**
 * 判断是否为合法的手机号码格式
 * @param num
 * @returns {boolean}
 */
function isValidMobile(num){
    var isValid = true;
    if(!/^13[0-9]{1}[0-9]{8}$|^15[0-9]{1}[0-9]{8}$|^18[0-9]{1}[0-9]{8}$/.test(num)){
        isValid = false;
    }
    return isValid;
}

/**
 * 判断是否为数字
 * @param value
 * @returns {boolean}
 */
function isNumber(value){
    var numberTest=/^[0-9]{1,}$/;
    var pointTest=/^[0-9]{1,}.[0-9]{1,}/;

    if(numberTest.test(value)){
        return true;
    }else{
        return pointTest.test(value);
    }
}

/**
 * 是否有特殊标点
 * @param text
 * @returns {boolean}
 */
function hasPunctuation(text)
{
    var blacklist = "~!@#$%^&*()_+|`-=\{}[]:\";\'<>?,./ ";//最后一个是空格
    for (var i = 0; i<text.length; i++)
    {
        var c = text.charAt(i);
        if (blacklist.indexOf(c)>=0)
            return true;
    }

    return false;
}

/**
 * 判断对象是否为null或空字符
 * @param obj
 * @returns {boolean}
 */
function isEmpty(obj){
    if (obj == null || obj == undefined || obj == '') {
        return true;
    }else{
        return false;
    }
}

/**
 * 获得URL中传递的参数
 * @param key
 * @returns {*} 找不到key对应的值则返还null
 */
function getURLParameter(key){
    return (document.location.search.match(new RegExp("(?:^\\?|&)"+key+"=(.*?)(?=&|$)"))||['',null])[1];
}

/**
 * 判断是否为微信app浏览器
 * @returns {boolean}
 */
function is_weixinBrowser(){
    var ua = navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i)=="micromessenger") {
        return true;
    } else {
        return false;
    }
}

/**
 * 显示"请在浏览器中打开"蒙版，微信专用
 */
function showOpenBrowserMask(text){
	var openBrowserMask = document.createElement("div");
	openBrowserMask.style.cssText="display:block; width:100%; height:100%; background-color: rgba(0,0,0,0.61); z-index: 10000;" +
			"position: fixed;bottom: 0;left: 0;right: 0;top:0;text-align:center;padding-top:140px;color:#fff;";
	openBrowserMask.id = "openBrowserMask";
	openBrowserMask.onclick=function(){hideOpenBrowserMask()};
	
	var arrowImg = document.createElement("img");
	arrowImg.style.cssText="position: absolute;right: 20px;top: 15px;width: 85px;"
	arrowImg.src="http://7xnxwn.com1.z0.glb.clouddn.com/wx_open_arrow.png";
	
	var p = document.createElement("p");
	p.innerHTML=text;
	
	openBrowserMask.appendChild(arrowImg);
	openBrowserMask.appendChild(p);
	document.body.appendChild(openBrowserMask);
}
function hideOpenBrowserMask(){
	var openBrowserMask = document.getElementById("openBrowserMask");
	document.body.removeChild(openBrowserMask);
}

/**
 * 懒加载"猜你喜欢"guessBox模块
 */
function loadGuessBox(courseId){
    $.ajax({
        type:"Get",
        url:"/course/recommend?courseId="+courseId,
        success:function(data){
            if(data.code==0){return;}
            var list=data.responseObject.recommend;
            for(i=0;i<list.length;i++){
                var cInfo = list[i];

                var guessItem = $("<div></div>").addClass("guessItem");

                var guessLeft = $("<div></div>").addClass("guessLeft");
                guessLeft.append($("<img/>").attr("src",cInfo.courseImage));

                var guessRight = $("<div></div>").addClass("guessRight");
                guessRight.append($("<p></p>").addClass("guessItemName").html(cInfo.courseName));
                    var timeRow = $("<p></p>").html(cInfo.startTime+" - " +cInfo.endTime+" ");
                    if(cInfo.property.isAuto==1){timeRow.append($("<img/>").attr("src","http://7xpzlu.com1.z0.glb.clouddn.com/text_sui.png"));}
                    if(cInfo.property.isGroup==1){timeRow.append($("<img/>").attr("src","http://7xpzlu.com1.z0.glb.clouddn.com/text_tuan.png"));}
                    if(cInfo.property.isTriger==1){timeRow.append($("<img/>").attr("src","http://7xpzlu.com1.z0.glb.clouddn.com/text_cou.png"));}
                    if(cInfo.property.isLimit==1){timeRow.append($("<img/>").attr("src","http://7xpzlu.com1.z0.glb.clouddn.com/text_3.png"));}
                guessRight.append(timeRow);
                guessRight.append($("<p></p>").html(cInfo.gymName+" | "+cInfo.rank+" 分"));
                guessRight.append($("<p></p>")
                        .append($("<span></span>").addClass("qcrlRed").html("￥"+cInfo.price*0.01+" "))
                        .append($("<span></span>").addClass("priceTextGreyThrough").html(!cInfo.costPrice?"":"￥"+cInfo.costPrice*0.01))
                        .append($("<span></span>").addClass("rightSpan").html(cInfo.commercialDistrict))
                );

                guessItem.append(guessLeft).append(guessRight);
                $("#guessBox").append(guessItem);
            }
            $("#guessBox").show();
        },
        error:function(err){}
    });
}

/**
 * 通过010等城市编码获取城市中文名的array
 */
var CitiesCodeMAP=new Array();
CitiesCodeMAP["010"]="北京";
CitiesCodeMAP["020"]="广州";
CitiesCodeMAP["021"]="上海";
CitiesCodeMAP["022"]="天津";
CitiesCodeMAP["023"]="重庆";
CitiesCodeMAP["025"]="南京";
CitiesCodeMAP["027"]="武汉";
CitiesCodeMAP["028"]="成都";
CitiesCodeMAP["029"]="西安";
CitiesCodeMAP["0512"]="苏州";
CitiesCodeMAP["0571"]="杭州";
CitiesCodeMAP["0755"]="深圳";