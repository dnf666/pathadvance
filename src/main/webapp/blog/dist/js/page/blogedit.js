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
			//清空表单内容
			blogpaper._clear();
			//加载修改的题目和内容
			blogpaper._load();
			//设置权限
			//blogpaper._set();
			//更新或者上传新的blog
			var identify=localStorage.getItem("identify");
				if (identify=="update") {
				//更新blog
				blogpaper._updata();
				}else{
				//上传新的blog
				blogpaper._add();					
				}

		},
		_clear(){
			$("#inputTitle").val('');
			$("#inputContent").val('');
		},
		_load(){
			var title = localStorage.getItem("title");
			var name  =  localStorage.getItem("name");
			var time = localStorage.getItem("time");
			var context = localStorage.getItem("context");
			// console.log(title);
			// console.log(name);
			// console.log(time);
			// console.log(context);
			if(title!==""){
			$(".title")[0].innerHTML=title;
			$(".name")[0].innerHTML='Posted by'+name+'&nbsp';
			$(".time")[0].innerHTML=time;
			$("#inputTitle").val(title);
			$("#inputContent").val(context);
			}

		},
		// _set(){
			//点击设置权限清空input value
			// $("#quanxinashezhi")[0].onclick=function(){
			// 	$(".listman")[0].style.display="none";
			// 	$("#userName")[0].value="";
			// }
			//样式设置
                // var a=[0,0,0,0,0];
                // for(var i=0;i<$(".man1").length;i++){
                //      $(".man1").eq(i).css("background","white");
                // }
                // for(var i=0;i<$(".man1").length;i++){
                //     $(".man1")[i].index=i;
                //           $(".man1")[i].onclick=function(){
                //             k=this.index;
                //             if(a[k]==0){
                //                 $(".man1").eq(k).css("background","#5CB85C");
                //                 a[k]=1;
                //             }else{
                //                 $(".man1").eq(k).css("background","white");
                //                 a[k]=0;
                //             }
                //         }
                // }  

            //权限设置
            // $("#search")[0].onclick=function(){
            // 	$(".listman")[0].style.display="block";
            // 	var userName=$("#userName").val();
            // 	$.ajax({
            // 		dataType:"json",
            // 		Type:"post",
            // 		url:"http://rapapi.org/mockjsdata/26117/pms/role/findUserBySearching.do",
            // 		data:{
            // 			"userName":userName,
            // 		},
            // 		success:function(json){
            // 			for(var k=0;k<$(".man1").length;k++){
	           //  			$(".man1").eq(k).html(json[0].object[0].userName)         				
            // 			}
            // 		},
            // 		error:function(json){
            // 			console.log("error!");
            // 		}
            // 	})

            // }
		// },
		_updata(){
			var updata=function(){
				var title=$("#inputTitle").val();
				var context=$("#inputContent").val();
				var id=localStorage.getItem("id");
				if (title&&context!="") {
					$.ajax({
						dataType:"json",
						Type:"post",
						url:"/pms/blog/updateBlog.do",
						data:{
							"context":context,
							"id":id,
							"isPrivate":isprivate,
							"title":title,
						},
						success:function(json){
							if (json.status==1) {
								alert("succeed！");
								window.location.href="blog.html";
							}else{
								alert("error！");
							}
						},
						error:function(json){
							console.log("error!");
						},
					})	
				}else{
					alert("请输入blog题目或者内容！");
				}
			}
			var uppublic=$("#public")[0];
			var uppersonal=$("#personal")[0];
			var isprivate;
			uppublic.onclick=function(){
				isprivate="false";
				updata();
			}
			uppersonal.onclick=function(){
				isprivate="ture";
				updata();
			}
		},
		_add(){
			var add=function(){
				var title=$("#inputTitle").val();
				var context=$("#inputContent").val();
				var masterName=localStorage.getItem("masterName");
				if (title&&context!="") {
					$.ajax({
						dataType:"json",
						Type:"post",
						url:"/pms/blog/addBlog.do",
						data:{
							"context":context,
							"createBy":masterName,
							"isPrivate":isprivate,
							"title":title,
						},
						success:function(json){
							if (json.status==1) {
								alert("succeed！");
								window.location.href="blog.html";
							}else{
								alert("error！");
							}
						},
						error:function(json){
							console.log("error!");
						},
					})	
				}else{
					alert("请输入blog题目或者内容！");
				}
	
				}
			var uppublic=$("#public")[0];
			var uppersonal=$("#personal")[0];
			var isprivate;
			uppublic.onclick=function(){
				isprivate="false";
				add();
			}
			uppersonal.onclick=function(){
				isprivate="ture";
				add();
			}
			// $("#sure")[0].onclick=function(){
			// 	var title = $("#inputTitle").val();
			// 	var context = $("#inputContent").val();
			// 	var masterName=localStorage.getItem("masterName");
			// 	$.ajax({
			// 		dataType:"json",
			// 		url:"http://rapapi.org/mockjsdata/26117/pms/blog/addBlog.do",
			// 		Type:"get",
			// 		data:{
			// 			"context":context,
			// 			"creareBy":masterName,
			// 			"title":title,
			// 		},
			// 		success:function(json){
			// 			if(json.status==1){
			// 				alert("succeed!");
			// 				window.location.href="blog.html";
			// 			}else{
			// 				alert("error!");
			// 			}
			// 		},
			// 		error:function(json){
			// 			console.log("error!");
			// 		}

			// 	})
			// }
		},
	};

	blogpaper.init();
	blogpaper.transfer();


});

