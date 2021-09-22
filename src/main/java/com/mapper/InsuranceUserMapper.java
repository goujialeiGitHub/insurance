package com.mapper;

import com.po.InsuranceUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InsuranceUserMapper {
    //注册用户
    public int save(InsuranceUser insuranceUser);
    //判断用户是否已注册
    public InsuranceUser findByUserId(String userId);
    //获取激活码
    public int updateActivated(String userId);
}
