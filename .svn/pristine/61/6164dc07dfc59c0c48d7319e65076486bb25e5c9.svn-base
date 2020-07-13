var vCourseOutline = new Vue({
    el: '#courseOutLine',
    data: function () {
        return {
            firstPath: '/outline/courseOutline',// 请求一级路径
            courseOutlineTree: [],// 课程大纲树
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            courseOutline: {
                id: '', outlineNo: '', courseOutlineTypeId: '', courseInfoId: '', state: '',
                createdDate: '', stateDate: '', outlineName: '', fileInfoId: '', remark: '',
                outlineNoName: '', typeName: '', courseName: '', fileName: '', accessUrl: '',
                fileInfoOldId: ''
            },// 课程大纲树关联实体类
            sCourseOutline: {
                outlineNo: 'E', courseOutlineTypeId: '', courseInfoId: '',
            },// 课程大纲搜索条件
            courseOutlineRemove: {
                node: '', data: ''
            },// 树删除临时变量
            assessment: {
                id: '', assessName: '', assessRate: '', courseOutlineId: '', seq: ''
            },//课程考核实体类
            assessItem: {
                id: '', assessmentId: '', indicatorSecId: '', content: '', maxScore: '', remark: ''
            },
            cascaderIndicatorIdList: [],// 指标关联级联选择id集合
            indicatorCascader: '',

            addCourseOutlineModal: false,// 大纲版本新增模态框
            editCourseOutlineModal: false,// 大纲版本编辑模态框
            removeCourseOutlineModal: false,// 大纲版本删除模态框

            addAssessmentModal: false,// 课程考核新增模态框
            editAssessmentModal: false,// 课程考核编辑模态框
            removeAssessmentModal: false,// 课程考核删除模态框

            addAssessItemModal: false,// 课程考核项新增模态框
            editAssessItemModal: false,//课程考核项编辑模态框
            removeAssessItemModal: false,// 课程考核项删除模态框

            outlineNoList: [],//大纲版本号集合
            courseOutlineTypeList: [],//大纲课程类别集合
            courseInfoList: [],//课程信息集合
            relatCascader: [],// 指标关联级联选择

            uploadUrl: UPLOAD_URL, // 上传地址
            textFormData: new FormData(), // 文本表单对象
            textFileType: 'T',// 文件类型
            textFileList: [],// 文件列表
            textIdList: [],// 图片id列表
            maxTextSize: 2,// 最大大小，单位mb(单位可自行调整)

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
        initPage() {
            this.getOutlineNoList();
            this.getCourseOutlineTypeList();
            this.getCourseInfoList();
            this.getIndicatorList();
        },
        /**
         * 查询指标树
         */
        getIndicatorList() {
            let data = {yearPlanId: ''};
            let url = '/indicator/indicatorRelat/selectIndicatorList';
            callAjaxPost(url, data, this.getIndicatorListSuc);
            this.loadingMsg = messageLoading();
        },
        getIndicatorListSuc(data) {
            console.log(data);
            closeMessageLoading(this.loadingMsg);
            this.relatCascader = data.obj;
            console.log(this.relatCascader);
        },

        /**
         * 获取大纲版本号集合
         */
        getOutlineNoList() {
            let url = this.firstPath + '/listOutlineNoByEecstate';
            callAjaxGetNoParam(url, this.getOutlineNoListSuc);
        },
        getOutlineNoListSuc(data) {
            this.outlineNoList = data.obj;
        },

        /**
         * 获取大纲课程类别集合
         */
        getCourseOutlineTypeList() {
            let url = '/outline/courseOutlineType/ListCourseOutlineType';
            callAjaxGetNoParam(url, this.getCourseOutlineTypeListSuc);
        },
        getCourseOutlineTypeListSuc(data) {
            this.courseOutlineTypeList = data.obj;
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
        },

        /**
         * 查询课程大纲集合树
         */
        getCourseOutlineTree() {
            // 检查数据
            if (checkEmpty(this.sCourseOutline.outlineNo, '请选择大纲版本号！') ||
                checkEmpty(this.sCourseOutline.courseOutlineTypeId, '请选择课程类别！') ||
                checkEmpty(this.sCourseOutline.courseInfoId, '请选择课程！')) {
                return;
            }
            let data = {
                outlineNo: this.sCourseOutline.outlineNo,
                courseOutlineTypeId: this.sCourseOutline.courseOutlineTypeId,
                courseInfoId: this.sCourseOutline.courseInfoId,
            };
            let url = this.firstPath + '/selectCourseOutlineTree';
            callAjaxPost(url, data, this.getCourseOutlineTreeSuc);
            this.loadingMsg = messageLoading();
        },
        getCourseOutlineTreeSuc(data) {
            console.log(data);
            closeMessageLoading(this.loadingMsg);
            if (data.obj.id !== null) {
                // 先设空，再像列表添加值
                this.courseOutlineTree = [];
                this.courseOutlineTree.push(data.obj);
            } else {
                messageInfo('未查询到相关数据！');
            }
        },

        /**
         * 添加大纲前的检查
         * 因为有上传文件操作所以单独写出一个方法
         */
        checkAddCourseOutline() {
            // 验证数据
            if (checkEmpty(this.courseOutline.outlineNo, '请选择版本号') ||
                checkEmpty(this.courseOutline.courseOutlineTypeId, '请选择课程类别') ||
                checkEmpty(this.courseOutline.courseInfoId, '请选择课程') ||
                checkEmpty(this.courseOutline.outlineName, '请输入大纲名称')) {
                return;
            }
            // 检查是否有文件
            if (this.textFileList.length === 0) {
                messageWarning('请上传文件');
                return;
            }
            // 验证数据在数据库中是否存在
            let data = {
                outlineNo: this.courseOutline.outlineNo,
                courseOutlineTypeId: this.courseOutline.courseOutlineTypeId,
                courseInfoId: this.courseOutline.courseInfoId,
            };
            let url = this.firstPath + '/checkIsExist';
            callAjaxPost(url, data, this.checkAddCourseOutlineSuc);
        },
        checkAddCourseOutlineSuc(data) {
            if (data.obj === 'exist') {
                messageWarning('当前大纲信息已存在,请勿重复添加');
                return;
            }
            this.submitTextList();
        },

        /**
         * 更新课程大纲前检查
         */
        checkEditCourseOutline() {
            // 验证数据
            if (checkEmpty(this.courseOutline.outlineName, '请输入大纲名称')) {
                return;
            }
            // 检查是否有文件
            if (this.textFileList.length === 0) {
                messageWarning('请上传文件');
                return;
            }
            // 存在新文件，将之前的文件id存放起来
            this.courseOutline.fileInfoOldId = this.courseOutline.fileInfoId;
            this.submitTextList();
        },

        /**
         * 提交文本表单
         */
        submitTextList() {
            console.log('上传大纲文件');
            // 添加文件列表
            for (let i = 0; i < this.textFileList.length; i++) {
                // 文件大小超过最大值
                if (this.textFileList[i].size > this.maxTextSize * 1024 * 1024) {
                    messageWarning('第' + (i + 1) + '个文件' + ':' + this.textFileList[i].name + ' 已超过' + this.maxTextSize + 'mb!');
                    this.textFormData = new FormData(); // 创建新的表单
                    return;
                }
                this.textFormData.append("fileList", this.textFileList[i].raw);
            }
            // 添加文件类型
            this.textFormData.append("fileType", this.textFileType);
            callAjaxFile(this.uploadUrl, this.textFormData, this.submitTextListSuc);
        },
        submitTextListSuc(data) {
            console.log(data);
            if (data.obj === null) {
                messageError('文件上传失败');
                return;
            }
            this.textFormData = new FormData();// 创建新的表单
            this.textFileList = [];// 清空文件列表
            this.courseOutline.fileInfoId = data.obj[0];//设置大纲文件id
            // 添加大纲
            if (this.addCourseOutlineModal) {
                this.addCourseOutline();
            }
            // 修改大纲
            if (this.editCourseOutlineModal) {
                this.editCourseOutline();
            }
        },

        /**
         * 文件超出个数限制
         */
        handleExceedTextLimit() {
            messageWarning('已超出最大文件个数');
        },

        /**
         * 文件状态改变时的钩子，添加文件、上传成功和上传失败时都会被调用
         * @param file 当前文件
         * @param fileList 文件列表
         */
        handleChangeText(file, fileList) {
            console.log(file);
            this.textFileList = fileList;
        },
        /**
         * 文件列表移除文件时的钩子
         * @param file 移除的文件
         * @param fileList 文件列表
         */
        handleRemoveText(file, fileList) {
            console.log(file);
            this.textFileList = fileList;
        },

        /**
         * 删除文件之前的钩子，参数为上传的文件和文件列表，
         * 若返回 false 或者返回 Promise 且被 reject，则停止删除。
         * @param file 移除的文件
         * @param fileList 文件列表
         */
        beforeRemoveText(file, fileList) {
            return this.$confirm(`确定移除 ${ file.name }？`);
        },

        /**
         * 添加大纲
         */
        addCourseOutline() {
            let data = {
                outlineNo: this.courseOutline.outlineNo,
                courseOutlineTypeId: this.courseOutline.courseOutlineTypeId,
                courseInfoId: this.courseOutline.courseInfoId,
                outlineName: this.courseOutline.outlineName,
                fileInfoId: this.courseOutline.fileInfoId,
                remark: this.courseOutline.remark,
            };
            let url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addCourseOutlineSuc);
            this.loadingMsg = messageLoading();
        },
        addCourseOutlineSuc(data) {
            closeMessageLoading(this.loadingMsg);
            messageSuccess("新增大纲成功");
            this.cancelAddCourseOutline();
        },

        /**
         * 取消添加大纲
         */
        cancelAddCourseOutline() {
            this.addCourseOutlineModal = false;
            this.clearCourseOutline();
        },

        /**
         * 打开编辑课程大纲模态框
         * @param data
         */
        openEditCourseOutlineModal(data) {
            console.log(data);
            this.courseOutline.id = data.id;
            this.courseOutline.outlineNoName = data.outlineNoName;
            this.courseOutline.typeName = data.typeName;
            this.courseOutline.courseName = data.courseName;
            this.courseOutline.outlineNo = data.outlineNo;
            this.courseOutline.courseOutlineTypeId = data.courseOutlineTypeId;
            this.courseOutline.courseInfoId = data.courseInfoId;
            this.courseOutline.outlineName = data.outlineName;
            this.courseOutline.fileInfoId = data.fileInfoId;
            this.courseOutline.remark = data.remark;
            // 打开课程考核模态框
            this.editCourseOutlineModal = true;
        },
        //修改课程大纲考核
        editCourseOutline() {
            let data = {
                id: this.courseOutline.id,
                outlineNo: this.courseOutline.outlineNo,
                courseOutlineTypeId: this.courseOutline.courseOutlineTypeId,
                courseInfoId: this.courseOutline.courseInfoId,
                outlineName: this.courseOutline.outlineName,
                fileInfoId: this.courseOutline.fileInfoId,
                fileInfoOldId: this.courseOutline.fileInfoOldId,
                remark: this.courseOutline.remark,
            };
            console.log(data);
            let url = this.firstPath + '/update';
            callAjaxPost(url, data, this.editCourseOutlineSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editCourseOutlineSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            // 关闭模态框
            this.editCourseOutlineModal = false;
            messageSuccess("课程考核修改成功");
            // 重新查询数据
            this.getCourseOutlineTree();
            // 清除教学计划表
            this.clearCourseOutline();
        },
        /**
         * 取消修改
         */
        cancelEditCourseOutline() {
            // 关闭模态框
            this.editCourseOutlineModal = false;
            // 清除教学计划表
            this.clearCourseOutline();
        },

        /**
         * 清除课程大纲的信息
         */
        clearCourseOutline() {
            this.courseOutline.id = '';
            this.courseOutline.outlineNo = '';
            this.courseOutline.courseOutlineTypeId = '';
            this.courseOutline.courseInfoId = '';
            this.courseOutline.outlineName = '';
            this.courseOutline.fileInfoId = '';
            this.courseOutline.remark = '';
        },

        /**
         * 打开新增课程考核模态框
         * @param data
         */
        openAddAssessmentModal(data) {
            console.log(data)
            let treeList = data.id.split("-");
            this.assessment.courseOutlineId = treeList[0];
            this.addAssessmentModal = true;
        },

        /**
         * 新增课程考核
         */
        addAssessment() {
            let data = {
                assessName: this.assessment.assessName,
                assessRate: this.assessment.assessRate,
                courseOutlineId: this.assessment.courseOutlineId,
                seq: this.assessment.seq,
            };
            let url = '/outline/assessment/insert';
            callAjaxPost(url, data, this.addAssessmentSuc);
            this.loadingMsg = messageLoading();
        },
        addAssessmentSuc(data) {
            closeMessageLoading(this.loadingMsg);
            messageSuccess("新增课程考核成功");
            this.getCourseOutlineTree();
            this.cancelAddAssessment();
        },

        /**
         * 取消新增课程考核
         */
        cancelAddAssessment() {
            this.addAssessmentModal = false;
            this.assessment.id = '';
            this.assessment.assessName = '';
            this.assessment.assessRate = '';
            this.assessment.courseOutlineId = '';
            this.assessment.seq = '';
        },
        /**
         * 打开课程考核编辑模态框
         */
        openEditAssessmentModal(data) {
            let idList = data.id.split("-");
            console.log(idList[1]);
            this.assessment.id = idList[1];
            this.assessment.assessName = data.assessName;
            this.assessment.assessRate = data.assessRate;
            this.assessment.courseOutlineId = data.courseOutlineId;
            this.assessment.seq = data.seq;
            //打开课程考核模态框
            this.editAssessmentModal = true;
        },
        //修改课程考核
        editAssessment() {
            let data = {
                id: this.assessment.id,
                assessName: this.assessment.assessName,
                assessRate: this.assessment.assessRate,
                courseOutlineId: this.assessment.courseOutlineId = '',
                seq: this.assessment.seq,
            };
            let url = '/outline/assessment/update';
            callAjaxPost(url, data, this.editAssessmentSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editAssessmentSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            switch (data.code) {
                case 200:
                    // 关闭模态框
                    this.editAssessmentModal = false;
                    messageSuccess("课程大纲修改成功");
                    // 重新查询数据
                    this.getCourseOutlineTree();
                    // 清除教学计划表
                    this.clearAssessment();
                    break;
                case 420:
                    messageError(this, data.msg);
                    break;
                default:
                    break;
            }
        },
        /**
         * 取消修改
         */
        cancelEditAssessment() {
            // 关闭模态框
            this.editAssessmentModal = false;
            // 清除教学计划表
            this.clearAssessment();
        },

        /**
         * 清除课程考核的信息
         */
        clearAssessment() {
            this.assessment.id = '';
            this.assessment.assessName = '';
            this.assessment.assessRate = '';
            this.assessment.courseOutlineId = '';
            this.assessment.seq = '';
        },

        /**
         * 打开新增课程考核项模态框
         * @param data
         */
        openAddAssessitemModal(data) {
            let treeList = data.id.split("-");
            this.addAssessItemModal = true;
            this.assessItem.assessmentId = treeList[1];
        },

        /**
         * 自定义已选项
         * @param labels 当前选择的label的集合
         * @param selectedData 当前选择的数据集合
         */
        cascaderFormat(labels, selectedData) {
            console.log(labels, selectedData);
            // 选择的数据为第三层即二级指标点
            if (selectedData.length === 3) {
                // 获取第三层的id并分隔为集合，如[3,1,18,46]
                this.cascaderIndicatorIdList = selectedData[2].id.split("-");
            } else {
                this.cascaderIndicatorIdList = [];
            }
            return labels[2];
        },


        /**
         * 新增课程考核项
         */
        addAssessItem() {
            // 指标关联集合第三个数据即二级指标点
            this.assessItem.indicatorSecId = this.cascaderIndicatorIdList[3];
            // 验证数据
            if (checkEmpty(this.assessItem.content, '请输入考核项名称') ||
                checkEmpty(this.assessItem.maxScore, '请输入考核项满分') ||
                checkEmpty(this.assessItem.indicatorSecId, '请选择毕业达成度指标点(二级指标)')) {
                return;
            }
            let data = {
                assessmentId: this.assessItem.assessmentId,
                indicatorSecId: this.assessItem.indicatorSecId,
                content: this.assessItem.content,
                maxScore: this.assessItem.maxScore,
                remark: this.assessItem.remark,
            };
            let url = '/outline/assessItem/insert';
            callAjaxPost(url, data, this.addAssessItemSuc);
            this.loadingMsg = messageLoading();
        },
        addAssessItemSuc(data) {
            closeMessageLoading(this.loadingMsg);
            messageSuccess("新增课程考核项成功");
            this.getCourseOutlineTree();
            this.cancelAddAssessItem();
        },

        /**
         * 取消新增课程考核项
         */
        cancelAddAssessItem() {
            this.addAssessItemModal = false;
            this.assessItem.id = '';
            this.assessItem.assessmentId = '';
            this.assessItem.indicatorSecId = '';
            this.assessItem.content = '';
            this.assessItem.maxScore = '';
            this.assessItem.remark = '';
        },

        /**
         * 清除课程大纲搜索条件的信息
         */
        clearSCourseOutline() {
            this.sCourseOutline.outlineNo = '';
            this.sCourseOutline.courseOutlineTypeId = '';
            this.sCourseOutline.courseInfoId = '';
        },

        /**
         * 打开编辑课程考核项模态框
         * @param data
         */
        openEditAssessItemModal(data) {
            let idList = data.id.split("-");
            console.log(this.relatCascader);
            this.indicatorCascader = 'test';


            this.assessItem.id = idList[2];
            this.assessItem.assessmentId = data.assessmentId;
            this.assessItem.indicatorSecId = data.indicatorSecId;
            this.assessItem.content = data.content;
            this.assessItem.maxScore = data.maxScore;
            this.assessItem.remark = data.remark;
            // 打开课程考核模态框
            this.editAssessItemModal = true;
        },
        //修改课程考核
        editAssessItem() {
            let data = {
                id: this.assessItem.id,
                assessmentId: this.assessItem.assessmentId,
                indicatorSecId: this.assessItem.indicatorSecId,
                content: this.assessItem.content,
                maxScore: this.assessItem.maxScore,
                remark: this.assessItem.remark,
            };
            let url = '/outline/assessItem/update';
            callAjaxPost(url, data, this.editAssessItemSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editAssessItemSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            // 关闭模态框
            this.editAssessItemModal = false;
            messageSuccess("课程考核修改成功");
            // 重新查询数据
            this.getCourseOutlineTree();
            // 清除教学计划表
            this.clearEditAssessItem();
        },
        /**
         * 取消修改
         */
        cancelEditAssessItem() {
            // 关闭模态框
            this.editAssessItemModal = false;
            // 清除教学计划表
            this.clearEditAssessItem();
        },

        /**
         * 清除课程大纲的信息
         */
        clearEditAssessItem() {
            this.assessItem.id = '';
            this.assessItem.assessmentId = '';
            this.assessItem.indicatorSecId = '';
            this.assessItem.content = '';
            this.assessItem.maxScore = '';
            this.assessItem.remark = '';
        },

        /**
         * 删除课程大纲
         *
         * @param data
         */
        openRemoveCourseOutlineModal(data) {
            this.courseOutline.id = data.id;
            this.removeCourseOutlineModal = true;
        },
        removeCourseOutline() {
            this.removeCourseOutlineModal = false;
            let data = this.courseOutline.id;
            let url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeCourseOutlineSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        removeCourseOutlineSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess('课程大纲删除成功');
            this.courseOutlineTree = [];
        },

        /**
         * 删除课程考核
         *
         * @param data
         */
        openRemoveAssessmentModal(data) {
            let idList = data.id.split("-");
            console.log(idList[1]);
            this.assessment.id = idList[1];
            this.removeAssessmentModal = true;
        },
        removeAssessment() {
            this.removeAssessmentModal = false;
            let data = this.assessment.id;
            let url = '/outline/assessment/delete';
            callAjaxPost(url, data, this.removeAssessmentSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        removeAssessmentSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess('课程考核删除成功');
            // 重新查询数据
            this.getCourseOutlineTree();
        },

        /**
         * 跳转展示pdf的窗口
         * @param data
         */
        showCourseOutlineFile(data) {
            //公钥
            let PUBLIC_KEY = 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC8HMr2CBpoZPm3t9tCVlrKtTmI4jNJc7/HhxjIEiDjC8czP4PV+44LjXvLYcSV0fwi6nE4LH2c5PBPEnPfqp0g8TZeX+bYGvd70cXee9d8wHgBqi4k0J0X33c0ZnW7JruftPyvJo9OelYSofBXQTcwI+3uIl/YvrgQRv6A5mW01QIDAQAB';
            //使用公钥加密 
            let encrypt = new JSEncrypt();
            encrypt.setPublicKey('-----BEGIN PUBLIC KEY-----' + PUBLIC_KEY + '-----END PUBLIC KEY-----');
            let encrypted = encrypt.encrypt(data.accessUrl);
            window.open(METHOD_URL + '/test/show_PDF?accessUrl=' + encrypted);
        },

        /**
         * 删除课程考核项
         *
         * @param data
         */
        openRemoveAssessItemModal(data) {
            let idList = data.id.split("-");
            console.log(idList[2]);
            this.assessItem.id = idList[2];
            this.removeAssessItemModal = true;
        },
        removeAssessItem() {
            this.removeAssessItemModal = false;
            let data = this.assessItem.id;
            let url = '/outline/assessItem/delete';
            callAjaxPost(url, data, this.removeAssessItemSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        removeAssessItemSuc() {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess('课程考核项删除成功');
            // 重新查询数据
            this.getCourseOutlineTree();
        },

    }
});