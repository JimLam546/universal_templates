package com.jim.universal_templates.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jim.universal_templates.entity.User;
import com.jim.universal_templates.entity.vo.UserVO;
import com.jim.universal_templates.exception.BusinessException;
import com.jim.universal_templates.exception.ErrorCode;
import com.jim.universal_templates.exception.ThrowUtils;
import com.jim.universal_templates.service.UserService;
import com.jim.universal_templates.mapper.UserMapper;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.jim.universal_templates.constant.UserConstant.USER_LOGIN_STATE;

/**
* @author Jim_Lam
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-12-19 15:45:42
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{


    /**
     * 用户注册
     *
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @param checkPassword 确认Miami
     * @return 用户id
     */
    @Override
    public Long userRegister(String userAccount, String userPassword, String checkPassword) {
        if (StrUtil.hasBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 1. 参数校验
        if (!userPassword.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次密码输入不一致");
        }
        if (userAccount.length() < 8 || userAccount.length() > 12) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号不能小于8位, 不能超过12位!");
        }
        if (userPassword.length() < 8 || userPassword.length() > 20) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码不能小于8位, 不能超过20位!");
        }
        // 2. 账号是否存在
        Long count = this.lambdaQuery().eq(User::getUserPassword, userPassword).eq(User::getUserAccount, userAccount).count();
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号已存在");
        }
        // 3. 用户密码加密
        String encryptPassword = getEncryptPassword(userPassword);
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        // 注册用户设置为普通用户权限
        user.setUserRole("user");
        user.setUsername("无名字");
        boolean save = this.save(user);
        if (!save) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
        }
        return user.getId();
    }



    /**
     * 获取 md5 加密的密码字符串
     *
     * @param userPassword 用户密码
     * @return 加密字符串
     */
    @Override
    public String getEncryptPassword(String userPassword) {
        // 加密盐
        final String ENCRYPTION_SALTS = "Jim";
        return DigestUtil.md5Hex((ENCRYPTION_SALTS + userPassword).getBytes());
    }

    @Override
    public UserVO userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // 1. 参数校验
        ThrowUtils.throwIf(StrUtil.hasBlank(userAccount, userPassword), ErrorCode.PARAMS_ERROR);
        if (userAccount.length() < 8 || userAccount.length() > 12) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号不能小于8位, 不能超过12位!");
        }
        if (userPassword.length() < 8 || userPassword.length() > 20) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码不能小于8位, 不能超过20位!");
        }
        // 2. 对用密码加密
        String encryptPassword = getEncryptPassword(userPassword);
        // 3. 查询账号密码是否匹配
        User user = this.lambdaQuery()
                .eq(User::getUserAccount, userAccount)
                .eq(User::getUserPassword, encryptPassword).one();
        if (user == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号不存在或密码错误");
        }
        // 保存用户登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, user);
        return getSaveUser(user);

    }

    @Override
    public UserVO getSaveUser(User user) {
        return BeanUtil.copyProperties(user, UserVO.class);
    }

    @Override
    public UserVO getLoginUser(HttpServletRequest request) {
        Object attribute = request.getSession().getAttribute(USER_LOGIN_STATE);
        ThrowUtils.throwIf(attribute == null, ErrorCode.NOT_LOGIN_ERROR);
        User loginUser = (User) attribute;
        User user = this.getById(loginUser.getId());
        ThrowUtils.throwIf(user == null, ErrorCode.NOT_LOGIN_ERROR);
        return this.getSaveUser(user);
    }

    @Override
    public boolean userLogout(HttpServletRequest request) {
        Object attribute = request.getSession().getAttribute(USER_LOGIN_STATE);
        ThrowUtils.throwIf(attribute == null, ErrorCode.NOT_LOGIN_ERROR);
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return true;
    }
}




