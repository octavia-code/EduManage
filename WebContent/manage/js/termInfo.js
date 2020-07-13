let vTermInfo = new Vue({
    el: '#termInfo',
    data: function () {
        return {
            firstPath: '/manage/termInfo',// 请求一级路径
            nowData: [], 
            column: [],
            loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            termInfo: {
                id: '', termName: '', remark: '', state:'', stateDate:''
            },// 实体类
            sTermInfo: {
                termName: ''
            },// 搜索信息
            termInfoTemp: '',// 修改临时存放信息
            addTermInfoModal: false,// 新增学期信息模态框
            editTermInfoModal: false,// 编辑学期信息模态框
            removeTermInfoModal: false,// 删除学期信息模态框
            removeTermInfoSelectModal: false,// 批量删除学期信息模态框
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
        	 // 生成表头
            this.column = [
            	{title: '学期名称', key: 'termName'},
                {title: '备注', key: 'state'}
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
            //  检查数据格式
            if (checkLength(this.sTermInfo.termName, '10', '学期信息不能超过10个字符')) {
                return;
            }
            console.log('当前页：' + this.pageNum);
            console.log('页面大小：' + this.pageSize);
            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                termName: this.sTermInfo.termName
            };
            let url = this.firstPath + '/selectTermName';
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
        	console.log("表格数据");
        	console.log(data);
            // 取消显示加载
            this.loading = false;
            this.nowData = data.obj.list;
            this.totalNum = data.obj.total;
            // 再次设置当前页码
            this.pageNum = data.obj.pageNum;
        },

        /**
         * 清除搜索条件
         */
        clearsTermInfo() {
            this.sTermInfo.termName = '';
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
         * 检查学期信息数据格式
         * @return {boolean} 若数据格式错误,返回true
         */
        checkTermInfo() {
            if (checkEmpty(this.termInfo.termName, '学期名称不能为空') ||
                checkLength(this.termInfo.termName, '10', '学期名称不能超过10个字符') ||
                checkLength(this.termInfo.remark, '400', '备注不能超过400个字符')) {
                return true;
            }
        },
        
        /**
         * 新增学期信息
         */
        addTermInfo() {
            // 检查数据格式
            if (this.checkTermInfo()) {
                return;
            }
            // 发送请求
            let data = {
               
                termName: this.termInfo.termName,
                remark: this.termInfo.remark,
            };
            let url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addTermInfoSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        /**
         * 新增学期信息回调函数
         *
         * @param data
         *            请求返回参数
         */
        addTermInfoSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            switch (data.code) {
                case 200:
                    // 关闭模态框
                    this.addTermInfoModal = false;
                    messageSuccess( "新增学期信息成功");
                    // 重新查询数据
                    this.filter();
                    // 清除学期信息
                    this.clearTermInfo();
                    break;
                case 420:
                    messageError(this, data.msg);
                    break;
                default:
                    break;
            }
        },
        /**
         * 取消新增学期
         */
        cancelAddTermInfo() {
            // 关闭模态框
            this.addTermInfoModal = false;
            // 清除学期信息
            this.clearTermInfo();
        },

        /**
         * 打开编辑学期信息模态框
         *
         * @param index
         *            当前数据索引
         */
        openEditTermInfoModal(index) {
            this.termInfoTemp = this.nowData[index];

            this.termInfo.id = this.nowData[index].id;
            this.termInfo.termName = this.nowData[index].termName;
            this.termInfo.remark = this.nowData[index].remark;
//             打开模态框
            this.editTermInfoModal = true;
        },
        /**
         * 修改学期信息
         */
        editTermInfo() {
            console.log(this.termInfoTemp);
            // 判断数据是否修改

            // 检查数据格式
            if (this.checkTermInfo()) {
                return;
            }
            let data = {
                id: this.termInfo.id,
                termName: this.termInfo.termName,
                remark: this.termInfo.remark,
            };
            let url = this.firstPath + '/update';
            callAjaxPost(url, data, this.editTermInfoSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editTermInfoSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            switch (data.code) {
                case 200:
                    // 关闭模态框
                    this.editTermInfoModal = false;
                    messageSuccess( "学期信息修改成功");
                    // 重新查询数据
                    this.filter();
                    // 清除学期信息
                    this.clearTermInfo();
                    break;
                case 420:
                    messageError(this, data.msg);
                    break;
                default:
                    break;
            }
        },
        /**
         * 取消修改学期信息
         */
        cancelEditTermInfo() {
            // 关闭模态框
            this.editTermInfoModal = false;
            // 清除学期信息
            this.clearTermInfo();
        },
        /**
         * 清除学期信息
         */
        clearTermInfo() {
            this.termInfo.id = '';
            this.termInfo.termName = '';
            this.termInfo.remark = '';
            this.termInfo.state = '';
            this.termInfo.stateDate = '';
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
         * 打开删除学期信息模态框
         */
        openRemoveTermInfoSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning(this, '请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeTermInfoSelectModal = true;
        },

        /**
         * 批量删除数据
         */
        removeTermInfoSelect() {
            // 关闭模态框
            this.removeTermInfoSelectModal = false;
            let idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            let data = idList;
            let url = this.firstPath + '/deleteSelection';
            callAjaxPost(url, data, this.removeTermInfoSelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        /**
         * 批量删除数据成功回调函数
         */
        removeTermInfoSelectSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
           messageSuccess( '成功删除'+ data.obj +'条记录！');
           
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },


        /**
         * 打开删除学期信息模态框
         * @param index 当前数据索引
         */
        openRemoveTermInfoModal(index) {
            this.termInfo.id = this.nowData[index].id;
            this.removeTermInfoModal = true;
        },

        
        /**
         * 删除学期信息
         *
         * @param index
         */
        removeTermInfo(index) {
            this.removeTermInfoModal = false;
            let data = this.termInfo.id;
            let url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeTermInfoSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        removeTermInfoSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '学期信息删除成功！');
            
            // 重新查询数据
            this.filter();
        },


    }
});

