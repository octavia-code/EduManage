let vEduPlan = new Vue({
    el: '#eduPlan',
    data: function () {
        return {
            firstPath: '/edu/eduPlan',// 请求一级路径
            nowData: [], column: [], loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            eduPlan: {
                id: '', epName: '', yearPlanId: '', schoolInfoId: '', specialtyId: '',
                coursePropId: '', courseTypeId: '', courseInfoId: '',
                termInfoId: '', weekTime: '', seq: ''
            },// 实体类
            sEduPlan: {
                yearPlanId: '', schoolInfoId: '', specialtyId: '',
                coursePropId: '', courseTypeId: '', courseInfoId: '',
                termInfoId: '',
            },// 搜索信息
            addEduPlanModal: false,// 新增教学计划表模态框
            editEduPlanModal: false,// 编辑教学计划表模态框
            removeEduPlanModal: false,// 删除教学计划表模态框
            removeEduPlanSelectModal: false,// 批量删除教学计划表模态框

            yearPlanList: [],//年份集合,
            schoolInfoList: [],//学院集合
            specialtyList: [],//专业集合
            coursePropList: [],//课程性质集合
            courseTypeList: [],//课程类别集合
            courseInfoList: [],//课程信息集合
            termInfoList: [],// 学期集合
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
            this.getTableHead();
            this.getYearPlanList();
            this.getSchoolInfoList();
            this.getSpecialtyList();
            this.getCoursePropList();
            this.getCourseTypeList();
            this.getCourseInfoList();
            this.getTermInfoList();
        },

        /**
         * 表格表头
         */
        getTableHead() {
            let data = {tableName: "edu_plan"};
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
            // 添加自定义操作栏
            //this.column.push(headAction(true, null, true,
            //this.openEditEduPlanModal, true, this.removeEduPlan));
            // 添加多选
            this.column.unshift(headSelection());
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
         * 获取学院集合
         */
        getSchoolInfoList() {
            let url = '/info/schoolInfo/selectSchoolInfoList';
            callAjaxGetNoParam(url, this.getSchoolInfoListSuc);
        },
        getSchoolInfoListSuc(data) {
            this.schoolInfoList = data.obj;
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
         * 获取课程性质集合
         */
        getCoursePropList() {
            let url = '/edu/courseProp/selectCoursePropList';
            callAjaxGetNoParam(url, this.getCoursePropListSuc);
        },
        getCoursePropListSuc(data) {
            this.coursePropList = data.obj;
        },

        /**
         * 获取课程类别集合
         */
        getCourseTypeList() {
            let url = '/edu/courseType/selectCourseTypeList';
            callAjaxGetNoParam(url, this.getCourseTypeListSuc);
        },
        getCourseTypeListSuc(data) {
            this.courseTypeList = data.obj;
        },

        /**
         * 获取课程信息集合
         */
        getCourseInfoList() {
            let url = '/manage/courseInfo/listCourseInfo';
            callAjaxGetNoParam(url, this.getCourseInfoListSuc);
        },
        getCourseInfoListSuc(data) {
            this.courseInfoList = data.obj;
        },

        /**
         * 获取课程信息集合
         */
        getTermInfoList() {
            let url = '/manage/termInfo/selectTermInfoList';
            callAjaxGetNoParam(url, this.getTermInfoListSuc);
        },
        getTermInfoListSuc(data) {
            this.termInfoList = data.obj;
        },

        /**
         * 表格过滤查询
         */
        filter() {
            console.log('当前页：' + this.pageNum);
            console.log('页面大小：' + this.pageSize);
            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                epName: this.sEduPlan.epName,
                yearPlanId: this.sEduPlan.yearPlanId,
                schoolInfoId: this.sEduPlan.schoolInfoId,
                specialtyId: this.sEduPlan.specialtyId,
                coursePropId: this.sEduPlan.coursePropId,
                courseTypeId: this.sEduPlan.courseTypeId,
                courseInfoId: this.sEduPlan.courseInfoId,
                termInfoId: this.sEduPlan.termInfoId,
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
        clearSEduPlan() {
            this.sEduPlan.epName = '';
            this.sEduPlan.yearPlanId = '';
            this.sEduPlan.schoolInfoId = '';
            this.sEduPlan.specialtyId = '';
            this.sEduPlan.coursePropId = '';
            this.sEduPlan.courseTypeId = '';
            this.sEduPlan.courseInfoId = '';
            this.sEduPlan.termInfoId = '';
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
         * 检查教学计划数据格式
         * @return {boolean} 若数据格式错误,返回true
         */
        checkEduPlan() {
            if (checkEmpty(this.eduPlan.epName, '名称不能为空') ||
               checkLength(this.eduPlan.epName, '200', '名称不能超过200个字符')||
               checkEmpty(this.eduPlan.yearPlanId, '年份标识不能为空') ||
               checkLength(this.eduPlan.yearPlanId, '2', '年份标识不能超过2个字符')||
               checkEmpty(this.eduPlan.schoolInfoId, '学院标识不能为空') ||
               checkLength(this.eduPlan.schoolInfoId, '2', '学院标识不能超过2个字符')||
               checkEmpty(this.eduPlan.specialtyId, '专业标识不能为空') ||
               checkLength(this.eduPlan.specialtyId, '2', '专业标识不能超过2个字符')||
               checkEmpty(this.eduPlan.coursePropId, '课程性质标识不能为空') ||
               checkLength(this.eduPlan.coursePropId, '2', '课程性质标识不能超过2个字符')||
               checkEmpty(this.eduPlan.courseTypeId, '课程类别标识不能为空') ||
               checkLength(this.eduPlan.courseTypeId, '2', '课程类别标识不能超过2个字符')||
               checkEmpty(this.eduPlan.courseInfoId, '课程信息标识不能为空') ||
               checkLength(this.eduPlan.courseInfoId, '5', '课程信息标识不能超过5个字符')||
               checkEmpty(this.eduPlan.termInfoId, '学期分配标识不能为空') ||
               checkLength(this.eduPlan.termInfoId, '2', '学期分配标识不能超过2个字符')||
               checkEmpty(this.eduPlan.weekTime, '周课时数不能为空') ||
               checkLength(this.eduPlan.weekTime, '2', '周课时数不能超过2个字符')) {
                return true;
            }
        },
        
        /**
         * 新增教学计划表
         */
        addEduPlan() {
        	// 检查数据格式
            if (this.checkEduPlan()) {
                return;
            }
            // 发送请求
            let data = {
                epName: this.eduPlan.epName,
                yearPlanId: this.eduPlan.yearPlanId,
                schoolInfoId: this.eduPlan.schoolInfoId,
                specialtyId: this.eduPlan.specialtyId,
                coursePropId: this.eduPlan.coursePropId,
                courseTypeId: this.eduPlan.courseTypeId,
                courseInfoId: this.eduPlan.courseInfoId,
                termInfoId: this.eduPlan.termInfoId,
                weekTime: this.eduPlan.weekTime,
                seq: this.eduPlan.seq,
            };
            let url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addEduPlanSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        /**
         * 新增教学计划表回调函数
         *
         * @param data
         *            请求返回参数
         */
        addEduPlanSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            switch (data.code) {
                case 200:
                    // 关闭模态框
                    this.addEduPlanModal = false;
                    messageSuccess( "新增教学计划表成功");
                    // 重新查询数据
                    this.filter();
                    // 清除教学计划表
                    this.clearEduPlan();
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
        cancelAddEduPlan() {
            // 关闭模态框
            this.addEduPlanModal = false;
            // 清除教学计划表
            this.clearEduPlan();
        },

        /**
         * 打开编辑教学计划表模态框
         *
         * @param index
         *            当前数据索引
         */
        openEditEduPlanModal(index) {
            this.eduPlan.id = this.nowData[index].id;
            this.eduPlan.epName = this.nowData[index].epName;
            this.eduPlan.yearPlanId = this.nowData[index].yearPlanId;
            this.eduPlan.schoolInfoId = this.nowData[index].schoolInfoId;
            this.eduPlan.specialtyId = this.nowData[index].specialtyId;
            this.eduPlan.coursePropId = this.nowData[index].coursePropId;
            this.eduPlan.courseTypeId = this.nowData[index].courseTypeId;
            this.eduPlan.courseInfoId = this.nowData[index].courseInfoId;
            this.eduPlan.termInfoId = this.nowData[index].termInfoId;
            this.eduPlan.weekTime = this.nowData[index].weekTime;
            this.eduPlan.seq = this.nowData[index].seq;
            // 打开模态框
            this.editEduPlanModal = true;
        },
        /**
         * 修改教学计划表
         */
        editEduPlan() {
        	// 检查数据格式
            if (this.checkEduPlan()) {
                return;
            }
            let data = {
                id: this.eduPlan.id,
                epName: this.eduPlan.epName,
                yearPlanId: this.eduPlan.yearPlanId,
                schoolInfoId: this.eduPlan.schoolInfoId,
                specialtyId: this.eduPlan.specialtyId,
                coursePropId: this.eduPlan.coursePropId,
                courseTypeId: this.eduPlan.courseTypeId,
                courseInfoId: this.eduPlan.courseInfoId,
                termInfoId: this.eduPlan.termInfoId,
                weekTime: this.eduPlan.weekTime,
                seq: this.eduPlan.seq,
            };
            let url = this.firstPath + '/update';
            callAjaxPost(url, data, this.editEduPlanSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editEduPlanSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            switch (data.code) {
                case 200:
                    // 关闭模态框
                    this.editEduPlanModal = false;
                    messageSuccess( "教学计划表修改成功");
                    // 重新查询数据
                    this.filter();
                    // 清除教学计划表
                    this.clearEduPlan();
                    break;
                case 420:
                    messageError(this, data.msg);
                    break;
                default:
                    break;
            }
        },
        /**
         * 取消修改教学计划表
         */
        cancelEditEduPlan() {
            // 关闭模态框
            this.editEduPlanModal = false;
            // 清除教学计划表
            this.clearEduPlan();
        },
        /**
         * 清除教学计划表
         */
        clearEduPlan() {
            this.eduPlan.id = '';
            this.eduPlan.epName = '';
            this.eduPlan.yearPlanId = '';
            this.eduPlan.schoolInfoId = '';
            this.eduPlan.specialtyId = '';
            this.eduPlan.coursePropId = '';
            this.eduPlan.courseTypeId = '';
            this.eduPlan.courseInfoId = '';
            this.eduPlan.termInfoId = '';
            this.eduPlan.weekTime = '';
            this.eduPlan.seq = '';
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
         * 打开删除教学计划表模态框
         */
        openRemoveEduPlanSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning(this, '请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeEduPlanSelectModal = true;
        },

        /**
         * 批量删除数据
         */
        removeEduPlanSelect() {
            // 关闭模态框
            this.removeEduPlanSelectModal = false;
            let idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            let data = idList;
            let url = this.firstPath + '/deleteSelection';
            callAjaxPost(url, data, this.removeEduPlanSelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        /**
         * 批量删除数据成功回调函数
         */
        removeEduPlanSelectSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '成功删除 ' + data.obj + ' 条记录');
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },


        /**
         * 打开删除课程信息模态框
         * @param index 当前数据索引
         */
        openRemoveEduPlanModal(index) {
            this.eduPlan.id = this.nowData[index].id;
            this.removeEduPlanModal = true;
        },
        /**
         * 删除教学计划表
         *
         * @param index
         */
        removeEduPlan(index) {
            this.removeEduPlanModal = false;
            let data = this.eduPlan.id;
            let url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeEduPlanSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        removeEduPlanSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '教学计划表删除成功');
            // 重新查询数据
            this.filter();
        },


    }
});

