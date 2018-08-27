package com.bob.demo.myo2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bob.demo.myo2o.entity.ProductCategory;

/** 
* @author bob 
* @version 创建时间：2018年8月26日 下午3:19:42 
* 类说明 产品类别
*/
public interface ProductCategoryDao {
	//批量添加商品类别
	int batchInsertProductCategory(List<ProductCategory> productCategoryList);
	//查询商品类别,通过shopId
	List<ProductCategory> queryByShopId(long shopId);
	//单个删除产品类别
	int deleteById(@Param("productCategoryId")long productCategoryId,@Param("shopId")long shopId);
}
