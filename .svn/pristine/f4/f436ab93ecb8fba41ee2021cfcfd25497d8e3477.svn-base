let vIndicatorFirst = new Vue({
    el: '#indicatorFirst',
    data: function () {
        return {
            firstPath: '/indicator/indicatorFirst',// 请求一级路径
            nowData: [], column: [], loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            indicatorFirst: {
                id: '', title: '', content: '', seq: '', userId: '', state: '', yearPlanId: ''
            },// 实体类
            sIndicatorFirst: {
                title: '', content: '',yearPlanId: ''
            },// 搜索信息
            addIndicatorFirstModal: false,// 新增一级指标模态框
            editIndicatorFirstModal: false,// 编辑一级指标模态框
            removeIndicatorFirstSelectModal: false,// 批量删除一级指标模态框
            removeIndicatorFirstModal: false,// 删除一级指标模态框
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
            let data = {tableName: "indicator_first"};
            let url = '/config/tableTitle/listByTableName';
            callAjaxPost(url, data, this.getTableHeadSuc);
            this.getYearPlanList();
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
            if (checkLength(this.sIndicatorFirst.title, '20', '一级指标主题不能超过20个字符') ||
                checkLength(this.sIndicatorFirst.content, '200', '一级指标内容不能超过200个字符')) {
                return;
            }
            console.log('当前页：' + this.pageNum);
            console.log('页面大小：' + this.pageSize);
            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                title: this.sIndicatorFirst.title,
                content: this.sIndicatorFirst.content,
                yearPlanId: this.sIndicatorFirst.yearPlanId
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
        	console.log(data);
            // 取消显示加载
            this.loading = false;
            this.nowData = data.obj.list;
            this.totalNum = data.obj.total;
            // 再次设置当前页码
            this.pageNum = data.obj.pageNum;
            console.log("第107行");
            console.log(this.nowData);
        },

        /**
		 * 清除搜索条件
		 */
        clearSIndicatorFirst() {
        	this.sIndicatorFirst.title = '';
            this.sIndicatorFirst.content = '';
            this.sIndicatorFirst.yearPlanId = '';
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
         * 检查一级指标数据格式
         * @return {boolean} 若数据格式错误,返回true
         */
        checkIndicatorFirst() {
        	if (checkEmpty(this.indicatorFirst.title, '一级指标主题不能为空') ||
            		checkLength(this.indicatorFirst.title, '20', '一级指标主题不能超过20个字符') ||
            		checkEmpty(this.indicatorFirst.content, '一级指标内容不能为空') ||
            		checkLength(this.indicatorFirst.content, '100', '一级指标内容不能超过100个字符')||
            		checkEmpty(this.indicatorFirst.yearPlanId, '一级指标年份不能为空')
            		) {
                return true;
            }
        },


        /**
		 * 新增一级指标
		 */
        addIndicatorFirst() {
        	  // 检查数据格式
        	 if (this.checkIndicatorFirst()) {
        	        return;
        	    }
            let data = {
                title: this.indicatorFirst.title,
                content: this.indicatorFirst.content,
                seq:this.indicatorFirst.seq,
                yearPlanId:this.indicatorFirst.yearPlanId
            };
            let url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addIndicatorFirstSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        /**
		 * 新增一级指标回调函数
		 * 
		 * @param data
		 *            请求返回参数
		 */
        addIndicatorFirstSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            switch (data.code) {
            case 200:
                // 关闭模态框
                this.addIndicatorFirstModal = false;
                messageSuccess( "新增一级指标成功");
                // 重新查询数据
                this.filter();
                // 清除一级指标
                this.clearIndicatorFirst();
                break;
            case 420:
                messageError(this, data.msg);
                break;
            default:
                break;
        }
        },
        /**
		 * 取消新增一级指标
		 */
        cancelAddIndicatorFirst() {
            // 关闭模态框
            this.addIndicatorFirstModal = false;
            // 清除一级指标
            this.clearIndicatorFirst();
        },

        /**
		 * 打开编辑一级指标模态框
		 * 
		 * @param index
		 *            当前数据索引
		 */
        openEditIndicatorFirstModal(index) {
        	console.log(this.nowData);
            this.indicatorFirst.id = this.nowData[index].id;
            this.indicatorFirst.title = this.nowData[index].title;
            this.indicatorFirst.content = this.nowData[index].content;
            this.indicatorFirst.seq=this.nowData[index].seq;
            this.indicatorFirst.yearPlanId=this.nowData[index].yearPlanId;
            // 打开模态框
            this.editIndicatorFirstModal = true;
        },
        /**
		 * 修改一级指标
		 */
        editIndicatorFirst() {
       	  // 检查数据格式
            if (checkEmpty(this.indicatorFirst.title, '一级指标主题不能为空') ||
            		checkLength(this.indicatorFirst.title, '20', '一级指标主题不能超过20个字符') ||
            		checkEmpty(this.indicatorFirst.content, '一级指标内容不能为空') ||
            		checkLength(this.indicatorFirst.content, '100', '一级指标内容不能超过100个字符')||
            		checkEmpty(this.indicatorFirst.yearPlanId, '一级指标年份不能为空')
            ) {
                return;
            }
            let data = {
            		id:this.indicatorFirst.id,
            		title: this.indicatorFirst.title,
                    content: this.indicatorFirst.content,
                    seq: this.indicatorFirst.seq,
                    yearPlanId:this.indicatorFirst.yearPlanId
            };
            let url= this.firstPath + '/update';
            callAjaxPost(url, data, this.editIndicatorFirstSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editIndicatorFirstSuc(data) {
        	// 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            switch (data.code) {
                case 200:
                    // 关闭模态框
                    this.editIndicatorFirstModal = false;
                    messageSuccess( "一级指标修改成功");
                    // 重新查询数据
                    this.filter();
                    // 清除一级指标
                    this.clearIndicatorFirst();
                    break;
                case 420:
                    messageError(this, data.msg);
                    break;
                default:
                    break;
            }
        },
        /**
		 * 取消修改一级指标
		 */
        cancelEditIndicatorFirst() {
            // 关闭模态框
            this.editIndicatorFirstModal = false;
            // 清除一级指标
            this.clearIndicatorFirst();
        },
        /**
		 * 清除一级指标
		 */
        clearIndicatorFirst() {
        	this.indicatorFirst.title=''
            this.indicatorFirst.content='',
            this.indicatorFirst.seq='',
            this.indicatorFirst.yearPlanId=''
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
         * 打开删除多选一级指标模态框
         */
        openRemoveIndicatorFirstSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning(this, '请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeIndicatorFirstSelectModal = true;
        },

        /**
		 * 批量删除数据
		 */
        removeIndicatorFirstSelect() {
            // 关闭模态框
            this.removeIndicatorFirstModal = false;
            let idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            console.log(idList);
            let data = idList;
            let url = this.firstPath + '/deleteSelection';
            callAjaxPost(url, data, this.removeIndicatorFirstSelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        /**
		 * 批量删除数据成功回调函数
		 */
        removeIndicatorFirstSelectSuc(data) {
        	  // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '成功删除 ' + data.obj + ' 条记录');
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },
        
        /**
         * 打开删除一级指标模态框
         * @param index 当前数据索引
         */
        openRemoveIndicatorFirstModal(index) {
            this.indicatorfirst.id = this.nowData[index].id;
            this.removeIndicatorFirstModal = true;
        },

        
        /**
		 * 删除一级指标
		 * 
		 * @param index
		 */
        removeIndicatorFirst(index) {
            let data = this.nowData[index].id;
            let url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeIndicatorFirstSuc);

            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        removeIndicatorFirstSuc(data) {
        	// 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '一级指标删除成功');
            // 重新查询数据
            this.filter();
        },
        
    }
});
