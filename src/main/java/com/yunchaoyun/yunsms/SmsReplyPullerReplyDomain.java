package com.yunchaoyun.yunsms;

import java.io.Serializable;

public class SmsReplyPullerReplyDomain implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2516290285916487405L;

	/**
	 *平台批次号(与状态的平台批次号不同)
	 */
	private String taskId;
	
	/**
	 * 单个手机号
	 */
	private String phoneNo;
	
	/**
	 * 回复内容
	 */
	private String content;
	
	/**
	 * 回复时间
	 */
	private String receiveTime;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	
}
