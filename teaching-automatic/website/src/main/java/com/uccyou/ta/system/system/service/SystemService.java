package com.uccyou.ta.system.system.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.uccyou.ta.system.system.repository.SystemRepository;

@Service
public class SystemService {

    private SystemRepository systemRepository;

    @Inject
    public void setSystemRepository(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }
}
