var vIndicatorSec = new Vue({
    el: '#indicatorSec',
    data: function () {
        return {
            firstPath: '/indicator/indicatorSec',// 请求一级路径
            nowData: [], column: [], loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            indicatorSec: {
                id: '',yearPlanId:'',indicatorFirstId: '', content: '', userId: '', seq: '', state: '',
            },// 实体类
            sIndicatorSec: {
            	yearPlanId: '',
            	indicatorFirstId: '',
            },// 搜索信息
            indicatorSecTemp: '',// 修改临时存放信息
            addIndicatorSecModal: false,// 新增指标信息模态框
            editIndicatorSecModal: false,// 编辑指标信息模态框
            removeIndicatorSecSelectModal: false,// 批量删除指标信息模态框
            removeIndicatorSecModal: false,// 删除指标信息模态框
            
            yearPlanList: [],//年份集合
            indicatorFirstList: [],//一级指标集合
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
         * 页面初始化加载项
         * 表格表头
         */
        initPage() {
            let data = {tableName: "indicator_sec"};
            let url = '/config/tableTitle/listByTableName';
            callAjaxPost(url, data, this.getTableHeadSuc);
            
            this.getYearPlanList();
            this.getIndicatorFirstList();
        },
        /**
         * 根据年份获取一级指标集合
         */
        getIndicatorFirstListByYearPlanId() {
        	let data = this.sIndicatorSec.yearPlanId;
            let url = '/indicator/indicatorFirst/selectIndicatorFirstListByYearPlanId';
            callAjaxPost(url, data, this.getIndicatorFirstListSuc);
        },

        getIndicatorFirstListByYearPlanIdSuc(data) {
            this.indicatorFirstList = data.obj;
        },
        
        /**
         * 获取一级指标集合
         */
        getIndicatorFirstList() {
            let url = '/indicator/indicatorFirst/selectIndicatorFirstList';
            callAjaxGetNoParam(url, this.getIndicatorFirstListSuc);
        },

        getIndicatorFirstListSuc(data) {
            this.indicatorFirstList = data.obj;
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
         * @param data  请求返回参数
         */
        getTableHeadSuc(data) {
        	// 生成表头
            this.column = showCol(data.obj.key, data.obj.title);
            // 添加自定义slot-scope
            this.column.push(headActionSlot());
            
            // 添加自定义操作栏
            // this.column.push(headAction(false, null, true,
            // this.openEditIndicatorSecModal, true, this.removeIndicatorSec));

            // 添加多选
            this.column.unshift(headSelection());
        },
        
        /**
         * 详情内容按钮
         *
         */
        showContent(index) {
            this.indicatorSec.content = this.nowData[index].content;
            alert(this.indicatorSec.content);
        },
        
        /**
         * 表格过滤查询
         */
        filter() {
            console.log('当前页：' + this.pageNum);
            console.log('页面大小：' + this.pageSize);
            
            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                yearPlanId: this.sIndicatorSec.yearPlanId,
                indicatorFirstId: this.sIndicatorSec.indicatorFirstId,
            };
            let url = this.firstPath + '/selectPageInfo';
            callAjaxPost(url, data, this.filterSuc);
            // 显示加载
            this.loading = true;
        },
        
        
        /**
         * 表格过滤查询回调函数
         * @param data 请求返回参数
         */
        filterSuc(data) {
            //取消显示加载
        	this.loading = false;
            this.nowData = data.obj.list;
            this.totalNum = data.obj.total;
            // 再次设置当前页码
            this.pageNum = data.obj.pageNum === 0 ? 1 : data.obj.pageNum;
        },
        
        /**
         * 清除搜索条件
         */
        clearSIndicatorSec() {
        	this.sIndicatorSec.yearPlanId = '';
        	this.sIndicatorSec.indicatorFirstId = '';
        },
        
        /**
         * 改变页码
         * @param pageNum 改变后的页码
         */
        onPageChange(pageNum) {
            this.pageNum = pageNum;
            this.filter();
        },
        /**
         * 切换每页条数
         * @param pageSize 换后的每页条数
         */
        onPageSizeChange(pageSize) {
            this.pageSize = pageSize;
            this.filter();
        },
        
        /**
         * 检查指标信息数据格式
         * @return {boolean} 若数据格式错误,返回true
         */
        checkIndicatorSec() {
        	if (checkEmpty(this.indicatorSec.content, '二级指标内容不能为空') ||
                	checkLength(this.indicatorSec.content, '2000', '二级指标内容不能超过2000个字符')) {
                return true;
            }
        },

        
        /**
         * 新增指标信息
         */
        addIndicatorSec() {
        	 // 检查数据格式
        	 if (this.checkIndicatorSec()) {
        	        return;
        	    }
            // 发送请求
            let data = {
            	yearPlanId: this.indicatorSec.yearPlanId,
            	indicatorFirstId: this.indicatorSec.indicatorFirstId,
            	content: this.indicatorSec.content,
            	userId: this.indicatorSec.userId,
                seq: this.indicatorSec.seq,
            };
            let url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addIndicatorSecSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        
        
        /**
         * 新增指标信息回调函数
         * @param data 请求返回参数
         */
        addIndicatorSecSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            switch (data.code) {
            	case 200:
            		// 关闭模态框
            		this.addIndicatorSecModal = false;
            		messageSuccess( "新增二级指标信息成功");
            		// 重新查询数据
            		this.filter();
            		// 清除二级指标信息
            		this.clearIndicatorSec();
            		break;
            	case 420:
            		messageError(this, data.msg);
            		break;
            	default:
            		break;
            }
        },
        /**
         * 取消新增二级指标
         */
        cancelAddIndicatorSec() {
            // 关闭模态框
            this.addIndicatorSecModal = false;
            // 清除二级指标信息
            this.clearIndicatorSec();
        },
        
        /**
         * 打开编辑二级指标模态框
         * @param index 当前数据索引
         */
        openEditIndicatorSecModal(index) {
        	console.log(this.nowData[index]);
        	this.classInfoTemp = this.nowData[index];
        	
            this.indicatorSec.id = this.nowData[index].id;
            this.indicatorSec.yearPlanId = this.nowData[index].yearPlanId;
            this.indicatorSec.indicatorFirstId = this.nowData[index].indicatorFirstId;  
            this.indicatorSec.content = this.nowData[index].content;
            this.indicatorSec.userId = this.nowData[index].userId;
            this.indicatorSec.seq = this.nowData[index].seq;
            console.log(this.indicatorSec);
            // 打开模态框
            this.editIndicatorSecModal = true;
        },
           
        /**
         * 修改二级指标信息
         */
        editIndicatorSec() {
        	console.log(this.indicatorSec);
            // 判断数据是否修改
        	
            // 检查数据格式
            if (checkEmpty(this.indicatorSec.content, '二级指标内容不能为空') ||
                checkLength(this.indicatorSec.content, '2000', '二级指标内容不能超过2000个字符')){
                return;
            }
            let data = {
                id: this.indicatorSec.id,
                yearPlanId: this.indicatorSec.yearPlanId,
                indicatorFirstId: this.indicatorSec.indicatorFirstId,
                content: this.indicatorSec.content,
                userId: this.indicatorSec.userId,
                seq: this.indicatorSec.seq,
            };
            
            console.log("将要修改的数据为：",data)
            let url = this.firstPath + '/update';
            callAjaxPost(url, data, this.editIndicatorSecSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editIndicatorSecSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            switch (data.code) {
                case 200:
                    // 关闭模态框
                    this.editIndicatorSecModal = false;
                    messageSuccess( "二级指标信息修改成功");
                    // 重新查询数据
                    this.filter();
                    // 清除二级指标
                    this.clearIndicatorSec();
                    break;
                case 420:
                    messageError(this, data.msg);
                    break;
                default:
                    break;
            }
        },
        /**
         * 取消修改指标信息
         */
        cancelEditIndicatorSec() {
            // 关闭模态框
            this.editIndicatorSecModal = false;
            // 清除二级指标信息
            this.clearIndicatorSec();
        },
        /**
         * 清除指标信息
         */
        clearIndicatorSec() {
        	this.indicatorSec.id = '';
        	this.indicatorSec.yearPlanId = '';
        	this.indicatorSec.indicatorFirstId = '';
            this.indicatorSec.content = '';
            this.indicatorSec.userId = '';
            this.indicatorSec.seq = '';
            this.indicatorSec.createdDate = '';
            this.indicatorSec.stateDate = '';
            this.indicatorSec.state = '';
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
         * 打开删除多选二级指标模态框
         */
        openRemoveIndicatorSecSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning(this, '请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeIndicatorSecSelectModal = true;
        },


        
        /**
         * 批量删除数据
         */
        removeIndicatorSecSelect() {
            // 关闭模态框
            this.removeIndicatorSecModal = false;
            let idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            let data = idList;
            let url = this.firstPath + '/deleteSelection';
            callAjaxPost(url, data, this.removeIndicatorSecSelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        /**
         * 批量删除数据成功回调函数
         */
        removeIndicatorSecSelectSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '成功删除 ' + data.obj + ' 条记录');
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },
        
        /**
         * 打开删除二级指标模态框
         * @param index 当前数据索引
         */
        openRemoveIndicatorSecModal(index) {
            this.indicatorsec.id = this.nowData[index].id;
            this.removeIndicatorSecModal = true;
        },

        /**
         * 删除二级指标指标信息
         * @param index
         */
        removeIndicatorSec(index) {
            let data = this.nowData[index].id;
            let url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeIndicatorSecSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        
        removeIndicatorSecSuc(data) {
        	// 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( ' 二级指标删除成功');
            // 重新查询数据
            this.filter();
        },

    }
});
