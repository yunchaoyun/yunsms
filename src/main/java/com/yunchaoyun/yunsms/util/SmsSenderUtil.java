package com.yunchaoyun.yunsms.util;

import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

import org.apache.commons.codec.digest.DigestUtils;

public class SmsSenderUtil {
	
	public static boolean isNotEmpty(String s) {
        if (s == null || s.isEmpty())
            return false;
        return true;
    }

    public static String getCurrentTime() {
		ZoneOffset zoneOffset = ZoneOffset.ofHours(8);
		LocalDateTime localDateTime = LocalDateTime.now();
		String time = String.valueOf(localDateTime.toEpochSecond(zoneOffset));
		
		return time;
    }


    public static String calculateSignature(String appkey, long random, long time,
            String phoneNumber) {

        StringBuffer buffer = new StringBuffer("appkey=")
            .append(appkey)
            .append("&random=")
            .append(random)
            .append("&time=")
            .append(time)
            .append("&mobile=")
            .append(phoneNumber);

        return sha256(buffer.toString());
    }

    public static String calculateSignature(String appkey, long random, long time,
            String[] phoneNumbers) {

        StringBuffer buffer = new StringBuffer("appkey=")
            .append(appkey)
            .append("&random=")
            .append(random)
            .append("&time=")
            .append(time)
            .append("&mobile=");

        if (phoneNumbers.length > 0) {
            buffer.append(phoneNumbers[0]);
            for (int i = 1; i < phoneNumbers.length; i++) {
                buffer.append(",");
                buffer.append(phoneNumbers[i]);
            }
        }

        return sha256(buffer.toString());
    }

    public static String calculateSignature(String appkey, long random, long time,
            ArrayList<String> phoneNumbers) {
        return calculateSignature(appkey, random, time, phoneNumbers.toArray(new String[0]));
    }
    
    public static String string2MD5(String inStr){  
        MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5"); 
        }catch (Exception e){  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = inStr.toCharArray();  
		byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
    }

    public static String calculateSignature(String appkey, long random, long time) {

        StringBuffer buffer = new StringBuffer("appkey=")
            .append(appkey)
            .append("&random=")
            .append(random)
            .append("&time=")
            .append(time);

        return sha256(buffer.toString());
    }

    public static String calculateFStatusSignature(String appkey, long random,
            long time, String fid) {

        StringBuffer buffer = new StringBuffer("appkey=")
            .append(appkey)
            .append("&random=")
            .append(random)
            .append("&time=")
            .append(time)
            .append("&fid=")
            .append(fid);

        return sha256(buffer.toString());
    }

    public static String calculateAuth(String appkey, long random, long time, String fileSha1Sum) {
        StringBuffer buffer = new StringBuffer("appkey=")
            .append(appkey)
            .append("&random=")
            .append(random)
            .append("&time=")
            .append(time)
            .append("&content-sha1=")
            .append(fileSha1Sum);

        return sha256(buffer.toString());
    }

    public static String sha1sum(String rawString) {
        return DigestUtils.sha1Hex(rawString);
    }

    public static String sha1sum(byte[] bytes) {
        return DigestUtils.sha1Hex(bytes);
    }

    public static String sha256(String rawString) {
        return DigestUtils.sha256Hex(rawString);
    }
}
