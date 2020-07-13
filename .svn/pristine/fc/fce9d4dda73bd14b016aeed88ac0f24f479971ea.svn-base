let vUserMenu = new Vue({
    el: '#userMenu',
    data: function () {
        return {
            firstPath: '/account/userMenu',//请求一级路径
            nowData: [], loading: true, selection: [], TreeData: [],// 表格参数
            column: [
                {title: '用户姓名', key: 'userName'},
                {title: '用户电话号码', key: 'phone'},
                {title: '邮箱', key: 'email'}
            ],
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            seeDataTreeModal: false,//查看权限树模态框
            editDataTreeModal: false,//编辑权限模态框
            sUserMenu: {
                userId: ''
            },
            seeDataTree: [[]],//查看权限集合
            editDataTree: [],//编辑权限集合
            checkedKeys: [],//具有的权限id集合
            editProp: {
                label: 'title',
                children: 'menuList'
            },
        }
    },
    components: {
        'layout-header': httpVueLoader('/layout/layout-header.vue'),
        'layout-sider': httpVueLoader('/layout/layout-sider.vue'),
        'layout-footer': httpVueLoader('/layout/layout-footer.vue')
    },
    mounted() {
        this.initPage();
        this.filter();
    },
    methods: {
        /**
         * 页面初始化
         */
        initPage() {
            // 设置表头
            this.setTableTitle();
        },

        setTableTitle() {
            // 添加自定义slot-scope
            this.column.push(headActionSlot());
            // 添加序号
            this.column.unshift(headIndex());
            // 添加多选
            this.column.unshift(headSelection());
        },

        filter() {
            let url = this.firstPath + '/listUserMenu';
            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize
            };
            callAjaxPost(url, data, this.filterSuc);
            this.loading = true;
        },

        filterSuc(data) {
            // 取消显示加载
            this.loading = false;
            this.nowData = data.obj.list;
            this.totalNum = data.obj.total;
            // 再次设置当前页码
            this.pageNum = data.obj.pageNum;
            console.log(data.obj, this.checkedKeys);
        },
        /**
         * 切换每页条数
         *
         * @param pageSize
         *            换后的每页条数
         */
        onPageSizeChange(pageSize) {
            this.pageSize = pageSize;
            this.initPage();
        },
        /**
         * 改变页码
         *
         * @param pageNum
         *            改变后的页码
         */
        onPageChange(pageNum) {
            this.pageNum = pageNum;
            this.initPage();
        },
        /**
         * 在多选模式下有效，只要选中项发生变化时就会触发
         *
         * @param selection
         *            已选项数据
         */
        onSelectionChange(selection) {
            this.selection = selection;
            console.log(this.selection);
        },

        /**
         * 查看权限树
         */
        getSeeDataTree(index) {
            var indexc = index;
            let url = this.firstPath + '/listByUserMenuTree';
            let data = this.nowData[index].id;
            callAjaxPost(url, data, this.getSeeDataTreeSuc)
        },
        getSeeDataTreeSuc(data) {
            this.seeDataTree = data.obj;
            this.seeDataTreeModal = true;
            console.log(data.obj)
        },

        /**
         * 展示编辑权限书
         */
        getEditDataTree() {
            //获取权限树 获取checkKey
            let url = '/account/userMenu/selectParentMenu';
            callAjaxGetNoParam(url, this.getEditDataTreeSuc);
        },
        getTree(index) {
            let url = this.firstPath + '/listByUserMenuTree';
            this.sUserMenu.userId = this.nowData[index].id;
            let data = this.nowData[index].id;
            callAjaxPost(url, data, this.getTreeSuc)
        },
        getTreeSuc(data) {
            this.seeDataTree = data.obj;
        },
        getEditDataTreeSuc(data) {
            this.editDataTree = data.obj;
//          将需要勾中的子节点勾中
            let tree = this.seeDataTree
            let keys = [];
            for (let i = 0; i < tree.length; i++) {
                for (let j = 0; j < tree[i].children.length; j++) {
                    keys.push(tree[i].children[j].id);
                }
            }
            this.checkedKeys = keys;
            this.editDataTreeModal = true;
        },
        /**
         * 提交子节点父节点id进行增加与删除
         */
        editOk() {
//        	debugger
            let menuIdList = this.$refs.editTree.getCheckedKeys().concat(this.$refs.editTree.getHalfCheckedKeys())
            let data = {
                menuIdList: menuIdList,
                userId: this.sUserMenu.userId
            }
            let url = this.firstPath + '/editUserMenu'
            if (isEmpty(menuIdList)) {
                messageWarning('该用户应该至少有一条权限')
                return
            }
            console.log(data)
            callAjaxPost(url, data, this.editOkSuc)
            this.loadingMsg = true
        },
        editOkSuc(data) {
            this.loadingMsg = false
            if (data === 'suc') {
                messageSuccess('编辑成功')
                return
            }
            messageWarning('编辑失败')
        },
    }
})