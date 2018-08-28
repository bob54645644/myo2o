package com.bob.demo.myo2o.dao;

import java.util.List;

import com.bob.demo.myo2o.entity.ProductImg;

/** 
* @author bob 
* @version 创建时间：2018年8月27日 下午5:04:45 
* 类说明 
*/
public interface ProductImgDao {
	//批量插入
	int batchInsertProductImg(List<ProductImg> productImgList);
	//查询，根据productId
	List<ProductImg> queryByProductId(long productId);
	//批量删除，通过productId
	int deleteByProductId(long productId);
}
