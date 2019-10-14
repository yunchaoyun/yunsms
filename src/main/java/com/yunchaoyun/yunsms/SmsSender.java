package com.yunchaoyun.yunsms;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.yunchaoyun.yunsms.httpclient.DefaultHTTPClient;
import com.yunchaoyun.yunsms.httpclient.HTTPClient;
import com.yunchaoyun.yunsms.httpclient.HTTPException;
import com.yunchaoyun.yunsms.httpclient.HTTPMethod;
import com.yunchaoyun.yunsms.httpclient.HTTPRequest;
import com.yunchaoyun.yunsms.httpclient.HTTPResponse;
import com.yunchaoyun.yunsms.util.SmsSenderUtil;


/**
 * sms短信发送类
 * @author teli_
 *
 */
public class SmsSender extends SmsBase {
	
	private String url = "https://api.yunchaoyun.com/yun/api/sms/v1/sendmsg";
	
	
	public SmsSender(String apiKey, String apiToken) {
		super(apiKey, apiToken, new DefaultHTTPClient());
	}

	public SmsSender(String apiKey, String apiToken, HTTPClient httpclient) {
		super(apiKey, apiToken, httpclient);
	}
	
	/**
	 * 指定模版签名单发
	 * @param phoneNumber 不带国家码的手机号
	 * @param smsSign 签名内容，不是签名编号
	 * @param templateNo 模版编号，不是模版内容
	 * @param ext 服务端原样返回的参数，可填空
	 * @return
	 * @throws HTTPException
	 * @throws IOException
	 */
	public SmsSingleSenderResult send(String phoneNumber, String smsSign, String templateNo, String ext) throws HTTPException, IOException {
		
		return sendWithParams("86", phoneNumber, smsSign, templateNo, null, ext);
	}
	
	/**
	 * 指定模版签名国家码单发
	 * @param nationCode 国家码，如 86 为中国
	 * @param phoneNumber 不带国家码的手机号
	 * @param smsSign 签名内容，不是签名编号
	 * @param templateNo 模版编号，不是模版内容
	 * @param ext 服务端原样返回的参数，可填空
	 * @return
	 * @throws HTTPException
	 * @throws IOException
	 */
	public SmsSingleSenderResult send(String nationCode, String phoneNumber, String smsSign, String templateNo, String ext) throws HTTPException, IOException {
	
		return sendWithParams(nationCode, phoneNumber, smsSign, templateNo, null, ext);
	}

	/**
	 * 指定模版签名单发带参数
	 * @param phoneNumber 不带国家码的手机号
	 * @param smsSign 签名内容，不是签名编号
	 * @param templateNo 模版编号，不是模版内容
	 * @param params 模板参数列表，如模板 {1}...{2}...{3}，那么需要带三个参数
	 * @param ext 服务端原样返回的参数，可填空
	 * @return
	 * @throws HTTPException
	 * @throws IOException
	 */
	public SmsSingleSenderResult sendWithParams(String phoneNumber, String smsSign, String templateNo, String[] params, String ext)  throws HTTPException, IOException {
		
		return sendWithParams("86", phoneNumber, smsSign, templateNo, params, ext);
	}
	
