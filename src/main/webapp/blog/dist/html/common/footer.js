(function(){
	"use strict"
	var footerScript = document.getElementsByTagName('script'),
		path;
	for(var i = footerScript.length; i > 0; i--) {
	    path = footerScript[i-1].getAttribute('data-js');
		if(path != null){
			break;	
		}
	}
		var temp = '';
	if (location.host == 'localhost:3000' || location.host == '202.202.43.7' ||
		location.host == '172.22.1.159') {
		temp = '';
	} else {
		temp = '..';
	}
	var footerTpl = function(){
		/*
        <fonter>
    <p class="pull-right"><a href="#top">回到顶部</a></p>
    <p class="text-center">Copyright &copy2017 经济管理学院信管工作室</p>
		</fonter>
		*/
	}
	var footer = footerTpl.toString().replace(/^[^\/]+\/\*!?/, '').replace(/\*\/[^\/]+$/, '') +
				'<script src="'+temp+'/js/lib/r.js" data-main="'+temp+'/js/page/' + path + '"></script>'+
			  '</div></body></html>';
	document.write(footer);

})();