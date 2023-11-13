package com.revy.api_server.service.provider;

import com.revy.api_server.exception.NotFoundServiceException;
import com.revy.api_server.service.BlogSearchClientService;
import com.revy.core.enums.BlogSearchClientType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Revy on 2023.11.13
 */
@Slf4j
@Component
public class BlogSearchClientServiceProvider {
    private final Map<BlogSearchClientType, BlogSearchClientService> blogSearchClientServiceMap;

    public BlogSearchClientServiceProvider(Set<BlogSearchClientService> blogSearchClientServiceSet) {
        blogSearchClientServiceMap = blogSearchClientServiceSet.stream()
                .collect(Collectors.toMap(BlogSearchClientService::getServiceType, Function.identity()));
    }

    public BlogSearchClientService getBlogSearchClientService(BlogSearchClientType blogSearchClientType) {
        Assert.notNull(blogSearchClientType, "blogSearchClientType must be not null.");
        return Optional.of(blogSearchClientServiceMap.get(blogSearchClientType))
                .orElseThrow(() -> new NotFoundServiceException("%s 타입의 서비스가 존재하지 않습니다."));
    }


}
