/**
 * 初始化表头数据
 *
 * @param key 对应列内容的字段名
 * @param title 列头显示文字
 * @returns {Array} 表格列的配置描述
 */
function showCol(key, title) {
    let col = new Array();
    for (let i = 0; i < title.length; i++) {
        let json = {
            key: key[i],
            title: title[i],
            // align: 'center'
        };
        col.push(json);
    }
    return col;
}


/**
 * 向已存在的表头数据中记录添加值，嵌入InputNumber数字输入框,同时表头生成一个button按钮
 *
 * @param vm vue实例对象
 * @param key 对应列内容的字段名
 * @param title 列头显示文字
 * @param func 按钮执行的方法
 * @returns {Array} 表格列的配置描述
 */
function showColMoreInputNumberWithButton(vm, col, key, title, func, func2){
	let column = col;
    for (let i = 0; i < title.length; i++) {
        let json = {
            key: key[i],
            align:'center',
            renderHeader:(h,params)=>{
				let btns = [];
				btns.push(h('span',{
					style:{
						'font-size':'5px',
						'margin-right':'10px',
						},
					 },title[i]));
				btns.push(h('Button',{
					style:{
						'height':'25px',
						'width':'60px',
					    'padding': 0,
					},
					on:{
						click:()=>{
							func(key[i].split("_")[1],title[i]);
						}
					},
				 },"编辑"));
				return btns;
				},
            render: (h, params) => {
                let currentParam = key[i];
                let InputId = "Item" + params.index + "_" + currentParam;
                switch(vm.nowData[params.index].stuState){
                case "H": //缓考
			                return h('InputNumber', {
			                    props: {
			                        value: params.row[currentParam],// 当前行添加对应的属性
			                        precision: 0,
			                        min: 0,
			                        max: 100,
			                        disabled:(params.column.key==vm.examKey)?true:false,
			                    },
			                    on: {
			                        input: (val) => {
			                            params.row[currentParam] = val; // 将输入框的值赋给对应属性
			                            vm.nowData[params.index] = params.row; // 将当前行的值赋给对应数据集合
			                        },
			                        'on-blur': (event) =>{
			                            vm.column = cloneObj(vm.stuColumn);
			                            // 动态添加表头
			                            vm.column = showColMoreInputNumberWithButton(vm, vm.column, vm.keyList, vm.titleList, vm.test,vm.countTotalScore);
			                            //总评表头相关配置
			                            let totalScoreColumn={
			                            		 title:'总评',
			                            		 key: 'totalScore',
			                            		 align:'center',
			                            		 render:(h,params)=>{
			                            			 	return h('span',vm.totalScoreList[params.index])
			                            		 }
			                            }
			                            //添加总评
			                            vm.column.push(totalScoreColumn);
			                        	// 添加自定义slot-scope
			                            vm.column.push(headActionSlot());
			                            func2();
			                        },
			                    },
			                })
                case "L": //缺考
	                return h('InputNumber', {
	                    props: {
	                        value: params.row[currentParam],// 当前行添加对应的属性
	                        precision: 0,
	                        min: 0,
	                        max: 100,
	                        disabled:(params.column.key==vm.examKey)?true:false,
	                    },
	                    on: {
	                        input: (val) => {
	                            params.row[currentParam] = val; // 将输入框的值赋给对应属性
	                            vm.nowData[params.index] = params.row; // 将当前行的值赋给对应数据集合
	                        },
	                        'on-blur': (event) =>{
	                            vm.column = cloneObj(vm.stuColumn);
	                            // 动态添加表头
	                            vm.column = showColMoreInputNumberWithButton(vm, vm.column, vm.keyList, vm.titleList, vm.test,vm.countTotalScore);
	                            //总评表头相关配置
	                            let totalScoreColumn={
	                            		 title:'总评',
	                            		 key: 'totalScore',
	                            		 align:'center',
	                            		 render:(h,params)=>{
	                            			 	return h('span',vm.totalScoreList[params.index])
	                            		 }
	                            }
	                            //添加总评
	                            vm.column.push(totalScoreColumn);
	                        	// 添加自定义slot-scope
	                            vm.column.push(headActionSlot());
	                            func2();
	                        },
	                    },
	                })
                case "M": //补考
	                return h('InputNumber', {
	                    props: {
	                        value: params.row[currentParam],// 当前行添加对应的属性
	                        precision: 0,
	                        min: 0,
	                        max: 100,
	                        disabled:(params.column.key!=vm.examKey)?true:false,
	                    },
	                    on: {
	                        input: (val) => {
	                            params.row[currentParam] = val; // 将输入框的值赋给对应属性
	                            vm.nowData[params.index] = params.row; // 将当前行的值赋给对应数据集合
	                        },
	                        'on-blur': (event) =>{
	                            vm.column = cloneObj(vm.stuColumn);
	                            // 动态添加表头
	                            vm.column = showColMoreInputNumberWithButton(vm, vm.column, vm.keyList, vm.titleList, vm.test,vm.countTotalScore);
	                            //总评表头相关配置
	                            let totalScoreColumn={
	                            		 title:'总评',
	                            		 key: 'totalScore',
	                            		 align:'center',
	                            		 render:(h,params)=>{
	                            			 	return h('span',vm.totalScoreList[params.index])
	                            		 }
	                            }
	                            //添加总评
	                            vm.column.push(totalScoreColumn);
	                        	// 添加自定义slot-scope
	                            vm.column.push(headActionSlot());
	                            func2();
	                        },
	                    },
	                })
                case "R": //重修
	                return h('InputNumber', {
	                    props: {
	                        value: params.row[currentParam],// 当前行添加对应的属性
	                        precision: 0,
	                        min: 0,
	                        max: 100,
	                        disabled:true,
	                    },
	                    on: {
	                        input: (val) => {
	                            params.row[currentParam] = val; // 将输入框的值赋给对应属性
	                            vm.nowData[params.index] = params.row; // 将当前行的值赋给对应数据集合
	                        },
	                        'on-blur': (event) =>{
	                            vm.column = cloneObj(vm.stuColumn);
	                            // 动态添加表头
	                            vm.column = showColMoreInputNumberWithButton(vm, vm.column, vm.keyList, vm.titleList, vm.test,vm.countTotalScore);
	                            //总评表头相关配置
	                            let totalScoreColumn={
	                            		 title:'总评',
	                            		 key: 'totalScore',
	                            		 align:'center',
	                            		 render:(h,params)=>{
	                            			 	return h('span',vm.totalScoreList[params.index])
	                            		 }
	                            }
	                            //添加总评
	                            vm.column.push(totalScoreColumn);
	                        	// 添加自定义slot-scope
	                            vm.column.push(headActionSlot());
	                            func2();
	                        },
	                    },
	                })
                case "A": //重置
	                return h('InputNumber', {
	                    props: {
	                        value: params.row[currentParam],// 当前行添加对应的属性
	                        precision: 0,
	                        min: 0,
	                        max: 100,
	                        disabled:false,
	                    },
	                    on: {
	                        input: (val) => {
	                            params.row[currentParam] = val; // 将输入框的值赋给对应属性
	                            vm.nowData[params.index] = params.row; // 将当前行的值赋给对应数据集合
	                        },
	                        'on-blur': (event) =>{
	                            vm.column = cloneObj(vm.stuColumn);
	                            // 动态添加表头
	                            vm.column = showColMoreInputNumberWithButton(vm, vm.column, vm.keyList, vm.titleList, vm.test,vm.countTotalScore);
	                            //总评表头相关配置
	                            let totalScoreColumn={
	                            		 title:'总评',
	                            		 key: 'totalScore',
	                            		 align:'center',
	                            		 render:(h,params)=>{
	                            			 	return h('span',vm.totalScoreList[params.index])
	                            		 }
	                            }
	                            //添加总评
	                            vm.column.push(totalScoreColumn);
	                        	// 添加自定义slot-scope
	                            vm.column.push(headActionSlot());
	                            func2();
	                        },
	                    },
	                })
                default :
			                return h('InputNumber', {
			                    props: {
			                        value: params.row[currentParam],// 当前行添加对应的属性
			                        precision: 0,
			                        min: 0,
			                        max: 100,
			                        disabled:false,
			                    },
			                    on: {
			                        input: (val) => {
			                            params.row[currentParam] = val; // 将输入框的值赋给对应属性
			                            vm.nowData[params.index] = params.row; // 将当前行的值赋给对应数据集合
			                        },
			                        'on-blur': (event) =>{
			                            vm.column = cloneObj(vm.stuColumn);
			                            // 动态添加表头
			                            vm.column = showColMoreInputNumberWithButton(vm, vm.column, vm.keyList, vm.titleList, vm.test,vm.countTotalScore);
			                            //总评表头相关配置
			                            let totalScoreColumn={
			                            		 title:'总评',
			                            		 key: 'totalScore',
			                            		 align:'center',
			                            		 render:(h,params)=>{
			                            			 	return h('span',vm.totalScoreList[params.index])
			                            		 }
			                            }
			                            //添加总评
			                            vm.column.push(totalScoreColumn);
			                        	// 添加自定义slot-scope
			                            vm.column.push(headActionSlot());
			                            func2();
			                        },
			                    },
			                })
                }
                

            }
        };
        column.push(json);
    }
    return column;
}

