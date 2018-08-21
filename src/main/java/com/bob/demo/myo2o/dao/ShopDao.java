package com.bob.demo.myo2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bob.demo.myo2o.entity.Shop;

/** 
* @author bob 
* @version 创建时间：2018年8月19日 下午10:28:29 
* 类说明 
*/
public interface ShopDao {
	//新增店铺
	int insertShop(Shop shop);
	//修改店铺 
	int updateShop(Shop shop);
	//通过shop信息查询分页所有店铺
	List<Shop> queryShop(@Param("shopCondition")Shop shopCondition,
			@Param("rowIndex")int rowIndex,
			@Param("pageSize")int pageSize);
	//根据相同的条件查询总数
	int queryShopCount(@Param("shopCondition")Shop shopCondition);
	//通过Id查询shop
	Shop queryShopById(long shopId);
}
