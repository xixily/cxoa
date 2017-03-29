/**
 * Created by dengxf on 2017/3/27.
 */
(function($){
//    var init = function(dom,options){
//        var jq = $(dom);
//        jq.addClass("display","none");
//        var panel = undefined;
//    }

    /**
     * 定义BootstrapChat
     * @param dom
     * @param options
     * @constructor
     */
    var BootstrapChat = function(dom, options){
        this.options = options || {};
        this.$jq = $(dom);
        this.$jq_ = this.$jq.clone();
        this.$jq.addClass("display","none");
        this.timeoutId_ = 0;
        this.timeoutFooter_ = 0;
    };

    /**
     * BootstrapChat 默认属性
     * @type {{}}
     */
    BootstrapChat.DEFAULTS = {
        bsClass:  'chat-panel panel',
        class: 'panel-default',
        style: undefined,
        head:{
            class:'panel-heading',
            iconCls: 'fa fa-comments fa-fw',
            text: 'Chat',
            style: undefined
        },
        body:{
            class: 'panel-body',
            style:undefined
        },
        ul:{
            class: 'chat',
            li_lt:{
                class: 'left clearfix',
                img:{
                    src: 'app/images/blue_60.png',
                    alt: 'me',
                    class: 'img-circle'
                }
            },
            li_rt:{
                class: 'right clearfix',
                img:{
                    src: 'app/images/blue_60.png',
                    alt: 'me',
                    class: 'img-circle'
                }
            }
        },
        date_format:'yyyy-MM-dd HH:mm:ss',
        app_data:{
            _head: 'Chat',
            _username: 'username',
            _time: 'time',
            _comments: 'comments'
        },
        sender:{
            txt: '发送',
            placeholder: '输入回复内容...',
            onClick: this._sendMessages
        }
    };

    BootstrapChat.prototype._addMessages = function(msg, ifself){
        if(isEmpty(msg)){
            return false;
        }
        var li = $('<li>').addClass(ifself ? 'left clearfix':'right clearfix');
        li.append($('<span>').append($('<img>').attr('src',msg.img.src).attr('alt',msg.img.alt).addClass(msg.img.class)).addClass(ifself ? 'chat-img pull-left':'chat-img pull-right'));
        li.append($('<div>').addClass('chat-body clearfix').append(
                $('<div>').addClass('header').append($('<strong>').addClass(ifself ? 'primary-font':'pull-right primary-font').text(msg.sender)).
                        append($('<small>').addClass(ifself ? 'pull-right text-muted':' text-muted').append($('<i>').addClass('fa fa-clock-o fa-fw')).append($('<span>').text(msg.date)))
        ).append($('<p>').text(msg.msg)))
        try{
            li.appendTo(this.$jq_);
            this.$jq_.find(".panel-body").scrollTop(this.$jq_.find(".panel-body")[0].scrollHeight);
        }catch(e){
            console.log("插入消息失败或滚动消息框失败：%o",e);
        }
        return li ;
    }

    BootstrapChat.prototype._addSysMessages = function(msg){
        if(msg.isEmpty()){
            return false;
        }
        var li = $('<li>').addClass( 'left clearfix');
        li.append($('<div>').addClass('chat-body clearfix').append(
                $('<div>').addClass('header').append($('<strong>').addClass( 'primary-font').html('<span style="color:red">[系统消息]:</span>')).
                        append($('<small>').addClass(ifself ? 'pull-right text-muted':' text-muted').append($('<i>').addClass('fa fa-clock-o fa-fw')).append($('<span>').text(msg.date)))
        ).append($('<p>').text(msg.msg)))
        try{
            li.appendTo(this.$jq_);
            this.$jq_.find(".panel-body").scrollTop(this.$jq_.find(".panel-body")[0].scrollHeight);
        }catch(e){
            console.log("插入消息失败或滚动消息框失败：%o",e);
        }
        return li ;
    }

    BootstrapChat.prototype._sendMessages = function(msg){
        if(!msg.id && !msg.msg_type) return false;
        this._addMessages(msg,true);
        delete msg.img;
        this.websocket.send(JSON.stringify(msg));
    }

    /**
     * 初始化一个聊天窗口
     * @param options
     */
    BootstrapChat.prototype._init = function(options){
        options = $.extend(true,{}, this.DEFAULTS, options);
        this.$jq_.attr('class','');
        this.$jq_.addClass(options.bsClass).addClass(options.class);
        //head
        var head = $('<div>').addClass(options.head.class).append($('<i>').addClass(options.head.iconCls)).append($('<span>').text(options.head.text));
        if(options.head && options.head.style){
            head.attr('style',options.head.style);
        }
        this.$jq_.append(head);
        //body
        var body = $('<div>').addClass(options.body.class);
        if(options.body && options.body.style){
            body.attr("style", options.body.style);
        }
        var ul = $('<ul>').addClass(options.ul.class)
        ul.appendTo(body);
        this.$jq_.append(body);
        //footer
        var footer = $('<div>').addClass('panel-footer');
        var form = $('<form>');
        var input_group = $('<div>').addClass('input-group')
                .append($('<input>').addClass('form-control input-sm').attr('type','text').attr('name',this.DEFAULTS.app_data._comments).attr('placeholder',this.DEFAULTS.sender.placeholder))
                .append($('<span class="input-group-btn">'))
                .append($('<button role="button" class="btn btn-warning btn-sm" app-action="caiwu.baoxiaoCheck.sendMessager">发送</button>'));
        this.$jq_.append(input_group);
        this.$jq.after(this.$jq_);
        this.$jq.remove();
        this.$jq_.data("bootstrap.chat",this.$jq_);
    };

    /**
     * 发送消息
     * @private
     */
    BootstrapChat.prototype._sendMessages = function(){

    }

    $.fn.bootstrapChat = function(options){
        var value;
        this.each(function(){
            var $this = $(this),
                    chat = $this.data('bootstrap.chat') ? $this.data('bootstrap.chat') : new BootstrapTable(this, options),
                    options = $.extend({}, BootstrapChat.DEFAULTS, $this.add(),
                            typeof options === 'object' && options);
            if(typeof options === 'string'){
                if(chat[options] && typeof chat[options] === 'function'){
                    value = chat[options].apply(chat);
                }else{
                    throw new Error("Unknow method: " + options);
                }
            }
        })
    }

}($))