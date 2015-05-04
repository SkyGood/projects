package com.uccyou.ta.commons.repository;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;

import com.uccyou.core.service.UccyouClientApi;

public class AbstractRepository {
    
    public UccyouClientApi uccyouClientApi;
    
    public String appKey;
    
    @Inject
    public void setUccyouClientApi(UccyouClientApi uccyouClientApi) {
        this.uccyouClientApi = uccyouClientApi;
    }
    
    @Inject
    public void setAppKey(@Value("${site.api.appkeys}") String appKey) {
        this.appKey = appKey;
    }
}
