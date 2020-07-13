module.exports = {
    data: function () {
        return {
        	userName: '',
            menu: {
                id: '', url: '', path: '', name: '',
                parentId: '', sort: '', isEnable: ''
            },// 菜单类
            menuList: [],// 菜单列表
            activeName: '',// 激活菜单的 name 值
            openMenu: [],// 展开的 Submenu 的 name 集合
            realTime: ''
        }



    },
    mounted() {
    	// 显示用户名
    	this.showUserName();
        this.realTime = this.dealWithTime(new Date());
        // 定时刷新时间
        let _this = this;
        // 定时器
        this.timer = setInterval(function () {
            _this.realTime = _this.dealWithTime(new Date()) // 修改数据date
        }, 1000)
    },
    methods: {
    	/**
         * 用户名展示
         */
        showUserName() {
            let userName = window.cookieUtil.get("userName");
            if (isNotEmpty(userName) && 'undefined' !== userName) {
                this.userName = userName;
                return;
            }
            let url = '/account/login/getUserInfo';
            callAjaxGetNoParam(url, this.showUserNameSuc);
        },

        /**
         * 查询菜单列表回调函数
         * @param data 请求返回参数
         */
        showUserNameSuc(data) {
        	debugger
            this.userName = data.obj.userName;
            // 将用户信息放入cookie中
            window.cookieUtil.set("userName", data.obj.userName, '6h');
        },

        /**
         * 退出按钮
         */
        quitLogin() {
            // 清除浏览器缓存
            window.sessionStorage.removeItem("menuVoList");
            window.cookieUtil.del("userName");
            window.cookieUtil.del("Authorization");
            let url = '/account/login/exit';
            callAjaxGetNoParam(url, this.quitLoginSuc);
        },

        /**
         * 查询菜单列表回调函数
         * @param data 请求返回参数
         */
        quitLoginSuc(data) {
            window.location.href = METHOD_URL
        },

        //实时时间
        dealWithTime(data) {
            let formatDateTime
            let Y = data.getFullYear()
            let M = data.getMonth() + 1
            let D = data.getDate()
            let H = data.getHours()
            let Min = data.getMinutes()
            let S = data.getSeconds()
            let W = data.getDay()
            H = H < 10 ? ('0' + H) : H
            Min = Min < 10 ? ('0' + Min) : Min
            S = S < 10 ? ('0' + S) : S
            switch (W) {
                case 0:
                    W = '天'
                    break
                case 1:
                    W = '一'
                    break
                case 2:
                    W = '二'
                    break
                case 3:
                    W = '三'
                    break
                case 4:
                    W = '四'
                    break
                case 5:
                    W = '五'
                    break
                case 6:
                    W = '六'
                    break
                default:
                    break
            }
            formatDateTime = Y + '年' + M + '月' + D + '日 ' + H + ':' + Min + ':' + S + ' 星期' + W;
            return formatDateTime
        }
    },
    // 注意在vue实例销毁前，清除我们的定时器。
    destroyed() {
        if (this.timer) {
            clearInterval(this.timer)
        }
    }
}