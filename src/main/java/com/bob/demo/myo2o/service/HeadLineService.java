package com.bob.demo.myo2o.service;
/** 
* @author bob 
* @version 创建时间：2018年8月29日 下午8:52:01 
* 类说明 
*/

import java.util.List;

import com.bob.demo.myo2o.entity.HeadLine;

public interface HeadLineService {
	//暂时只需要查询即可
	List<HeadLine> getHeadLineByCondition(HeadLine headLineCondition);
}
