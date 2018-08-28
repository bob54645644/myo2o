package com.bob.demo.myo2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bob.demo.myo2o.entity.Product;

/** 
* @author bob 
* @version 创建时间：2018年8月27日 下午3:13:06 
* 类说明 产品DAO
*/
public interface ProductDao {
	//新增product
	int insertProduct(Product product);
	
	//通过id查询
	Product queryById(long productId);
	
	//通过product条件，分页查询
	List<Product> queryProduct(@Param("productCondition")Product productCondition,
			@Param("rowIndex")int rowIndex,@Param("pageSize")int pageSize);
	//同样条件，返回总数
	int queryProductCount(@Param("productCondition")Product productCondition);
	
	//修改product
	int updateProduct(Product product);
	//删除product，通过productId和shopId
	int deleteProduct(@Param("productId")long productId,@Param("shopId")long shopId);
	//删除productCategory时，将相应的product对应的标识置为null
	int updateProductCategoryIdToNull(Long productCategoryId);
}
