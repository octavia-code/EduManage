/**
 * vGlobal实例对象
 */
const vGlobal = new Vue({
    data: function () {
        return {
            url:'/outline/assessment/listByCourseOutlineId',
        }
    },
    mounted() {
        console.log('===============加载vue全局实例===============');
        messageConfig();
        noticeConfig();
    }
});

/**
 * 更改表格行样式
 * @param row
 * @param index 当前行索引
 * @returns  样式名称
 */
function rowClassName(row, index) {
    if (index % 2 === 1) {
        return 'myRowClassName';
    }
}


/**
 * Message 全局提示配置,使用提醒时先配置此方法
 * @param vGlobal   vGlobal实例
 * top   提示组件距离顶端的距离，单位像素,如：50，不可覆盖
 * duration  默认自动关闭的延时，单位秒，如：3，不关闭写0，可被覆盖
 */
function messageConfig() {
    vGlobal.$Message.config({
        top: 40,
        duration: 13,
    });
}

/**
 * Message 成功提示
 * @param vGlobal   vGlobal实例
 * @param content   提示内容
 */
function messageSuccess(content) {
    vGlobal.$Message.success({
        content: content,
    });
}

/**
 * Message 普通提示
 * @param vGlobal   vGlobal实例
 * @param content   提示内容<br>
 *  duration  默认自动关闭的延时，单位秒，如：3，不关闭写0
 */
function messageInfo(content) {
    vGlobal.$Message.info({
        content: content,
        duration: 6
    });
}

function messageInfo(content) {
    vGlobal.$Message.info({
        content: content,
        duration: 6
    });
}

/**
 * Message 警告提示
 * @param vGlobal   vGlobal实例
 * @param content   提示内容
 * duration  默认自动关闭的延时，单位秒，如：3
 * closable  是否显示关闭按钮，默认false
 */
function messageWarning(content) {
    vGlobal.$Message.warning({
        content: content,
        duration: 4,
        closable: true
    });
}

/**
 * Message 错误提示
 * @param vGlobal   vGlobal实例
 * @param content   提示内容
 *  duration  默认自动关闭的延时，单位秒，如：3
 *  closable  是否显示关闭按钮，默认false
 */
function messageError(content) {
    vGlobal.$Message.error({
        content: content,
        duration: 10,
        closable: true
    });
}

/**
 * Message 加载提示
 * @param vGlobal   vGlobal实例
 * @param content   提示内容
 * @return 提示对象
 */
function messageLoading() {
    const msg = vGlobal.$Message.loading({
        content: '正在加载中...',
        duration: 0
    });
    return msg;
}

/**
 * Message 关闭提示
 * @param msg 提示对象
 */
function closeMessageLoading(msg) {
    setTimeout(msg, 0);
}


/**
 * Notice 全局通知提醒配置,使用提醒时先配置此方法
 * @param vGlobal   vGlobal实例
 *  top   提示组件距离顶端的距离，单位像素,如：50
 *  duration  默认自动关闭的延时，单位秒，如：3
 */
function noticeConfig() {
    vGlobal.$Notice.config({
        top: 50,
        duration: 5,
    });
}

/**
 * Notice 全局关闭某个通知
 * @param vGlobal vGlobal实例
 * @param name 当前通知的唯一标识
 */
function closeNotice(name) {
    console.log(name);
    vGlobal.$Notice.close(name);
}

/**
 * Notice 全局销毁
 * @param vGlobal vGlobal实例
 */
function destroyNotice() {
    vGlobal.$Notice.destroy()
}

/**
 * Notice 打开提醒
 * @param vGlobal vGlobal实例
 * @param title 通知提醒的标题
 * @param desc 通知提醒的内容，为空或不填时，自动应用仅标题模式下的样式
 */
function noticeOpen(title, desc) {
    vGlobal.$Notice.open({
        title: title,
        desc: desc === (null || '') ? null : desc,
    });
}

/**
 * Notice 打开提醒,需手动关闭
 * @param vGlobal vGlobal实例
 * @param title 通知提醒的标题
 * @param desc 通知提醒的内容，为空或不填时，自动应用仅标题模式下的样式
 * duration 默认自动关闭的延时，单位秒，如：3,为 0 则不自动关闭。
 */
function noticeOpenEver(title, desc) {
    vGlobal.$Notice.open({
        title: title,
        desc: desc === (null || '') ? null : desc,
        duration: 0
    });
    return vGlobal.$Notice.name;
}

/**
 * Notice 打开消息提醒
 * @param vGlobal vGlobal实例
 * @param title 通知提醒的标题
 * @param desc 通知提醒的内容，为空或不填时，自动应用仅标题模式下的样式
 */
function noticeInfo(title, desc) {
    vGlobal.$Notice.info({
        title: title,
        desc: desc === (null || '') ? null : desc,
    });
}

/**
 * Notice 打开成功提醒
 * @param vGlobal vGlobal实例
 * @param title 通知提醒的标题
 * @param desc 通知提醒的内容，为空或不填时，自动应用仅标题模式下的样式
 */
function noticeSuccess(title, desc) {
    vGlobal.$Notice.success({
        title: title,
        desc: desc === (null || '') ? null : desc,
    });
}

/**
 * Notice 打开警告提醒
 * @param vGlobal vGlobal实例
 * @param title 通知提醒的标题
 * @param desc 通知提醒的内容，为空或不填时，自动应用仅标题模式下的样式
 */
function noticeWarning(title, desc) {
    vGlobal.$Notice.warning({
        title: title,
        desc: desc === (null || '') ? null : desc,
    });
}

/**
 * Notice 打开错误提醒
 * @param vGlobal vGlobal实例
 * @param title 通知提醒的标题
 * @param desc 通知提醒的内容，为空或不填时，自动应用仅标题模式下的样式
 */
function noticeError(title, desc) {
    vGlobal.$Notice.error({
        title: title,
        desc: desc === (null || '') ? null : desc,
    });
}











