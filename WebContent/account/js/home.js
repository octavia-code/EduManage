var vHome = new Vue({
	// 对应div的id
	el : '#app',
	data : {
		list : [],
		userInfo : [],
		userName : ''
	},
	mounted : function() {
		changePagewidth();// 修改页面宽度
		getUserInfo();
		select();
	},
})
function changePagewidth() {
	var width = window.screen.width * 4 / 5;

	width = width < 1100 ? 1100 : width;
	$("#mainbody").css("width", width);

	// 获取屏幕高度
	var h = window.screen.height;

	// 页面头高度
	var headH = 90;

	// 菜单高度
	var menuH = 50;

	// 底部高度
	var footH = 60;

	// 内容高度
	h = h - headH - menuH - footH;

	h = h < 570 ? 570 : h;
	$("#iframe").css("height", h);
}

function getUserInfo() {
	var url = methodUrl + 'login/getUserInfo.sose'
	callAxiosGetNoParam(url, userSuc, fail)
}
function userSuc(data) {
	if(data.userInfo.userType=="B"||data.userInfo.userType=="C"){
		vHome.userName=data.userInfo.unitName;
	}else{
		vHome.userName = data.userInfo.name;
	};
};
// 查询菜单选项
function select() {
	var url = methodUrl + 'direct/list.sose'
	callAxiosGetNoParam(url, listSuc, fail)
}
function listSuc(data) {
	if (data.info == "null") {
		confirm("该账号未分配权限！请联系管理员分配权限");
		exit();
	}
	vHome.list = data.list
}
// iframe src属性修改
function selectMenu(url) {
	for (var i = 0; i < vHome.list.length; i++) {
		for (var j = 0; j < vHome.list[i].directList.length; j++) {
			if (url == vHome.list[i].directList[j].url) {
				document.title = vHome.list[i].directList[j].name
			}
		}
	}
	$("#iframe").attr("src", url)
}
function mousemove(data) {
	var id = "#info" + data;
	$(id).removeClass("mout")
	$(id).addClass("mover")
}
function mouseout(data) {
	var id = "#info" + data;
	$(id).removeClass("mover");
	$(id).addClass("mout");
}
// 个人信息
function spanclick(data) {
	// 用户个人信息
	if (data == 0) {
		var url = 'person.html';
		$("#iframe").attr("src", url);
	}
	// 退出
	if (data == 1) {
		exit();
	}
}
function exit(){
	var url = methodUrl + 'login/exit.sose';
	callAxiosGetNoParam(url, exitSuc, fail);
}
function exitSuc(data) {
	if (data.info == "success") {
		window.location.href = 'login.html'
	}
}
function fail() {
	console.log("首页加载失败，请检查")
}
// 动态设置显示区域大小
$(window).resize(function() {
	var w = window.innerWidth * 0.75
	$("#app").attr("width", w);
})