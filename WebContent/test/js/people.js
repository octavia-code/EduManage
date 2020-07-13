var vPeople = new Vue({
    el: '#people',
    data: function () {
        return {
            people: {
                id: 'adsdasdasd', name: '', age: '', weight: '',
                birthday: '', createTime: '', state: 'A'
            },//实例类
            value: '',
        }
    },
    mounted: function () {
        console.log(this.people.state);
    },
    methods: {
        instance2() {
            this.$Modal.success({
                title: 'das',
                content: '<h2>注册成功</h2>',
                okText: '跳转登录',
            });
        },

        instance(type) {
            const title = 'Title';
            const content = '<p>Content of dialog</p><p>Content of dialog</p>';
            switch (type) {
                case 'info':
                    this.$Modal.info({
                        title: title,
                        content: content
                    });
                    break;
                case 'success':
                    this.$Modal.success({
                        title: title,
                        content: content
                    });
                    break;
                case 'warning':
                    this.$Modal.warning({
                        title: title,
                        content: content
                    });
                    break;
                case 'error':
                    this.$Modal.error({
                        title: title,
                        content: content
                    });
                    break;
            }
        }

    }
});