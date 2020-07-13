let vCourseClassInfo = new Vue({
    el: '#courseClassInfo',
    data: function () {
        let vm = this;
        return {
            StudentManageData_BackUp: [],
            firstPath: '/info/courseClassInfo',// 请求一级路径
            nowData: [], column: [],             //大网页表格数据
            StudentManageData: [],

            StudentManageColumn: [
                {
                    title: '序号',
                    key: 'studentNumber',
                    width: 110,
                    size: "small",
                    render: (h, params) => {
                        return h('InputNumber', {
                            props: {
                                value: params.row.seq,
                                precision: 0,
                                min: 0,
                                max: 100
                            },
                            on: {
                                input: (val) => {
                                    params.row.seq = val;
                                    vm.StudentManageData[params.index] = params.row;
                                    console.log(val, params.index, vm.StudentManageData[params.index]);
                                }
                            },
                        })
                    }
                },
                {title: '学号', key: 'studentNumber', width: 160},
                {title: '学生姓名', key: 'studentName', width: 160},
            ],//学生管理模态框表格数据
            StudentManageColumn_BackUp: [],//学生管理模态框表格数据备份
            StudentInfoData: [], StudentInfoColumn: [],  //学生库模态框表格数据
            loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            courseClassInfo: {
                id: '', yearTermId: '', courseInfoId: '', courseOutlineId: '', staffId: '', schoolInfoId: '',
                specialtyId: '', classInfoId: '', choiceCourseNoId: '', courseNo: '', fileInfoId: '', className: '',
            },// 实体类
            sCourseClassInfo: {
                yearTermId: '',
                courseInfoId: '',
                courseOutlineId: '',
                staffId: '',
                schoolInfoId: '',
                specialtyId: '',
                classInfoId: '',
                choiceCourseNoId: '',
                courseNo: '',
            },// 搜索信息
            courseClassStudentInfo: {
                id: '', courseClassInfoId: '', studentInfoId: '', seq: '',
            },//课程班级学生信息实体类
            studentInfo: {
                id: '', studentNumber: '', studentName: '', remark: '',
            },// 学生实体类

            sStudentInfo: {
                yearPlanId: '', schoolInfoId: '', specialtyId: '', classInfoId: '', studentName: '',
            },
            addCourseClassInfoModal: false,// 新增课程班级信息模态框
            editCourseClassInfoModal: false,// 编辑课程班级信息模态框
            removeCourseClassInfoModal: false,// 删除课程班级信息模态框
            removeCourseClassInfoSelectModal: false,// 批量删除课程班级信息模态框
            StudentManageModal: false,// 学生管理信息模态框
            removeStudentInfoModal: false,//删除上课学生信息模态框
            addStudentModel: false,//添加学生模态框

            yearTermList: [],// 学年集合
            courseInfoList: [],// 课程集合
            courseOutlineList: [],//课程大纲集合
            staffInfoList: [],// 教师集合
            schoolInfoList: [],// 学院集合
            specialtyList: [],// 专业集合
            classInfoList: [],// 班级集合
            courseNoList: [],//选课课号集合
            yearPlanList: [],// 年份名称集合

            excelUploadModal: false,// 文件上传模态框
            uploadUrl: UPLOAD_URL, // 上传地址
            textFormData: new FormData(), // 文本表单对象
            textFileType: 'T',// 文件类型
            textFileList: [],// 文件列表
            textIdList: [],// 文件id列表
            maxTextSize: 2,// 最大大小，单位mb(单位可自行调整)
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
         * 排序函数
         */
        sortSeq() {
            var data = [];
            var seqInfo = [];
            for (var i = 0; i < this.StudentManageData.length; i++) {
                if (this.StudentManageData[i].seq != this.StudentManageData_BackUp[i].seq) {
                    seqInfo = {
                        courseClassInfoId: this.courseClassInfo.id,
                        studentId: this.StudentManageData[i].studentId,
                        seq: this.StudentManageData[i].seq
                    }
                    console.log(seqInfo);
                    data.push(seqInfo);
                }
            }
            console.log(data);
            if (data.length > 0) {
                var url = '/info/courseClassStudentInfo/sortSeq';
                callAjaxPost(url, data, this.sortSeqSuc);
                // 显示加载
                this.loading = true;
            } else {
                messageWarning("未作任何更改!");
            }
        },
        /**
         * 排序回调函数
         */
        sortSeqSuc(data) {
            // 取消显示加载
            this.loading = false;
            messageSuccess("排序成功！");
            this.StudentManageData_BackUp = cloneObj(this.StudentManageData);
        },

        /**
         * 页面初始化加载项
         */
        initPage() {
            this.getTableHead();
            this.getSchoolNameList();
            this.getTermNameList();
            this.getCourseNameList();
            this.getStaffNameList();
            this.getYearPlanList();
            this.StudentManageColumn_BackUp = cloneObj(this.StudentManageColumn);
            /*this.getClassNameList();*/
            /*  this.getSpecialtyList();*/
        },

        /**
         * 表格表头
         */
        getTableHead() {
            let data = {tableName: "course_class_info"};
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
         * 获取年份(几届)集合
         */
        getYearPlanList() {
            let url = '/manage/yearPlan/selectYearPlanList';
            callAjaxGetNoParam(url, this.getYearPlanListSuc);

        },
        getYearPlanListSuc(data) {
            this.yearPlanList = data.obj;
        },

        /**
         * 获取学年名称集合
         */
        getTermNameList() {
            let url = '/manage/yearTerm/selectYearTermList';
            callAjaxGetNoParam(url, this.getTermNameListSuc);
        },

        getTermNameListSuc(data) {
            this.yearTermList = data.obj;
        },

        /**
         * 获取课程名称集合
         */
        getCourseNameList() {
            let url = '/manage/courseInfo/listCourseInfo';
            callAjaxGetNoParam(url, this.getCourseNameListSuc);
        },

        getCourseNameListSuc(data) {
            this.courseInfoList = data.obj;
        },

        /**
         * 获取课程大纲集合
         * @param value
         */
        getCourseOutlineList(value) {
            let data = {
                courseInfoId: value
            };
            let url = '/outline/courseOutline/listByCourseOutline';
            /*     console.log("即将传入的课程id："+data.courseInfoId);*/
            callAjaxPost(url, data, this.getCourseOutlineListSuc);

        },
        getCourseOutlineListSuc(data) {
            this.courseOutlineList = data.obj;
            /*console.log("courseOutlineList第一位id：" + this.courseOutlineList[0].id);*/
        },
        /**
         * 获取教师工号和名称集合
         */
        getStaffNameList() {
            let url = '/info/staffInfo/selectStaffInfoList';
            callAjaxGetNoParam(url, this.getStaffNameListSuc);
        },

        getStaffNameListSuc(data) {
            this.staffInfoList = data.obj;
            /*            console.log("教师:"+this.staffInfoList[0].staffName);*/
        },

        //通过课程大纲id，学年id、教师id查询选课号
        getCourseNoList() {
            //检查数据格式
            if (checkEmpty(this.sCourseClassInfo.yearTermId, '请选择学年名称！') ||
                checkEmpty(this.sCourseClassInfo.courseInfoId, '请选择课程名称！') ||
                checkEmpty(this.sCourseClassInfo.courseOutlineId, '请选择课程大纲名称！')
            ) {
                return;
            }
            // 发送请求
            let data = {
                yearTermId: this.sCourseClassInfo.yearTermId,
                courseOutlineId: this.sCourseClassInfo.courseOutlineId,
                staffInfoId: this.sCourseClassInfo.staffId
            };
            console.log("学年id：" + data.yearTermId);
            console.log("课程大纲id：" + data.courseOutlineId);
            console.log("教师id：" + data.staffInfoId);
            let url = '/info/choiceCourseNo/selectByChoiceCourseNo';
            callAjaxPost(url, data, this.getCourseNoSuc);

        },
        getCourseNoSuc(data) {
            if (data.obj == null) {
                this.sCourseClassInfo.choiceCourseNoId = '';
                this.sCourseClassInfo.courseNo = '';
                messageWarning("数据库无对应选课号,请重新输入!");
            } else {
                this.sCourseClassInfo.courseNo = data.obj.courseNo;
                this.sCourseClassInfo.choiceCourseNoId = data.obj.id;
            }
        },

        /**
         * 获取学院名称集合
         */
        getSchoolNameList() {
            let url = '/info/schoolInfo/selectSchoolInfoList';
            callAjaxGetNoParam(url, this.getSchoolNameListSuc);
        },

        getSchoolNameListSuc(data) {
            this.schoolInfoList = data.obj;

        },
        /**
         * 通过学院获取专业名称集合
         * @param value
         */
        getClassNameListBySchooleInfoId(value) {
            let data = {
                schoolInfoId: value,
            };
            let url = "/manage/specialty/selectBySpecialty";
            callAjaxPost(url, data, this.getClassNameListBySchooleInfoIdSuc);
        },
        getClassNameListBySchooleInfoIdSuc(data) {
            this.specialtyList = data.obj;
        },

        /**
         * 通过专业获取班级名称集合
         */
        getClassNameListBySpecialtyId(value) {
            let data = {
                specialtyId: value
            };
            console.log("专业id：" + data.specialtyId);
            let url = "/info/classInfo/listBySpecialtyId";
            callAjaxPost(url, data, this.getClassNameListBySpecialtyIdSuc)

        },

        getClassNameListBySpecialtyIdSuc(data) {
            this.classInfoList = data.obj;
            console.log("班级名称第一位：" + this.classInfoList[0].className);
        },


        /**
         * 表格过滤查询
         */
        filter() {
            /*//检查数据格式
            if (checkEmpty(this.sCourseClassInfo.yearTermId, '请选择学年名称！') ||
                checkEmpty(this.sCourseClassInfo.courseInfoId, '请选择课程名称！') ||
                checkEmpty(this.sCourseClassInfo.courseOutlineId, '请选择课程大纲名称！')
            ) {
                return;
            }*/
            console.log('当前页：' + this.pageNum);
            console.log('页面大小：' + this.pageSize);

            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                yearTermId: this.sCourseClassInfo.yearTermId,
                courseInfoId: this.sCourseClassInfo.courseInfoId,
                courseOutlineId: this.sCourseClassInfo.courseOutlineId,
                choiceCourseNoId: this.sCourseClassInfo.choiceCourseNoId,
                staffId: this.sCourseClassInfo.staffId,
                schoolInfoId: this.sCourseClassInfo.schoolInfoId,
                specialtyId: this.sCourseClassInfo.specialtyId,
                classInfoId: this.sCourseClassInfo.classInfoId,
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
            console.log("教师工号：" + data.obj.list[0].staffCode);
            console.log("班级名称：" + data.obj.list[0].className);
        },


        /**
         * 清除搜索条件
         */
        clearSCourseClassInfo() {
            this.sCourseClassInfo.schoolInfoId = '',
                this.sCourseClassInfo.yearTermId = '',
                this.sCourseClassInfo.courseInfoId = '',
                this.sCourseClassInfo.courseOutlineId = '',
                this.sCourseClassInfo.staffId = '',
                this.sCourseClassInfo.schoolInfoId = '',
                this.sCourseClassInfo.choiceCourseNoId = '',
                this.sCourseClassInfo.specialtyId = '',
                this.sCourseClassInfo.classInfoId = '',
                this.sCourseClassInfo.courseNo = ''
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
         * 通过学年id，教师id，课程大纲id查询:选课课号信息id,选课课号名称
         */
        selectChoiceCourseNo() {
            //检查数据格式
            if (checkEmpty(this.courseClassInfo.yearTermId, '请选择学年名称！') ||
                checkEmpty(this.courseClassInfo.courseInfoId, '请选择课程名称！') ||
                checkEmpty(this.courseClassInfo.courseOutlineId, '请选择课程大纲名称！')
            ) {
                return;
            }
            //通过学年id，教师id，课程大纲id查询:选课课号信息id,选课课号名称
            let data = {
                yearTermId: this.courseClassInfo.yearTermId,
                staffInfoId: this.courseClassInfo.staffId,
                courseOutlineId: this.courseClassInfo.courseOutlineId,
            };
            console.log("学年id:" + data.yearTermId + "教师id:" + data.staffInfoId + "课程大纲id:" + data.courseOutlineId);
            let url = '/info/choiceCourseNo/selectByChoiceCourseNo';
            callAjaxPost(url, data, this.selectByChoiceCourseNoSuc);

        },
        /**
         * 查询:选课课号信息id,选课课号名称回调函数
         */
        selectByChoiceCourseNoSuc(data) {
            if (data.obj == null) {
                this.courseClassInfo.choiceCourseNoId = '';
                this.courseClassInfo.courseNo = '';
                messageWarning("数据库无对应选课号,请重新输入!");
            } else {
                this.courseClassInfo.choiceCourseNoId = data.obj.id;
                this.courseClassInfo.courseNo = data.obj.courseNo;
            }

        },
        /**
         * 打开“新增课程班级信息”模态框
         */
        openAddCourseClassInfoModal() {
            this.initPage();
            this.clearSCourseClassInfo();
            this.addCourseClassInfoModal = true;
        },

        /**
         * 新增课程班级信息
         */
        addCourseClassInfo() {

            // 检查数据格式
            if (checkEmpty(this.courseClassInfo.staffId, '请选择教师名称！') ||
                checkEmpty(this.courseClassInfo.schoolInfoId, '请选择学院名称！') ||
                checkEmpty(this.courseClassInfo.specialtyId, '请选择专业名称！') ||
                checkEmpty(this.courseClassInfo.classInfoId, '请选择班级名称！')
            ) {
                return;
            }
            let data = {
                schoolInfoId: this.courseClassInfo.schoolInfoId,
                yearTermId: this.courseClassInfo.yearTermId,
                courseInfoId: this.courseClassInfo.courseInfoId,
                choiceCourseNoId: this.courseClassInfo.choiceCourseNoId,
                staffId: this.courseClassInfo.staffId,
                classInfoId: this.courseClassInfo.classInfoId,
            };
            console.log("学年id:" + data.yearTermId +
                "教师id:" + data.staffId +
                "课程信息id:" + data.courseInfoId +
                "选课号id为：" + data.choiceCourseNoId +
                "学院id：" + data.schoolInfoId +
                "班级信息id:" + data.classInfoId);//55
            let url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addCourseClassInfoSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },


        /**
         * 新增课程班级信息回调函数
         *
         * @param data
         *            请求返回参数
         */
        addCourseClassInfoSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            console.log(data);
            if (data.obj === 'exist') {
                messageWarning('已存在相同记录');
                return;
            }
            // 关闭模态框
            this.addCourseClassInfoModal = false;
            messageSuccess("新增课程班级信息成功");
            // 重新查询数据
            this.filter();
            // 清除课程班级信息
            this.clearCourseClassInfo();
        },
        /**
         * 取消新增课程
         */
        cancelAddCourseClassInfo() {
            // 关闭模态框
            this.addCourseClassInfoModal = false;
            // 清除课程班级信息
            this.clearCourseClassInfo();
        },

        /**
         * 清除课程班级信息
         */
        clearCourseClassInfo() {
            this.courseClassInfo.id = '';// id
            this.courseClassInfo.yearTermId = '',// 学年id
                this.courseClassInfo.courseInfoId = '',// 课程信息id
                this.courseClassInfo.courseOutlineId = ''// 课程大纲id
            this.courseClassInfo.staffId = '',// 教师id
                this.courseClassInfo.schoolInfoId = '',// 学院id
                this.courseClassInfo.specialtyId = '',// 教师id
                this.courseClassInfo.classInfoId = '',// 班级id
                this.courseClassInfo.choiceCourseNoId = '',// 选课课号id
                this.courseClassInfo.courseNo = ''// 选课号
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
         * 打开批量删除课程信息模态框
         */
        openRemoveCourseClassInfoSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning('请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeCourseClassInfoSelectModal = true;
        },

        /**
         * 批量删除课程数据
         */
        removeCourseClassInfoSelect() {
            // 关闭模态框//666
            this.removeCourseClassInfoModal = false;
            let idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            let data = idList;
            let url = this.firstPath + '/deleteSelection';
            callAjaxPost(url, data, this.removeCourseClassInfoSelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        /**
         * 批量删除课程数据成功回调函数
         */
        removeCourseClassInfoSelectSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess('成功删除 ' + data.obj + ' 条记录');
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },

        /**
         * 单个删除课程信息
         * 打开删除课程信息模态框
         * @param index 当前数据索引
         */
        openRemoveCourseClassInfoModal(index) {
            this.courseClassInfo.id = this.nowData[index].id;
            this.removeCourseClassInfoModal = true;
        },

        /**
         * 单个删除课程班级信息
         *
         * @param index
         */
        removeCourseClassInfo() {
            let data = this.courseClassInfo.id;
            let url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeCourseClassInfoSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        removeCourseClassInfoSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess('课程班级信息删除成功');
            // 重新查询数据
            this.filter();
        },

        /**
         * 关闭学生管理模态框
         */
        closeStudentManageModal() {
            //111
            this.StudentManageColumn.shift(headSelection());
            this.StudentManageModal = false;

        },
        /**
         * 打开学生管理信息模态框
         *
         * @param index
         */
        openStudentManageModal(index) {
            this.courseClassInfo.id = this.nowData[index].id;
            console.log('课程班级信息标识:' + this.courseClassInfo.id);//输出课程班级信息标识
            this.courseClassInfo.className = this.nowData[index].className;
            // 添加多选
            this.StudentManageColumn.unshift(headSelection());
            this.StudentManageFilter();
            // 打开模态框
            this.StudentManageModal = true;
        },

        /**
         * 学生管理过滤查询
         */
        StudentManageFilter() {

            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                courseClassInfoId: this.courseClassInfo.id,
            };
            let url = '/info/studentInfo/selectStudentManage';
            callAjaxPost(url, data, this.StudentManageFilterSuc);
            // 显示加载
            this.loading = true;
        },
        /**
         * 表格过滤查询回调函数
         *
         * @param data
         *            请求返回参数
         */
        StudentManageFilterSuc(data) {
            // 取消显示加载
            this.loading = false;
            this.StudentManageData = data.obj.list;
            this.StudentManageData_BackUp = cloneObj(this.StudentManageData);
            console.log('StudentManageData数据:' + data.obj.list);//输出课程班级信息标识
            this.totalNum = data.obj.total;
            // 再次设置当前页码,若查询记录为空，设为第一页
            this.pageNum = data.obj.pageNum === 0 ? 1 : data.obj.pageNum;
        },


        /**
         * 打开删除学生信息模态框
         */
        openRemoveStudentInfoModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning('请先勾选数据，再批量删除');
                return;
            }
            //关闭学生管理模态框
            this.StudentManageModal = false;
            // 打开学生模态框
            this.removeStudentInfoModal = true;


        },
        /**
         * 批量删除学生数据
         */
        removeStudentInfoSelect() {
            // 关闭模态框
            this.removeStudentInfoInfoModal = false;
            let idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
                console.log('id集合：' + idList[i]);
            }
            let data = idList;
            let url = '/info/courseClassStudentInfo/deleteSelection';
            callAjaxPost(url, data, this.removeStudentInfoSelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
            this.StudentManageModal = true;
        },

        /**
         * 批量删除学生数据成功回调函数
         */
        removeStudentInfoSelectSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess('成功删除 ' + data.obj + ' 条记录');
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.StudentManageFilter();
        },


        /**
         * 打开学生库模态框
         */
        openAddStudentModel() {
            this.selection.length = 0;
            this.getStudentInfoTableHead();//获取表格列名称
            this.initPage();//获取选择器数据
            this.StudentInfoData = '';//表格内容清空
            this.StudentManageModal = false;//关闭学生管理模态框
            this.addStudentModel = true;//打开学生库模态框

        },

        /**
         * 表格表头
         */
        getStudentInfoTableHead() {
            let data = {tableName: "student-family"};
            let url = '/config/tableTitle/listByTableName';
            callAjaxPost(url, data, this.getStudentInfoTableHeadSuc);
        },

        /**
         * 获取表头回调函数
         *
         * @param data
         *            请求返回参数
         */
        getStudentInfoTableHeadSuc(data) {
            // 生成表头
            this.StudentInfoColumn = showCol(data.obj.key, data.obj.title);

            // 添加多选
            this.StudentInfoColumn.unshift(headSelection());
        },
        /**
         * 打开学生管理模态框
         * 并关闭学生库模态框
         */
        returnStudentManageModal() {
            this.addStudentModel = false;
            this.StudentManageModal = true;
            this.clearSStudentInfo();

        },

        /**
         * 关闭学生库页面
         */
        //111
        onCancelStudentInfo() {
            this.StudentManageColumn.shift(headSelection());
            this.addStudentModel = false;
            this.clearSStudentInfo();
            this.StudentInfoData = '';
        },


        /**
         * 学生库过滤查询
         */
        StudentInfoFilter() {
            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                yearPlanId: this.sStudentInfo.yearPlanId,
                schoolInfoId: this.sStudentInfo.schoolInfoId,
                specialtyId: this.sStudentInfo.specialtyId,
                classInfoId: this.sStudentInfo.classInfoId,
                studentName: this.sStudentInfo.studentName,
                courseClassInfoId: this.courseClassInfo.id
            };
            console.log('课程班级信息标识:' + this.courseClassInfo.id);//输出课程班级信息标识
            let url = '/info/studentInfo/listByMessage';
            callAjaxPost(url, data, this.StudentInfoFilterSuc);
            // 显示加载
            this.loading = true;
        },
        /**
         * 表格过滤查询回调函数
         *
         * @param data
         *            请求返回参数
         */
        StudentInfoFilterSuc(data) {
            // 取消显示加载
            this.loading = false;
            this.StudentInfoData = data.obj.list;
            console.log('StudentInfoData数据:' + data.obj.list);//输出课程班级信息标识
            this.totalNum = data.obj.total;
            // 再次设置当前页码,若查询记录为空，设为第一页
            this.pageNum = data.obj.pageNum === 0 ? 1 : data.obj.pageNum;
        },

        /**
         * 清除搜索条件
         */
        clearSStudentInfo() {
            this.sStudentInfo.yearPlanId = '',
                this.sStudentInfo.schoolInfoId = '',
                this.sStudentInfo.specialtyId = '',
                this.sStudentInfo.classInfoId = '',
                this.sStudentInfo.studentName = ''
        },
        /**
         * 确认添加按钮，从学生库添加学生到上课班级
         */
        makeSureAdd() {
            if (this.selection.length === 0) {
                messageWarning('请先勾选数据，再批量删除');
                return;
            }
            this.addStudentInfo();
            this.addStudentModel = false;
            this.StudentManageModal = true;
            this.clearSStudentInfo();
        },


        /**
         * 批量增加学生信息
         */
        addStudentInfo() {
            let studentInfoIdList = [];
            for (let i = 0; i < this.selection.length; i++) {
                studentInfoIdList[i] = this.selection[i].id;
                console.log("学生信息id:" + studentInfoIdList[i]);
            }
            let data = {
                studentInfoIdList,
                courseClassInfoId: this.courseClassInfo.id
            }
            console.log("data中的学生id集合为：" + data.studentInfoIdList);
            console.log("课程班级id为：" + data.courseClassInfoId);
            let url = '/info/courseClassStudentInfo/insertList';
            callAjaxPost(url, data, this.addStudentInfoSuc);
            // 显示加载
            this.loading = true;
        },

        /**
         * 批量增加学生信息成功回调函数
         *
         * @param data
         *            请求返回参数
         */
        addStudentInfoSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess('成功添加 ' + data.obj + ' 条记录');
            // 清除多选列表
            this.selection = [];
            this.StudentManageFilter();

        },
        /**
         * 导出Excel文件
         */
        exportData() {
            let url = '/info/courseClassStudentInfo/excelDownload?className=' + this.courseClassInfo.className + 
            "&courseClassInfoId=" + this.courseClassInfo.id;
            window.location.href = url;
        },

        /**
         * 文件超出个数限制
         */
        handleExceedTextLimit() {
            messageWarning('已超出最大文件个数');
        },


        /**
         * 文件状态改变时的钩子，添加文件、上传成功和上传失败时都会被调用
         * @param file 当前文件
         * @param fileList 文件列表
         */
        handleChangeText(file, fileList) {
            console.log(file)
            this.textFileList = fileList;

        },

        /**
         * 文件列表移除文件时的钩子
         * @param file 移除的文件
         * @param fileList 文件列表
         */

        handleRemoveText(file, fileList) {
            console.log(file);
            this.textFileList = fileList;
        },

        /**
         * 删除文件之前的钩子，参数为上传的文件和文件列表，
         * 若返回 false 或者返回 Promise 且被 reject，则停止删除。
         * @param file 移除的文件
         * @param fileList 文件列表
         */
        beforeRemoveText(file, fileList) {
            return this.$confirm(`确定移除 ${ file.name }？`);
        },

        /**
         * 提交文本表单
         */
        submitTextList() {
            console.log('上传Excel文件');
            //  ======加判断=======

            // 添加文件列表
            for (let i = 0; i < this.textFileList.length; i++) {
                // 文件大小超过最大值
                if (this.textFileList[i].size > this.maxTextSize * 1024 * 1024) {
                    messageWarning('第' + (i + 1) + '个文件' + ':' + this.textFileList[i].name + ' 已超过' + this.maxTextSize + 'mb!');
                    this.textFormData = new FormData(); // 创建新的表单
                    return;
                }
                this.textFormData.append("fileList", this.textFileList[i].raw);
            }
            // 添加文件类型
            this.textFormData.append("fileType", this.textFileType);
            callAjaxFile(this.uploadUrl, this.textFormData, this.submitTextListSuc);

        },
        submitTextListSuc(data) {
            console.log(data);
            if (data.obj === null) {
                messageError('文件上传失败');
                return;
            }
            this.textFormData = new FormData();// 创建新的表单
            this.textFileList = [];// 清空文件列表
            this.courseClassInfo.fileInfoId = data.obj[0];//设置文件id
            let updata = {
                courseClassInfoId: this.courseClassInfo.id,
                fileInfoId: this.courseClassInfo.fileInfoId
            }
            let url = '/info/courseClassStudentInfo/insertByExcel';
            callAjaxPost(url, updata, this.uploadSuc);

        },
        uploadSuc(data) {
            messageSuccess('上传成功');
            this.StudentManageFilter();
        },


    }
});

