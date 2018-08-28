package com.bob.demo.myo2o.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bob.demo.myo2o.dao.ProductDao;
import com.bob.demo.myo2o.dao.ProductImgDao;
import com.bob.demo.myo2o.dto.ProductExecution;
import com.bob.demo.myo2o.entity.Product;
import com.bob.demo.myo2o.enums.ProductStateEnum;
import com.bob.demo.myo2o.exceptions.ProductOperationalException;
import com.bob.demo.myo2o.service.ProductService;
import com.bob.demo.myo2o.utils.ImageHolder;

/**
 * @author bob
 * @version 创建时间：2018年8月27日 下午10:48:48 类说明
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;

	@Override
	@Transactional
	public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws ProductOperationalException {
		// 空值判断
		if (product != null && thumbnail != null && thumbnail.getInputStream() != null
				&& thumbnail.getImageName() != null) {
			// 新增product
			try {
				//初始化信息
				product.setCreateTime(new Date());
				product.setLastEditTime(new Date());
				product.setEnableStatus(1);
				int effectedNum = productDao.insertProduct(product);
				if(effectedNum<=0) {
					throw new ProductOperationalException("新增product失败");
				}
			}catch(Exception e) {
				return new ProductExecution(ProductStateEnum.INNER_ERROR);
			}
			// 处理缩略图
			try {
				
			}catch(Exception e) {
				return new ProductExecution(ProductStateEnum.INNER_ERROR);
			}
		}
			
			
			// 处理详情图
			return null;
	}

	@Override
	public Product getProductById(long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductExecution getProductByCondition(Product productCondition, int pageIndex, int pageSize)
			throws ProductOperationalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws ProductOperationalException {
		// TODO Auto-generated method stub
		return null;
	}

}
