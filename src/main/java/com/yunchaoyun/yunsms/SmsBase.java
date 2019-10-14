package com.yunchaoyun.yunsms;

import com.yunchaoyun.yunsms.httpclient.HTTPClient;
import com.yunchaoyun.yunsms.httpclient.HTTPException;
import com.yunchaoyun.yunsms.httpclient.HTTPResponse;

public class SmsBase {
	
	protected String apiKey;
	
	protected String apiToken;

    protected HTTPClient httpclient;
    
    /**
     * SmsBase constructor
     *
     * @param apiKey apiKey
     * @param apiToken apiToken
     * @param httpclient  http client
     */
    public SmsBase(String apiKey, String apiToken, HTTPClient httpclient) {
    	this.apiKey = apiKey;
    	this.apiToken = apiToken;
        this.httpclient = httpclient;
    }

    /**
     * Handle http status error
     *
     * @param response   raw http response
     * @return response  raw http response
     * @throws HTTPException  http status exception
     */
    public HTTPResponse handleError(HTTPResponse response) throws HTTPException {
        if (response.statusCode < 200 || response.statusCode >= 300) {
            throw new HTTPException(response.statusCode, response.reason);
        }
        return response;
    }
}
