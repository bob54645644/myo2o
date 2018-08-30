package com.bob.demo.myo2o.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bob.demo.myo2o.dao.ProductDao;
import com.bob.demo.myo2o.dao.ProductImgDao;
import com.bob.demo.myo2o.dto.ProductExecution;
import com.bob.demo.myo2o.entity.Product;
import com.bob.demo.myo2o.entity.ProductImg;
import com.bob.demo.myo2o.enums.ProductStateEnum;
import com.bob.demo.myo2o.exceptions.ProductOperationalException;
import com.bob.demo.myo2o.service.ProductService;
import com.bob.demo.myo2o.utils.Calculate;
import com.bob.demo.myo2o.utils.ImageHolder;
import com.bob.demo.myo2o.utils.PathUtil;
import com.bob.demo.myo2o.utils.ThumbnailUtil;

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
		if (product != null && thumbnail != null && product.getShop() != null && product.getShop().getShopId() != null
				&& thumbnail.getInputStream() != null && thumbnail.getImageName() != null) {
			// 新增product
			try {
				// 初始化信息
				product.setCreateTime(new Date());
				product.setLastEditTime(new Date());
				product.setEnableStatus(1);
				int effectedNum = productDao.insertProduct(product);
				if (effectedNum <= 0) {
					throw new ProductOperationalException("新增product失败");
				}
			} catch (Exception e) {
				return new ProductExecution(ProductStateEnum.INNER_ERROR);
			}
			// 处理缩略图
			String targetAddr = null;
			try {
				// 根据product，生成目标文件夹
				targetAddr = generateThumbnailTargetAddr(product);
				// 生成缩略图
				String relativePath = ThumbnailUtil.generateThumbnail(thumbnail, targetAddr);
				// 设置缩略图地址
				product.setProductImg(relativePath);
				// 更新数据库
				int effectedNum2 = productDao.updateProduct(product);
				if (effectedNum2 <= 0) {
					throw new ProductOperationalException("缩略图插入失败!");
				}
			} catch (Exception e) {
				return new ProductExecution(ProductStateEnum.INNER_ERROR);
			}
			// 处理详情图
			if (productImgList != null && productImgList.size() > 0 && targetAddr != null) {
				List<ProductImg> imgList = new ArrayList<>();
				for (ImageHolder ih : productImgList) {
					if (ih != null && ih.getInputStream() != null && ih.getImageName() != null) {
						// 生成详情图
						try {
							String relativeAddr = ThumbnailUtil.generateImg(ih, targetAddr);
							ProductImg productImg = new ProductImg();

							// 设置属性，包括图片地址,放在product文件夹下
							productImg.setCreateTime(new Date());
							productImg.setImgAddr(relativeAddr);
							productImg.setPriority(0);
							productImg.setProductId(product.getProductId());

							imgList.add(productImg);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							return new ProductExecution(ProductStateEnum.INNER_ERROR);
						}
					} else {
						return new ProductExecution(ProductStateEnum.EMPTY);
					}
				}
				// 新增到数据库
				int effectedNum3 = productImgDao.batchInsertProductImg(imgList);
				if (effectedNum3 <= 0) {
					return new ProductExecution(ProductStateEnum.INNER_ERROR);
				}
			}
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
		return new ProductExecution(ProductStateEnum.SUCCESS);
	}

	private String generateThumbnailTargetAddr(Product product) {
		return "/upload/item/shop/" + product.getShop().getShopId() + "/product/" + product.getProductId();
	}

	@Override
	public Product getProductById(long productId) {
		if (productId > -1) {
			return productDao.queryById(productId);
		} else {
			return null;
		}
	}

	@Override
	public ProductExecution getProductByCondition(Product productCondition, int pageIndex, int pageSize)
			throws ProductOperationalException {
		if (productCondition != null && pageIndex > -1 && pageSize > -1) {
			// 页码转换为行号
			int rowIndex = Calculate.calculateRowIndex(pageIndex, pageSize);
			try {
				List<Product> productList = productDao.queryProduct(productCondition, rowIndex, pageSize);
				int count = productDao.queryProductCount(productCondition);
				return new ProductExecution(ProductStateEnum.SUCCESS, productList, count);
			} catch (Exception e) {
				ProductExecution pe = new ProductExecution(ProductStateEnum.INNER_ERROR);
				pe.setStateInfo(e.getMessage());
				return pe;
			}
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}

	@Override
	@Transactional
	public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws ProductOperationalException {
		// 空值判断
		if (product != null && product.getProductId() != null && product.getShop() != null
				&& product.getShop().getShopId() != null) {
			// 有缩略图，处理
			String targetAddr = null;
			if (thumbnail != null && thumbnail.getInputStream() != null && thumbnail.getImageName() != null) {
				// 查看以前是否有缩略图，有则先删除之前的
				Product nowProduct = productDao.queryById(product.getProductId());
				if (nowProduct.getProductImg() != null) {
					// 删除旧的
					PathUtil.removeByPath(nowProduct.getProductImg());
				}
				// 先获得目标文件夹
				targetAddr = generateThumbnailTargetAddr(product);
				// 生成新的
				try {
					String relativePath = ThumbnailUtil.generateThumbnail(thumbnail, targetAddr);
					product.setProductImg(relativePath);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					ProductExecution pe = new ProductExecution(ProductStateEnum.INNER_ERROR);
					pe.setStateInfo(e.getMessage());
					return pe;
				}
			}
			// 有详情图，处理
			if (productImgList != null && productImgList.size() > 0) {
				// 之前如果有，先删除之前的
				List<ProductImg> nowProductImgList = productImgDao.queryByProductId(product.getProductId());
				nowProductImgList.forEach(x->{
					PathUtil.removeByPath(x.getImgAddr());
				});
				//生成新的
				List<ProductImg> imgList = new ArrayList<>();
				for (ImageHolder ih : productImgList) {
					if (ih != null && ih.getInputStream() != null && ih.getImageName() != null) {
						// 生成详情图
						try {
							String relativeAddr = ThumbnailUtil.generateImg(ih, targetAddr);
							ProductImg productImg = new ProductImg();

							// 设置属性，包括图片地址,放在product文件夹下
							productImg.setCreateTime(new Date());
							productImg.setImgAddr(relativeAddr);
							productImg.setPriority(0);
							productImg.setProductId(product.getProductId());

							imgList.add(productImg);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							return new ProductExecution(ProductStateEnum.INNER_ERROR);
						}
					} else {
						return new ProductExecution(ProductStateEnum.EMPTY);
					}
				}
				//详情图新增到数据库
				int effectedNum = productImgDao.batchInsertProductImg(imgList);
				if(effectedNum <=0) {
					return new ProductExecution(ProductStateEnum.INNER_ERROR);
				}
			}
			//更新product表
			product.setLastEditTime(new Date());
			int effectedNum2 = productDao.updateProduct(product);
			if(effectedNum2<=0) {
				return new ProductExecution(ProductStateEnum.INNER_ERROR);
			}else {
				return new ProductExecution(ProductStateEnum.SUCCESS);
			}
			
		}else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}

}
