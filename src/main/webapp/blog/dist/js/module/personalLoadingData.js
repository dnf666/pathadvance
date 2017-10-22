
define(['lib/jquery', 'util/funcTpl'], function($,funcTpl) {
     
    var personalLoadingData = {
        init:function(){
        //加载blog列表
         personalLoadingData._load();
        },

        //加载所有blog列表
        _load(){

            //判断用户的访问权限
            var userName=localStorage.getItem("userName");
            var masterName=localStorage.getItem("masterName");
            var url;
            var stat;
            $.ajax({
                dataType:"json",
                url:"/pms/role/selectRoleByConbinationKey.do",
                type:"post",
                data:{
                    "masterId":masterName,
                    "userId":userName
                },
                success:function(json){
                    stat=json.status;
                }
            })
            if(stat==1){meUrl="/pms/blog/selectOwnAll.do"}
            else{meUrl="/pms/blog/selectOtherAll.do"};


            //加载被访问者的blog列表
            var i=0;
            var all=3;
            var ajax=function(){
            $.ajax({
                dataType:"json",
                url:meUrl,
                type:"post",
                data:{
                    "userName":userName
                },
                success:function(json){
                    for(i;i<all;i+=1){
                    if (i<json.length) {

                            console.log(i);
                            var card=" ";
                            card.className='card';
                            card=
                                '<div class="card-body card-content">'+
                                '<h4 class="card-title text-center"><a class="title"></a></h4>'+
                                '<p class="card-text text-left"></p>'+
                                '<p class="information text-left">Posted by <a class="author"></a> <span class="time">On Tuly 26,2017 </span></p>'+
                                '<hr>'+
                                '</div>',
                                $(".content").eq(0).before(card);
                            $(".title").eq(i).html(json[i].title);
                            $(".card-text").eq(i).html(json[i].context);
                            $(".author").eq(i).html(json[i].createBy);
                            $(".time").eq(i).html(json[i].createTime);
                            $(".title").eq(i).index=i;
                            $(".title").eq(i).click(function(){
                                localStorage.setItem("id",json[i].id

                                );
                                localStorage.setItem("userName",json[i].createBy);
                                console.log(json[i].id

                                );
                                window.location.href="blogpaper.html";
                            })


                            $(".author").eq(i).index=i;
                            $(".author").eq(i).click(function(){
                                localStorage.setItem("userName",json.createBy);
                                console.log(json[i].createBy);
                                window.location.href="blogpersonal.html";
                            })


                        }
                    else{
                        i++;
                        $(".tip").eq(0).css("display","none");
                        $(".nodata").show().html("别滚动了，已经到底了。。。");
                    }
                        //i++;
                    }
                    // else{i++};

  
                                
                },
                error:function(json){
                    console.log("error");
                }
            }) 
            }
            //调用ajax
            ajax();

            //下拉加载blog
            var  lala=function(){
                   var winH = $(window).height(); //页面可视区域高度 
                   $(window).scroll(function() {
                        var pageH = $(document.body).height();
                        var scrollT = $(window).scrollTop(); //滚动条top 
                        var aa = (pageH - winH - scrollT) / winH;
                        if (aa < 0.001) {
                            setTimeout(function(){
                            all=all+3;
                            //console.log(i);
                             //console.log(all);
                            // setTimeout(function(){
                            //     load();
                            // },(2000));
                            ajax();                                    
                            },(1000))

                        }
                    })
               } 
            lala();                 
        },


    }
    return personalLoadingData.init;
});


