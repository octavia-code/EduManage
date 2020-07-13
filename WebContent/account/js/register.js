// 访问路径第一层
var firstPath = '/account/login';
var vRegister = new Vue({
    el: '#register',
    data: function () {
        return {
            type: "phone",// phone:手机注册     email:邮箱注册
            bindShow: true,// true:手机注册     false:邮箱注册
            userName: '', password: '', password1: '', phone: '', email: '',
            msgCode: '', emailCode: '', picCode: '',//绑定属性
            imgSrc: METHOD_URL + firstPath + '/registerCode/findVerifyCode' + METHOD_SUFFIX,
            loginNameFindErr: '', passwordTip: '6-16个字母、数字或者英文符号，区分大小写', passwordErr: '', passwordErr1: '',
            phoneErr: '', msgCodeErr: '', emailErr: '', emailCodeErr: '', picCodeErr: '', typeErr: '', message: '', //错图提示信息
            sendPhoneFlag: false,// 是否可以发送短信验证码
            sendEmailFlag: false,// 是否可以发送邮箱验证码
            checkLoginNameFlag: true, checkPasswordFlag1: true, checkPasswordFlag2: true,
            checkCompareFlag: true, checkPhoneFlag: false, msgCodeFlag: false,
            checkEmailFlag: false, emailCodeFlag: false, picCodeFlag: false,//检查注册时，true可以注册,false不可以注册
            btnDisabled: false, //按钮不可用
            btnSendCode: '获取验证码',//验证码按钮
            InterValObj: '', //timer变量，控制时间
            count: 60,  //间隔函数，1秒执行
            curCount: ''//当前剩余秒数
        }
    },
    //监听器
    watch: {     //配置监视
        type: function (value) { //type发生变化
            this.bindShow = (this.type === "phone" ? true : false);
            console.log('注册类型：' + this.bindShow);
        }
    }
});

/**
 * 检查用户名是否已经存在
 */
function findLoginName() {
    if (vRegister.userName === null || vRegister.userName === '') {
        vRegister.loginNameFindErr = '请输入用户名！';
        return;
    }
    //设置参数
    let data = {userName: vRegister.userName};
    //调动业务端方法
    let url = firstPath + '/checkByName';
    callAjaxPost(url, data, findLoginNameSuc);
}

function findLoginNameSuc(data) {
    //用户名已存在或后台异常直接返回
    if (data.obj === 'error') {
        vRegister.loginNameFindErr = '用户名检测异常，请稍后再试！';
        vRegister.checkLoginNameFlag = false;
        return;
    }
    if (data.obj === 'exist') {
        vRegister.loginNameFindErr = '用户名已经存在，请重新输入！';
        vRegister.checkLoginNameFlag = false;
        return;
    }
    if (data.obj === 'null') {
        vRegister.loginNameFindErr = '';
        vRegister.checkLoginNameFlag = true;
    }
}

/**
 * 检查密码格式
 */
function checkPassword() {
    if (vRegister.password === null || vRegister.password === '') {
        vRegister.passwordErr = '请输入密码！';
        return;
    }
    if (vRegister.password.length < 6 || vRegister.password.length > 16) {
        vRegister.passwordErr = '密码长度在6~16之间';
        vRegister.checkPasswordFlag1 = false;
    } else {
        vRegister.passwordErr = '';
        vRegister.checkPasswordFlag1 = true;
    }
    //支持字母、数字、标点符号、特殊字符
    //let reg=/^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])|(?=.*[A-Z])(?=.*[a-z])(?=.*[^A-Za-z0-9])|(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])|(?=.*[a-z])(?=.*[0-9])(?=.*[^A-Za-z0-9])).{6,}|(?:(?=.*[A-Z])(?=.*[a-z])|(?=.*[A-Z])(?=.*[0-9])|(?=.*[A-Z])(?=.*[^A-Za-z0-9])|(?=.*[a-z])(?=.*[0-9])|(?=.*[a-z])(?=.*[^A-Za-z0-9])|(?=.*[0-9])(?=.*[^A-Za-z0-9])|).{6,16}$/
    //密码必须为6-18位字母、数字
    // let reg=/^(?![^a-zA-Z]+$)(?!\D+$)/
    // 密码必须为6-16位字母、数字、特殊符号的   数字格式2 (?=.*[0-9])   特殊符号格式2(?=.*[^a-zA-Z0-9])
    /*let reg = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[~!@#$%^&*()_+`\-={}:";'<>?,.\/]).{6,16}$/;
    if (!reg.test(vRegister.password)) {
        vRegister.passwordErr = '同时包含数字，字母，特殊符号';
        vRegister.checkPasswordFlag2=false;
    } else {
        vRegister.passwordErr = '';
        vRegister.checkPasswordFlag2=true;
    }*/
//    vRegister.passwordErr = '';
    vRegister.checkPasswordFlag2 = true;
}

