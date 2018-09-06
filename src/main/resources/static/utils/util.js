//=======================================listui表格工具,不具备通用性===============================================
var Icon = {
    see: '<i class="icon icon-eye-open mar-r-10" title="查看"></i>',
    add: '<i class="iconfont icon-zuzhijiagou-tianjia mar-r-10" title="添加"></i>',
    edit: '<i class="iconfont icon-cloud-change mar-r-10" title="编辑"></i>',
    remove: '<i class="iconfont icon-lajitong mar-r-10" title="删除"></i>',
    remove_x: '<i class="icon icon-times title="删除"></i>',
    disable: '<i class="icon icon-toggle-on mar-r-10" title="禁用"></i>',
    noDisable: '<i class="icon icon-toggle-off mar-r-10" title="启用"></i>',
    submit: '<i class="icon icon-save mar-r-10" title="提交"></i>',
    acceptance: '<i class="icon icon-check mar-r-10" title="受理"></i>',
    agree: '<i class="icon icon-check mar-r-10" title="同意"></i>',
    end: '<i class="icon icon-check mar-r-10" title="结束"></i>',
    launch: '<i class="icon icon-warning-sign mar-r-10" title="发起整改"></i>',
    reject: '<i class="icon icon-times mar-r-10" title="驳回"></i>',
    eraser: '<i class="icon icon-eraser mar-r-10" title="清除"></i>',
    tag: '<i class="icon icon-tag mar-r-10" title="原因"></i>',
    down: '<i class="icon mar-r-10 icon-cloud-download" title="下载"></i>',
    draft: '<i class="icon icon-save mar-r-10" title="生成报告草稿"></i>',
    Official_report: '<i class="icon icon-signin mar-r-10" title="生成正式报告"></i>',
    pay: '<i class="icon mar-r-10 icon-yen" title="付款"></i>',
    cancel: '<i class="icon mar-r-10 icon-minus" title="取消"></i>',
    record: '<i class="icon icon-tags mar-r-10" title="记录"></i>',
    isDisable: function (val) {
        return val == 0 ? Icon.disable : Icon.noDisable;
    }
};

function hideCol(container, tr, indexs) {
    if ($(container).find('table tbody tr').length == 0) {
        for (var v in indexs) {
            $(container).find('table thead tr').find('th:eq(' + indexs[v] + ')').hide();
        }
    }
    for (var v in indexs) {
        $(tr).find('td:eq(' + indexs[v] + ')').hide();
    }
}

function listuiCustom(url, set) {
    var arr = $('.listui.custom');
    if ($(arr).length < 1) {
        return;
    }
    for (var i = 0; i < $(arr).length; i++) {
        if ($(arr).find('.listQueryUrl').val() == url) {
            var customPageInfo = $(arr).find('.custom-page-info');
            if ($(customPageInfo).length == 0) {
                var pageInfo = $(arr).find('.page-info');
                var fl = IEUtil.isIEDown9() ? 'f-l' : '';
                var html = [
                    '<div class="custom-page-info" style="float:right;margin: 20px 0px 20px 10px;">',
                    '   <input class="page-size ' + fl + ' only-num out-line" desc="0" style="width:40px;height: 33px;text-align: center;border: 1px solid #ddd;">',
                    '   <input class="page-size-btn ' + fl + ' out-line" num="" type="button" value="跳转" style="width: 40px;height: 33px;padding-top: 2px;border: 1px solid #ddd;background-color: white;">',
                    '   <select class="costom-listui-select ' + fl + ' out-line" style="width: 50px;height: 33px;border: 1px solid #ddd;">',
                    '       <option>10</option>',
                    '       <option>20</option>',
                    '       <option>30</option>',
                    '       <option>50</option>',
                    '   </select>',
                    '</div>'
                ];
                pageInfo.prepend(html.join(''));
            } else {
                var num = $('.listui.custom .custom-page-info .page-size-btn').attr('num');
                $('.listui.custom .custom-page-info .page-size-btn').attr('num', '');
                set.data['pageNumber'] = num ? num : set.data['page'];
                set.data['pageSize'] = $(customPageInfo).find('select').val();
            }
        }
    }
}

