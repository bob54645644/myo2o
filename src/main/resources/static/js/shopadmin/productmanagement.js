$(function(){
	//获得productList的url
	var productListUrl = "/shopadmin/getproductlist";
	
	//填充页面的方法
	initPage();
	
	function initPage(){
		$.getJSON(productListUrl,function(data){
			if(data.success){
				//productList
				var productList = data.productList;
				var html = '';
				//填充
				productList.map(function(item,index){
					html+='<div class="col-33">'+item.productName+'</div>'
					+'<div class="col-33">'+item.priority+'</div>'
					+'<div class="col-33">'
					+'<a href="/shopadmin/productoperational?productId='+item.productId+'" external>编辑</a>'
					+handleStatus(item.productId,item.enableStatus)
					+'<a href="#">预览</a>'
					+'</div>';
				});
				$('.productcategory-wrap').html(html);
			}else{
				$.toast("信息获取失败！");
			}
		});
	};
	//实现handleStatus方法，根据productStatus，相应显示页面
	function handleStatus(productId,enableStatus){
		if(enableStatus==1){
			return '<a href="#" class="status" data-id="'+productId+'">下架</a>';
		}else{
			return '<a href="#" class="status" data-id="'+productId+'">上架</a>';
		}
		
	}
	//status即上下架
	$('.productcategory-wrap').on('click','.status',function(e){
		var productId = e.currentTarget.dataset.id;
		//提交
		var product = {};
		product.productId=productId;
		$.ajax({
			url:'/shopadmin/modifyproduct',
			type:'post',
			data:{
				productStr:JSON.stringify(product),
				status:true
			},
			success:function(data){
				if(data.success){
					$.toast("操作成功！");
					initPage();
				}else{
					$.toast(data.errMsg);
				}
			}
			
		});
	});
});