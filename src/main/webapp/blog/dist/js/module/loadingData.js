
define(['lib/jquery', 'util/funcTpl'], function($,funcTpl) {
     
    var loadingData = {
        init:function(){
                var load=function(){
                    var i=0;
                    $.ajax({
                        dataType:"json",
                        url:"/pms/blog/selectAll.do",
                        type:"GET",
                        success:function(json){
                            if (json.title!==undefined) {
                                for(i;i<1;i++){
                                    var card=document.createElement('div');
                                        card.className='card';
                                        card.innerHTML='<div class="card-body card-content">'+
                                                    '<h4 class="card-title text-center"><a href="blogpaper.html" class="title"></a></h4>'+
                                                    '<p class="card-text text-left">ladkjafdsaf</p>'+
                                                    '<p class="information text-left">Posted by <a href="blogpersonal.html" class="author">Huzhongyuan</a> <span class="time">On Tuly 26,2017 </span></p>'+
                                                    '<hr>'+
                                                       '</div>',
                                    $(".content")[0].append(card);
                                    $(".title").eq(i).html(json.title);
                                    $(".card-text").eq(i).html(json.context);
                                    $(".author").eq(i).html(json.createBy); 
                                    $(".time").eq(i).html(json.createTime);                 
                                }
                                i++;                            
                            }else{i++};
                        },
                        error:function(json){
                            console.log("error");
                        }
                    })                     
                }

                //加载所有blog
                load();
               
        },
    }
    return loadingData.init;
});