var ajaxBack = $.ajax;
$(function () {
    $.ajax = function (setting) {
        var urls = setting.url.split('/');
        if (urls[urls.length - 1] == 'listQuery') {
            listuiCustom(setting.url.replace('/listQuery', ''), setting);
            var u = setting.url.split('?');
            if (u.length > 1) {
                setting.url = u[0] + '/listQuery';
            }
        }
        var cb = setting.complete;
        setting.complete = function () {
            if ($.isFunction(cb)) {
                cb.apply(setting.context, arguments);
            }
        }
        return ajaxBack(setting);
    }

    $('.listui.custom').on('input propertychange', '.custom-page-info .page-size', function () {
        var li = $('.listui.custom .page-info .pager li');
        var max = parseInt($($(li)[$(li).length - 2]).text());
        var value = $(this).val();
        if (value > max) {
            var val = '';
            for (var i = 0; i < value.length; i++) {
                val += value[i];
                if (val > max) {
                    val = val.substring(0, val.length - 1);
                    break;
                }
            }
            $(this).val(val);
        } else if (value == 0) {
            $(this).val('');
        }
    });

    $('.listui.custom').on('click', '.custom-page-info .page-size-btn', function () {
        var page = $(this).parents('.custom-page-info').find('.page-size').val();
        if (page) {
            $(this).attr('num', page);
            $(this).attr('num');
            listui.refresh($(this).parents('.listui').attr('id'));
        }
    });

    $('.listui.custom').on('change', '.costom-listui-select', function () {
        listui.refresh($(this).parents('.listui').attr('id'));
    });
});

//==================================================================================================================

$(function () {

    //如果是IE,则加载IE对应的css文件
    if (IEUtil.isIE()) {
        $("head").append("<link>");
        css = $("head").children(":last");
        css.attr({
            rel: "stylesheet",
            type: "text/css",
            href: $('body').attr('ctx') + "/css/ieStyle.css"
        });
    }

    //如果是火狐或edge,加载IE对应的css文件
    if (ChromeUtil.isFire() || ChromeUtil.isEdge()) {
        $("head").append("<link>");
        css = $("head").children(":last");
        css.attr({
            rel: "stylesheet",
            type: "text/css",
            href: $('body').attr('ctx') + "/css/ieStyle.css"
        });
    }

    /**
     * no-zh 不允许输入中文
     * propertychange在IE8无法动态添加,使用该工具时需手动调用
     */
    TextUtil.noENEvent();

    /**
     * only-num 只允许输入数字和一位小数点, 如有dec属姓,并属性值等于0,则不允许输入小数点, 如属性值等于1,则允许输入多个小数点,
     * 可通过filter属性过滤一个或多个字符,以|分隔
     * propertychange在IE8无法动态添加,使用该工具时需手动调用
     */
    TextUtil.onlyNumEvent();

    /**
     * no-special 不允许输入殊殊字符
     * propertychange在IE8无法动态添加,使用该工具时需手动调用
     */
    TextUtil.noSpecialEvent();

    /**
     * 给必填字段加红色*号(注意：动态加载的html无法生效)
     */
    TextUtil.notNullFlag();

});

var ChromeUtil = {
    isFire: function () {
        return isFirefox = navigator.userAgent.indexOf("Firefox") > 0;
    },
    isEdge: function () {
        return navigator.userAgent.indexOf("Edge") > -1;
    },
    isIE: function () {
        return !!window.ActiveXObject || "ActiveXObject" in window;
    }
};

