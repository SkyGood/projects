package com.uccyou.ta.system.admin.request;

public class ImportDataRequest {

    private String identity;

    private String filePath;

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
