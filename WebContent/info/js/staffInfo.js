var vStaffInfo = new Vue({
    el: '#staffInfo',
    data: function () {
        return {
            firstPath: '/info/staffInfo',// 请求一级路径
            nowData: [], column: [], loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            staffInfo: {
            	 id: '', staffName: '', staffCode: '', userId: '', birthday: '', sex: '',createdDate: '', stateDate: '', state: '',
            },// 实体类
            sStaffInfo: {
            	staffName: '',
            	staffCode: '',
            },// 搜索信息
            StaffInfoTemp: '',// 修改临时存放信息
            addStaffInfoModal: false,// 新增员工信息模态框
            editStaffInfoModal: false,// 编辑员工信息模态框
            removeStaffInfoModal: false,// 删除员工信息模态框
            removeStaffInfoSelectModal: false,// 批量删除员工信息模态框
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
        	let data = {tableName: "staff_info"};
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
        	console.log("表头信息");
        	console.log(data);
        	 // 生成表头
            this.column = showCol(data.obj.key, data.obj.title);
            // 添加自定义slot-scope
            this.column.push(headActionSlot());
            // 添加自定义操作栏
            //this.column.push(headAction(false, null, true, this.openEditstaffModal, true, this.removestaff));
            // 添加多选
            this.column.unshift(headSelection());
        },
       
        /**
         * 表格过滤查询
         */
        filter() {
        	// 检查数据格式
            if (checkLength(this.sStaffInfo.staffName, '50', '员工姓名不能超过50个字符') ||
            	checkLength(this.sStaffInfo.staffCode, '50', '员工编码不能超过50个字符')) {
                return;
            }
            
            console.log('当前页：' + this.pageNum);
            console.log('页面大小：' + this.pageSize);
            
            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                staffName: this.sStaffInfo.staffName,
                staffCode: this.sStaffInfo.staffCode,
            };

            let url =  this.firstPath + '/selectPageInfo';
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
        	console.log("表格内容");
        	console.log(data);
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
        clearSStaffInfo() {
            this.sStaffInfo.staffName = '';
            this.sStaffInfo.staffCode = '';
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
         * 新增员工信息
         */
        addStaffInfo() {
        	// 检查数据格式
        	 if (this.checkStaffInfo()) {
                 return;
             }
         	// 发送请求
            let data = {
                     staffName: this.staffInfo.staffName,
                     staffCode: this.staffInfo.staffCode,
                     userId: this.staffInfo.userId,
                     birthday: this.staffInfo.birthday,
                     sex: this.staffInfo.sex,
            };
            let url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addStaffInfoSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        
        
        /**
         * 新增员工信息回调函数
         * @param data 请求返回参数
         */
        addStaffInfoSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            switch (data.code) {
            case 200:
                // 关闭模态框
                this.addStaffInfoModal = false;
                messageSuccess( "新增员工信息成功");
                // 重新查询数据
                this.filter();
                // 清除员工信息
                this.clearStaffInfo();
                break;
            case 420:
                messageError(this, data.msg);
                break;
            default:
                break;
            }
        },
        /**
         * 取消新增员工信息
         */
        cancelAddStaffInfo() {
            // 关闭模态框
            this.addStaffInfoModal = false;
            // 清除员工信息
            this.clearStaffInfo();
        },

        /**
         * 打开编辑员工信息模态框
         * @param index 当前数据索引
         */
        openEditStaffInfoModal(index) {
        	this.staffInfoTemp = this.nowData[index];
        	
            this.staffInfo.id = this.nowData[index].id;
            this.staffInfo.staffName = this.nowData[index].staffName;
            this.staffInfo.staffCode = this.nowData[index].staffCode;
            this.staffInfo.userId = this.nowData[index].userrId;
            this.staffInfo.birthday = this.nowData[index].birthdayString;
            this.staffInfo.sex = this.nowData[index].sex;
            // 打开模态框
            this.editStaffInfoModal = true;
        },


        /**
         * 修改员工信息
         */
        editStaffInfo() {
        	console.log(this.staffInfoTemp);
            // 判断数据是否修改

            // 检查数据格式
        	 if (this.checkStaffInfo()) {
                 return;
             }
            let data = {
            		id: this.staffInfo.id,
                    staffName: this.staffInfo.staffName,
                    staffCode: this.staffInfo.staffCode,
                    userId: this.staffInfo.userId,
                    birthday: this.staffInfo.birthday,
                    sex: this.staffInfo.sex,
            };
            let url = this.firstPath + '/update';
            callAjaxPost(url, data, this.editStaffInfoSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editStaffInfoSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            switch (data.code) {
            case 200:
                // 关闭模态框
                this.editStaffInfoModal = false;
                messageSuccess( "员工信息修改成功");
                // 重新查询数据
                this.filter();
                // 清除员工信息
                this.clearStaffInfo();
                break;
            case 420:
                messageError(this, data.msg);
                break;
            default:
                break;
            }
        },

        /**
         * 取消修改员工信息
         */
        cancelEditStaffInfo() {
            // 关闭模态框
            this.editStaffInfoModal = false;
            // 清除员工信息
            this.clearStaffInfo();
        },
        
        /**
         * 检查员工信息数据格式
         * @return {boolean} 若数据格式错误,返回true
         */
        checkStaffInfo() {
        	if (checkEmpty(this.staffInfo.staffName, '员工姓名不能为空') ||
                    checkLength(this.staffInfo.staffName, '50', '员工姓名不能超过50个字符') ||
                    checkEmpty(this.staffInfo.staffCode, '员工编码不能为空') ||
                    checkLength(this.staffInfo.staffCode, '50', '员工编码不能超过50个字符')) {
        		return true;
                }
        },

        /**
         * 清除员工信息
         */
        clearStaffInfo() {
        	this.staffInfo.id = '';
            this.staffInfo.staffName = '';
            this.staffInfo.staffCode = '';
            this.staffInfo.userId = '';
            this.staffInfo.birthday = '';
            this.staffInfo.sex = '';
            this.staffInfo.state = ''
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
         * 打开删除多选员工信息模态框
         */
        openRemoveStaffInfoSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning(this, '请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeStaffInfoSelectModal = true;
        },
        /**
         * 批量删除数据
         */
        removeStaffInfoSelect() {
            // 关闭模态框
            this.removeStaffInfoModal = false;
            let idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            console.log(idList);
            let data = idList;
            let url = this.firstPath + '/deleteSelection';
            callAjaxPost(url, data, this.removeStaffInfoSelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        /**
         * 批量删除数据成功回调函数
         */
        removeStaffInfoSelectSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '成功删除 ' + data.obj + ' 条记录');
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },

        /**
         * 打开删除员工信息模态框
         * @param index 当前数据索引
         */
        openRemoveStaffInfoModal(index) {
            this.staffinfo.id = this.nowData[index].id;
            this.removeStaffInfoModal = true;
        },
        
        /**
         * 删除员工信息
         * @param index
         */
        removeStaffInfo(index) {
        	let data = this.nowData[index].id;
            let url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeStaffInfoSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        removeStaffInfoSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '员工信息删除成功');
            // 重新查询数据
            this.filter();
        },


    }
});