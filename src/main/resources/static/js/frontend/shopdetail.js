$(function(){
	//从当前url中获取shopId
	var shopId = getParamValue("shopId");
	//获取店铺详情的url
	var getShopDetailInfoUrl = "/frontend/getshopdetailinfo?shopId="+shopId;
	//获取商品列表的url
	var getProductListUrl = "/frontend/getshopdetail?shopId="+shopId;
	
	//待选择的信息
	var productCategoryId = '';
	var productName = '';
	
	//商品列表所用信息
	var pageNum = 1;
	var pageSize=2;
	var maxItems = 999;
	var loading = false;
	
	
	//初始化页面
	initPage();
	//开始先获取两个商品
	addItems(pageNum,pageSize);
	
	function initPage(){
		$.getJSON(getShopDetailInfoUrl,function(data){
			if(data.success){
				//店铺信息
				var shop = data.shop;
				//标题，店铺名
				$('#shop-name').text(shop.shopName);
				//店铺缩略图
				$('#shop-img').attr('src',shop.shopImg);
				//最近一次修改时间
				$('#last-edit-time').text(new Date(shop.lastEditTime).Format('yyyy-MM-dd'));
				//店铺描述
				$('#shop-desc').text(shop.shopDesc);
				//地址
				$('#shop-addr').text(shop.shopAddr);
				//电话
				$('#phone').text(shop.phone);
				
				//商品类别
				var productCategoryList = data.productCategoryList;
				var categoryHtml = '';
				productCategoryList.map(function(item,index){
					categoryHtml += '<div class="col-33">'
						+'<a class="button" data-id="'+item.productCategoryId+'">'
						+item.productCategoryName
						+'</a>'
						+'</div>';
				});
				$('#product-category-wrap').html(categoryHtml);
			}else{
				$.toast(data.errMsg);
			}
		});
	}
	function addItems(num,size){
		//如果正在加载，则返回，防止多次加载
		if(loading){
			return ;
		}
		loading = true;
		//拼接url
		var destUrl = getProductListUrl+'&pageIndex='+num
		+'&pageSize='+size
		+'&productCategoryId='+productCategoryId
		+'&productName='+productName;
		$.getJSON(destUrl,function(data){
			if(data.success){
				maxItems = data.count;
				var productList = data.productList;
				var tempHtml = '';
				productList.map(function(item,index){
					tempHtml+='<div class="card product-card">'
						+'<div class="card-header">'+item.productName+'</div>'
						+'<div class="card-content">'
						+'<div class="list-block media-list">'
						+'<ul>'
						+'<li class="item-content">'
						+'<div class="item-media">'
						+'<a href="/frontend/productdetail?productId='+item.productId+'">'
						+'<img src="'+item.productImg+'" width="44"></a>'
						+'</div>'
						+'<div class="item-inner">'
						+'<div>'+item.productDesc+'</div>'
						+'</div></li></ul></div></div>'
						+'<div class="card-footer">'
						+'<span>'+new Date(item.lastEditTime).Format('yyyy-MM-dd')+'</span>'
						+'<span>点击查看</span>'
						+'</div></div></div>';
				});
				pageNum++;
				$('#product-wrap').append(tempHtml);
			}else{
				$.toast(data.errMsg);
			}
			loading=false;
		});
	}
	//下拉触发
	$(document).on('infinite','.infinite-scroll-bottom',function(){
		//统计卡片数量
		var totalNum = $('.product-card').length;
//		$.toast(totalNum);
		
		if(totalNum >=maxItems){
			$('.preloader').hide();
		}else{
			$('.preloader').show();
		}
		addItems(pageNum,pageSize);
		// 容器发生改变,如果是js滚动，需要刷新滚动
		$.refreshScroller();
	});
	
	//处理点击商品分类
	$('#product-category-wrap').on('click','.button',function(e){
		productCategoryId = e.target.dataset.id;
//		$.toast(productCategoryId);
		if($(e.target).hasClass('button-fill')){
			$(e.target).removeClass('button-fill');
			productCategoryId = '';
		}else{
			$(e.target).addClass('button-fill').siblings().removeClass('button-fill');
		}
		
		
		$('#product-wrap').empty();
		pageNum = 1;
		addItems(pageNum,pageSize);
	});
	//处理输入商品名
	$('#search').on('change',function(){
//		$.toast("search");
		productName = $('#search').val();
		$('#product-wrap').empty();
		pageNum = 1;
		addItems(pageNum,pageSize);
	});
	$.init();
});