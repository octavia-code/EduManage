var data = [
    {riqi: '2019-06-07', address: '北京', goods: '冰箱', price: '3399', sales: 530},
    {riqi: '2019-06-07', address: '天津', goods: '空调', price: '4299', sales: 522},
    {riqi: '2019-06-07', address: '上海', goods: '洗衣机', price: '1299', sales: 544},
    {riqi: '2019-06-07', address: '广州', goods: '彩电', price: '4599', sales: 562},
    {riqi: '2019-06-07', address: '深圳', goods: '热水器', price: '1099', sales: 430},
    {riqi: '2019-06-07', address: '重庆', goods: '笔记本电脑', price: '4999', sales: 666},
    {riqi: '2019-06-07', address: '厦门', goods: '油烟机', price: '2899', sales: 438}
];

function negativeValueRenderer(instance, td, row, col, prop, value, cellProperties) {
    Handsontable.renderers.TextRenderer.apply(this, arguments);
    td.style.color = '#000';
    if (prop == 'address') {
        //修改字体颜色
        //如果要添加其他样式，可以用以下写法
        //td.style = 'font-weight: bold;';
    }
    else if (prop == 'price') {
        //格式化价格数据
        td.innerText = value != null ? numbro(value).format('0.00') : "";
    }
    else if (prop == 'sales') {
        //右对齐
        td.style.textAlign = 'right';
        td.innerText = value != null ? numbro(value).format('0,0.00') : "";
    }
}

Handsontable.renderers.registerRenderer('negativeValueRenderer', negativeValueRenderer);

var hot = new Handsontable(document.getElementById('example'), {
    data: data,
    colHeaders: ['日期', '地点', '商品', '单价', '销量'], // 使用自定义列头
    rowHeaders: true,
    readOnly: true, // 设置可读
    colWidths: 150, // 设置所有列宽为150像素
    filters: true,
    columnSorting: false,
    sortIndicator: true,
    autoColumnSize: true,
    manualColumnResize: true,
    undo: true,
    redo: true,
    wordWrap: true,
    copyable: true,
    mergeCells: false,
    manualRowResize: true,
    manualRowMove: true,
    manualColumnMove: true,
    renderAllRows: true,
    allowInsertRow: true,
    allowInsertColumn: false,
    fixedColumnsLeft: 1,
    columns: [{
        data: 'riqi',
        type: 'date',
        dateFormat: 'YYYY-MM-DD'
    }, {
        data: 'address',
        type: 'text'
    }, {
        data: 'goods',
        type: 'text'
    }, {
        data: 'price',
        type: 'numeric'
    }, {
        data: 'sales',
        type: 'numeric'
    }],
  /*  contextMenu: ['row_above', 'row_below', '---------', 'remove_row', '---------', 'undo', 'redo', '---------', 'make_read_only', '---------', 'alignment'],*/
    dropdownMenu: ['filter_by_condition', 'filter_by_value', 'filter_action_bar'],
    cells: function (row, col, prop) {
        var cellProperties = {};
        cellProperties.renderer = "negativeValueRenderer";
        return cellProperties;
    },
});
