$.messager={
    show:function(obj){

    },
    alert:function(title,msg,icon,callback){
        var msgInfo = typeof title=="object"?title:{title:title,msg:msg,icon:icon,fn:callback};
    }
}
$.showDialog = function(){
    var dlg = $("<div class=\"dialog\"></div>").appendTo("body");
}