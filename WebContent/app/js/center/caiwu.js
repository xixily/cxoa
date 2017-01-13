/**
 * Created by dengxf on 2016/12/5.
 */
var caiwu = {
    addMediaList: function (options, dom) {
        var li = $(dom.find("ul.media-list li")[0]);
        var li = $("<li class=\"media media-yellow\">");
        var media_left = $("<a class=\"media-left\" href=\"javascript:void(0)\">");
        var left_img = $("<img src=\"images/111.jpg\" alt=\"响应式图片\" width=\"132\" height=\"132\">")
        var media_body = $("<a href=\"javascript:showbxInfo($(this))\" class=\"media-body\" style=\"\">")
    },
    baoxiao: {
        status: {
            1: "1.jpg",
            2: "112.jpg",
            3: "113.jpg",
            4: "111.jpg",
            5: "112.jpg",
            6: "113.jpg",
            7: "113.jpg"
        },
        init: function () {
            $(document).on("click",'#bx_media_li a.media-body',function(event){
                event.preventDefault();//关闭默认事件
                event.stopImmediatePropagation();
                caiwu.baoxiao.showInfo($(this));
            })
//            $(document).ready(function(){
//                $('#bx_media_li a.media-body').bind('click',function(event){
//                    event.preventDefault();//关闭默认事件
//                    event.stopImmediatePropagation();
//                    caiwu.baoxiao.showInfo($(this));
//                })
//            })
        },
        find: function (data) {
            data = data ? data : {};
            if (data.value && data.type) {
                data[data.type] = data.value;
            }
//            var url = 'data/baoxiaolist.json';
            var url = 'public/caiwu/queryBaoxiao.action';
            getBufferView('caiwu/baoxiaoManager', function (view) {
                var body = $($(view).find("#" + app.getBaoxiao() + "body")[0]);
                var li = $(body.find('ul.media-list li')[0]);
                li.remove();
                if (!bodyAppend(body, app.getBaoxiao())) {
                    $.messager.alert("提示：", "添加body错误...");
                }
                ;
                body = $("#" + app.getBaoxiao() + "body");
                $.extend(data, app.getPageDefault());
//                body.data(app.getPage(), app.getPageDefault());
                body.data(app.getQueryParams(), data);
                $.post(url, data, function (result) {
                    try{
                        result = eval("(" + result + ")");
                    }catch(e){
                        try{
                            result = $(result);
                            var msg = result.find('span').html();
                            var dialogInfo = appDialog.getDefaults();
                            dialogInfo.content = msg;
                            dialogInfo.buttons[0].text = '确认';
                            dialogInfo.buttons[0].handler = function(event){
                                location.replace('/cxoa/app_index.html');
                            }
                            createDialog(dialogInfo).modal('show');
                            return false;
                        }catch(e){
                            location.replace('/cxoa/app_index.html');
                            return false;
                        }
                    }
                    if (result.success) {
                        body.data(app.getDivData(), result);//把数据和div关联
                        var rows = result.rows;
                        $('#' + app.getBaoxiao() + 'sum span[app-data="total"]').html(result.total);
                        $('#' + app.getBaoxiao() + 'sum span[app-data="thisYearTotal"]').html(result.thisYearTotal);
                        $('#' + app.getBaoxiao() + 'sum span[app-data="lastYearTotal"]').html(result.lastYearTotal);
                        if (rows && rows.length > 0) {
                            for (var i = 0; i < rows.length; i++) {
                                var row = rows[i];
                                var list = li.clone(true);
                                if (row.status && row.status != "") {
                                    list.find('[app-img]').attr('src', imgPath + caiwu.baoxiao.status[row.status]);
                                }
                                for (var a in row) {
                                    list.find('[app-data="' + a + '"]').html(row[a]);
                                }
                                list.appendTo($("#bx_media_li"));
                            }
                        }
                    } else {
                        var data = appDialog.getDefaults();
                        data.content = '获取页面信息失败~！';
                        var dialog = createDialog(appDialog.getDefaults(), function (event) {
//                            console.log('保存按钮：' + event);
                        });
                        dialog.modal('show');
                    }
                })

            })
        },
        append: function () {
            var url = 'public/caiwu/queryBaoxiao.action'
//            var url = 'data/baoxiaolist.json';
            var body = $("#" + app.getBaoxiao() + "body");
            var data = $("#" + app.getBaoxiao() + "body").data(app.getQueryParams());
            data.page +=1;
//            console.log(data);
//            var page = body.data(app.getPage());
//            data.page = (page.page+=1);
//            data.size = page.size;
            getBufferView('caiwu/baoxiaoManager', function (view) {
                var li = $($(view).find('ul.media-list li')[0]);
                $.post(url, data, function (result) {
                    try{
                        result = eval("(" + result + ")");
                    }catch(e){
                        try{
                            result = $(result);
                            var msg = result.find('span').html();
                            var dialogInfo = appDialog.getDefaults();
                            dialogInfo.content = msg;
                            dialogInfo.buttons[0].text = '确认';
                            dialogInfo.buttons[0].handler = function(event){
                                location.replace('/cxoa/app_index.html');
                            }
                            createDialog(dialogInfo).modal('show');
                            return false;
                        }catch(e){
                            location.replace('/cxoa/app_index.html');
                            return false;
                        }
                    }
                    if (result.success) {
                        if(result.rows && result.rows.length<=0){
                            $("#" + app.getBaoxiao() + "body").data(app.getQueryParams()).page -= 1;
                            $('#cw_bx_append').html("<i class=\"fa fa-drupal\"> 已经到底了~~ </i>");
                            $('#cw_bx_append').toggleClass('disabled');
                            return false;
                        }
                        var oldRows = body.data(app.getDivData()).rows;
                        $.extend(false, body.data(app.getDivData()), result);
                        body.data(app.getDivData()).rows = $.merge(oldRows, result.rows);
                        var rows = result.rows;
                        $('#' + app.getBaoxiao() + 'sum span[app-data="total"]').html(result.total);
                        $('#' + app.getBaoxiao() + 'sum span[app-data="thisYearTotal"]').html(result.thisYearTotal);
                        $('#' + app.getBaoxiao() + 'sum span[app-data="lastYearTotal"]').html(result.lastYearTotal);
                        if (rows && rows.length > 0) {
                            for (var i = 0; i < rows.length; i++) {
                                var row = rows[i];
                                var list = li.clone(true);
                                if (row.status && row.status != "") {
                                    list.find('[app-img]').attr('src', imgPath + caiwu.baoxiao.status[row.status]);
                                }
                                for (var a in row) {
                                    list.find('[app-data="' + a + '"]').html(row[a]);
                                }
                                list.appendTo($("#bx_media_li"));
                            }
                        }
                    } else {
                        var data = $.appDialog.getDefaults();
                        data.content = '获取页面信息失败~！';
                        var dialog = $.appDialog.createDialog($.appDialog.getDefaults(), function (event) {
//                            console.log('保存按钮：' + event);
                        });
                        dialog.modal('show');
                    }
                })
            })
        },
        showInfo: function(dom){
            if(dom){
//                console.log(dom);
                var id = dom.find('[app-data="id"]').html();
                var data = $("#" + app.getBaoxiao() + "body").data(app.getDivData());
                var formData = {};
                if(data && data.rows){
                    if(data.index){
                        var index = data.index[id];
                        formData = data.rows[index];
                    }else{
                        data.index = {};
                        $.each(data.rows, function(i,obj){
                            data.index[obj.id] = i;
                            if(obj.id){
                                formData = obj;
                            }
                        })
                    }
                    $('#bx_info form').form('setForm',formData,dom);
                    caiwu.baoxiao.disable('bx_info form',true);
                }
//                alert(id);
            }
            if($('#bx_list')[0].className == ""){
                $('#bx_list').addClass('col-lg-12');
                $('#bx_list').css('height','auto');
                $('#bx_info').css('height','0')
            }else{
                $('#bx_list').removeClass('col-lg-12');
                $('#bx_list').css('height','0')
                $('#bx_info').css('height','auto')
            }
        },
        disable: function(){
            var user = session.user;
            if(false){
                $('#cw_bx_user').attr('disabled','');
            }
            if(true){
                $('#cw_bx_approver').attr('disabled','');
            }
            if(true){
                $('#cw_bx_cn').attr('disabled','');
            }
        }
    }
}