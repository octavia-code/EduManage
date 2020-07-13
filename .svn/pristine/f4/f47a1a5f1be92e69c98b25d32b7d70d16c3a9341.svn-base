/**
 * 前端验证数据格式类型
 */

/**
 * 判断数据是否为空
 * @param value 需要验证的数据
 * @returns {boolean}
 */
function isEmpty(value) {
    if (value === undefined || value === null || value === "" || value.toString().trim().length === 0) {
        return true;
    } else {
        return false;
    }
}

/**
 * 判断数据是否为空
 * @param value 需要验证的数据
 * @returns {boolean}
 */
function isNotEmpty(value) {
    return !isEmpty(value);
}

/**
 * 判断数据是否为空
 * @param value 需要验证的数据
 * @param msg 错误提示信息
 * @returns {boolean}
 */
function checkEmpty(value, msg) {
    if (value === undefined || value === null || value === "" || value.toString().trim().length === 0) {
        messageWarning(msg);
        return true;
    } else {
        return false;
    }
}

/**
 * 判断数据长度是否超过自定义限值
 *
 * @param value
 *            需要验证的数据
 * @param length
 *            自定义限值
 * @param msg
 *            错误提示信息
 * @returns {boolean}
 */
function checkLength(value, length, msg) {
    if (value.length > length) {
        messageWarning(msg);
        return true;
    } else {
        return false;
    }
}

/**
 * 检查数据合法性
 * @param value 检查的值
 * @param Par 合法值
 * @param mode 模式
 * @returns TRUE or FALSE
 */
function checkLegal(value, Par, mode) {
    if (value === null || value === "" || value.toString().trim().length === 0) {
        messageWarning("为空");// 判断为空弹出消息框“为空”
        return true;
    } else {
        var isInt = "^[0-9]*$";
        var isDouble = "^-?([1-9]\\d*\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$";
        var RegInt = new RegExp(isInt, "g");
        var RegDouble = new RegExp(isDouble, "g");
        if (RegInt.test(value))// 判断是否为Int
        {
            switch (mode) {
                case "max"://验证Int最大值
                    if (value > Par) {
                        messageWarning("超过最大值" + Par);// 当Int超过最大值弹出消息框“超过最大值”
                        return true;
                    } else
                        return false;
                    break;
                case "min"://验证Int最小值
                    if (value < Par) {
                        messageWarning("低于最小值" + Par);// 当Int低于最小值弹出消息框“低于最小值”
                        return true;
                    } else
                        return false;
                    break;
                case "length"://验证Int长度
                    var length = "^\\d{" + Par + "}$";
                    var RegLength = new RegExp(length, "g");
                    if (!RegLength.test(value)) {
                        messageWarning("必须为" + Par + "位");// 当Int长度不正确时弹出消息框“必须为Par位”
                        return true;
                    } else
                        return false;
                    break;
                case "min_len"://验证Int最小长度
                    var min_len = "^\\d{" + Par + ",}$";
                    var RegMinIntLength = new RegExp(min_len, "g");
                    if (!RegMinIntLength.test(value)) {
                        messageWarning("至少为" + Par + "位");// 当Int长度不满足最小位数时弹出消息框“必须至少为Par位”
                        return true;
                    } else
                        return false;
                    break;
                case "max_len"://验证Int最大长度
                    var max_len = "^\\d{1," + Par + "}$";
                    var RegMaxIntLength = new RegExp(max_len, "g");
                    if (!RegMaxIntLength.test(value)) {
                        messageWarning("最多为" + Par + "位");// 当Int长度不满足最大位数时弹出消息框“必须最多为Par位”
                        return true;
                    } else
                        return false;
                    break;
                case "isPhone"://验证是否为手机号
                    var isPhone = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
                    var RegIsPhone = new RegExp(isPhone, "g");
                    if (!RegIsPhone.test(value)) {
                        messageWarning("请填写正确的手机号码")//当手机号码不合法时弹出消息框“请填写正确的手机号码”
                        return true;
                    } else
                        return false;
                    break;
            }
        } else if (RegDouble.test(value)) {// 判断值是不是Double类型浮点数
            switch (mode) {
                case "max"://验证Double最大值
                    if (value > Par) {
                        messageWarning("超过最大值" + Par);// 当Double超过最大值弹出消息框“超过最大值”
                        return true;
                    } else
                        return false;
                    break;
                case "min"://验证Double最小值
                    if (value < Par) {
                        messageWarning("低于最小值" + Par);// 当Double低于最小值弹出消息框“低于最小值”
                        return true;
                    } else
                        return false;
                    break;
                case "afterpoint"://验证Double小数点位数
                    var afterpoint = "^(\\-)?\\d+(\\.\\d{" + Par + "})$";
                    var RegAfterPoint = new RegExp(afterpoint, "g");
                    if (!RegAfterPoint.test(value)) {
                        messageWarning("小数点后必须为" + Par + "位");// 当Double小数点后长度不正确时弹出消息框“小数必须为Par位”
                        return true;
                    } else
                        return false;
                    break;
                case "max_afterpoint"://验证Double小数点后最大位数
                    var maxafterpoint = "^[0-9]+(\\.[0-9]{1," + Par + "})?$";
                    var RegMaxAfterPoint = new RegExp(maxafterpoint, "g");
                    if (!RegMaxAfterPoint.test(value)) {
                        messageWarning("小数点后最多为" + Par + "位");// 当Double小数点后长度超过最大长度时弹出消息框“小数最长为Par位”
                        return true;
                    } else
                        return false;
                    break;
                case "min_afterpoint"://验证Double小数点后最小位数
                    var minafterpoint = "^[0-9]+(\\.[0-9]{" + Par + ",})?$";
                    var RegMinAfterPoint = new RegExp(minafterpoint, "g");
                    if (!RegMinAfterPoint.test(value)) {
                        messageWarning("小数点后最少为" + Par + "位");// 当Double小数点后长度低于最小长度时弹出消息框“小数最长为Par位”
                        return true;
                    } else
                        return false;
                    break;
            }
        } else {
            switch (mode) {
                case "min_len"://验证String类型最小长度
                    var min_len = "^.{" + Par + ",}$";
                    var RegMinStrLen = new RegExp(min_len, "g");
                    if (!RegMinStrLen.test(value)) {
                        messageWarning("最小长度为" + Par + "位")//当String不满足最小长度时弹出消息框“最小长度为Par位”
                        return true;
                    } else
                        return false;
                    break;
                case "max_len"://验证String类型最大长度
                    var max_len = "^.{1," + Par + "}$";
                    var RegMaxStrLen = new RegExp(max_len, "g");
                    if (!RegMaxStrLen.test(value)) {
                        messageWarning("最大长度为" + Par + "位")//当String超过最大长度时弹出消息框“最小长度为Par位”
                        return true;
                    } else
                        return false;
                    break;
                case "length"://验证String类型长度
                    var length = "^.{" + Par + "}$";
                    var RegStrLen = new RegExp(length, "g");
                    if (!RegStrLen.test(value)) {
                        messageWarning("长度必须为" + Par + "位")//当String长度不合法时弹出消息框“最小长度为Par位”
                        return true;
                    } else
                        return false;
                    break;
                case "isEmail"://验证是否为电子邮件
                    var isEmail = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
                    var RegIsEmail = new RegExp(isEmail, "g");
                    if (!RegIsEmail.test(value)) {
                        messageWarning("请填写正确的Email地址")//当Email地址不合法时弹出消息框“请填写正确的Email地址”
                        return true;
                    } else
                        return false;
                    break;
            }
        }
    }

}
