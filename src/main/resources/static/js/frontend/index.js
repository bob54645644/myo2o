$(function() {
	// 获得头条信息的url
	var getHeadLineListUrl = "/frontend/getheadlinelist";

	// 填充主页信息
	$.getJSON(getHeadLineListUrl, function(data) {
		var headLineList = data.headLineList;
		var headLineHtml = '';
		headLineList.map(function(item, index) {
			headLineHtml += '<div class="swiper-slide">' + '<a href="'
					+ item.headLineLink + '">' + '<img src="'
					+ item.headLineImg + '" width="100%" height="100%">'
					+ '</a>' + '</div>';
		});
		$('.swiper-wrapper').html(headLineHtml);
		// 填充一级分类
		var shopCategoryList = data.shopCategoryList;
		var categoryHtml = '';
		shopCategoryList.map(function(item, index) {
			categoryHtml += '<a href="/frontend/shoplist?parentId='
					+ item.shopCategoryId + '">'
					+'<span>'+item.shopCategoryName+'</span>'
					+'<img src="'+item.shopCategoryImg+'" width="40" height="50">'
					+'</a>';
		});
		$('#parent-shop').html(categoryHtml);
	});

	// 轮播图设置
	$(".swiper-container").swiper({
		autoplay : 3000,
		autoplayDisableOnInteraction : false
	});
	$.init();
});