/**
 * 检查两次密码输入是否相同
 */
function compare() {
    //密码验证不能为空
    if (vRegister.password1 == null || vRegister.password1 == '') {
        vRegister.passwordErr1 = '请再次输入密码！';
        return;
    }
    if (vRegister.password !== vRegister.password1) {
        vRegister.passwordErr1 = '密码不一致，请重新输入！';
        vRegister.checkCompareFlag = false;
    } else {
        vRegister.passwordErr1 = '';
        vRegister.checkCompareFlag = true;
    }
}

/**
 * 检验手机号码格式
 * 当手机号码格式正确，设置sendPhoneFlag为true
 */
function checkPhone() {
    let result = VERIFY_PHONE.test(vRegister.phone);
    if (vRegister.phone.trim() == '') {
        vRegister.phoneErr = "请输入手机号码！";
        return;
    } else if (!result) {    //手机格式不正确
        vRegister.phoneErr = "请输入正确手机号码！";
        return;
    } else if (result) {   //手机格式正确
        vRegister.phoneErr = "";
        vRegister.sendPhoneFlag = true;
        // findByPhone();		//检查手机号码是否已经存在
    }
}

/**
 * 检查手机号码是否已经存在
 * 1,设置sendPhoneFlag为false
 * 2,检验手机号码格式
 * 3,当手机号码格式正确，向后端发送请求
 */
function findByPhone() {
    vRegister.sendPhoneFlag = false;
    checkPhone();
    if (vRegister.sendPhoneFlag) {
        //设置参数
        let data = {phone: vRegister.phone};
        //调动业务端方法
        let url = firstPath + '/findByPhone';
        callAjaxPost(url, data, findByPhoneSuc);
    }
}

function findByPhoneSuc(data) {
    if (data.obj === 'exist' || data.obj === 'error') {
        vRegister.phoneErr = '手机号已经存在，请重新输入！';
        vRegister.checkPhoneFlag = false;
        return;
    }
    if (data.obj === 'null') {
        vRegister.phoneErr = '';
        vRegister.checkPhoneFlag = true;
        //发送短信
        sendMessage();
    }
}

/**
 * 检查短信验证码
 */
function checkMsgCode() {
    if (vRegister.msgCode === null || vRegister.msgCode === '') {
        vRegister.msgCodeErr = '请输入短信验证码！';
        vRegister.msgCodeFlag = false;
    } else {
        vRegister.msgCodeErr = '';
        vRegister.msgCodeFlag = true;
    }
}


/**
 * 检验邮箱格式
 * 当邮箱格式正确，设置sendEmailFlag为true
 */
function checkEmail() {
    let result = VERIFY_EMAIL.test(vRegister.email);
    if (vRegister.email.trim() === '') {
        vRegister.emailErr = "请输入邮箱！";
        return;
    } else if (!result) {
        vRegister.emailErr = "请输入正确邮箱！";
        return;
    } else if (result) {
        vRegister.emailErr = "";
        vRegister.sendEmailFlag = true;
    }
}

/**
 * 检查邮箱是否已经存在
 * 1,设置sendEmailFlag为false
 * 2,检验邮箱格式
 * 3,当邮箱格式正确，向后端发送请求
 */
function findByEmail() {
    vRegister.sendEmailFlag = false;
    checkEmail();
    if (vRegister.sendEmailFlag) {
        //设置参数
        let data = {email: vRegister.email};
        //调动业务端方法
        let url = firstPath + '/findByEmail';
        callAjaxPost(url, data, findByEmailSuc);
    }
}

function findByEmailSuc(data) {
    if (data.obj === 'exist' || data.obj === 'error') {
        vRegister.emailErr = '邮箱已经存在，请重新输入！';
        vRegister.checkEmailFlag = false;
        return;
    }
    if (data.obj === 'null') {
        vRegister.emailErr = '';
        vRegister.checkEmailFlag = true;
        //发送邮箱
        sendEmail();
    }
}

/**
 * 检查邮箱验证码
 */
function checkEmailCode() {
    if (vRegister.emailCode === null || vRegister.emailCode === '') {
        vRegister.emailCodeErr = '请输入邮箱验证码！';
        vRegister.emailCodeFlag = false;
    } else {
        vRegister.emailCodeErr = '';
        vRegister.emailCodeFlag = true;
    }
}


