(function ($) {
//	var data = [ [""] ];

    container = document.getElementById('dataTable');
    vIndex.hot = new Handsontable(container, {
        data: vIndex.nowData,//初始化时候的数据
        minCols: vIndex.minCols,//最小列数
        minRows: vIndex.minRows,//最小行数
        minSpareRows: 1,//空出多少行
        minSpareCols: 1,//最小行空间，不足则添加空行
        colHeaders: true,//显示行表头
        rowHeaders: true,//显示列表头
        mergeCells: true,
//        readOnly:true,
        /*        contextMenu:true,//显示表头下拉菜单
        */        manualColumnResize: true,
        manualRowResize: true,

        contextMenu: [
            "mergeCells",
            "row_above",
            "row_below",
            "col_left",
            "col_right",
            "---------",
            "remove_row",
            "remove_col",
            "---------",
            "alignment",
            "copy",
            "cut"

        ],


    });
    $(document).ready(function () {
        var mergedCellArr = vIndex.hot.getPlugin('mergeCells').mergedCellsCollection.mergedCells;//获取合并信息
        $("#btn1").click(function () {
            console.log(mergedCellArr);
        });
    });

})(jQuery);