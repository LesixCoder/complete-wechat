jQuery(function () {
    // function fnOrderTab() {
    //     var urlData = GetQueryString("status");
    //     if (urlData == null) jQuery('.header-nav a').eq(0).addClass('active').siblings('a').removeClass('active');
    //     if (urlData == '1') jQuery('.header-nav a').eq(1).addClass('active').siblings('a').removeClass('active');
    //     if (urlData == '2') jQuery('.header-nav a').eq(2).addClass('active').siblings('a').removeClass('active');
    //     if (urlData == '3') jQuery('.header-nav a').eq(3).addClass('active').siblings('a').removeClass('active');
    // };
    // fnOrderTab();

    // function GetQueryString(name) {
    //     var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    //     var r = window.location.search.substr(1).match(reg);
    //     if (r != null) return unescape(r[2]);
    //     return null;
    // }
    // $("#list-box").waterfall({
    //     itemClass: ".box",
    //     minColCount: 2,
    //     spacingWidth: 2,
    //     spacingHeight: 2,
    //     resizeable: true
    // });
    // 赛事云风采切换UI
    function tabEle(eleParent,eleChild,style){
        jQuery(eleParent).find(eleChild).on('touchend',function(e){
            e.preventDefault();
            jQuery(this).addClass(style).siblings(eleChild).removeClass(style);
        })
    }
    tabEle('#main_btnG','a','active');
    jQuery('#payBtn').on('touchend',function(e){
        var href = jQuery('#main_btnG a.active').attr('data-href');
        if(href) window.location.href = href;
        else return false;
    });

    // 照片张数展示
    (function () {
        var num = jQuery('.box-person span').text();
        var arr = new Array();
        var arr = num.split('');
        arr.forEach(function (value, index, array) {
            var i = document.createElement('i');
            i.innerHTML = value
            jQuery('.box-person span').append(i);
        })
        var tmp = $('.box-person span').children().clone();
        $('.box-person span').html('').append(tmp);
    })();

    // 初始化
    var initPhotoSwipeFromDOM = function (gallerySelector) {
        // parse slide data (url, title, size ...) from DOM elements 
        // (children of gallerySelector)
        var parseThumbnailElements = function (el) {
            var thumbElements = el.childNodes,
                numNodes = thumbElements.length,
                items = [],
                figureEl,
                linkEl,
                size,
                item;
            for (var i = 0; i < numNodes; i++) {
                figureEl = thumbElements[i]; // <figure> element
                // include only element nodes 
                if (figureEl.nodeType !== 1) {
                    continue;
                }
                linkEl = figureEl.children[0]; // <a> element
                size = linkEl.getAttribute('data-size').split('x');
                // create slide object
                item = {
                    src: linkEl.getAttribute('href'),
                    w: parseInt(size[0], 10),
                    h: parseInt(size[1], 10)
                };
                if (figureEl.children.length > 1) {
                    // <figcaption> content
                    item.title = figureEl.children[1].innerHTML;
                }
                if (linkEl.children.length > 0) {
                    // <img> thumbnail element, retrieving thumbnail url
                    item.msrc = linkEl.children[0].getAttribute('src');
                }
                item.el = figureEl; // save link to element for getThumbBoundsFn
                items.push(item);
            }
            return items;
        };
        // find nearest parent element
        var closest = function closest(el, fn) {
            return el && (fn(el) ? el : closest(el.parentNode, fn));
        };
        // triggers when user clicks on thumbnail
        var onThumbnailsClick = function (e) {
            e = e || window.event;
            e.preventDefault ? e.preventDefault() : e.returnValue = false;
            var eTarget = e.target || e.srcElement;
            // find root element of slide
            var clickedListItem = closest(eTarget, function (el) {
                return (el.tagName && el.tagName.toUpperCase() === 'FIGURE');
            });
            if (!clickedListItem) {
                return;
            }
            // find index of clicked item by looping through all child nodes
            // alternatively, you may define index via data- attribute
            var clickedGallery = clickedListItem.parentNode,
                childNodes = clickedListItem.parentNode.childNodes,
                numChildNodes = childNodes.length,
                nodeIndex = 0,
                index;
            for (var i = 0; i < numChildNodes; i++) {
                if (childNodes[i].nodeType !== 1) {
                    continue;
                }
                if (childNodes[i] === clickedListItem) {
                    index = nodeIndex;
                    break;
                }
                nodeIndex++;
            }
            if (index >= 0) {
                // open PhotoSwipe if valid index found
                openPhotoSwipe(index, clickedGallery);
            }
            return false;
        };
        // parse picture index and gallery index from URL (#&pid=1&gid=2)
        var photoswipeParseHash = function () {
            var hash = window.location.hash.substring(1),
                params = {};
            if (hash.length < 5) {
                return params;
            }
            var vars = hash.split('&');
            for (var i = 0; i < vars.length; i++) {
                if (!vars[i]) {
                    continue;
                }
                var pair = vars[i].split('=');
                if (pair.length < 2) {
                    continue;
                }
                params[pair[0]] = pair[1];
            }
            if (params.gid) {
                params.gid = parseInt(params.gid, 10);
            }
            return params;
        };
        var openPhotoSwipe = function (index, galleryElement, disableAnimation, fromURL) {
            var pswpElement = document.querySelectorAll('.pswp')[0],
                gallery,
                options,
                items;
            items = parseThumbnailElements(galleryElement);
            // define options (if needed)
            options = {
                arrowEl: false,
                // Tap on sliding area should close gallery
                tapToClose: false,
                // Tap should toggle visibility of controls
                tapToToggleControls: false,
                shareEl: false,
                loop: false,
                bgOpacity: 0.85,
                barsSize: {
                    top: 100,
                    bottom: 100
                },
                fullscreenEl: false,
                // define gallery index (for URL)
                galleryUID: galleryElement.getAttribute('data-pswp-uid'),
                getThumbBoundsFn: function (index) {
                    // See Options -> getThumbBoundsFn section of documentation for more info
                    var thumbnail = items[index].el.getElementsByTagName('img')[0], // find thumbnail
                        pageYScroll = window.pageYOffset || document.documentElement.scrollTop,
                        rect = thumbnail.getBoundingClientRect();
                    return {
                        x: rect.left,
                        y: rect.top + pageYScroll,
                        w: rect.width
                    };
                }
            };
            // PhotoSwipe opened from URL
            if (fromURL) {
                if (options.galleryPIDs) {
                    // parse real index when custom PIDs are used 
                    // http://photoswipe.com/documentation/faq.html#custom-pid-in-url
                    for (var j = 0; j < items.length; j++) {
                        if (items[j].pid == index) {
                            options.index = j;
                            break;
                        }
                    }
                } else {
                    // in URL indexes start from 1
                    options.index = parseInt(index, 10) - 1;
                }
            } else {
                options.index = parseInt(index, 10);
            }
            // exit if index not found
            if (isNaN(options.index)) {
                return;
            }
            if (disableAnimation) {
                options.showAnimationDuration = 0;
            }
            // Pass data to PhotoSwipe and initialize it
            gallery = new PhotoSwipe(pswpElement, PhotoSwipeUI_Default, items, options);
            gallery.init();
        };
        // loop through all gallery elements and bind events
        var galleryElements = document.querySelectorAll(gallerySelector);
        for (var i = 0, l = galleryElements.length; i < l; i++) {
            galleryElements[i].setAttribute('data-pswp-uid', i + 1);
            galleryElements[i].onclick = onThumbnailsClick;
        }
        // Parse URL and open gallery if it contains #&pid=3&gid=1
        var hashData = photoswipeParseHash();
        if (hashData.pid && hashData.gid) {
            openPhotoSwipe(hashData.pid, galleryElements[hashData.gid - 1], true, true);
        }
    };
    // execute above function
    initPhotoSwipeFromDOM('.my-gallery');

    // function getQueryString(name) {
    //     var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    //     var r = window.location.search.substr(1).match(reg);
    //     if (r != null) return unescape(r[2]);
    //     return null;
    // }
    // jQuery('.my-gallery .box').each(function () {
    //     jQuery(this).find('.collect').children('i').click(function () {
    //         console.log(jQuery(this));
    //     })
    // });

    // if (("onhashchange" in window) && ((typeof document.documentMode === "undefined") || document.documentMode == 8)) {
    //     // 浏览器支持onhashchange事件  
    //     window.onhashchange = hashFn; // TODO，对应新的hash执行的操作函数  
    // } else {
    //     // 不支持则用定时器检测的办法  
    //     setInterval(function () {
    //         var ischanged = isHashChanged(); // TODO，检测hash值或其中某一段是否更改的函数  
    //         if (ischanged) {
    //             hashFn(); // TODO，对应新的hash执行的操作函数  
    //         }
    //     }, 150);
    // }
    // hashFn();
    // function hashFn(){
    //     jQuery('.pswp__caption__center span.collect').on('touchend', function () {
    //         var param = location.hash.substring(12);
    //         var collectData = jQuery('.pswp__caption__center span.collect').attr('data-collect');
    //         //console.log(param);
    //         //console.log(collectData);
    //         if (collectData == param) {
    //             if (jQuery('.pswp__caption__center span.collect[data-collect=' + param + ']').attr('data-cut') == '0') {
    //                 jQuery('.pswp__caption__center span.collect[data-collect=' + param + ']').attr('data-cut', '1').html('<i class="glyphicon glyphicon-heart"></i>已收藏');
    //             } else {
    //                 jQuery('.pswp__caption__center span.collect[data-collect=' + param + ']').attr('data-cut', '0').html('<i class="glyphicon glyphicon-heart-empty"></i>收藏');
    //             }
    //         }
    //     })
    //     jQuery('.pswp__caption__center span:last').on('touchend', function () {
    //         window.location.href = 'http://www.baidu.com/';
    //     })
    // }
})