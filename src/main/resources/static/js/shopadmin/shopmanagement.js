$(function(){
	//获得shopId
	var shopId = getParamValue("shopId");
	//店铺管理信息的url
	var shopManagementInfoUrl = "/shopadmin/shopmanagementinfo?shopId="+shopId;
	$.getJSON(shopManagementInfoUrl,function(data){
		if(data.redirect){
			window.location.href="/shopadmin/shoplist";
		}else if(data.shopId!=undefined && data.shopId!=null){
			$('#shop-info').attr('href','/shopadmin/shopoperational?shopId='+data.shopId);
		}else{
			$('#shop-info').attr('href','/shopadmin/shopoperational?shopId='+shopId);
		}
	});
});