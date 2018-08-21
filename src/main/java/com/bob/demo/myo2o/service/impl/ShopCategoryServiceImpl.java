package com.bob.demo.myo2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bob.demo.myo2o.dao.ShopCategoryDao;
import com.bob.demo.myo2o.entity.ShopCategory;
import com.bob.demo.myo2o.service.ShopCategoryService;

/** 
* @author bob 
* @version 创建时间：2018年8月20日 下午8:18:51 
* 类说明 
*/
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService{
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	//根据信息，获取店铺分类,null返回一级类别，非null且parent为空返回所有二级类别，其他正常
	@Override
	public List<ShopCategory> getShopCategory(ShopCategory shopCategoryCondition) {
		List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(shopCategoryCondition);
		return shopCategoryList;
	}

}
