$(function () { 
    /*查询btn事件*/
    $("#inquire").on('click', function () {
        $("#List  tr:not(:first)").html("");
        var code = jQuery("#quireInput").val();
        if (code == "" || code == "null" || code == "undefined") {
            alert("输入不合法");
            return;
        }
        var data = {
            code: jQuery("#quireInput").val()
        }
        jQuery.ajax({
            url: 'getOrdersListCode',
            data: data,
            type: 'post',
            async: false,
            dataType: 'json',
            success: function (data) {
                var code = data.code;
                if (code == 1) {
                    alert(data.message);
                } else if (code == 2) {
                    alert(data.message);
                }
                if (data.data.ordersList[0] != null) {
                    tiancai(data.data.ordersList[0]);
                }
            },
            error: function () {
                alert("服务器连接失败！");
            }
        });
    });
});

function tiancai(e) {
    var con = e.orders_list.length;
    var time = "";

    if (e.orders_list[0].createTime != null && e.orders_list[0].createTime != "null") {
        time = format(parseInt(e.orders_list[0].createTime));
    }
    var state = e.orders_list[0].status;
    if (state == 1) {
        state = "未使用";
    } else if (state == 2) {
        state = "已使用";
    } else if (state == 3) {
        state = "退款中";
    } else if (state == 4) {
        state = "已退款";
    } else {
        state = "未知";
    }
    var html =
        "<tbody>" +
        "<tr>" + "<td rowspan='" + con + "'>" + e.code + "</td>" + "<td rowspan='" + con + "'>" + e.course_name + "</td>" + "<td rowspan='" + con + "'>" + e.pay_price + "</td>" + "<td rowspan='" + con + "'>" + e.create_time + "</td>" + "<td lang='code'>" + e.orders_list[0].code + "</td>" + "<td>" + time + "</td>" + "<td lang='state'>" + state + "</td>" + "<td lang='btn'>" + "<button class='btn btn-xs btn-success' data-toggle='modal' data-target='#rolePopUp'>已使用</button>" + "</td>" + " </tr>";
    var html2 = "";
    var json = e.orders_list;
    for (var i = 1; i < json.length; i++) {
        var time2 = "";
        var state = json[i].status;
        if (json[i].createTime != null && json[i].createTime != "null") {
            time2 = format(parseInt(json[i].createTime));
        }
        if (state == 1) {
            state = "未使用";
        } else if (state == 2) {
            state = "已使用";
        } else if (state == 3) {
            state = "退款中";
        } else if (state == 4) {
            state = "已退款";
        } else {
            state = "未知";
        }
        html2 = html2 +
            "<tr>" + "<td lang='code'" +
            ">" + json[i].code + "</td>" + "<td>" + time2 + "</td>" + "<td lang='state'>" + state + "</td>" + "<td lang='btn' id=" + json[i].id + ">" + "<button class='btn btn-xs btn-success' data-toggle='modal' data-target='#rolePopUp' >使用</button>" + "</td>" + "</tr>";
    }
    var html3 = html + html2 + "</tbody>";
    $("#List").append($(html3));
    /*状态样式*/
    $("table#List tbody").each(function () {
        $(this).children('tr').each(function (index) {
            var $tdCur = $(this).find('td[lang=state]').text();
            var $tdBtn = $(this).find('td[lang=btn]>button');
            var $tdSpan = $(this).find('td[lang=btn]>button>span');
            if ($tdCur == '未使用') {
                //$(this).addClass('active');
                $tdBtn.text('使用');
                $tdBtn.on('click', function () {
                    if (confirm("确定要使用吗？")) {
                        var e = $(this).parent("td").siblings("td[lang='code']").text();
                        employByCode(e);
                    }
                });
            } else if ($tdCur == '已使用') {
                $tdBtn.text('已使用');
                $tdBtn.addClass('disabled').text('已使用');
            } else if ($tdCur == '退款中') {
                $tdBtn.addClass('btn-danger disabled').text('退款中');
            }
        })
    });
}

function employByCode(e) {
    $("#List  tr:not(:first)").html("");
    var data = {
        code: e
    }
    jQuery.ajax({
        url: 'employCodeByCode',
        data: data,
        type: 'post',
        async: false,
        dataType: 'json',
        success: function (data) {
            var code = data.code;
            if (data.data.ordersList[0] != null) {
                tiancai(data.data.ordersList[0]);
            }
            alert(data.message);
        },
        error: function () {
            alert("服务器连接失败！");
        }
    });
}
/**
 * 处理时间
 * @param m
 * @returns
 */
function add0(m) {
    return m < 10 ? '0' + m : m;
}

function format(shijianchuo) {
    //shijianchuo是整数，否则要parseInt转换
    var time = new Date(shijianchuo);
    var y = time.getFullYear();
    var m = time.getMonth() + 1;
    var d = time.getDate();
    var h = time.getHours();
    var mm = time.getMinutes();
    var s = time.getSeconds();
    return y + '-' + add0(m) + '-' + add0(d) + " " + add0(h) + ":" + add0(mm);
}
