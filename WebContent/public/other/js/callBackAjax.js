// 获取设置在本地存储中token
// var token = window.localStorage.getItem('Authorization');
var token = window.cookieUtil.get('Authorization');

//指定预处理参数选项的函数
$.ajaxPrefilter(function (options, originalOptions, jqXHR) {
    // options对象 包括accepts、crossDomain、contentType、url、async、type、headers、error、dataType等许多参数选项
    // originalOptions对象 就是你为$.ajax()方法传递的参数对象，也就是 { url: "/index.php" }
    // jqXHR对象 就是经过jQuery封装的XMLHttpRequest对象(保留了其本身的属性和方法)

//	options.type = "GET"; // 将请求方式改为GET
//	options.headers = { }; // 清空自定义的请求头
});

//设置AJAX的全局默认选项
$.ajaxSetup({
    headers: {
//        Authorization: token,
        'X-Requested-With': 'XMLHttpRequest',
    },
    complete: function (XMLHttpRequest, textStatus) {
        // 通过XMLHttpRequest取得响应头，REDIRECT
        let redirect = XMLHttpRequest.getResponseHeader("REDIRECT");//若HEADER中含有REDIRECT说明后端想重定向
        if (redirect === "REDIRECT") {
            let win = window;
            while (win != win.top) {
                win = win.top;
            }
            debugger
            url = XMLHttpRequest.getResponseHeader("CONTENTPATH");
            // 删除过期的cookie
            window.cookieUtil.del("Authorization");
            alert('重新登录：' + url);
            noticeInfo('登录信息已过期，请重新登录');
            win.location.href = url;
            return;
        }
        // 获取新的token
        let token = XMLHttpRequest.getResponseHeader("TOKEN");
        if (isNotEmpty(token)) {
            // 将token放入cookie中
            alert(token);
            // window.cookieUtil.del("Authorization");
            // 重新设置cookie，将作用域设为'/'
            window.cookieUtil.set("Authorization", token, '2h', '/');
        }
    }
});

/**
 * Ajax 无参无回调函数get请求函数
 * 一般用于清除后台信息
 * @param url
 *            请求链接
 * @constructor
 */
function callAjaxGetNoParamsAndFun(url) {
    console.log('请求路径：' + METHOD_URL + url + METHOD_SUFFIX);
    $.ajax({
        url: METHOD_URL + url + METHOD_SUFFIX,
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        method: "get",
        error: function (data) {
            console.log('Ajax请求错误！' + url);
            console.log(data);
            errorClass(data);
        }
    })
}

/**
 * Ajax get请求函数
 *
 * @param url
 *            请求链接
 * @param funcSuc
 *            请求成功的回调函数
 * @constructor
 */
function callAjaxGetNoParam(url, funcSuc) {
    console.log('请求路径：' + METHOD_URL + url + METHOD_SUFFIX);
    $.ajax({
        url: METHOD_URL + url + METHOD_SUFFIX,
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        method: "get",
        dataType: "json",
        success: function (data) {
            sucClass(data, funcSuc);
        },
        error: function (data) {
            console.log('Ajax请求错误！' + url);
            console.log(data);
            errorClass(data);
        },

    })
}

/**
 * Ajax post请求函数
 *
 * @param url
 *            请求链接
 * @param params
 *            请求参数
 * @param funcSuc
 *            请求成功的回调函数
 * @constructor
 */
function callAjaxPost(url, params, funcSuc) {
    console.log('请求路径：' + METHOD_URL + url + METHOD_SUFFIX);
    $.ajax({
        url: METHOD_URL + url + METHOD_SUFFIX,
        type: "post",
        async: true,// 异步
        xhrFields: {
            withCredentials: true
        },// 携带cookie
        crossDomain: true,// 跨域
        data: JSON.stringify(params),
        contentType: "application/json; charset=utf-8",// 参数格式
        dataType: "json", // 返回JSON数据
        success: function (data) {
            sucClass(data, funcSuc);
        },
        error: function (data) {
            console.log('Ajax请求错误！	' + url);
            console.log(data);
            // noticeError(this, 'Ajax请求错误！', data);
            errorClass(data);
        }
    })
}

/**
 * 上传文件表单
 * @param url 上传路径
 * @param formData 文件表单
 * @param funcSuc 成功回调函数
 */
function callAjaxFile(url, formData, funcSuc) {
    $.ajax({
        type: "post",
        url: url,
        data: formData,
        processData: false,// 告诉jQuery不要去处理发送的数据
        contentType: false,// 告诉jQuery不要去设置Content-Type请求头
        dataType: 'json',
        success: function (data) {
            funcSuc(data)
        },
        error: function (error) {
            console.log('Ajax请求错误！');
            console.log(data);
            errorClass(data);
        }
    });
}


/**
 * 表单提交方式，暂不使用
 * @param url
 * @param params
 * @param funcSuc
 */
function callAjaxPostForm(url, params, funcSuc) {
    console.log('请求路径：' + METHOD_URL + url + METHOD_SUFFIX);
    $.ajax({
        async: true,
        url: METHOD_URL + url + METHOD_SUFFIX,
        type: 'POST',
        contentType: 'application/x-www-form-urlencoded; charset=utf-8',
        data: params,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            if (data.info === 'error') {
                console.log('系统后台错误！');
                noticeError(G_vCall, '系统后台错误！', data.errorMsg);
                // errorClass(data);
                return;
            }
            //调用成功
            funcSuc(data)
        },
        error: function (data) {
            console.log('Ajax请求错误！');
            console.log(data);
            // noticeError(this, 'Ajax请求错误！', data);
            errorClass(data);
        }
    });
}


/**
 * ajax请求成功调用函数
 * @param data 返回参数
 * @param funcSuc 成功回调函数
 */
function sucClass(data, funcSuc) {
    // console.log(data);
    if (data.code === 500) {
        console.log('系统后台错误！');
        noticeError('系统后台错误！', data.msg);
        // errorClass(data.code);
        return;
    }
    //调用成功
    funcSuc(data)
}

function errorClass(code) {
    switch (code) {
        case 403:
            // parent.location.href = "error/403.html";
            break;
        case 404:
            // parent.location.href = "/error/404.html";
            break;
        case 500:
            // parent.location.href = "/error/500.html";
            break;
        default:
            break;
    }
}