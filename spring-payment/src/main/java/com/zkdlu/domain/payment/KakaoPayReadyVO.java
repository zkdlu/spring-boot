package com.zkdlu.domain.payment;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class KakaoPayReadyVO {
    private String tid;
    private String next_redirect_pc_url;
    private Date created_at;
}
