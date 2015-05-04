package ccst.sh.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ccst.sh.system.dao.SystemDao;

@Service
@Transactional(readOnly = true, rollbackFor = {Exception.class})
public class SystemService {

    @Autowired
    private SystemDao systemDao;

    @Transactional
    public void importData(String path) {
        systemDao.importData(path);
    }

    public Boolean login(String username, String password) {
        return systemDao.login(username, password);
    }
    
    @Transactional
    public Boolean deleteUser(Integer userId) {
        try {
            systemDao.deleteUser(userId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
