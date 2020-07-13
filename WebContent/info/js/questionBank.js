let vQuestionBank = new Vue({
    el: '#questionBank',
    data: function () {
        return {
            firstPath: '/info/questionBank',// 请求一级路径
            nowData: [], column: [], loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            questionBank: {
                id: '', subject: '', chapter: '', stem: '',answer:'', state: '',
            },// 实体类
            sQuestionBank: {
            	subject: '', chapter: ''
            },// 搜索信息
            addQuestionBankModal: false,// 新增信息模态框
            editQuestionBankModal: false,// 编辑信息模态框
            removeQuestionBankModal: false,// 删除信息模态框
            removeQuestionBankSelectModal: false,// 批量删除信息模态框
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
    			{title:'科目',key:'subject',width:200},
    			{title:'章节',key:'chapter',width:200},
    			{title:'题干',key:'stem',width:500}
    	];
    	// 添加自定义slot-scope
        this.column.push(headActionSlot());
        // 添加序号
        this.column.unshift(headIndex());
        // 添加多选
        this.column.unshift(headSelection());
        },


        /**
		 * 表格过滤查询
		 */
        filter() {
            // 检查数据格式
            if (checkLength(this.sQuestionBank.subject, '50', '科目名称不能超过50个字符') ) {
                return;
            }
            console.log('当前页：' + this.pageNum);
            console.log('页面大小：' + this.pageSize);
            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                subject: this.sQuestionBank.subject,
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
            this.pageNum = data.obj.pageNum === 0 ? 1 : data.obj.pageNum;
        },

        /**
		 * 清除搜索条件
		 */
        clearSQuestionBank() {
            this.sQuestionBank.subject = '';
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
		 * 检查题干信息数据格式
		 * 
		 * @return {boolean} 若数据格式错误,返回true
		 */
        checkQuestionBank() {
            if (checkEmpty(this.questionBank.subject, '科目名称不能为空') ||
                checkLength(this.questionBank.subject, '50', '科目名称不能超过50个字符')) {
                return true;
            }
        },
        
        /**
		 * 新增题干信息
		 */
        addQuestionBank() {
            // 检查数据格式
            if (this.checkQuestionBank()) {
                return;
            }
            // 发送请求
            let data = {
            		subject: this.questionBank.subject,
            		chapter: this.questionBank.chapter,
            		stem: this.questionBank.stem,
            		answer: this.questionBank.answer
            };
            let url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addQuestionBankSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        /**
		 * 新增题干信息回调函数
		 * 
		 * @param data
		 *            请求返回参数
		 */
        addQuestionBankSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            if(data.obj==='exist'){
	          	 messageWarning('当前记录的题干已存在');
	               return;
	           }
            // 关闭模态框
            this.addQuestionBankModal = false;
            messageSuccess( "新增题干信息成功");
            // 重新查询数据
            this.filter();
            // 清除题干信息
            this.clearQuestionBank();
        },
        /**
		 * 取消题干信息
		 */
        cancelAddQuestionBank() {
            // 关闭模态框
            this.addQuestionBankModal = false;
            // 清除题干信息
            this.clearQuestionBank();
        },

        /**
		 * 打开编辑题干信息模态框
		 * 
		 * @param index
		 *            当前数据索引
		 */
        openEditQuestionBankModal(index) {
            this.questionBank.id = this.nowData[index].id;
            this.questionBank.subject = this.nowData[index].subject;
            this.questionBank.chapter = this.nowData[index].chapter;
            this.questionBank.stem = this.nowData[index].stem;
            this.questionBank.answer = this.nowData[index].answer;
            // 打开模态框
            this.editQuestionBankModal = true;
        },
        /**
		 * 修改题干信息
		 */
        editQuestionBank() {
            // 判断数据是否修改

            // 检查数据格式
            if (this.checkQuestionBank()) {
                return;
            }
            let data = {
            	id: this.questionBank.id,
            	subject: this.questionBank.subject,
            	chapter: this.questionBank.chapter, 
            	stem: this.questionBank.stem,
            	answer: this.questionBank.answer
            };
            let url = this.firstPath + '/update';
            callAjaxPost(url, data, this.editQuestionBankSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editQuestionBankSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            if(data.obj==='exist'){
	          	 messageWarning('当前记录的题干信息已存在');
	               return;
	           }
            // 关闭模态框
            this.editQuestionBankModal = false;
            messageSuccess( "信息修改成功");
            // 重新查询数据
            this.filter();
            // 清除题干信息
            this.clearQuestionBank();
        },
        /**
		 * 取消修改题干信息
		 */
        cancelEditQuestionBank() {
            // 关闭模态框
            this.editQuestionBankModal = false;
            // 清除题干信息
            this.clearQuestionBank();
        },
        /**
		 * 清除题干信息
		 */
        clearQuestionBank() {
            this.questionBank.id = '';
            this.questionBank.subject = '';
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
		 * 打开删除题干信息模态框
		 */
        openRemoveQuestionBankSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning(this, '请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeQuestionBankSelectModal = true;
        },

        /**
		 * 批量删除数据
		 */
        removeQuestionBankSelect() {
            // 关闭模态框
            this.removeQuestionBankSelectModal = false;
            let idList = []; 
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            let data = idList;
            let url = this.firstPath + '/deleteSelection';
            callAjaxPost(url, data, this.removeQuestionBankSelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        /**
		 * 批量删除数据成功回调函数
		 */
        removeQuestionBankSelectSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '成功删除 ' + data.obj + ' 条记录');
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },

        /**
		 * 打开删除题干信息模态框
		 * 
		 * @param index
		 *            当前数据索引
		 */
        openRemoveQuestionBankModal(index) {
            this.questionBank.id = this.nowData[index].id;
            this.removeQuestionBankModal = true;
        },

        /**
		 * 删除题干信息
		 * 
		 * @param index
		 */
        removeQuestionBank(index) {
            this.removeQuestionBankModal = false;
            let data = this.questionBank.id;
            let url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeQuestionBankSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        removeQuestionBankSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '信息删除成功');
            // 重新查询数据
            this.filter();
        },


    }
});

