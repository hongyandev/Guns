
var fileArr = new Array();
jQuery(function($){
    /*var file = '<%=request.getAttribute("item")%>';
    console.log(file);*/
    Dropzone.autoDiscover = false;
    Dropzone.options.myAwesomeDropzone = false;
    try {
        $(".dropzone").dropzone({
            url:Feng.ctxPath + '/product/uploadProductFile/',
            method:"post",
            paramName:"file",
            autoProcessQueue:true,//自动上传
            maxFilesize:1024, // MB
            uploadMultiple:false,
            parallelUploads:10,
            acceptedFiles:".rar,.zip,.7z,.doc,.docx,.xls,.xlsx,.ppt,.pptx,.txt,.pdf",
            dictInvalidFileType:"支持的文件类型是.rar,.zip,.7z,.doc,.docx,.ppt,.pptx,.txt,.pdf",
            addRemoveLinks:true,
            //    maxFiles：   //指的是上传目录下的最大文件数
            dictRemoveFile:"移除文件",
            dictUploadCanceled:"取消",
            dictCancelUploadConfirmation:"取消上传该文件?",
            dictDefaultMessage:
                "<span class='bigger-150 bolder'><i class='icon-caret-right red'></i>拖动文件</span>上传\
                <span class='smaller-80 gre'>(或者点击上传)</span> <br /> \
                <i class='upload-icon icon-cloud-upload blue icon-3x'></i>",
            /*dictDefaultMessage:"上传文件",*/
            dictResponseError:"文件上传失败!",
            dictFileTooBig:"文件过大,上传失败!",
            //change the previewTemplate to use Bootstrap progress bars
            previewTemplate: "<div class=\"dz-preview dz-file-preview\">\n  <div class=\"dz-details\">\n    <div class=\"dz-filename\"><span data-dz-name></span></div>\n    <div class=\"dz-size\" data-dz-size></div>\n   </div>\n  <div class=\"progress progress-small progress-striped active\"><div class=\"progress-bar progress-bar-success\" data-dz-uploadprogress></div></div>\n  <div class=\"dz-success-mark\"><span></span></div>\n  <div class=\"dz-error-mark\"><span></span></div>\n  <div class=\"dz-error-message\"><span data-dz-errormessage></span></div>\n</div>",
            init:function(){
                if($("#fileRealPath").val()){
                    var mockFile = { name: $("#name").val(), size: $("#fileSize").val(),type:$("#contentType").val()};
                    this.addFile.call(this, mockFile);
                    this.options.thumbnail.call(this, mockFile, $("#fileRealPath").val());
                }

                this.on("addedfile",function(file,data) {
                    fileArr.push(file.upload.uuid);
                    //解决点击时重复发送请求
                    $(".dz-remove").each(function(index) {
                        if(!$(".dz-remove:eq(" + index + ")").attr("id")) {
                            $(".dz-remove:eq(" + index + ")").attr("id",fileArr[index]);
                        }
                    })

                });
                this.on("success",function(file,data){
                    //var myDropzone = this;

                    console.log("File " + file.name + "uploaded");
                    $("#" + file.upload.uuid).click(function() {
                        var productId = $("#productKey").val();
                        var operation = function () {
                            $.ajax({
                                type:"POST",
                                url:"",
                                data:{"productKey":productId},
                                dataType:"json",
                                success:function(data){
                                    // this.removeFile(file);
                                }
                            })
                        };
                        Feng.confirm("是否刪除该产品的图片?", operation);

                    })

                });
                this.on("sending",function(file, xhr, formData){
                    formData.append('productId', $('#productKey').val());
                });
                this.on("removedfile",function(file,data){

                });

                this.on("canceled",function(file,data) {
                    // alert("canceled");
                    this.removeFile(file);
                });

                this.on("error",function(file,data){

                });
                this.on("complete",function(file) {
                   /* if(file.status == "canceled" || file.status == "error") {
                        var productId = $("#productKey").val();
                        setTimeout(function() {
                            $.ajax({
                                type:"POST",
                                url:Feng.ctxPath + '/product/uploadProductFile/',
                                data:{"productId":productId},
                                dataType:"json",
                                success:function(data){
                                    if(data == "success") {
                                        // alert("删除成功");
                                        Feng.alert("删除成功")
                                    }
                                },
                                error:function(ajax) {
                                    Feng.alert(ajax.status);
                                }
                            })
                        },2000);
                    }*/
                });

            }

        });
    } catch(e) {
        console.log(e);
        alert('Dropzone.js does not support older browsers!');
    }
});
