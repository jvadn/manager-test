var SUCCESS = 200;
var ERROR = 400;
var OTHER = 401;
var contentType_param = "application/x-www-form-urlencoded";
var contentType_json = "application/json";

AjaxUtil = {
    json: {
        encode: function (data) {
            return JSON.stringify(data);
        },
        encode_param: function (data) {
            for (var key in data) {
                if (typeof data[key] == 'object') {
                    data[key] = JSON.stringify(data[key]);
                }
            }
            return data;
        },
        length: function (value) {
            if (!value) {
                return 0;
            }
            var i = 0;
            for (var v in value) {
                i++;
            }
            return i;
        }
    },
    request: {
        post_param: function (url, data, callBack, errorBack, async) {
            AjaxUtil.ajax(url, AjaxUtil.json.encode_param(data), contentType_param, 'POST', callBack, errorBack, async == false ? async : true);
        },
        post: function (url, data, callBack, errorBack, async) {
            AjaxUtil.ajax(url, AjaxUtil.json.encode(data), contentType_json, 'POST', callBack, errorBack, async == false ? async : true);
        },
        get: function (url, callBack, errorBack, async) {
            AjaxUtil.ajax(url, null, contentType_json, 'GET', callBack, errorBack, async == false ? async : true, false);
        }
    },
    ajax: function (url, data, contentType, method, callBack, errorBack, async, cache, beforeSend,
                    complete) {
        $.ajax({
            url: url,
            //contentType : "application/json",
            contentType: contentType,
            dataType: "json",
            method: method,
            cache: cache == false ? false : true,
            data: data,
            async: async,
            success: function (obj) {
                if (obj && callBack) {
                    if (obj.result || obj.result == 'null') {
                        obj.result = JSON.parse(obj.result);
                    }
                    //针对messageObj对象,如无此对象应删除
                    if (obj.state == false) {
                        try {
                            errorBack(obj);
                        } catch (e) {
                            AjaxUtil.alert('操作失败！');
                        }
                        return;
                    }
                    // switch (obj.code) {
                    // case SUCCESS:
                    // 	callBack(obj);
                    // 	break;
                    // case ERROR:
                    // case OTHER:
                    // 	errorBack(obj);
                    // 	break;
                    // default:
                    // 	break;
                    // }
                    callBack(obj);
                } else {
                    try {
                        errorBack(obj);
                    } catch (e) {
                        AjaxUtil.alert('请求失败！');
                    }
                }
            },
            error: function (request, textStatus, errorThrown) {
                if(url.indexOf('https://api.weixin.qq.com') != -1){
                    return;
                }
                console.error(url);
                AjaxUtil.alert('请求失败！');
            },
            beforeSend: function (request) { // 发送请求之前
                if (beforeSend)
                    beforeSend(request);
            },
            complete: function (request) { // 请求完成之后
                if (complete)
                    complete(request);
            }
        });
    },
    alert: function (msg) {
        try {
            AlertUtil.defaultAlert(msg);
        } catch (e) {
            alert(msg);
        }
    }
}