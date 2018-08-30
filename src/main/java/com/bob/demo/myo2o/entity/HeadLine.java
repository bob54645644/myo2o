package com.bob.demo.myo2o.entity;
/** 
* @author bob 
* @version 创建时间：2018年8月29日 下午7:41:49 
* 类说明 	头条
*/

import java.util.Date;

public class HeadLine {
	private Long headLineId;
	private String headLineName;
	private String headLineImg;
	private Integer priority;
	//0禁止 1允许
	private Integer enableStatus;
	private String headLineLink;
	private Date createTime;
	private Date lastEditTime;
	public Long getHeadLineId() {
		return headLineId;
	}
	public void setHeadLineId(Long headLineId) {
		this.headLineId = headLineId;
	}
	public String getHeadLineName() {
		return headLineName;
	}
	public void setHeadLineName(String headLineName) {
		this.headLineName = headLineName;
	}
	public String getHeadLineImg() {
		return headLineImg;
	}
	public void setHeadLineImg(String headLineImg) {
		this.headLineImg = headLineImg;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Integer getEnableStatus() {
		return enableStatus;
	}
	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}
	public String getHeadLineLink() {
		return headLineLink;
	}
	public void setHeadLineLink(String headLineLink) {
		this.headLineLink = headLineLink;
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
		return "HeadLine [headLineId=" + headLineId + ", headLineName=" + headLineName + ", headLineImg=" + headLineImg
				+ ", priority=" + priority + ", enableStatus=" + enableStatus + ", headLineLink=" + headLineLink
				+ ", createTime=" + createTime + ", lastEditTime=" + lastEditTime + "]";
	}
	
	
}
