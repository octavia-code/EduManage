let vGraduation = new Vue({
    el: '#graduation',
    data: function () {
        return {
            firstPath: '/indicator/graduation',// 请求一级路径
            nowData: [], column: [], loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            graduation: {
                id: '', title: '', yearPlanId: '', specialtyId: '', userId: '', remark: '',
            },// 实体类
            sGraduation: {
                title: '', yearPlanId: '', specialtyId: ''
            },// 搜索信息
            addGraduationModal: false,// 新增毕业要求指标点模态框
            editGraduationModal: false,// 编辑毕业要求指标点模态框
            removeGraduationSelectModal: false,// 批量删除毕业要求指标点模态框
            removeGraduationModal: false,// 删除毕业要求指标点模态框

            specialtyList: [],//专业集合
            yearPlanList: [],//年份集合
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
         * 页面初始化加载项
         */
        initPage() {
            this.getTableHead();
            this.getSpecialtyList();
            this.getYearPlanList();
        },

        /**
         * 表格表头
         */
        getTableHead() {
            let data = {tableName: "graduation_require_indicator"};
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
            //添加“备注”所在列信息
            //this.column.push(remark());
            // 添加自定义slot-scope
            this.column.push(headActionSlot());

            // 添加自定义操作栏
            // this.column.push(headAction(true, null, true,
            // this.openEditGraduationModal, true, this.removeGraduation));

            // 添加多选
            this.column.unshift(headSelection());
        },

        /**
         * 获取专业集合
         */
        getSpecialtyList() {
            let url = '/manage/specialty/selectSpecialtyList';
            callAjaxGetNoParam(url, this.getSpecialtyListSuc);
        },

        getSpecialtyListSuc(data) {
            this.specialtyList = data.obj;
        },
        /**
         * 获取年份集合
         */
        getYearPlanList() {
            let url = '/manage/yearPlan/selectYearPlanList';
            callAjaxGetNoParam(url, this.getYearPlanListSuc);
        },

        getYearPlanListSuc(data) {
            this.yearPlanList = data.obj;
        },

        /**
         * 表格过滤查询
         */
        filter() {
            // 检查数据格式
            if (checkLength(this.sGraduation.title, '100', '标题不能超过20个字符')) {
                return;
            }
            console.log('当前页：' + this.pageNum);
            console.log('页面大小：' + this.pageSize);

            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                title: this.sGraduation.title,
                yearPlanId: this.sGraduation.yearPlanId,
                specialtyId: this.sGraduation.specialtyId,
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
            // 再次设置当前页码,若查询记录为空，设为第一页
            this.pageNum = data.obj.pageNum === 0 ? 1 : data.obj.pageNum;
        },

        /**
         * 清除搜索条件
         */
        clearSGraduation() {
            this.sGraduation.title = '';
            this.sGraduation.yearPlanId = '';
            this.sGraduation.specialtyId = '';
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
         * 检查毕业要求指标点数据格式
         * @return {boolean} 若数据格式错误,返回true
         */
        checkGraduation() {
        	if (checkLength(this.sGraduation.title, '100', '标题不能超过20个字符')) {
                return true;
            }
        },

        /**
         * 新增毕业要求指标点
         */
        addGraduation() {
            // 检查数据格式
        	if (this.checkGraduation()) {
                return;
            }
            // 发送请求
            let data = {
                title: this.graduation.title,
                yearPlanId: this.graduation.yearPlanId,
                specialtyId: this.graduation.specialtyId,
                remark: this.graduation.remark,
            };
            let url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addGraduationSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        /**
         * 新增毕业要求指标点回调函数
         *
         * @param data
         *            请求返回参数
         */
        addGraduationSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            switch (data.code) {
                case 200:
                    // 关闭模态框
                    this.addGraduationModal = false;
                    messageSuccess( "新增毕业要求指标点成功");
                    // 重新查询数据
                    this.filter();
                    // 清除毕业要求指标点
                    this.clearGraduation();
                    break;
                case 420:
                    messageError(this, data.msg);
                    break;
                default:
                    break;
            }
        },
        /**
         * 取消新增课程
         */
        cancelAddGraduation() {
            // 关闭模态框
            this.addGraduationModal = false;
            // 清除毕业要求指标点
            this.clearGraduation();
        },

        /**
         * 打开编辑毕业要求指标点模态框
         *
         * @param index
         *            当前数据索引
         */
        openEditGraduationModal(index) {
            this.graduation.id = this.nowData[index].id;
            this.graduation.title = this.nowData[index].title;
            this.graduation.yearPlanId = this.nowData[index].yearPlanId;
            this.graduation.specialtyId = this.nowData[index].specialtyId;
            this.graduation.remark = this.nowData[index].remark;
            // 打开模态框
            this.editGraduationModal = true;
        },
        /**
         * 修改毕业要求指标点
         */
        editGraduation() {
            console.log(this.graduationTemp);
            // 检查数据格式
            if (checkLength(this.sGraduation.title, '100', '标题不能超过20个字符')) {
                return;
            }
            let data = {
                id: this.graduation.id,
                title: this.graduation.title,
                yearPlanId: this.graduation.yearPlanId,
                specialtyId: this.graduation.specialtyId,
                remark: this.graduation.remark,
            };
            let url = this.firstPath + '/update';
            callAjaxPost(url, data, this.editGraduationSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editGraduationSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            switch (data.code) {
                case 200:
                    // 关闭模态框
                    this.editGraduationModal = false;
                    messageSuccess( "毕业要求指标点修改成功");
                    // 重新查询数据
                    this.filter();
                    // 清除毕业要求指标点
                    this.clearGraduation();
                    break;
                case 420:
                    messageError(this, data.msg);
                    break;
                default:
                    break;
            }
        },
        /**
         * 取消修改毕业要求指标点
         */
        cancelEditGraduation() {
            // 关闭模态框
            this.editGraduationModal = false;
            // 清除毕业要求指标点
            this.clearGraduation();
        },
        /**
         * 清除毕业要求指标点
         */
        clearGraduation() {
            this.graduation.id = '';
            this.graduation.title = '';
            this.graduation.yearPlanId = '';
            this.graduation.specialtyId = '';
            this.graduation.remark = '';
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
         * 打开删除多选毕业要求指标点模态框
         */
        openRemoveGraduationSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning(this, '请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeGraduationSelectModal = true;
        },


        /**
         * 批量删除数据
         */
        removeGraduationSelect() {
            // 关闭模态框
            this.removeGraduationModal = false;
            let idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            let data = idList;
            let url = this.firstPath + '/deleteSelection';
            callAjaxPost(url, data, this.removeGraduationSelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        /**
         * 批量删除数据成功回调函数
         */
        removeGraduationSelectSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '成功删除 ' + data.obj + ' 条记录');
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },

        /**
         * 打开删除毕业要求指标点模态框
         * @param index 当前数据索引
         */
        openRemoveGraduationModal(index) {
            this.graduation.id = this.nowData[index].id;
            this.removeGraduationModal = true;
        },
        
        /**
         * 删除毕业要求指标点
         *
         * @param index
         */
        removeGraduation(index) {
            let data = this.nowData[index].id;
            let url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeGraduationSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        removeGraduationSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '毕业要求指标点删除成功');
            // 重新查询数据
            this.filter();
        },


    }
});

