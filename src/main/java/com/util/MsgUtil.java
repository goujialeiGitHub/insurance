package com.util;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.po.InsuranceUser;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

//手机号激活
public class MsgUtil {
    public static String msgRegister(String userId){
        //初始化sdk
        CCPRestSmsSDK ccpRestSmsSDK=new CCPRestSmsSDK();
        //初始化服务器端口和地址
        ccpRestSmsSDK.init("app.cloopen.com","8883");
        //设置账号和token
        ccpRestSmsSDK.setAccount("8aaf07087bc82708017bec89b3f209a1","52ae3fcb9a89483c9f247530f60882f6");
        //设置appid
        ccpRestSmsSDK.setAppId("8aaf07087bc82708017bec89b4c609a8");
        //获取系统当前时间
        Date date=new Date();
        long datetime=date.getTime();
        String strdate=datetime+"";
        //截取时间毫秒数作为验证码
        String smsdk=strdate.substring(strdate.length()-4);
        System.out.println("验证码："+smsdk);
        //设置内容和时长
        HashMap<String,Object> result=ccpRestSmsSDK.sendTemplateSMS(userId,"1",new String[]{smsdk,"3"});
        //判断短信是否发送成功
        if("000000".equals(result.get("statusCode"))){
            //正常返回输出data包体信息（map）
            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for(String key:keySet){
                Object obj = data.get(key);
                System.out.println(key +" = "+obj);
            }
        }else{
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
        }
        return smsdk;
    }
}
