package com.bob.demo.myo2o.service.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.bob.demo.myo2o.utils.PathUtil;
import com.bob.demo.myo2o.utils.ThumbnailUtil;

/** 
* @author bob 
* @version 创建时间：2018年8月20日 下午3:06:22 
* 类说明 
*/
@Service
public class ShopServiceImpl implements ShopService{
	
	Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);
	
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
				logger.error("获取店铺列表失败");
				se = new ShopExecution(ShopStateEnum.INNER_ERROR);
			}
			return se;
		}else {
			logger.error("获取店铺列表，信息为空");
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
					logger.error("新增shop失败");
					throw new ShopOperationalException("新增店铺操作失败！");
				}
			}catch(ShopOperationalException e) {
				logger.error("新增shop失败");
				return new ShopExecution(ShopStateEnum.INNER_ERROR);
			}
			//处理缩略图
			//根据shopId生成文件夹
			String targetAddr = getTargetAddr(shop);
			try {
				String relativePath = ThumbnailUtil.generateThumbnail(imageHolder, targetAddr);
				shop.setShopImg(relativePath);
				logger.debug("处理shop缩略图成功");
			}catch(Exception e) {
				logger.error("处理shop缩略图失败");
				throw new ShopOperationalException(e.getMessage());
			}
			//更新数据库的shopImg
			try {
				int effectedNum2 = shopDao.updateShop(shop);
				if(effectedNum2 <=0) {
					logger.error("新增shop缩略图失败");
					throw new ShopOperationalException("更新店铺缩略图失败");
				}
			}catch(Exception e) {
				logger.error("新增shop缩略图失败");
				throw new ShopOperationalException("更新店铺缩略图失败！");
			}
			logger.debug("新增shop成功");
			return new ShopExecution(ShopStateEnum.SUCCESS);
		}else {
			logger.debug("新增shop,信息为空");
			return new ShopExecution(ShopStateEnum.EMPTY);
		}
	}
	//根据shopId生成文件夹
	private String getTargetAddr(Shop shop) {
		// TODO Auto-generated method stub
		return "/upload/item/shop/"+shop.getShopId();
	}
	//根据shopId查询shop
	@Override
	public Shop getByShopId(long shopId) {
		Shop shop = shopDao.queryShopById(shopId);
		return shop;
	}
	//编辑店铺
	@Override
	public ShopExecution modifyShop(Shop shop, ImageHolder imageHolder) throws ShopOperationalException{
		if(shop!=null && shop.getShopId()!=null) {
			//处理缩略图
			if(imageHolder!=null && imageHolder.getInputStream()!=null &&
					imageHolder.getImageName()!=null) {
				//如果原来有，先删除原来的
				Shop tempShop = shopDao.queryShopById(shop.getShopId());
				if(tempShop.getShopImg()!=null) {
					//删除
					PathUtil.removeByPath(tempShop.getShopImg());
				}
				//生成新的
				try {
					String newImg = ThumbnailUtil.generateThumbnail(imageHolder, getTargetAddr(shop));
					shop.setShopImg(newImg);
					logger.debug("编辑店铺，修改缩略图成功");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.error("编辑店铺，修改缩略图失败");
					return new ShopExecution(ShopStateEnum.INNER_ERROR);
				}
				
			}
			//更新店铺信息
			try {
				int effectedNum = shopDao.updateShop(shop);
				if(effectedNum<=0){
					logger.error("修改店铺失败");
					throw new ShopOperationalException("更新店铺信息失败");
				}
			}catch(Exception e) {
				logger.error("修改店铺失败");
				return new ShopExecution(ShopStateEnum.INNER_ERROR);
			}
			logger.debug("修改店铺成功");
			return new ShopExecution(ShopStateEnum.SUCCESS);
		}else {
			logger.debug("修改店铺，信息为空");
			return new ShopExecution(ShopStateEnum.EMPTY);
		}
		
	}

}
