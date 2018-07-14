/**
 * product管理初始化
 */
var Product = {
    id: "ProductTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Product.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '租户id', field: 'tenantId', visible: true, align: 'center', valign: 'middle'},
            {title: '产品PK', field: 'productKey', visible: true, align: 'center', valign: 'middle'},
            {title: '数据格式', field: 'dataFormat', visible: true, align: 'center', valign: 'middle'},
            {title: '入网类型', field: 'netType', visible: true, align: 'center', valign: 'middle'},
            {title: '产品密钥', field: 'productSecret', visible: true, align: 'center', valign: 'middle'},
            {title: '节点类型', field: 'nodeType', visible: true, align: 'center', valign: 'middle'},
            {title: '领域', field: 'domain', visible: true, align: 'center', valign: 'middle'},
            {title: '产品名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '地域', field: 'region', visible: true, align: 'center', valign: 'middle'},
            {title: '所有者领域', field: 'ownerDomain', visible: true, align: 'center', valign: 'middle'},
            {title: '归属品类id', field: 'categoryld', visible: true, align: 'center', valign: 'middle'},
            {title: 'categoryKey', field: 'categoryKey', visible: true, align: 'center', valign: 'middle'},
            {title: 'categoryName', field: 'categoryName', visible: true, align: 'center', valign: 'middle'},
            {title: '访问方式', field: 'accessMethod', visible: true, align: 'center', valign: 'middle'},
            {title: '产品状态(0:开发中,1:已发布)', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'gmtCreate', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'},
            {title: '创建者', field: 'creator', visible: true, align: 'center', valign: 'middle'},
            {title: '修改者', field: 'modifier', visible: true, align: 'center', valign: 'middle'},
            {title: '产品ID', field: 'productId', visible: true, align: 'center', valign: 'middle'},
            {title: '商品码', field: 'aliyunCommodityCode', visible: true, align: 'center', valign: 'middle'},
            {title: 'connectMode', field: 'connectMode', visible: true, align: 'center', valign: 'middle'},
            {title: 'rbac租户Id', field: 'rbacTenantId', visible: true, align: 'center', valign: 'middle'},
            {title: 'IoT平台', field: 'iotPackage', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Product.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Product.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加product
 */
Product.openAddProduct = function () {
    var index = layer.open({
        type: 2,
        title: '添加product',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/product/product_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看product详情
 */
Product.openProductDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'product详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/product/product_update/' + Product.seItem.productKey
        });
        this.layerIndex = index;
    }
};

/**
 * 产品属性详情
 */
Product.detail = function() {
	if (this.check()){
		var index = layer.open({
            type: 2,
            title: '产品属性',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/product/detail/' + Product.seItem.productKey
		});
		this.layerIndex = index;
	}
}

/**
 * 产品拓展属性详情
 */
Product.detailExtend = function() {
	if (this.check()){
		var index = layer.open({
            type: 2,
            title: '产品拓展属性',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/product/detailExtend/' + Product.seItem.productKey
		});
		this.layerIndex = index;
	}
}


/**
 * 删除product
 */
Product.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/product/delete", function (data) {
            Feng.success("删除成功!");
            Product.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("productId",this.seItem.productKey);
        ajax.start();
    }
};

/**
 * 查询product列表
 */
Product.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Product.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Product.initColumn();
    var table = new BSTable(Product.id, "/product/list", defaultColunms);
    table.setPaginationType("client");
    Product.table = table.init();
});
