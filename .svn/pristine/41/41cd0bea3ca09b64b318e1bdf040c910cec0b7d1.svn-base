let vSupportCoefficient = new Vue({
    el: '#supportCoefficient',
    data: function () {
        return {
            firstPath: '/indicator/supportCoefficient',// 请求一级路径
            relatPath: '/indicator/indicatorRelat',// 请求一级路径
            relatTree: [],// 指标关联树
            nowData: [], column: [], loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            supportCoefficient: {
                id: '', yearPlanId: '', courseInfoId: '', value: '', indicatorRelatId: '',
            },// 实体类
            indicatorRemove: {
                node: '', data: ''
            },// 树删除临时变量
            addSupportCoefficientModal: false,// 新增支撑系数模态框
            editSupportCoefficientModal: false,// 编辑支撑系数模态框
            removeSupportCoefficientModal: false,// 删除支撑系数模态框

            yearPlanList: [],//年份集合,

            courseInfoList: [],//课程信息集合
        }
    },
    components: {
        'layout-header': httpVueLoader('/layout/layout-header.vue'),
        'layout-sider': httpVueLoader('/layout/layout-sider.vue'),
        'layout-footer': httpVueLoader('/layout/layout-footer.vue')
    },
    mounted() {
        
        
        this.initPage();
    },
    methods: {
        /**
         * 页面初始化加载项 表格表头
         */
        initPage() {
            this.getYearPlanList();
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
            this.supportCoefficient.yearPlanId = data.obj[1].id;
            this.getCourseInfoList();
        },

        /**
         * 获取课程信息集合
         */
        getCourseInfoList() {
            let url = '/manage/courseInfo/listCourseInfo';
            callAjaxGetNoParam(url, this.getCourseInfoListSuc);
        },

        getCourseInfoListSuc(data) {
            this.courseInfoList = data.obj;
            this.supportCoefficient.courseInfoId = data.obj[0].id;
            this.getIndicatorList();
        },



        /**
         * 查询指标树
         */
        getIndicatorList() {
         
            if (checkEmpty(this.supportCoefficient.yearPlanId, '请选择年份！') ||
                    checkEmpty(this.supportCoefficient.courseInfoId, '请选择课程！') 
                ) {
                    return;
                }
            
           
            let data = {
                yearPlanId: this.supportCoefficient.yearPlanId,
                courseInfoId: this.supportCoefficient.courseInfoId,
            };
            console.log('yearPlanId:' + this.supportCoefficient.yearPlanId);
            console.log('courseInfoId:' + this.supportCoefficient.courseInfoId);
            let url = this.relatPath + '/selectIndicatorList';
            callAjaxPost(url, data, this.getIndicatorListSuc);
            this.loadingMsg = messageLoading();
        },
        getIndicatorListSuc(data) {
            console.log(data);
            closeMessageLoading(this.loadingMsg);
            this.relatTree = data.obj;
        },

        /**
         * 打开编辑支撑系数模态框
         *
         * @param index
         *            当前数据索引
         */
        openEditSupportCoefficientModal(data) {
            if (this.supportCoefficient.courseInfoId == '') {
                messageInfo('请选择课程!');
                return;
            }
            console.log(data);
            console.log(data.supportCoefficientId);
            this.supportCoefficient.id = data.supportCoefficientId;
            this.supportCoefficient.value = data.value;
            this.supportCoefficient.indicatorRelatId = data.id.split("-")[0];
            // 打开模态框
            this.editSupportCoefficientModal = true;
        },
        /**
         * 修改支撑系数
         */
        editSupportCoefficient() {
            let data = {
                id: this.supportCoefficient.id,
                courseInfoId: this.supportCoefficient.courseInfoId,
                value: this.supportCoefficient.value,
                indicatorRelatId: this.supportCoefficient.indicatorRelatId,
            };
            let url = this.firstPath + '/update';
            callAjaxPost(url, data, this.editSupportCoefficientSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editSupportCoefficientSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            switch (data.code) {
                case 200:
                    // 关闭模态框
                    this.editSupportCoefficientModal = false;
                    messageSuccess( "支撑系数修改成功");
                    // 重新查询数据
                    this.getIndicatorList();
                    // 清除支撑系数
                    this.clearSupportCoefficient();
                    break;
                case 420:
                    messageError(this, data.msg);
                    break;
                default:
                    break;
            }
        },
        /**
         * 取消修改支撑系数
         */
        cancelEditSupportCoefficient() {
            // 关闭模态框
            this.editSupportCoefficientModal = false;
            // 清除支撑系数
            this.clearSupportCoefficient();
        },
        /**
         * 清除支撑系数
         */
        clearSupportCoefficient() {
            this.supportCoefficient.id = '';
            this.supportCoefficient.value = '';
            this.supportCoefficient.indicatorRelatId = '';
        },

        /**
         * 清除搜索条件
         */
        clearSearchSupportCoefficient() {
            this.supportCoefficient.yearPlanId = '';
            this.supportCoefficient.courseInfoId = '';
        },

        /**
         * 打开删除支撑系数模态框
         */
        openRemoveSupportCoefficientModal(node, data) {
            console.log(node);
            console.log(data);
            // 打开模态框
            this.removeSupportCoefficientModal = true;
            this.indicatorRemove.node = node;
            this.indicatorRemove.data = data;
        },

        /**
         * 删除支撑系数
         */
        removeSupportCoefficient() {
            console.log(this.indicatorRemove.data);
            let postData = this.indicatorRemove.data.supportCoefficientId;
            let url = this.firstPath + '/delete';
            callAjaxPost(url, postData, this.removeSupportCoefficientSuc);
            this.loadingMsg = messageLoading();
        },

        removeSupportCoefficientSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '支撑系数删除成功');
            // 重新查询数据
            this.getIndicatorList();
        },


    }
});

