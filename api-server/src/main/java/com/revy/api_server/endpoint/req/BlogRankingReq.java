package com.revy.api_server.endpoint.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Revy on 2023.11.15
 * 인기 검색어 검색 Request
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BlogRankingReq {
    @Min(value = 1, message = "사이즈의 최소값은 1입니다.")
    @Max(value = 10, message = "사이즈의 최대값은 10입니다.")
    private int size = 10;
}
