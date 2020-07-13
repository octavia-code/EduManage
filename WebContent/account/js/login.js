// let homePage =  "/test/people.html";
var homePage = "/manage/course_info";
//访问路径第一层
var firstPath = '/account/login';
var vLogin = new Vue({
    //对应div的id
    el: '#login',
    data: function () {
        return {
            loginType: 'account',
            loginError: '',
            phoneError: '', //错误提示信息
            imgSrc: METHOD_URL + firstPath + "/loginCode/findVerifyCode" + METHOD_SUFFIX, //图形验证码
            registerHref: '/register',
            accountForm: {
                userName: '', password: '', code: ''
            }, //账户表单属性
            accountRules: {
                userName: [{required: true, message: '账号不能为空', trigger: 'blur'}],
                password: [{required: true, message: '密码不能为空', trigger: 'blur'}],
                code: [{required: true, message: '验证码不能为空', trigger: 'blur'}]
            }, //账户表单验证规则
            phoneForm: {
                phone: '', msgCode: '', code: ''
            }, //手机表单属性
            phoneRules: {
                phone: [{validator: checkPhone, trigger: 'blur'}],
                msgCode: [{required: true, message: '短信验证码不能为空', trigger: 'blur'}],
                code: [{required: true, message: '图形验证码不能为空', trigger: 'blur'}]
            }, //手机表单验证规则
            sendFlag: false, //是否能够发送短信
            btnDisabled: false, //按钮不可用
            btnSendCode: '获取验证码', //验证码按钮
            InterValObj: '', //timer变量，控制时间
            count: 60, //间隔函数，1秒执行
            curCount: '' //当前剩余秒数
        }
    },
    mounted() {
        console.log(this.imgSrc);
    }
});

/**
 * 使用账号登录
 */
function loginByAccount() {
    vLogin.loginType = 'account';
    refreshAccountImg();
}

/**
 * 使用手机号码登录
 */
function loginByPhone() {
    vLogin.loginType = 'phone';
    refreshPhoneImg();
}

/**
 * 表单提交
 */
function loginSubmit() {
    console.log(vLogin.loginType);
    if (vLogin.loginType == 'account') {
        submitByAccount();
    }
    if (vLogin.loginType == 'phone') {
        submitByPhone();
    }
}

/**
 * 提交账号表单
 */
function submitByAccount() {
    vLogin.$refs.accountForm.validate(function (valid) {
        if (valid) {
            comparePicCode();//验证码
        }
    });
}

/**
 *  提交手机表单
 */
function submitByPhone() {
    vLogin.$refs.phoneForm.validate(function (valid) {
        if (valid) {
            comparePicCode(); //验证码
        }
    });
}

/**
 * 自定义检验手机号码格式
 */
function checkPhone(rule, value, callback) {
    let result = VERIFY_PHONE.test(value);
    if (!value) {
        return callback(new Error("请输入手机号码"));
    } else if (!result) { //手机格式不正确
        return callback(new Error("请输入正确手机号码"));
    } else {
        vLogin.sendFlag = true; //可以发送短信
        callback();
    }
}

/**
 * 图形验证码验证
 */
function comparePicCode() {
    //设置参数
    let data;
    if (vLogin.loginType == 'account') {
        data = {
            picCode: vLogin.accountForm.code
        }
    } else if (vLogin.loginType == 'phone') {
        data = {
            picCode: vLogin.phoneForm.code
        }
    }
    console.log(vLogin.loginType);
    console.log(data);
    //调用业务端方法
    let url = firstPath + "/loginCode/comparePicCode";
    callAjaxPost(url, data, comparePicCodeSuc);
}

function comparePicCodeSuc(data) {
    console.log(vLogin.loginType);
    //1,验证码错误或后台异常直接返回
    if (data.obj === "fail") {
        //验证码错误,刷新页面，选择性重置相关值
        vLogin.loginError = '验证码错误，请重新输入！';
        vLogin.accountForm.code = '';
        refreshAccountImg();
        return;
    }
    // 2,验证码正确，验证用户名密码
    if (data.obj === 'success') {
        if (vLogin.loginType === 'account') {
            accountLogin();
        } else if (vLogin.loginType === 'phone') {
            phoneLogin();
        }
    }
}

/**
 * 验证用户名密码
 */
function accountLogin() {
    //设置参数
    let data = {
        userName: vLogin.accountForm.userName,
        password: vLogin.accountForm.password
    };
    console.log(data);
    //调用业务端方法
    let url = firstPath + "/accountLogin";
    callAjaxPost(url, data, loginSuc);
}

