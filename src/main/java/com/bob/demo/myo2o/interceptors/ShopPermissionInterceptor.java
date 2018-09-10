package com.bob.demo.myo2o.interceptors;
/** 
* @author bob 
* @version 创建时间：2018年9月10日 上午10:49:45 
* 类说明  店铺操作权限验证拦截器
*/

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.bob.demo.myo2o.entity.Shop;

@Component
public class ShopPermissionInterceptor implements  HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object shopListObj = request.getSession().getAttribute("shopList");
		Object currentShopObj = request.getSession().getAttribute("currentShop");
		if(shopListObj !=null && currentShopObj != null) {
			try {
				@SuppressWarnings("unchecked")
				List<Shop> shopList = (List<Shop>)shopListObj;
				System.out.println(shopList);
				Shop currentShop = (Shop) currentShopObj;
				for(Shop shop:shopList) {
					if(shop.getShopId()==currentShop.getShopId()) {
						return true;
					}
				}
				return false;
			}catch(Exception e) {
				return false;
			}
		}
		return false;
	}
	
}
