package com.bob.demo.myo2o.dao;

import java.util.List;

import com.bob.demo.myo2o.entity.Area;

/** 
* @author bob 
* @version 创建时间：2018年8月19日 下午4:27:21 
* 类说明 
*/
public interface AreaDao {
	//插入一条数据
	int insertArea(Area area);
	//查询所有的区域信息
	List<Area> queryAreaList();
}
