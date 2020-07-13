let vYearPlan = new Vue({
    el: '#yearPlan',
    data: function () {
        return {
            firstPath: '/manage/yearPlan',// 请求二级路径
            nowData: [], column: [], loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            yearPlan: {
                id: '', yearName: '', remark: '', state: '',
            },// 实体类
            sYearPlan: {
                yearName: ''
            },// 搜索信息
            termInfoTemp: '',// 修改临时存放信息
            addYearPlanModal: false,// 新增年份信息模态框
            editYearPlanModal: false,// 编辑年份信息模态框
            removeYearPlanModal: false,// 删除年份信息模态框
            removeYearPlanSelectModal: false,// 批量删除年份信息模态框
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
            let data = {tableName: "year_plan"};
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
            // 添加多选
            this.column.unshift(headSelection());
        },

        /**
         * 表格过滤查询
         */
        filter() {
            //  检查数据格式
            if (checkLength(this.sYearPlan.yearName, '200', '年份名称不能超过20个字符')) {
                return;
            }
            console.log('当前页：' + this.pageNum);
            console.log('页面大小：' + this.pageSize);
            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                yearName: this.sYearPlan.yearName
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
        clearSYearPlan() {
            this.sYearPlan.yearName = '';
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
         * 检查年份信息数据格式
         * @return {boolean} 若数据格式错误,返回true
         */
        checkYearPlan() {
            if (checkEmpty(this.yearPlan.yearName, '年份名称不能为空') ||
               checkLength(this.yearPlan.yearName, '20', '年份名称不能超过20个字符')) {
                return true;
            }
        },
        /**
         * 新增年份信息
         */
        addYearPlan() {
            // 检查数据格式
            if (this.checkYearPlan()) {
                return;
            }
            // 发送请求
            let data = {
                yearName: this.yearPlan.yearName,
                remark: this.yearPlan.remark,
            };
            let url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addYearPlanSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        /**
         * 新增年份信息回调函数
         *
         * @param data
         *            请求返回参数
         */
        addYearPlanSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            switch (data.code) {
            case 200:
                // 关闭模态框
                this.addYearPlanModal = false;
                messageSuccess( "新增年份信息成功");
                // 重新查询数据
                this.filter();
                // 清除年份信息
                this.clearYearPlan();
                break;
            case 420:
                messageError(this, data.msg);
                break;
            default:
                break;
            }
        },
        /**
         * 取消新增年份
         */
        cancelAddYearPlan() {
            // 关闭模态框
            this.addYearPlanModal = false;
            // 清除年份信息
            this.clearYearPlan();
        },

        /**
         * 打开编辑年份信息模态框
         *
         * @param index
         *            当前数据索引
         */
        openEditYearPlanModal(index) {
        	this.editYearPlanModal = true;
            this.yearPlan.id = this.nowData[index].id;
            this.yearPlan.yearName = this.nowData[index].yearName;
            this.yearPlan.remark = this.nowData[index].remark;
        },
        /**
         * 修改年份信息
         */
        editYearPlan() {
            // 检查数据格式
            if (this.checkYearPlan()) {
                return;
            }
            let data = {
                id: this.yearPlan.id,
                remark: this.yearPlan.remark,
            };
            let url = this.firstPath + '/update';
            callAjaxPost(url, data, this.editYearPlanSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editYearPlanSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            // 关闭模态框
            this.editYearPlanModal = false;
            messageSuccess( "年份信息修改成功");
            // 重新查询数据
            this.filter();
            // 清除年份信息
            this.clearYearPlan();
        },
        /**
         * 取消修改年份信息
         */
        cancelEditYearPlan() {
            // 关闭模态框
            this.editYearPlanModal = false;
            // 清除年份信息
            this.clearYearPlan();
        },
        /**
         * 清除年份信息
         */
        clearYearPlan() {
            this.yearPlan.id = '';
            this.yearPlan.yearName = '';
            this.yearPlan.remark = '';
            this.yearPlan.state = '';
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
         * 打开删除年份信息模态框
         */
        openRemoveYearPlanSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning(this, '请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeYearPlanSelectModal = true;
        },

        /**
         * 批量删除数据
         */
        removeYearPlanSelect() {
            // 关闭模态框
            this.removeYearPlanSelectModal = false;
            let idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            console.log(idList);
            let data = idList;
            let url = this.firstPath + '/deleteSelection';
            callAjaxPost(url, data, this.removeYearPlanSelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        /**
         * 批量删除数据成功回调函数
         */
        removeYearPlanSelectSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '成功删除 ' + data.obj + ' 条记录');
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },


        /**
         * 打开删除年份信息模态框
         * @param index 当前数据索引
         */
        openRemoveYearPlanModal(index) {
            this.yearPlan.id = this.nowData[index].id;
            this.removeYearPlanModal = true;
        },

        /**
         * 删除年份信息
         *
         * @param index
         */
        removeYearPlan(index) {
            this.removeYearPlanModal = false;
            let data = this.yearPlan.id;
            let url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeYearPlanSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        removeYearPlanSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '年份信息删除成功');
            // 重新查询数据
            this.filter();
        },


    }
});
