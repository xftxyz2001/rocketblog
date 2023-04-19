package com.xftxyz.rocketblog.service;

import com.xftxyz.rocketblog.pojo.User;

public interface AdminService {

    void resetDatabase(User user, String password);

    void deleteLogFiles();
    
}
