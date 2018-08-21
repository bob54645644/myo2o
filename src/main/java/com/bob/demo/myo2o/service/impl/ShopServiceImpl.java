package com.bob.demo.myo2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bob.demo.myo2o.dao.ShopDao;
import com.bob.demo.myo2o.dto.ShopExecution;
import com.bob.demo.myo2o.entity.Shop;
import com.bob.demo.myo2o.enums.ShopStateEnum;
import com.bob.demo.myo2o.exceptions.ShopOperationalException;
import com.bob.demo.myo2o.service.ShopService;
import com.bob.demo.myo2o.utils.Calculate;
import com.bob.demo.myo2o.utils.ImageHolder;
import com.bob.demo.myo2o.utils.ThumbnailUtil;

/** 
* @author bob 
* @version 创建时间：2018年8月20日 下午3:06:22 
* 类说明 
*/
@Service
public class ShopServiceImpl implements ShopService{
	
	@Autowired
	private ShopDao shopDao;
	//根据条件获取店铺列表
	@Override
	public ShopExecution getShopListByCondition(Shop shopCondition,
			int pageIndex,int pageSize) {
		// TODO Auto-generated method stub
		
		if(shopCondition != null && pageIndex>-1 && pageSize>-1) {
			ShopExecution se=null;
			int rowIndex = Calculate.calculateRowIndex(pageIndex, pageSize);
			try {
				List<Shop> shopList = shopDao.queryShop(shopCondition, rowIndex, pageSize);
				int count = shopDao.queryShopCount(shopCondition);
				se = new ShopExecution(ShopStateEnum.SUCCESS, count, shopList);
			}catch(Exception e) {
				se = new ShopExecution(ShopStateEnum.INNER_ERROR);
			}
			return se;
		}else {
			return new ShopExecution(ShopStateEnum.EMPTY);
		}
	}
	//新增店铺
	@Transactional
	@Override
	public ShopExecution addShop(Shop shop,ImageHolder imageHolder) throws ShopOperationalException {
		if(shop!=null && shop.getOwner()!=null && shop.getOwner().getPersonId()!=null
				&& imageHolder!=null && imageHolder.getInputStream()!=null) {
			//新增到数据库,之后才有shopId
			try {
				int effectedNum = shopDao.insertShop(shop);
				if(effectedNum<=0) {
					throw new ShopOperationalException("新增店铺操作失败！");
				}
			}catch(ShopOperationalException e) {
				return new ShopExecution(ShopStateEnum.INNER_ERROR);
			}
			//处理缩略图
			//根据shopId生成文件夹
			String targetAddr = getTargetAddr(shop);
			try {
				String relativePath = ThumbnailUtil.generateThumbnail(imageHolder, targetAddr);
				shop.setShopImg(relativePath);
			}catch(Exception e) {
				throw new ShopOperationalException(e.getMessage());
			}
			//更新数据库的shopImg
			try {
				int effectedNum2 = shopDao.updateShop(shop);
				if(effectedNum2 <=0) {
					throw new ShopOperationalException("更新店铺缩略图失败");
				}
			}catch(Exception e) {
				throw new ShopOperationalException("更新店铺缩略图失败！");
			}
			
			return new ShopExecution(ShopStateEnum.SUCCESS);
		}else {
			return new ShopExecution(ShopStateEnum.EMPTY);
		}
	}
	//根据shopId生成文件夹
	private String getTargetAddr(Shop shop) {
		// TODO Auto-generated method stub
		return "/upload/item/shop/"+shop.getShopId();
	}

}
