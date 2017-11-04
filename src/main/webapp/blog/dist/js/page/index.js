/**
 *首页
 * @author: qking
 **/
require.config({
	baseUrl: MIS.STATIC_ROOT
});
require(['lib/jquery', 'module/item','util/funcTpl','util/request'], function($, item,funcTpl,request) {
	
	var index = {
		init: function() {

            /*将页面比较大的逻辑提出来，写在js/module,在此处调用*/
			item();
	
			$("#index").append(funcTpl(index.tpl))
			
			request.ajax(
				'GET',
				'/api/index'
				).then(function(res){
					console.log(res)
			
				},function(err){
					console.log(err)
				})
						
		},
		tpl:function(){
				/*<div class="col-md-2 red"></div>*/
		}
	};

	index.init();


});




