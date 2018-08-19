package com.bob.demo.myo2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bob.demo.myo2o.dao.AreaDao;
import com.bob.demo.myo2o.entity.Area;
import com.bob.demo.myo2o.service.AreaService;

/** 
* @author bob 
* @version 创建时间：2018年8月19日 下午5:17:15 
* 类说明 
*/
@Service
public class AreaServiceImpl implements AreaService{
	
	@Autowired
	private AreaDao areaDao;
	@Override
	public List<Area> getAreaList() {
		// TODO Auto-generated method stub
		return areaDao.queryAreaList();
	}

}
