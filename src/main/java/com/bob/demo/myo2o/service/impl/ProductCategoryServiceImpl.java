package com.bob.demo.myo2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bob.demo.myo2o.dao.ProductCategoryDao;
import com.bob.demo.myo2o.dao.ProductDao;
import com.bob.demo.myo2o.dto.ProductCategoryExecution;
import com.bob.demo.myo2o.entity.ProductCategory;
import com.bob.demo.myo2o.enums.ProductCategoryStateEnum;
import com.bob.demo.myo2o.exceptions.ProductOperationalException;
import com.bob.demo.myo2o.service.ProductCategoryService;

/**
 * @author bob
 * @version 创建时间：2018年8月26日 下午5:04:34 类说明
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryDao productCategoryDao;
	@Autowired
	private ProductDao productDao;

	@Override
	@Transactional
	public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
			throws ProductOperationalException {
		if (productCategoryList != null && productCategoryList.size() > 0) {
			try {
				int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
				if (effectedNum <= 0) {
					throw new ProductOperationalException("批量添加productCategory失败");
				} else {
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
				}
			} catch (Exception e) {
				return new ProductCategoryExecution(ProductCategoryStateEnum.INNER_ERROR);
			}
		} else {
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY);
		}
	}

	@Override
	public ProductCategoryExecution removeProductCategory(long productCategoryId, long shopId)
			throws ProductOperationalException {
		if (productCategoryId >= 0 && shopId >= 0) {
			try {
				//先解除product与productCategory的关联
				int effectedNum2 = productDao.updateProductCategoryIdToNull(productCategoryId);
				//小于0很关键，因为有的product没有关联productcategory
				if(effectedNum2 <0) {
					throw new ProductOperationalException("删除productCategory关联失败");
				}
				int effectedNum = productCategoryDao.deleteById(productCategoryId, shopId);
				if (effectedNum <= 0) {
					throw new ProductOperationalException("删除productCategory失败");
				} else {
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
				}
			} catch (Exception e) {
				return new ProductCategoryExecution(ProductCategoryStateEnum.INNER_ERROR);
			}
		} else {
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY);
		}
	}

	@Override
	public List<ProductCategory> getProductCategoryByShopId(long shopId) {
		if(shopId>=0) {
			List<ProductCategory> productCategoryList = productCategoryDao.queryByShopId(shopId);
			return productCategoryList;
		}else {
			return null;
		}
	}

}
