let vYearTerm = new Vue({
    el: '#yearTerm',
    data: function () {
        return {
            firstPath: '/manage/yearTerm',// 请求二级路径
            nowData: [], column: [], loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            yearTerm: {
                id: '', termName: '', remark: '',
            },// 实体类
            sYearTerm: {
                termName: ''
            },// 搜索信息
            addYearTermModal: false,// 新增学年信息模态框
            editYearTermModal: false,// 编辑学年学年信息模态框
            removeYearTermModal: false,// 删除学年信息模态框
            removeYearTermSelectModal: false,// 批量删除学年信息模态框
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
         * 页面初始化加载项 表格表头
         */
        initPage() {
            let data = {tableName: "year_term"};
            let url = '/config/tableTitle/listByTableName';
            callAjaxPost(url, data, this.getTableHeadSuc);
        },

        /**
         * 获取表头回调函数
         *
         * @param data
         *            请求返回参数
         */
        getTableHeadSuc(data) {
            // 生成表头
            this.column = showCol(data.obj.key, data.obj.title);
            // 添加自定义slot-scope
            this.column.push(headActionSlot());

            //添加自定义操作栏
            //this.column.push(headAction(false, null, true,
            //this.openEditYearTermModal, true, this.removeYearTerm));

            // 添加多选
            this.column.unshift(headSelection());
        },

        /**
         * 表格过滤查询
         */
        filter() {
            //  检查数据格式
            if (checkLength(this.sYearTerm.termName, '20', '学年名称不能超过20个字符')) {
                return;
            }
            console.log('当前页：' + this.pageNum);
            console.log('页面大小：' + this.pageSize);
            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                termName: this.sYearTerm.termName
            };
            let url = this.firstPath + '/selectPageInfo';
            callAjaxPost(url, data, this.filterSuc);
            // 显示加载
            this.loading = true;
        },
        
        /**
         * 表格过滤查询回调函数
         *
         * @param data
         *            请求返回参数
         */
        filterSuc(data) {
            // 取消显示加载
            this.loading = false;
            this.nowData = data.obj.list;
            this.totalNum = data.obj.total;
            // 再次设置当前页码
            this.pageNum = data.obj.pageNum;
        },
        
        /**
         * 清除搜索条件
         */
        clearSYearTerm() {
            this.sYearTerm.termName = '';
        },

        /**
         * 改变页码
         *
         * @param pageNum
         *            改变后的页码
         */
        onPageChange(pageNum) {
            this.pageNum = pageNum;
            this.filter();
        },
        
        /**
         * 切换每页条数
         *
         * @param pageSize
         *            换后的每页条数
         */
        onPageSizeChange(pageSize) {
            this.pageSize = pageSize;
            this.filter();
        },

        /**
         * 检查学年信息数据格式
         * @return {boolean} 若数据格式错误,返回true
         */
        checkYearTerm() {
            if (checkEmpty(this.yearTerm.termName, '年份名称不能为空') ||
               checkLength(this.yearTerm.termName, '20', '年份名称不能超过20个字符')) {
                return true;
            }
        },
        
        /**
         * 新增学年信息
         */
        addYearTerm() {
            // 检查数据格式
            if (this.checkYearTerm()) {
                return;
            }
            // 发送请求
            let data = {
                termName: this.yearTerm.termName,
                remark: this.yearTerm.remark,
            };
            let url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addYearTermSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        
        /**
         * 新增学年信息回调函数
         *
         * @param data
         *            请求返回参数
         */
        addYearTermSuc(data) {
        	// 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            // 关闭模态框
            this.addYearTermModal = false;
            messageSuccess( "新增学年信息成功");
            // 重新查询数据
            this.filter();
            // 清除课程信息
            this.clearYearTerm();
        },
        /**
         * 取消新增学年
         */
        cancelAddYearTerm() {
            // 关闭模态框
            this.addYearTermModal = false;
            // 清除学年信息
            this.clearYearTerm();
        },

        /**
         * 打开编辑学年信息模态框
         *
         * @param index
         *            当前数据索引
         */
        openEditYearTermModal(index) {
        	this.editYearTermModal = true;
            this.yearTerm.id = this.nowData[index].id;
            this.yearTerm.termName = this.nowData[index].termName;
            this.yearTerm.remark = this.nowData[index].remark;
        },
        /**
         * 修改学年信息
         */
        editYearTerm() {
            // 检查数据格式
            if (this.checkYearTerm()) {
                return;
            }
            let data = {
                id: this.yearTerm.id,
                remark: this.yearTerm.remark,
            };
            let url = this.firstPath + '/update';
            callAjaxPost(url, data, this.editYearTermSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editYearTermSuc(data) {
	        // 关闭加载提示
	        closeMessageLoading(this.loadingMsg);
	        // 关闭模态框
	        this.editYearTermModal = false;
	        messageSuccess( "学年修改成功");
	        // 重新查询数据
	        this.filter();
	        // 清除课程信息
	        this.clearYearTerm();
        },
        /**
         * 取消修改学年信息
         */
        cancelEditYearTerm() {
            // 关闭模态框
            this.editYearTermModal = false;
            // 清除学年信息
            this.clearYearTerm();
        },
        /**
         * 清除学年信息
         */
        clearYearTerm() {
            this.yearTerm.id = '';
            this.yearTerm.termName = '';
            this.yearTerm.remark = '';
            this.yearTerm.state = '';
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
         * 打开删除学年信息模态框
         */
        openRemoveYearTermSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning(this, '请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeYearTermSelectModal = true;
        },

        /**
         * 批量删除数据
         */
        removeYearTermSelect() {
            // 关闭模态框
            this.removeYearTermSelectModal = false;
            let idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            let data = idList;
            let url = this.firstPath + '/deleteSelection';
            callAjaxPost(url, data, this.removeYearTermSelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        /**
         * 批量删除数据成功回调函数
         */
        removeYearTermSelectSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '成功删除 ' + data.obj + ' 条记录');
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },
        
        /**
         * 打开删除学年信息模态框
         * @param index 当前数据索引
         */
        openRemoveYearTermModal(index) {
            this.yearTerm.id = this.nowData[index].id;
            this.removeYearTermModal = true;
        },

        /**
         * 删除学年信息
         *
         * @param index
         */
        removeYearTerm(index) {
            this.removeYearTermModal = false;
            let data = this.yearTerm.id;
            let url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeYearTermSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        removeYearTermSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '学年信息删除成功');
            // 重新查询数据
            this.filter();
        },
    }
});
