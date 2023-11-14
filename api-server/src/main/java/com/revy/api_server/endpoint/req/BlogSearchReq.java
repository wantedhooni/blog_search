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
public class BlogSearchReq {

    @NotBlank(message = "키워드는 필수값입니다.")
    private String keyword;

    @Min(value = 1, message = "페이지 사이즈의 최소값은 1입니다.")
    @Max(value = 50, message = "페이지 사이즈의 최대값은 50입니다.")
    private int size = 10;

    @Min(value = 1, message = "페이지 번호의 최소값은 1입니다.")
    @Max(value = 50, message = "페이지 번호의 최대값은 50입니다.")
    private int page = 1;

    private BlogSort sort = BlogSort.ACCURACY;
}
