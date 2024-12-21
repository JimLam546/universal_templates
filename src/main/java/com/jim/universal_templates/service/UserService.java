package com.jim.universal_templates.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jim.universal_templates.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jim.universal_templates.entity.request.UserQueryRequest;
import com.jim.universal_templates.entity.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author Jim_Lam
* @description 针对表【user】的数据库操作Service
* @createDate 2024-12-19 15:45:42
*/
public interface UserService extends IService<User> {

    Long userRegister(String userAccount, String userPassword, String checkPassword);

    String getEncryptPassword(String userPassword);

    UserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    UserVO getSaveUser(User user);

    UserVO getLoginUser(HttpServletRequest request);

    boolean userLogout(HttpServletRequest request);

    UserVO getUserVOById(Long userId, HttpServletRequest request);

    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);

    List<UserVO> getUserVOList(List<User> userList);
}
