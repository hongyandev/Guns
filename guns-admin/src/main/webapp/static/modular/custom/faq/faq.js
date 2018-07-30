/**
 * 帮助管理初始化
 */
var Faq = {
    id: "FaqTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Faq.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '问题类别', field: 'faqTypeName', visible: true, align: 'center', valign: 'middle'},
            {title: '问题', field: 'faqTitle', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createDate', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'updateDate', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Faq.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Faq.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加帮助
 */
Faq.openAddFaq = function () {
    var index = layer.open({
        type: 2,
        title: '添加帮助',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/faq/faq_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看帮助详情
 */
Faq.openFaqDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '帮助详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/faq/faq_update/' + Faq.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除帮助
 */
Faq.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/faq/delete", function (data) {
            Feng.success("删除成功!");
            Faq.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("faqId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询帮助列表
 */
Faq.search = function () {
    var queryData = {};
    queryData['faqType'] = $("#faqType").val();
    queryData['faqTitle'] = $("#faqTitle").val();
    Faq.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Faq.initColumn();
    var table = new BSTable(Faq.id, "/faq/list", defaultColunms);
    table.setPaginationType("client");
    Faq.table = table.init();
});
