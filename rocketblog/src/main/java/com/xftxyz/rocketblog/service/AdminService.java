package com.xftxyz.rocketblog.service;

import org.springframework.core.io.Resource;

public interface AdminService {

    void resetDatabase();

    public void deleteImageFiles();

    void deleteLogFiles();

    Resource downloadLog();

}
