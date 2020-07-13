let editor = UE.getEditor('editor');
let vSendMail = new Vue({
	el:'#sendMail',
	data:function(){
		let vm = this;
		return{
			firstPath: '/test/sendMail',// 请求一级路径
			nowData: [], column: [], loading: true, selection: [],// 表格参数
			totalNum: 0, pageNum: 1, pageSize: 2,  // 分页参数
			loadingMsg: '',// 加载提示
			notice: '',// 提醒对象
			nowInputRecipient:'',//现在正在输入的收件人
			recipientBox:[],//邮箱收件人
			recipientCounter:0,//收件人计数器
			mailTitle:'',//邮箱标题
			sendLoading:false,
		}
	},
	 components: {
	        'layout-header': httpVueLoader('/layout/layout-header.vue'),
	        'layout-sider': httpVueLoader('/layout/layout-sider.vue'),
	        'layout-footer': httpVueLoader('/layout/layout-footer.vue')
	    },
	  methods:{
		  /**
		   * 失去焦点后添加收件人
		   */
		  addRecipient(){
			  if(!isEmpty(this.nowInputRecipient)){
				  this.recipientBox.push(this.nowInputRecipient);
				  this.recipientCounter++;
				  this.nowInputRecipient="";
				  console.log("收件人数目：",this.recipientCounter);
				  console.log("收件人盒子",this.recipientBox);
			  }
		  },
		  /**
		   * 监听按键添加收件人
		   */
		  addRecipientWithKey(event){
			  	//监听到";"自动添加收件人
			  if(event.keyCode==186){
				  //去除收件地址末尾的“;”
				  this.nowInputRecipient= this.nowInputRecipient.substr(0,this.nowInputRecipient.length-1); 
				  this.recipientBox.push(this.nowInputRecipient);
				  this.recipientCounter++;
				  this.nowInputRecipient="";
				  console.log("收件人数目:",this.recipientCounter);
				  console.log("收件人盒子:",this.recipientBox);
				//监听到"回车"自动添加收件人
			  }else if(event.keyCode==13){
				  this.recipientBox.push(this.nowInputRecipient);
				  this.recipientCounter++;
				  this.nowInputRecipient="";
				  console.log("收件人数目:",this.recipientCounter);
				  console.log("收件人盒子:",this.recipientBox);
			  };
		  },
		  /**
		   * 移除收件人
		   */
		  deleteRecipient(index){
			  console.log("你要删除的索引:"+index);
			  console.log("收件人:"+this.recipientBox.splice(index,1)+"已删除！");
			  this.recipientCounter--;
		  },
		  /**
		   * 发送邮件
		   */
		  sendMail(){
			  //检查收件人是否为空
			  if(this.recipientCounter==0){
				  messageWarning('请填写收件人');
				  return;
			  //检查邮件标题是否为空
			  }else if(isEmpty(this.mailTitle)){
				  messageWarning('请填写邮件标题');
				  return;
			  }
			  let url="/sendMail/send";
			  this.sendLoading=true;
			  let mailInfo={
					  recipientBox:this.recipientBox,
					  mailTitle:this.mailTitle,
					  mailContent:editor.getContent(),
			  }
			  callAjaxPost(url,mailInfo,this.sendMailSuc);
		  },
		  /**
		   * 发送邮件回调函数
		   */
		  sendMailSuc(data){
			  messageSuccess('发送成功！');
			  this.sendLoading=false;
		  },
	  }
	 
})

