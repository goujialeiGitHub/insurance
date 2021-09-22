package com.util;

/**
 * 系统错误编码，根据业务定义如下
 * 用户部分编码以10000开始
 *
 *
 */
public class ErrorCode {
	/*认证模块错误码-start*/
	public final static String AUTH_UNKNOWN="10000";//异常反馈
	public final static String AUTH_USER_ALREADY_EXISTS="10001";//注册失败，后台数据异常
	public final static String AUTH_AUTHENTICATION_FAILED="10002";//注册失败,账户已注册且激活,请登录
    public final static String AUTH_AUTHENTICATION_UPDATE="10003";//激活码错误，激活失败，重新填写激活码
	public final static String AUTH_PARAMETER_ERROR="10004";//注册失败，该账户已注册未激活已重新发送验证码
	public final static String AUTH_ACTIVATE_FAILED="10005";//激活码错误，激活失败，重新发送激活码
	public final static String AUTH_REPLACEMENT_FAILED="10006";//激活失败，该账号已激活
	public final static String AUTH_TOKEN_INVALID="10007";//激活失败，该账号还未注册
	public static final String AUTH_ILLEGAL_USERCODE = "10008";//登录失败，该账号还未注册
	public static final String AUTH_ILLEGAL_USERCOD1 = "10009";//前台传输数据有误
	public static final String AUTH_ILLEGAL_USERCODE2 = "10010";//密码不正确或未激活
	public static final String AUTH_ILLEGAL_USERCODE3 = "10011";//登录失败，密码错误
	public static final String AUTH_ILLEGAL_USERCODE4 = "10012";//登录失败，密码错
	/*认证模块错误码-end*/
}
