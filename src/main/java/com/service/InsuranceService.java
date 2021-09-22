package com.service;

import com.po.InsuranceUser;

public interface InsuranceService {
    //注册
    public int save(InsuranceUser insuranceUser);
    //判断用户是否已注册
    public InsuranceUser findByUserId(String userId);
    //获取激活码
    public int updateActivated(String userId);
}
