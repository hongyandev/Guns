/**
 * 设备分组管理初始化
 */
var IotGroup = {
    id: "IotGroupTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
IotGroup.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '分组ID', field: 'groupId', visible: true, align: 'center', valign: 'middle'},
            {title: '用户ID', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '分组名', field: 'groupName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
IotGroup.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        IotGroup.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加设备分组
 */
IotGroup.openAddIotGroup = function () {
    var index = layer.open({
        type: 2,
        title: '添加设备分组',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/iotGroup/iotGroup_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看设备分组详情
 */
IotGroup.openIotGroupDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '设备分组详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/iotGroup/iotGroup_update/' + IotGroup.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除设备分组
 */
IotGroup.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/iotGroup/delete", function (data) {
            Feng.success("删除成功!");
            IotGroup.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("iotGroupId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询设备分组列表
 */
IotGroup.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    IotGroup.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = IotGroup.initColumn();
    var table = new BSTable(IotGroup.id, "/iotGroup/list", defaultColunms);
    table.setPaginationType("client");
    IotGroup.table = table.init();
});
