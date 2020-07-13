/**
 * cookie相关操作
 */
(function () {
    let cookies = {};
    /**
     * 设置cookie
     * @param key
     * @param value
     * @param delay cookie有效日期 单位:（3s 4m 5h 7d 秒 分 时 天; 没有则默认为天 ）
     * @param path cookie的作用范围，设置了值不为null的时候，以设置的值为准
     * @returns {*}
     */
    cookies.set = function (key, value, delay, path) {
        let cookieString;
        // cookie值为一个对象
        if (typeof value === "object") {
            // JavaScript 对象转换为字符串
            value = JSON.stringify(value);
        }
        // 对字符串进行编码
        value = escape(value);
        cookieString = key + "=" + value + ";";

        // 默认cookie为七天之后过期 3s 4m 5h 7d 秒 分 时 天
        if (isEmpty(delay)) {
            delay = "7d";
        }
        // 将cookie有效日期转小写
        delay = delay.toLowerCase();
        // 获取当前时间
        let expireDate = new Date();
        // 解析cookie有效日期并返回一个整数。
        let num = parseInt(delay);
        // 获取有效日期单位
        let unit = delay.charAt(delay.length - 1);
        switch (unit) {
            case 'd':
                expireDate.setDate(expireDate.getDate() + num);
                break;
            case 'h':
                expireDate.setHours(expireDate.getHours() + num);
                break;
            case 'm':
                expireDate.setMinutes(expireDate.getMinutes() + num);
                break;
            case 's':
                expireDate.setSeconds(expireDate.getSeconds() + num);
                break;
            default:
                expireDate.setDate(expireDate.getDate() + num);
        }
        cookieString += "expires=" + expireDate.toGMTString() + ";";

        // 如果path不为空，设置cookie的作用域
        let cookiePath;
        if (isNotEmpty(path)) {
            cookiePath = ";path=" + path + ";";
            cookieString += cookiePath;
        }
        document.cookie = cookieString;
        return this.get(key);
    };

    /**
     * 得到cookie 如果不存在 返回 undefined
     * 不传参表示获得所有
     * @param key
     * @return {*}
     */
    cookies.get = function (key) {
        let objCookie = {};
        let cookie = document.cookie;
        let keyValueList = cookie.split(";");
        for (let index in keyValueList) {
            let keyValue = keyValueList[index].split("=");
            let k = keyValue[0].trim();
            let v = keyValue[1];
            // 对由 escape() 编码的字符串进行解码。
            v = unescape(v);
            v = this.decodeJson(v);
            objCookie[k] = v;
        }
        if (typeof key == "undefined" || key == null) {
            return objCookie;
        }
        return objCookie[key];
    };
    /**
     * 删除cookie,不传参表示删除所有
     * @param key
     * @return {boolean}
     */
    cookies.del = function (key) {
        // 删除所有cookie
        if (typeof key == "undefined" || key == null) {
            let cookieList = this.get();
            for (key in cookieList) {
                this.del(key);
            }
            return true;
        } else {
            if (this.get(key) == "undefined") {
                return false;
            } else {
                let exp = new Date();
                exp.setTime(-1000);
                document.cookie = key + "=" + ";expires=" + exp.toGMTString() + ";path=/"; // 单斜杠 ‘/’表示项目当前根路径
                return true;
            }
        }
    };

    cookies.decodeJson = function (value) {
        // 数组转成的对象字符串
        let regAryStr = /^\[[\s|\S]*\]$/;
        // 对象转成的对象字符串
        let regObjStr = /^\{([\"\s|\S]+\"\:\"[\s|\S]*)+\"\}$/;
        if (regAryStr.test(value)) {
            return eval("(" + value + ")");
        }
        if (regObjStr.test(value)) {
            return JSON.parse(value);
        }
        return value;
    };
    window.cookieUtil = cookies;
})(window);


