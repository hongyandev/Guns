/**
 * 初始化产品详情对话框
 */
var ProductInfoDlg = {
    productInfoData : {}
};

/**
 * 清除数据
 */
ProductInfoDlg.clearData = function() {
    this.productInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProductInfoDlg.set = function(key, val) {
    this.productInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProductInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ProductInfoDlg.close = function() {
    parent.layer.close(window.parent.Product.layerIndex);
}

/**
 * 收集数据
 */
ProductInfoDlg.collectData = function() {
    this
    .set('tenantId')
    .set('productKey')
    .set('dataFormat')
    .set('netType')
    .set('productSecret')
    .set('nodeType')
    .set('domain')
    .set('name')
    .set('region')
    .set('ownerDomain')
    .set('categoryld')
    .set('categoryKey')
    .set('categoryName')
    .set('accessMethod')
    .set('status')
    .set('gmtCreate')
    .set('gmtModified')
    .set('creator')
    .set('modifier')
    .set('productId')
    .set('aliyunCommodityCode')
    .set('connectMode')
    .set('rbacTenantId')
    .set('iotPackage');
}

/**
 * 提交添加
 */
ProductInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/product/add", function(data){
        Feng.success("添加成功!");
        window.parent.Product.table.refresh();
        ProductInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.productInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ProductInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/product/update", function(data){
        Feng.success("修改成功!");
        window.parent.Product.table.refresh();
        ProductInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.productInfoData);
    ajax.start();
}

$(function() {

});
