let vScoreAssessItem = new Vue({
	el:'#scoreAssessItem',
	data:function(){
		let vm = this;
		return{
			firstPath: '/score/scoreAssessItem',// 请求一级路径
			nowData: [], column: [
				{title:"学生学号",key:"studentNumber",width:150},
				{title:"学生姓名",key:"studentName",width:150},
			], loading: true, selection: [],// 表格参数
			totalNum: 0, pageNum: 1, pageSize: 2,  // 分页参数
			loadingMsg: '',// 加载提示
			notice: '',// 提醒对象
			itemKey:[],//考核项关键词集合
			itemTitle:[],//考核项标题集合
			itemMaxScore:[],//考核项占比集合
			backUpData:[],//备份数据
			counter:0,//传输计次器
			counterSuc:0,//回调计次器
			assessmentId:'',//考核Id
			classId:'',//班级Id
			choiceCourseNoId:'',//选课号Id
			scoreMode:'',//计分模式
		}
	},
	 components: {
	        'layout-header': httpVueLoader('/layout/layout-header.vue'),
	        'layout-sider': httpVueLoader('/layout/layout-sider.vue'),
	        'layout-footer': httpVueLoader('/layout/layout-footer.vue')
	    },
	    mounted() {
	    	this.selectItem();
	    	this.getScore();
	    },
	    methods:{
	    	/*
			 * 获取考核项
			 */
	    	selectItem(){
	    		this.assessmentId=getQueryString('assessmentId');
	    		this.classId=getQueryString('classId');
	    		this.choiceCourseNoId=getQueryString('choiceCourseNoId');
	    		console.log(this.assessmentId);
/*	    		this.scoreMode=getQueryString('scoreMode');*/
	    		var url= this.firstPath+"/selectItemByAssessmentId";
	    		callAjaxPost(url,this.assessmentId,this.selectItemSuc);
	    	},
	    	/*
			 * 获取考核项回调函数
			 */
	    	selectItemSuc(data){
	    	console.log(data,"考核项回调");
	    	for(var i=0;i<data.obj.length;i++)
	    		{
	    		this.itemKey.push("item_"+data.obj[i].indicatorSecId);
	    		this.itemTitle.push(data.obj[i].content+"("+data.obj[i].maxScore+")");
	    		this.itemMaxScore.push(data.obj[i].maxScore);
	    		}
	    	this.column = showColMoreInputNumber(this, this.column,this.itemKey,this.itemTitle);
	    	},
	    	/*
			 * 获取成绩
			 */
	    	getScore(){
	    		var url=this.firstPath + "/getScore";
	    		let data={
	    				assessmentId:this.assessmentId,
	    				classId:this.classId,	
	    				choiceCourseNoId:this.choiceCourseNoId,
	    		};
	    		callAjaxPost(url,data,this.getScoreSuc);
	    	},
	    	/*
			 * 获取成绩回调函数
			 */
	    	getScoreSuc(data){
	    		// 取消显示加载
	            this.loading = false;
	    		console.log(data.obj,"成绩回调");
	    		this.nowData=data.obj;
	    		for(var i=0;i<this.nowData.length;i++)
            	{
            		for(var j=0;j<this.itemKey.length;j++)
            		{
            			this.nowData[i][this.itemKey[j]] = '';
            		}
            	}
	    		for(var i=0;i<data.obj.length;i++)
	    		{
	    			for(var j=0;j<data.obj.length;j++)
	    				{
	    					if(this.nowData[i].studentNumber==data.obj[j].studentNumber)
	    					{
	    						if(data.obj[j].scoreAssessItemList!=null){
		    						for(var g=0;g<data.obj[j].scoreAssessItemList.length;g++)
		    						{
		    							var str="item_"+data.obj[j].scoreAssessItemList[g].assessItemId;
		    							this.nowData[i][str] = data.obj[j].scoreAssessItemList[g].score;
		    						}
	    						}
	    					}
	    				}
	    		}
	    		this.backUpData=cloneObj(this.nowData);// 备份原成绩数据
	    	},
	    	/*
			 * 提交成绩
			 */
	    	sumbitScore(){
	        	var data=[];
	        	var scoreItem=[];
	        	for(var i=0;i<this.itemKey.length;i++)
	        	{
	        		data=[];
	        		score_item=[];
		         	for(var j=0;j<this.nowData.length;j++)
		         	{	
		         				if(this.backUpData!=null){
				         			if(this.nowData[j][this.itemKey[i]]!=this.backUpData[j][this.itemKey[i]])
				         			{
					         			var str=this.itemKey[i].split("_")[1];
					         			var itemKey=parseInt(str);
					         			scoreItem={
					         					    assessItemId:itemKey,
					         						scoreAssessmentId:this.nowData[j].scoreAssessmentId,
					         						score:this.nowData[j][this.itemKey[i]]
					         			}
					         			data.push(scoreItem);	
				         			} 
		         				}else{
					         			var str=this.itemKey[i].split("_")[1];
					         			var itemKey=parseInt(str);
					         			scoreItem={
					         					    assessItemId:itemKey,
					         						scoreAssessmentId:this.nowData[j].scoreAssessmentId,
					         						score:this.nowData[j][this.itemKey[i]]
					         			}
					         			data.push(scoreItem);	
		         				}
		         	}
		         	if(data.length>0){
		         		this.counter++;
			         	var url=this.firstPath+'/sumbitScore';
		    	    	callAjaxPost(url,data,this.sumbitScoreSuc);
		         	}
	        	}
	    	},
	    	
	    	sumbitScoreSuc(data){
	    		this.counterSuc++;
	    		if(this.counterSuc==this.counter){
	    			messageSuccess('提交完成！');
	    		}	    		
	    	},
	    	 /**
				 * 改变页码
				 * 
				 * @param pageNum
				 *            改变后的页码
				 */
	        onPageChange(pageNum) {
	            this.pageNum = pageNum;
	            this.selectStuByClassId();
	        },
	        /**
			 * 切换每页条数
			 * 
			 * @param pageSize
			 *            换后的每页条数
			 */
	        onPageSizeChange(pageSize) {
	            this.pageSize = pageSize;
	            this.selectStuByClassId();
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
	    }
	    	
	      
	  })

