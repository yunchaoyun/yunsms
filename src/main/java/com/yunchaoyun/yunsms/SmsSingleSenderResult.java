package com.yunchaoyun.yunsms;

import com.alibaba.fastjson.JSONObject;
import com.yunchaoyun.yunsms.httpclient.HTTPResponse;

public class SmsSingleSenderResult extends SmsResultBase {

	public String code;
	public String errMsg;
	public String ext;
	public String taskId;

    public SmsSingleSenderResult() {
    	this.code = "0";
        this.errMsg = "OK";
        this.ext = "";
        this.taskId = "";
    }

    @Override
    public SmsSingleSenderResult parseFromHTTPResponse(HTTPResponse response) {

        JSONObject json = parseToJson(response);

        code = json.getString("code");
        errMsg = json.getString("errMsg");

        if (json.containsKey("ext")) {
            ext = json.getString("ext");
        }
        if (json.containsKey("taskId")) {
        	taskId = json.getString("taskId");
        }
        
        return this;
    }
}
