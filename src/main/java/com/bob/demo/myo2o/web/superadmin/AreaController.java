package com.bob.demo.myo2o.web.superadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bob.demo.myo2o.entity.Area;
import com.bob.demo.myo2o.service.AreaService;

/**
 * @author bob
 * @version 创建时间：2018年8月19日 下午5:25:35 类说明
 */
@RestController
@RequestMapping("/superadmin")
public class AreaController {

	@Autowired
	private AreaService areaService;

	// 获取所有区域信息
	@GetMapping("/arealist")
	public Map<String, Object> arealist() {
		Map<String, Object> modelMap = new HashMap<>();
		try {
			List<Area> areaList = areaService.getAreaList();
			modelMap.put("success", true);
			modelMap.put("areaList", areaList);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;

	}
}
