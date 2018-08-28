$(function(){
	//获取productCategory列表的url
	var productCategoryListUrl = "/shopadmin/productcategorylist";
	//新增productcategory的url
	var addProductCategoryUrl = "/shopadmin/batchaddproductcategory";
	//删除productcategory的url
	var deleteProductCategoryUrl = "/shopadmin/removeproductcategory";
	initPage();
	//渲染页面
	function initPage(){
		$.getJSON(productCategoryListUrl,function(data){
			if(data.success){
				//取出productCategoryList
				var productCategoryList = data.productCategoryList;
				//填充
				var temp = '';
				productCategoryList.map(function(item,index){
					temp +='<div class="row row-content now">' 
						+'<div class="col-33">'+item.productCategoryName+'</div>'
						+'<div class="col-33">'+item.priority+'</div>'
						+'<div class="col-33">'
						+'<a href="#" data-id="'+item.productCategoryId+'" class="button delete">'
						+'删除</a></div></div>';
				});
				$('#productcategory-wrap').html(temp);
			}else{
				$.toast(data.errMsg);
			}
		});
	};
	//新增按钮触发事件
	$('#addbutton').click(function(){
		var addHtml ='<div class="row row-content new">' 
			+'<div class="col-33">'
			+'<input type="text" id="productCategoryName" placeholder="商品类别名称"/>'
			+'</div>'
			+'<div class="col-33">'
			+'<input type="text" id="priority" placeholder="优先级"/>' +'</div>'
			+'<div class="col-33">'
			+'<a href="#" class="button delete">删除</a></div></div>';
		$('#productcategory-wrap').append(addHtml);
	});
	//提交按钮触发事件
	$('#submit').click(function(){
		var tempArr = $('.new');
		var productCategoryList = [];
		tempArr.map(function(index,item){
			//临时productcategory对象
			var tempObj={};
			tempObj.productCategoryName = $(item).find('#productCategoryName').val();
			tempObj.priority = $(item).find('#priority').val();
			productCategoryList.push(tempObj);
		});
		$.ajax({
			url:addProductCategoryUrl,
			type:'post',
			data:JSON.stringify(productCategoryList),
			contentType:'application/json',
			success:function(data){
				if(data.success){
					$.toast("提交成功！");
					initPage();
				}else{
					$.toast(data.errMsg);
				}
			}
		});
	});
	//新增的productcategory点击触发
	$('#productcategory-wrap').on('click','.row-content.new .delete',function(e){
		$(this).parent().parent().remove();
	});
	//现在的productcategory点击触发  
	$('#productcategory-wrap').on('click','.row-content.now .delete',function(e){
		//获得点击的列对应的id
		var productCategoryId = e.currentTarget.dataset.id;
		//对话框
		$.confirm('确定吗？',function(){
			$.ajax({
				url:deleteProductCategoryUrl,
				type:'post',
				data:{
					productCategoryId:productCategoryId
				},
				success:function(data){
					if(data.success){
						$.toast('删除成功！');
						initPage();
					}else{
						$.toast('删除失败！');
					}
				}
			});
		});
	});
});