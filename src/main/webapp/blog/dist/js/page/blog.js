/**
 *blog首页
 * @author: qking
 **/
require.config({
	baseUrl: MIS.STATIC_ROOT
});
require(['lib/jquery', 'module/blogitem','module/indexLoadingData','util/funcTpl','util/request'], function($, blogitem,indexLoadingData,funcTpl,request) {
	
	var blog = {
		init: function() {
			// $(".introduction")[0].onclick=function(){
			// 	$(".introduction")[0].innerHTML=
			// 		 '<form class="form-inline" style="margin-bottom:0;">'+
   //                          '<input class="form-control mr-sm-2 " type="text" placeholder="Search" aria-label="Search"  style="width:400px;margin:0px auto;" >'+
   //                   '</form>';
			// }
			// $("input").blur(function(){
			// 	var content=$("#form_control")[0].val();
			// 	$(".introduction")[0].innerHTML=
			// 		'<div class="introduction">'+content+'</div>';
			// });
			localStorage.setItem("masterName","戴林甫");
			
			//头部模块
			blogitem();

			//加载函数块
			indexLoadingData();

		},

	};


	blog.init();



});




