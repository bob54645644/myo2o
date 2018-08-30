$(function() {
	// 从当前url中获取productId，有则是编辑，没有则是新增
	var productId = getParamValue('productId');
	// 是否时编辑
	var edit = productId ? true : false;
	// 新增时，初始化url
	var productCategoryListUrl = '/shopadmin/getproductcategorylist';
	// 编辑时，初始化url
	var editInitUrl = "/shopadmin/geteditinitinfo?productId=" + productId;
	// 新增的url
	var addProductUrl = "/shopadmin/addproduct";
	//编辑的url
	var editProductUrl = '/shopadmin/modifyproduct';
	if (edit) {
		// 编辑时，初始化
		editInitPage();
	} else {
		// 新增时，初始化方法
		addInitPage();
	}

	// 新增时，初始化方法
	function addInitPage() {
		$.getJSON(productCategoryListUrl, function(data) {
			if (data.success) {
				// productCategoryList
				var productCategoryList = data.productCategoryList;
				var html = '';
				productCategoryList.map(function(item, index) {
					html += '<option data-id="' + item.productCategoryId + '">'
							+ item.productCategoryName + '</option>';
				});
				$('#product-category').html(html);
			} else {
				$.toast(data.errMsg);
			}
		});
	}
	// 编辑时，初始化
	function editInitPage() {
		$.getJSON(editInitUrl, function(data) {
			if (data.success) {
				var productCategoryList = data.productCategoryList;
				var product = data.product;
				// 填充下拉列表
				var categoryHtml = '';
				productCategoryList.map(function(item, index) {
					categoryHtml += '<option data-id="'
							+ item.productCategoryId + '">'
							+ item.productCategoryName + '</option>';
				});
				$('#product-category').html(categoryHtml);
				// 选中下拉列表
				$(
						'#product-category option[data-id="'
								+ product.productCategory.productCategoryId
								+ '"]').attr('selected', 'selected');
				//填充其他信息
				$('#product-name').val(product.productName);
				$('#priority').val(product.priority);
				$('#normal-price').val(product.normalPrice);
				$('#promotion-price').val(product.promotionPrice);
				$('#product-desc').val(product.productDesc);
			} else {
				$.toast("信息获取失败！");
			}
		});
	}
	// 详情图上传按钮处理
	$('.productImg-wrap').on(
			'change',
			'.product-img:last-child',
			function() {
				if ($('.product-img').length < 6) {
					$('.productImg-wrap').append(
							'<input type="file" class="product-img">');
				}
			});
	// 提交触发函数
	$('#submit').click(
			function() {
				// 验证码
				var verifyCode = $('#verify-code').val();
				if (verifyCode == undefined || verifyCode == '') {
					$.toast("请填写验证码！");
					return;
				}

				// 获取填写的信息
				var product = {};
				//不管是新增还是编辑，都设置productId
				product.productId = productId;
				product.productName = $('#product-name').val();
				product.priority = $('#priority').val();
				product.normalPrice = $('#normal-price').val();
				product.promotionPrice = $('#promotion-price').val();
				product.productDesc = $('#product-desc').val();
				product.productCategory = {
					productCategoryId : $('#product-category').find('option')
							.not(function() {
								return !this.selected;
							}).data('id')
				}
				// 缩略图
				var thumbnailImg = $('#thumbnail-img')[0].files[0];
				var formData = new FormData();
				formData.append('productStr', JSON.stringify(product));
				formData.append('verifyCode', verifyCode);
				formData.append('thumbnailImg', thumbnailImg);
				// 详情图,遍历添加
				$('.product-img').map(
						function(index, item) {
							if ($('.product-img')[index].files.length > 0) {
								formData.append('productImg' + index,
										$('.product-img')[index].files[0]);
							}
						});
				$.ajax({
					url : edit?editProductUrl:addProductUrl,
					type : 'post',
					data : formData,
					contentType : false,
					processData : false,
					cache : false,
					success : function(data) {
						if (data.success) {
							$.toast("提交成功！");
							$('#captcha-img').click();
						} else {
							$.toast("提交失败！");
							$('#captcha-img').click();
						}
					}
				});
			});
});