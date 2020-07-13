let vIndicatorThird = new Vue({
    el: '#indicatorThird',
    data: function () {
        return {
            firstPath: '/indicator/indicatorThird',// 请求一级路径
            nowData: [], column: [], loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            indicatorThird: {
                id: '', content: '', seq: '', userId: '', createDate: '',
                stateDate: '', state: '',
            },// 实体类
            sIndicatorThird: {
            	content: '', seq: ''
            },// 搜索信息
            addIndicatorThirdModal: false,// 新增毕业要求三级指标模态框
            editIndicatorThirdModal: false,// 编辑毕业要求三级指标模态框
            removeIndicatorThirdSelectModal: false,// 批量删除三级指标模态框
            removeIndicatorThirdModal: false,// 删除毕业要求三级指标模态框
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
            let data = {tableName: "indicator_third"};
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
            if (checkLength(this.sIndicatorThird.content, '2000', '内容不能超过2000个字符')) {
                return;
            }
            console.log('当前页：' + this.pageNum);
            console.log('页面大小：' + this.pageSize);
            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                content: this.sIndicatorThird.content,
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
        clearSIndicatorThird() {
            this.sIndicatorThird.content = '';
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
         * 检查毕业要求三级指标数据格式
         * @return {boolean} 若数据格式错误,返回true
         */
        checkIndicatorThird() {
        	if (checkEmpty(this.indicatorThird.content, '内容不能为空') ||
                    checkLength(this.indicatorThird.content, '2000', '内容不能超过2000个字符') 
                   ) {
                return true;
            }
        },


        /**
		 * 新增毕业要求三级指标
		 */
        addIndicatorThird() {
            // 检查数据格式
        	 if (this.checkIndicatorThird()) {
        	        return;
        	    }
            // 发送请求
            let data = {
            		content: this.indicatorThird.content,
            		seq: this.indicatorThird.seq,
            		userId: this.indicatorThird.userId,
               
            };
            let url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addIndicatorThirdSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        /**
		 * 新增毕业要求三级指标回调函数
		 * 
		 * @param data
		 *            请求返回参数
		 */
        addIndicatorThirdSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            if(data.obj==='exist'){
              	 messageWarning('当前记录的序号已存在');
                   return;
               }
            // 关闭模态框
            this.addIndicatorThirdModal = false;
            messageSuccess( "新增毕业要求三级指标成功");
            // 重新查询数据
            this.filter();
            // 清除三级指标
            this.clearIndicatorThird();
        },
        /**
		 * 取消毕业要求三级指标
		 */
        cancelAddIndicatorThird() {
            // 关闭模态框
            this.addIndicatorThirdModal = false;
            // 清除三级指标
            this.clearIndicatorThird();
        },

        /**
		 * 打开编辑毕业要求三级指标模态框
		 * 
		 * @param index
		 *            当前数据索引
		 */
        openEditIndicatorThirdModal(index) {
            this.indicatorThird.id = this.nowData[index].id;
            this.indicatorThird.content = this.nowData[index].content;
            this.indicatorThird.seq = this.nowData[index].seq;
            // 打开模态框
            this.editIndicatorThirdModal = true;
        },
        /**
		 * 修改毕业要求三级指标
		 */
        editIndicatorThird() {

            // 检查数据格式
            if (checkEmpty(this.indicatorThird.content, '内容不能为空') ||
                checkLength(this.indicatorThird.content, '2000', '内容不能超过2000个字符')
                ) {
                return;
            }
            let data = {
                id: this.indicatorThird.id,
                content: this.indicatorThird.content,
                seq: this.indicatorThird.seq,
            };
            let url = this.firstPath + '/update';
            callAjaxPost(url, data, this.editIndicatorThirdSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editIndicatorThirdSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            if(data.obj==='exist'){
             	 messageWarning('当前记录的序号已存在');
                  return;
              }
            // 关闭模态框
            this.editIndicatorThirdModal = false;
            messageSuccess( "毕业要求三级指标修改成功");
            // 重新查询数据
            this.filter();
            // 清除毕业要求三级指标
            this.clearIndicatorThird();
        },
        /**
		 * 取消修改毕业要求三级指标
		 */
        cancelEditIndicatorThird() {
            // 关闭模态框
            this.editIndicatorThirdModal = false;
            // 清除毕业要求三级指标
            this.clearIndicatorThird();
        },
        /**
		 * 清除毕业要求三级指标
		 */
        clearIndicatorThird() {
            this.indicatorThird.id = '';
            this.indicatorThird.content = '';
            this.indicatorThird.seq = '';
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
         * 打开删除多选三级指标模态框
         */
        openRemoveIndicatorThirdSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning(this, '请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeIndicatorThirdSelectModal = true;
        },


        /**
		 * 批量删除数据
		 */
        removeIndicatorThirdSelect() {
            // 关闭模态框
            this.removeIndicatorThirdModal = false;
            let idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            let data = idList;
            let url = this.firstPath + '/deleteSelection';
            callAjaxPost(url, data, this.removeIndicatorThirdSelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        /**
		 * 批量删除数据成功回调函数
		 */
        removeIndicatorThirdSelectSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '成功删除 ' + data.obj + ' 条记录');
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },
        
        /**
         * 打开删除三级指标模态框
         * @param index 当前数据索引
         */
        openRemoveIndicatorThirdModal(index) {
            this.indicatorThird.id = this.nowData[index].id;
            this.removeIndicatorThirdModal = true;
        },

        /**
		 * 删除毕业要求三级指标
		 * 
		 * @param index
		 */
        removeIndicatorThird(index) {
            let data = this.indicatorThird.id;
            let url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeIndicatorThirdSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        removeIndicatorThirdSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '毕业要求三级指标删除成功');
            // 重新查询数据
            this.filter();
        },


    }
});

