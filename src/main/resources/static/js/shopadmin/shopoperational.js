$(function(){
	//获取当前url中的shopId
	var shopId = getParamValue('shopId');
	//注册店铺时，获取初始化信息的url
	var registerShopInfoUrl = "/shopadmin/registershopinfo";
	//注册店铺的url
	var registerShopUrl = '/shopadmin/registershop';
	//编辑店铺时，获取初始化信息的url
	var editShopInfoUrl = "/shopadmin/editshopinfo?shopId="+shopId;
	//编辑店铺url
	var editShopUrl = "/shopadmin/editshop";
	
	//根据shopId判断是新增还是编辑
	var edit=shopId?true:false;
	//编辑和新增，处理方式不同
	if(edit){
		getEditInfo();
	}else{
		getRegisterInfo();
	}
	//实现函数 新增页面
	function getRegisterInfo(){
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
	};
	//编辑页面
	function getEditInfo(){
		$.getJSON(editShopInfoUrl,function(data){
			if(data.success){
				//shop
				var shop = data.shop;
				//处理区域信息
				var areaList = data.areaList;
				//填充区域
				var areaHtml = '';
				areaList.map(function(item,index){
					areaHtml +='<option data-id="'+item.areaId
					+'">'+item.areaName+'</option>';
				});
				$('#area').html(areaHtml);
				//选定原来的选项
				$('#area option[data-id="'+shop.area.areaId+'"]').attr('selected','selected');
				
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
				//选定原来的
				$('#shop-category option[data-id="'+shop.shopCategory.shopCategoryId+'"]')
				.attr('selected','selected');
				
				//填充其他信息
				$('#shop-name').val(shop.shopName);
				$('#shop-addr').val(shop.shopAddr);
				$('#phone').val(shop.phone);
				$('#shop-desc').val(shop.shopDesc);
			}else{
				$.toast("获取信息失败！");
			}
		});
	};
	
	$('#submit').click(function(){
		//获取店铺信息
		var shop = {};
		//设置shopId
		shop.shopId=shopId;
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
			//根据是编辑还是新增，选择相应的url
			url:edit?editShopUrl:registerShopUrl,
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