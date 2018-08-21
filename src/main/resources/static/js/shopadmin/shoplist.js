$(function(){
	//获取店铺列表的url
	var shopListUrl = "/shopadmin/getshoplist";
	$.getJSON(shopListUrl,function(data){
		if(data.success){
			//获得shopList
			var shopList = data.shopList;
			//修改欢迎名称
			$("#username").html(data.username);
			//待填充信息
			var html = '';
			shopList.map(function(item,index){
				html += '<div class="row">'
					+'<div class="col-33">'+item.shopName
					+'</div>'
					+'<div class="col-33">'+getStatus(item.enableStatus)
					+'</div>'
					+'<div class="col-33">'+getOperational(item.enableStatus,item.shopId)
					+'</div>'
					+'</div>';
			});
			$('#shop-wrap').html(html);
		}else{
			$.toast("获取信息失败！");
		}
	});
	function getStatus(status){
		if(status==0){
			return '审核中';
		}else if(status==1){
			return '审核通过';
		}else{
			return '非法店铺';
		}
	};
	function getOperational(status,shopId){
		if(status==1){
			return '<a href="/shopadmin/shopmanagement?shopId='+shopId+'" external>'
			+'进入</a>';
		}else{
			return '';
		}
	};
	
	$.init();
});