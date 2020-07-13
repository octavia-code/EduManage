let vVideo = new Vue({
    el: '#videoManage',
    data: function () {
        return {
            firstPath: '/config/fileInfo',// 请求一级路径
            nowData: [], column: [], loading: true, selection: [],// 表格参数
            totalNum: 0, pageNum: 1, pageSize: 10,  // 分页参数
            loadingMsg: '',// 加载提示
            notice: '',// 提醒对象
            sFileInfo:{
            	fileName:'',
            	path:'',
            	type:'',
            },
            answerList:[],
            questionFiveList:[],
            videoUploadModal:false,
            textFileType: 'V',// 文件类型
            uploadUrl: UPLOAD_URL, // 上传地址
            textFormData: new FormData(), // 文本表单对象
            textFileList: [],// 文件列表
            videoPlayModal:false,//视频播放模态框
            videoPath:'',//视频路径
            stemTestModal: false,//章节测试模态框
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
            this.column = [
    			{title:'视频文件名',key:'fileName',width:900}
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
            let data = {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                fileName:this.sFileInfo.fileName
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
            console.log(this.nowData)
            this.totalNum = data.obj.total;
            // 再次设置当前页码,若查询记录为空，设为第一页
            this.pageNum = data.obj.pageNum === 0 ? 1 : data.obj.pageNum;
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
         * 提交文本表单
         */
        submitTextList() {
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
            callAjaxFile(this.uploadUrl, this.textFormData, this.uploadSuc);

        },
        
        uploadSuc(data) {
            messageSuccess('上传成功');
            this.filter();
        },
        
        /**
         * 文件状态改变时的钩子，添加文件、上传成功和上传失败时都会被调用
         * @param file 当前文件
         * @param fileList 文件列表
         */
        handleChangeText(file, fileList) {
            console.log(file)
            this.textFileList = fileList;

        },
        
        openVideoPlayModal(index){
        	var videoPath = this.nowData[index].accessUrl;
        	let vdo = document.getElementById("playVideo");
        	vdo.src = videoPath;
        	this.videoPlayModal = true;
        },
        
        openStemTestModal(index){
        	this.answerList = []
        	this.selectFiveQuestion();
        	this.stemTestModal=true;
        },
        
        selectFiveQuestion(){
        	let url = '/info/questionBank/selectFiveQuestion'
        	callAjaxGetNoParam(url,this.selectFiveQuestionSuc);
        },
        
        selectFiveQuestionSuc(data){
        	console.log(data.obj)
        	this.questionFiveList = data.obj
        },
        
        submitAnswer(){
        	console.log(this.answerList)
        	let score = 0
        	for(let i = 0;i<this.answerList.length;i++){
        		if(this.questionFiveList[i].answer==this.answerList[i]){
        			score = score+3
        		}
        	}
        	messageSuccess("您的得分是"+score+"分")
        	let data = {
                	lineup: score.toString() 
                };
        	let url = '/info/achieveDegree/update'
        	callAjaxPost(url,data,this.submitAnswerSuc)
        },
        
        submitAnswerSuc(data){
        	messageSuccess("分数写入完成")
        }
        
    }
});

