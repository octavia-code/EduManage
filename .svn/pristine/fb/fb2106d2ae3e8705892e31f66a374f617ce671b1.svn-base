let vClassStudent = new Vue({
    el: '#classStudent',
    data: function () {
        return {
            firstPath: '/info/classStudent',// 请求一级路径
            nowData: [], column: [], loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            classStudent: {
                id: '', 
                classInfoId: '', 
                studentInfoId: '', 
                studentState: '',
                fileInfoId:''
            },// 实体类
            sClassStudent: {
                schoolInfoId:'',
                specialtyId:'', 
                classInfoId: '',
                yearPlanId: '',
                yearName: '', 
                studentName: '',
                studentNumber: '', 
                codeName: '',
            },// 搜索信息
            addClassStudentModal: false,// 新增班级学生信息模态框
            editClassStudentModal: false,// 编辑班级学生信息模态框
            removeClassStudentSelectModal: false,// 批量删除班级学生信息模态框
            removeClassStudentModal: false,// 删除班级学生信息模态框
      /*      classInfoList: [],//班级集合,
            studentInfoList: [],//学生信息集合*/
            studentStateList: [],//学生状态集合

            schoolInfoList: [],// 学院集合
            specialtyList: [],// 专业集合
            classInfoList: [],// 班级集合
            yearPlanList: [],//学年集合
            studentNameList: [],//学生名称集合
            
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
        this.loading = false;
        /*this.filter();*/
    },
    methods: {
        /**
         * 页面初始化加载项 表格表头
         */
        initPage() {
            this.getTableHead();
            this.getSchoolNameList();
            this.getStudentStateList();
            this.getYearNameList();
        },

        /**
         * 表格表头
         */
        getTableHead() {
            let data = {tableName: "class_student"};
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
                specialtyId: value,
                yearPlanId: this.sClassStudent.yearPlanId,
            };
            console.log("专业id：" + data.specialtyId);
            console.log("学年id：" + data.yearPlanId);
            let url = "/info/classInfo/listBySpecialtyId";
            callAjaxPost(url, data, this.getClassNameListBySpecialtyIdSuc)

        },

        getClassNameListBySpecialtyIdSuc(data) {
            this.classInfoList = data.obj;
        },
        
        /**
         * 获取学生名称集合
         */
        getStudentNameList(value){
        	let data = {
        			yearPlanId: this.sClassStudent.yearPlanId,
        			schoolInfoId: this.sClassStudent.schoolInfoId,
        			specialtyId: this.sClassStudent.specialtyId,
        			classInfoId: value,
        			
        	}
        	let url = '/info/classStudent/listByClassStudent';
        	callAjaxGetNoParam(url, data, this.getStudentNameListSuc);
        	console.log(data);
        },
        getStudentNameListSuc(data){
        	this.studentNameList = data.obj;
        	console.log(this.studentNameList);
        },


        /**
         * 获取学生状态信息集合
         */
        getStudentStateList(){
        	let url = '/info/classStudent/selectStudentStateList';
            callAjaxGetNoParam(url, this.getStudentStateListSuc);
        },
        getStudentStateListSuc(data) {
            this.studentStateList = data.obj;
            console.log(this.studentStateList)
        },
        
        /**
         * 获取学年名称集合
         */
        getYearNameList() {
            let url = '/manage/yearPlan/selectYearPlanList';
            callAjaxGetNoParam(url, this.getYearNameListSuc);
        },

        getYearNameListSuc(data) {
            this.yearPlanList = data.obj;
        },
       
       
        
        /**
         * 通过班级id和学年id获取班级名称集合
         */
//        getClassNameByYearPlanIdSpecialtyId() {
//            let data = {
//                yearPlanId: this.sClassStudent.yearPlanId,
//                classInfoId: this.sClassStudent.classInfoId,
//                specialtyId: this.sClassStudent.specialtyId,
//            };
//            console.log("学年id：" + data.yearPlanId);
//            console.log("班级id：" + data.classInfoId);
//            console.log("专业id：" + data.specialtyId);
//            let url = '/info/classInfo/getClassNameByYearPlanIdSpecialtyId';
//            callAjaxPost(url, data, this.getClassNameSuc);
//        },
//        getClassNameByYearPlanIdSpecialtyIdSuc(data){
//            if (data.obj == null) {
//                this.sCourseClassInfo.yearPlanId = '';
////                this.sCourseClassInfo.classInfoId = '';
//                this.sCourseClassInfo.specialtyId = '';
//                messageWarning(this, "数据库无对应班级,请重新输入!");
//            } else {
//            	this.classInfoList = data.obj;
//            }
//
//            /*console.log("选课号id：" + this.sCourseClassInfo.choiceCourseNoId);
//            console.log("选课号：" + this.sCourseClassInfo.courseNo);*/
//        },	
        

         /**
         * 表格过滤查询
         */
        filter() {
            console.log('当前页：' + this.pageNum);
            console.log('页面大小：' + this.pageSize);
            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                schoolInfoId:this.sClassStudent.schoolInfoId,
                specialtyId:this.sClassStudent.specialtyId,
                classInfoId:this.sClassStudent.classInfoId,
                yearPlanId:this.sClassStudent.yearPlanId,
//                studentName:this.sClassStudent.studentName,
//                studentState:this.sClassStudent.studentState,
//                studentState:this.sClassStudent.codeName,
            };
            console.log("data:表格过滤学年id为："+data.yearPlanId);
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
            console.log("返回学生姓名为："+data.obj.list[0]);
            console.log("返回学生姓名为："+data.obj.list[0].studentName);
            console.log("返回学生状态为："+data.obj.list[0].codeName);
            console.log("返回学生状态为："+data.obj.list[0].studentState);
            // 取消显示加载
            this.loading = false;
            this.nowData = data.obj.list;
            console.log(this.nowData)
            this.totalNum = data.obj.total;
            // 再次设置当前页码,若查询记录为空，设为第一页
            this.pageNum = data.obj.pageNum === 0 ? 1 : data.obj.pageNum;
        },

        /**
         * 清除搜索条件
         */
        clearSClassStudent() {
            this.sClassStudent.schoolInfoId = '';
            this.sClassStudent.specialtyId = '';
            this.sClassStudent.classInfoId = '';
            this.sClassStudent.yearPlanId = '';

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
         * 新增班级学生信息
         */
        addClassStudent() {
       /*     console.log("数据为："+this.classStudent.classInfoId);*/
        	 // 检查数据格式
        	if (this.checkClassStudent()) {
                return;
            }
            // 发送请求
            let data = {
            	classInfoId: this.classStudent.classInfoId,
                studentInfoId: this.classStudent.studentInfoId,
                studentState: this.classStudent.studentState,
            };
            let url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addClassStudentSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        /**
         * 新增班级学生信息回调函数
         *
         * @param data
         *            请求返回参数
         */
        addClassStudentSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            switch (data.code) {
                case 200:
                    // 关闭模态框
                    this.addClassStudentModal = false;
                    messageSuccess( "新增班级学生信息成功");
                    // 重新查询数据
                    this.filter();
                    // 清除班级学生信息
                    this.clearClassStudent();
                    break;
                case 420:
                    messageError(this, data.msg);
                    break;
                default:
                    break;
            }
        },
        
        /**
         * 检查班级学生信息数据格式
         * @return {boolean} 若数据格式错误,返回true
         */
        checkClassStudent() {
            if (checkEmpty(this.classStudent.studentState, '请选择学生状态')) {
//            	checkEmpty(this.classStudent.classInfoId, '请选择班级') || // 检查下拉框是否已选择，不要判断长度
//                checkEmpty(this.classStudent.studentInfoId, '请输入学生标识') ||    // 检查String是否为空
//                checkLength(this.classStudent.studentInfoId, '5', '学生标识不能超过5个字符') ||   // 检查String长度

                return true;
            }
        },


        /**
         * 取消新增学生
         */
        cancelAddClassStudent() {
            // 关闭模态框
            this.addClassStudentModal = false;
            // 清除班级学生信息
            this.clearClassStudent();
        },

        /**
         * 打开编辑班级学生信息模态框
         *
         * @param index
         *            当前数据索引
         */
        openEditClassStudentModal(index) {
            this.classStudent.id = this.nowData[index].id;
//            this.classStudent.classInfoId = this.nowData[index].classInfoId;
//            this.classStudent.studentInfoId = this.nowData[index].studentInfoId;
            this.classStudent.studentState = this.nowData[index].studentState;
            // 打开模态框
            this.editClassStudentModal = true;
        },
        /**
         * 修改班级学生信息
         */
        editClassStudent() {
        	 // 检查数据格式
        	if (this.checkClassStudent()) {
                return;
            }
            let data = {
                id: this.classStudent.id,
                studentState: this.classStudent.studentState,
            };
            let url = this.firstPath + '/update';
            callAjaxPost(url, data, this.editClassStudentSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editClassStudentSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            switch (data.code) {
                case 200:
                    // 关闭模态框
                    this.editClassStudentModal = false;
                    messageSuccess( "班级学生信息修改成功");
                    // 重新查询数据
                    this.filter();
                    // 清除班级学生信息
                    this.clearClassStudent();
                    break;
                case 420:
                    messageError(this, data.msg);
                    break;
                default:
                    break;
            }
        },
        /**
         * 取消修改班级学生信息
         */
        cancelEditClassStudent() {
            // 关闭模态框
            this.editClassStudentModal = false;
            // 清除班级学生信息
            this.clearClassStudent();
        },
        /**
         * 清除班级学生信息
         */
        clearClassStudent() {
            this.classStudent.id = '';
            this.classStudent.classInfoId = '';
            this.classStudent.studentInfoId = '';
            this.classStudent.yearPlanId = '';
            this.classStudent.studentState = '';
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
         * 打开删除多选班级学生信息模态框
         */
        openRemoveClassStudentSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning(this, '请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeClassStudentSelectModal = true;
        },

        /**
         * 批量删除数据
         */
        removeClassStudentSelect() {
            // 关闭模态框
            this.removeClassStudentModal = false;
            let idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            let data = idList;
            let url = this.firstPath + '/deleteSelection';
            callAjaxPost(url, data, this.removeClassStudentSelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        /**
         * 批量删除数据成功回调函数
         */
        removeClassStudentSelectSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '成功删除 ' + data.obj + ' 条记录');
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },

        /**
         * 打开删除班级学生信息模态框
         * @param index 当前数据索引
         */
        openRemoveClassStudentModal(index) {
            this.classstudent.id = this.nowData[index].id;
            this.removeClassStudentModal = true;
        },
        
        /**
         * 删除班级学生信息
         *
         * @param index
         */
        removeClassStudent(index) {
            let data = this.nowData[index].id;
            let url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeClassStudentSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        removeClassStudentSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '班级学生信息删除成功');
            // 重新查询数据
            this.filter();
        },

        exportData(){
        	let url = '/info/classStudent/excelDownload?classInfoId=' + this.sClassStudent.classInfoId;
        	if(!isEmpty(this.sClassStudent.classInfoId)){
            window.location.href = url;
            return;
        	}
        	messageWarning('请选择您想要导出班级名称！');
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
            console.log('this.sClassStudent.classInfoId'+this.sClassStudent.classInfoId)
            if (data.obj === null) {
                messageError('文件上传失败');
                return;
            }
            this.textFormData = new FormData();// 创建新的表单
            this.textFileList = [];// 清空文件列表
            this.classStudent.fileInfoId = data.obj[0];//设置文件id
            let updata = {
                classInfoId: this.sClassStudent.classInfoId,
                fileInfoId: this.classStudent.fileInfoId
            }
            let url = '/info/classStudent/insertByExcel';
            callAjaxPost(url, updata, this.uploadSuc);

            
        },
        uploadSuc(data) {
            messageSuccess('上传成功');
            this.filter();
        },

        uploadConfirm(){
        	if(isEmpty(this.sClassStudent.classInfoId)){
        		messageWarning('请先选择班级名称');
        		return;
        	}
        	this.excelUploadModal=true;
        },
    }
});

