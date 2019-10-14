package com.yunchaoyun.yunsms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.yunchaoyun.yunsms.httpclient.HTTPResponse;

/**
 * Sms result base class
 *
 * @since 1.0.0
 */
public abstract class SmsResultBase {
	
	protected HTTPResponse response;

    /**
     * Parse result from HTTPResponse
     *
     * @param response  HTTP response from api return
     * @return SmsResultbase
     * @throws JSONException  json parse exception
     */
    public abstract SmsResultBase parseFromHTTPResponse(HTTPResponse response);

    /**
     * Parse HTTP response to JSONObject
     *
     * @param response  HTTP response
     * @return JSONObject
     * @throws JSONException  json parse exception
     */
    public JSONObject parseToJson(HTTPResponse response) {
        this.response = response;
        
        return JSON.parseObject(response.body);
    }

    /**
     * Get raw response
     *
     * @return HTTPResponse
     */
    public HTTPResponse getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return response.body;
    }
}