/**
 * 检查图形验证码
 */
function checkImgCode() {
    if (vRegister.picCode === "" || vRegister.picCode === null) {
        vRegister.picCodeErr = '请输入图形验证码！';
        vRegister.picCodeFlag = false;
    } else {
        vRegister.picCodeErr = '';
        vRegister.picCodeFlag = true;
    }
}

/**
 * 1,前端验证注册信息
 */
function checkRegister() {
    //1.1，验证输入数据格式
    findLoginName();
    checkPassword();
    compare();
    // 手机注册
    if (vRegister.bindShow) {
        checkPhone();
        checkMsgCode();
    } else {
        // 邮箱注册
        checkEmail();
        checkEmailCode();
    }
    checkImgCode();
    //1.2，验证格式Flag,全为true才能进行下一步操作
    if (vRegister.checkLoginNameFlag == false || vRegister.checkPasswordFlag1 == false || vRegister.checkPasswordFlag2 == false ||
        vRegister.checkCompareFlag == false || vRegister.picCodeFlag == false) {
        return;
    }
    if (vRegister.bindShow) {
        if (vRegister.checkPhoneFlag == false || vRegister.msgCodeFlag == false) {
            return;
        }
    } else {
        if (vRegister.checkEmailFlag == false || vRegister.emailCodeFlag == false) {
            return;
        }
    }
    //1.3，后端验证图形验证码
    comparePicCode();
}

/**
 * 2，后端验证注册信息
 * 2.1,验证图形验证码
 */
function comparePicCode() {
    //设置参数
    let data = {picCode: vRegister.picCode};
    //调动业务端方法
    let url = firstPath + '/registerCode/comparePicCode';
    callAjaxPost(url, data, comparePicCodeSuc);
}

function comparePicCodeSuc(data) {
    //判断验证码是否正确，错误直接返回
    if (data.obj === "fail") {
        vRegister.picCodeErr = '图形验证码错误，请重新输入！';
        vRegister.picCode = '';
        refreshRegister();//刷新验证码
        return;
    }
    if (data.obj === "error") {
        vRegister.picCodeErr = '验证码检测异常，请稍后再试！';
        vRegister.picCode = '';
        refreshRegister();//刷新验证码
        return;
    }
    if (data.obj === "success") {
        vRegister.picCodeErr = '';
        //图形验证码填写正确，调用验证短信验证码方法
        if (vRegister.bindShow) {
            compareSmsCode();
        } else {
            compareEmailCode();
        }
    }
}

/**
 * 2.2验证短信验证码是否填写正确
 */
function compareSmsCode() {
    //设置参数
    let data = {msgCode: vRegister.msgCode};
    //调动业务端方法
    let url = firstPath + '/registerPhone/compareSmsCode';
    callAjaxPost(url, data, compareSmsCodeSuc);
}

function compareSmsCodeSuc(data) {
    //短信验证码超时或者不正确或后台异常直接返回
    if (data.obj === "error") {
        vRegister.msgCodeErr = '验证码检测异常，请稍后再试！';
        vRegister.msgCode = '';
        return;
    }
    if (data.obj === "timeOut") {
        vRegister.msgCodeErr = '短信验证码已失效，请重新获取!';
        vRegister.msgCode = '';
        return;
    }
    if (data.obj === "msgCodeErr") {
        vRegister.msgCodeErr = '短信验证码错误！';
        vRegister.msgCode = '';
        return;
    }
    if (data.obj === "success") {
        //短信验证码验证成功，调用注册方法
        register();
    }
}

/**
 * 2.2验证邮箱验证码是否填写正确
 */
function compareEmailCode() {
    //设置参数
    let data = {emailCode: vRegister.emailCode};
    //调动业务端方法
    let url = firstPath + '/registerEmail/compareEmailCode';
    callAjaxPost(url, data, compareEmailCodeSuc);
}

function compareEmailCodeSuc(data) {
    //邮箱验证码超时或者不正确或后台异常直接返回
    if (data.obj === "error") {
        vRegister.emailCodeErr = '验证码检测异常，请稍后再试！';
        vRegister.emailCode = '';
        return;
    }
    if (data.obj === "timeOut") {
        vRegister.emailCodeErr = '邮箱验证码已失效，请重新获取!';
        vRegister.emailCode = '';
        return;
    }
    if (data.obj === "emailCodeErr") {
        vRegister.emailCodeErr = '邮箱验证码错误！';
        vRegister.emailCode = '';
        return;
    }
    if (data.obj === "success") {
        //验证码验证成功，调用注册方法
        register();
    }
}

