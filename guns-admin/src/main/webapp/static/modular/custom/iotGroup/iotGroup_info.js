/**
 * 初始化设备分组详情对话框
 */
var IotGroupInfoDlg = {
    iotGroupInfoData : {}
};

/**
 * 清除数据
 */
IotGroupInfoDlg.clearData = function() {
    this.iotGroupInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
IotGroupInfoDlg.set = function(key, val) {
    this.iotGroupInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
IotGroupInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
IotGroupInfoDlg.close = function() {
    parent.layer.close(window.parent.IotGroup.layerIndex);
}

/**
 * 收集数据
 */
IotGroupInfoDlg.collectData = function() {
    this
    .set('groupId')
    .set('userId')
    .set('groupName');
}

/**
 * 提交添加
 */
IotGroupInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/iotGroup/add", function(data){
        Feng.success("添加成功!");
        window.parent.IotGroup.table.refresh();
        IotGroupInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.iotGroupInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
IotGroupInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/iotGroup/update", function(data){
        Feng.success("修改成功!");
        window.parent.IotGroup.table.refresh();
        IotGroupInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.iotGroupInfoData);
    ajax.start();
}

$(function() {

});
