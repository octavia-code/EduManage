// 访问路径第一层
var firstPath = '/account/login';
var vForgetPwd = new Vue({
    el: '#forgetPwd',
    data: function () {
        return {
            userName: '', password: '', password1: '', phone: '', msgCode: '', picCode: '',//绑定属性
            imgSrc: METHOD_URL + firstPath + '/forgetPwdCode/findVerifyCode' + METHOD_SUFFIX,
            loginNameFindErr: '', passwordTip: '6-16个字母、数字或者英文符号，区分大小写', passwordErr: '', passwordErr1: '',
            phoneErr: '', msgCodeErr: '', picCodeErr: '', message: '', //错图提示信息
            sendFlag: false,//检查手机号码格式，true为可以发送短信
            checkLoginNameFlag: true, checkPasswordFlag1: true, checkPasswordFlag2: true,
            checkCompareFlag: true, checkPhoneFlag: false, msgCodeFlag: false, picCodeFlag: false,//检查注册时，true可以注册,false不可以注册
            btnDisabled: false, //按钮不可用
            btnSendCode: '获取验证码',//验证码按钮
            InterValObj: '', //timer变量，控制时间
            count: 60,  //间隔函数，1秒执行
            curCount: ''//当前剩余秒数
        }
    }
});

/**
 * 检查用户名是否为空
 */
function checkLoginName() {
    if (vForgetPwd.userName == null || vForgetPwd.userName == '') {
        vForgetPwd.loginNameFindErr = '请输入用户名！';
        vForgetPwd.checkLoginNameFlag = false;
        return;
    } else {
        vForgetPwd.loginNameFindErr = '';
        vForgetPwd.checkLoginNameFlag = true;
    }
}

/**
 * 检查用户名是否已经存在
 */
function findLoginName() {
    if (vForgetPwd.userName == null || vForgetPwd.userName == '') {
        vForgetPwd.loginNameFindErr = '请输入用户名！';
        return;
    } else {
    }
    //设置参数
    var data = {userName: vForgetPwd.userName};
    //调动业务端方法
    var url = firstPath + '/checkByName';
    callAjaxPost(url, data, findLoginNameSuc);
}

function findLoginNameSuc(data) {
    //用户名已存在或后台异常直接返回
    if (data.obj === 'error') {
        vForgetPwd.loginNameFindErr = '用户名检测异常，请稍后再试！';
        vForgetPwd.checkLoginNameFlag = false;
        return;
    }
    if (data.obj === 'exist') {
        vForgetPwd.loginNameFindErr = '用户名已经存在，请重新输入！';
        vForgetPwd.checkLoginNameFlag = false;
        return;
    }
    if (data.obj === 'null') {
        vForgetPwd.loginNameFindErr = '';
        vForgetPwd.checkLoginNameFlag = true;
    }
}


/**
 * 检验手机号码格式
 * 当手机号码格式正确，设置sendFlag为true
 */
function checkPhone() {
    //更新到2018年5月，支持最新的166号段
    var result = VERIFY_PHONE.test(vForgetPwd.phone);
    if (vForgetPwd.phone.trim() == '') {
        vForgetPwd.phoneErr = "请输入手机号码";
        return;
    } else if (!result) {    //手机格式不正确
        vForgetPwd.phoneErr = "请输入正确手机号码";
        return;
    } else if (result) {   //手机格式正确
        vForgetPwd.phoneErr = "";
        vForgetPwd.sendFlag = true;
        // checkNameByPhone();     //检查手机号码与用户名是否匹配
    } else {
        alert("未知错误");
    }
}

/**
 * 检查手机号码与用户名是否匹配
 */
function checkNameByPhone() {
    //检查用户名是否为空
    checkLoginName();
    //检验手机号码格式
    vForgetPwd.sendFlag = false;
    checkPhone();
    if (vForgetPwd.sendFlag && vForgetPwd.checkLoginNameFlag) {
        //设置参数
        var data = {
            phone: vForgetPwd.phone,
            userName: vForgetPwd.userName
        };
        //调动业务端方法
        var url = firstPath + '/checkNameByPhone';
        callAjaxPost(url, data, checkNameByPhoneSuc);
    }
}

