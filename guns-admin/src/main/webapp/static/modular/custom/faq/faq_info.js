/**
 * 初始化帮助详情对话框
 */
var FaqInfoDlg = {
    faqInfoData : {}
};

/**
 * 清除数据
 */
FaqInfoDlg.clearData = function() {
    this.faqInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FaqInfoDlg.set = function(key, val) {
    this.faqInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FaqInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
FaqInfoDlg.close = function() {
    parent.layer.close(window.parent.Faq.layerIndex);
}

/**
 * 收集数据
 */
FaqInfoDlg.collectData = function() {
    this
    .set('id')
    .set('faqType')
    .set('faqTitle')
    .set('faqContent')
    .set('createDate')
    .set('updateDate');
}

/**
 * 提交添加
 */
FaqInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/faq/add", function(data){
        Feng.success("添加成功!");
        window.parent.Faq.table.refresh();
        FaqInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.faqInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
FaqInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/faq/update", function(data){
        Feng.success("修改成功!");
        window.parent.Faq.table.refresh();
        FaqInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.faqInfoData);
    ajax.start();
}

$(function() {

});
