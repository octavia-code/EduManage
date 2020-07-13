var vPerson = new Vue({
	el : '#person',
	data : function() {
		return {
			id:'',loginName: '', password: '', password1: '', phone: '', msgCode: '',//绑定属性
            passwordTip: '6-16个字母、数字或者英文符号，区分大小写', passwordErr: '', passwordErr1: '',
            loginNameFindErr:'',msgCodeErr: '', message: '', //错图提示信息
            sendFlag:false,//检查手机号码格式，true为可以发送短信
            checkPasswordFlag1:true,checkPasswordFlag2:true,
            checkCompareFlag:true,msgCodeFlag:false,//检查注册时，true可以注册,false不可以注册
            btnDisabled: false, //按钮不可用
            btnSendCode: '获取验证码',//验证码按钮
            InterValObj: '', //timer变量，控制时间
            count: 60,  //间隔函数，1秒执行
            curCount: ''//当前剩余秒数
		}
	},
	mounted:function () {
		this.initPage();
    },
	methods : {
		//初始化
		initPage(){
            var url=methodUrl+'login/getUserInfo.sose';
            callAxiosGetNoParam(url,this.initPageSuc,this.fail);
		},
        initPageSuc(data){
            console.log("用户基本信息：");
            console.log(data);
            this.id=data.userInfo.id;
            this.loginName=data.userInfo.name;
            this.phone=data.userInfo.phone;
		},
        fail(){
        	console.log("方法调用失败！");
		}
	}
});
/**
 * 检查短信验证码是否为空
 */
function checkMsgCode() {
    if (vPerson.msgCode == null || vPerson.msgCode == '') {
        vPerson.msgCodeErr = '请输入短信验证码！';
        vPerson.msgCodeFlag = false;
    } else {
        vPerson.msgCodeErr = '';
        vPerson.msgCodeFlag = true;
    }
}

/**
 * 检查密码格式
 */
function checkPassword() {
    if (vPerson.password == null || vPerson.password == '') {
        vPerson.passwordErr = '请输入密码！';
        return;
    }
    if (vPerson.password.length < 6 || vPerson.password.length > 16) {
        vPerson.passwordErr = '密码长度在6~16之间';
        vPerson.checkPasswordFlag1=false;
    } else {
        vPerson.passwordErr = '';
        vPerson.checkPasswordFlag1=true;
    }
    //支持字母、数字、标点符号、特殊字符
    //var reg=/^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])|(?=.*[A-Z])(?=.*[a-z])(?=.*[^A-Za-z0-9])|(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])|(?=.*[a-z])(?=.*[0-9])(?=.*[^A-Za-z0-9])).{6,}|(?:(?=.*[A-Z])(?=.*[a-z])|(?=.*[A-Z])(?=.*[0-9])|(?=.*[A-Z])(?=.*[^A-Za-z0-9])|(?=.*[a-z])(?=.*[0-9])|(?=.*[a-z])(?=.*[^A-Za-z0-9])|(?=.*[0-9])(?=.*[^A-Za-z0-9])|).{6,16}$/
    //密码必须为6-18位字母、数字
    // var reg=/^(?![^a-zA-Z]+$)(?!\D+$)/
    // 密码必须为6-16位字母、数字、特殊符号的   数字格式2 (?=.*[0-9])   特殊符号格式2(?=.*[^a-zA-Z0-9])
    /*var reg = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[~!@#$%^&*()_+`\-={}:";'<>?,.\/]).{6,16}$/;
    if (!reg.test(vPerson.password)) {
        vPerson.passwordErr = '同时包含数字，字母，特殊符号';
        vPerson.checkPasswordFlag2=false;
    } else {
        vPerson.passwordErr = '';
        vPerson.checkPasswordFlag2=true;
    }*/
//    vPerson.passwordErr = '';
    vPerson.checkPasswordFlag2=true;
}

/**
 * 检查两次密码输入是否相同
 */
function compare() {
    //密码验证不能为空
    if (vPerson.password1 == null || vPerson.password1 == '') {
        vPerson.passwordErr1 = '请再次输入密码';
        return;
    }
    if (vPerson.password !== vPerson.password1) {
        vPerson.passwordErr1 = '密码不一致，请重新输入';
        vPerson.checkCompareFlag=false;
    } else {
        vPerson.passwordErr1 = '';
        vPerson.checkCompareFlag=true;
    }
}

/**
 * 1,前端验证注册信息
 */
function checkForget() {
    //1.1，验证输入数据格式
    checkMsgCode();
    checkPassword();
    compare();
    //1.2，验证格式Flag,全为true才能进行下一步操作
    if(vPerson.checkPasswordFlag1 == false || vPerson.checkPasswordFlag2 == false ||
        vPerson.checkCompareFlag == false || vPerson.msgCodeFlag == false){
        return;
    }
    //调用验证短信验证码方法
    compareSmsCode();
}

