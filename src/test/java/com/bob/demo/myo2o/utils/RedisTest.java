package com.bob.demo.myo2o.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.bob.demo.myo2o.entity.Area;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/** 
* @author bob 
* @version 创建时间：2018年9月10日 上午9:24:40 
* 类说明 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)//按方法名字典顺序执行
public class RedisTest {
	@Autowired
//	private RedisOperations<String , List<Area>> redisOperations;
	private StringRedisTemplate stringRedisTemplate;
	
	@Test
	public void test() throws IOException {
		List<Area> areaList = new ArrayList<>();
		
		Area area1 = new Area();
		area1.setAreaName("东苑");
		Area area2 = new Area();
		area2.setAreaName("西苑");
		
		areaList.add(area1);
		areaList.add(area2);
		
		ObjectMapper mapper = new ObjectMapper();
		
		
		stringRedisTemplate.opsForValue().set("areaList", mapper.writeValueAsString(areaList));
		String areaListStr = stringRedisTemplate.opsForValue().get("areaList");
		System.out.println(areaListStr);
		JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, Area.class);
		List<Area> areaList1= mapper.readValue(areaListStr, javaType);
		System.out.println(areaList1);
	}

}
