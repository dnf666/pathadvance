
define(['lib/jquery', 'util/funcTpl'], function($,funcTpl) {
     
    var item = {
        init:function(){
            
            $("#index").append(funcTpl(item.tpl));
        },
        tpl:function(){
            /*
             <div>item部分</div>
             <div class="row">item1</div>
            */
        }

    }
    return item.init;
});


