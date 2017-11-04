
define(['lib/jquery', 'util/funcTpl'], function($,funcTpl) {
     
    var blogitem = {
        init:function(){
            $("#index").before(funcTpl(blogitem.tpl));
            $("#index").append(funcTpl(blogitem.putout));

            // $(".man").eq(0).css("background","white");
            // alert($(".man").eq(0).css("background"));
           // 查看我的blog
           blogitem._myblog();
           //写博客
           blogitem._write();
           //权限管理
           blogitem._manage();
           //查询好友
           blogitem._friend();
        },
        _myblog(){
            $(".myblog")[0].onclick=function(){
                var masterName=localStorage.getItem("masterName");
                localStorage.setItem("userName",masterName);
                console.log(masterName);
                window.location.href="blogpersonal.html";
            }
        },
        _write(){
            $(".write")[0].onclick=function(){
                localStorage.setItem("title","");
                localStorage.setItem("name","");
                localStorage.setItem("time","");
                localStorage.setItem("context","");
                localStorage.setItem("identify","add");
                window.location.href="blogedit.html";
            }
        },
        _manage(){
            var management=$(".management")[0];
            //储存五个人的状态码
            var status=[];
            management.onclick=function(){
                $(".showList")[0].style.display="none";
                var nameList=[];
                $("#name").val("");
                //根据名字查人
                var Search=function(){
                    $("#Search")[0].onclick=function(){
                        var name=$("#name").val();
                        $.ajax({
                            dataType:"json",
                            Type:"post",
                            url:"http://rapapi.org/mockjsdata/26117/pms/role/findUserBySearching.do",
                            data:{
                                "name":name,
                            },
                            success:function(json){
                                $(".showList")[0].style.display="block";
                                for(var i=0;i<5;i++){
                                    nameList[i]=json[0].object[0].userName;  
                                    $(".trust")[i].innerHTML=json[0].object[0].userName;                        
                                }
                                    //查看五个人是否有权限
                                    var masterName=localStorage.getItem("masterName");
                                    var ajax=function(){
                                        
                                            $.ajax({
                                                dataType:"json",
                                                Type:"post",
                                                url:"/pms/role/selectRoleByConbinationKey.do",
                                                data:{
                                                    "masterId":masterName,
                                                    "userId":nameList,
                                                },
                                                success:function(json){
                                                    for(var i=0;i<5;i++){
                                                        status[i]=json.status;
                                                    }

                                                    //根据是否有权限设置相应的按钮                                                    //根据是否有权限设置相应的按钮
                                                    var showButton=function(){
                                                        for(var i=0;i<5;i++){
                                                            if(status[i]==1){
                                                                $(".listChange")[i].innerHTML="撤销";                           
                                                            }else{
                                                                $(".listChange")[i].innerHTML="给予";
                                                            }

                                                        }
                                                    }

                                                    //根据是否有权限设置相应的按钮
                                                    showButton(); 
                                                    
                                                    //更改权限
                                                    var changeManage=function(){
                                                        for(var i=0;i<5;i++){
                                                            $(".listChange")[i].index=i;
                                                            $(".listChange")[i].onclick=function(){
                                                                var index=this.index;
                                                                if(status[index]==0){
                                                                    $.ajax({
                                                                            dataType:"json",
                                                                            Type:"post",
                                                                            url:"/pms/role/insertRole.do",
                                                                            data:{
                                                                                    "masterId":masterName,
                                                                                    "userId":nameList[index],
                                                                            },
                                                                            success:function(){
                                                                                    alert("添加成功!");
                                                                                    status[index]=1;
                                                                                    $(".listChange")[index].innerHTML="撤销";
                                                                            },
                                                                            error:function(){
                                                                                    console.log("添加失败!");
                                                                            }
                                                                    })
                                                                }else{
                                                                    $.ajax({
                                                                            dataType:"json",
                                                                            Type:"post",
                                                                            url:"/pms/role/deleteRole.do",
                                                                            data:{
                                                                                    "masterId":masterName,
                                                                                    "userId":nameList[index],
                                                                            },
                                                                            success:function(){
                                                                                    alert("删除成功!");
                                                                                    status[index]=0;
                                                                                    $(".listChange")[index].innerHTML="给予";
                                                                            },
                                                                            error:function(){
                                                                                    console.log("删除失败!");
                                                                            }
                                                                    })

                                                                }
                                                            }

                                                        
                                                
                                                    }
                                                    }      
                                                    //更改权限
                                                    changeManage();

                                                },
                                                error:function(json){
                                                    alert("error!");
                                                }
                                            })                        
                                    }

                                    //查看五个人是否有权限
                                    ajax();

                            },
                            error:function(json){
                                console.log("error!");
                            }
                        })

                    }
                }

                //根据名字查人
                Search();







                // var a=[0,0,0,0,0];
                // for(var i=0;i<$(".man").length;i++){
                //      $(".man").eq(i).css("background","white");
                // }
                // for(var i=0;i<$(".man").length;i++){
                //     $(".man")[i].index=i;
                //           $(".man")[i].onclick=function(){
                //             k=this.index;
                //             if(a[k]==0){
                //                 $(".man").eq(k).css("background","#5CB85C");
                //                 a[k]=1;
                //             }else{
                //                 $(".man").eq(k).css("background","white");
                //                 a[k]=0;
                //             }
                //         }
                // }  
            }
        },
        _friend(){
            //获取登陆用户名
            var masterName=localStorage.getItem("masterName");
            //点击好友按钮时
            $(".friend").eq(0).click(function(){
                $(".showfriend")[0].style.display="none";
                //存储状态码
                var status=[];
                var listName=[];
                $("#friendButton").eq(0).click(function(){
                    $(".showfriend")[0].style.display="block";
                    var friendName=$("#friendName").val();
                    console.log(friendName);
                    $.ajax({
                        dataType:"json",
                        Type:"post",
                        url:"http://rapapi.org/mockjsdata/26117/pms/role/findUserBySearching.do",
                        data:{
                            "userName":friendName,
                        },
                        success:function(json){
                            for(var i=0;i<5;i++){
                                listName[i]=json[0].object[0].userName;
                                $(".friendList a")[i].innerHTML=listName[i];
                            }
                            for(var i=0;i<5;i++){
                                $(".friendList")[i].index=i;
                                $(".friendList")[i].onclick=function(){
                                var index=this.index;
                                localStorage.setItem("userName",listName[index]);

                                window.location.href="blogpersonal.html";
                            }
                            }

                        },
                        error:function(json){
                            console.log("error!");
                        }
                    })

                })
            })
        },
        tpl:function(){
            /*  
            <style type="text/css">
                .man{
                    cursor:pointer;
                    background-color:white;
                }
                .friendList a:hover{
                    text-decoration:underline;
                }
            </style>

        <div class="collapse navbar-collapse nav-head" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right nav-list">
                <li>
                    <a href="blog.html">博客首页</a>
                </li>
                
                <li>
                    <a class="myblog" style="cursor:pointer;">我的博客</a>
                </li>
                
                <li>
                    <a  class="write" style="cursor:pointer">写博客</a>
                </li>
                
                <li class="management">
                      <a href="#" data-toggle="modal" data-target="#about-modal">权限设置</a>             
                </li>
                <li class="friend" style="cursor:pointer;">
                    <a  data-toggle="modal" data-target="#about-modal1">好友</a>
                </li>
                <li>
                     <a href="index.html" style="position:relative;font-size:16px;color:white;">退出</a>
                </li>
            </ul>
    </div>
             */
        },

        putout:function(){
            /*
                <!-- 权限设置 -->
                <div class="modal fade" id="about-modal" tabindex="-1" role="dialog" aria-labelledby="modal-label"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <div class="modal-header text-center">
                                <button type="button" class="close" data-dismiss="modal"><span
                                        aria-hidden="true">&times;</span><span class="sr-only">关闭</span></button>
                                <h4 class="modal-title" id="modal-label">权限设置</h4>
                            </div>

                            <div class="modal-body text-center">
                                <nav class="navbar navbar-light bg-light" style="margin:0px;">
                                      <form class="form-inline" style="margin-bottom:0;">
                                        <input class="form-control mr-sm-2 " type="text" id="name" placeholder="Search" aria-label="Search"  style="position:relative;width:70%;margin:0px auto;margin-bottom:10px;">
                                        <button class="btn btn-success my-2 my-sm-0" id="Search" type="button" style="position:relative;margin-bottom:10px;">Search</button>
                                      </form>
                                </nav>
                                <ul class="list-group showList" style="position:relative;width:80%;margin:0px auto;">
                                  <li class="list-group-item List" style="position:relative"><span class="trust">Cras justo odio</span><button type="button" class="btn btn-info listChange" style="position:absolute;right:2px;top:2.5px;"></button></li>
                                  <li class="list-group-item List" style="position:relative"><span class="trust">Cras justo odio</span><button type="button" class="btn btn-info listChange" style="position:absolute;right:2px;top:2.5px;"></button></li>
                                  <li class="list-group-item List" style="position:relative"><span class="trust">Cras justo odio</span><button type="button" class="btn btn-info listChange" style="position:absolute;right:2px;top:2.5px;"></button></li>
                                  <li class="list-group-item List" style="position:relative"><span class="trust">Cras justo odio</span><button type="button" class="btn btn-info listChange" style="position:absolute;right:2px;top:2.5px;"></button></li>
                                  <li class="list-group-item List" style="position:relative"><span class="trust">Cras justo odio</span><button type="button" class="btn btn-info listChange" style="position:absolute;right:2px;top:2.5px;"></button></li>
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-info" data-dismiss="modal">确定</button>
                            </div>

                        </div>
                    </div>
                </div>




                <!-- 查询好友 -->
                <div class="modal fade" id="about-modal1" tabindex="-1" role="dialog" aria-labelledby="modal-label"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <div class="modal-header text-center">
                                <button type="button" class="close" data-dismiss="modal"><span
                                        aria-hidden="true">&times;</span><span class="sr-only">关闭</span></button>
                                <h4 class="modal-title" id="modal-label">查询好友</h4>
                            </div>

                            <div class="modal-body text-center">
                                <nav class="navbar navbar-light bg-light" style="margin:0px;">
                                      <form class="form-inline" style="margin-bottom:0;">
                                        <input class="form-control mr-sm-2 " id="friendName" type="text" placeholder="Search" aria-label="Search"  style="position:relative;width:70%;margin:0px auto;margin-bottom:10px;">
                                        <button class="btn btn-success my-2 my-sm-0"  id="friendButton" type="button" style="position:relative;margin-bottom:10px;">Search</button>
                                      </form>
                                </nav>
                                <ul class="list-group showfriend" style="position:relative;width:80%;margin:0px auto;">
                                  <li class="list-group-item friendList"><a style="cursor:pointer"></a></li>
                                  <li class="list-group-item friendList"><a style="cursor:pointer"></a></li>
                                  <li class="list-group-item friendList"><a style="cursor:pointer"></a></li>
                                  <li class="list-group-item friendList"><a style="cursor:pointer"></a></li>
                                  <li class="list-group-item friendList"><a style="cursor:pointer"></a></li>
                                </ul>    
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-info" data-dismiss="modal">确定</button>
                            </div>

                        </div>
                    </div>
                </div>
             */
        },
    };
    return blogitem.init;
});


