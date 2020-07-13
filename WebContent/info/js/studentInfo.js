let vStudentInfo = new Vue({
    el: '#studentInfo',
    data: function () {
        return {
            firstPath: '/info/studentInfo',// 请求一级路径
            nowData: [], column: [], loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            studentInfo: {
                id: '', studentNumber: '', studentName: '', remark: '',sex:''
            },// 实体类
            sStudentInfo: {
                studentNumber: '', studentName: ''
            },// 搜索信息
            studentInfoTemp: '',// 修改临时存放信息
            addStudentInfoModal: false,// 新增学生信息模态框
            editStudentInfoModal: false,// 编辑学生信息模态框
            removeStudentInfoModal: false,// 删除学生信息模态框
            removeStudentInfoSelectModal: false,// 批量删除学生信息模态框
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
        	this.column = [
        			{title:'学号',key:'studentNumber',width:200},
        			{title:'学生姓名',key:'studentName',width:200},
        			{title:'性别',key:'sex',width:200},
        			{title:'备注',key:'remark',width:300}
        	];
        	// 添加自定义slot-scope
            this.column.push(headActionSlot());
            // 添加序号
            this.column.unshift(headIndex());
            // 添加多选
            this.column.unshift(headSelection());
            
        },


        /**
         * 获取表头回调函数
         *
         * @param data
         *            请求返回参数
         */
        getTableHeadSuc(data) {
        	console.log("表头信息");
        	console.log(data);
            // 生成表头
            this.column = showCol(data.obj.key, data.obj.title);
           

        },

        /**
         * 表格过滤查询
         */
        filter() {
            // 检查数据格式
            if (checkLength(this.sStudentInfo.studentNumber, '20', '学号不能超过20个字符') ||
                checkLength(this.sStudentInfo.studentName, '50', '学生名称不能超过50个字符')) {
                return;
            }
            console.log('当前页：' + this.pageNum);
            console.log('页面大小：' + this.pageSize);

            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                studentNumber: this.sStudentInfo.studentNumber,
                studentName: this.sStudentInfo.studentName
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
        clearSStudentInfo() {
            this.sStudentInfo.studentNumber = '';
            this.sStudentInfo.studentName = '';
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
         * 新增学生信息
         */
        addStudentInfo() {
            // 检查数据格式
        	 if (this.checkStudentInfo()) {
                 return;
             }
            // 发送请求
            let data = {
            	studentNumber: this.studentInfo.studentNumber,
            	studentName: this.studentInfo.studentName,
            	sex: this.studentInfo.sex,
                remark: this.studentInfo.remark,
            };
            let url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addStudentInfoSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        /**
         * 新增学生信息回调函数
         *
         * @param data
         *            请求返回参数
         */
        addStudentInfoSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            if(data.obj==='exist'){
            	messageWarning("该记录已存在");
            	return;
            }
            this.addStudentInfoModal = false;
            messageSuccess( "新增学生信息成功");
            // 重新查询数据
            this.filter();
            // 清除学生信息
            this.clearStudentInfo();
                  
        },
        /**
         * 取消新增学生
         */
        cancelAddStudentInfo() {
            // 关闭模态框
            this.addStudentInfoModal = false;
            // 清除学生信息
            this.clearStudentInfo();
        },

        /**
         * 打开编辑学生信息模态框
         *
         * @param index
         *            当前数据索引
         */
        openEditStudentInfoModal(index) {
            this.studentInfoTemp = this.nowData[index];

            this.studentInfo.id = this.nowData[index].id;
            this.studentInfo.studentNumber = this.nowData[index].studentNumber;
            this.studentInfo.studentName = this.nowData[index].studentName;
            this.studentInfo.sex = this.nowData[index].sex;
            this.studentInfo.remark = this.nowData[index].remark;
            // 打开模态框
            this.editStudentInfoModal = true;
        },
        /**
         * 修改学生信息
         */
        editStudentInfo() {
            console.log(this.studentInfoTemp);
            // 判断数据是否修改

            // 检查数据格式
            if (this.checkStudentInfo()) {
                return;
            }
            let data = {
                id: this.studentInfo.id,
                studentNumber: this.studentInfo.studentNumber,
                sex:this.studentInfo.sex,
                remark: this.studentInfo.remark,
            };
            let url = this.firstPath + '/update';
            callAjaxPost(url, data, this.editStudentInfoSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editStudentInfoSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            switch (data.code) {
                case 200:
                    // 关闭模态框
                    this.editStudentInfoModal = false;
                    messageSuccess( "学生信息修改成功");
                    // 重新查询数据
                    this.filter();
                    // 清除学生信息
                    this.clearStudentInfo();
                    break;
                case 420:
                    messageError(this, data.msg);
                    break;
                default:
                    break;
            }
        },
        /**
         * 取消修改学生信息
         */
        cancelEditStudentInfo() {
            // 关闭模态框
            this.editStudentInfoModal = false;
            // 清除学生信息
            this.clearStudentInfo();
        },
        
        /**
         * 检查学生信息数据格式
         * @return {boolean} 若数据格式错误,返回true
         */
        checkStudentInfo() {
        	if (checkEmpty(this.studentInfo.studentNumber, '学号不能为空') ||
                    checkLength(this.studentInfo.studentNumber, '20', '学号不能超过20个字符') ||
                    checkEmpty(this.studentInfo.studentName, '学生名称不能为空') ||
                    checkLength(this.studentInfo.studentName, '20', '学生名称不能超过20个字符')||
                    checkEmpty(this.studentInfo.sex,'性别不能为空')) {
        		return true;
                }
        },

        /**
         * 清除学生信息
         */
        clearStudentInfo() {
            this.studentInfo.id = '';
            this.studentInfo.studentNumber = '';
            this.studentInfo.studentName = '';
            this.studentInfo.sex = '';
            this.studentInfo.remark = '';
            this.studentInfo.state = '';
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
         * 打开删除多选学生信息模态框
         */
        openRemoveStudentInfoSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning(this, '请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeStudentInfoSelectModal = true;
        },

        /**
         * 批量删除数据
         */
        removeStudentInfoSelect() {
            // 关闭模态框
            this.removeStudentInfoModal = false;
            let idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            let data = idList;
            let url = this.firstPath + '/deleteSelection';
            callAjaxPost(url, data, this.removeStudentInfoSelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        /**
         * 批量删除数据成功回调函数
         */
        removeStudentInfoSelectSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '成功删除 ' + data.obj + ' 条记录');
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },
        
        /**
         * 打开删除学生信息模态框
         * @param index 当前数据索引
         */
        openRemoveStudentInfoModal(index) {
            this.studentinfo.id = this.nowData[index].id;
            this.removeStudentInfoModal = true;
        },

        /**
         * 删除学生信息
         *
         * @param index
         */
        removeStudentInfo(index) {
            let data = this.nowData[index].id;
            let url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeStudentInfoSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        removeStudentInfoSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '学生信息删除成功');
            // 重新查询数据
            this.filter();
        },


    }
});

