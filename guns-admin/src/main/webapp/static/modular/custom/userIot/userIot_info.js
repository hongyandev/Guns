/**
 * 初始化用户设备详情对话框
 */
var UserIotInfoDlg = {
    userIotInfoData : {}
};

/**
 * 清除数据
 */
UserIotInfoDlg.clearData = function() {
    this.userIotInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserIotInfoDlg.set = function(key, val) {
    this.userIotInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserIotInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
UserIotInfoDlg.close = function() {
    parent.layer.close(window.parent.UserIot.layerIndex);
}

/**
 * 收集数据
 */
UserIotInfoDlg.collectData = function() {
    this
    .set('iotId')
    .set('productKey')
    .set('nickName')
    .set('owned')
    .set('status')
    .set('userId')
    .set('groupId')
    .set('sceneId')
    .set('gmtModified');
}

/**
 * 提交添加
 */
UserIotInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/userIot/add", function(data){
        Feng.success("添加成功!");
        window.parent.UserIot.table.refresh();
        UserIotInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userIotInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
UserIotInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/userIot/update", function(data){
        Feng.success("修改成功!");
        window.parent.UserIot.table.refresh();
        UserIotInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userIotInfoData);
    ajax.start();
}

$(function() {

});
