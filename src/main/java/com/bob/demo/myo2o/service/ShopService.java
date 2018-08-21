package com.bob.demo.myo2o.service;
/** 
* @author bob 
* @version 创建时间：2018年8月20日 下午3:01:35 
* 类说明 
*/


import com.bob.demo.myo2o.dto.ShopExecution;
import com.bob.demo.myo2o.entity.Shop;
import com.bob.demo.myo2o.exceptions.ShopOperationalException;
import com.bob.demo.myo2o.utils.ImageHolder;

public interface ShopService {
	//根据条件获取店铺列表
	ShopExecution getShopListByCondition(Shop shopCondition,
			int pageIndex,int pageSize);
	//新增店铺
	ShopExecution addShop(Shop shop,ImageHolder imageHolder) throws ShopOperationalException;
}
