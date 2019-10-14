package com.yunchaoyun.yunsms;

import java.io.IOException;
import java.net.URISyntaxException;

import com.yunchaoyun.yunsms.httpclient.DefaultHTTPClient;
import com.yunchaoyun.yunsms.httpclient.HTTPClient;
import com.yunchaoyun.yunsms.httpclient.HTTPException;
import com.yunchaoyun.yunsms.httpclient.HTTPMethod;
import com.yunchaoyun.yunsms.httpclient.HTTPRequest;
import com.yunchaoyun.yunsms.httpclient.HTTPResponse;
import com.yunchaoyun.yunsms.util.SmsSenderUtil;

public class SmsStatusPuller extends SmsBase {
	
	private String url = "https://api.yunchaoyun.com/yun/api/sms/v1/pullstatus";
	
	public SmsStatusPuller(String apiKey, String apiToken) {
		super(apiKey, apiToken, new DefaultHTTPClient());
	}

	public SmsStatusPuller(String apiKey, String apiToken, HTTPClient httpclient) {
		super(apiKey, apiToken, httpclient);
	}

	/**
	 * 分页拉取短信发送状态
	 * @param page 页数，第几页
	 * @param pageSize 每页显示数
	 * @return
	 * @throws HTTPException
	 * @throws IOException
	 */
	public SmsStatusPullerResult pullStatus(int page, int pageSize) throws HTTPException, IOException {
		return pullStatus(page, pageSize, null, null, null, null);
	}
	
	/**
	 * 根据单个平台批次号分页拉取短信发送状态
	 * @param page 页数，第几页
	 * @param pageSize 每页显示数
	 * @param taskId 单个平台批次号
	 * @return
	 * @throws HTTPException
	 * @throws IOException
	 */
	public SmsStatusPullerResult pullStatus(int page, int pageSize, String taskId) throws HTTPException, IOException {
		return pullStatus(page, pageSize, taskId, null, null, null);
	}
	
	/**
	 * 根据单个平台批次号和单个手机号分页拉取短信发送状态
	 * @param page 页数，第几页
	 * @param pageSize 每页显示数
	 * @param taskId 单个平台批次号
	 * @param phoneNo 单个手机号
	 * @return
	 * @throws HTTPException
	 * @throws IOException
	 */
	public SmsStatusPullerResult pullStatus(int page, int pageSize, String taskId, String phoneNo) throws HTTPException, IOException {
		return pullStatus(page, pageSize, taskId, phoneNo, null, null);
	}
	
	/**
	 * 根据时间范围分页拉取短信发送状态
	 * @param page 页数，第几页
	 * @param pageSize 每页显示数
	 * @param beginTime 开始时间，如"2019-01-01 00:00:00"的范围是大于等于此时间
	 * @param endTime 结束时间，如"2019-01-31 23:59:59"的范围是小于等于此时间
	 * @return
	 * @throws HTTPException
	 * @throws IOException
	 */
	public SmsStatusPullerResult pullStatusByTime(int page, int pageSize, String beginTime, String endTime) throws HTTPException, IOException {
		return pullStatus(page, pageSize, null, null, beginTime, endTime);
	}
	
	/**
	 * 根据条件分页拉取短信发送状态
	 * @param page 页数，第几页
	 * @param pageSize 每页显示数
	 * @param taskId 单个平台批次号
	 * @param phoneNo 单个手机号
	 * @param beginTime 开始时间，如"2019-01-01 00:00:00"的范围是大于等于此时间
	 * @param endTime 结束时间，如"2019-01-31 23:59:59"的范围是小于等于此时间
	 * @return
	 * @throws HTTPException
	 * @throws IOException
	 */
	public SmsStatusPullerResult pullStatus(int page, int pageSize, String taskId, String phoneNo, String beginTime, String endTime) throws HTTPException, IOException {
		String sign = SmsSenderUtil.string2MD5(this.apiKey + this.apiToken);
		String taskIdStr = SmsSenderUtil.isNotEmpty(taskId) ? taskId : "";
		String phoneNoStr = SmsSenderUtil.isNotEmpty(phoneNo) ? phoneNo : "";
		String beginTimeStr = SmsSenderUtil.isNotEmpty(beginTime) ? beginTime : "";
		String endTimeStr = SmsSenderUtil.isNotEmpty(endTime) ? endTime : "";
		String body = "apiKey=" + this.apiKey + "&phoneNo=" + phoneNoStr + "&taskId=" + taskIdStr +
				"&sign=" + sign + "&beginTime=" + beginTimeStr + "&endTime=" + endTimeStr + 
				"&page=" + page + "&pageSize=" + pageSize;
		
	    HTTPRequest req = new HTTPRequest(HTTPMethod.POST, this.url)
	            .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
	            .setConnectionTimeout(60 * 1000)
	            .setRequestTimeout(60 * 1000)
	            .setBody(body);

        try {
            // May throw IOException and URISyntaxexception
            HTTPResponse res = httpclient.fetch(req);

            // May throw HTTPException
            handleError(res);

            return (new SmsStatusPullerResult()).parseFromHTTPResponse(res);
        } catch (URISyntaxException e) {
            throw new RuntimeException("API url has been modified, current url: " + url);
        }
	}
}
