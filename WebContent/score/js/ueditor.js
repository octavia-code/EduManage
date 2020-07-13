var ueditor = new Vue({
	el: '#ueditor',
    data: function () {
        return {
            firstPath: '/score/ueditor',// 请求一级路径
            formLeft: {
                input1: '',
                input2: ''
            },
            
        }
    },
    components: {
        'layout-header': httpVueLoader('/layout/layout-header.vue'),
        'layout-sider': httpVueLoader('/layout/layout-sider.vue'),
        'layout-footer': httpVueLoader('/layout/layout-footer.vue')
    },
	
})


//实例化编辑器
var ue = UE.getEditor('editor');
