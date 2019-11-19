
$(function(){
    // var option={};
    $("#roleTable").bootstrapTable({

        url:"../sys/role/list",
        pagination:true,
        sidePagination:"server",
        pageSize:2,
        search:true,
        showRefresh:true,
        toolbar:"#toolbar",
        columns:[
            {checkbox:true},
            {title:"编号",field:"roleId",sortable:true},
            {title:"角色名",field:"roleName"},
            {title:"备注",field:"remark"},
            {title:"创建者ID",field:"createUserId"},
            {title:"创建时间",field:"createTime"},
            {title:"创建时间(处理后)",field:"createTime",formatter(value,row,index){
                    //js 格式化毫秒时间
                    return new Date(value).toLocaleString();
                }}
        ]

    });
});

var app  = new Vue({
    el:"#roleApp",
    data:{
        role:{},
        title:"",
        showRoleList:true
    },
    methods:{
        add:function(){
            app.role={roleName:null,remark:null,createUserId:null};
            app.showRoleList=false;
            app.title="新增角色";

        },
        update:function(){
            //判断用户是否选中其中一行
            var rows  = $("#roleTable").bootstrapTable("getSelections");
            if(rows.length==0){
                layer.alert("请选择一行");
                return ;
            }
            if(rows.length>1){
                layer.alert("请选择一行修改");
                return ;
            }
            //得到选择的用户id
            var row = rows[0];//得到选中的这一行
            var roleId = row["roleId"];//得到这行的userId

            //alert(roleId);


            $.get("../sys/role/info/"+roleId,function(r){
                if(r.code==0){
                    app.showRoleList=false;
                    app.title="修改角色";
                    app.role = r.role;
                }

            });




        },
        del:function(){
            var rows  = $("#roleTable").bootstrapTable("getSelections");
            if(rows.length==0){
                layer.alert("请至少选择一行");
                return ;
            }
            var ids  =  new Array();
            for(var i=0;i<rows.length;i++){
                ids.push(rows[i]["roleId"])
            }

            // confirm
            //提示确认框
            layer.confirm('您确定要删除所选数据吗？', {
                btn: ['确定', '取消'] //可以无限个按钮
            }, function(index, layero){

                $.ajax({
                    type: "POST",
                    url: "../sys/role/del",
                    data: JSON.stringify(ids),
                    success : function(r) {
                        if(r.code === 0){
                            layer.alert('删除成功');
                            $('#roleTable').bootstrapTable('refresh');
                        }else{
                            layer.alert(r.msg);
                        }
                    },
                    error : function() {
                        layer.alert('服务器没有返回数据，可能服务器忙，请重试');
                    }
                });
            });


        },
        saveOrUpdate:function(event){
            //有user_id为 修改   无：新增

            var url = app.role.roleId==null?"../sys/role/save":"../sys/role/update";
            $.post(url,JSON.stringify(app.role),function(r){
                if(r.code==0){//处理成功
                    layer.alert('操作成功', function(index){
                        layer.close(index);
                        app.reload();
                    });

                }else{
                    alert(r.msg);
                }

            } );

        },
        reload:function(){
            app.showRoleList=true;
            $("#roleTable").bootstrapTable("refresh");

        }
    }
});