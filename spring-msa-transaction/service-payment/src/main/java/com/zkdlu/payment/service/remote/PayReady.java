package com.zkdlu.payment.service.remote;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Getter
public class PayReady {
    @JsonProperty("tid")
    private String tid;
    @JsonProperty("next_redirect_pc_url")
    private String nextRedirectPcUrl;
    @JsonProperty("created_at")
    private Date createdAt;
}
