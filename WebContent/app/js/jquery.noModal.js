/**
 * Created by dengxf on 2016/12/2.
 */
// jquery.noModal.js
/*!
 * 非模态层，可拖拽
 * wangbt
 */
;(function($) {
    'usr strict';

    var sprintf = function(str) {
        var args = arguments,
                flag = true,
                i = 1;

        str = str.replace(/%s/g, function() {
            var arg = args[i++];

            if (typeof arg === 'undefined') {
                flag = false;
                return '';
            }
            return arg;
        });
        return flag ? str : '';
    };

    /*****NoModal Begin****/
    var NoModal = function(element, options) {
        this.$content = $(options["content"]);
        this.$content_ = this.$content.clone();
        this.$option = options;
        this.init();
    };

    NoModal.DEFAULTS = {
        id: "noModal",
        zIndex: 1024,
        title: "",
        content: "",
        width: 500,
        height: 400,
        isDragable: true,
        isHideBut: false,
        isDisabled: false,
        singleButtons: [{
            id: "closeBtnId",
            name: "关闭",
            order: 1,
            halign: "right",
            isDisabled: false,
            params: 'undefined',
            callback: function(btnObj) {
                return true;
            }
        }, {
            id: "surceBtnId",
            name: "确定",
            order: 2,
            halign: "right",
            isDisabled: false,
            params: 'undefined',
            callback: function(btnObj) {
                return true;
            }
        }]

    };

    NoModal.prototype.init = function() {
        this.initModalTitle();
        this.initModalContent();
        var $that = this;
        this.initModalFooter();

        this.initModal();
    };

    NoModal.prototype.initModalTitle = function() {
        var w = this.$option.width - 50;
        this.$titleObj = $([
            '<div class="modal-title">',
            '<div class="title-text" style="width: ' + w + 'px;">' + this.$option.title + '</div>',
            '<div class="title-close"><button title="关闭">X</button></div>',
            '</div>'
        ].join(''));
    };

    NoModal.prototype.initModalContent = function() {
        var heightCss = "";
        if(!this.$option.isHideBut) {
            heightCss = "height: " + Number(this.$option.height - 100) + "px;"
        } else {
            heightCss = "height: " + Number(this.$option.height - 55) + "px;"
        }
        var style = sprintf("style='%s'", heightCss);
        this.$contentObj = $([
            '<div class="modal-content" ' + style + '>',
            '<div class="content-body">',
            '</div>',
            '</div>'
        ].join(''));
        this.$content.appendTo(this.$contentObj.find(".content-body"));
    };

    NoModal.prototype.initModalFooter = function() {
        var $that = this;
        this.$footerObj = $(['<div class="modal-footer">', '</div>'].join(''));
        /*this.$footerObj.css({"height": "50px"});*/
        var $source = $('<div></div>');
        var btns = $that.$option.singleButtons;
        if (btns instanceof Array && btns.length > 0) {
            btns.sort(function(obj1, obj2) {
                var order1 = typeof obj1['order'] == 'undefined' ? 0 : obj1['order'];
                var order2 = typeof obj2['order'] == 'undefined' ? 0 : obj2['order'];
                if (order1 < order2) {
                    return -1;
                } else if (order1 > order2) {
                    return 1;
                }
                return 0;
                //等同上面效果
                //return obj1['order'] - obj2['order'];
            });
            $.each(btns, function(k, v) {
                var $btn = $('<button type="button"></button>');
                $btn.attr("id", $that.$option.id + '_' + v['id']);
                $btn.text(v['name']);
                $btn.addClass('btn btn-info');
                if (v['halign'] != 'undefined'){
                    $btn.css({
                        "float": v['halign'],
                        "margin-top": "5px",
                        "margin-right": "10px"
                    });
                }
                $btn.attr("disabled", v['isDisabled']);
                $btn.click("click", function() {
                    var isHand = true;
                    if (v["callback"]) {
                        isHand = v.callback(v.params);
                    }
                    if (isHand) {
                        $that.$modal.remove();
                    }
                });

                $btn.appendTo($source)
            });
        }
        $source.appendTo(this.$footerObj);
    };

    NoModal.prototype.initModal = function() {
        var $that = this;
        var marginLeft = -(this.$option.width / 2),
                marginTop = -(this.$option.height / 2);
        this.$modal = null;
        if($(window.document).find("#" + this.$option.id).length <= 0){
            this.$modal = $("<div class='no-modal' no-modal='nomodal'></div>");
            this.$modal.attr("id", this.$option.id);
            this.$modal.css({
                'margin-left': marginLeft + 'px',
                "margin-top": marginTop + "px",
                "width": $that.$option.width,
                "height": $that.$option.height,
                "position": "fixed"
            });
        }else{
            this.$modal = $("#"+this.$option.id);
        }

        this.$modal.html("");

        this.$titleObj.appendTo(this.$modal);
        this.$contentObj.appendTo(this.$modal);
        if(!this.$option.isHideBut) {
            this.$footerObj.appendTo(this.$modal);
        }
        this.$modal.appendTo($("body"));//isDragable
        this.$modal.fadeIn(2000);

        if(this.$option.isDragable) {
            this.$modal.draggable({
                handle: ".modal-title, .modal-footer",
                containment: "html",
                scroll: false,
                cursor: "move"
            });
        }
        this.$contentObj.find(".content-body").mCustomScrollbar({
            theme: "minimal-dark"
        });
        this.$titleObj.find(".title-close button").off("click").on("click", function(){
            $that.$modal.remove();
        });
    };

    NoModal.prototype.showModal = function(){

    };

    NoModal.prototype.hiddenModal = function(){

    };

    /***NoModal End***/

    var allMethods = ['showModal','hiddenModal'];

    $.fn.noModal = function(option, _query){
        var value;
        this.each(function(){
            var $this = $(this),
                    data = $this.data('no.modal'),
                    options = $.extend({}, NoModal.DEFAULTS, typeof option == "object" && option);
            if(typeof option == "string"){
                if($.isArray(option, allMethods) < 0){
                    throw new Error("Unknown method: " + option);
                }

                if(!data) return;

                value = data[option](_query);
                if(option == 'destroy'){
                    $this.removeData("no.modal");
                }
            }
            /*if(!data){
             $this.data("no.modal", new NoModal(this, options));
             }*/
            $this.data("no.modal", new NoModal(this, options));
        });
        return typeof value === 'undefined' ? this : value;
    };

    $.fn.noModal.Constructor = NoModal;
    $.fn.noModal.defaults = NoModal.DEFAULTS;
    $.fn.noModal.methods = allMethods;


    jQuery.extend({
        noModal: function(option, _query){
            $(window).noModal(option, _query);
        }
    });

    $(function(){
        $("[no-modal='nomodal']").noModal();
    });
})(jQuery);