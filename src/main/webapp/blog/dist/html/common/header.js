(function() {
	
	"use strict"
	var companyId,companyName;
	var headerCss = document.getElementsByTagName('script'),
		path, cssPath, headTitle = "";
	var temp = '';

	if (location.host == 'localhost:3000' || location.host == '202.202.43.7' ||
		location.host == '172.22.1.159') {
		temp = '';
	} else {
		temp = '..';
	}

	for(var i = 0; i < headerCss.length; i++){
	    cssPath = headerCss[i].getAttribute('data-css');
		headTitle = headerCss[i].getAttribute('data-title');
		if(cssPath != null && cssPath != undefined){

			//css路径
			cssPath = '<link rel="stylesheet" href="'+temp+'/css/page/' + cssPath + '.css"/>'
			break;	
		}

	}
	var html = '<!DOCTYPE html>'+
				'<html lang="en">'+
				'<head>'+
					'<meta charset="UTF-8">'+
					'<meta name="viewPort" content="width=device-width, initial-scale=1.0">'+
					'<title>'+headTitle+'</title>'+
					'<link rel="stylesheet" href="'+temp+'/css/lib/bootstrap.css"/>'+
					'<link rel="stylesheet" href="'+temp+'/css/common/global.css"/>'+
					'<link rel="stylesheet" href="'+temp+'/css/common/header.css"/>'
					+cssPath+
					'<script>'+	
						'var MIS = {};'+
						'MIS.STATIC_ROOT = "/pms/blog/dist/js"'+
					'</script>'+
					'<script src="'+temp+'/js/lib/jquery.js"></script>'+
					// '<script src="'+temp+'/js/modules/api.js"></script>'
					'<script src="'+temp+'/js/lib/bootstrap.js"></script>'+
				'</head>';
				
    var headerTpl = function() {
		/*


		*/
	};

	var  header = html +'<div class="wrapper">'+ headerTpl.toString().replace(/^[^\/]+\/\*!?/, '').replace(/\*\/[^\/]+$/, '');
	document.write(header);

	


})();