package com.yunchaoyun.yunsms;

import java.io.Serializable;

public class SmsStatusPullerStatusDomain implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4503908003706187825L;

	/**
	 * 单个平台批次号
	 */
	private String taskId;

	/**
	 * 单个手机号
	 */
	private String phoneNo;

	/**
	 * 发送状态
	 */
	private String status;

	/**
	 * 签名
	 */
	private String sign;

	/**
	 * 模版编号
	 */
	private String templateNo;

	/**
	 * 客户提交时间
	 */
	private String submitTime;

	/**
	 * 状态返回时间
	 */
	private String backTime;

	/**
	 * 网关返回值
	 */
	private String gateWayStatus;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTemplateNo() {
		return templateNo;
	}

	public void setTemplateNo(String templateNo) {
		this.templateNo = templateNo;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getBackTime() {
		return backTime;
	}

	public void setBackTime(String backTime) {
		this.backTime = backTime;
	}

	public String getGateWayStatus() {
		return gateWayStatus;
	}

	public void setGateWayStatus(String gateWayStatus) {
		this.gateWayStatus = gateWayStatus;
	}
	
	
}