/**
 * 向已存在的表头数据中记录添加值，嵌入InputNumber数字输入框
 *
 * @param vm vue实例对象
 * @param key 对应列内容的字段名
 * @param title 列头显示文字
 * @returns {Array} 表格列的配置描述
 */
function showColMoreInputNumber(vm, col, key, title) {
    for (let i = 0; i < title.length; i++) {
        let json = {
            key: key[i],
            title: title[i],
            width: 150,
            render: (h, params) => {
                let currentParam = key[i];
                let InputId = "Item" + params.index + "_" + currentParam;
                return h('InputNumber', {
                    props: {
                        value: params.row[currentParam],// 当前行添加对应的属性
                        precision: 0,
                        min: 0,
                        max: 100,
                    },
                    on: {
                        input: (val) => {
                            params.row[currentParam] = val; // 将输入框的值赋给对应属性
                            vm.nowData[params.index] = params.row; // 将当前行的值赋给对应数据集合
                        },
                    },
                })
            }
        };
        col.push(json);
    }
    return col;
}

/**
 * 表头多选，使用unshift()头部添加
 *
 * @return {{type: string, width: number, align: string}}
 */
function headSelection() {
    let selection = {
        type: 'selection',
        width: 60,
        align: 'center'
    };
    return selection;
}

