let vSchoolInfo = new Vue({
    el: '#schoolInfo',
    data: function () {
        return {
            firstPath: '/info/schoolInfo',// 请求一级路径
            nowData: [], column: [], loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            schoolInfo: {
                id: '', schoolName: '', remark: '', stateDate: '', state: '',
            },// 实体类
            sSchoolInfo: {
            	schoolName: '', remark: ''
            },// 搜索信息
            addSchoolInfoModal: false,// 新增学院信息模态框
            editSchoolInfoModal: false,// 编辑学院信息模态框
            removeSchoolInfoModal: false,// 删除学院信息模态框
            removeSchoolInfoSelectModal: false,// 批量删除学院信息模态框
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
            let data = {tableName: "school_info"};
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
            // 检查数据格式
            if (checkLength(this.sSchoolInfo.schoolName, '50', '学院名称不能超过50个字符') ) {
                return;
            }
            console.log('当前页：' + this.pageNum);
            console.log('页面大小：' + this.pageSize);
            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                schoolName: this.sSchoolInfo.schoolName,
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
        clearSSchoolInfo() {
            this.sSchoolInfo.schoolName = '';
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
		 * 检查学院信息数据格式
		 * 
		 * @return {boolean} 若数据格式错误,返回true
		 */
        checkSchoolInfo() {
            if (checkEmpty(this.schoolInfo.schoolName, '学院名称不能为空') ||
                checkLength(this.schoolInfo.schoolName, '50', '学院名称不能超过50个字符')) {
                return true;
            }
        },
        
        /**
		 * 新增学院信息
		 */
        addSchoolInfo() {
            // 检查数据格式
            if (this.checkSchoolInfo()) {
                return;
            }
            // 发送请求
            let data = {
            		schoolName: this.schoolInfo.schoolName,
            		remark: this.schoolInfo.remark,      
            };
            let url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addSchoolInfoSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        /**
		 * 新增学院信息回调函数
		 * 
		 * @param data
		 *            请求返回参数
		 */
        addSchoolInfoSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            if(data.obj==='exist'){
	          	 messageWarning('当前记录的学院名称已存在');
	               return;
	           }
            // 关闭模态框
            this.addSchoolInfoModal = false;
            messageSuccess( "新增学院信息成功");
            // 重新查询数据
            this.filter();
            // 清除学院信息
            this.clearSchoolInfo();
        },
        /**
		 * 取消学院信息
		 */
        cancelAddSchoolInfo() {
            // 关闭模态框
            this.addSchoolInfoModal = false;
            // 清除学院信息
            this.clearSchoolInfo();
        },

        /**
		 * 打开编辑学院信息模态框
		 * 
		 * @param index
		 *            当前数据索引
		 */
        openEditSchoolInfoModal(index) {
            this.schoolInfo.id = this.nowData[index].id;
            this.schoolInfo.schoolName = this.nowData[index].schoolName;
            this.schoolInfo.remark = this.nowData[index].remark;
            // 打开模态框
            this.editSchoolInfoModal = true;
        },
        /**
		 * 修改学院信息
		 */
        editSchoolInfo() {
            // 判断数据是否修改

            // 检查数据格式
            if (this.checkSchoolInfo()) {
                return;
            }
            let data = {
            	id: this.schoolInfo.id,
            	schoolName: this.schoolInfo.schoolName,
            	remark: this.schoolInfo.remark,  
            };
            let url = this.firstPath + '/update';
            callAjaxPost(url, data, this.editSchoolInfoSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editSchoolInfoSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            if(data.obj==='exist'){
	          	 messageWarning('当前记录的学院名称已存在');
	               return;
	           }
            // 关闭模态框
            this.editSchoolInfoModal = false;
            messageSuccess( "学院信息修改成功");
            // 重新查询数据
            this.filter();
            // 清除学院信息
            this.clearSchoolInfo();
        },
        /**
		 * 取消修改学院信息
		 */
        cancelEditSchoolInfo() {
            // 关闭模态框
            this.editSchoolInfoModal = false;
            // 清除学院信息
            this.clearSchoolInfo();
        },
        /**
		 * 清除学院信息
		 */
        clearSchoolInfo() {
            this.schoolInfo.id = '';
            this.schoolInfo.schoolName = '';
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
		 * 打开删除学院信息模态框
		 */
        openRemoveSchoolInfoSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning(this, '请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeSchoolInfoSelectModal = true;
        },

        /**
		 * 批量删除数据
		 */
        removeSchoolInfoSelect() {
            // 关闭模态框
            this.removeSchoolInfoSelectModal = false;
            let idList = []; 
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            let data = idList;
            let url = this.firstPath + '/deleteSelection';
            callAjaxPost(url, data, this.removeSchoolInfoSelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        /**
		 * 批量删除数据成功回调函数
		 */
        removeSchoolInfoSelectSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '成功删除 ' + data.obj + ' 条记录');
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },

        /**
		 * 打开删除学院信息模态框
		 * 
		 * @param index
		 *            当前数据索引
		 */
        openRemoveSchoolInfoModal(index) {
            this.schoolInfo.id = this.nowData[index].id;
            this.removeSchoolInfoModal = true;
        },

        /**
		 * 删除学院信息
		 * 
		 * @param index
		 */
        removeSchoolInfo(index) {
            this.removeSchoolInfoModal = false;
            let data = this.schoolInfo.id;
            let url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeSchoolInfoSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        removeSchoolInfoSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '学院信息删除成功');
            // 重新查询数据
            this.filter();
        },


    }
});