function checkNameByPhoneSuc(data) {
    //用户名已存在或后台异常直接返回
    if (data.obj === 'error') {
        vForgetPwd.phoneErr = '手机号检测失败，请稍后再试！';
        vForgetPwd.checkPhoneFlag = false;
        return;
    }
    if (data.obj === 'mismatch') {
        vForgetPwd.phoneErr = '此手机号未绑定当前用户名！';
        vForgetPwd.checkPhoneFlag = false;
        return;
    }
    if (data.obj === 'match') {
        vForgetPwd.phoneErr = '';
        vForgetPwd.checkPhoneFlag = true;
        //发送短信
        sendMessage();
    }
}


/**
 * 检查短信验证码是否为空
 */
function checkMsgCode() {
    if (vForgetPwd.msgCode == null || vForgetPwd.msgCode == '') {
        vForgetPwd.msgCodeErr = '请输入短信验证码！';
        vForgetPwd.msgCodeFlag = false;
    } else {
        vForgetPwd.msgCodeErr = '';
        vForgetPwd.msgCodeFlag = true;
    }
}

/**
 * 检查图形验证码是否为空
 */
function checkImgCode() {
    if (vForgetPwd.picCode == "" || vForgetPwd.picCode == null) {
        vForgetPwd.picCodeErr = '请输入图形验证码！';
        vForgetPwd.picCodeFlag = false;
    } else {
        vForgetPwd.picCodeErr = '';
        vForgetPwd.picCodeFlag = true;
    }
}


/**
 * 检查密码格式
 */
function checkPassword() {
    if (vForgetPwd.password == null || vForgetPwd.password == '') {
        vForgetPwd.passwordErr = '请输入密码！';
        return;
    }
    if (vForgetPwd.password.length < 6 || vForgetPwd.password.length > 16) {
        vForgetPwd.passwordErr = '密码长度在6~16之间';
        vForgetPwd.checkPasswordFlag1 = false;
    } else {
        vForgetPwd.passwordErr = '';
        vForgetPwd.checkPasswordFlag1 = true;
    }
    //支持字母、数字、标点符号、特殊字符
    //var reg=/^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])|(?=.*[A-Z])(?=.*[a-z])(?=.*[^A-Za-z0-9])|(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])|(?=.*[a-z])(?=.*[0-9])(?=.*[^A-Za-z0-9])).{6,}|(?:(?=.*[A-Z])(?=.*[a-z])|(?=.*[A-Z])(?=.*[0-9])|(?=.*[A-Z])(?=.*[^A-Za-z0-9])|(?=.*[a-z])(?=.*[0-9])|(?=.*[a-z])(?=.*[^A-Za-z0-9])|(?=.*[0-9])(?=.*[^A-Za-z0-9])|).{6,16}$/
    //密码必须为6-18位字母、数字
    // var reg=/^(?![^a-zA-Z]+$)(?!\D+$)/
    // 密码必须为6-16位字母、数字、特殊符号的   数字格式2 (?=.*[0-9])   特殊符号格式2(?=.*[^a-zA-Z0-9])
    /*var reg = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[~!@#$%^&*()_+`\-={}:";'<>?,.\/]).{6,16}$/;
    if (!reg.test(vForgetPwd.password)) {
        vForgetPwd.passwordErr = '同时包含数字，字母，特殊符号';
        vForgetPwd.checkPasswordFlag2=false;
    } else {
        vForgetPwd.passwordErr = '';
        vForgetPwd.checkPasswordFlag2=true;
    }*/
//    vForgetPwd.passwordErr = '';
    vForgetPwd.checkPasswordFlag2 = true;
}

/**
 * 检查两次密码输入是否相同
 */
function compare() {
    //密码验证不能为空
    if (vForgetPwd.password1 == null || vForgetPwd.password1 == '') {
        vForgetPwd.passwordErr1 = '请再次输入密码';
        return;
    }
    if (vForgetPwd.password !== vForgetPwd.password1) {
        vForgetPwd.passwordErr1 = '密码不一致，请重新输入';
        vForgetPwd.checkCompareFlag = false;
    } else {
        vForgetPwd.passwordErr1 = '';
        vForgetPwd.checkCompareFlag = true;
    }
}


