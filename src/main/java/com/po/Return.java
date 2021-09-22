package com.po;

/**
 * 数据传输对象（后端输出对象）
 * 通用结果返回前台对象（通过ajax+json+String 返回后台数据部分）
 */
public class Return<T>{
	private String result; //判断系统是否出错做出相应的true或者false的返回
	private String errorId;//该错误码为自定义，一般0表示无错
	private String msg;//对应的提示信息
	private Object data;//具体返回数据内容(pojo、前后台交互front、其他)
	public Object getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getErrorId() {
		return errorId;
	}
	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}