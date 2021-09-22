package com.service.Impl;

import com.mapper.InsuranceUserMapper;
import com.po.InsuranceUser;
import com.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InsuranceServiceImpl implements InsuranceService {
    @Autowired
    private InsuranceUserMapper insuranceUserMapper;

    @Override
    public int save(InsuranceUser insuranceUser) {
        return insuranceUserMapper.save(insuranceUser);
    }

    @Override
    public InsuranceUser findByUserId(String userId) {
        return insuranceUserMapper.findByUserId(userId);
    }

    @Override
    public int updateActivated(String userId) {
        return insuranceUserMapper.updateActivated(userId);
    }
}
