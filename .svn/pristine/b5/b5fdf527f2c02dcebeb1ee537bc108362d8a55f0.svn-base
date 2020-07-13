var vMenuList = new Vue({
    el: '#MenuList',
    data: function () {
        return {
            firstPath: '/account/menu',// 请求一级路径
            nowData: [], column: [
            	{title:"菜单标题",key:"title",width:150},
            	{title:"菜单名",key:"name"},
            	{title:"菜单路径",key:"path"},
            	{title:"父级菜单",key:"parentTitle",width:200},
            	{title:"菜单排序序号",key:"seq",width:150},
            	{title:"启用状态",key:"enableString",width:150}
            ], // 表头信息
            loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            menuList: {
                id: '', title: '', name: '', path: '', parentId: '0', seq: '', enable: '',
            },// 实体类
            cloneMenuList:{
            	id: '', title: '', name: '', path: '', parentId: '0', seq: '', enable: '',
            },// 克隆实体类
            sMenuList: {
                id: '', title: '', name: '', path: '', parentId: '', seq: '', enableString: '皆可',// 详细搜索状态项
            },// 详细搜索信息
            fTitle: '',// 模糊搜索标题
            fatherMenuList: [],// 下拉框项
            addMenuListModal: false,// 新增菜单模态框
            editMenuListModal: false,// 编辑菜单模态框
            removeMenuListModal: false,// 删除菜单模态框
            removeMenuListSelectModal: false,// 批量删除菜单模态框
            detailSearchModal: false,// 详细查询模态框
            searchMode:'',//搜索模式
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
		 * 搜索父级菜单项
		 */
        selectFatherMenuList() {
            let url = this.firstPath + '/selectFatherMenuList';
            callAjaxGetNoParam(url, this.selectFatherMenuListSuc);
        },

        /**
		 * 搜索父级菜单项返回函数
		 */
        selectFatherMenuListSuc(data) {
            this.fatherMenuList = data.obj;
        },
        /**
		 * 打开详细查找模态框
		 */
        opendetailSearchModal() {
            this.clearsMenuList();
            // 打开模态框
            this.detailSearchModal = true;
        },
        /*
		 * 详细查找函数
		 */
        detailSearch() {
            this.detailSearchModal = false;
            if (this.sMenuList.parentId.length == 0) {
                this.sMenuList.parentId = -1;
            }
            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                title: this.sMenuList.title,
                parentId: this.sMenuList.parentId,
                enableString: this.sMenuList.enableString,
            };
            let url = this.firstPath + '/detail';
            callAjaxPost(url, data, this.detailSearchSuc);
            // 打开加载提示
            this.loading = true;
        },
        /*
		 * 详细查找返回函数
		 */
        detailSearchSuc(data) {
            // 取消显示加载
            this.loading = false;
            this.nowData = data.obj.list;
            this.searchMode='详细';
            // 判断是启用还是禁用
            for (let i = 0; i < this.nowData.length; i++) {
                if (this.nowData[i].enable) {
                	this.nowData[i].enableString = '启用';
                } else {
                    this.nowData[i].enableString = "禁用";
                }
            }
            this.totalNum = data.obj.total;
            // 再次设置当前页码
            this.pageNum = data.obj.pageNum === 0 ? 1 : data.obj.pageNum;
        },

        canceldetailSearchModal() {
            // 关闭模态框
            this.detailSearchModal = false;
            // 清除菜单
            this.clearMenuList();
        },

        openaddMenuListModal() {
            this.clearMenuList();
            // 打开模态框
            this.addMenuListModal = true;
        },
        /**
		 * 页面初始化加载项 表格表头
		 */
        initPage() {
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
            this.selectFatherMenuList();
            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                title: this.fTitle,
            };
            let url = this.firstPath + '/filter';
            callAjaxPost(url, data, this.filterSuc);
            // 显示加载
            this.loading = true;
        },
        /**
		 * 表格倒序过滤查询
		 */
        filterDesc() {
            console.log('当前页：' + this.pageNum);
            console.log('页面大小：' + this.pageSize);
            this.selectFatherMenuList();
            this.clearsMenuList();
            let data = {
                pageNum: 1,
                pageSize: this.pageSize,
                title: this.fTitle,
            };
            let url = this.firstPath + '/filterDesc';
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
            console.log(this.nowData);
            this.searchMode='模糊';
            // 判断是启用还是禁用
            for (let i = 0; i < this.nowData.length; i++) {
                if (this.nowData[i].enable) {
                	this.nowData[i].enableString = '启用';
                } else {
                    this.nowData[i].enableString = "禁用";
                }
            }
            console.log(this.nowData);
            this.totalNum = data.obj.total;
            // 再次设置当前页码
            this.pageNum = data.obj.pageNum === 0 ? 1 : data.obj.pageNum;
        },

        /**
		 * 清除搜索条件
		 */
        clearSMenuList() {
            this.clearsMenuList();
            this.filter();
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
		 * 添加前检查合法性
		 * 
		 */
        addCheck() {
            if (checkEmpty(this.menuList.title, '菜单标题不能为空！') ||checkLength(this.menuList.title,'32','菜单标题不能超过32位')||
                checkEmpty(this.menuList.name, '菜单名不能为空！')||checkLength(this.menuList.name,'32','菜单名不能超过32位')||
                checkEmpty(this.menuList.parentId,'请选择父级菜单!')||checkLength(this.menuList.path,'32','菜单路径不能超过32位')
            ) {
                return;
            }
            if(this.checkSeqLegal(this.menuList.parentId,this.menuList.seq,null)){
            	return;
            }
            let data = {
                title: this.menuList.title,
                path: this.menuList.path,
            }
            let url = this.firstPath + '/addCheckSame';
            callAjaxPost(url, data, this.addCheckSameSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        addCheckSameSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            if (data.obj === "title_path_exist") {
                messageWarning("已存在相同的菜单项标题和路径!")
                return;
            }
            else if (data.obj === "title_exist") {
            	messageWarning("已存在相同的菜单项标题!")
                return;
            }
            else if (data.obj === "path_exist") {
            	messageWarning("已存在相同的菜单项路径!")
                return;
            }
            else{
                this.addMenuList();
            }
        },

        /**
		 * 新增菜单项信息
		 */
        addMenuList() {
            // 关闭模态框
            this.addMenuListModal = false;
            let data = {
                title: this.menuList.title,
                name: this.menuList.name,
                path: this.menuList.path,
                parentId: this.menuList.parentId,
                seq: this.menuList.seq,
                enable: this.menuList.enable,
            };
            let url = this.firstPath + '/insert';
            callAjaxPost(url, data, this.addMenuListSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        /**
		 * 新增菜单回调函数
		 * 
		 * @param data
		 *            请求返回参数
		 */
        addMenuListSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess('新增菜单项成功！');
            // 重新查询数据
            if(this.searchMode=='模糊'){
            this.filterDesc();
            }else if(this.searchMode=='详细'){
            this.detailSearch();
            }    
            // 清除数据
            this.clearMenuList();
        },
        /**
		 * 取消新增课程
		 */
        cancelAddMenuList() {
            // 关闭模态框
            this.addMenuListModal = false;
            // 清除菜单
            this.clearMenuList();
        },

        /**
		 * 打开编辑菜单模态框
		 * 
		 * @param index
		 *            当前数据索引
		 */
        openEditMenuListModal(index) {
            console.log(this.nowData[index]);
            this.menuList.id =this.cloneMenuList.id= this.nowData[index].id;
            this.menuList.title =this.cloneMenuList.title= this.nowData[index].title;
            this.menuList.name =this.cloneMenuList.name= this.nowData[index].name;
            this.menuList.path =this.cloneMenuList.path= this.nowData[index].path;
            this.menuList.parentId = this.cloneMenuList.parentId=this.nowData[index].parentId;
            this.menuList.seq =this.cloneMenuList.seq= this.nowData[index].seq;
            this.menuList.enable = this.cloneMenuList.enable=this.nowData[index].enable;
            // 打开模态框
            this.editMenuListModal = true;
        },

        /**
		 * 编辑前重复检查
		 * 
		 */
        editCheck() {
            if (this.menuList.title===this.cloneMenuList.title&&this.menuList.parentId===this.cloneMenuList.parentId&&this.menuList.seq===this.cloneMenuList.seq&&this.menuList.enable===this.cloneMenuList.enable) {
            	messageWarning('您未做任何修改!');
                return;
            }
            if (checkEmpty(this.menuList.title, '菜单项标题不能为空！')||checkLength(this.menuList.title,'32','菜单标题不能超过32位')) {
                return;
            }
            if(this.checkSeqLegal(this.menuList.parentId,this.menuList.seq,this.menuList.id)){
            	return;
            }
            let data = {
                id: this.menuList.id,
                title: this.menuList.title,
            }
            this.editMenuList();
        },

        /**
		 * 修改菜单项信息
		 */
        editMenuList() {
            // 关闭模态框
            this.editMenuListModal = false;

            let data = {
                id: this.menuList.id,
                title: this.menuList.title,
                parentId: this.menuList.parentId,
                seq: this.menuList.seq,
                enable: this.menuList.enable,
            };
            let url = this.firstPath + '/update';
            callAjaxPost(url, data, this.editMenuListSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        editMenuListSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess('修改菜单项成功！');
            // 重新查询数据
            if(this.searchMode=='模糊'){
                this.filter();
                }else if(this.searchMode=='详细'){
                this.detailSearch();
                }   
            // 清除菜单项信息
            this.clearMenuList();
        },

        /**
		 * 取消修改菜单项信息
		 */
        cancelEditMenuList() {
            // 关闭模态框
            this.editMenuListModal = false;
            // 清除菜单项信息
            this.clearMenuList();
        },

        /**
		 * 清除菜单项信息
		 */
        clearMenuList() {
            this.menuList.title = '';
            this.menuList.name = '';
            this.menuList.path = '';
            this.menuList.parentId = '';
            this.menuList.seq = '';
            this.menuList.enable = '';
        },

        /**
		 * 清除菜单项查找信息
		 */
        clearsMenuList() {
            this.sMenuList.title = '';
            this.sMenuList.name = '';
            this.sMenuList.path = '';
            this.sMenuList.parentId = '';
            this.sMenuList.seq = '';
            this.sMenuList.enable = '';
            this.sMenuList.enableString = '皆可';
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
		 * 打开删除菜单模态框
		 */
        openRemoveMenuListSelectModal() {
            // 判断当前多选是否勾选
            if (this.selection.length === 0) {
                messageWarning('请先勾选数据，再批量禁用');
                return;
            }
            // 打开模态框
            this.removeMenuListSelectModal = true;

        },

        /**
		 * 批量删除数据
		 */
        removeMenuListSelect() {
            // 关闭模态框
            this.removeMenuListSelectModal = false;
            let TitleList = [];
            for (let i = 0; i < this.selection.length; i++) {
                TitleList[i] = this.selection[i].title;
            }
            console.log(TitleList);
            let data = TitleList;
            let url = this.firstPath + '/deleteSelection';
            callAjaxPost(url, data, this.removeMenuListSelectSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },

        /**
		 * 批量删除数据成功回调函数
		 */
        removeMenuListSelectSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess('批量删除成功！');
            // 清除多选列表
            this.selection = [];
            // 加载表格数据
            this.filter();

        },


        /**
		 * 打开删除菜单项信息模态框
		 * 
		 * @param index
		 *            当前数据索引
		 */
        openRemoveMenuListModal(index) {
            this.menuList.id = this.nowData[index].id;
            this.removeMenuListModal = true;
        },
        /**
		 * 删除菜单项信息
		 * 
		 * @param index
		 */
        removeMenuList(index) {
            this.removeMenuListModal = false;
            let data = this.menuList.id;
            let url = this.firstPath + '/delete';
            callAjaxPost(url, data, this.removeMenuListSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        /**
		 * 删除菜单项回调函数
		 */
        removeMenuListSuc(data) {
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess('菜单项删除成功！');

            // 重新查询数据
            this.filter();
        },
        /**
		 * 检查seq合法性
		 */
        checkSeqLegal(parentId,seq,id){
        	for(let i=0;i<this.nowData.length;i++){
        		if((this.nowData[i].parentId===parentId&&this.nowData[i].seq===seq)&&this.nowData[i].id!=id){
        			messageWarning('该父级菜单下已有相同的排序序号！');
        			return true;
        		}
        	}
        	return false;
        }

    }
});