/**
 * 2.2验证短信验证码是否填写正确
 */
function compareSmsCode() {
    //设置参数
    var data = {msgCode: vPerson.msgCode};
    //调动业务端方法
    var url = methodUrl+'login/forgetPwdPhone/compareSmsCode.sose';
    callAxiosPost(url, data, compareSmsCodeSuc, compareSmsCodeFail);
}
function compareSmsCodeSuc(data) {
    //短信验证码超时或者不正确或后台异常直接返回
    if (data.info == "error") {
        vPerson.msgCodeErr = '验证码验证异常，请稍后再试！';
        vPerson.msgCode = '';
        return;
    }
    if (data.info == "timeOut") {
        vPerson.msgCodeErr = '短信验证码已失效，请重新获取!';
        vPerson.msgCode = '';
        return;
    }
    if (data.info == "msgCodeErr") {
        vPerson.msgCodeErr = '短信验证码错误！';
        vPerson.msgCode = '';
        return;
    }
    if (data.info == "success") {
        //短信验证码验证成功，调用重置密码方法
        forgetPwd();
    }
}
function compareSmsCodeFail() {
    console.log("compareSmsCode()调用失败");
}


/**
 * 3,重置密码
 */
function forgetPwd() {
    //设置参数
    var data = {password: vPerson.password};
    //调动业务端方法
    var url = methodUrl+'login/changePwd.sose';
    callAxiosPost(url, data, forgetPwdSuc, forgetPwdFail);
}
function forgetPwdSuc(data) {
    //失败或后台异常，直接返回
    if (data.info == "error" || data.info == "fail") {
        vPerson.loginNameFindErr = '重置密码失败，请稍后再试！';
        return;
    }
    if (data.info == "pwdError") {
        vPerson.passwordErr = '新密码不能是旧密码！';
        return;
    }
    if (data.info == "success") {
        //打开Vue对话框
        vPerson.$Modal.success({
            content: '<p>重置密码成功</p>',
            okText: '确定',
            onOk: function () {
                //重置登陆信息
                resetForgetParam();
                //清除后台session中的验证码,防止短信验证码还有效
                var url=methodUrl + 'login/clearSession.sose';
                callAxiosGetNoParamsAndFun(url);
                // window.location.href="/gies/login.html";
            }
        });
    }
}
function forgetPwdFail() {
    console.log("forgetPwd()调用失败！")
}

/**
 * 清除参数
 */
function resetForgetParam() {
    console.log("重置参数");
    vPerson.password = '';
    vPerson.password1 = '';
    vPerson.msgCode = '';
    vPerson.msgCodeErr='';
    window.clearInterval(InterValObj);
    vPerson.btnSendCode = '获取验证码';
    vPerson.btnDisabled = true;
}

/**
 * 发送短信获取验证码
 */
function sendMessage() {
    //设置当前剩余秒数
    curCount = vPerson.count;
    InterValObj = setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
    var data = {phone: vPerson.phone};
    //调动业务端方法
    var url = methodUrl+"login/forgetPwdPhone/sendMessage.sose";
    callAxiosPost(url, data, sendMessageSus, sendMessageFail);
}
function sendMessageSus(data) {
    console.log(data);
    //发送
    if (data.info == "error" || data.info == "fail") {
        vPerson.msgCodeErr = '短信发送异常，请稍后再试！';
//        alert("测试用验证码：" + data.msgCode);//正式上线时，注意去掉这行
        return;
    }
    if (data.info == "1016") {
        vPerson.msgCodeErr = '手机号格式错误';
        return;
    }
    if (data.info == "1023") {
        vPerson.msgCodeErr = '30秒内下发短信条数超过上限';
        return;
    }
    if (data.info == "1024") {
        vPerson.msgCodeErr = '1小时内下发短信条数超过上限';
        return;
    }
    if (data.info == "1025") {
        vPerson.msgCodeErr = '今日发短信条数超过上限';
        return;
    }
    if (data.info == "1031") {
        vPerson.msgCodeErr = '短信发送功能失效，请稍后再试';
        return;
    }
    if (data.info == "success") {
        console.log("发送成功！")
    }
}
function sendMessageFail() {
    console.log("sendMessage方法调用失败！")
}

/**
 * timer处理函数
 * @constructor
 */
function SetRemainTime() {
    if (curCount == 0) {
        window.clearInterval(InterValObj);//停止计时器
        vPerson.btnDisabled = false;//短信按钮可用
        vPerson.btnSendCode = "重新获取";
    }
    else {
        vPerson.btnDisabled = true;//短信按钮不可用
        vPerson.btnSendCode = curCount + "秒后重发";
        curCount--;//当前秒数-1
    }
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