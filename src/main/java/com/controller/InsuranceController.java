package com.controller;

import com.alibaba.fastjson.JSON;
import com.po.InsuranceUser;
import com.po.Return;
import com.service.InsuranceService;
import com.util.*;
import com.util.front.InsuranceRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping(value = "controller")
public class InsuranceController {
    @Autowired
    private InsuranceService insuranceService;
    private Jedis jedis = new Jedis("127.0.0.1", 6379);

    //注册账户
    @RequestMapping(value = "saveUser")
    private Return saveUser(@RequestBody InsuranceRelation insuranceRelation) {
        System.out.println("注册。。。。" + insuranceRelation.toString());
        try {
            //处理前台页面传递的数据
            InsuranceUser oldUser = new InsuranceUser();
            oldUser.setUserId(insuranceRelation.getUserId());
            oldUser.setUserName(insuranceRelation.getUserName());
            oldUser.setUserPhone(insuranceRelation.getUserPhone());
            oldUser.setIdNumber(insuranceRelation.getIdNumber());
            //自处理数据
            oldUser.setUserType(1);
            oldUser.setCreationTime(new Date());
            oldUser.setCreator(insuranceRelation.getUserName());
            oldUser.setModifyDate(new Date());
            oldUser.setModifiers(insuranceRelation.getUserName());
            oldUser.setActivated(0);
            String passwd = MD5Util.getMd5(insuranceRelation.getUserPassword(), 32);
            oldUser.setUserPassword(passwd);
            int code = insuranceService.save(oldUser);
            if (code > 0) {
                this.emailAndMsg(oldUser, EmailUtil.emailRegister(oldUser), MsgUtil.msgRegister(oldUser.getUserId()));
                return ReturnUtil.returnSuccess("注册成功");
            } else {
                return ReturnUtil.returnFail("注册失败,后台数据错误", ErrorCode.AUTH_USER_ALREADY_EXISTS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.returnFail("用户注册异常" + e.getMessage(), ErrorCode.AUTH_UNKNOWN);
        }
    }
    //查询账户是否已存在
    @RequestMapping(value = "findByUserId")
    private Return findByUserId(@RequestBody InsuranceRelation insuranceRelation) {
        if (insuranceRelation != null && insuranceRelation.getUserId() != null) {
            InsuranceUser insuranceUser = insuranceService.findByUserId(insuranceRelation.getUserId());
            if (insuranceUser != null) {
                if (insuranceUser.getActivated() == 1) {//账户存在且激活
                    return ReturnUtil.returnFail("注册失败,账户已注册且激活,请登录", ErrorCode.AUTH_AUTHENTICATION_FAILED);
                } else {//账户存在未激活（重新发验证码）
                    this.emailAndMsg(insuranceUser, EmailUtil.emailRegister(insuranceUser), MsgUtil.msgRegister(insuranceUser.getUserId()));
                    return ReturnUtil.returnFail("注册失败，该账户已注册未激活已重新发送验证码", ErrorCode.AUTH_PARAMETER_ERROR);
                }
            }
        }
        return ReturnUtil.returnSuccess();
    }

    //激活注册用户方法
    @RequestMapping(value = "activatedUser/{activatedId}")
    private Return activatedUser(@RequestBody InsuranceRelation insuranceRelation, @PathVariable String activatedId) {
        System.out.println("激活用户。。。。");
        InsuranceUser insuranceUser = insuranceService.findByUserId(insuranceRelation.getUserId());
        if (insuranceUser != null) {//存在账户
            if (insuranceUser.getActivated() == 0) {//激活判断
                if (activatedId.equals(jedis.get(insuranceUser.getUserId()))) {//可激活
                    int code = insuranceService.updateActivated(insuranceUser.getUserId());
                    return ReturnUtil.returnSuccess("激活成功");
                } else {
                    if (jedis.get(insuranceUser.getUserId()) != null) {
                        return ReturnUtil.returnFail("激活码错误，激活失败，重新填写激活码", ErrorCode.AUTH_AUTHENTICATION_UPDATE);
                    } else {
                        this.emailAndMsg(insuranceUser, EmailUtil.emailRegister(insuranceUser), MsgUtil.msgRegister(insuranceUser.getUserId()));
                        return ReturnUtil.returnFail("激活码错误，激活失败，重新发送激活码", ErrorCode.AUTH_ACTIVATE_FAILED);
                    }
                }
            } else {//已激活
                return ReturnUtil.returnFail("激活失败，该账号已激活", ErrorCode.AUTH_REPLACEMENT_FAILED);
            }
        } else {
            return ReturnUtil.returnFail("激活失败，该账号还未注册", ErrorCode.AUTH_TOKEN_INVALID);
        }
    }

    //判断账户是邮箱还是手机号（重复代码整合）
    private void emailAndMsg(InsuranceUser insuranceUser, String emailCode, String msgCode) {
        if (insuranceUser.getUserId().indexOf("@") != -1) {//是邮箱
            //发送邮箱验证码
            emailCode = EmailUtil.emailRegister(insuranceUser);
            jedis.setex(insuranceUser.getUserId(), 120, emailCode);
        } else {//是手机
            msgCode = MsgUtil.msgRegister(insuranceUser.getUserId());
            //缓存激活码时长
            jedis.setex(insuranceUser.getUserId(), 120, msgCode);
        }
    }

    //账户登录
    @RequestMapping(value = "loginUser")
    private Return loginUser(HttpServletRequest request, HttpServletResponse response, @RequestBody InsuranceRelation insuranceRelation) {
        System.out.println("账户登录。。。。");
        try {
            if (insuranceRelation != null && insuranceRelation.getUserId() != null) {
                InsuranceUser insuranceUser = insuranceService.findByUserId(insuranceRelation.getUserId());
                if (insuranceUser != null) {
                    //判断用户是否已经存在（已登录）
                    if (jedis.get(insuranceUser.getUserId()) != null) {
                        String oldtoken = jedis.get(insuranceUser.getUserId());
                        try {
                            //判断相关参数，完成浏览器重复登录
                            String token = TokenUtil.replaceToken(request.getHeader("user-agent"), oldtoken, response);
                            if (token != null) {
                                return ReturnUtil.returnSuccess("登录成功");
                            }
                        } catch (TokenValidationFailedException e) {
                            e.printStackTrace();
                            return ReturnUtil.returnFail(e.getMessage(), ErrorCode.AUTH_UNKNOWN);
                        }
                        return ReturnUtil.returnSuccess("登录成功");
                    } else {
                        //加密密码
                        String md5passwd = MD5Util.getMd5(insuranceRelation.getUserPassword(), 32);
                        if (md5passwd.equals(insuranceUser.getUserPassword()) && insuranceUser.getActivated() == 1) {//登录成功
                            //获取浏览器头
                            String head = request.getHeader("user-agent");
                            //生成token
                            String token = TokenUtil.getTokenGenerator(request.getHeader("user-agent"), insuranceUser);
                            //token存入redis和浏览器中
                            jedis.setex(insuranceUser.getUserId(), 120, token);
                            //将token作为key，用户对象作为value，判断后面用户是否已经登录
                            String jsonUser = JSON.toJSONString(insuranceUser);
                            jedis.setex(token, 120, jsonUser);
                            //将token存到浏览器的cookie中
                            Cookie ctoken = new Cookie("token", token);
                            ctoken.setMaxAge(120);
                            response.addCookie(ctoken);
                            return ReturnUtil.returnSuccess("登录成功");
                        } else {
                            return ReturnUtil.returnFail("密码不正确或未激活", ErrorCode.AUTH_ILLEGAL_USERCODE2);
                        }
                    }
                } else {
                    return ReturnUtil.returnFail("用户还未注册", ErrorCode.AUTH_ILLEGAL_USERCODE);
                }
            } else {
                return ReturnUtil.returnFail("前台传输数据错误", ErrorCode.AUTH_ILLEGAL_USERCOD1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.returnFail("用户登录异常", ErrorCode.AUTH_UNKNOWN);
        }
    }
    }
