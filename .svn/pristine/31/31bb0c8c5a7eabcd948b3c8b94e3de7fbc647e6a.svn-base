let vSpecialty = new Vue({
    el: '#specialty',
    data: function () {
        return {
            firstPath: '/manage/specialty',// 请求一级路径
            nowData: [], column: [], loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            specialty: {
                id: '', subjectName: '', remark: '', state: '',schoolInfoId: '',schoolName: ''
            },// 实体类
            sSpecialty: {
                subjectName: ''
            },// 搜索信息
            specialtyTemp: '',// 修改临时存放信息
            addSpecialtyModal: false,// 新增专业信息模态框
            editSpecialtyModal: false,// 编辑专业信息模态框
            removeSpecialtyModal: false,// 删除专业信息模态框
            removeSpecialtySelectModal: false,// 批量删除专业信息模态框
            
            schoolInfoList:[]//学院信息类
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
        this.getSchoolInfoList();
    },
    methods: {
        /**
		 * 页面初始化加载项 表格表头
		 */
        initPage() {
        	this.column = [
              	{title: '专业名称', key: 'subjectName', width: 250},
                {title: '学院名称', key: 'schoolName', width: 250},
                {title: '备注', key: 'remark', width: 300}
            ];
              // 添加自定义slot-scope
              this.column.push(headActionSlot());
              // 添加序号
              this.column.unshift(headIndex());
              // 添加多选
              this.column.unshift(headSelection());
        },

        /**
         * 获取学院信息集合
         * 
         */
        getSchoolInfoList(){
        	let url = '/info/schoolInfo/selectSchoolInfoList';
        	callAjaxGetNoParam(url,this.getSchoolInfoListSuc);
        },
		
		getSchoolInfoListSuc(data){
			this.schoolInfoList=data.obj;
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
                subjectName: this.sSpecialty.subjectName
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
        clearSpecialty2() {
            this.sSpecialty.subjectName = '';
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
         * 检查专业信息数据格式
         * @return {boolean} 若数据格式错误,返回true
         */
        checkSpecialty() {
            if (checkEmpty(this.specialty.subjectName, '专业名称不能为空') ||
                checkLength(this.specialty.subjectName, '20', '专业名称不能超过20个字符')||
                checkEmpty(this.specialty.schoolName,'学院名称不能为空')) {
                return true;
            }
        },
        
        /**
		 * 新增专业信息
		 */
        addSpecialty() {
        	// 检查数据格式
            if (this.checkSpecialty()) {
                return;
            }

            // 发送请求
            let data = {             
                subjectName: this.specialty.subjectName,                
                remark: this.specialty.remark,
                schoolInfoId:this.specialty.schoolInfoId
            };
            let url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addSpecialtySuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        /**
		 * 新增专业信息回调函数
		 * 
		 * @param data
		 *            请求返回参数
		 */
        addSpecialtySuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            if(data.obj==='exist'){
            	messageWarning("记录已存在");
            	return;
            }
            // 关闭模态框
            this.addSpecialtyModal = false;
            messageSuccess( "新增专业信息成功");
            // 重新查询数据
            this.filter();
            // 清除专业信息
            this.clearSpecialty();
    },
        /**
		 * 取消新增专业
		 */
        cancelAddSpecialty() {
            // 关闭模态框
            this.addSpecialtyModal = false;
            // 清除专业信息
            this.clearSpecialty();
        },

        /**
		 * 打开编辑专业信息模态框
		 * 
		 * @param index
		 *            当前数据索引
		 */
        openEditSpecialtyModal(index) {
        	this.editSpecialtyModal = true;
            this.specialty.id = this.nowData[index].id;
            this.specialty.subjectName = this.nowData[index].subjectName;
            this.specialty.remark = this.nowData[index].remark;
            this.specialty.schoolInfoId = this.nowData[index].schoolInfoId;
        },
        /**
		 * 修改专业信息
		 */
        editSpecialty() {
        	// 检查数据格式
            if (this.checkSpecialty()) {
                return;
            }
            // 发送请求
            let data = {
                subjectName:this.specialty.subjectName,
            	remark: this.specialty.remark,
                schoolInfoId:this.specialty.schoolInfoId
            };
            let url = this.firstPath + '/update';
            callAjaxPost(url, data, this.editSpecialtySuc);

            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        editSpecialtySuc(data) {
        	// 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            // 关闭模态框
            this.editSpecialtyModal = false;
            messageSuccess( "专业信息修改成功");
            // 重新查询数据
            this.filter();
            // 清除专业信息
            this.clearSpecialty();
        },
        /**
		 * 取消修改专业信息
		 */
        cancelEditSpecialty() {
            // 关闭模态框
            this.editSpecialtyModal = false;
            // 清除专业信息
            this.clearSpecialty();
        },
        /**
		 * 清除专业信息
		 */
        clearSpecialty() {
            this.specialty.id = ''; 
            this.specialty.subjectName = '';          
            this.specialty.remark = '';
            this.specialty.schoolInfoId = '';
           
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
		 * 打开删除专业信息模态框
		 */
        openRemoveSpecialtySelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning(this, '请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeSpecialtySelectModal = true;
        },

        /**
		 * 批量删除数据
		 */
        removeSpecialtySelect() {
            // 关闭模态框
            this.removeSpecialtySelectModal = false;
            let idList = [];
            for (let i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            console.log(idList);
            let data = idList;
            let url = this.firstPath + '/deleteSelection';
            callAjaxPost(url, data, this.removeSpecialtySelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        /**
		 * 批量删除数据成功回调函数
		 */
        removeSpecialtySelectSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '成功删除'+ data.obj +'条记录！');
            
             // 清除多选列表
             this.selection = [];
             // 加载表格数据
             this.filter();
        },


        /**
         * 打开删除专业信息模态框
         * @param index 当前数据索引
         */
        openRemoveSpecialtyModal(index) {
            this.specialty.id = this.nowData[index].id;
            this.removeSpecialtyModal = true;
        },

        /**
		 * 删除专业信息
		 * 
		 * @param index
		 */
        removeSpecialty(index) {
            this.removeSpecialtyModal = false;
            let data = this.specialty.id;
            let url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeSpecialtySuc);

            // 表单方式提交
            // let url = this.firstPath + '/delete2';
            // callAjaxPostForm(url, data, this.removeCourseInfoSuc);

            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        removeSpecialtySuc(data) {
        	// 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '专业信息删除成功！');
            
            // 重新查询数据
            this.filter();
        },
        /*
        exportSpecialty(){
        	let url = this.firstPath + '/excelExport';
        	window.location.href=url;
        }
*/

    }
});
