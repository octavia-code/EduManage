let vGradeForm = new Vue({
    el: '#gradeForm',
    data: function () {
        return {
            firstPath: '/score/gradeForm',// 请求一级路径
            nowData: [], StudentManageData: [], loading: false, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象

            gradeForm: {
                id: '',schoolInfoId: '',choiceCourseNoId: '',courseClassInfoId: '',stuState: '',state: '',createdDate: '',stateDate: '',
                yearTermId:'',courseInfoId:'',courseOutlineId:'',staffId:'',schoolInfoId:'',specialtyId:'',classInfoId:'',courseNo:'',
                studentName:'',courseClassStudentInfoId:'',scoreTotalIdList:[],totalScore:'',
            },// 实体类//999

            sGradeForm: {
                yearTermId:'',courseInfoId:'',courseOutlineId:'',staffId:'',schoolInfoId:'',specialtyId:'',classInfoId:'',courseNo:'',
                choiceCourseNoId: '',
            },// 搜索信息

            assessment:{
            	gradeFormId:'',assessmentId:'',courseClassStudentInfoId:'',
            },//课程考核项类
            scoreTotal:{
            	id:'',
            },//总成绩类
            
            addGradeFormModal: false,// 新增教学计划表模态框
            editGradeFormModal: false,// 编辑教学计划表模态框
            removeGradeFormModal: false,// 删除教学计划表模态框
            delayedExamModal: false,//缓考模态框
            missingAnExaminationModal: false,//缺考模态框
            makeUpExaminationModal: false,//补考模态框
            reTakeModal: false,//重修模态框
            reSetModal:false,//重置模态框
            
            
            yearTermList: [],// 学年集合
            courseInfoList: [],// 课程集合
            courseOutlineList:[],// 课程大纲集合
            staffInfoList: [],// 教师集合
            schoolInfoList: [],// 学院集合
            specialtyList: [],// 专业集合
            classInfoList: [],// 班级集合
            courseNoList:[],// 选课课号集合
            courseClassStudentInfoIdList:[],//课程班级信息id集合
            totalScoreList:[],//总成绩集合
            
            
            assessmentIdList: [],// 考核id集合

            column:[],//表格渲染后的表头
            stuColumn: [
                {title: '序号', key: 'seq', width: 60},
                {title: '学号', key: 'studentNumber', width: 150},
                {title: '姓名', key: 'studentName', width: 150},
            ],// 学生固定表头
            
            cloneData: [],// 原数据备份
            changeData: [],// 变更的数据

            assessmentList: [],
            keyList: [],// 对应列内容的字段名集合
            titleList: [], // 列头显示文字集合
            
            scoreList:[],//查询总成绩集合
            scoreIdList:[],//总成绩id集合
            
            excellentNum: 0,// 90分以上的人
            excellentPercent: 0,// 90分以上的人所占比例
            wellNum: 0,// 80-89分（良好）的人
            wellPercent: 0,// 80-89分（良好）的人所占比例
            mediumNum: 0,// 70-79分（中等）的人
            mediumPercent: 0,// 70-79分（中等）的人所占比例
            passNum: 0,// 60-69分（及格）的人
    		passPercent: 0,// 60-69分（及格）的人所占比例
    		failNum: 0,// 不及格（不及格）的人
    		failPercent: 0,// 不及格（不及格）的人所占比例
    		totalNum: 0,// 合计
    		tatolPercent: 0,// 合计所占比例
    		delayedExamNum: 0,// 缓考的人
    		absentNum: 0,// 缺考的人
    		exemptionNum: 0,// 免修的人
    		realTestNum: 0,// 实考的人
    		
    		chocieIndex:-1,//当前选择的行索引
    		examKey:'',//期末考试对应列
    		totalScore: 0,//总评
    		assessRateList:[],//考核比列集合
        }
        
    },
    components: {
        'layout-header': httpVueLoader('/layout/layout-header.vue'),
        'layout-sider': httpVueLoader('/layout/layout-sider.vue'),
        'layout-footer': httpVueLoader('/layout/layout-footer.vue')
    },
    mounted() {
        
        this.initPage();
    },
    methods: {
        /**
		 * 页面初始化加载项 表格表头
		 */
        initPage() {
            // 获取学年集合
            this.getTermNameList();
            // 获取课程名称集合
            this.getCourseNameList();
            // 获取教师集合
            this.getStaffNameList();
            // 获取学院名称集合
            this.getSchoolNameList();
            //获取总成绩集合
//            this.getScoreList();
         
        },
        /**
         * 获取总成绩集合
         */
        getScoreList(){
        	 let url = '/score/scoreTotal/selectScoreTotalList';
             callAjaxGetNoParam(url, this.getScoreListSuc);
         },
         getScoreListSuc(data) {
             this.scoreList = data.obj;
             for(let i = 0;i < data.obj.length;i ++){
            	 this.scoreIdList[i] = data.obj[i].id;
             }
             console.log("总成绩集合：",this.scoreList);
             console.log("总成绩集合：",this.scoreIdList);
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
		 * 
		 * @param value
		 */
        getCourseOutlineList(value){
            let data={
                courseInfoId:value
            };
            let url='/outline/courseOutline/listByCourseOutline';
            callAjaxPost(url, data , this.getCourseOutlineListSuc);
        },
        getCourseOutlineListSuc(data){
            this.courseOutlineList=data.obj;
            console.log("courseOutlineList第一位id：" + this.courseOutlineList[0].id);
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
            /* console.log("教师:"+this.staffInfoList[0].staffName); */
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
		 * 通过学院id获取专业名称集合
		 * 
		 * @param value
		 */
        getClassNameListBySchooleInfoId(value){
            let data={
                schoolInfoId:value,
            };
            let url="/manage/specialty/selectBySpecialty";
            callAjaxPost(url,data,this.getClassNameListBySchooleInfoIdSuc);
        },
        getClassNameListBySchooleInfoIdSuc(data){
            this.specialtyList=data.obj;
        },

        /**
		 * 通过专业id获取专业名称集合
		 */
        getClassNameListBySpecialtyId(value){
            let data={
//            	yearPlanId:18,
                specialtyId:value
            	
            };
            console.log("专业id："+data.specialtyId);
            let url="/info/classInfo/listBySpecialtyId";
            callAjaxPost(url,data,this.getClassNameListBySpecialtyIdSuc)
        },

        getClassNameListBySpecialtyIdSuc(data){
            this.classInfoList=data.obj;
            console.log("班级名称第一位："+this.classInfoList[0].className);
        },
// 777
        // 通过课程大纲id，学年id、教师id查询选课号
        getCourseNoList() {
        	// 检查数据格式
            if (checkEmpty(this.sGradeForm.yearTermId, '请选择学年名称！') ||
                checkEmpty(this.sGradeForm.courseInfoId, '请选择课程名称！') ||
                checkEmpty(this.sGradeForm.courseOutlineId, '请选择课程大纲名称！')
            ) {
                return;
            }
            // 发送请求
            let data = {
                yearTermId: this.sGradeForm.yearTermId,
                courseOutlineId: this.sGradeForm.courseOutlineId,
                staffInfoId: this.sGradeForm.staffId
            };
            console.log(data);
            let url = '/info/choiceCourseNo/selectByChoiceCourseNo';
            callAjaxPost(url, data, this.getCourseNoSuc);
        },
        
        /**
		 * 在添加模态框通过课程大纲id，学年id、教师id查询选课号
		 */
        getCourseNoListInAddModel(){
        	 // 检查数据格式
            if (checkEmpty(this.gradeForm.yearTermId, '请选择学年名称！') ||
                checkEmpty(this.gradeForm.courseInfoId, '请选择课程名称！') ||
                checkEmpty(this.gradeForm.courseOutlineId, '请选择课程大纲名称！')
            ) {
                return;
            }
        	 // 发送请求
            let data = {
                yearTermId: this.gradeForm.yearTermId,
                courseOutlineId: this.gradeForm.courseOutlineId,
                staffInfoId: this.gradeForm.staffId
            };
            let url = '/info/choiceCourseNo/selectByChoiceCourseNo';
            callAjaxPost(url, data, this.getCourseNoSuc);
        },
        getCourseNoSuc(data) {
            console.log("--------");
            this.sGradeForm.courseNo = data.obj.courseNo;
            this.sGradeForm.choiceCourseNoId = data.obj.id;
            
            this.gradeForm.courseNo = data.obj.courseNo;
            this.gradeForm.choiceCourseNoId = data.obj.id;
            console.log("选课号id：" + this.gradeForm.choiceCourseNoId);
            console.log("选课号：" + this.gradeForm.courseNo);
            this.getCourseClassIdList();// 55
        },
        
        /**
		 * 通过选课号id查询课程班级id集合
		 */
        getCourseClassIdList() {
            let data = {
                choiceCourseNoId: this.gradeForm.choiceCourseNoId,
            };
            console.log("data里choiceCourseNoId数据为：" + data.choiceCourseNoId);
            let url = '/info/courseClassInfo/selectByChoiceNoId';
            callAjaxPost(url, data, this.getCourseClassIdListSuc);
            console.log("-------");
        },
        getCourseClassIdListSuc(data) {
            console.log("第一个classInfoId：" + data.obj[0].className);
            this.courseClassInfoIdList = data.obj;
        },
        
        /**
		 * 通过班级id获取课程班级id
		 */
        getClassIdList() {
            let data = {
                classId: this.gradeForm.classId,
            };
            console.log("classId里choiceCourseNoId数据为：" + data.classId);
            let url = '/info/courseClassInfo/selectByClassId';
            callAjaxPost(url, data, this.getClassIdListSuc);
            console.log("-------");
        },
        getClassIdListSuc(data) {
            console.log("第一个classInfoId：" + data.obj.id);
            this.courseClassInfoId = data.obj;
        },
        
        /**
		 * 通过学院id 学年id 课程id 选课号id 教工id 班级id 查询课程班级id
		 */
        getCourseClassInfoId(){
            let data = {
            		schoolInfoId:this.sGradeForm.schoolInfoId,
                    yearTermId:this.sGradeForm.yearTermId,
                    courseInfoId:this.sGradeForm.courseInfoId,
                    choiceCourseNoId:this.sGradeForm.choiceCourseNoId,
                    staffId:this.sGradeForm.staffId,
                    classInfoId:this.sGradeForm.classInfoId
            };
           
            console.log("学院id位："+data.schoolInfoId);
            console.log("学年id位："+data.yearTermId);
            console.log("课程id位："+data.courseInfoId);
            console.log("选课号id位："+data.choiceCourseNoId);
            console.log("教工id位："+data.staffId);
            console.log("班级id位："+data.classInfoId);
            let url = '/info/courseClassInfo/getCourseClassInfoId';
            callAjaxPost(url, data, this.getCourseClassInfoIdSuc);
        },
        /**
		 * 通过学院id 学年id 课程id 选课号id 教工id 班级id 查询课程班级id
		 */
        getCourseClassInfoIdInAddModel(){
            let data = {
            		schoolInfoId:this.gradeForm.schoolInfoId,
                    yearTermId:this.gradeForm.yearTermId,
                    courseInfoId:this.gradeForm.courseInfoId,
                    choiceCourseNoId:this.gradeForm.choiceCourseNoId,
                    staffId:this.gradeForm.staffId,
                    classInfoId:this.gradeForm.classInfoId
            };
            console.log("----学院id：" + data.schoolInfoId);
            let url = '/info/courseClassInfo/getCourseClassInfoId';
            callAjaxPost(url, data, this.getCourseClassInfoIdSuc);
        },
        
        getCourseClassInfoIdSuc(data){
            console.log("--------");
            this.sGradeForm.courseClassInfoId = data.obj;
            
            this.gradeForm.courseClassInfoId = data.obj;
            
            console.log("课程班级信息id：" + this.gradeForm.courseClassInfoId);
            this.getGradeFromId();
        },
        /**
		 * 通过学院id 选课号id 课程班级id查询成绩单id
		 */
        getGradeFromId(){
            let data={
                schoolInfoId: this.sGradeForm.schoolInfoId,
                choiceCourseNoId: this.sGradeForm.choiceCourseNoId,
                courseClassInfoId: this.sGradeForm.courseClassInfoId,
            }
            console.log("-------",data);
            let url = this.firstPath + '/selectByGradeFromId';
            callAjaxPost(url, data, this.getGradeFromIdSuc);
        },
        getGradeFromIdSuc(data){
            this.gradeForm.id = data.obj;
            console.log("成绩单id：" + this.gradeForm.id);
        },
        /**
		 * 添加成绩单
		 */
        addGradeForm() {
            let data = {
                schoolInfoId: this.gradeForm.schoolInfoId,
                choiceCourseNoId: this.gradeForm.choiceCourseNoId,
                courseClassInfoId: this.gradeForm.courseClassInfoId,
            };
            console.log("学院id为:" + data.schoolInfoId);
            console.log("选课号id为:" + data.choiceCourseNoId);
            console.log("课程班级id为:" + data.courseClassInfoId);
            let url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addGradeFormSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        /**
		 * 新增教学计划表回调函数
		 * 
		 * 
		 * @param data
		 *            请求返回参数
		 */
        addGradeFormSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
         // 关闭模态框
            this.addGradeFormModal = false;
            messageSuccess( "新增成绩单成功");
            // 重新查询数据
// this.filter();
            // 清除教学计划表
            this.clearGradeForm();
        },
        /**
		 * 取消新增课程
		 */
        cancelAddGradeForm() {
            // 关闭模态框
            this.addGradeFormModal = false;
            // 清除教学计划表
            this.clearGradeForm();
        },
        
        
        
        /**
		 * 通过班级课程id查询学生信息
		 */
        StudentManageFilter() {
            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                courseClassInfoId: this.gradeForm.courseClassInfoId,
            };
            console.log("-------" );
            console.log("课程班级id为：" + data.courseClassInfoId);
            let url = '/info/studentInfo/selectPageInfo2';
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
            console.log('StudentManageData数据:' + data.obj.list[0].studentName);// 输出课程班级信息标识
            this.totalNum = data.obj.total;
            // 再次设置当前页码,若查询记录为空，设为第一页
            this.pageNum = data.obj.pageNum === 0 ? 1 : data.obj.pageNum;
        },

        
        /**
		 * 清除搜索条件
		 */
        clearSGradeForm() {// 55
            this.sGradeForm.yearTermId = '';
            this.sGradeForm.courseInfoId = '';
            this.sGradeForm.courseOutlineId = '';
            this.sGradeForm.staffId = '';
            this.sGradeForm.schoolInfoId = '';
            this.sGradeForm.specialtyId = '';
            this.sGradeForm.classInfoId = '';
            this.sGradeForm.courseNo = '';
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
		 * 打开编辑教学计划表模态框
		 * 
		 * @param index
		 *            当前数据索引
		 */
        openEditGradeFormModal(index) {
            this.gradeForm.id = this.nowData[index].id;
            console.log('gradeForm.id:' + this.gradeForm.id);
            this.gradeForm.schoolInfoId = this.nowData[index].schoolInfoId;
            this.gradeForm.choiceCourseNoId = this.nowData[index].choiceCourseNoId;
            this.gradeForm.courseClassInfoId = this.nowData[index].courseClassInfoId;
            this.gradeForm.state = this.nowData[index].state;
            this.gradeForm.createdDate = this.nowData[index].createdDate;
            this.gradeForm.stateDate = this.nowData[index].stateDate;
            // 打开模态框
            this.editGradeFormModal = true;
        },

        /**
		 * 修改教学计划表
		 */
        editGradeForm() {
            let data = {
                id: this.gradeForm.id = '',
                schoolInfoId: this.gradeForm.schoolInfoId = '',
                choiceCourseNoId: this.gradeForm.choiceCourseNoId = '',
                courseClassInfoId: this.gradeForm.courseClassInfoId = '',
                state: this.gradeForm.state = '',
                createdDate: this.gradeForm.createdDate = '',
                stateDate: this.gradeForm.stateDate = '',
            };
            let url = this.firstPath + '/update';
            callAjaxPost(url, data, this.editGradeFormSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editGradeFormSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            switch (data.code) {
                case 200:
                    // 关闭模态框
                    this.editGradeFormModal = false;
                    messageSuccess( "教学计划表修改成功");
                    // 重新查询数据
                    this.filter();
                    // 清除教学计划表
                    this.clearGradeForm();
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
        cancelEditGradeForm() {
            // 关闭模态框
            this.editGradeFormModal = false;
            // 清除教学计划表
            this.clearGradeForm();
        },
        /**
		 * 清除教学计划表
		 */
        clearGradeForm() {
            this.gradeForm.id = '';
            this.gradeForm.schoolInfoId = '';
            this.gradeForm.choiceCourseNoId = '';
            this.gradeForm.courseClassInfoId = '';
            this.gradeForm.state = '';
            this.gradeForm.createdDate = '';
            this.gradeForm.stateDate = '';
            this.gradeForm.yearTermId = '';
            this.gradeForm.courseInfoId = '';
            this.gradeForm.courseOutlineId = '';
            this.gradeForm.staffId = '';
            this.gradeForm.schoolInfoId = '';
            this.gradeForm.specialtyId = '';
            this.gradeForm.classInfoId = '';
            this.gradeForm.courseNo = '';
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
        openRemoveGradeFormModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning(this, '请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeGradeFormModal = true;
        },

        /**
		 * 批量删除数据
		 */
        removeGradeFormSelect() {
            // 关闭模态框
            this.removeGradeFormModal = false;
            let idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            let data = idList;
            let url = this.firstPath + '/deleteSelection';
            callAjaxPost(url, data, this.removeGradeFormSelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        /**
		 * 批量删除数据成功回调函数
		 */
        removeGradeFormSelectSuc(data) {
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
        removeGradeForm(index) {
            let data = this.nowData[index].id;
            let url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeGradeFormSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        removeGradeFormSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '教学计划表删除成功');
            // 重新查询数据
            this.filter();
        },
// 666
        /**
		 * 通过成绩登记表id查询课程考核集合
		 */
        getAssessmentList() {
            let data = 1/*this.sGradeForm.courseOutlineId*/;
            console.log("课程大纲id:"+data);
            let url = '/outline/assessment/listByCourseOutlineId';
            callAjaxPost(url, data, this.getAssessmentListSuc);
        },
        getAssessmentListSuc(data) {
            console.log(data);
            this.assessmentList = data.obj;
            // 考核id集合
            for (let i = 0; i < this.assessmentList.length; i++) {
                this.assessmentIdList.push(this.assessmentList[i].id);
            }
            console.log("课程考核集合:",this.assessmentIdList);
            this.setTableHead();
           
        },
        /**
		 * 设置动态加载的表头
		 */
        setTableHead() {
        	this.keyList = [];
        	this.titleList = [];
            // 将考核集合中的数据转为 key 和 title 集合
            for (let i = 0; i < this.assessmentList.length; i++) {
                // key:score_assessmentId
                this.keyList.push("score_" + this.assessmentList[i].id);
                this.titleList.push(this.assessmentList[i].assessName + "(" + this.assessmentList[i].assessRate + "%)");
                //获取考核比列集合
                this.assessRateList[i] = this.assessmentList[i].assessRate;
                if(this.assessmentList[i].assessName=="期末考试"){
                	//期末考试对应成绩
                	this.examKey="score_" + this.assessmentList[i].id;
                }
            }
            console.log("***",this.assessRateList);
            this.column = cloneObj(this.stuColumn);
            // 动态添加表头
            this.column = showColMoreInputNumberWithButton(this, this.column, this.keyList, this.titleList, this.test, this.countTotalScore);
            //总评表头相关配置
            let totalScoreColumn={
            		 title:'总评',
            		 key: 'totalScore',
            		 align:'center',
            		 render:(h,params)=>{
            			 	return h('span',this.totalScoreList[params.index])
            		 }
            }
            //添加总评
            this.column.push(totalScoreColumn);
        	// 添加自定义slot-scope
            this.column.push(headActionSlot());
            // 获取成绩，防止成绩渲染比表头快
            this.getStudentWithScore();
            this.countTotalScore();
            
        },
        /*
         * 计算总评成绩
         */
        countTotalScore(){
            //循环获取考核项成绩
            for(var g = 0;g < this.nowData.length; g++){
            	this.totalScoreList[g]=0;
            	for(var t=0;t < this.keyList.length;t++){
            		this.totalScoreList[g]=this.totalScoreList[g]+(this.nowData[g][this.keyList[t]] * this.assessRateList[t]/100);
            	}
            	this.totalScoreList[g]=this.totalScoreList[g].toFixed(2);
/*            	var s = 0;
            	this.totalScoreList[g] = (this.nowData[g][this.keyList[s]] * this.assessRateList[s]/100
            	+this.nowData[g][this.keyList[s+1]] * this.assessRateList[s+1]/100
            	+this.nowData[g][this.keyList[s+2]] * this.assessRateList[s+2]/100
            	+this.nowData[g][this.keyList[s+3]] * this.assessRateList[s+3]/100).toFixed(2);*/
            }//777
            console.log("***************总评集合："+ this.totalScoreList);
        },
        /**
         * 页面传值
         */
        test(selectAssessmentId,selectTitlte){
/*        	let scoreMode="一般";
        	if(selectTitlte.indexOf("期末")!=-1){
        		scoreMode="考试";
        	}*/
        	console.log(selectAssessmentId);
            let params = {
              assessmentId:selectAssessmentId,
              classId:this.sGradeForm.classInfoId,
              choiceCourseNoId:this.sGradeForm.choiceCourseNoId,
/*              scoreMode:scoreMode,*/
            };
            let url='/score/scoreAssessItem.html?assessmentId='+params.assessmentId+'&classId='+params.classId+'&choiceCourseNoId='+params.choiceCourseNoId/*+'&scoreMode='+params.scoreMode*/;
            window.open(url);
/*            scoreMode="一般";*/
        },
        
        /**
		 * 获取学生和对应考核成绩的信息
		 */
        getStudentWithScore() {
            let data = {
                gradeFormId: 17/*this.gradeForm.id*/,
                courseOutlineId: 1/*this.sGradeForm.courseOutlineId*/,
                courseClassInfoId: 26/*this.sGradeForm.courseClassInfoId*/
            };
            console.log("------")
            console.log(data);
            let url = '/info/courseClassStudentInfo/listStudentWithScore';
            callAjaxPost(url, data, this.getStudentWithScoreSuc);
        },
        getStudentWithScoreSuc(data) {
            console.log(data);
            var count = 0;
            this.nowData = data.obj;
            console.log("***********++++++",this.nowData);
            for(let r = 0;r < this.nowData.length; r++){
            	this.courseClassStudentInfoIdList[r]=this.nowData[r].courseClassStudentInfoId;
            /*	console.log("赋值数据：",this.courseClassStudentInfoIdList[r]);//888
             */
            	this.gradeForm.scoreTotalIdList[r]=this.nowData[r].scoreTotalId;
            }
            console.log("----+++"+this.gradeForm.scoreTotalIdList)
            // 遍历表格数据中的json对象
            for (let j = 0; j < this.nowData.length; j++) {
                // json对象添加属性，score_assessmentId
                for (let k = 0; k < this.keyList.length; k++) {
                    let score_assessmentId = this.keyList[k]; // 以表头key创建成绩属性
                    this.nowData[j][score_assessmentId] = ''; // 给json对象添加成绩属性
                    let score_assessmentId_id = score_assessmentId + '_id'; // 成绩id属性=成绩属性+id
                    this.nowData[j][score_assessmentId_id] = '';
                    // 循环当前数据中的考核成绩集合
                    for (let f = 0; f < this.nowData[j].scoreAssessmentList.length; f++) {
                        // 从表头key集合中获取考核id
                        let assessmentId = this.keyList[k].split("_")[1];
                        // 考核成绩集合中的考核id 和 表头key集合中获取考核id相等
                        if (this.nowData[j].scoreAssessmentList[f].assessmentId == assessmentId) {
                            // 给成绩属性赋值
                            this.nowData[j][score_assessmentId] = this.nowData[j].scoreAssessmentList[f].score;
                            // 给成绩id属性赋值
                            this.nowData[j][score_assessmentId_id] = this.nowData[j].scoreAssessmentList[f].scoreAssessmentId;
                            this.nowData[j]["stuState"] = 'A';
                        }
                        
                        count++;
                    }
                }
                // 删除不必要的属性
                 delete this.nowData[j].scoreAssessmentList;
            }
            console.log(this.nowData);
            
            this.countTotalScore();
            
            // 循环获取期末考试成绩
            for(let b = 0; b < this.nowData.length; b++){
            	var scoreTemp = this.nowData[b][this.keyList[0]];
            	console.log("期末成绩---为："+scoreTemp);
            	switch(true){
	            	case scoreTemp >= 90 :
	            		this.excellentNum ++;
	            		break;
	            	case (scoreTemp >= 80 && scoreTemp < 90) :
	            		this.wellNum ++;
	            		break;
	            	case (scoreTemp >= 70 && scoreTemp < 80) :
	            		this.mediumNum ++;
	            		break;
	            	case (scoreTemp >= 60 && scoreTemp < 70) :
	            		this.passNum ++;
	            		break;
	            	case scoreTemp < 60 :
	            		this.failNum ++;
	            		break;
	            	case scoreTemp = 0 :
	            		this.absentNum ++;
	            		break;
	            	default:
	            		break;
            	}
            }
            this.totalNum = this.excellentNum + this.wellNum + this.mediumNum + this.passNum + this.failNum;
            this.excellentPercent =  (this.excellentNum / this.totalNum * 100).toFixed(2);
            this.wellPercent = (this.wellNum / this.totalNum * 100).toFixed(2);
            this.mediumPercent = (this.mediumNum / this.totalNum * 100).toFixed(2);
            this.passPercent = (this.passNum / this.totalNum * 100).toFixed(2);
            this.failPercent = (this.failNum / this.totalNum * 100).toFixed(2);
            this.tatolPercent = (this.totalNum / this.totalNum * 100).toFixed(2);
            this.realTestNum = this.totalNum;
            
            console.log('循环的次数：' + count);
            this.cloneData = cloneObj(this.nowData); // 备份原数据
            console.log('数据备份完成', this.nowData, this.cloneData);
        },
        
        
        /**
		 * 添加表格所有的考核成绩
		 */
        addAllScoreAssessment() {
            for (let i = 0; i < this.nowData.length; i++) {
                console.log(this.nowData[i].seq, Compare(this.nowData[i], this.cloneData[i]));
                // 判断数据与原数据是否相等
                let isEqule = Compare(this.nowData[i], this.cloneData[i]);
                // 不相等即修改过的数据，添加到变更集合中
                if (!isEqule) {
                    this.changeData.push(this.nowData[i]);
                }
            }
            console.log(this.changeData, this.nowData, this.cloneData);
            if (this.changeData.length == 0) {
                messageInfo('没有修改过的数据');
                return;
            }

            let url = '/score/scoreAssessment/insertOrUpdateList';
            let data = {
            	scoreIdList: this.scoreIdList,
            	//期末考试对应成绩
            	examKey: this.examKey,
                assessmentIdList: this.assessmentIdList,
                scoreAssessmentList: this.changeData,
            };
            console.log(data);
            callAjaxPost(url, data, this.addAllScoreAssessmentSuc);
        },
        addAllScoreAssessmentSuc(data) {
            messageSuccess('提交成功');
            // 重新查询数据
            this.getStudentWithScore();
//            this.addScoreTotal();
        },
        
        /**
         * 插入总成绩表
         */
        addScoreTotal(){
        	let url = '/score/scoreTotal/insert';
        	console.log("couIdList数据：",this.courseClassStudentInfoIdList);
            let data = {
            	idList:this.gradeForm.scoreTotalIdList,
            	//couId课程班级学生信息标识	
            	couIdList:this.courseClassStudentInfoIdList,
            	/*studentState:this.nowData[this.chocieIndex].stuState,*/
            	totalScoreList:this.totalScoreList,
            };
            console.log("即将插入的data数据：",data);//888
            callAjaxPost(url, data, this.addScoreTotalSuc);
        },
        addScoreTotalSuc(data) {
            messageSuccess('提交成功');
            //获取总成绩集合
            this.getScoreList();
            
            console.log("查询的总成绩id",this.scoreIdList);
            this.addAllScoreAssessment();
        },
        
        /**
         * 打开缓考模态框
         */
        openDelayedExamModal(index){
        	this.assessment.gradeFormId = this.gradeForm.id;
        	this.assessment.assessmentId = this.assessmentList;
        	this.assessment.courseClassStudentInfoId = this.courseClassStudentInfoIdList;
        	this.delayedExamModal = true;//缓考模态框
        	this.chocieIndex=index;
        },
        delayedExam(){
        	this.nowData[this.chocieIndex].stuState = "H";
        	this.column = cloneObj(this.stuColumn);
            // 动态添加表头
            this.column = showColMoreInputNumberWithButton(this, this.column, this.keyList, this.titleList, this.test,this.countTotalScore);
        	// 添加自定义slot-scope
            this.column.push(headActionSlot());          
            for(let i=0;i<this.titleList.length;i++){
            	if(this.assessmentList[i].assessName=="期末考试"){
            		this.nowData[this.chocieIndex][this.keyList[i]]=0;
            	}else{
            		this.nowData[this.chocieIndex][this.keyList[i]]=this.cloneData[this.chocieIndex][this.keyList[i]];
            	}
            }
            let url = '/score/scoreAssessment/updateState';
            let data = {
            		stuState:"H",
            		id:this.nowData[this.chocieIndex].score_1_id
            };
            console.log("*******",data);
            callAjaxPost(url, data, this.delayedExamSuc);
        },
        delayedExamSuc(data) {
            messageSuccess('该同学以设置为缓考!');
        },
        /**
         * 打开缺考模态框
         */
        openMissingAnExaminationModal(index){
        	this.missingAnExaminationModal = true;//缺考模态框
        	this.chocieIndex=index;
        },
        missingAnExamination(){
        	this.nowData[this.chocieIndex].stuState = "Q";
        	this.column = cloneObj(this.stuColumn);
            // 动态添加表头
            this.column = showColMoreInputNumberWithButton(this, this.column, this.keyList, this.titleList, this.test,this.countTotalScore);
        	// 添加自定义slot-scope
            this.column.push(headActionSlot());
            for(let i=0;i<this.titleList.length;i++){
            	if(this.assessmentList[i].assessName=="期末考试"){
            		this.nowData[this.chocieIndex][this.keyList[i]]=0;
            	}else{
            		this.nowData[this.chocieIndex][this.keyList[i]]=this.cloneData[this.chocieIndex][this.keyList[i]];
            	}
            }
            let url = '/score/scoreAssessment/updateState';
            let data = {
            		stuState:"Q",
            		id:this.nowData[this.chocieIndex].score_1_id,
            };
            console.log("*******",data);
            callAjaxPost(url, data, this.missingAnExaminationSuc);
        },
        missingAnExaminationSuc(data) {
            messageSuccess('该同学以设置为缺考!');
        },
        /**
         * 打开补考模态框
         */
        openMakeUpExaminationModal(index){
        	this.makeUpExaminationModal = true;//补考模态框
        	this.chocieIndex=index;
        },
        makeUpExamination(){
        	this.nowData[this.chocieIndex].stuState = "B";
        	this.column = cloneObj(this.stuColumn);
            // 动态添加表头
            this.column = showColMoreInputNumberWithButton(this, this.column, this.keyList, this.titleList, this.test,this.countTotalScore);
        	// 添加自定义slot-scope
            this.column.push(headActionSlot());
            for(let i=0;i<this.titleList.length;i++){
            	if(this.assessmentList[i].assessName!="期末考试"){
            		this.nowData[this.chocieIndex][this.keyList[i]]=0;
            	}else{
            		this.nowData[this.chocieIndex][this.keyList[i]]=this.cloneData[this.chocieIndex][this.keyList[i]];
            	}
            }
            let url = '/score/scoreAssessment/updateState';
            let data = {
            		stuState:"B",
            		id:this.nowData[this.chocieIndex].score_1_id,
            };
            console.log("*******",data);
            callAjaxPost(url, data, this.makeUpExaminationSuc);
        },
        makeUpExaminationSuc(data) {
            messageSuccess('该同学以设置为补考!');
        },
        /**
         * 打开重修模态框
         */
        openreTakeModal(index){
        	this.reTakeModal = true;//重修模态框
        	this.chocieIndex=index;
        },
        reTake(){
        	this.nowData[this.chocieIndex].stuState = "C";
        	this.column = cloneObj(this.stuColumn);
            // 动态添加表头
            this.column = showColMoreInputNumberWithButton(this, this.column, this.keyList, this.titleList, this.test,this.countTotalScore);
        	// 添加自定义slot-scope
            this.column.push(headActionSlot());
            
            let url = '/score/scoreAssessment/updateState';
            let data = {
            		stuState:"C",
            		id:this.nowData[this.chocieIndex].score_1_id,
            };
            console.log("*******",data);
            callAjaxPost(url, data, this.reTakeSuc);
        },
        reTakeSuc(data) {
            messageSuccess('该同学以设置为重修!');
        },
        /**
         * 打开重置模态框
         */
        openreSetModal(index){
        	this.reSetModal = true;//重置模态框
        	this.chocieIndex=index;
        },
        reSet(){
        	this.nowData[this.chocieIndex].stuState = "A";
        	this.column = cloneObj(this.stuColumn);
            // 动态添加表头
            this.column = showColMoreInputNumberWithButton(this, this.column, this.keyList, this.titleList, this.test,this.countTotalScore);
        	// 添加自定义slot-scope
            this.column.push(headActionSlot());
            for(let i=0;i<this.titleList.length;i++){
            		this.nowData[this.chocieIndex][this.keyList[i]]=this.cloneData[this.chocieIndex][this.keyList[i]];
            }
        },
        
    }
});