var IEUtil = {
    isIE: function () {
        if (!!window.ActiveXObject || "ActiveXObject" in window) {
            return true;
        }
        return false;
    },
    isIEDown9: function () {
        if (IEUtil.isIE() && IEUtil.ieVersion() == '8.0' || IEUtil.ieVersion() == '9.0' || IEUtil.ieVersion() == '10.0') {
            return true;
        }
        return false;
    },
    isIE9: function () {
        return IEUtil.ieVersion() == '9.0';
    },
    isIE10: function () {
        if (IEUtil.isIE() && IEUtil.ieVersion() == '10.0') {
            return true;
        }
        return false;
    },
    isIE11: function () {
        if (IEUtil.isIE() && IEUtil.ieVersion() == '11.0') {
            return true;
        }
        return false;
    },
    noClick: function () {
        if (IEUtil.isIE()) {
            // $('.no-click').each(function (i, dom) {
            //     $(dom).attr('disabled', 'disabled');
            //     if ($(dom).find('.date-span').length > 0) {
            //         var val = $(dom).find('.date-span').parents('.input-group').find('input').val();
            //         $(dom).find('.date-span').parents('.input-group').html('<input class="form-control" style="width:183px;" disabled="disabled" value="' + val + '"/>');
            //     }
            // });
            // $('.no-click-type').each(function (i, dom) {
            //     $(dom).attr('disabled', 'disabled');
            //     if ($(dom).find('.date-span').length > 0) {
            //         var val = $(dom).find('.date-span').parents('.input-group').find('input').val();
            //         $(dom).find('.date-span').parents('.input-group').html('<input class="form-control" style="width:183px;" disabled="disabled" value="' + val + '"/>');
            //     }
            // });
        }
    },
    ieVersion: function () {
        var UA = navigator.userAgent;
        var ver = null;
        if (/msie/i.test(UA)) {
            ver = UA.match(/msie (\d+\.\d+)/i)[1];
        } else if (~UA.toLowerCase().indexOf('trident') && ~UA.indexOf('rv')) {
            ver = UA.match(/rv:(\d+\.\d+)/)[1];
        }
        return ver;
    },
    ieUpload: function (ie) {
        if (!IEUtil.isIEDown9() && ie.trigger) {
            if ($(ie.trigger).hasClass('hide')) {
                $(ie.trigger).show();
            } else if ($(ie.trigger).hasClass('hidden')) {
                $(ie.trigger).removeClass('hidden');
            }
            return;
        } else {
            if ($(ie.me).hasClass('hide')) {
                $(ie.me).show();
            } else if ($(ie.me).hasClass('hidden')) {
                $(ie.me).removeClass('hidden');
            }
        }
        IEUtil.show(ie);
        if (ie.autoUpload) {
            $(ie.me).on('change', 'input', function () {
                var check = IEUtil.check(ie.filter, $(this).val().split('.'));
                if (check) {
                    AlertUtil.defaultAlert(check);
                    return;
                }
                if (ie.before) {
                    ie.before();
                }
                $(this).parents('form').ajaxSubmit(function (data) {
                    var res = JSON.parse(data);
                    if (ie.callback) {
                        ie.callback(res);
                    }
                    //删除后再添加file,使文件可以重复上传
                    $(ie.me).find('.ie-r').remove()
                    IEUtil.show(ie);
                    return false;
                });
            });
        }
    },
    show: function (ie) {
        var width = ie.width ? ie.width : '72px';
        var height = ie.height ? ie.height : '32px';
        var html = [
            '<input type="file" class="ie-r poin" name="file" style="font-size:500px;position:absolute;top:32px;filter: alpha(opacity=0);opacity:0;margin-top:-' + height + ';height:' + height + ';width:' + width + ';"/>',
            '<button type="button" class="ie-r btn btn-primary" style="float:right;padding-left:10px;height:' + height + ';width:' + width + ';">' + ie.name + '</button>'
        ];
        $(ie.me).html(html.join(''));
        $(ie.me).show();
    },
    check: function (filter, fs) {
        if (filter) {
            if (fs.length > 1) {
                fs = fs[fs.length - 1];
                var flag = true;
                for (var v in filter) {
                    if (filter[v] == fs) {
                        flag = false;
                    }
                }
                if (flag) {
                    return '上传文件格式错误！';
                }
            } else {
                return '上传文件格式错误！';
            }
        }
        return null;
    }
};

