package com.uccyou.ta.system.notice.response;

import java.io.Serializable;

public class RecentlyNoticeResponse implements Serializable {

    private static final long serialVersionUID = 4166857521015291171L;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