/**
 * 表头多选，固定到左侧，使用unshift()头部添加
 *
 * @return {{type: string, width: number, align: string}}
 */
function headSelectionLeft() {
    let selection = {
        type: 'selection',
        width: 60,
        fixed: 'left',
        align: 'center'
    };
    return selection;
}

/**
 * 添加序号
 * @returns {{title: string, type: string, width: number, align: string}}
 */
function headIndex() {
    let indexList = {
        title: '序号',
        type: 'index',
        width: 70,
        align: 'center'
    };
    return indexList;
}

/**
 * 添加序号,固定到左侧
 * @returns {{title: string, type: string, width: number, align: string}}
 */
function headIndexLeft() {
    let indexList = {
        title: '序号',
        type: 'index',
        width: 70,
        fixed: 'left',
        align: 'center'
    };
    return indexList;
}

/**
 * 加载slot-scope操作栏
 *
 * @return 操作栏
 */
function headActionSlot() {
    let action = {
        title: '操作',
        slot: 'action',
        width: 200,
        align: 'center'
    };
    return action;
}

/**
 * 加载slot-scope操作栏,固定到右侧
 *
 * @return 操作栏
 */
function headActionSlotRight() {
    let action = {
        title: '操作',
        slot: 'action',
        width: 200,
        fixed: 'right',
        align: 'center'
    };
    return action;
}

/**
 * 自定义表头列模板，通过push() 结尾添加
 *
 * @param is_dtl
 *            是否显示详情按钮
 * @param dtlFunc
 *            详情展示方法，若无写null
 * @param is_edit
 *            是否显示编辑按钮
 * @param editFunc
 *            编辑方法，若无写null
 * @param is_del
 *            是否显示删除按钮
 * @param delFunc
 *            删除方法，若无写null
 * @returns 自定义列模板
 */
function headAction(is_dtl, dtlFunc, is_edit, editFunc, is_del, delFunc) {
    // 自定义操作栏宽度
    let actionWidth = 200;
    // 初始化表头action
    let action = {
        title: '操作',
        key: 'action',
        width: actionWidth,
        align: 'center',
        render: (h, params) => {
            let btns = [];
            if (is_dtl) {
                btns.push(
                    h('Button', {
                        props: {
                            type: 'success',
                            size: 'small',
                        },
                        style: {
                            marginRight: '5px'
                        },
                        on: {
                            click: () => {
                                dtlFunc(params.index)
                            }
                        }
                    }, '备注详情')
                )
            }
            if (is_edit) {
                btns.push(
                    h('Button', {
                        props: {
                            type: 'primary',
                            size: 'small',
                        },
                        style: {
                            marginRight: '5px'
                        },
                        on: {
                            click: () => {
                                editFunc(params.index)
                            }
                        }
                    }, '修改')
                )
            }
            if (is_del) {
                btns.push(h('Poptip',
                    {
                        props: {
                            confirm: true,
                            title: '您确定要删除吗?'
                        },
                        on: {
                            'on-ok': () => {
                                delFunc(params.index);
                            }
                        }
                    },
                    [
                        h('Button', {
                            props: {
                                type: 'warning',
                                size: 'small'
                            },
                            style: {
                                marginRight: '5px'
                            },
                        }, '删除')
                    ]
                    )
                )
            }
            return h("div", btns)
        }
    };
    return action;


}

/**
 * 添加文字提示
 * @param key
 * @param title
 * @returns {{title: string, key: string, width: number, render: (function(*, *))}}
 */
function headTooltip(key, title) {
    let action = {
        title: title,
        key: key,
        width: 200,
        render: (h, param) => {
            // 表格显示的文字
            let texts = '';
            // 内容不为空
            if (!isEmpty(param.row[key])) {
                // 长度小于15
                if (param.row[key].length <= 12) {
                    texts = param.row[key];
                    return h("span", texts)
                } else {
                    texts = (param.row[key]).substring(0, 12) + "...";
                    // 鼠标移入时显示的文字
                    let str = param.row[key];
                    return h(
                        "tooltip", {
                            props: {
                                placement: "top-start",
                                transfer: true,
                                theme: "light",
                            }
                        },
                        [
                            texts,
                            h("span", {
                                slot: "content",
                                style: {
                                    whiteSpace: "normal",
                                    wordBreak: "break-all"
                                },// 控制文字样式，可以换行显示
                            }, str)
                        ]
                    )
                }
            }
        }
    };
    return action;
}




    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
