package com.util;

import com.po.Return;
import sun.applet.resources.MsgAppletViewer;

//return的返回工具类
//四种返回结果（成功，失败，有参，无参）
public class ReturnUtil {
    public static String success="true";
    public static String fail="false";
    public static String errorId="0";
    //全部返回成功，无参
    public static Return returnSuccess(){
        Return ret=new Return();
        ret.setResult(success);
        return ret;
    }
    //全部返回成功,带全参
    public static Return returnSuccess(String msg,Object data){
        Return ret=new Return();
        ret.setResult(success);
        ret.setMsg(msg);
        ret.setErrorId(errorId);
        ret.setData(data);
        return ret;
    }
    //全部返回成功,有信息
    public static Return returnSuccess(String msg){
        Return ret=new Return();
        ret.setResult(success);
        ret.setMsg(msg);
        ret.setErrorId(errorId);
        return ret;
    }
    //全部返回成功,有数据
    public static Return returnSuccess(Object data){
        Return ret=new Return();
        ret.setResult(success);
        ret.setErrorId(errorId);
        ret.setData(data);
        return ret;
    }
    //全部返回失败，有失败信息或者编号
    public static Return returnFail(String msg,String errorId){
        Return ret=new Return();
        ret.setResult(fail);
        ret.setErrorId(errorId);
        ret.setMsg(msg);
        return ret;
    }
}
