let vTest = new Vue({
    el: '#test',
    data: function () {
        let vm = this;
        return {
            soltColumn: [
                {title: '序号', key: 'seq', width: 100, sortable: true},
                {title: '学号', key: 'studentNumber', width: 150},
                {
                    title: '姓名', key: 'studentName', width: 150,
                    render: (h, params) => {
                        // 赋值
                        // 方式一
                        // return h('span', params.row.studentName)
                        // 方式二，嵌套了一层div，在div中渲染span
                        return h('div', [
                            h('span', {
                                // 设置元素样式
                                // a.添加style属性
                                /*style: {
                                    fontSize: '14px',
                                    padding: '5px 10px',
                                    cursor: 'pointer',
                                    color: '#fc1'
                                }*/
                                // b.添加class属性
                                class: 'student-class'
                            }, params.row.studentName),
                        ]);
                    }
                },
                {
                    title: '简历', key: 'remark', width: 150,
                    render: (h, params) => {
                        return h('span', {
                            style: {
                                width: '30px',
                                color: 'red',
                                fontSize: '14px'
                            }
                        }, params.row.studentInfo.remark)
                    }
                },
                {
                    title: '成绩', key: 'score', width: 100,
                    render: (h, params) => {
                        // 判断成绩级别，分别设置样式
                        let score = params.row.score;
                        // 成绩样式
                        let scoreClass = '';
                        switch (true) {
                            case (score >= 90):
                                scoreClass = 'score-excellent';
                                break;
                            case (score < 60):
                                scoreClass = 'score-flunk';
                                break;
                            default:
                                break;
                        }
                        return h('span', {
                            class: scoreClass
                        }, [score])
                    }
                }
            ],// solt表头
            nowData3: [
                {
                    courseClassStudentId: 1, // 课程班级学生信息id
                    seq: 1, // 序号
                    studentId: 1, // 学生id
                    studentNumber: 10011, // 学号
                    studentName: '老王', // 姓名
                    score: 100,//成绩
                    studentInfo: {
                        remark: '这是简历1',
                        height: 180,
                    }
                },
                {
                    courseClassStudentId: 2, // 课程班级学生信息id
                    seq: 2, // 序号
                    studentId: 2, // 学生id
                    studentNumber: 10012, // 学号
                    studentName: '小张', // 姓名
                    score: 78,//成绩
                    studentInfo: {
                        remark: '这是简历2',
                        height: 180,
                    }
                },
                {
                    courseClassStudentId: 3, // 课程班级学生信息id
                    seq: 3, // 序号
                    studentId: 3, // 学生id
                    studentNumber: 10013, // 学号
                    studentName: '小红', // 姓名
                    score: 59,//成绩
                    studentInfo: {
                        remark: '这是简历3',
                        height: 180,
                    }
                },
            ],// 学生信息原数据

            column2: [
                {title: '姓名', key: 'name', width: 100, align: 'center'},
                {
                    title: '年龄',
                    key: 'age',
                    width: 100,
                    align: 'center',
                    render: (h, params) => {
                        return h('InputNumber', {
                            props: {
                                value: params.row.age,
                                precision: 0,
                                min: 0,
                                max: 100
                            },
                            on: {
                                input: (val) => {
                                    params.row.age = val;
                                    vm.nowData[params.index] = params.row;
                                    console.log(val, params.index, vm.nowData[params.index]);
                                }
                            },
                        })
                    }
                },
                {
                    title: '性别',
                    key: 'gender',
                    width: 100,
                    align: 'center',
                    render: (h, params) => {
                        return h('Input', {
                            props: {
                                value: params.row.gender,
                            },
                            on: {
                                input: (val) => {
                                    params.row.gender = val;
                                    vm.nowData[params.index] = params.row;
                                    console.log(val, params.index, vm.nowData[params.index]);
                                }
                            }
                        })
                    }
                },
                {
                    title: '籍贯',
                    key: 'nativePlace',
                    width: 150,
                    align: 'center',
                    render: (h, params) => {
                        return h('Select', {
                                props: {
                                    value: params.row.nativePlace,
                                },
                                on: {
                                    'on-change': (value) => {
                                        vm.nowData[params.index].nativePlace = value;
                                        console.log(value, params.index, vm.nowData[params.index]);
                                    }
                                },
                            },
                            // 通过map函数就可以代替v-for的渲染（如果数据中的value值为空，select将不被渲染）
                            vm.nativePlaceList2.map((item) => {
                                return h('Option', {
                                    props: {
                                        value: item.value,
                                        label: item.name
                                    }
                                })
                            })
                            // 数据本身的集合
                            // params.row.nativePlaceList.map((item) => {
                            //     return h('Option', {
                            //         props: {
                            //             value: item.value,
                            //             label: item.name
                            //         }
                            //     })
                            // })
                        )
                    }
                },
            ],// render表头
            nowData2: [
                {
                    id: 1,
                    name: '老王',
                    age: 25,
                    gender: '男',
                    nativePlace: 1,
                    // assessment_1: 11,
                    // assessment_2: 22,
                    // assessment_3: 33,
                    // assessment_4: 44,
                    // 写在数据中的集合
                    // nativePlaceList: [
                    //     {
                    //         value: 1,
                    //         name: '江苏淮安'
                    //     },
                    //     {
                    //         value: 2,
                    //         name: '江苏南京'
                    //     },
                    // ]
                },
                {
                    id: 2,
                    name: '小红',
                    age: 18,
                    gender: '女',
                    nativePlace: 1,
                    // assessment_1: 11,
                    // assessment_2: 22,
                    // assessment_3: 33,
                    // assessment_4: 44,
                },
            ],// 原数据
            nativePlaceList2: [
                {
                    value: 1,
                    name: '江苏淮安1'
                },
                {
                    value: 2,
                    name: '江苏南京2'
                },
                {
                    value: 3,
                    name: '江苏扬州3'
                },
            ], //籍贯集合

            gradeFormId: 1,//成绩登记表id
            courseOutlineId: 1,//课程大纲id
            courseClassInfoId: 1,// 上课班级id
            assessmentIdList: [],// 考核id集合

            column: [
                {title: '序号', key: 'seq', width: 100},
                {title: '学号', key: 'studentNumber', width: 150},
                {title: '姓名', key: 'studentName', width: 150},
            ],// 学生表头
            nowData: [], // 原数据
            cloneData: [],// 原数据备份
            changeData: [],// 变更的数据

            assessmentList2: [
                {id: 1, assessName: '期末考试', assessRate: 40},
                {id: 2, assessName: '实践考核', assessRate: 30},
                {id: 3, assessName: '测试分析与设计研讨与报告撰写', assessRate: 20},
                {id: 4, assessName: '作业', assessRate: 10}
            ],// 考核集合
            assessmentList: [],
            keyList: [],// 对应列内容的字段名集合
            titleList: [], // 列头显示文字集合
        }
    },
    components: {
        'layout-header': httpVueLoader('/layout/layout-header.vue'),
        'layout-sider': httpVueLoader('/layout/layout-sider.vue'),
        'layout-footer': httpVueLoader('/layout/layout-footer.vue')
    },
    mounted() {
        // this.initPage();
//        this.getAssessmentList();
        console.log(this.nowData3);
        this.soltColumn.push(headActionSlot());
    },
    methods: {
        /**
         * 拖拽排序松开时触发，返回置换的两行数据索引
         * @param index1 第一行数据索引
         * @param index2 第二行数据索引
         */
        onDragDrop(index1, index2) {
            console.log(index1, index2);
            // 使用临时对象交换序号
            let tempSeq = this.nowData3[index1].seq;
            this.nowData3[index1].seq = this.nowData3[index2].seq;
            this.nowData3[index2].seq = tempSeq;
            console.log(this.nowData3);
        },

        /**
         * 编辑
         */
        editNowData3(index) {
            console.log(this.nowData3[index]);
        },


        /**
         * 通过成绩登记表id查询课程考核集合
         */
        getAssessmentList() {
            let data = this.courseOutlineId;
            let url = '/outline/assessment/listByCourseOutlineId';
            callAjaxPost(url, data, this.getAssessmentListSuc);
        },
        getAssessmentListSuc(data) {
            console.log(data);
            this.assessmentList = data.obj;
            // 考核id集合
            for (let i = 0; i < this.assessmentList.length; i++) {
                this.assessmentIdList.push(this.assessmentList[i].id);
            }
            console.log(this.assessmentIdList);
            this.setTableHead();
        },

        /**
         * 设置动态加载的表头
         */
        setTableHead() {
            // 将考核集合中的数据转为 key 和 title 集合
            for (let i = 0; i < this.assessmentList.length; i++) {
                // key:score_assessmentId
                this.keyList.push("score_" + this.assessmentList[i].id);
                this.titleList.push(this.assessmentList[i].assessName + "(" + this.assessmentList[i].assessRate + "%)");
            }

            // 动态添加表头
            this.column = showColMoreInputNumber(this, this.column, this.keyList, this.titleList);
            console.log(this.column);
            // 获取成绩，防止成绩渲染比表头快
            this.getStudentWithScore();
        },

        /**
         * 获取学生和对应考核成绩的信息
         */
        getStudentWithScore() {
            let data = {
                gradeFormId: this.gradeFormId,
                courseOutlineId: this.courseOutlineId,
                courseClassInfoId: this.courseClassInfoId
            };
            let url = '/info/courseClassStudentInfo/listStudentWithScore';
            callAjaxPost(url, data, this.getStudentWithScoreSuc);
        },
        getStudentWithScoreSuc(data) {
            console.log(data);
            var count = 0;
            this.nowData = data.obj;
            // 遍历表格数据中的json对象
            for (let j = 0; j < this.nowData.length; j++) {
                // json对象添加属性，score_assessmentId
                for (let k = 0; k < this.keyList.length; k++) {
                    let score_assessmentId = this.keyList[k]; // 以表头key创建成绩属性
                    this.nowData[j][score_assessmentId] = ''; // 给json对象添加成绩属性
                    let score_assessmentId_id = score_assessmentId + '_id'; // 成绩id属性=成绩属性+id
                    this.nowData[j][score_assessmentId_id] = '';

                    // 循环当前数据中的考核成绩集合
                    for (let f = 0; f < this.nowData[j].scoreAssessmentList.length; f++) {
                        // 从表头key集合中获取考核id
                        let assessmentId = this.keyList[k].split("_")[1];
                        // 考核成绩集合中的考核id 和 表头key集合中获取考核id相等
                        if (this.nowData[j].scoreAssessmentList[f].assessmentId == assessmentId) {
                            // 给成绩属性赋值
                            this.nowData[j][score_assessmentId] = this.nowData[j].scoreAssessmentList[f].score;
                            // 给成绩id属性赋值
                            this.nowData[j][score_assessmentId_id] = this.nowData[j].scoreAssessmentList[f].scoreAssessmentId;
                        }
                        count++;
                    }
                }
                // 删除不必要的属性
                delete this.nowData[j].scoreAssessmentList;
            }
            console.log('循环的次数：' + count);
            this.cloneData = cloneObj(this.nowData); // 备份原数据
            console.log('数据备份完成', this.nowData, this.cloneData);
        },

        /**
         * 添加表格所有的考核成绩
         */
        addAllScoreAssessment() {
            for (let i = 0; i < this.nowData.length; i++) {
                console.log(this.nowData[i].seq, Compare(this.nowData[i], this.cloneData[i]));
                //判断数据与原数据是否相等
                let isEqule = Compare(this.nowData[i], this.cloneData[i]);
                // 不相等即修改过的数据，添加到变更集合中
                if (!isEqule) {
                    this.changeData.push(this.nowData[i]);
                }
            }
            console.log(this.changeData, this.nowData, this.cloneData);
            if (this.changeData.length == 0) {
                messageInfo('没有修改过的数据');
                return;
            }

            let url = '/score/scoreAssessment/insertOrUpdateList';
            let data = {
                assessmentIdList: this.assessmentIdList,
                scoreAssessmentList: this.changeData,
            };
            callAjaxPost(url, data, this.addAllScoreAssessmentSuc);
        },
        addAllScoreAssessmentSuc(data) {
            messageSuccess('提交成功');
            // 重新查询数据
            this.getStudentWithScore();
        }

    }
});