/**
 * 3.注册
 */
function register() {
    //设置参数
    let data = {
        userName: vRegister.userName,
        password: vRegister.password,
        phone: vRegister.phone,
        email: vRegister.email,
        type: vRegister.type
    };
    //调动业务端方法
    let url = firstPath + '/accountRegister';
    callAjaxPost(url, data, registerSuc);
}

function registerSuc(data) {
    //注册失败或后台异常，直接返回
    if (data.obj === "fail" || data.obj === "error") {
        vRegister.loginNameFindErr = '注册失败，请重新注册！';
        return;
    }
    if (data.obj === "phoneError") {
        vRegister.phoneErr = '请填写获取短信的手机号码！';
        return;
    }
    if (data.obj === "emailError") {
        vRegister.emailErr = '请填写获取验证码的邮箱！';
        return;
    }
    if (data.obj === "success") {
        //打开Vue对话框
        vRegister.$Modal.success({
            content: '<p>注册成功</p>',
            okText: '跳转登录',
            onOk: function () {
                //重置登陆信息
                resetRegisterParam();
                //清除后台session中的数据
                let url = firstPath + '/clearSession';
                callAjaxGetNoParamsAndFun(url);
                window.location.href = "/";
            }
        });
    }
}


/**
 * 重置注册时填写的参数
 */
function resetRegisterParam() {
    vRegister.userName = '';
    vRegister.password = '';
    vRegister.password1 = '';
    vRegister.phone = '';
    vRegister.email = '';
    vRegister.type = '';
    vRegister.msgCode = '';
    vRegister.msgCodeErr = '';
    window.clearInterval(InterValObj);
    vRegister.btnSendCode = '获取验证码';
    vRegister.btnDisabled = true;
    vRegister.picCode = '';
}

/**
 * 发送短信获取验证码
 */
function sendMessage() {
    //设置当前剩余秒数
    curCount = vRegister.count;
    InterValObj = setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
    let data = {phone: vRegister.phone};
    //调动业务端方法
    let url = firstPath + "/registerPhone/sendMessage";
    callAjaxPost(url, data, sendMessageSus);
}

function sendMessageSus(data) {
    //发送
    if (data.obj == "error" || data.obj == "fail") {
        vRegister.msgCodeErr = '短信发送异失败，请稍后再试！';
        return;
    }
    if (data.obj == "1016") {
        vRegister.msgCodeErr = '手机号格式错误';
        return;
    }
    if (data.obj == "1023") {
        vRegister.msgCodeErr = '30秒内下发短信条数超过上限';
        return;
    }
    if (data.obj == "1024") {
        vRegister.msgCodeErr = '1小时内下发短信条数超过上限';
        return;
    }
    if (data.obj == "1025") {
        vRegister.msgCodeErr = '今日发短信条数超过上限';
        return;
    }
    if (data.obj == "1031") {
        vRegister.msgCodeErr = '短信发送功能失效，请稍后再试';
        return;
    }
    if (data.obj == "success") {
        console.log("发送成功！")
    }
}

/**
 * 发送邮箱
 */
function sendEmail() {
    //设置当前剩余秒数
    curCount = vRegister.count;
    InterValObj = setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
    let data = {email: vRegister.email};
    //调动业务端方法
    let url = firstPath + "/registerEmail/sendEmail";
    callAjaxPost(url, data, sendEmailSus);
}

function sendEmailSus(data) {
    if (data.obj === "fail") {
        vRegister.emailCodeErr = '短信发送异失败，请稍后再试！';
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
    if (curCount === 0) {
        window.clearInterval(InterValObj);//停止计时器
        vRegister.btnDisabled = false;//短信按钮可用
        vRegister.btnSendCode = "重新获取";
    }
    else {
        vRegister.btnDisabled = true;//短信按钮不可用
        vRegister.btnSendCode = curCount + "秒后重发";
        curCount--;//当前秒数-1
    }
}

/**
 * 刷新注册验证码
 */
function refreshRegister() {
    let url = firstPath + "/registerCode/findVerifyCode.sose?number=" + Math.random();
    //attr() 方法设置或返回被选元素的属性和值。
    $("#registerImg").attr("src", url);
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
    let e = event || window.event;
    let keyCode = e.keyCode || e.which;
    switch (keyCode) {
        //回车
        case 13:
            checkRegister();
            break;
        default:
            break;
    }
};