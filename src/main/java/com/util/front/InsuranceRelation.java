package com.util.front;

import java.io.Serializable;
//封装前后台交互数据
public class InsuranceRelation implements Serializable {
    private String userId;      //用户账号
    private String userName;    //姓名
    private String idNumber;    //身份证
    private String userPhone;   //联系方式
    private String userPassword;//密码

    public InsuranceRelation() {
    }

    public InsuranceRelation(String userId, String userName, String idNumber, String userPhone, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.idNumber = idNumber;
        this.userPhone = userPhone;
        this.userPassword = userPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "InsuranceRelation{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
