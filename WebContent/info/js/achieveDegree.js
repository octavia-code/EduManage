let vAchieveDegree = new Vue({
    el: '#achieveDegree',
    data: function () {
        return {
            firstPath: '/info/achieveDegree',// 请求一级路径
            nowData: [], column: [], loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            achieveDegree: {
                id: '', userCode: '', linedown: '', lineup: ''
            },// 实体类
            sAchieveDegree: {
            	userCode: ''
            },// 搜索信息
            addAchieveDegreeModal: false,// 新增信息模态框
            editAchieveDegreeModal: false,// 编辑信息模态框
            removeAchieveDegreeModal: false,// 删除信息模态框
            degreeModal:false,
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
    			{title:'学生学号',key:'userCode',width:200},
    			{title:'考试成绩',key:'linedown',width:200},
    			{title:'线上成绩',key:'lineup',width:500}
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
            if (checkLength(this.sAchieveDegree.userCode, '50', '学生学号不能超过50个字符') ) {
                return;
            }
            console.log('当前页：' + this.pageNum);
            console.log('页面大小：' + this.pageSize);
            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                userCode: this.sAchieveDegree.userCode,
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
        clearSAchieveDegree() {
            this.sAchieveDegree.userCode = '';
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
        checkAchieveDegree() {
            if (checkEmpty(this.achieveDegree.userCode, '学生学号不能为空') ||
                checkLength(this.achieveDegree.userCode, '50', '学号不能超过50个字符')) {
                return true;
            }
        },
        
        /**
		 * 新增题干信息
		 */
        addAchieveDegree() {
            // 检查数据格式
            if (this.checkAchieveDegree()) {
                return;
            }
            // 发送请求
            let data = {
            		userCode: this.achieveDegree.userCode,
            		linedown: this.achieveDegree.linedown,
            		lineup: this.achieveDegree.lineup
            };
            let url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addAchieveDegreeSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        /**
		 * 新增题干信息回调函数
		 * 
		 * @param data
		 *            请求返回参数
		 */
        addAchieveDegreeSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            // 关闭模态框
            this.addAchieveDegreeModal = false;
            messageSuccess( "新增信息成功");
            // 重新查询数据
            this.filter();
            // 清除题干信息
            this.clearAchieveDegrees();
        },
        /**
		 * 取消题干信息
		 */
        cancelAddAchieveDegree() {
            // 关闭模态框
            this.addAchieveDegreeModal = false;
            // 清除题干信息
            this.clearAchieveDegree();
        },

        /**
		 * 打开编辑题干信息模态框
		 * 
		 * @param index
		 *            当前数据索引
		 */
        openEditAchieveDegreeModal(index) {
            this.achieveDegree.id = this.nowData[index].id;
            this.achieveDegree.userCode = this.nowData[index].userCode;
            this.achieveDegree.linedown = this.nowData[index].linedown;
            this.achieveDegree.lineup = this.nowData[index].lineup;
            // 打开模态框
            this.editAchieveDegreeModal = true;
        },
        /**
		 * 修改题干信息
		 */
        editAchieveDegree() {
            // 判断数据是否修改

            // 检查数据格式
            if (this.checkAchieveDegree()) {
                return;
            }
            let data = {
            	id: this.achieveDegree.id,
            	userCode: this.achieveDegree.userCode,
            	linedown: this.achieveDegree.linedown, 
            	lineup: this.achieveDegree.lineup
            };
            let url = this.firstPath + '/update';
            callAjaxPost(url, data, this.editAchieveDegreeSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editAchieveDegreeSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            // 关闭模态框
            this.editAchieveDegreeModal = false;
            messageSuccess( "信息修改成功");
            // 重新查询数据
            this.filter();
            // 清除题干信息
            this.clearAchieveDegree();
        },
        /**
		 * 取消修改题干信息
		 */
        cancelEditAchieveDegree() {
            // 关闭模态框
            this.editAchieveDegreeModal = false;
            // 清除题干信息
            this.clearAchieveDegree();
        },
        /**
		 * 清除题干信息
		 */
        clearAchieveDegree() {
            this.achieveDegree.id = '';
            this.achieveDegree.userCode = '';
        },


        /**
		 * 打开删除题干信息模态框
		 */
        openRemoveAchieveDegreeSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning(this, '请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeAchieveDegreeSelectModal = true;
        },


        /**
		 * 打开删除题干信息模态框
		 * 
		 * @param index
		 *            当前数据索引
		 */
        openRemoveAchieveDegreeModal(index) {
            this.achieveDegree.id = this.nowData[index].id;
            this.removeAchieveDegreeModal = true;
        },

        /**
		 * 删除题干信息
		 * 
		 * @param index
		 */
        removeAchieveDegree(index) {
            this.removeAchieveDegreeModal = false;
            let data = this.achieveDegree.id;
            let url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeAchieveDegreeSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        removeAchieveDegreeSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '信息删除成功');
            // 重新查询数据
            this.filter();
        },

        openDegreeModal(index){
        	this.achieveDegree.linedown = this.nowData[index].linedown;
        	this.achieveDegree.lineup = this.nowData[index].lineup;
        	this.degreeModal = true;
        }

    }
});

