package com.revy.api_server.service;

import com.revy.api_server.service.data.BlogSearchResultData;
import com.revy.core.enums.blog.BlogSearchClientType;
import com.revy.core.enums.blog.BlogSort;

/**
 * Created by Revy on 2023.11.13
 */
public interface BlogSearchClientService {

    BlogSearchClientType getServiceType();

    BlogSearchResultData searchBlog(String keyword, int size, int page, BlogSort sortCode);
}
