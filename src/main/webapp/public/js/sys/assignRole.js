var  app  = new Vue({
    el:"#assignRole",
    data:{
        users:[],
        roles:[],
    },
    methods:{
        getAllUsers:function(){

            $.get("/sys/user/userList",function(r){
                app.users =  r.userList;
            });
        },

        findUserNotHasRole:function () {
            var userId=$("#users option:checked").val();
            //alert(userId);
            $.get("../sys/user/notHasRole/"+userId,function (r) {
                //alert(r.roles);
                app.roles=r.roles;
            });
        },

        assignRole:function () {
            var userId=$("#users option:checked").val();
            var roleId=$("#hasRole option:checked").val();
            $.get("../sys/role/giveRole?userId="+userId+"&roleId="+roleId,function (r) {
                if(r.code === 0){
                    location.reload();
                }else{
                    layer.alert(r.msg);
                }
            });
        }
    },

    created:function(){
        this.getAllUsers();

    }

});