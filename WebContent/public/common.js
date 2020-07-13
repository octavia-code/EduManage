/**
 * 项目名
 * 若项目名称为空，需要在server.xml中配置以下目录
 * <Context debug="0" docBase="/emp" path="/" reloadable="true"/>
 */
const PROJECT_NAME = '';

/**
 * 方法请求路径:主机地址+项目名
 */
const METHOD_URL = 'http://127.0.0.1:8080'+ PROJECT_NAME;
//const METHOD_URL = 'http://112.86.129.73:2000/'+ PROJECT_NAME;

/**
 * 方法请求后缀
 */
const METHOD_SUFFIX = '.sose';

/**
 * 文件上传地址
 */
const UPLOAD_URL = 'http://127.0.0.1:8080/config/fileInfo/upload';
//const UPLOAD_URL = 'http://112.86.129.73:2000/config/fileInfo/upload';


// vue/iview相关/layout相关
document.write("<script type='text/javascript' src='" + PROJECT_NAME + "/public/jquery/jQuery-2.1.4.min.js'></script>"
    + "<script type='text/javascript' src='" + PROJECT_NAME + "/public/vue/vue-2.6.10.min.js'></script>"
    + "<script type='text/javascript' src='" + PROJECT_NAME + "/public/vue/http-vue-loader.js'></script>"
    + "<link rel='stylesheet' type='text/css' href='" + PROJECT_NAME + "/public/iview-3.3.3/css/iview.css'>"
    + "<script type='text/javascript' src='" + PROJECT_NAME + "/public/iview-3.3.3/iview.min.js'></script>"
    + "<script type='text/javascript' src='" + PROJECT_NAME + "/public/other/js/jitsoseVue.js'></script>"
    + "<link rel='stylesheet' type='text/css' href='" + PROJECT_NAME + "/public/other/css/vue.css'>"
    + "<link rel='stylesheet' type='text/css' href='" + PROJECT_NAME + "/layout/css/layout.css'>"
    + "<script type='text/javascript' src='" + PROJECT_NAME + "/public/vue/vue-resource-1.5.1.js'></script>"
);

// 引入element-ui组件库
document.write("<link rel='stylesheet' href='" + PROJECT_NAME + "/public/element-ui-2.10.0/lib/theme-chalk/index.css'>"
    + "<script type='text/javascript' src='" + PROJECT_NAME + "/public/element-ui-2.10.0/lib/index.js'></script>"
);

//document.write("<link rel='stylesheet' href='https://unpkg.com/element-ui/lib/theme-chalk/index.css'>"
//    + "<script async src='https://unpkg.com/element-ui/lib/index.js'></script>"
//);

// 自定义
document.write("<script type='text/javascript' src='" + PROJECT_NAME + "/public/other/js/cookieUtil.js'></script>"
    + "<script type='text/javascript' src='" + PROJECT_NAME + "/public/other/js/callBackAjax.js'></script>"
    + "<script type='text/javascript' src='" + PROJECT_NAME + "/public/other/js/jitsoseUtil.js'></script>"
    + "<script type='text/javascript' src='" + PROJECT_NAME + "/public/other/js/tableTitleUtil.js'></script>"
    + "<script type='text/javascript' src='" + PROJECT_NAME + "/public/other/js/dataFormatUtil.js'></script>"
    + "<script type='text/javascript' src='" + PROJECT_NAME + "/public/other/js/verifyConstant.js'></script>"
    + "<link rel='stylesheet' type='text/css' href='" + PROJECT_NAME + "/public/other/css/layout.css'>"
    + "<link rel='stylesheet' type='text/css' href='" + PROJECT_NAME + "/public/other/css/body.css'>"
    + "<link rel='stylesheet' type='text/css' href='" + PROJECT_NAME + "/public/other/css/pop.css'>"
);

//引入UEditor富文本框组件


// 解决页面favicon.ico 404错误
document.write("<link rel='bookmark' href='" + PROJECT_NAME + "/favicon.ico' type='image/x-icon'>");

