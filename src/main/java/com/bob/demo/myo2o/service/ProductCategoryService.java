package com.bob.demo.myo2o.service;
/** 
* @author bob 
* @version 创建时间：2018年8月26日 下午4:14:01 
* 类说明 
*/

import java.util.List;

import com.bob.demo.myo2o.dto.ProductCategoryExecution;
import com.bob.demo.myo2o.entity.ProductCategory;
import com.bob.demo.myo2o.exceptions.ProductOperationalException;

public interface ProductCategoryService {

	//批量添加productCategory
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
	throws ProductOperationalException;
	//根据shopId查询
	List<ProductCategory> getProductCategoryByShopId(long shopId);
	//根据id和shopId，删除
	ProductCategoryExecution removeProductCategory(long productCategoryId,long shopId)
	throws ProductOperationalException;
}
