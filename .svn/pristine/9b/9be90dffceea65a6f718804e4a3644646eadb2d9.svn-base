let vClassInfo = new Vue({
    el: '#classInfo',
    data: function () {
        return {
            firstPath: '/info/classInfo',// 请求一级路径
            nowData: [], column: [], loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            classInfo: {
                id: '', className: '', specialtyId: '', yearPlanId: '', remark: '', createdDate: '',
                stateDate: '', state: '',
            },// 实体类
            sClassInfo: {
                className: '',
            },// 搜索信息
            classInfoTemp: '',// 修改临时存放信息
            addClassInfoModal: false,// 新增班级信息模态框
            editClassInfoModal: false,// 编辑班级信息模态框
            removeClassInfoSelectModal: false,// 批量删除班级信息模态框
            removeClassInfoModal: false,// 删除班级信息模态框

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
         * 页面初始化加载项 表格表头
         */
        initPage() {
            let data = {tableName: "class_info"};
            let url = '/config/tableTitle/listByTableName';
            callAjaxPost(url, data, this.getTableHeadSuc);

            this.getSpecialtyList();
            this.getyearPlanList();
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
        getyearPlanList() {
            let url = '/manage/yearPlan/selectYearPlanList';
            callAjaxGetNoParam(url, this.getYearPlanListSuc);
        },

        getYearPlanListSuc(data) {
            this.yearPlanList = data.obj;
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
            //this.column.push(headAction(true, null, true,
            //this.openEditclassInfoModal, true, this.removeclassInfo));

            // 添加多选
            this.column.unshift(headSelection());
        },

        /**
         * 备注详情按钮
         *
         */
        showRemark(index) {
            this.classInfo.remark = this.nowData[index].remark;
            alert(this.classInfo.remark);
        },

        /**
         * 表格过滤查询
         */
        filter() {
            // 检查数据格式
            if (checkLength(this.sClassInfo.className, '30', '班级名称不能超过30个字符')) {
                return;
            }


            console.log('当前页：' + this.pageNum);
            console.log('页面大小：' + this.pageSize);

            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                className: this.sClassInfo.className
            };
            let url = this.firstPath + '/selectClassName';
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
        clearSClassInfo() {
            this.sClassInfo.className = '';
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
         * 新增班级信息
         */
        addClassInfo() {
            // 检查数据格式
        	 if (this.checkClassInfo()) {
        	        return;
        	    }

            // 发送请求
            let data = {
                className: this.classInfo.className,
                remark: this.classInfo.remark,
                specialtyId: this.classInfo.specialtyId,
                yearPlanId: this.classInfo.yearPlanId,
            };
            let url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addClassInfoSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        /**
         * 新增班级信息回调函数
         *
         * @param data
         *            请求返回参数
         */
        addClassInfoSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            switch (data.code) {
                case 200:
                    // 关闭模态框
                    this.addClassInfoModal = false;
                    messageSuccess( "新增班级信息成功");
                    // 重新查询数据
                    this.filter();
                    // 清除班级信息
                    this.clearClassInfo();
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
        cancelAddClassInfo() {
            // 关闭模态框
            this.addClassInfoModal = false;
            // 清除班级信息
            this.clearClassInfo();
        },

        /**
         * 打开编辑班级信息模态框
         *
         * @param index
         *            当前数据索引
         */
        openEditClassInfoModal(index) {
            //console.log(this.nowData[index]);
            this.classInfoTemp = this.nowData[index];
            this.classInfo.id = this.nowData[index].id;
            this.classInfo.className = this.nowData[index].className;
            this.classInfo.remark = this.nowData[index].remark;
            this.classInfo.specialtyId = this.nowData[index].specialtyId;
            this.classInfo.yearPlanId = this.nowData[index].yearPlanId;
            console.log(this.nowData[index],this.classInfo);
            // 打开模态框
            this.editClassInfoModal = true;
        },
        /**
         * 修改班级信息
         */
        editClassInfo() {
            console.log(this.classInfo);
            // 判断数据是否修改

            // 检查数据格式
            if (this.checkClassInfo()) {
    	        return;
    	    }
            let data = {
                id: this.classInfo.id,
                className: this.classInfo.className,
                remark: this.classInfo.remark,
            };
            let url = this.firstPath + '/update';
            callAjaxPost(url, data, this.editClassInfoSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editClassInfoSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            switch (data.code) {
                case 200:
                    // 关闭模态框
                    this.editClassInfoModal = false;
                    messageSuccess( "班级信息修改成功");
                    // 重新查询数据
                    this.filter();
                    // 清除班级信息
                    this.clearClassInfo();
                    break;
                case 420:
                    messageError(this, data.msg);
                    break;
                default:
                    break;
            }
        },
        /**
         * 取消修改班级信息
         */
        cancelEditClassInfo() {
            // 关闭模态框
            this.editClassInfoModal = false;
            // 清除班级信息
            this.clearClassInfo();
        },
        
        /**
         * 检查班级学生信息数据格式
         * @return {boolean} 若数据格式错误,返回true
         */
        checkClassInfo() {
        	if (checkEmpty(this.classInfo.className, '班级名称不能为空') ||
                    checkLength(this.classInfo.className, '30', '班级名称不能超过30个字符') ||
                    checkLength(this.classInfo.remark, '500', '备注不能超过500个字符')) {
        		return true;
                }
        },

        /**
         * 清除班级信息
         */
        clearClassInfo() {
            this.classInfo.id = '';
            this.classInfo.className = '';
            this.classInfo.specialtyId = '';
            this.classInfo.yearPlanId = '';
            this.classInfo.remark = '';
            this.classInfo.createdDate = '';
            this.classInfo.stateDate = '';
            this.classInfo.state = '';
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
         * 打开删除多选班级信息模态框
         */
        openRemoveClassInfoSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning(this, '请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeClassInfoSelectModal = true;
        },

        /**
         * 批量删除数据
         */
        removeClassInfoSelect() {
            // 关闭模态框
            this.removeClassInfoModal = false;
            let idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            let data = idList;
            let url = this.firstPath + '/deleteSelection';
            callAjaxPost(url, data, this.removeClassInfoSelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        /**
         * 批量删除数据成功回调函数
         */
        removeClassInfoSelectSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '成功删除 ' + data.obj + ' 条记录');
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },

        /**
         * 打开删除班级信息模态框
         * @param index 当前数据索引
         */
        openRemoveClassInfoModal(index) {
            this.classinfo.id = this.nowData[index].id;
            this.removeClassInfoModal = true;
        },
        
        /**
         * 删除班级信息
         *
         * @param index
         */
        removeClassInfo(index) {
            let data = this.nowData[index].id;
            let url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeClassInfoSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        removeClassInfoSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '班级信息删除成功');
            // 重新查询数据
            this.filter();
        },


    }
});

