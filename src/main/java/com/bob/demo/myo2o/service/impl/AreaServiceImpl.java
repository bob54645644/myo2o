package com.bob.demo.myo2o.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.bob.demo.myo2o.dao.AreaDao;
import com.bob.demo.myo2o.entity.Area;
import com.bob.demo.myo2o.service.AreaService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/** 
* @author bob 
* @version 创建时间：2018年8月19日 下午5:17:15 
* 类说明 
*/
@Service
public class AreaServiceImpl implements AreaService{
	Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);
	@Autowired
	private AreaDao areaDao;
	@Autowired
	private StringRedisTemplate redisTemplate;
	@Override
	public List<Area> getAreaList(){
		ObjectMapper mapper = new ObjectMapper();
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		List<Area> areaList = null;
		try {
			String areaListStr = opsForValue.get("araeList");
			if(areaListStr !=null) {
				JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, Area.class);
				areaList = mapper.readValue(areaListStr, javaType);
				return areaList;
			}else {
				areaList = areaDao.queryAreaList();
				opsForValue.set("areaList", mapper.writeValueAsString(areaList));
				return areaList;
			}
			
		}catch(Exception e) {
			logger.error("areaList获取失败！");
			return null;
		}
	}

}
