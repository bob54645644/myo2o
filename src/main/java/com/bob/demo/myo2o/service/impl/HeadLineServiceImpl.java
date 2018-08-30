package com.bob.demo.myo2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bob.demo.myo2o.dao.HeadLineDao;
import com.bob.demo.myo2o.entity.HeadLine;
import com.bob.demo.myo2o.service.HeadLineService;

/** 
* @author bob 
* @version 创建时间：2018年8月29日 下午8:57:53 
* 类说明 
*/
@Service
public class HeadLineServiceImpl implements HeadLineService{
	@Autowired
	private HeadLineDao headLineDao;

	@Override
	public List<HeadLine> getHeadLineByCondition(HeadLine headLineCondition) {
		// TODO Auto-generated method stub
		return headLineDao.queryHeadLineListByCondition(headLineCondition);
	}

}
