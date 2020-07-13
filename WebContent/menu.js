var vMenu = new Vue({
    el: '#people',   	  
    data: function () {
        return {
            firstPath: '/main/menu',
            menu: {
                id: '', url: '', path: '', name: '',
                parentId: '', sort: '', isEnable: ''
            },// 菜单类
            menuList: [],// 菜单列表
            activeName: '',// 激活菜单的 name 值
            openMenu: [],// 展开的 Submenu 的 name 集合
            realTime:'',
            html:''
        }
    },
    mounted() {
    	//iframe自适应大小
    	function reinitIframe(){
            var iframe = document.getElementById("index-iframe");
            try{
                var bHeight = iframe.contentWindow.document.body.scrollHeight;
                var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
                var height = Math.min(bHeight, dHeight);
                iframe.height = height;
                /*console.log(iframe.contentWindow);
                console.log(bHeight,dHeight);*/
	            }catch (ex){}
	        };
		setInterval(reinitIframe,100);

		
		
	  console.log("2222");
      this.selectMenuList();
        // 页面加载完后显示当前时间
	  this.realTime = this.dealWithTime(new Date())
	  // 定时刷新时间
	  let _this = this
	  // 定时器
	  this.timer = setInterval(function () {
	    _this.realTime = _this.dealWithTime(new Date()) // 修改数据date
	  }, 1000)

    },
    methods: {
 
        /**
         * 查询菜单列表
         */
        selectMenuList() {
            let url = methodUrl + this.firstPath + '/selectMenuList';
            callAjaxGetNoParam(url, this.selectMenuListSuc);
        },
        
        /*const router = new VueRouter({
        	  mode: 'history',
        	  routes: [...]
        	})*/

        /**
         * 查询菜单列表回调函数
         * @param data 请求返回参数
         */
        selectMenuListSuc(data) {
            this.menuList = data.menuList;
        },

        /**
         * 选择菜单（MenuItem）时触发
         * @param name MenuItem的name属性值
         */
        onSelectMenu(name) {
            console.log(name);
            /*window.location.href = name;*/
            this.html = name+'.html';
           /* $("#child-url").load('/test/test.html');*/
        },
        
        
        //实时时间
        dealWithTime (data) {
		      let formatDateTime
		      let Y = data.getFullYear()
		      let M = data.getMonth() + 1
		      let D = data.getDate()
		      let H = data.getHours()
		      let Min = data.getMinutes()
		      let S = data.getSeconds()
		      let W = data.getDay()
		      H = H < 10 ? ('0' + H) : H
		      Min = Min < 10 ? ('0' + Min) : Min
		      S = S < 10 ? ('0' + S) : S
		      switch (W) {
		        case 0:
		          W = '天'
		          break
		        case 1:
		          W = '一'
		          break
		        case 2:
		          W = '二'
		          break
		        case 3:
		          W = '三'
		          break
		        case 4:
		          W = '四'
		          break
		        case 5:
		          W = '五'
		          break
		        case 6:
		          W = '六'
		          break
		        default:
		          break
		      }
		      formatDateTime = Y + '年' + M + '月' + D + '日 ' + H + ':' + Min + ':' + S + ' 星期' + W
		      return formatDateTime
		  }
    },
    // 注意在vue实例销毁前，清除我们的定时器。
		destroyed () {
		  if (this.timer) { 
		    clearInterval(this.timer)
		  }
		}
});