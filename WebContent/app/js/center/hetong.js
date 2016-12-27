/**
 * Created by dengxf on 2016/12/13.
 */
var hetong = {
    htManager:{
        zhidao: {
            coldefine: {
                cellCore: '姓名',
                cemail: '邮箱',
                gemail: '指导邮箱',
                lastYear: '2015',
                thisYear: '2016',
                yingshou: '应收'
            },
            detailFormatter: function(index, row, element){
                var html = [];
                $.each(row, function (key, value) {
                    if(typeof value == "number") value += " 元";
                    html.push('<p><b>' + hetong.htManager.zhidao.coldefine[key] + ':</b> ' + value + '</p>');
                });
                return html.join('');
            },
            footerFormatter_yingshou: function(data){
                var sum = 0;
                $.each(data,function(i,obj){
                    sum += obj.yingshou;
                })
                return '<a>'+round2(sum,2)+'</a>';
            },
            footerFormatter_2015: function(data){
                var sum = 0;
                $.each(data,function(i,obj){
                    sum += obj.lastYear;
                })
                return '<a>'+round2(sum,2)+'</a>';
            },
            footerFormatter_2016:function(data){
                var sum = 0;
                $.each(data,function(i,obj){
                    sum += obj.thisYear;
                })
                return '<a>'+ round2(sum,2)+'</a>';
            },
            onDblClickRow: function(row, $element, field){
                var url = 'hetong/cellsView';
                if(url && url!=''){
                    var cellViews = $('#cellsview');
                    var table = cellViews.find('table');
                    if(table && table.length>0){
                        table.bootstrapTable('destroy');
                    }
                    $('#guidance').hide();
                    $('#cellsview').show();
                    getBufferView(url,function(view){
                        var view = $(view);
                        var cvContainer = $('#cellsview');
                        cvContainer.html("");
                        var ul = view.find('form ul');
                        var data = $('#ht_guidance_table').bootstrapTable('getData');
                        $.each(data, function(i, obj){
                            var li = $('<li><a><span class="sr-only">'+ obj.email +'</span>' + obj.username + '</a>');
                            li.appendTo(ul);
                            if(0 === i){
                                view.find('#ht_find_cells').html('<input type="hidden" name="email" value="'+ row.email + '"/>'+ row.username);
                            }
                        })
                        view.appendTo($('#cellsview'));
                        $('#ht_cellsView_table').bootstrapTable({
                            url:'public/hetong/getAllCells.action',
                            queryParams: function(params){
                                params.email = row.email;
                                return params;
                            }
                        });
                    })
                }
                console.log(row);
                console.log($element);
                console.log(field);
            },
            onDblClickRow2: function(row, $element, field){
                var cellViews = $('#cellsview');
                var table = cellViews.find('table');
                var url = 'app/data/ud_data.json';
                table.bootstrapTable('destroy');
                $('#cellsview').show();
                $('#guidance').hide();
                table.bootstrapTable({
                    height: 568,
                    striped: true,
                    showColumns: true,
                    showRefresh: true,
                    showToggle: true,
                    search: true,
                    idField: 'email',
                    searchAlign: 'right',
                    buttonsAlign: 'right',
                    toolbar: '#ht_cells_toolbar',
                    detailView: true,
                    url: url,
                    queryParams: row,
                    detailFormatter: hetong.htManager.zhidao.detailFormatter,
                    onLoadSuccess: function(data){
//                        $('#cellsview').show();
//                        $('#guidance').hide();
                        return data;
                    },
                    columns:[
                        {
                            field:'dId',
                            title:'自动编号',
                            sortable: true,
                            visible: false,
                            searchable: false
                        },{
                            field:'dname',
                            title:'单位名称',
                            sortable: true
                        },{
                            field:'email',
                            title:'负责人邮箱',
                            sortable: true,
                            visible: false,
                        },{
                            field:'cemail',
                            title:'细胞核邮箱',
                            sortable: true,
                            visible: false,
                        },{
                            field:'charger',
                            title:'负责人',
                            sortable: true,
                        },{
                            field:'thisYear',
                            title:'2015 收款（元）',
                            sortable: true,
                        },{
                            field:'lastYear',
                            title:'2016 收款（元）',
                            sortable: true,
                        },{
                            field:'yingshou',
                            title:'应收款（元）',
                            sortable: true,
                        }
                    ]
                });
            }
        },
        testTable: function(){
            $('#ht_customer_list').DataTable({
                responsive: true,
                language: {
                    lengthMenu: '显示 _MENU_',
                    zeroRecords: '抱歉，没有找到任何记录',
                    info: '当前第 _PAGE_ 页，共 _PAGES_ 页',
                    infoEmpty: '没有记录',
                    infoFiltered: '(从 _MAX_ 中过滤记录)',
                    search: '查找',
                    paginate: {
                        sPrevious: '前一页',
                        sNext: '下一页'
                    }
                }
            });
        },
        findCharger: function(id,$table){
            //TODO 考虑一下在server端分页有没有必要

        },
        prepareParams: function(params){
            params = params ? params : {};
            $('#ht_customer_toolbar').find('input[name]').each(function () {
                params[$(this).attr('name')] = $(this).val();
            });
            return params;
        },
        responseHandler: function(){

        },
        queryCustomer: function(){

        },

    }
}