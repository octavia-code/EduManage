var vIndex = new Vue({
    el: '#index',
    data: function () {
        let vm = this;
        return {
            firstPath:'/Excel',
            minCols: 4, minRows: 4, //表格行数列数初始化
            nowData: [["dgdf"],["sdf"]],//表格数据
            hot: '',//与jquery双向绑定
            rowIdList:[],colIdList:[],contentList:[],//单元格数据
            spanRowIdList:[],spanColIdList:[],rowspanList:[],colspanList:[],//合并数据
            styleList:[],//对齐数据
            userId:''//用户id
        }
    },

    components: {},
    mounted() {

    },
    methods: {
        /**
         * 保存表格数据
         */
        saveTA() {
            console.log("数据信息:", this.nowData);
            let spanData = this.hot.getPlugin('mergeCells').mergedCellsCollection.mergedCells;//获取合并信息
            console.log("合并信息:", spanData);
            
            
//获取单元格数据
            /*console.log("this.nowData.length:",length);*/
            for (let i = 0; i < this.nowData.length; i++) {
                for (let j = 0; j < this.nowData.length; j++) {
                    if (this.nowData[i][j] != null) {
                        this.rowIdList.push(i);
                        this.colIdList.push(j);
                        this.contentList.push(this.nowData[i][j]);
                    }
                }
            }
           console.log(this.rowIdList, this.colIdList, this.contentList);
           //添加单元格数据，进入数据库
//            this.insertContent();

            
            
//获取合并数据

            for(let i = 0; i < spanData.length; i++){
                this.spanRowIdList.push(spanData[i].row);
                this.spanColIdList.push(spanData[i].col);
                this.rowspan.push(spanData[i].rowspan);
                this.colspan.push(spanData[i].colspan);
            }
            console.log(this.spanRowIdList,this.spanColIdList,this.rowspan,this.colspan)
//添加合并数据，进入数据库

        },

        /**
         * 插入单元格数据
         */
        insertContent(){
        	let data={
                userId:this.userId,
                rowIdList:this.rowIdList,
                colIdList:this.colIdList,
                contentList:this.contentList,
                rowspanList:this.rowspanList,
                colspanList:this.colspanList,
                styleList:this.styleList,

            };
        	console.log("data:",data)
            let url=this.firstPath+ '/insertList';
            callAjaxPost(url,data,this.insertContentSuc);
            // 打开加载提示
            this.loadingMsg = messageLoading();
        },
        insertContentSuc(data){
            // 关闭加载提示
            closeMessageLoading(this.loadingMsg);
            messageSuccess( '单元格数据插入成功');

        },

        /**
         * 插入合并数据
         */
        insertSpan(){

        },


    }


})