$(function(){
    //todo 写完后台代码再填坑
    $.ajax({
        url:"/pms/admin/getUsersForAdmin.do",
        data:"start=0&limit=10",
        type:"post",
        success:function (obejct) {
            if (obejct.status == false){
                window.location.href = "/pms/admin/login.html";
            }
            var list = obejct.object.list;
            loadElement(list);
            loadPages(obejct.object.sumCount,1);
        }
    })
})

function ready(){
    $(document).ready(function () {
        $.ajax({
            url:"/pms/admin/element/head.html",
            type:"post",
            success:function(html){
                var header = $("#header");
                header.text("");
                header.append(html);
            },
            //todo 写完后台代码再填坑
            error:function(){
                alert("通讯错误");
                $.ajax({
                    url:"",
                    type:"post",
                    success:function(object){
                        
                    },
                    error:function(){
                        window.location.href="pms/admin/login.html";
                    }
                })
            }
        });
    })
}

function refresh(page) {
    var requestPage = page;
    var limit = 10;
    $.post(
        "/pms/admin/getUsersForAdmin.do",
        {
            'start':((page-1)*limit),
            'limit':limit
        },
        function (object) {
            console.log(object);
            loadElement(object.object.list);
            loadPages(object.object.sumCount,requestPage);
        }
    )
}

function loadElement(list){
    var body = $("#users_body");
    body.text("");
    for (var i = 0 ; i < list.length ; i++){
        var user = list[i];
        body.append("<tr>" +
            "<td>"+user.userName+"</td>" +
            "<td>"+user.password+"</td>"+
            "<td>"+user.stuId+"</td>"+
            "<td>"+user.peofession+"</td>"+
            "<td>"+user.delFlag+"</td>"+
            "<td>"+user.createAt.substring(0,10)+"</td>"+
            "<td></td>"+
            "</tr>");
    }
}

function loadPages(count,nowPage){
    var pageNumber = (count % 10 == 0)? ((count-count%10)/10):((count-count%10)/10)+1;
    var pageBody = $("#pageBody");
    pageBody.text('');
    if (nowPage == 1){
        pageBody.append(
            "<li><a>&laquo;</a></li>"
        );
    }else {
        pageBody.append(
            "<li><a onclick=refresh("+(nowPage-1)+")>&laquo;</a></li>"
        );
    }
    for (var i=0; i<pageNumber ; i++){
        if (i+1 == nowPage){
            pageBody.append(
                "<li class=\"active\"><a>"+(i+1)+"</a></li>"
            );
            continue;
        }
        pageBody.append(
            "<li><a onclick=refresh("+(i+1)+")>"+(i+1)+"</a></li>"
        );
    }
    if (nowPage == pageNumber){
        pageBody.append(
            "<li><a>&raquo;</a></li>"
        );
    }else{
        pageBody.append(
            "<li><a onclick=refresh("+(nowPage+1)+")>&raquo;</a></li>"
        );
    }

}