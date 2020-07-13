module.exports = {
    data: function () {
        return {
            firstPath: '/account/menu',
            menu: {
                id: '', url: '', path: '', name: '',
                parentId: '', sort: '', isEnable: ''
            },// 菜单类
            menuList: [],// 菜单列表
            activeName: '',// 激活菜单的 name 值
            openMenu: [],// 展开的 Submenu 的 name 集合
            realTime: '',
            html: '',
        }
    },
    mounted() {
        // console.log("I am the sider");
        // 查询菜单
        this.selectMenuList();
        // 获取主机地址之后的目录，设置为激活的菜单
        this.activeName = window.document.location.pathname;
        // 以'/'分隔从后截取，设置为展开的父菜单
        this.openMenu.push(this.activeName.substring(0, this.activeName.lastIndexOf('/')));
    },
    methods: {
        /**
         * 查询菜单列表
         */
        selectMenuList() {
//            this.menuList = [
//                {
//                    "name": "/account", "title": "账户管理",
//                    "menuList": [
//                        {"name": "/account/role_", "title": "角色管理", "menuList": null},
//                        {"name": "/account/menu_", "title": "菜单管理", "menuList": null}
//                    ]
//                },
//                {
//                    "name": "/zhibiao", "title": "评估指标设计",
//                    "menuList": [
//                        {"name": "/zhibiao/yiji", "title": "一级指标", "menuList": null},
//                        {"name": "/zhibiao/erji", "title": "二级指标", "menuList": null}
//                    ]
//                },
//            ];
//            this.activeName="/zhibiao/erji";
//            this.openMenu=["/zhibiao"];
//            this.setCurrentMenu();
//            return;

            // 从window.sessionStorage（会话存储）中获取菜单对象字符串
            let menuVoListStr = window.sessionStorage.getItem("menuVoList");
            // 会话存储中存有菜单
            if (isNotEmpty(menuVoListStr)) {
                // 将对象字符串转为对象
                this.menuList = JSON.parse(menuVoListStr);
                this.setCurrentMenu();
                return;
            }

            // 会话存储中没有菜单，重新获取
            let url = this.firstPath + '/listMenuByUserId';
            callAjaxGetNoParam(url, this.selectMenuListSuc);
        },

        /**
         * 查询菜单列表回调函数
         */
        selectMenuListSuc(data) {
            this.menuList = data.obj;
            // 将菜单转成对象字符串
            let menuVoListStr = JSON.stringify(data.obj);
            // 将菜单放入会话存储中
            window.sessionStorage.setItem("menuVoList", menuVoListStr);
            this.setCurrentMenu();
        },

        /**
         * 根据当前页面路径设置打开的菜单
         */
        setCurrentMenu() {
            // 动态设置menu组件，设置active-name和open-names时，需要手动更新，并给menu组件绑定ref
            this.$nextTick(() => {
                // 手动更新展开的子目录，注意要在 $nextTick 里调用
                this.$refs.sider_menu.updateOpened();
                // 手动更新当前选择项，注意要在 $nextTick 里调用
                this.$refs.sider_menu.updateActiveName();
            });
             console.log("默认打开父菜单:" + this.openMenu);
             console.log("激活的菜单:" + this.activeName);
        },

        /**
         * 选择菜单（MenuItem）时触发
         * @param name MenuItem的name属性值
         */
        onSelectMenu(name) {
            console.log("页面跳转:" + name);
            window.location.href = name;
        },
    },
};