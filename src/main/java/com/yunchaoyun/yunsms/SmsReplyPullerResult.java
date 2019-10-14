package com.yunchaoyun.yunsms;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yunchaoyun.yunsms.httpclient.HTTPResponse;

public class SmsReplyPullerResult extends SmsResultBase {
	
	public String code;
	public String errMsg;
	public List<SmsReplyPullerReplyDomain> result;

    public SmsReplyPullerResult() {
    	this.code = "0";
        this.errMsg = "OK";
        this.result = new ArrayList<SmsReplyPullerReplyDomain>();
    }

	@Override
	public SmsReplyPullerResult parseFromHTTPResponse(HTTPResponse response) {
		
		JSONObject json = parseToJson(response);
        code = json.getString("code");
        errMsg = json.getString("errMsg");

        if(json.containsKey("result")) {
        	result = JSON.parseArray(json.getString("result"), SmsReplyPullerReplyDomain.class);
        }
        
        return this;
	}

}
