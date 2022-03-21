package com.grpc.model.BSN;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Api {

    // 应用方的api_secret
    public static final String APISECRET = "AKIDz8krbsJ5yKBZQpn74WFkmLPx3gn";

    public static void main(String args[]) {
//示例参数,此示例为创建nft类别的参数
//三种参数获取方式:路径中参数,query中参数,以及body中参数
//只要有参数就要参与签名,无论是以上哪种方式给出的参数
        Map<String, Object> params = new HashMap() {
            {
                put("data", "demo");
                put("description", "demo");
                put("name", "demo");
                put("operation_id", "demo");
                put("owner", "demo");
                put("symbol", "demo");
                put("uri", "https://www.bianjie.ai/");
                put("uri_hash", "demo");
            }
        };
//获取当前时间戳(毫秒级)
        long l = System.currentTimeMillis();
        String timeStamp = String.valueOf(l);
        System.out.println("时间戳:" + l);
//生成signature
        String signature = Signature(params, APISECRET, timeStamp);
        System.out.println("signature:" + signature);
    }

    public static String Signature(Map<String, Object> params, String apiSecret, String timeStamp) {
//使用SHA256(Params+Timestamp+ApiSecret)生成signature
        String s = JSON.toJSONString(params, SerializerFeature.MapSortField);
        String res = s + timeStamp + apiSecret;
        String oriTextHashBytes = getSHA256StrJava(res);
        return oriTextHashBytes;
    }

    /**
     * 利用java原生的摘要实现SHA256加密
     *
     * @param str 加密后的报文
     * @return
     */
    public static String getSHA256StrJava(String str) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    /**
     * 将byte转为16进制
     *
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
//1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}
