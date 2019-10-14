# 云潮云短信  java SDK
## 云潮云短信服务
目前云潮云短信为客户提供各种国内短信发送服务，云潮云短信SDK支持以下操作：
- 单发短信
- 指定模版单发短信
- 指定模板单发带变量短信
- 群发短信
- 指定模版群发短信
- 指定模板群发带变量短信
- 拉取短信回执和短信回复状态
>注意：短信拉取功能需要开通相应的权限，一般不建议使用，如确实需要，请联系相关技术人员开通(QQ:3369645866)

### 开发
#### 准备
在开始开发短信应用之前，需要准备如下信息:
1. 登录 www.yunchaoyun.com 完成注册。
2. 进行账户的实名认证，并审核通过。
3. 开通开发者模式，在开发者中心获取SDK  ApiKey和ApiToken。
4. 申请短信签名
一条完整的短信由短信签名跟短信正文内容两部分组成，短信签名必须申请和审核，请在短信控制台完成相关操作
5. 申请模版
同样短信的正文内容模版必须申请和审核，模版可在短信控制台相应模块中申请

#### 安装
yunsms sdk我们提供多种方法供用户使用：
1. 直接将源代码引入到项目工程中，[源代码](https://github.com/yunchaoyun/yunsms "源代码")地址:https://github.com/yunchaoyun/yunsms
2. 直接下载jar包，集成到项目工程中，jar包[下载地址](https://github.com/yunchaoyun/yunsms "下载地址"), lib文件夹中yunsms-1.0.jar。

#### 文档
若您有问题，可以查看我们短信服务相关文档，[文档地址](https://www.yunchaoyun.com/api.html "文档地址")

#### 示例
##### 单发短信
    /**
     * 云潮云短信java sdk示例
     * 
     * @author teli_
     *
     */
    public class SmsTest {
    	
    	//短信必要的参数
    	//开发者中心  apikey
    	public static final String apikey = "xxxxxxxxx";
    	//开发者中心  apiToken
    	public static final String apiToken = "xxxxxxxxxxxxxxxxxxxxxxxx";
    	//短信签名
    	public static final String smsSign = "云潮云平台";
    	//短信审核通过后的模版编号
    	public static final String templateNo = "1910090037";
    	//短信接受人手机号
    	public static String phoneNo = "";
    	//额外参数
    	public static String ext = "";
    
    	public static void main(String[] args) {
    		SmsSender sender = new SmsSender(apikey, apiToken);
    		try {
    			SmsSingleSenderResult result = sender.send(phoneNo, smsSign, templateNo, ext);
    
    			System.out.println(result);
    		} catch (HTTPException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    
    	}
    
    }
##### 单发带变量短信
    import java.io.IOException;
    
    import com.yunchaoyun.yunsms.SmsSender;
    import com.yunchaoyun.yunsms.SmsSingleSenderResult;
    import com.yunchaoyun.yunsms.httpclient.HTTPException;
    
    /**
     * 云潮云短信java sdk示例
     * 
     * @author teli_
     *
     */
    public class SmsTest {
    	
    	//短信必要的参数
    	//开发者中心  apikey
    	public static final String apikey = "xxxxxxxxx";
    	//开发者中心  apiToken
    	public static final String apiToken = "xxxxxxxxxxxxxxxxxxxxxxxx";
    	//短信签名
    	public static final String smsSign = "云潮云平台";
    	//短信审核通过后的模版编号
    	public static final String templateNo = "1910090037";
    	//变量内容  根据短信内容模版中的变量 赋值
    	public static String[] params = {"886655"};
    	
    	//短信接受人手机号
    	public static String phoneNo = "";
    	//额外参数
    	public static String ext = "";
    
    	public static void main(String[] args) {
    		SmsSender sender = new SmsSender(apikey, apiToken);
    		try {
    			SmsSingleSenderResult result = sender.sendWithParams(phoneNo, smsSign, templateNo, params, ext);
    
    			System.out.println(result);
    		} catch (HTTPException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    
    	}
    
    }
##### 群发短信
    package com.zhonghe.yunsms.test;
    
    import java.io.IOException;
    
    import com.yunchaoyun.yunsms.SmsMultiSenderResult;
    import com.yunchaoyun.yunsms.SmsSender;
    import com.yunchaoyun.yunsms.SmsSingleSenderResult;
    import com.yunchaoyun.yunsms.httpclient.HTTPException;
    
    /**
     * 云潮云短信java sdk示例
     * 
     * @author teli_
     *
     */
    public class SmsTest {
    	
    	//短信必要的参数
    	//开发者中心  apikey
    	public static final String apikey = "xxxxxxxxx";
    	//开发者中心  apiToken
    	public static final String apiToken = "xxxxxxxxxxxxxxxxxxxxxxxx";
    	//短信签名
    	public static final String smsSign = "云潮云平台";
    	//短信审核通过后的模版编号
    	public static final String templateNo = "1910090037";
    	
    	
    	//短信接受人手机号
    	public static String[] phoneNos = {"138********","138********", "138********", "138********", "138********"};
    	//额外参数
    	public static String ext = "";
    
    	public static void main(String[] args) {
    		SmsSender sender = new SmsSender(apikey, apiToken);
    		try {
    			SmsMultiSenderResult result = sender.multiSend(phoneNos, smsSign, templateNo, ext);
    			
    			System.out.println(result);
    			
    		} catch (HTTPException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    
    	}
    
    }
    
##### 群发带变量短信
    package com.zhonghe.yunsms.test;
    
    import java.io.IOException;
    
    import com.yunchaoyun.yunsms.SmsMultiSenderResult;
    import com.yunchaoyun.yunsms.SmsSender;
    import com.yunchaoyun.yunsms.httpclient.HTTPException;
    
    /**
     * 云潮云短信java sdk示例
     * 
     * @author teli_
     *
     */
    public class SmsTest {
    	
    	//短信必要的参数
    	//开发者中心  apikey
    	public static final String apikey = "xxxxxxxxx";
    	//开发者中心  apiToken
    	public static final String apiToken = "xxxxxxxxxxxxxxxxxxxxxxxx";
    	//短信签名
    	public static final String smsSign = "云潮云平台";
    	//短信审核通过后的模版编号
    	public static final String templateNo = "1910090037";
    	 //变量内容  根据短信内容模版中的变量 赋值
        public static String[] params = {"886655", "12123r"};
    	
    	//短信接受人手机号
    	public static String[] phoneNos = {"138********","138********", "138********", "138********", "138********"};
    	//额外参数
    	public static String ext = "";
    
    	public static void main(String[] args) {
    		SmsSender sender = new SmsSender(apikey, apiToken);
    		try {
    			SmsMultiSenderResult result = sender.multiSendWithParams(phoneNos, smsSign, templateNo,params, ext);
    			
    			System.out.println(result);
    			
    		} catch (HTTPException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    
    	}
    
    }
    
##### 状态的拉取
    package com.zhonghe.yunsms.test;
    
    import java.io.IOException;
    
    import com.yunchaoyun.yunsms.SmsStatusPuller;
    import com.yunchaoyun.yunsms.SmsStatusPullerResult;
    import com.yunchaoyun.yunsms.httpclient.HTTPException;
    
    /**
     * 云潮云短信平台  短信状态的拉取
     * @author teli_
     *
     */
    public class StatusPullTest {
    	
    	//短信必要的参数
    	//开发者中心  apikey
    	public static final String apikey = "xxxxxxxxx";
    	//开发者中心  apiToken
    	public static final String apiToken = "xxxxxxxxxxxxxxxxxxxxxxxx";
    
    	public static void main(String[] args) {
    		SmsStatusPuller puller = new SmsStatusPuller(apikey, apiToken);
    		
    		int currentPage = 1;
    		int pageSize = 20;
    		
    		//短信发送批次号
    		String taskId = "";
    		
    		try {
    			//直接拉取短信状态
    			SmsStatusPullerResult result = puller.pullStatus(currentPage, pageSize);
    			
    			System.out.println(result);
    			
    			//根据批次号拉取短信状态
    			SmsStatusPullerResult result2 = puller.pullStatus(currentPage, pageSize, taskId);
    			
    			System.out.println(result2);
    		} catch (HTTPException | IOException e) {
    			e.printStackTrace();
    		}
    		
    		
    	}
    	
    }


> 注意上面的这个示例代码只作参考，无法直接编译和运行，需要作相应修改。