/**
 * 1,前端验证注册信息
 */
function checkForget() {
    //1.1，验证输入数据格式
    checkLoginName();
    checkPhone();
    checkMsgCode();
    checkImgCode();
    checkPassword();
    compare();
//    checkNameByPhone();
    if (vForgetPwd.checkPhoneFlag == false) {
        vForgetPwd.msgCodeErr = '请获取短信验证码！';
    }
    //1.2，验证格式Flag,全为true才能进行下一步操作
    if (vForgetPwd.checkLoginNameFlag == false || vForgetPwd.checkPasswordFlag1 == false || vForgetPwd.checkPasswordFlag2 == false ||
        vForgetPwd.checkCompareFlag == false || vForgetPwd.checkPhoneFlag == false || vForgetPwd.msgCodeFlag == false ||
        vForgetPwd.picCodeFlag == false) {
        return;
    }
    console.log("222");
    //1.3，后端验证图形验证码
    comparePicCode();
}

/**
 * 2，后端验证注册信息
 * 2.1,验证图形验证码
 */
function comparePicCode() {
    //设置参数
    var data = {picCode: vForgetPwd.picCode};
    //调动业务端方法
    var url = firstPath + '/forgetPwdCode/comparePicCode';
    callAjaxPost(url, data, comparePicCodeSuc);
}

function comparePicCodeSuc(data) {
    console.log(data);
    //判断验证码是否正确，错误直接返回
    if (data.obj === "fail") {
        vForgetPwd.picCodeErr = '图形验证码错误，请重新输入！';
        vForgetPwd.picCode = '';
        refreshForget();//刷新验证码
        return;
    }
    if (data.obj === "error") {
        vForgetPwd.picCodeErr = '验证码检测异常，请稍后再试！';
        vForgetPwd.picCode = '';
        refreshForget();//刷新验证码
        return;
    }
    if (data.obj === "success") {
        //图形验证码填写正确，调用验证短信验证码方法
        compareSmsCode();
    }
}


/**
 * 2.2验证短信验证码是否填写正确
 */
function compareSmsCode() {
    //设置参数
    var data = {msgCode: vForgetPwd.msgCode};
    //调动业务端方法
    var url = firstPath + '/forgetPwdPhone/compareSmsCode';
    callAjaxPost(url, data, compareSmsCodeSuc);
}

function compareSmsCodeSuc(data) {
    console.log(data);
    //短信验证码超时或者不正确或后台异常直接返回
    if (data.obj === "error") {
        vForgetPwd.msgCodeErr = '验证码检测异常，请稍后再试！';
        vForgetPwd.msgCode = '';
        return;
    }
    if (data.obj === "timeOut") {
        vForgetPwd.msgCodeErr = '短信验证码已失效，请重新获取!';
        vForgetPwd.msgCode = '';
        return;
    }
    if (data.obj === "msgCodeErr") {
        vForgetPwd.msgCodeErr = '短信验证码错误！';
        vForgetPwd.msgCode = '';
        return;
    }
    if (data.obj === "success") {
        //短信验证码验证成功，调用重置密码方法
        forgetPwd();
    }
}


/**
 * 3,重置密码
 */
function forgetPwd() {
    //设置参数
    var data = {
        userName: vForgetPwd.userName,
        phone: vForgetPwd.phone,
        password: vForgetPwd.password
    };
    //调动业务端方法
    var url = firstPath + '/forgetPwdByPhone';
    callAjaxPost(url, data, forgetPwdSuc);
}

