package com.zkdlu;

import java.util.Date;

public class KakaoPayReadyVO {
    private String tid, next_redirect_pc_url;
    private Date created_at;

    public KakaoPayReadyVO() {
    }

    public String getTid() {
        return tid;
    }

    public String getNext_redirect_pc_url() {
        return next_redirect_pc_url;
    }

    public Date getCreated_at() {
        return created_at;
    }
}
