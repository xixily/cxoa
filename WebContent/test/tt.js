/**
 * Created by dengxf on 2016/11/4.
 */
function tt(data,parentId){
    if(data.rows){
        $.each(data.rows,function(n,obj){
            session.jiagou.push(obj.fourthLevel+'_'+obj.id + '_'+ obj.level);
            if(obj.level == 4){
                obj.iconCls = 'icon-man';
            }else{
                obj.iconCls = 'icon-organisation';
            }
        })
    }else if(data.level){
        if(data.level == 4){
            data.iconCls = 'icon-man';
        }else{
            data.iconCls = 'icon-organisation';
        }
    }
    return data;
}