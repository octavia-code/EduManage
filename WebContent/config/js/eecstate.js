var vEecstate = new Vue({
    el: '#eecstate',
    data: function () {
        return {

            firstPath: '/config/eecstate',// 请求一级路径
            nowData: [], column: [], loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            eecstate: {
                id: '', table: '', tableName: '', colm: '', colmName: '', code: '', codeName: '', seq: '',
                remark: ''
            },// 实体类
            sEecstate: {
                tableName: '', colmName: '', code: '', codeName: '', seq: ''
            },// 搜索信息
            addEecstateModal: false,// 新增域表信息模态框
            editEecstateModal: false,// 编辑域表信息模态框
            removeEecstateModal: false,// 删除域表信息模态框
            removeEecstateSelectModal: false,//批量删除域表信息模态框
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
            let data = {tableName: "eecstate"};
            let url = '/config/tableTitle/listByTableName';
            callAjaxPost(url, data, this.getTableHeadSuc);
        },

        /**
         * 获取表头回调函数
         * @param data  请求返回参数
         */
        getTableHeadSuc(data) {
            // 生成表头
            this.column = showCol(data.obj.key, data.obj.title);
            //添加“备注”所在列信息
            this.column.push(headTooltip('remark', '备注'));
            // 添加自定义slot-scope
            this.column.push(headActionSlot());
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
            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                tableName: this.sEecstate.tableName,
                colmName: this.sEecstate.colmName,
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
            // 再次设置当前页码,若查询记录为空，设为第一页
            this.pageNum = data.obj.pageNum === 0 ? 1 : data.obj.pageNum;
        },

        /**
         * 清除搜索条件
         */
        clearSEecstate() {
            this.sEecstate.tableName = '';
            this.sEecstate.colmName = '';
            this.sEecstate.code = '';
            this.sEecstate.codeName = '';
            this.sEecstate.seq = '';
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
         * 检查域表信息数据格式
         * @return {boolean} 若数据格式错误,返回true
         */
        checkEecstate() {
            if (checkEmpty(this.eecstate.table, '请输入表') ||
                checkLength(this.eecstate.table, '20', '表不能超过20个字符') ||
                checkEmpty(this.eecstate.tableName, '请输入表名称') ||
                checkLength(this.eecstate.tableName, '20', '表名称不能超过20个字符') ||
                checkEmpty(this.eecstate.colm, '请输入列') ||
                checkLength(this.eecstate.colm, '50', '列名称不能超过50个字符') ||
                checkEmpty(this.eecstate.colmName, '请输入列名称') ||
                checkLength(this.eecstate.colmName, '20', '列名称不能超过20个字符') ||
                checkEmpty(this.eecstate.code, '请输入编码') ||
                checkLength(this.eecstate.code, '1', '编码不能超过1个字符') ||
                checkEmpty(this.eecstate.codeName, '请输入编码名称') ||
                checkLength(this.eecstate.codeName, '50', '编码名不能超过50个字符') ||
                checkEmpty(this.eecstate.seq, '请输入序号') ||
                checkLength(this.eecstate.seq, '2', '序号不能超过2个字符')) {
                return true;
            }
        },
        /**
         * 新增域表信息
         */
        addEecstate() {
            // 检查数据格式
            if (this.checkEecstate()) {
                return;
            }
            // 发送请求
            let data = {
                table: this.eecstate.table,
                tableName: this.eecstate.tableName,
                colm: this.eecstate.colm,
                colmName: this.eecstate.colmName,
                code: this.eecstate.code,
                codeName: this.eecstate.codeName,
                seq: this.eecstate.seq,
                remark: this.eecstate.remark,
            };
            let url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addEecstateSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        /**
         * 新增域表信息回调函数
         * @param data 请求返回参数
         */
        addEecstateSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            console.log("返回数据：", data);
            if (data.obj === "exist") {
                messageWarning('已存在相同记录');
                return;
            }
            messageSuccess("新增信息成功");
            this.filter();
            this.addEecstateModal = false;
        },
        /**
         * 取消新增域表
         */
        cancelAddEecstate() {
            // 关闭模态框
            this.addEecstateModal = false;
            // 清除域表信息
            this.clearEecstate();
        },

        /**
         * 打开编辑域表信息模态框
         * @param index 当前数据索引
         */
        openEditEecstateModal(index) {
            this.eecstate.id = this.nowData[index].id;
            this.eecstate.table = this.nowData[index].table;
            this.eecstate.tableName = this.nowData[index].tableName;
            this.eecstate.colm = this.nowData[index].colm;
            this.eecstate.colmName = this.nowData[index].colmName;
            this.eecstate.code = this.nowData[index].code;
            this.eecstate.codeName = this.nowData[index].codeName;
            this.eecstate.seq = this.nowData[index].seq;
            this.eecstate.remark = this.nowData[index].remark;
            // 打开模态框
            this.editEecstateModal = true;
        },
        /**
         * 修改域表信息
         */
        editEecstate() {
            // 检查数据格式
            if (this.checkEecstate()) {
                return;
            }
            /* debugger*/
            let data = {
                id: this.eecstate.id,
                table: this.eecstate.table,
                tableName: this.eecstate.tableName,
                colm: this.eecstate.colm,
                colmName: this.eecstate.colmName,
                code: this.eecstate.code,
                codeName: this.eecstate.codeName,
                seq: this.eecstate.seq,
                remark: this.eecstate.remark
            };
            let url = this.firstPath + '/update';
            callAjaxPost(url, data, this.editEecstateSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editEecstateSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            // 关闭模态框
            this.editEecstateModal = false;
            messageSuccess("域表信息修改成功");
            // 重新查询数据
            this.filter();
            // 清除域表信息
            this.clearEecstate();
        },
        /**
         * 取消修改域表信息
         */
        cancelEditEecstate() {
            // 关闭模态框
            this.editEecstateModal = false;
            // 清除域表信息
            this.clearEecstate();
        },
        /**
         * 清除域表信息
         */
        clearEecstate() {
            this.eecstate.id = '';
            this.eecstate.table = '';
            this.eecstate.tableName = '';
            this.eecstate.colm = '';
            this.eecstate.colmName = '';
            this.eecstate.code = '';
            this.eecstate.codeName = '';
            this.eecstate.seq = '';
            this.eecstate.remark = '';
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
         * 打开删除数据模态框
         */
        openRemoveEecstateSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning('请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeEecstateSelectModal = true;
        },

        /**
         * 批量删除数据
         */
        removeEecstateSelect() {
            // 关闭模态框
            this.removeEecstateModal = false;
            var idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            console.log(idList);
            let data = idList;
            let url = this.firstPath + '/deleteSelection';
            callAjaxPost(url, data, this.removeEecstateSelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        /**
         * 批量删除数据成功回调函数
         */
        removeEecstateSelectSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess('成功删除 ' + data.obj + ' 条记录！');
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },

        /**
         * 打开删除数据模态框
         * @param index 当前数据索引
         */
        openRemoveEecstate(index) {
            this.eecstate.id = this.nowData[index].id;
            this.removeEecstateModal = true;
        },

        /**
         * 删除域表信息
         * @param index
         */
        removeEecstate(index) {
            this.removeEecstateModal = false;
            let data = this.eecstate.id;
            let url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeEecstateSuc);

            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        removeEecstateSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess('域表信息删除成功！');
            // 重新查询数据
            this.filter();
        },


    }
});
