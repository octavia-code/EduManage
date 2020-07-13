var vCourseProp = new Vue({
    el: '#courseProp',
    data: function () {
        return {
            firstPath: '/edu/courseProp',// 请求一级路径
            nowData: [], column: [], loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            courseProp: {
                id: '', propName: '', createdDate: '', state:'', stateDate:''
            },// 实体类
            sCourseProp: {
                propName: ''
            },// 搜索信息
            addCoursePropModal: false,// 新增课程信息模态框
            editCoursePropModal: false,// 编辑课程信息模态框
            removeCoursePropModal: false,// 删除课程信息模态框
            removeCoursePropSelectModal: false,// 批量删除课程信息模态框
            counter: -1,
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
        	// 生成表头
            this.column = [
            	{title: '名称', key: 'propName'}
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
            console.log('当前页：' + this.pageNum);
            console.log('页面大小：' + this.pageSize);
            var data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                propName: this.sCourseProp.propName,
            };

            var url = this.firstPath + '/selectByCourseProp';
            callAjaxPost(url, data, this.filterSuc);
            // 显示加载
            this.loading = true;

        },
        
       
        /**
         * 表格过滤查询回调函数
         * @param data 请求返回参数
         */
        filterSuc(data) {
            // 取消显示加载
            this.loading = false;
            this.nowData = data.obj.list;
            this.totalNum = data.obj.total;
            // 再次设置当前页码
            this.pageNum = data.obj.pageNum;
        },
        

        /**
         * 设置或取消全选
         * @param status true:全选/false:取消全选
         */
        // handleSelectAll(status) {
        //     this.$refs.selection.selectAll(status);
        // },


        /**
         * 清除搜索条件
         */
        clearSCourseProp() {
            this.sCourseProp.propName = '';
            this.filter();
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
         * 检查教学计划数据格式
         * @return {boolean} 若数据格式错误,返回true
         */
        checkCourseProp() {
            if (checkEmpty(this.courseProp.propName, '名称不能为空') ||
               checkLength(this.courseProp.propName, '200', '名称不能超过200个字符')) {
                return true;
            }
        },
        
        /**
         * 新增课程信息
         */
        addCourseProp() {
        	// 检查数据格式
            if (this.checkCourseProp()) {
                return;
            }
            // 关闭模态框
            this.addCoursePropModal = false;
            var data = {
                id:this.courseProp.id,
                propName: this.courseProp.propName,
            };
            var url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addCoursePropSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        /**
         * 新增课程信息回调函数
         * @param data 请求返回参数
         */
        addCoursePropSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '新增课程性质成功！');
            // 重新查询数据
            this.filter();
            // 清除数据
            this.clearCourseProp();
        },
        /**
         * 取消新增课程
         */
        cancelAddCourseProp() {
            // 关闭模态框
            this.addCoursePropModal = false;
            // 清除课程信息
            this.clearCourseProp();
        },

        /**
         * 打开编辑课程信息模态框
         * @param index 当前数据索引
         */
        openEditCoursePropModal(index) {
            console.log(this.nowData[index]);
            this.courseProp.id = this.nowData[index].id;
            this.courseProp.propName = this.nowData[index].propName;
            // 打开模态框
            this.editCoursePropModal = true;
        },
        
        
        /**
         * 修改课程信息
         */
        editCourseProp() {
        	// 检查数据格式
            if (this.checkCourseProp()) {
                return;
            }
            // 关闭模态框
            this.editCoursePropModal = false;
            var data = {
                id: this.courseProp.id,
                propName: this.courseProp.propName,
            };
            var url = this.firstPath + '/update';
            callAjaxPost(url, data, this.editCoursePropSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editCoursePropSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '修改课程性质成功！');
            
            // 重新查询数据
            this.filter();
            // 清除课程信息
            this.clearCourseProp();
        },
        
       
       
        /**
         * 取消修改课程信息
         */
        cancelEditCourseProp() {
            // 关闭模态框
            this.editCoursePropModal = false;
            // 清除课程信息
            this.clearCourseProp();
        },
        /**
         * 清除课程信息
         */
        clearCourseProp() {
            this.courseProp.id = '';
            this.courseProp.propName = '';
            this.courseProp.state = '';
            this.courseProp.createdDate = '';
            this.courseProp.stateDate = '';
        },

        /**
         * 在多选模式下有效，只要选中项发生变化时就会触发
         * @param selection 已选项数据
         */
        onSelectionChange(selection) {
            this.selection = selection;
            console.log(this.selection);
        },

        /**
         * 打开删除课程信息模态框
         */
        openRemoveCoursePropSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning(this, '请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeCoursePropSelectModal = true;

        },

        /**
         * 批量删除数据
         */
        removeCoursePropSelect() {
            // 关闭模态框
            this.removeCoursePropSelectModal = false;
            var idList = [];
            for (var i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            console.log(idList);
            var data = idList;
            var url = this.firstPath + '/deleteSelection';
            callAjaxPost(url, data, this.removeCoursePropSelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        /**
         * 批量删除数据成功回调函数
         */
        removeCoursePropSelectSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '成功删除'+ data.obj +'条记录！');
           
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },

        /**
         * 打开删除课程信息模态框
         * @param index 当前数据索引
         */
        openRemoveCoursePropModal(index) {
            this.courseProp.id = this.nowData[index].id;
            this.removeCoursePropModal = true;
        },
        /**
         * 删除课程信息
         * @param index
         */
        removeCourseProp(index) {
            this.removeCoursePropModal = false;
            var data = this.courseProp.id;
            var url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeCoursePropSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        removeCoursePropSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '课程信息删除成功！');
            
            // 重新查询数据
            this.filter();
        },
        

    }
});