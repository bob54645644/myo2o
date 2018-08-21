package com.bob.demo.myo2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bob.demo.myo2o.entity.ShopCategory;

/** 
* @author bob 
* @version 创建时间：2018年8月19日 下午7:07:38 
* 类说明 
*/
public interface ShopCategoryDao {
	//添加店铺分类
	int insertShopCategory(ShopCategory shopCategory);
	//修改店铺分类  暂时不需要
//	int updateShopCategory(ShopCategory shopCategory);
	//根据店铺类别条件，查询店铺分类
	List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition")ShopCategory shopCategoryCondition);
	//根据店铺类别Id查询店铺类别
	ShopCategory queryShopCategoryById(@Param("shopCategoryId")long shopCategoryId);
	
}
