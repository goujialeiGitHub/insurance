package com.po;

import java.io.Serializable;
import java.util.Date;
//用户信息
public class InsuranceUser implements Serializable {
    private Integer id;         //主键
    private String userId;      //用户账号
    private String userPhone;   //联系方式
    private String userName;    //姓名
    private String userPassword;//密码
    private String idNumber;    //身份证
    private Integer userType;   //用户类型(标识：0管理员 1自注册用户 2保险销售部门 3风险合规部)
    private Date creationTime;  //创建事件
    private String creator;    //创建人
    private Date modifyDate;  //修改时间
    private String modifiers;   //修改人
    private Integer activated;  //是否激活

    public InsuranceUser() {
    }

    public InsuranceUser(Integer id, String userId, String userPhone, String userName, String userPassword, String idNumber, Integer userType, Date creationTime, String creator, Date modifyDate, String modifiers, Integer activated) {
        this.id = id;
        this.userId = userId;
        this.userPhone = userPhone;
        this.userName = userName;
        this.userPassword = userPassword;
        this.idNumber = idNumber;
        this.userType = userType;
        this.creationTime = creationTime;
        this.creator = creator;
        this.modifyDate = modifyDate;
        this.modifiers = modifiers;
        this.activated = activated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifiers() {
        return modifiers;
    }

    public void setModifiers(String modifiers) {
        this.modifiers = modifiers;
    }

    public Integer getActivated() {
        return activated;
    }

    public void setActivated(Integer activated) {
        this.activated = activated;
    }

    @Override
    public String toString() {
        return "InsuranceUser{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", userType=" + userType +
                ", creationTime=" + creationTime +
                ", creator=" + creator +
                ", modifyDate='" + modifyDate + '\'' +
                ", modifiers='" + modifiers + '\'' +
                ", activated=" + activated +
                '}';
    }
}
