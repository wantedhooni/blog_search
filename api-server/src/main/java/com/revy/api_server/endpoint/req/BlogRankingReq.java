package com.revy.api_server.endpoint.req;

import com.revy.core.enums.blog.BlogSort;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BlogRankingReq {
    @Min(value = 1, message = "사이즈의 최소값은 1입니다.")
    @Max(value = 10, message = "사이즈의 최대값은 10입니다.")
    private int size = 10;
}
