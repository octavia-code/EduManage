let vChoiceCourseNo = new Vue({
    el: '#choiceCourseNo',
    data: function () {
        return {
            firstPath: '/info/choiceCourseNo',// 请求一级路径
            nowData: [], column: [], loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            choiceCourseNo: {
                id: '', courseNo: '', yearTermId: '',termName:'', staffInfoId: '',staffCode: '',courseOutlineId: '',courseCode:'', remark: '', createdDate: '',
                stateDate: '', state: '',staffName:'',outlineName:''
            },
            
			// 实体类            
            sChoiceCourseNo: {
            	termName:'',outlineName:'',staffName:''
            },// 搜索信息
            choiceCourseNoTemp: '',// 修改临时存放信息
            addChoiceCourseNoModal: false,// 新增选课信息模态框
            editChoiceCourseNoModal: false,// 编辑选课信息模态框
            removeChoiceCourseNoSelectModal: false,
            removeChoiceCourseNoModal: false,// 删除选课信息模态框

            yearTermList: [],//学年集合
            staffInfoList: [],//课程教师集合
            courseOutlineList: [],//课程大纲集合
            courseInfoList: [],//课程信息集合
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
            let data = {tableName: "choice_course_no"};
            let url = '/config/tableTitle/listByTableName';
            callAjaxPost(url, data, this.getTableHeadSuc);

            this.getYearTermList();
            this.getStaffInfoList();
            this.getCourseOutlineList();
            this.getCourseInfoList();
        },
        /**
         * 获取学年集合
         */
        getYearTermList() {
            let url = '/manage/yearTerm/selectYearTermList';
            callAjaxGetNoParam(url, this.getYearTermListSuc);
        },

        getYearTermListSuc(data) {
            this.yearTermList = data.obj;
        },
        /**
         * 获取教工信息集合
         */
        getStaffInfoList() {
            let url = '/info/staffInfo/selectStaffInfoList';
            callAjaxGetNoParam(url, this.getStaffInfoListSuc);
        },

        getStaffInfoListSuc(data) {
            this.staffInfoList = data.obj;
        },
        /**
         * 获取课程信息集合
         */
        getCourseInfoList(){
        	let url = '/manage/courseInfo/listCourseInfo';
        	callAjaxGetNoParam(url, this.getCourseInfoListSuc);
        },
        
        getCourseInfoListSuc(data){
        	this.courseInfoList = data.obj;
        },
        /**
         * 获取课程大纲信息集合
         */
        getCourseOutlineList(){
        	let url = '/outline/courseOutline/selectCourseOutlineList';
        	callAjaxGetNoParam(url, this.getCourseOutlineListSuc);
        },
        
        getCourseOutlineListSuc(data){
        	this.courseOutlineList = data.obj;
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
         * 备注详情按钮
         *
         */
        showRemark(index) {
            this.choiceCourseNo.remark = this.nowData[index].remark;
            alert(this.choiceCourseNo.remark);
        },

        /**
         * 表格过滤查询
         */
        filter() {
             //检查数据格式
//            if (checkLength(this.sChoiceCourseNo.courseNo, '200', '选课课号不能超过200字符')) {
//                return;
//            }
            console.log('当前页：' + this.pageNum);
            console.log('页面大小：' + this.pageSize);

            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                termName:this.sChoiceCourseNo.termName,
                outlineName:this.sChoiceCourseNo.outlineName,
                staffName:this.sChoiceCourseNo.staffName
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
            //console.log(data.obj.list);
            this.totalNum = data.obj.total;
            // 再次设置当前页码
            this.pageNum = data.obj.pageNum;
        },

        /**
         * 清除搜索条件
         */
        clearSChoiceCourseNo() {
        	this.sChoiceCourseNo.termName = '';
            this.sChoiceCourseNo.outlineName = '';
            this.sChoiceCourseNo.staffName = '';
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
         * 改变学年下拉框
         * @Param value 选中的数据的id
         */
        changeYearTerm(value){
        	if(!isEmpty(value)){
        		for(let i= 0;i<this.yearTermList.length;i++){
        			if(this.yearTermList[i].id === value){
        				this.choiceCourseNo.termName=this.yearTermList[i].termName;
        				console.log('-------');
        				console.log(this.choiceCourseNo.termName);
        			}
        		}
        	}
        },
        /**
         * 改变大纲下拉框
         * @Param value 选中的数据的id
         */
        changeCourseOutlineId(value){        	
        	if(!isEmpty(value)){           	
        		for(let i= 0;i<this.courseInfoList.length;i++){
        			if(this.courseInfoList[i].id === value){
        				this.choiceCourseNo.courseCode=this.courseInfoList[i].courseCode;
//        		this.choiceCourseNo.courseCode=this.courseInfoList[value].courseCode;     
//        		this.sChoiceCourseNo.outlineName=this.courseOutlineList[value].outlineName;
//        		console.log(this.sChoiceCourseNo.outlineName);
//        		console.log(this.courseOutlineList[value].outlineName);
        			}
        		}
        	}        	
        },
        /**
         * 改变教师ID下拉框
         * @Param value 选中的数据的id
         */
        changeStaffId(value){
        	if(!isEmpty(value)){
        		for(let i= 0;i<this.staffInfoList.length;i++){
        			if(this.staffInfoList[i].id === value){
        				this.choiceCourseNo.staffCode=this.staffInfoList[i].staffCode;
        			}
        		}
//        		this.choiceCourseNo.staffCode=this.staffInfoList[value].staffCode;
//        		this.choiceCourseNo.staffInfoId=this.staffInfoList[value].id;
//        		this.sChoiceCourseNo.staffName=this.staffInfoList[value].staffName;
        		
        	}
        	
        },
        
        

        /**
         * 新增课程信息
         */
        addChoiceCourseNo() {
        	if (this.checkChoiceCourseNo()) {
                return;
            }
            // 检查数据格式
//            if (checkEmpty(this.choiceCourseNo.courseNo, '选课课号不能为空') ||
//                checkLength(this.choiceCourseNo.courseNo, '200', '选课课号不能超过200个字符') ||
//                checkLength(this.choiceCourseNo.remark, '500', '备注不能超过5000个字符')) {
//                return;
//            }
            // 检查数据格式
            if (checkEmpty(this.choiceCourseNo.yearTermId, '学年不能为空') ||
            	checkEmpty(this.choiceCourseNo.courseOutlineId, '大纲名称不能为空')||
            	checkEmpty(this.choiceCourseNo.staffInfoId, '教师姓名不能为空')||
                checkLength(this.choiceCourseNo.remark, '500', '备注不能超过5000个字符')) {
                return;
            }

            // 发送请求
            let data = {
            	courseNo:'('+this.choiceCourseNo.termName+')'+'-'+this.choiceCourseNo.courseCode+'-'+this.choiceCourseNo.staffCode,
				remark: this.choiceCourseNo.remark,
                yearTermId: this.choiceCourseNo.yearTermId,
                staffInfoId: this.choiceCourseNo.staffInfoId,
                courseOutlineId: this.choiceCourseNo.courseOutlineId,
                
            };
            console.log(this.choiceCourseNo.yearTermId);
            let url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addChoiceCourseNoSuc);
            console.log('-------------------');
            console.log(this.courseNo);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        /**
         * 新增课程信息回调函数
         *
         * @param data
         *            请求返回参数
         */
        addChoiceCourseNoSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            switch (data.code) {
                case 200:
                    // 关闭模态框
                    this.addChoiceCourseNoModal = false;
                    messageSuccess( "新增选课课号信息成功");
                    // 重新查询数据
                    this.filter();
                    // 清除课程信息
                    this.clearChoiceCourseNo();
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
        cancelAddChoiceCourseNo() {
            // 关闭模态框
            this.addChoiceCourseNoModal = false;
            // 清除课程信息
            this.clearChoiceCourseNo();
        },

        /**
         * 打开编辑课程信息模态框
         *
         * @param index
         *            当前数据索引
         */
        openEditChoiceCourseNoModal(index) {
//        	debugger
            this.choiceCourseNo.id = this.nowData[index].id;
            this.choiceCourseNo.courseNo = '('+this.nowData[index].termName+')'+'-'+this.nowData[index].courseCode+'-'+this.nowData[index].staffCode;
            //this.choiceCourseNo.courseNo = this.nowData[index].courseNo;
            this.choiceCourseNo.remark = this.nowData[index].remark;
            this.choiceCourseNo.yearTermId = this.nowData[index].yearTermId;
            this.choiceCourseNo.staffInfoId = this.nowData[index].staffInfoId;
            this.choiceCourseNo.courseOutlineId = this.nowData[index].courseOutlineId;
            // 打开模态框
            this.editChoiceCourseNoModal = true;
        },
        /**
         * 修改课程信息
         */
        editChoiceCourseNo() {
            console.log(this.choiceCourseNo);
            // 判断数据是否修改

            // 检查数据格式
            if (this.checkChoiceCourseNo()) {
                return;
            }
            let data = {
                id: this.choiceCourseNo.id,
                courseNo:'('+this.choiceCourseNo.termName+')'+'-'+this.choiceCourseNo.courseCode+'-'+this.choiceCourseNo.staffCode,
                //courseNo:this.choiceCourseNo.courseNo,
                remark: this.choiceCourseNo.remark,
                yearTermId:this.choiceCourseNo.yearTermId,
                staffInfoId:this.choiceCourseNo.staffInfoId,
                courseOutlineId:this.choiceCourseNo.courseOutlineId,
            };
            let url = this.firstPath + '/update';
            callAjaxPost(url, data, this.editChoiceCourseNoSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editChoiceCourseNoSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            switch (data.code) {
                case 200:
                    // 关闭模态框
                    this.editChoiceCourseNoModal = false;
                    messageSuccess( "信息修改成功");
                    // 重新查询数据
                    this.filter();
                    // 清除课程信息
                    this.clearChoiceCourseNo();
                    break;
                case 420:
                    messageError(this, data.msg);
                    break;
                default:
                    break;
            }
        },
        /**
         * 取消修改课程信息
         */
        cancelEditChoiceCourseNo() {
            // 关闭模态框
            this.editChoiceCourseNoModal = false;
            // 清除课程信息
            this.clearChoiceCourseNo();
        },
        
        /**
         * 检查课程信息数据格式
         * @return {boolean} 若数据格式错误,返回true
         */
        checkChoiceCourseNo() {
        	 if (checkEmpty(this.choiceCourseNo.yearTermId, '请选择学年') ||
    			 checkEmpty(this.choiceCourseNo.courseOutlineId, '请选择课程大纲') ||
    			 checkEmpty(this.choiceCourseNo.staffInfoId, '请选择教师')) {
                 return true;
             }
        },
        
        /**
         * 清除课程信息
         */
        clearChoiceCourseNo() {
            this.choiceCourseNo.id = '';
            this.choiceCourseNo.courseNo = '';
            this.choiceCourseNo.yearTermId = '';
            this.choiceCourseNo.staffInfoId = '';
            this.choiceCourseNo.courseOutlineId = '';
            this.choiceCourseNo.remark = '';
            this.choiceCourseNo.createdDate = '';
            this.choiceCourseNo.stateDate = '';
            this.choiceCourseNo.state = '';
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
         * 打开删除课程信息模态框
         */
        openRemoveChoiceCourseNoSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning(this, '请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeChoiceCourseNoSelectModal = true;
        },

        /**
         * 批量删除数据
         */
        removeChoiceCourseNoSelect() {
            // 关闭模态框
            this.removeChoiceCourseNoSelectModal = false;
            let idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            let data = idList;
            let url = this.firstPath + '/deleteSelection';
            callAjaxPost(url, data, this.removeChoiceCourseNoSelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        /**
         * 批量删除数据成功回调函数
         */
        removeChoiceCourseNoSelectSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '成功删除 ' + data.obj + ' 条记录');
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },
        /**
         * 打开删除选课信息模态框
         * @param index 当前数据索引
         */
        openRemoveChoiceCourseNoModal(index) {
            this.choiceCourseNo.id = this.nowData[index].id;
            this.removeChoiceCourseNoModal = true;
        },

        /**
         * 删除课程信息
         *
         * @param index
         */
        removeChoiceCourseNo() {
        	this.removeChoiceCourseNoModal = false;
            let data = this.choiceCourseNo.id;
            let url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeChoiceCourseNoSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        removeChoiceCourseNoSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '信息删除成功');
            // 重新查询数据
            this.filter();
        },


    }
});