function forgetPwdSuc(data) {
    //失败或后台异常，直接返回
    if (data.obj === "error" || data.obj === "fail") {
        vForgetPwd.loginNameFindErr = '重置密码失败，请稍后再试！';
        return;
    }
    if (data.obj === "phoneError") {
        vForgetPwd.phoneErr = '请填写获取短信的手机号码！';
        return;
    }
    if (data.obj === "pwdError") {
        vForgetPwd.passwordErr = '新密码不能是旧密码！';
        return;
    }
    if (data.obj === "success") {
        //打开Vue对话框
        vForgetPwd.$Modal.success({
            content: '<p>重置密码成功</p>',
            okText: '跳转登录',
            onOk: function () {
                //重置登陆信息
                resetForgetParam();
                //清除后台session中的验证码,防止短信验证码还有效
                var url = firstPath + '/clearSession';
                callAjaxGetNoParamsAndFun(url);
                window.location.href = "/";
            }
        });
    }
}


/**
 * 清除参数
 */
function resetForgetParam() {
    console.log("重置参数");
    vForgetPwd.userName = '';
    vForgetPwd.password = '';
    vForgetPwd.password1 = '';
    vForgetPwd.phone = '';
    vForgetPwd.msgCode = '';
    vForgetPwd.msgCodeErr = '';
    window.clearInterval(InterValObj);
    vForgetPwd.btnSendCode = '获取验证码';
    vForgetPwd.btnDisabled = true;
    vForgetPwd.picCode = '';
}

/**
 * 发送短信获取验证码
 */
function sendMessage() {
    //设置当前剩余秒数
    curCount = vForgetPwd.count;
    InterValObj = setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
    var data = {phone: vForgetPwd.phone};
    //调动业务端方法
    var url = firstPath + "/forgetPwdPhone/sendMessage";
    callAjaxPost(url, data, sendMessageSus);
}

function sendMessageSus(data) {
    console.log(data);
    //发送
    if (data.obj === "error" || data.obj === "fail") {
        vForgetPwd.msgCodeErr = '短信发送异常，请稍后再试！';
//        alert("测试用验证码：" + data.msgCode);//正式上线时，注意去掉这行
        return;
    }
    if (data.obj === "1016") {
        vForgetPwd.msgCodeErr = '手机号格式错误';
        return;
    }
    if (data.obj === "1023") {
        vForgetPwd.msgCodeErr = '30秒内下发短信条数超过上限';
        return;
    }
    if (data.obj === "1024") {
        vForgetPwd.msgCodeErr = '1小时内下发短信条数超过上限';
        return;
    }
    if (data.obj === "1025") {
        vForgetPwd.msgCodeErr = '今日发短信条数超过上限';
        return;
    }
    if (data.obj === "1031") {
        vForgetPwd.msgCodeErr = '短信发送功能失效，请稍后再试';
        return;
    }
    if (data.obj === "success") {
        console.log("发送成功！")
    }
}


/**
 * timer处理函数
 * @constructor
 */
function SetRemainTime() {
    if (curCount == 0) {
        window.clearInterval(InterValObj);//停止计时器
        vForgetPwd.btnDisabled = false;//短信按钮可用
        vForgetPwd.btnSendCode = "重新获取";
    }
    else {
        vForgetPwd.btnDisabled = true;//短信按钮不可用
        vForgetPwd.btnSendCode = curCount + "秒后重发";
        curCount--;//当前秒数-1
    }
}

/**
 * 刷新注册验证码
 */
function refreshForget() {
    var url = firstPath + "/forgetPwdCode/findVerifyCode.sose?number=" + Math.random();
    //attr() 方法设置或返回被选元素的属性和值。
    $("#forgetImg").attr("src", url);
}

/**
 * 键盘输入事件绑定到特定按钮，事件
 * onkeydown 这个事件在用户按下任何键盘键（包括系统按钮，如箭头键和功能键）时发生
 * onkeyup   这个事件在用户放开任何先前按下的键盘键时发生。
 * onkeypress 这个事件在用户按下并放开任何字母数字键时发生。系统按钮（例如，箭头键和功能键）无法得到识别
 */
document.onkeydown = function (event) {
    /*IE用event.keCode方法获取当前被按下的键盘按键值
    而NetScape/FireFox/Opera用的则是event.which*/
    var e = event || window.event;
    var keyCode = e.keyCode || e.which;
    switch (keyCode) {
        //回车
        case 13:
            checkForget();
            break;
        default:
            break;
    }
};