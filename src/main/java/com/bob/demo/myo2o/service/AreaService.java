package com.bob.demo.myo2o.service;
/** 
* @author bob 
* @version 创建时间：2018年8月19日 下午5:15:31 
* 类说明 areaService
*/

import java.util.List;

import com.bob.demo.myo2o.entity.Area;

public interface AreaService {
	//获取区域信息
	List<Area> getAreaList();
}
