package ccst.sh.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ccst.sh.user.dao.UserDao;
import ccst.sh.user.domain.request.CheckCodeRequest;
import ccst.sh.user.domain.request.CheckNameRequest;
import ccst.sh.user.domain.request.UserChangeInfoRequest;
import ccst.sh.user.domain.request.UserChangePasswordRequest;
import ccst.sh.user.domain.request.UserLoginRequest;
import ccst.sh.user.domain.request.UserRegisterRequest;
import ccst.sh.user.domain.response.UserLoginResponse;
import ccst.sh.user.domain.response.UserNoticesResponse;
import ccst.sh.user.domain.response.UserRegisterResponse;

@Service
@Transactional(readOnly = true, rollbackFor = { Exception.class })
public class UserService {

    @Autowired
    private UserDao userDao;

    /* 验证是否存在用户名 */
    public Boolean checkName(CheckNameRequest request) {
        return userDao.checkName(request);
    }
    
    /* 验证学号是否注册 */
    public Boolean checkCode(CheckCodeRequest request) {
        return userDao.checkCode(request); 
    }
    
    /* 注册 */
    @Transactional
    public UserRegisterResponse register(UserRegisterRequest request) {
        return userDao.register(request);
    }
    
    /* 登陆 */
    public UserLoginResponse login(UserLoginRequest request) {
        return userDao.login(request);
    }
    
    /*用户修改基本信息*/
    @Transactional
    public Boolean changeInfo(UserChangeInfoRequest request) {
        return userDao.chanegInfo(request);
    }
    
    /*用户修改密码*/
    @Transactional
    public Boolean changePassword(UserChangePasswordRequest request) {         
        return userDao.changePassword(request);
    }
    /*验证密码是否存在*/
    public Boolean checkOldPassword(UserChangePasswordRequest request) {
        return userDao.checkOldPassword(request);
    }
    /*获取所有班级公告*/
    public List<UserNoticesResponse> getNotices(Integer userId, Integer pageNo, Integer pageSize) {
        return userDao.getNotices(userId, pageNo, pageSize);
    }

}
