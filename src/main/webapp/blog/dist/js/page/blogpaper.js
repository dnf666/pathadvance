/**
 *blogpaper
 * @author: qking
 **/
require.config({
	baseUrl: MIS.STATIC_ROOT
});
require(['lib/jquery', 'module/blogitem','util/funcTpl','util/request'], function($, blogitem,funcTpl,request) {
	
	var blogpaper = {
		init: function() {
            /*将页面比较大的逻辑提出来，写在js/module,在此处调用*/
			blogitem();
						
		},

		//调用函数
		transfer:function(){
			//加载blog
			blogpaper._loading();
			//给blog oener提供修改权限
			blogpaper._identify();
		},
		_loading(){
			var id=localStorage.getItem("id");
			$.ajax({
				dataType:"json",
				url:"/pms/blog/selectByUserName.do",
				type:"get",
				data:{
					"id":id
				},
				success:function(json){
					$(".title")[0].innerHTML=json.title;
					$(".author")[0].innerHTML='&nbsp'+json.createBy+'&nbsp&nbsp';
					$(".time")[0].innerHTML=json.createTime;
					$(".paper")[0].innerHTML=json.context;
					console.log(json);
				},
				error:function(json){
					console.log("error");
				}
			})
		},
		//判断是否为本人，若是本人生成修改博客按钮
		_identify(){
			var userName=localStorage.getItem("userName");
			var masterName=localStorage.getItem("masterName");
			if (userName==masterName) {
				var change=document.createElement("div");
				change.class=change;
				change.innerHTML='<div>'+
					 '<div class="card">'+
           				 '<a id="current">'+'<button class="btn btn-info" >修改</button>'+'</a>'+
           				       '<button type="button" class="btn btn-info" data-toggle="modal" data-target="#exampleModal">'+
			              '删除'+
			            '</button>'+

			            '<div class="modal fade modalbox" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">'+
			              '<div class="modal-dialog" role="document">'+
			                '<div class="modal-content">'+
			                  '<div class="modal-header">'+
			                    '<h5 class="modal-title" id="exampleModalLabel" style="display:inline">&nbsp!&nbsp!&nbsp!&nbsp!&nbsp!</h5>'+
			                    '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'+
			                      '<span aria-hidden="true">&times;</span>'+
			                    '</button>'+
			                  '</div>'+
			                  '<div class="modal-body">'+
			                   '您确定要删除该博客'+
			                  '</div>'+
			                  '<div class="modal-footer">'+
			                    '<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>'+
			                    '<button type="button" class="btn btn-primary news" id="delete">确定</button>'+
			                  '</div>'+
			                '</div>'+
			              '</div>'+
			            '</div>'+
       				 '</div>'+
				'</div>';
				$(".index").eq(0).append(change);
				//修改blog			
				$("#current")[0].onclick=function(){
					var id=localStorage.getItem("id");
					console.log(id);
					$.ajax({
						dataType:"json",
						url:"http://rapapi.org/mockjsdata/26117/pms/blog/selectByUserName.do",
						type:"get",
						data:id,
						success:function(json){
							localStorage.setItem("title",json.title);
							localStorage.setItem("name",json.createBy);
							localStorage.setItem("time",json.createTime);
							localStorage.setItem("context",json.context);
							localStorage.setItem("id",json.id);
							console.log(json.id);
							localStorage.setItem("identify","update");
							// var identify=localStorage.getItem("identify");
							// console.log(identify);
							window.location.href="blogedit.html";
						},
					})
				}
				//删除blog
				$("#delete")[0].onclick=function(){
					var id=localStorage.getItem("id");
					$.ajax({
						dataType:"json",
						Type:"post",
						url:"http://rapapi.org/mockjsdata/26117/pms/blog/deleteBlog.do",
						data:id,
						success:function(json){
							 if (json.status==1) {
							 	alert("succeed！");
							 	window.location.href="blog.html";
							 }else{
							 	alert("error!");
							 }
						},
						error:function(json){
							console.log("error");
						}
					})
				}
			}

		},



	};


	blogpaper.init();
	blogpaper.transfer();


});



