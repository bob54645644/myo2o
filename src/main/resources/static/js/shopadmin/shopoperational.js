$(function(){
	//注册店铺时，获取初始化信息的url
	var registerShopInfoUrl = "/shopadmin/registershopinfo";
	//注册店铺的url
	var registerShopUrl = '/shopadmin/registershop';
	$.getJSON(registerShopInfoUrl,function(data){
		if(data.success){
			//处理区域信息
			var areaList = data.areaList;
			//填充区域
			var areaHtml = '';
			areaList.map(function(item,index){
				areaHtml +='<option data-id="'+item.areaId
				+'">'+item.areaName+'</option>';
			});
			$('#area').html(areaHtml);
			
			//处理店铺分类
			var shopCategoryList = data.shopCategoryList;
			//填充店铺分类
			var shopCategoryHtml = '';
			shopCategoryList.map(function(item,index){
				shopCategoryHtml +='<option data-id="'
					+item.shopCategoryId+'">'
					+item.shopCategoryName+'</option>';
			});
			$('#shop-category').html(shopCategoryHtml);
		}else{
			$.toast("获取信息失败！");
		}
	});
	
	$('#submit').click(function(){
		//获取店铺信息
		var shop = {};
		shop.shopName=$('#shop-name').val();
		shop.shopAddr=$('#shop-addr').val();
		shop.phone = $('#phone').val();
		shop.shopDesc = $('#shop-desc').val();
		shop.area={
				areaId:$('#area').find('option').not(function(){
					return !this.selected;
				}).data("id")
		};
		shop.shopCategory={
				shopCategoryId:$('#shop-category').find('option').not(function(){
					return !this.selected;
				}).data('id')
		}
		//处理缩略图
		var thumbnailImg=$('#thumbnail-img')[0].files[0];
		//验证码
		var verifyCode = $('#verify-code').val();
		if(verifyCode==undefined || verifyCode==''){
			$.toast("请输入验证码！");
			return ;
		}
		
		var formData = new FormData();
		formData.append('shopStr',JSON.stringify(shop));
		formData.append('thumbnailImg',thumbnailImg);
		formData.append('verifyCode',verifyCode);
		$.ajax({
			url:registerShopUrl,
			type:'post',
			data:formData,
			contentType : false,
			processData : false,
			cache : false,
			success:function(data){
				if(data.success){
					$.toast("操作成功！");
					$('img').click();
				}else{
					$.toast(data.errMsg);
					$('img').click();
				}
			}
		});
	});
});