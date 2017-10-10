/**
 *blog权限管理
 * @author: qking
 **/
require.config({
	baseUrl: MIS.STATIC_ROOT
});
require(['lib/jquery', 'module/blogitem','util/funcTpl','util/request'], function($, blogitem,funcTpl,request) {
	
	var blogmanagement = {
		init: function() {

            /*将页面比较大的逻辑提出来，写在js/module,在此处调用*/
			blogitem();
			$("#index").before(funcTpl(blog.tpl))
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
			/*

			 */
		}
	};

	blogmanagement.init();


});




