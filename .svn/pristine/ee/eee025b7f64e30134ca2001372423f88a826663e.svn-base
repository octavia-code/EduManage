var Tid;
var vTableTitle = new Vue({
    el: '#TableTitle',
    data: function () {
        return {
            firstPath: '/config/tableTitle',// 请求一级路径
            nowData: [], column: [], loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            tableTitle: {
                id: '', title: '', tableName: '', tableKey:'',  seq:'', enable:'启用'
            },// 实体类
            sTableTitle: {
            	id: '', tableName: '',
            },// 搜索信息
            addTableTitleModal: false,// 新增表格标题信息模态框
            editTableTitleModal: false,// 编辑表格标题信息模态框
            removeTableTitleModal: false,// 删除表格标题信息模态框
            removeTableTitleSelectModal:false,//批量删除表格标题信息模态框
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
    	openAddTableTitleModal()
    	{
    		this.clearTableTitle();
            // 打开模态框
            this.addTableTitleModal = true;
    	},
        /**
		 * 页面初始化加载项 表格表头
		 */
        initPage() {
            var data = {tableName: "table_title"};
            var url = this.firstPath +  '/listByTableName';
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

            // 添加自定义操作栏
            // this.column.push(headAction(false, null, true,
			// this.openEditCourseTypeModal, true, this.removeCourseType));

            // 添加多选
            this.column.unshift(headSelection());

            console.log(this.column);
        },

        /**
		 * 表格过滤查询
		 */
        filter() {
            console.log('当前页：' + this.pageNum);
            console.log('页面大小：' + this.pageSize);
            var data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                tableName: this.sTableTitle.tableName,
            };
            var url = this.firstPath + '/filter';
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
            for(var i=0;i<data.obj.list.length;i++)
            	if(data.obj.list[i].enable==1) data.obj.list[i].enable="启用";
            	else data.obj.list[i].enable="禁用";
            this.nowData = data.obj.list;
            this.totalNum = data.obj.total;
            // 再次设置当前页码
            this.pageNum = data.obj.pageNum === 0 ? 1 : data.obj.pageNum;
        },

        /**
		 * 设置或取消全选
		 * 
		 * @param status
		 *            true:全选/false:取消全选
		 */
        // handleSelectAll(status) {
        // this.$refs.selection.selectAll(status);
        // },

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
         * 检查表头标题项数据格式
         * @return {boolean} 若数据格式错误,返回true
         */
        checkTableTitle() {
            if (checkEmpty(this.tableTitle.tableName, '请输入数据库表名') ||    // 检查String是否为空
                checkLength(this.tableTitle.tableName, '40', '数据库表名不能超过50个字符') ||   // 检查String长度
                checkEmpty(this.tableTitle.tableKey, '请输入key') ||    // 检查String是否为空
                checkLength(this.tableTitle.tableKey, '30', 'key不能超过20个字符') ||   // 检查String长度
                checkEmpty(this.tableTitle.title, '请输入表头名称') ||
                checkLength(this.tableTitle.title, '50', '表头名称不能超过50个字符')) {
                return true;
            }
        },

        /**
		 * 新增表头项信息
		 */
        addTableTitle() {
        	// 检查数据格式
            if (this.checkTableTitle()) {
                return;
            }
            // 关闭模态框
            this.addTableTitleModal = false;
            if(this.tableTitle.enable=="启用")  this.tableTitle.enable=1;
            else this.tableTitle.enable=0;
            var data = {
                tableName:this.tableTitle.tableName,
                tableKey:this.tableTitle.tableKey,
                title:this.tableTitle.title,
                seq:this.tableTitle.seq,
                enable:this.tableTitle.enable,
            };
            var url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addTableTitleSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        /**
		 * 新增表格标题信息回调函数
		 * 
		 * @param data
		 *            请求返回参数
		 */
        addTableTitleSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
                messageSuccess( '新增表头项成功！');
            // 重新查询数据
            this.filter();
            // 清除数据
            this.clearTableTitle();
        },
        /**
		 * 取消新增表格标题
		 */
        cancelAddTableTitle() {
            // 关闭模态框
            this.addTableTitleModal = false;
            // 清除表格标题信息
            this.clearTableTitle();
        },

        /**
		 * 打开编辑表格标题信息模态框
		 * 
		 * @param index
		 *            当前数据索引
		 */
        openEditTableTitleModal(index) {
            console.log(this.nowData[index]);
            this.tableTitle.title = this.nowData[index].title;
            this.tableTitle.tableName = this.nowData[index].tableName;
            this.tableTitle.tableKey = this.nowData[index].tableKey;
            this.tableTitle.seq = this.nowData[index].seq;
            this.tableTitle.enable = this.nowData[index].enable;
            Tid=this.nowData[index].id;
            // 打开模态框
            this.editTableTitleModal = true;
        },

        /**
		 * 修改表头项信息
		 */
        editTableTitle() {
        	// 检查数据格式
            if (this.checkTableTitle()) {
                return;
            }
            if(this.tableTitle.enable=="启用")  this.tableTitle.enable=1;
            else this.tableTitle.enable=0;
            var data = {
            		id:Tid,
                    title:this.tableTitle.title,
                    tableName:this.tableTitle.tableName,
                    tableKey:this.tableTitle.tableKey,
                    seq:this.tableTitle.seq,
                    enable:this.tableTitle.enable,
            };
            var url = this.firstPath + '/update';
            callAjaxPost(url, data, this.editTableTitleSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editTableTitleSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            // 关闭模态框
            this.editTableTitleModal = false;
            messageSuccess( '修改表头项成功！');
            // 重新查询数据
            this.filter();
            // 清除表头项信息
            this.clearTableTitle();
        },

        /**
		 * 取消修改表头项信息
		 */
        cancelEditTableTitle() {
            // 关闭模态框
            this.editTableTitleModal = false;
            // 清除表头项信息
            this.clearTableTitle();
        },
        
        /**
		 * 清除表头项信息
		 */
        clearTableTitle() {
            this.tableTitle.title = '';
            this.tableTitle.tableName = '';
            this.tableTitle.tableKey = '';
            this.tableTitle.seq='';
            this.tableTitle.enable='启用';
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
		 * 打开删除表格标题信息模态框
		 */
        openRemoveTableTitleSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning(this, '请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeTableTitleSelectModal = true;

        },

        /**
		 * 批量删除数据
		 */
        removeTableTitleSelect() {
            // 关闭模态框
            this.removeTableTitleSelectModal = false;
            var idList = [];
            for (var i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            console.log(idList);
            var data = idList;
            var url = this.firstPath + '/deleteSelection';
            callAjaxPost(url, data, this.removeTableTitleSelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        
        /**
		 * 批量删除数据成功回调函数
		 */
        removeTableTitleSelectSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
                messageSuccess( '批量删除成功！');
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },
        
        /**
         * 打开删除数据模态框
         * @param index 当前数据索引
         */
        openRemoveTableTitleModal(index) {
            this.tableTitle.id = this.nowData[index].id;
            this.removeTableTitleModal = true;
        },

        /**
		 * 删除表头项信息
		 * 
		 * @param index
		 */
        removeTableTitle(index) {
        	this.removeTableTitleModal = false;
            let data = this.tableTitle.id;
            var url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeTableTitleSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        /**
		 * 删除表头项回调函数
		 */
        removeTableTitleSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
                messageSuccess( '表头项删除成功！');

            // 重新查询数据
            this.filter();
        },
    }
});