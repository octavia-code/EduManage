let vScoreAssessment = new Vue({
    el: '#scoreAssessment',
    data: function () {
        return {
            firstPath: '/score/scoreAssessment',// 请求一级路径
            nowData: [], column: [], loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            scoreAssessment: {
                id: '', gradeFormId: '', assessmentId: '', courseClassStudentInfoId: '', score: '', state: '',
                createdDate: '', stateDate: '', studentName: '', studentNumber: '', className: '', schoolName: '',
                courseName: '', scord: '', courseNo: '', outlineName: '', termName: '', staffName: '', assessName: '',
                assessRate: '', peaceTime: '', endTerm: '', experiment: '', remark: '', seq: '',
            },// 实体类
            sScoreAssessment: {
                schoolName: '', className: '', courseNo: '', staffName: '',
                courseName: '', scord: '',
            },// 搜索信息
            addScoreAssessmentModal: false,// 新增教学计划表模态框
            editScoreAssessmentModal: false,// 编辑教学计划表模态框
            removeScoreAssessmentModal: false,// 删除教学计划表模态框

            schoolList: [],//获取学院集合
            staffList: [],//获取教师集合
            courseList: [],//获取课程集合
            chechCourseList: [],//获取选课课号集合
            studentList: [],//获取学生集合
            classList: [],//获取班级信息集合
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
            this.getCourseList();
            //获取学院信息集合
            this.getSchoolNameList();
            //获取教师信息集合
            this.getStaffNameList();
            //获取选课号信息集合
            this.getChechCourseList();
            //获取学生信息集合
            this.getStudentList();
            this.getClassList();
        },

        /**
         * 表格表头
         */
        getTableHead() {
            let data = {tableName: "score_assessment"};
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
            console.log('当前页：' + this.pageNum);
            console.log('页面大小：' + this.pageSize);
            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                schoolName: this.sScoreAssessment.schoolName,
                className: this.sScoreAssessment.className,
                courseNo: this.sScoreAssessment.courseNo,
                staffName: this.sScoreAssessment.staffName,
                courseName: this.sScoreAssessment.courseName,
                scord: this.sScoreAssessment.scord,
            };
            let url = this.firstPath + '/selectScoreAssessment';
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
         * 获取课程集合
         */
        getCourseList() {
            let url = '/manage/courseInfo/listCourseInfo';
            callAjaxGetNoParam(url, this.getCourseListSuc);
        },
        getCourseListSuc(data) {
            this.courseList = data.obj;
            console.log(data.obj);
        },

        changeCourseName(value) {
            console.log(value);
            this.sScoreAssessment.scord = this.nowData[value].scord;
//        	let url = '/score/ScoreAssessment/changeScoreAssessment';
//        	callAjaxPost(url, value, this.getCourseSuc);
        },
//        getCourseSuc(){
//        	
//        },      
        //获取开课学院回调
        getSchoolNameList() {
            let url = '/info/schoolInfo/selectSchoolInfoList';
            callAjaxGetNoParam(url, this.getSchoolNameListSuc);
        },
        getSchoolNameListSuc(data) {
            this.schoolList = data.obj;
            console.log(data.obj);
        },
        //获取教师信息集合回调
        getStaffNameList() {
            let url = '/info/staffInfo/selectStaffInfoList';
            callAjaxGetNoParam(url, this.getStaffNameListSuc);
        },
        getStaffNameListSuc(data) {
            this.staffList = data.obj;
            console.log(data.obj);
        },
        //获取选课号信息集合回调
        getChechCourseList() {
            let url = '/manage/courseInfo/listCourseInfo';
            callAjaxGetNoParam(url, this.getChechCourseListSuc);
        },
        getChechCourseListSuc(data) {
            this.chechCourseList = data.obj;
            console.log(data.obj);
        },
        //获取学生集合
        getStudentList() {
            let url = '/info/studentInfo/selectStudentInfoList';
            callAjaxGetNoParam(url, this.getStudentListSuc);
        },
        getStudentListSuc(data) {
            this.studentList = data.obj;
            console.log(data.obj);
        },
        changeStudentNumber(value) {
            console.log(value);
            this.scoreAssessment.studentNumber = this.nowData[value].studentNumber;
        },
        //获取班级信息集合
        getClassList() {
            let url = '/info/classInfo/selectClassInfoList';
            callAjaxGetNoParam(url, this.getClassListSuc);
        },
        getClassListSuc(data) {
            this.classList = data.obj;
            console.log(data.obj);
        },
        /**
         * 清除搜索条件
         */
        clearSScoreAssessment() {
            this.sScoreAssessment.schoolName = '';
            this.sScoreAssessment.className = '';
            this.sScoreAssessment.courseNo = '';
            this.sScoreAssessment.staffName = '';
            this.sScoreAssessment.courseName = '';
            this.sScoreAssessment.scord = '';
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
         * 新增教学计划表
         */
        addScoreAssessment() {
            // 发送请求
            let data = {
                studentName: this.ScoreAssessment.studentName,
                studentNumber: this.ScoreAssessment.studentNumber,
                peaceTime: this.ScoreAssessment.peaceTime,
                endTerm: this.ScoreAssessment.endTerm,
                experiment: this.ScoreAssessment.experiment,
                score: this.ScoreAssessment.score,
                remark: this.ScoreAssessment.remark,
            };
            let url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addScoreAssessmentSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        /**
         * 新增教学计划表回调函数
         *
         * @param data
         *            请求返回参数
         */
        addScoreAssessmentSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            switch (data.code) {
                case 200:
                    // 关闭模态框
                    this.addScoreAssessmentModal = false;
                    messageSuccess( "新增教学计划表成功");
                    // 重新查询数据
                    this.filter();
                    // 清除教学计划表
                    this.clearScoreAssessment();
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
        cancelAddScoreAssessment() {
            // 关闭模态框
            this.addScoreAssessmentModal = false;
            // 清除教学计划表
            this.clearScoreAssessment();
        },

        /**
         * 打开编辑教学计划表模态框
         *
         * @param index
         *            当前数据索引
         */
        openEditScoreAssessmentModal(index) {
            this.scoreAssessment.id = this.nowData[index].id;
            this.scoreAssessment.gradeFormId = this.nowData[index].gradeFormId;
            this.scoreAssessment.assessmentId = this.nowData[index].assessmentId;
            this.scoreAssessment.courseClassStudentInfoId = this.nowData[index].courseClassStudentInfoId;
            this.scoreAssessment.score = this.nowData[index].score;
            this.scoreAssessment.state = this.nowData[index].state;
            this.scoreAssessment.createdDate = this.nowData[index].createdDate;
            this.scoreAssessment.stateDate = this.nowData[index].stateDate;
            this.scoreAssessment.schoolName = this.nowData[index].schoolName;
            this.scoreAssessment.className = this.nowData[index].className;
            this.scoreAssessment.courseNo = this.nowData[index].courseNo;
            this.scoreAssessment.staffName = this.nowData[index].staffName;
            this.scoreAssessment.courseName = this.nowData[index].courseName;
            this.scoreAssessment.scord = this.nowData[index].scord;
            this.scoreAssessment.outlineName = this.nowData[index].outlineName;
            this.scoreAssessment.termName = this.nowData[index].termName;
            this.scoreAssessment.studentName = this.nowData[index].studentName;
            this.scoreAssessment.studentNumber = this.nowData[index].studentNumber;
            this.scoreAssessment.assessName = this.nowData[index].assessName;
            this.scoreAssessment.assessRate = this.nowData[index].assessRate;
            this.scoreAssessment.seq = this.nowData[index].seq;
            this.scoreAssessment.peaceTime = this.nowData[index].peaceTime;
            this.scoreAssessment.endTerm = this.nowData[index].endTerm;
            this.scoreAssessment.experiment = this.nowData[index].experiment;
            this.scoreAssessment.remark = this.nowData[index].remark;
            // 打开模态框
            this.editScoreAssessmentModal = true;
        },

        /**
         * 修改教学计划表
         */
        editScoreAssessment() {
            let data = {
                id: this.ScoreAssessment.id = '',
                gradeFormId: this.scoreAssessment.gradeFormId = '',
                assessmentId: this.scoreAssessment.assessmentId = '',
                courseClassStudentInfoId: this.scoreAssessment.courseClassStudentInfoId = '',
                score: this.scoreAssessment.score = '',
                schoolName: this.ScoreAssessment.schoolName = '',
                className: this.ScoreAssessment.className = '',
                courseNo: this.ScoreAssessment.courseNo = '',
                staffName: this.ScoreAssessment.staffName = '',
                courseName: this.ScoreAssessment.courseName = '',
                scord: this.ScoreAssessment.scord = '',
                termName: this.ScoreAssessment.termName = '',
                studentName: this.ScoreAssessment.studentName = '',
                studentNumber: this.ScoreAssessment.studentNumber = '',
                peaceTime: this.ScoreAssessment.peaceTime = '',
                endTerm: this.ScoreAssessment.endTerm = '',
                experiment: this.ScoreAssessment.experiment = '',
                remark: this.ScoreAssessment.remark = '',
            };
            let url = this.firstPath + '/update';
            callAjaxPost(url, data, this.editScoreAssessmentSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editScoreAssessmentSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            switch (data.code) {
                case 200:
                    // 关闭模态框
                    this.editScoreAssessmentModal = false;
                    messageSuccess( "教学计划表修改成功");
                    // 重新查询数据
                    this.filter();
                    // 清除教学计划表
                    this.clearScoreAssessment();
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
        cancelEditScoreAssessment() {
            // 关闭模态框
            this.editScoreAssessmentModal = false;
            // 清除教学计划表
            this.clearScoreAssessment();
        },
        /**
         * 清除教学计划表
         */
        clearScoreAssessment() {
            this.ScoreAssessment.id = '';
            this.scoreAssessment.gradeFormId = '';
            this.scoreAssessment.assessmentId = '';
            this.scoreAssessment.courseClassStudentInfoId = '';
            this.scoreAssessment.score = '';
            this.ScoreAssessment.state = '';
            this.ScoreAssessment.createdDate = '';
            this.ScoreAssessment.stateDate = '';
            this.ScoreAssessment.schoolName = '';
            this.ScoreAssessment.className = '';
            this.ScoreAssessment.courseNo = '';
            this.ScoreAssessment.staffName = '';
            this.ScoreAssessment.courseName = '';
            this.ScoreAssessment.scord = '';
            this.ScoreAssessment.outlineName = '';
            this.ScoreAssessment.termName = '';
            this.ScoreAssessment.studentName = '';
            this.ScoreAssessment.studentNumber = '';
            this.ScoreAssessment.seq = '';
            this.scoreAssessment.assessName = '';
            this.scoreAssessment.assessRate = '';
            this.ScoreAssessment.peaceTime = '';
            this.ScoreAssessment.endTerm = '';
            this.ScoreAssessment.experiment = '';
            this.ScoreAssessment.remark = '';
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
        openRemoveScoreAssessmentModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning(this, '请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeScoreAssessmentModal = true;
        },

        /**
         * 批量删除数据
         */
        removeScoreAssessmentSelect() {
            // 关闭模态框
            this.removeScoreAssessmentModal = false;
            let idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            let data = idList;
            let url = this.firstPath + '/deleteSelection';
            callAjaxPost(url, data, this.removeScoreAssessmentSelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        /**
         * 批量删除数据成功回调函数
         */
        removeScoreAssessmentSelectSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '成功删除 ' + data.obj + ' 条记录');
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },

        /**
         * 删除教学计划表
         *
         * @param index
         */
        removeScoreAssessment(index) {
            let data = this.nowData[index].id;
            let url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeScoreAssessmentSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        removeScoreAssessmentSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '教学计划表删除成功');
            // 重新查询数据
            this.filter();
        },


    }
});

