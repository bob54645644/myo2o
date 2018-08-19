package com.bob.demo.myo2o.entity;
/** 
* @author bob 
* @version 创建时间：2018年8月19日 上午11:24:59 
* 类说明 用户信息 实体类
*/

import java.util.Date;

public class PersonInfo {
	//用户Id
	private Long personId;
	//姓名
	private String personName;
	//头像
	private String personImg;
	//邮箱
	private String email;
	//性别 
	private String gender;
	//账号状态 0、禁用，1、可用
	private Integer enableStatus;
	//账号类型 1、顾客 2、店家 3、超级管理员
	private Integer personType;
	//创建时间
	private Date createTime;
	//最后修改时间
	private Date lastEditTime;
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPersonImg() {
		return personImg;
	}
	public void setPersonImg(String personImg) {
		this.personImg = personImg;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getEnableStatus() {
		return enableStatus;
	}
	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}
	public Integer getPersonType() {
		return personType;
	}
	public void setPersonType(Integer personType) {
		this.personType = personType;
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
		return "PersonInfo [personId=" + personId + ", personName=" + personName + ", personImg=" + personImg
				+ ", email=" + email + ", gender=" + gender + ", enableStatus=" + enableStatus + ", personType="
				+ personType + ", createTime=" + createTime + ", lastEditTime=" + lastEditTime + "]";
	}
	

}
