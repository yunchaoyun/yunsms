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

public class SmsReplyPuller extends SmsBase {
	
	private String url = "https://api.yunchaoyun.com/yun/api/sms/v1/pullreply";
	
	public SmsReplyPuller(String apiKey, String apiToken) {
		super(apiKey, apiToken, new DefaultHTTPClient());
	}

	public SmsReplyPuller(String apiKey, String apiToken, HTTPClient httpclient) {
		super(apiKey, apiToken, httpclient);
	}
	
	/**
	 * 分页拉取短信上行
	 * @param page 当前页数，第几页
	 * @param pageSize 每页显示数
	 * @return
	 * @throws HTTPException
	 * @throws IOException
	 */
	public SmsReplyPullerResult pullReply(int page, int pageSize) throws HTTPException, IOException {
		return pullReply(page, pageSize, null, null);
	}
	
	/**
	 * 分页拉取短信上行
	 * @param page 当前页数，第几页
	 * @param pageSize 每页显示数
	 * @param beginTime 开始时间，如"2019-01-01 00:00:00"的范围是大于等于此时间
	 * @param endTime 结束时间，如"2019-01-31 23:59:59"的范围是小于等于此时间
	 * @return
	 * @throws HTTPException
	 * @throws IOException
	 */
	public SmsReplyPullerResult pullReplyByTime(int page, int pageSize, String beginTime, String endTime) throws HTTPException, IOException {
		return pullReply(page, pageSize, beginTime, endTime);
	}
	
	/**
	 * 根据条件分页拉取短信上行
	 * @param page 当前页数，第几页
	 * @param pageSize 每页显示数
	 * @param beginTime 开始时间，如"2019-01-01 00:00:00"的范围是大于等于此时间
	 * @param endTime 结束时间，如"2019-01-31 23:59:59"的范围是小于等于此时间
	 * @return
	 * @throws HTTPException
	 * @throws IOException
	 */
	public SmsReplyPullerResult pullReply(int page, int pageSize, String beginTime, String endTime) throws HTTPException, IOException {
		String sign = SmsSenderUtil.string2MD5(this.apiKey + this.apiToken);
		String beginTimeStr = SmsSenderUtil.isNotEmpty(beginTime) ? beginTime : "";
		String endTimeStr = SmsSenderUtil.isNotEmpty(endTime) ? endTime : "";
		String body = "apiKey=" + this.apiKey + "&sign=" + sign + 
				"&beginTime=" + beginTimeStr + "&endTime=" + endTimeStr + 
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

            return (new SmsReplyPullerResult()).parseFromHTTPResponse(res);
        } catch (URISyntaxException e) {
            throw new RuntimeException("API url has been modified, current url: " + url);
        }
	}
}
