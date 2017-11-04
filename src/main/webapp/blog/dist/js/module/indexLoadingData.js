
define(['lib/jquery', 'util/funcTpl'], function($,funcTpl) {
     
    var loadingData = {
        init:function(){
            //加载所有blog
            loadingData._load();  
            // alert($(document).height());
            // alert($(window).height())
        },
        _load(){
            var i=0;
            var all=3;
            var load=function(){
                    $.ajax({
                        dataType:"json",
                        url:"/pms/blog/selectAll.do",
                        type:"GET",
                        success:function(json){
                            if (i<json.length) {
                                for(i;json[i]&&i<all;i++){
                                    console.log(i);
                                    var card=" ";
                                        card.className='card';
                                        card=
                                            '<div class="card-body card-content">'+
                                            '<h4 class="card-title text-center"><a class="title"></a></h4>'+
                                            '<p class="card-text text-left"></p>'+
                                            '<p class="information text-left">Posted by <a class="author"></a> <span class="time"></span></p>'+
                                            '<hr>'+
                                            '</div>',
                                            $(".content").eq(0).before(card);
                                            $(".title").eq(i).html(json[i].title);
                                            $(".card-text").eq(i).html(json[i].context);
                                            $(".author").eq(i).html(json[i].createBy);
                                            $(".time").eq(i).html(json[i].createTime);
                                            $(".title")[i].index=i;
                                    $(".title")[i].onclick=function(){
                                        console.log(this.index);
                                        console.log(i);
                                        localStorage.setItem("id",json[this.index].id);
                                        localStorage.setItem("userName",json[this.index].createBy);
                                        console.log(json[this.index].id);
                                        window.location.href="blogpaper.html";
                                    }
                                            $(".author").eq(i).index=i;
                                            $(".author").eq(i).click(function(){
                                                localStorage.setItem("userName",json[i].createBy);
                                                console.log(json.createBy);
                                                window.location.href="blogpersonal.html";
                                            })
                                }
                                //i++;

                            }
                            else{
                                console.log(i);
                                $(".nodata").show().html("别滚动了，已经到底了。。。");
                            }
                                // else{i++};

                        },
                        error:function(json){
                            console.log("error");
                        }
                    }) 
            }
                load();
                function jiazai(){
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
                                    load();                                    
                                },(1000))

                                }
                    })
                }
                jiazai();




        //下拉加载blog
            // $(window).bind('scroll',function(){show()});

            // function show(){
            //     if($(window).scrollTop()+$(window).height()>=$(document).height()){
            //         all+=3;
            //         load();
            //     }
            // }
              // var XLJZ = '下拉加载';
              // var SKJZ = '松开加载';
              // var elDropDownRefreshText = document.querySelector('.drop-down-refresh-text');
              // var dropDownRefreshText = XLJZ;

              // var tractor = new Tractor({
              //   scroller: '.scroller',
              //   openDragLoading: true,
              //   openScrollLoading: true,
              //   dragValve: 50,
              //   scrollValve: 50,
              //   onDragStart: function () {
              //     console.log('onDragStart');
              //   },
              //   onDragLessValve: function () {
              //     console.log('onDragLessValve');
              //     if (dropDownRefreshText !== XLJZ) { elDropDownRefreshText.textContent = dropDownRefreshText = XLJZ; }
              //   },
              //   onDragGreaterValve: function () {
              //     console.log('onDragGreaterValve');
              //     if (dropDownRefreshText !== SKJZ) { elDropDownRefreshText.textContent = dropDownRefreshText = SKJZ; }
              //   },
              //   onDragDone: function () {
              //     console.log('onDragDone');
              //     elDropDownRefreshText.textContent = '加载中...';

              //     // 模拟接口耗时
              //     setTimeout(function () {
              //       insertDom();
              //       tractor.dragLoadingDone();
              //       elDropDownRefreshText.textContent = dropDownRefreshText = XLJZ;
              //     }, 1000);
              //   },
              //   onScroll2Valve: function () {
              //     console.log('onScroll2Valve');

              //     // 模拟接口耗时
              //     setTimeout(function () {
              //       appendDom();
              //       tractor.scrollLoadingDone();
              //     }, 1000);
              //   }
              // });
              //   insertDom();

              // function insertDom() {
              //   load();
              // }
              // function appendDom() {
              //   console.log("append!");
              //   all+=3;
              //   load();
              // }  

        },
    }
    return loadingData.init;
});


