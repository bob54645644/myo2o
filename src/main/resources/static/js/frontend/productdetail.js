$(function(){
	//获取productId
	var productId = getParamValue("productId");
	//获取商品详情的url
	var getProductDetailUrl = "/frontend/getproductdetail?productId="+productId;
	$.getJSON(getProductDetailUrl,function(data){
		if(data.success){
			var product = data.product;
			//填充商品详情
			$('#product-name').text(product.productName);
			$('#product-img').attr('src',product.productImg);
			$('#edit-time').text(new Date(product.lastEditTime).Format("yyyy-MM-dd"));
			$('#product-desc').text(product.productDesc);
			//处理价格
			if(product.normalPrice == undefined && product.promotionPrice == undefined){
				$('#price').remove();
			}else if(product.normalPrice != undefined && product.promotionPrice == undefined){
				$('#normalPrice').remove();
				$('#promotionPrice').text('￥'+product.normalPrice);
			}else if(product.normalPrice == undefined && product.promotionPrice != undefined){
				$('#normalPrice').remove();
				$('#promotionPrice').text('￥'+product.promotionPrice);
			}else if(product.normalPrice != undefined && product.promotionPrice != undefined){
				$('#normalPrice').html('<del>￥'+product.normalPrice+'</del>');
				$('#promotionPrice').text('￥'+product.promotionPrice);
			}
			//填充详情图
			var productImgList = product.productImgList;
			var tempHtml = '';
			productImgList.map(function(item,index){
				tempHtml +='<img src="'+item.imgAddr+'" width="100%">';
			});
//			$.toast(html);
			$('#product-imgs').html(tempHtml);
		}else{
			$.toast(data.errMsg);
		}
	});
	// 点击后打开右侧栏
	$('#me').click(function() {
		$.openPanel('#panel-right-demo');
	});
});