let vTblHeadList = new Vue({
    el: '#tblHeadList',
    data: function () {
        return {
            firstPath: '/tblHeadList',// 请求一级路径
            nowData: [], column: [], loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            tblHeadList: {
                id: '', planId: '', colName: '', colNbr: '', rowNbr: '', isEmpty: 'true',
                userId: '', createdDate: '', state: '', stateDate: '', colStart: '',
                colEnd: '', rowStart: '', rowEnd: '',courseName: '',
            },// 实体类
            sTblHeadList: {
                colName: '',
                courseName: ''
            },// 搜索信息
            tblHeadListTemp: '',// 修改临时存放信息
            addTblHeadListModal: false,// 新增表头信息模态框
            editTblHeadListModal: false,// 编辑表头信息模态框
            removeTblHeadListModal: false,// 删除表头信息模态框
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
            let data = {tableName: "tbl_Head_List"};
            let url = '/tableTitle/listByTableName';
            callAjaxPost(url, data, this.getTableHeadSuc);
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
            // this.column.push(headAction(false, null, true, this.openEdittblHeadListModal, true, this.removetblHeadList));

            // 添加多选
            this.column.unshift(headSelection());

            console.log(this.column);
        },

        /**
         * 表格过滤查询
         */
        filter() {
        //  检查数据格式
            if (checkLength(this.sTblHeadList.colName, '20', '列展示名不能超过20个字符') ||
                checkLength(this.sTblHeadList.courseName, '200', '课程名称不能超过20个字符')) {
                return;
            }
            console.log('当前页：' + this.pageNum);
            console.log('页面大小：' + this.pageSize);
            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                courseName: this.sTblHeadList.courseName,
                colName: this.sTblHeadList.colName,
            };
            let url =  this.firstPath + '/selectPageInfo';
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
         * 清除搜索条件
         */
        clearSTblHeadList() {
            this.sTblHeadList.courseName = '';
            this.sTblHeadList.colName = '';
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
         * 新增表头信息
         */
        addTblHeadList() {
            // 关闭模态框
            this.addTblHeadListModal = false;
            let data = {
                planId: this.tblHeadList.planId,
                colName: this.tblHeadList.colName,
                colNbr: this.tblHeadList.colNbr,
                rowNbr: this.tblHeadList.rowNbr,
                isEmpty: this.tblHeadList.isEmpty,
                userId: this.tblHeadList.userId,
                createdDate: this.tblHeadList.createdDate,
                colStart: this.tblHeadList.colStart,
                colEnd: this.tblHeadList.colEnd,
                rowStart: this.tblHeadList.rowStart,
                rowEnd: this.tblHeadList.rowEnd,
                courseName: this.tblHeadList.courseName,
                
            };
            let url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addTblHeadListSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        /**
         * 新增表头信息回调函数
         * @param data 请求返回参数
         */
        addTblHeadListSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            switch (data.code) {
            case 200:
                // 关闭模态框
                this.addTblHeadListModal = false;
                messageSuccess( "新增表头信息成功");
                // 重新查询数据
                this.filter();
                // 清除课程信息
                this.clearTblHeadList();
                break;
            case 420:
                messageError(this, data.msg);
                break;
            default:
                break;
        }
    },
        /**
         * 取消新增表头
         */
        cancelAddTblHeadList() {
            // 关闭模态框
            this.addTblHeadListModal = false;
            // 清除课程信息
            this.clearTblHeadList();
        },

        /**
         * 打开编辑表头信息模态框
         * @param index 当前数据索引
         */
        openEditTblHeadListModal(index) {
            console.log(this.nowData[index]);
            this.tblHeadList.id = this.nowData[index].id;
            this.tblHeadList.planId = this.nowData[index].planId;
            this.tblHeadList.tblHeadList = this.nowData[index].tblHeadList;
            this.tblHeadList.colName = this.nowData[index].colName;
            this.tblHeadList.colNbr = this.nowData[index].colNbr;
            this.tblHeadList.rowNbr = this.nowData[index].rowNbr;
            this.tblHeadList.isEmpty = this.nowData[index].isEmpty;
            this.tblHeadList.userId = this.nowData[index].userId;
            this.tblHeadList.createdDate = this.nowData[index].createdDate;
            this.tblHeadList.state = this.nowData[index].state;
            this.tblHeadList.stateDate = this.nowData[index].stateDate;
            this.tblHeadList.colStart = this.nowData[index].colStart;
            this.tblHeadList.colEnd = this.nowData[index].colEnd;
            this.tblHeadList.rowStart = this.nowData[index].rowStart;
            this.tblHeadList.rowEnd = this.nowData[index].rowEnd;
            this.tblHeadList.courseName = this.nowData[index].courseName;
            // 打开模态框
            this.editTblHeadListModal = true;
        },
        /**
         * 修改表头信息
         */
        editTblHeadList() {
            // 关闭模态框
            this.editTblHeadListModal = false;
            let data = {
                id: this.tblHeadList.id,
                planId: this.tblHeadList.planId,
                colName: this.tblHeadList.colName,
                colNbr: this.tblHeadList.colNbr,
                rowNbr: this.tblHeadList.rowNbr,
                isEmpty: this.tblHeadList.isEmpty,
                userId: this.tblHeadList.userId,
                createdDate: this.tblHeadList.createdDate,
                state: this.tblHeadList.state,
                stateDate: this.tblHeadList.stateDate,
                colStart: this.tblHeadList.colStart,
                colEnd: this.tblHeadList.colEnd,
                rowStart: this.tblHeadList.rowStart,
                rowEnd: this.tblHeadList.rowEnd,
                courseName: this.tblHeadList.courseName,
            };
            let url =  this.firstPath + '/update';
            callAjaxPost(url, data, this.editTblHeadListSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editTblHeadListSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            if (data.info === 'success') {
                messageSuccess( '修改表头信息成功！');
            } else if (data.info === 'fail') {
                messageError(this, '修改表头信息失败！');
            }
            // 重新查询数据
            this.filter();
            // 清除课程信息
            this.clearTblHeadList();
        },
        /**
         * 取消修改课程信息
         */
        cancelEditTblHeadList() {
            // 关闭模态框
            this.editTblHeadListModal = false;
            // 清除课程信息
            this.clearTblHeadList();
        },
        /**
         * 清除课程信息
         */
        clearTblHeadList() {
            this.tblHeadList.id = '';
            this.tblHeadList.planId = '';
            this.tblHeadList.colName = '';
            this.tblHeadList.colNbr = '';
            this.tblHeadList.rowNbr = '';
            this.tblHeadList.isEmpty = '';
            this.tblHeadList.userId = '';
            this.tblHeadList.createdDate = '';
            this.tblHeadList.state = '';
            this.tblHeadList.stateDate = '';
            this.tblHeadList.colStart = '';
            this.tblHeadList.colEnd = '';
            this.tblHeadList.rowStart = '';
            this.tblHeadList.rowEnd = '';
            this.tblHeadList.courseName = '';     
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
        openRemoveTblHeadListModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning(this, '请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeTblHeadListModal = true;

        },

        /**
         * 批量删除数据
         */
        removeTblHeadListSelect() {
            // 关闭模态框
            this.removeTblHeadListModal = false;
            let idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            console.log(idList);
            let data = idList;
            let url =  this.firstPath + '/deleteSelection';
            callAjaxPost(url, data, this.removeTblHeadListSelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        /**
         * 批量删除数据成功回调函数
         */
        removeTblHeadListSelectSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            if (data.info === 'success') {
                messageSuccess( '批量删除成功！');
            } else if (data.info === 'fail') {
                messageError(this, '批量删除失败！');
            }
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },

        /**
         * 删除课程信息
         * @param index
         */
        removeTblHeadList(index) {
            let data = this.nowData[index].id;
            let url =  this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeTblHeadListSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        removeTblHeadListSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '表头信息删除成功');
            // 重新查询数据
            this.filter();
        },


    }
});
