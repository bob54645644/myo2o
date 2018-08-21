package com.bob.demo.myo2o.service;
/** 
* @author bob 
* @version 创建时间：2018年8月20日 下午8:16:22 
* 类说明 
*/

import java.util.List;

import com.bob.demo.myo2o.entity.ShopCategory;

public interface ShopCategoryService {
	//根据信息，获取店铺分类
	List<ShopCategory> getShopCategory(ShopCategory shopCategoryCondition);
}