var ArrayUtil = {
    getByAttr: function (arr, att, value) {
        for (var i = 0; i < arr.length; i++) {
            if (arr[i][att] == value) {
                return arr[i];
            }
        }
        return null;
    }
};

var FormUtil = {
    decodeURIComponent: function ($this) {
        return decodeURIComponent($($this).serialize(), true).split('&');
    },
    set: function (tag, data) {
        var datas = $(tag).find('[name]');
        for (var i = 0; i < datas.length; i++) {
            $(datas[i]).val(data[$(datas[i]).attr('name')]);
        }
    },
    setSpan: function (tag, data) {
        var datas = $(tag).find('[name]');
        for (var i = 0; i < datas.length; i++) {
            $(datas[i]).text(data[$(datas[i]).attr('name')] ? data[$(datas[i]).attr('name')] : '');
        }
    },
    get: function ($this) {
        var datas = FormUtil.decodeURIComponent($this);
        var data = {};
        for (var i = 0; i < datas.length; i++) {
            var ds = datas[i].split('=');
            if (ds.length > 1 && ds[1]) {
                var list = ds[1].split('+');
                if (list.length > 1) {
                    ds[1] = '';
                    for (var v in list) {
                        ds[1] += list[v];
                    }
                }
            }
            data[ds[0]] = ds.length > 1 && ds[1] ? $.trim(ds[1]) : '';
        }
        return data;
    }
};

var RefreshUtil = {
    reset: function (cla) {
        var tags = cla ? $(cla) : $('.reset');
        if (!tags || tags.length < 1) {
            return;
        }
        for (var i = 0; i < tags.length; i++) {
            var type = $(tags[i]).get(0).tagName;
            if (type == 'IMG') {
                $(tags[i]).attr('src', '');
            } else if (type == 'SELECT') {
                $(tags[i]).val($($(tags[i]).find('option').get(0)).val());
            } else {
                $(tags[i]).val('')
            }
        }
    }
};

var DateUtil = {
    formatDate: function (date, format) {
        format = format ? format : 'yyyy-MM-dd hh:mm:ss';
        return date && date.getFullYear() > 1970 ? date.format(format) : '';
    },
    format: function (format) {
        DateUtil.formatDate(new Date(), format);
    }
};

var AlertUtil = {
    defaultAlert: function (e) {
        layer.msg(e);
    },
    layerConfirm: function (msg, callback, noback) {
        var la = layer.open({
            content: msg,
            btn: ['确定', '取消'],
            yes: function (index, layero) {
                callback();
                layer.close(la);
            },
            no: function () {
                noback();
                layer.close(la);
            }
        });
        $('.layui-layer-btn1').addClass('hidden');
        $('.layui-layer-setwin').addClass('hidden');
    },
    layerOpen: function ($view, hw, callback, title) {
        var la = layer.open({
            type: 1,
            title: title,
            content: $view,
            area: hw,
            btn: ['确定', '取消'],
            yes: function (index, layero) {
                if (callback) {
                    callback(la);
                } else {
                    layer.close(la);
                }
            }
        });
    }
};

var NumUtil = {
    retain: function (value) {
        var value = Math.round(parseFloat(value) * 100) / 100;
        var xsd = value.toString().split(".");
        if (xsd.length == 1) {
            value = value.toString() + ".00";
            return value;
        }
        if (xsd.length > 1) {
            if (xsd[1].length < 2) {
                value = value.toString() + "0";
            }
            return value;
        }
        return null;
    }
};

