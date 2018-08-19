package com.bob.demo.myo2o.entity;
/** 
* @author bob 
* @version 创建时间：2018年8月18日 下午9:15:35 
* 类说明 区域信息
*/

import java.util.Date;

public class Area {
	
	private int areaId;
	private String areaName;
	private int priority;
	private Date createTime;
	private Date lastEditTime;
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	@Override
	public String toString() {
		return "Area [areaId=" + areaId + ", areaName=" + areaName + ", priority=" + priority + ", createTime="
				+ createTime + ", lastEditTime=" + lastEditTime + "]";
	}
	
}
