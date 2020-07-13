var Tid;
var TtypeName;
var vCourseType = new Vue({
    el: '#courseType',
    data: function () {
        return {
            firstPath: '/edu/courseType',// 请求一级路径
            nowData: [], column: [], loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            courseType: {
                id: '', typeName: '', createdDate: '', state:'', stateDate:''
            },// 实体类
            sCourseType: {
                typeName: ''
            },// 搜索信息
            addCourseTypeModal: false,// 新增课程类别模态框
            editCourseTypeModal: false,// 编辑课程类别模态框
            removeCourseTypeModal: false,// 删除课程类别模态框
            removeCourseTypeSelectModal: false,// 批量删除课程类别模态框
            idcounter:0,namecounter:0,
            
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
    	
    	
    	/*
    	 * 检查ID和名称是否为空
    	 */
    	Check(i){
        if(i=='名称')
    			{
                if(this.courseType.typeName=='')
                {
    			this.namecounter=1;
            	this.$Message.config({
            	    duration: 3
            	});
            	this.$Message.error({
                     content: i+'不能为空',
                     duration: 2
                 });
                }
                else{
                	this.namecounter=0;
                }
    			};
    	
    	
    	},
    	
    	/*
    	 * 编辑课程类别内容检查
    	 */
    	checkUpdate()
    	{
    		if(this.courseType.typeName=="")
    			{
    			this.namecounter=1;
            	this.$Message.config({
            	     duration: 3
            	});
            	this.$Message.error({
                     content: '您有未填写项',
                     duration: 2
                 });
    			}
    		else if(this.courseType.typeName!=""){
    			if(this.courseType.typeName==TtypeName)
    				{
                	this.$Message.config({
                	     duration: 3
                	});
                	this.$Message.warning({
                         content: '您未作任何修改',
                         duration: 2
                     });
    				}
    			else{
    					var data = {
    							id:Tid,
    							typeName: this.courseType.typeName,
    					};
                        var url = this.firstPath + '/checkUpdate';
                        callAjaxPost(url, data, this.checkUpdateSuc);
                        // 显示加载
                        this.loading = true;
    				}
    		}
    	},
    	
    	/*
    	 * 编辑课程类别内容检查返回函数
    	 */
    	checkUpdateSuc(data)
    	{
    		// 取消显示加载
            this.loading = false;

            if(data.obj.info=="none")
            {
            	 this.editCourseTypeModal = false;
                 var data = { 
                     id:Tid,
                     typeName: this.courseType.typeName,
                 };
                 var url = this.firstPath + '/update';
                 callAjaxPost(url, data, this.editCourseTypeSuc);
                 // 打开加载提示
                 this.loadingMsg = messageLoading();
            }
            else if(data.obj.info=="same")
            {
            var information="已存在相同的"
            	if(data.obj.name=="same")
            	{
            	information+="类别名称";
            	namecounter=1;
            	}
            messageError(this, information);
            }
    	},
    	
    	/*
    	 * 新增课程类别内容检查
    	 */
    	checkInsert()
    	{
    		if(this.courseType.typeName=="")
    			{
    			this.namecounter=1;
            	this.$Message.config({
            	     duration: 3
            	});
            	this.$Message.error({
                     content: '您有未填写项',
                     duration: 2
                 });
    			}
    		else if(this.courseType.typeName!=""){

    		var data = {
                    typeName: this.courseType.typeName,
                };
                var url = this.firstPath + '/checkInsert';
                callAjaxPost(url, data, this.checkInsertSuc);
                // 显示加载
                this.loading = true;

    		}
    	},
    	
    	/*
    	 * 新增课程类别内容检查返回函数
    	 */
    	checkInsertSuc(data)
    	{
    		// 取消显示加载
            this.loading = false;

            if(data.obj.info=="none")//没有相同的内容，执行添加操作
            {
                this.addCourseTypeModal = false;
                var data = {
                    typeName: this.courseType.typeName,
                };
                var url = this.firstPath + '/insert';
                callAjaxPost(url, data, this.addCourseTypeSuc);
                // 打开加载提示
                this.loadingMsg = messageLoading();
            }
            else if(data.obj.info=="same")//存在相同的内容
            {
            var information="已存在相同的"
            	if(data.obj.name=="same")
            	{
            	information+="类别名称";
            	namecounter=1;
            	}
            messageError(this, information);
            }
    	},
        /**
         * 页面初始化加载项
         * 表格表头
         */
        initPage() {
    		// 生成表头
            this.column = [
            	{title: '类别名称', key: 'typeName'}
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
                typeName: this.sCourseType.typeName,
            };

            var url = this.firstPath + '/listByCourseType';
            callAjaxPost(url, data, this.filterSuc);
            // 显示加载
            this.loading = true;

        },
        
        Desc_filter() {
            console.log('当前页：' + this.pageNum);
            console.log('页面大小：' + this.pageSize);
            var data = {
                pageNum: 1,
                pageSize: this.pageSize,
                typeName: this.sCourseType.typeName,
            };

            var url = this.firstPath + '/listByCourseTypeDESC';
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
            this.pageNum = data.obj.pageNum === 0 ?1 : data.obj.pageNum;
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
        clearSCourseType() {
            this.sCourseType.typeName = '';
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
        
        
        /*
         * 打开新增课程类别模态框
         */
        OpenAddCourseTypeModal() {
            // 清除课程类别
            this.clearCourseType();
            // 打开模态框
            this.addCourseTypeModal = true;
        },

        /**
         * 新增课程类别回调函数
         * @param data 请求返回参数
         */
        addCourseTypeSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '新增课程类别成功！');
            // 重新查询数据
            this.Desc_filter();
            // 清除数据
            this.clearCourseType();
        },
        /**
         * 取消新增课程类别
         */
        cancelAddCourseType() {
            // 关闭模态框
            this.addCourseTypeModal = false;
            // 清除课程类别
            this.clearCourseType();
        },

        /**
         * 打开编辑课程类别模态框
         * @param index 当前数据索引
         */
        openEditCourseTypeModal(index) {
        	this.clearCourseType();
            console.log(this.nowData[index]);
            this.courseType.id = this.nowData[index].id;
            this.courseType.typeName = this.nowData[index].typeName;
            Tid=this.nowData[index].id;
            TtypeName=this.nowData[index].typeName;
            // 打开模态框
            this.editCourseTypeModal = true;
        },
        
        

        editCourseTypeSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '修改课程类别成功！');
            // 重新查询数据
            this.filter();
            // 清除课程类别
            this.clearCourseType();
        },
        

        /**
         * 取消修改课程类别
         */
        cancelEditCourseType() {
            // 关闭模态框
            this.editCourseTypeModal = false;
            // 清除课程类别
            this.clearCourseType();
        },
        /**
         * 清除课程类别
         */
        clearCourseType() {
            this.courseType.id = '';
            this.courseType.typeName = '';
            this.courseType.state = '';
            this.courseType.createdDate = '';
            this.courseType.stateDate = '';
            this.idcounter = 0;
            this.namecounter = 0;
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
         * 打开删除课程类别模态框
         */
        openRemoveCourseTypeSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning(this, '请先勾选数据，再批量删除');
                return;
            }
            // 打开模态框
            this.removeCourseTypeSelectModal = true;

        },

        /**
         * 批量删除数据
         */
        removeCourseTypeSelect() {
            // 关闭模态框
            this.removeCourseTypeSelectModal = false;
            var idList = [];
            for (var i = 0; i < this.selection.length; i++) {
                idList[i] = this.selection[i].id;
            }
            console.log(idList);
            var data = idList;
            var url = this.firstPath + '/deleteSelection';
            callAjaxPost(url, data, this.removeCourseTypeSelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        /**
         * 批量删除数据成功回调函数
         */
        removeCourseTypeSelectSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '批量删除成功！');
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();
        },


        /**
         * 打开删除课程类别模态框
         * @param index 当前数据索引
         */
        openRemoveCourseTypeModal(index) {
            this.courseType.id = this.nowData[index].id;
            this.removeCourseTypeModal = true;
        },
        /**
         * 删除课程类别
         * @param index
         */
        removeCourseType(index) {
            this.removeCourseTypeModal = false;
            var data = this.courseType.id;
            var url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeCourseTypeSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        removeCourseTypeSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '课程类别删除成功！');
            // 重新查询数据
            this.filter();
        },
        

    }
});