var TextUtil = {
    event: function () {
        $('.no-zh').on('input propertychange', function () {
            TextUtil.noEN(this);
        });
        $('.only-num').on('input propertychange', function () {
            TextUtil.onlyNum(this, $(this).attr('dec'));
        });
        $('.no-special').on('input propertychange', function () {
            TextUtil.noSpecial(this);
        });
    },
    notEntry: function ($mes) {
        var div;
        if ($mes && $mes.length > 0) {
            for (var i = 0; i < $mes.length; i++) {
                $($mes[i]).attr('disabled', true);
                div = $($mes[i]).find('input');
                if (div && div.length > 0) {
                    for (var j = 0; j < div.length; j++) {
                        $(div[j]).attr('disabled', true);
                    }
                }
            }
        }
    },
    entry: function ($mes) {
        var div;
        if ($mes && $mes.length > 0) {
            for (var i = 0; i < $mes.length; i++) {
                $($mes[i]).removeAttr('disabled');
                div = $($mes[i]).find('input');
                if (div && div.length > 0) {
                    for (var j = 0; j < div.length; j++) {
                        $(div[j]).removeAttr('disabled');
                    }
                }
            }
        }
    },
    parentWidth: function ($me) {
        $me = $me ? $me : '.div-text';
        $($me).each(function (i, dom) {
            $(dom).css('width', $(dom).width() - 6);
        });
    },
    notNullFlag: function ($tag) {
        $tag = $tag ? $tag : $('.not-null-flag');
        $($tag).each(function (i, dom) {
            if ($(dom).find('.red').length == 0) {
                $(dom).append('<span class="red"> *</span>');
            }
        });
    },
    notNull: function ($tags, callback) {
        if (!$tags || $tags.length < 1) {
            $tags = $('.not-null');
        }
        for (var i = 0; i < $tags.length; i++) {
            if (!$.trim($($tags[i]).val())) {
                var msg = $($tags[i]).attr('msg');
                msg = msg ? msg : $($tags[i]).attr('placeholder');
                if ($($tags[i]).get(0).tagName == 'SPAN' && $($tags[i]).find('input').length > 0) {
                    var flag = false;
                    $($tags[i]).find('input').each(function (i, dom) {
                        if ($(dom).is(":checked")) {
                            flag = true;
                        }
                    });
                    if (!flag) {
                        callback ? callback(msg) : AlertUtil.defaultAlert(msg);
                        return false;
                    }
                } else {
                    callback ? callback(msg) : AlertUtil.defaultAlert(msg);
                    return false;
                }
            }
        }
        return true;
    },
    notNullOne: function ($tags, message, callback) {
        if (!$tags || $tags.length < 1) {
            return true;
        }
        var msg = message ? message : '至少有一项不为空！';
        for (var i = 0; i < $tags.length; i++) {
            if ($.trim($($tags[i]).val())) {
                return true;
            }
        }
        callback ? callback(msg) : AlertUtil.defaultAlert(msg);
        return false;
    },
    onlyPhone: function (me) {
        var value = $(me).val();
        var msg = $(me).attr('reg-msg')
        msg = msg ? msg : '格式错误';
        if (!value) {
            AlertUtil.defaultAlert(msg);
            return false;
        }
        var reg = /^1[34578]\d{9}$/;
        if (reg.test(value)) {
            return true;
        }
        AlertUtil.defaultAlert(msg);
        return false;
    },
    onlycontact: function (me) {
        var value = $(me).val();
        var msg = $(me).attr('reg-msg')
        msg = msg ? msg : '格式错误';
        if (!value) {
            AlertUtil.defaultAlert(msg);
            return false;
        }
        var reg = /^1[34578]\d{9}$/;
        var guReg = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
        if (reg.test(value)) {
            return true;
        } else if (guReg.test(value)) {
            return true;
        }
        AlertUtil.defaultAlert(msg);
        return false;
    },
    onlyEmail: function (me) {
        var value = $(me).val();
        var msg = $(me).attr('reg-msg')
        msg = msg ? msg : '格式错误';
        if (!value) {
            AlertUtil.defaultAlert(msg);
            return false;
        }
        var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
        if (reg.test(value)) {
            return true;
        }
        AlertUtil.defaultAlert(msg);
        return false;
    },
    onlyNum: function (me, dec) {
        var value = $(me).val();
        if (!value) {
            return;
        }
        $(me).val(RegUtil.nan(value, dec, TextUtil.getFilters($(me).attr('filter'))));
    },
    onlyNumEvent: function () {
        $('.only-num').on('input propertychange', function () {
            TextUtil.onlyNum(this, $(this).attr('dec'));
        });
    },
    noSpecial: function (me) {
        var value = $(me).val();
        if (!value) {
            return;
        }
        var reg = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！%@#￥……&*（）——|{}【】‘；：”“'。，、？]");
        if (!reg.test(value)) {
            return;
        }
        $(me).val(RegUtil.reg(value, reg));
    },
    noSpecialEvent: function () {
        $('.no-special').on('input propertychange', function () {
            if ($.syncProcessSign) return;
            $.syncProcessSign = true;
            TextUtil.noSpecial(this);
            $.syncProcessSign = false;
        });
    },
    noEN: function (me) {
        var value = $(me).val();
        if (!value) {
            return;
        }
        var reg = new RegExp("[\\u4E00-\\u9FFF]+", "g");
        if (!reg.test(value)) {
            return;
        }
        $(me).val(RegUtil.reg(value, reg));
    },
    noENEvent: function () {
        $('.no-zh').on('input propertychange', function () {
            TextUtil.noEN(this);
        });
    },
    getFilters: function (filter) {
        var filters = [];
        if (filter) {
            var fit = filter.split('|');
            for (var v in fit) {
                filters.push(fit[v]);
            }
        }
        return filters;
    }
};

