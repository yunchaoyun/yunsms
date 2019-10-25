package com.yunchaoyun.yunsms;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yunchaoyun.yunsms.httpclient.HTTPResponse;

public class SmsStatusPullerResult extends SmsResultBase {
	
	public String code;
	public String errMsg;
	public List<SmsStatusPullerStatusDomain> result;

    public SmsStatusPullerResult() {
    	this.code = "0";
        this.errMsg = "ok";
        this.result = new ArrayList<SmsStatusPullerStatusDomain>();
    }

	@Override
	public SmsStatusPullerResult parseFromHTTPResponse(HTTPResponse response) {
		
		JSONObject json = parseToJson(response);
        code = json.getString("code");
        errMsg = json.getString("errMsg");

        if(json.containsKey("result")) {
        	result = JSON.parseArray(json.getString("result"), SmsStatusPullerStatusDomain.class);
        }
        
        return this;
	}

}
