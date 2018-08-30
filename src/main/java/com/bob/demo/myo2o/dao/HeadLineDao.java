package com.bob.demo.myo2o.dao;

import java.util.List;

import com.bob.demo.myo2o.entity.HeadLine;

/** 
* @author bob 
* @version 创建时间：2018年8月29日 下午8:16:54 
* 类说明 
*/
public interface HeadLineDao {
	//新增HeadLine
	int insertHeadLine(HeadLine headLine);
	//通过HeadLineCondition查询
	List<HeadLine> queryHeadLineListByCondition(HeadLine headLineCondition);
	
}
