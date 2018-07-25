/**
 * 用户设备管理初始化
 */
var UserIot = {
    id: "UserIotTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
UserIot.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '物ID', field: 'iotId', visible: true, align: 'center', valign: 'middle'},
            {title: '产品Key', field: 'productKey', visible: true, align: 'center', valign: 'middle'},
            {title: '物昵称', field: 'nickName', visible: true, align: 'center', valign: 'middle'},
            {title: '0:分享者;1:拥有者', field: 'owned', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '用户ID', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '分组ID', field: 'groupId', visible: true, align: 'center', valign: 'middle'},
            {title: '场景ID', field: 'sceneId', visible: true, align: 'center', valign: 'middle'},
            {title: '绑定时间', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
UserIot.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        UserIot.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户设备
 */
UserIot.openAddUserIot = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户设备',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/userIot/userIot_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户设备详情
 */
UserIot.openUserIotDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户设备详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/userIot/userIot_update/' + UserIot.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户设备
 */
UserIot.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/userIot/delete", function (data) {
            Feng.success("删除成功!");
            UserIot.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("userIotId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询用户设备列表
 */
UserIot.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    UserIot.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = UserIot.initColumn();
    var table = new BSTable(UserIot.id, "/userIot/list", defaultColunms);
    table.setPaginationType("client");
    UserIot.table = table.init();
});
