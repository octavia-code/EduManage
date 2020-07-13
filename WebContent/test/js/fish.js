var vCourseInfo = new Vue({
    el: '#courseInfo',
    data: function () {
        return {
            firstPath: '/manage/courseInfo',// 请求一级路径
            loading: false, selection: [],// 表格参数
            nowData: [
                {yiji: '信息感知能力（0.45）', erji: '最大观测范围', zhunze: '设定理想观测范围为250km，1+(X-250)/250', shuzhi: '220'},
                {yiji: '信息感知能力（0.45）', erji: '接入传感器数量', zhunze: '设定理想数量为20，1+(X-20)/20', shuzhi: '18'},
                {yiji: '信息感知能力（0.45）', erji: '观测要素种类', zhunze: '设定理想种类为8大类，1+(X-8)/8', shuzhi: '7'},
                {yiji: '信息感知能力（0.45）', erji: '核心感知变量', zhunze: '设定理想变量为36钟，1+(X-36)/36', shuzhi: '32'},
                {yiji: '信息感知能力（0.45）', erji: '传感器组网能力', zhunze: '有为1，无为0', shuzhi: '1'},
                {yiji: '信息感知能力（0.45）', erji: '信息更新周期', zhunze: '设定为1分钟，1+(1-X)/1', shuzhi: '1.5分钟'},
                {yiji: '信息感知能力（0.45）', erji: '最大感知精度', zhunze: '设定为3m，1+(3-X)/3', shuzhi: '4m'},
                {yiji: '信息传输能力（0.1）', erji: '传输带宽', zhunze: '设定8M，1+(8-x)/250', shuzhi: '8M'},
                {yiji: '信息传输能力（0.1）', erji: '传输时延', zhunze: '设定为0.5s，1+(0.5-X)/0.5', shuzhi: '0.8s'},
                {yiji: '信息存储和处理能力（0.1）', erji: '大数据存储能力', zhunze: '设定为2PB/月，1+(X-2)/2', shuzhi: '1.8PB'},
            ],
            column: [
                {title: '一级指标', key: 'yiji', width: 200},
                {title: '二级指标', key: 'erji', width: 200},
                {title: '准则', key: 'zhunze'},
                {title: '数值', key: 'shuzhi', width: 100},
            ],
            courseTypeList: [
                {id: 1, typeName: '信息感知能力（0.45）'},
                {id: 2, typeName: '信息传输能力（0.1）'},
                {id: 3, typeName: '信息存储和处理能力（0.1）'},
                {id: 4, typeName: '决策支持能力（0.3）'},
            ],//课程类别集合


            totalNum: 22, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            courseInfo: {
                id: '', courseTypeId: '', courseCode: '', courseName: '', scord: '', theoryDur: '', expDur: '',
                totalDur: '', outsideDru: '', remark: '',
            },// 实体类
            sCourseInfo: {
                courseTypeId: '', courseCode: '', courseName: ''
            },// 搜索信息
            addCourseInfoModal: false,// 新增课程信息模态框
            editCourseInfoModal: false,// 编辑课程信息模态框
            removeCourseInfoSelectModal: false,// 批量删除课程信息模态框
            removeCourseInfoModal: false,// 删除课程信息模态框

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
         */
        initPage() {
            this.setTableHead();
        },

        /**
         * 设置表头
         */
        setTableHead() {
            // 添加slot-scope操作栏
            this.column.push(headActionSlotRight());
            // 添加序号
            this.column.unshift(headIndexLeft());
            // 添加多选
            this.column.unshift(headSelectionLeft());
        },


        /**
         * 表格过滤查询
         */
        filter() {
            // // 检查数据格式
            // if (checkLength(this.sCourseInfo.courseCode, '20', '课程编码不能超过20个字符') ||
            //     checkLength(this.sCourseInfo.courseName, '200', '课程名称不能超过20个字符')) {
            //     return;
            // }
            // let data = {
            //     pageNum: this.pageNum,
            //     pageSize: this.pageSize,
            //     courseTypeId: this.sCourseInfo.courseTypeId,
            //     courseCode: this.sCourseInfo.courseCode,
            //     courseName: this.sCourseInfo.courseName
            // };
            // let url = this.firstPath + '/selectPageInfo';
            // callAjaxPost(url, data, this.filterSuc);
            // // 显示加载
            // this.loading = true;
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
            // 再次设置当前页码,若查询记录为空，设为第一页
            this.pageNum = data.obj.pageNum === 0 ? 1 : data.obj.pageNum;
        },

        /**
         * 清除搜索条件
         */
        clearSCourseInfo() {
            this.sCourseInfo.courseCode = '';
            this.sCourseInfo.courseName = '';
            this.sCourseInfo.courseTypeId = '';
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
         * @param pageSize 换后的每页条数
         */
        onPageSizeChange(pageSize) {
            this.pageSize = pageSize;
            this.filter();
        },

        /**
         * 检查课程信息数据格式
         * @return {boolean} 若数据格式错误,返回false
         */
        checkCourseInfo() {
            if (checkEmpty(this.courseInfo.courseTypeId, '请选择课程类别') || // 检查下拉框
                checkEmpty(this.courseInfo.courseCode, '请输入课程编码') ||    // 检查String是否为空
                checkLength(this.courseInfo.courseCode, '20', '课程编码不能超过20个字符') ||   // 检查String长度
                checkEmpty(this.courseInfo.courseName, '请输入课程名称') ||
                checkLength(this.courseInfo.courseName, '200', '课程名称不能超过20个字符')) {
                return true;
            }
        },

        /**
         * 新增课程信息
         */
        addCourseInfo() {
            // 检查数据格式
            if (this.checkCourseInfo()) {
                return;
            }
            // 发送请求
            let data = {
                courseTypeId: this.courseInfo.courseTypeId,
                courseCode: this.courseInfo.courseCode,
                courseName: this.courseInfo.courseName,
                scord: this.courseInfo.scord,
                theoryDur: this.courseInfo.theoryDur,
                expDur: this.courseInfo.expDur,
                totalDur: this.courseInfo.totalDur,
                outsideDru: this.courseInfo.outsideDru,
                remark: this.courseInfo.remark,
            };
            let url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addCourseInfoSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        /**
         * 新增课程信息回调函数
         *
         * @param data
         *            请求返回参数
         */
        addCourseInfoSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            if (data.obj == 'code_exist') {
                messageWarning('当前课程编码已存在');
                return;
            }
            // 关闭模态框
            this.addCourseInfoModal = false;
            messageSuccess("新增课程信息成功");
            // 重新查询数据
            this.filter();
            // 清除课程信息
            this.clearCourseInfo();
        },
        /**
         * 取消新增课程
         */
        cancelAddCourseInfo() {
            // 关闭模态框
            this.addCourseInfoModal = false;
            // 清除课程信息
            this.clearCourseInfo();
        },

        /**
         * 打开编辑课程信息模态框
         *
         * @param index
         *            当前数据索引
         */
        openEditCourseInfoModal(index) {
            this.courseInfo.id = this.nowData[index].id;
            this.courseInfo.courseTypeId = this.nowData[index].courseTypeId;
            this.courseInfo.courseCode = this.nowData[index].courseCode;
            this.courseInfo.courseName = this.nowData[index].courseName;
            this.courseInfo.scord = this.nowData[index].scord;
            this.courseInfo.theoryDur = this.nowData[index].theoryDur;
            this.courseInfo.expDur = this.nowData[index].expDur;
            this.courseInfo.totalDur = this.nowData[index].totalDur;
            this.courseInfo.outsideDru = this.nowData[index].outsideDru;
            this.courseInfo.remark = this.nowData[index].remark;
            // 打开模态框
            this.editCourseInfoModal = true;
        },
        /**
         * 修改课程信息
         */
        editCourseInfo() {
            // 检查数据格式
            if (this.checkCourseInfo()) {
                return;
            }
            let data = {
                id: this.courseInfo.id,
                courseTypeId: this.courseInfo.courseTypeId,
                courseCode: this.courseInfo.courseCode,
                courseName: this.courseInfo.courseName,
                scord: this.courseInfo.scord,
                theoryDur: this.courseInfo.theoryDur,
                expDur: this.courseInfo.expDur,
                totalDur: this.courseInfo.totalDur,
                outsideDru: this.courseInfo.outsideDru,
                remark: this.courseInfo.remark,
            };
            let url = this.firstPath + '/update';
            callAjaxPost(url, data, this.editCourseInfoSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editCourseInfoSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            if (data.obj == 'code_exist') {
                messageWarning('当前课程编码已存在');
                return;
            }
            // 关闭模态框
            this.editCourseInfoModal = false;
            messageSuccess("课程信息修改成功");
            // 重新查询数据
            this.filter();
            // 清除课程信息
            this.clearCourseInfo();
        },
        /**
         * 取消修改课程信息
         */
        cancelEditCourseInfo() {
            // 关闭模态框
            this.editCourseInfoModal = false;
            // 清除课程信息
            this.clearCourseInfo();
        },
        /**
         * 清除课程信息
         */
        clearCourseInfo() {
            this.courseInfo.id = '';
            this.courseInfo.courseTypeId = '';
            this.courseInfo.courseCode = '';
            this.courseInfo.courseName = '';
            this.courseInfo.scord = '';
            this.courseInfo.theoryDur = '';
            this.courseInfo.expDur = '';
            this.courseInfo.totalDur = '';
            this.courseInfo.outsideDru = '';
            this.courseInfo.remark = '';
            this.courseInfo.state = '';
        },

        /**
         * 在多选模式下有效，只要选中项发生变化时就会触发
         *
         * @param selection
         *            已选项数据
         */
        onSelectionChange(selection) {
            this.selection = selection;
        },

        /**
         * 打开删除多选课程信息模态框
         */
        openRemoveCourseInfoSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning(this, '请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeCourseInfoSelectModal = true;
        },

        /**
         * 批量删除数据
         */
        removeCourseInfoSelect() {
            // 关闭模态框
            this.removeCourseInfoSelectModal = false;
            let idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            let data = idList;
            let url = this.firstPath + '/deleteSelection';
            callAjaxPost(url, data, this.removeCourseInfoSelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        /**
         * 批量删除数据成功回调函数
         */
        removeCourseInfoSelectSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess('成功删除 ' + data.obj + ' 条记录');
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },

        /**
         * 打开删除课程信息模态框
         * @param index 当前数据索引
         */
        openRemoveCourseInfoModal(index) {
            this.courseInfo.id = this.nowData[index].id;
            this.removeCourseInfoModal = true;
        },

        /**
         * 删除课程信息
         */
        removeCourseInfo() {
            this.removeCourseInfoModal = false;
            let data = this.courseInfo.id;
            let url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeCourseInfoSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        removeCourseInfoSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess('课程信息删除成功');
            // 重新查询数据
            this.filter();
        },


    }
});

