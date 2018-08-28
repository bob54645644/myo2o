package com.bob.demo.myo2o.service;
/** 
* @author bob 
* @version 创建时间：2018年8月27日 下午6:59:20 
* 类说明 
*/

import java.util.List;

import com.bob.demo.myo2o.dto.ProductExecution;
import com.bob.demo.myo2o.entity.Product;
import com.bob.demo.myo2o.exceptions.ProductOperationalException;
import com.bob.demo.myo2o.utils.ImageHolder;

public interface ProductService {
	// 新增产品
	ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws ProductOperationalException;

	// 根据id查询产品
	Product getProductById(long productId);

	// 根据条件，分页查询,并且返回数量
	ProductExecution getProductByCondition(Product productCondition, int pageIndex, int pageSize)
			throws ProductOperationalException;

	// 修改product
	ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws ProductOperationalException;

}
