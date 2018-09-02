$(function() {
	// 从当前url中获取parentId
	var parentId = getParamValue("parentId");
	// 获取店铺分类和区域信息的url
	var initInfoUrl = "/frontend/getshoplistinfo?parentId=" + parentId;
	// 获取店铺列表的url
	var getShopListUrl = "/frontend/getshoplist";

	// 搜索相关的参数
	var shopCategoryId = '';
	var areaId = '';
	var shopName = '';

	// 加载flag
	var loading = false;
	// 最多可加载的条目
	var maxItems = 999;

	// 每次加载添加多少条目
	var pageSize = 2;
	// 页码数
	var pageNum = 1;

	// 获取分类和区域，并填充
	initPage();

	// 先填充2条
	addItems(pageNum, pageSize);

	function initPage() {
		$
				.getJSON(
						initInfoUrl,
						function(data) {
							if (data.success) {
								// 填充店铺分类
								var shopCategoryList = data.shopCategoryList;
								var categoryHtml = '<div class="col-33"><a href="#" class="button" data-id="">全部类别</a></div>';
								shopCategoryList
										.map(function(item, index) {
											categoryHtml += '<div class="col-33">'
													+ '<a href="#" class="button" data-id="'
													+ item.shopCategoryId
													+ '">'
													+ item.shopCategoryName
													+ '</a></div>';
										});
								$('#category-wrap').html(categoryHtml);
								// 填充区域
								var areaList = data.areaList;
								var areaHtml = '<option value="">全部区域</option>';
								areaList.map(function(item, index) {
									areaHtml += '<option value="'
											+ item.areaId + '">'
											+ item.areaName + '</option>';
								});
								$('#area-wrap').html(areaHtml);
							} else {
								$.toast("初始化信息获取失败！");
							}
						});
	}
	;

	function addItems(index, size) {
		// 组合查询url
		var queryUrl = getShopListUrl + '?pageIndex=' + index + '&pageSize='
				+ size + "&shopCategoryId=" + shopCategoryId + "&areaId="
				+ areaId + "&shopName=" + shopName;
		$
				.getJSON(
						queryUrl,
						function(data) {
							if (data.success) {
								// 最大数量
								maxItems = data.count;
								var shopList = data.shopList;
								var shopHtml = '';
								shopList
										.map(function(item, index) {
											shopHtml += '<div class="card"><div class="card-header">'
													+ item.shopName
													+ '</div>'
													+ '<div class="card-content"><div class="card-content-inner">'
													+ '<a href="/frontend/shopdetail?shopId='
													+ item.shopId
													+ '" external>'
													+ '<img src="'
													+ item.shopImg
													+ '">'
													+ '</a>'
													+ '<p>'
													+ item.shopDesc
													+ '</p>'
													+ '</div></div>'
													+ '<div class="card-footer">'
													+ '<span>'
													+ lastEditTime(item.lastEditTime)
													+ '</span>'
													+ '<span>点击查看</span></div></div>';
										});
								$('#shop-card-wrap').append(shopHtml);
								pageNum++;
							} else {
								$.toast("店铺列表获取失败！");
							}
						});
	}
	// 注册'infinite'事件处理函数
	$(document).on('infinite', '.infinite-scroll-bottom', function() {
		// 如果正在加载，则退出
		if (loading)
			return;
		// 设置flag
		loading = true;

		
		// 统计卡片数量
		var numberTotal = $('.card').length;
		if (maxItems<=pageSize || numberTotal >= maxItems ) {
			// 加载完毕，则隐藏无限加载事件，以防不必要的加载
			$('.infinite-scroll-preloader').hide();
		} else {
			$('.infinite-scroll-preloader').show();
		}
		// 添加新条目
		addItems(pageNum, pageSize);
		// 容器发生改变,如果是js滚动，需要刷新滚动
		$.refreshScroller();
		// 重置加载flag
		loading = false;

	});

	function lastEditTime(editTime) {
		return new Date(editTime).Format("yyyy-MM-dd");
	}

	// 处理选择店铺类别
	$('#category-wrap').on('click', '.button', function(e) {
		shopCategoryId = e.currentTarget.dataset.id;
		
		//若之前已经选定了别的，则移除其选定效果，选定新的
		if($(e.target).hasClass('button-fill')){
			$(e.target).removeClass('button-fill');
			shopCategoryId='';
		}else{
			$(e.currentTarget).addClass('button-fill').siblings().removeClass('button-fill');
		}
		
		$('#shop-card-wrap').empty();
		pageNum = 1;
		addItems(pageNum, pageSize);

	});
	// 处理选择区域
	$('#area-wrap').on('change',function(){
		areaId = $('#area-wrap').val();

		$('#shop-card-wrap').empty();
		pageNum = 1;
		addItems(pageNum, pageSize);
	});
	// 处理搜索店铺名
	$('#search').on('change',function(){
		shopName = $('#search').val();

		$('#shop-card-wrap').empty();
		pageNum = 1;
		addItems(pageNum, pageSize);
	});
	// 点击后打开右侧栏
	$('#me').click(function() {
		$.openPanel('#panel-right-demo');
	});
	$.init();
});