package com.mimi.zfw.service;

import com.mimi.zfw.mybatis.pojo.User;
import com.mimi.zfw.mybatis.pojo.UserExample;

public interface IUserService extends IBaseService<User, UserExample, String> {

    public User saveOriginUser(User user);
    
    public String updatePassword(User user);

    public User findByName(String name);

    public User findByEmail(String email);
    
    public User findByPhoneNum(String phoneNum);
    
    public User findByLoginName(String loginName);

    public void initUser();

    public void login(String name, String password);
    
    public void login(String loginName);
    
    public boolean checkNameFormat(String name);
    
    public boolean checkPhoneNumFormat(String phoneNum);
    
    public boolean checkEamilFormat(String eamil);

    public String updateCurUserHeadImgUrl(String headImgUrl);

    public User getCurUser();
    
    public boolean isLogined();

}