	/**
	 * 指定模版签名单发
	 * @param nationCode 国家码，如 86 为中国
	 * @param phoneNumber 不带国家码的手机号
	 * @param smsSign 签名内容，不是签名编号
	 * @param templateNo 模版编号，不是模版内容
	 * @param params 模板参数列表，如模板 {1}...{2}...{3}，那么需要带三个参数
	 * @param ext 服务端原样返回的参数，可填空
	 * @return
	 * @throws HTTPException
	 * @throws IOException
	 */
	public SmsSingleSenderResult sendWithParams(String nationCode, String phoneNumber, String smsSign, String templateNo,
	        String[] params, String ext) throws HTTPException, IOException {
	
		String time = SmsSenderUtil.getCurrentTime();
		String extStr = SmsSenderUtil.isNotEmpty(ext) ? ext : "";
		String sign = SmsSenderUtil.string2MD5(this.apiKey + this.apiToken);
		String paramsStr = null == params ? "" : String.join(",", params);
		String body = "apiKey=" + this.apiKey + "&time=" + time +
				"&templateNo=" + templateNo + "&phoneNo=" + phoneNumber +
				"&smsSign=" + smsSign + "&sign=" + sign + "&params=" + paramsStr +
				"&ext=" + extStr;
		
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

            return (new SmsSingleSenderResult()).parseFromHTTPResponse(res);
        } catch (URISyntaxException e) {
            throw new RuntimeException("API url has been modified, current url: " + url);
        }
	}
	
	/**
	 * 指定模版签名群发
	 * @param phoneNumbers 不带国家码的手机号数组
	 * @param smsSign 签名内容，不是签名编号
	 * @param templateNo 模版编号，不是模版内容
	 * @param ext 服务端原样返回的参数，可填空
	 * @return
	 * @throws HTTPException
	 * @throws IOException
	 */
	public SmsMultiSenderResult multiSend(String[] phoneNumbers, String smsSign, String templateNo, String ext) throws HTTPException, IOException {
		return multiSend(new ArrayList<String>(Arrays.asList(phoneNumbers)), smsSign, templateNo, ext);
	}
	
	/**
	 * 指定模版签名群发
	 * @param phoneNumbers 不带国家码的手机号集合
	 * @param smsSign 签名内容，不是签名编号
	 * @param templateNo 模版编号，不是模版内容
	 * @param ext 服务端原样返回的参数，可填空
	 * @return
	 * @throws HTTPException
	 * @throws IOException
	 */
	public SmsMultiSenderResult multiSend(List<String> phoneNumbers, String smsSign, String templateNo, String ext) throws HTTPException, IOException {
		return multiSend("86", phoneNumbers, smsSign, templateNo, ext);
	}

	/**
	 * 指定模版签名国家码群发
	 * @param nationCode 国家码，如 86 为中国
	 * @param phoneNumbers 不带国家码的手机号集合
	 * @param smsSign 签名内容，不是签名编号
	 * @param templateNo 模版编号，不是模版内容
	 * @param ext 服务端原样返回的参数，可填空
	 * @return
	 * @throws HTTPException
	 * @throws IOException
	 */
	public SmsMultiSenderResult multiSend(String nationCode, List<String> phoneNumbers, String smsSign, String templateNo, String ext) throws HTTPException, IOException {
		return multiSendWithParams(nationCode, phoneNumbers, smsSign, templateNo, null, ext);
	}
	
	/**
	 * 指定模版签名群发带参数
	 * @param phoneNumbers 不带国家码的手机号数组
	 * @param smsSign 签名内容，不是签名编号
	 * @param templateNo 模版编号，不是模版内容
	 * @param params 模板参数列表，如模板 {1}...{2}...{3}，那么需要带三个参数
	 * @param ext 服务端原样返回的参数，可填空
	 * @return
	 * @throws HTTPException
	 * @throws IOException
	 */
	public SmsMultiSenderResult multiSendWithParams(String[] phoneNumbers, String smsSign, String templateNo, String[] params, String ext) throws HTTPException, IOException {
		return multiSendWithParams(new ArrayList<String>(Arrays.asList(phoneNumbers)), smsSign, templateNo, new ArrayList<String>(Arrays.asList(params)), ext);
	}
	
	/**
	 * 指定模版签名群发带参数
	 * @param phoneNumbers 不带国家码的手机号集合
	 * @param smsSign 签名内容，不是签名编号
	 * @param templateNo 模版编号，不是模版内容
	 * @param params 模板参数列表，如模板 {1}...{2}...{3}，那么需要带三个参数
	 * @param ext 服务端原样返回的参数，可填空
	 * @return
	 * @throws HTTPException
	 * @throws IOException
	 */
	public SmsMultiSenderResult multiSendWithParams(List<String> phoneNumbers, String smsSign, String templateNo, List<String> params, String ext) throws HTTPException, IOException {
		return multiSendWithParams("86", phoneNumbers, smsSign, templateNo, params, ext);
	}
	
	/**
	 * 指定模版签名群发
	 * @param nationCode 国家码，如 86 为中国
	 * @param phoneNumbers 不带国家码的手机号
	 * @param smsSign 签名内容，不是签名编号
	 * @param templateNo 模版编号，不是模版内容
	 * @param params 模板参数列表，如模板 {1}...{2}...{3}，那么需要带三个参数
	 * @param ext 服务端原样返回的参数，可填空
	 * @return
	 * @throws HTTPException
	 * @throws IOException
	 */
	public SmsMultiSenderResult multiSendWithParams(String nationCode, List<String> phoneNumbers, String smsSign, String templateNo, List<String> params, String ext) throws HTTPException, IOException {
		String time = SmsSenderUtil.getCurrentTime();
		String exts = SmsSenderUtil.isNotEmpty(ext) ? ext : "";
		String sign = SmsSenderUtil.string2MD5(this.apiKey + this.apiToken);
		String paramsStr = null == params ? "" : String.join(",", params);
		String phoneNumbersStr = null == phoneNumbers? "" : String.join(",", phoneNumbers);
		String body = "apiKey=" + this.apiKey + "&time=" + time +
				"&templateNo=" + templateNo + "&phoneNo=" + phoneNumbersStr +
				"&smsSign=" + smsSign + "&sign=" + sign + "&params=" + paramsStr +
				"&ext=" + exts;
    	
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

            return (new SmsMultiSenderResult()).parseFromHTTPResponse(res);
        } catch (URISyntaxException e) {
            throw new RuntimeException("API url has been modified, current url: " + url);
        }
	}
}