var RegUtil = {
    reg: function (value, reg) {
        if (!value || (value.length == 1 && reg.test(val))) {
            return '';
        }
        var val = '';
        for (var i = 0; i < value.length; i++) {
            val += value[i];
            reg.lastIndex = 0;
            if (reg.test(value[i])) {
                val = val.substring(0, val.length - 1);
                break;
            }
        }
        return val;
    },
    pattern: function (value, reg) {
        if (!value || (value.length == 1 && reg.test(val))) {
            return '';
        }
        var val = '';
        for (var i = 0; i < value.length; i++) {
            val += value[i];
            if (!reg.test(val)) {
                val = val.substring(0, val.length - 1);
                break;
            }
        }
        return val;
    },
    nan: function (value, dec, filters) {
        if (!value || (value.length == 1 && isNaN(value) && !RegUtil.filter(value, filters))) {
            return '';
        }
        var val = '';
        var flag = true;
        for (var i = 0; i < value.length; i++) {
            //小数点过滤
            if (dec && dec == 0 && value[i] == '.') {
                continue;
            }
            val += value[i];
            if (dec && dec == 1 && value[i] == '.') {
                continue;
            }
            //过滤设定字符
            if (RegUtil.filter(value[i], filters)) {
                continue;
            }
            //是否数字
            if (isNaN(value[i])) {
                if (!dec && flag && value[i] == '.') {
                    flag = false;
                    continue;
                }
                val = val.substring(0, val.length - 1);
                break;
            }
        }
        return val;
    },
    filter: function (value, filters) {
        if (filters) {
            for (var v in filters) {
                if (value == filters[v]) {
                    return true;
                }
            }
        }
        return false;
    }
};

var SortUtil = {
    orderNumPrivate: function (values) {
        values.sort(function (a, b) {
            var x = a.orderNum.split("."), y = b.orderNum.split(".");
            if (x.length > 1 && y.length > 1) {
                for (var i = 0; i < x.length || i < y.length; i++) {
                    if (x[i] == y[i]) {
                        continue;
                    }
                    return x[i] - y[i];
                }
                return 0;
            }
            return x[0] - y[0];
        });
    },
    dateObject: function (values, desc) {
        values.sort(function (a, b) {
            var x = a.updateDate ? a.updateDate : 0, y = b.updateDate ? b.updateDate : 0;
            if (desc) {
                return new Date(y).getTime() - new Date(x).getTime()
            }
            return new Date(x).getTime() - new Date(y).getTime()
        });
    }
};