function loginSuc(data) {
    //1,验证码用户名密码错误 或后 台异常直接返回
    if (data.obj.info === "fail" || data.obj.info === "error") {
        //验证码错误,刷新页面，选择性重置相关值
        vLogin.loginError = '用户名或密码错误！';
        resetParams();
        return;
    }
    if (data.obj.info === "freeze") {
        //验证码错误,刷新页面，选择性重置相关值
        vLogin.loginError = '该账户已冻结！';
        resetParams();
        return;
    }
    if (data.obj.info === "success") {
        // 将token放入本地存储中
        // window.localStorage.setItem("Authorization", data.obj.token);
        
        // 将菜单转成对象字符串
        let menuVoList = data.obj.menuVoList;
        // 用户无权限
        if (menuVoList.length == 0) {
            noticeWarning('当前用户无权限', '请联系管理员分配权限');
            return;
        }
        // 将菜单转成对象字符串
        let menuVoListStr = JSON.stringify(menuVoList);
        
        // 将菜单放入会话存储中
        window.sessionStorage.setItem("menuVoList", menuVoListStr);
        // 将用户信息放入cookie中
        window.cookieUtil.set("userName", data.obj.userName);
        // 将token放入cookie中
        window.cookieUtil.set("Authorization", data.obj.token);
        
        // 跳转首页,默认为当前用户的第一个菜单
        homePage = menuVoList[0].menuList[0].name;
        window.location.href = homePage;
    }
}

/**
 * 验证用户名密码
 */
function phoneLogin() {
    //设置参数
    let data = {
        phone: vLogin.phoneForm.phone,
        msgCode: vLogin.phoneForm.msgCode
    };
    //调用业务端方法
    let url = firstPath + "/phoneLogin";
    callAjaxPost(url, data, phoneLoginSuc);
}

function phoneLoginSuc(data) {
    console.log(data);
    switch (data.obj) {
        case "phoneError":
            vLogin.loginError = '手机号码有误！';
            resetParams();
            break;
        case "timeOut":
            vLogin.loginError = '短信验证码失效！';
            resetParams();
            break;
        case "msgCodeErr":
            vLogin.loginError = '短信验证码有误！';
            resetParams();
            break;
        default:
            break;

    }

    if (data.obj === "success") {
        window.location.href = homePage;
    }
}


/**
 * 登录失败，重置相关参数并刷新验证码
 */
function resetParams() {
    vLogin.accountForm.password = '';
    vLogin.accountForm.code = '';
    vLogin.phoneForm.msgCode = '';
    vLogin.phoneForm.code = '';
    refreshAccountImg();
}

/**
 * 发送短信获取验证码
 */
function sendMessage() {
    if (vLogin.sendFlag) {
        //设置当前剩余秒数
        curCount = vLogin.count;
        InterValObj = setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
        let data = {
            phone: vLogin.phoneForm.phone
        };
        //调动业务端方法
        let url = firstPath + "/loginPhone/sendMessage";
        callAjaxPost(url, data, sendMessageSus);
    } else {
        //**********还没写好
        vLogin.phoneError = "请输入手机号码";
    }
}

/**
 * 需要封装成一个方法 switch/case
 * @param data
 */
function sendMessageSus(data) {
    //发送
    if (data.obj === 1016) {
        vLogin.loginError = '手机号格式错误';
        return;
    }
    if (data.obj === 1023) {
        vLogin.loginError = '30秒内下发短信条数超过上限';
        return;
    }
    if (data.obj === 1024) {
        vLogin.loginError = '1小时内下发短信条数超过上限';
        return;
    }
    if (data.obj === 1025) {
        vLogin.loginError = '今日发短信条数超过上限';
        return;
    }
    if (data.obj === 1031) {
        vLogin.loginError = '短信发送功能失效，请稍后再试';
        return;
    }
    if (data.obj === 0) {
        console.log("发送成功！")
    }
}

/**
 * timer处理函数
 * @constructor
 */
function SetRemainTime() {
    if (curCount === 0) {
        window.clearInterval(InterValObj); //停止计时器
        vLogin.btnDisabled = false; //短信按钮可用
        vLogin.btnSendCode = "重新获取";
    } else {
        vLogin.btnDisabled = true; //短信按钮不可用
        vLogin.btnSendCode = curCount + "秒后重发";
        curCount--; //当前秒数-1
    }
}

/**
 * 刷新账号验证码
 */
function refreshAccountImg() {
    let url = METHOD_URL + firstPath + "/loginCode/findVerifyCode.sose?number=" + Math.random();
    $("#accountImg").attr("src", url);
}

/**
 * 刷新手机验证码
 */
function refreshPhoneImg() {
    let url = METHOD_URL + firstPath + "/loginCode/findVerifyCode.sose?number=" + Math.random();
    $("#phoneImg").attr("src", url);
}

/**
 * 打开忘记密码页面
 */
function openForgetPwd() {
    let url = "/reset_password";
    window.open(url);
}
