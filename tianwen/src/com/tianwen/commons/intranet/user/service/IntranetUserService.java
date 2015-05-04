package com.tianwen.commons.intranet.user.service;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.tianwen.commons.intranet.user.repository.IntranetUserRepository;
import com.tianwen.commons.intranet.user.request.UserSearchRequest;
import com.tianwen.commons.intranet.user.response.UserResponse;
import com.uccyou.core.page.PageModel;

@Service
public class IntranetUserService {

    private IntranetUserRepository userRepository;

    public PageModel<UserResponse> users(UserSearchRequest request, Integer pageNo,
            Integer pageSize) {
        return userRepository.users(request, pageNo, pageSize);
    }
    
    @Inject
    public void setUserRepository(IntranetUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public boolean alive(Integer userId, String status) {
		return userRepository.alive(userId, status);
	}

	public Integer userNumber() {
		return userRepository.userNumber();
	}
}
