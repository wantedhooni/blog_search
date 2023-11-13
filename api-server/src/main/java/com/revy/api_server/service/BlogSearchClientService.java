package com.revy.api_server.service;

import com.revy.api_server.service.data.BlogSearchResultData;
import com.revy.core.enums.BlogSearchClientType;
import com.revy.core.enums.BlogSort;

/**
 * Created by Revy on 2023.11.13
 */
public interface BlogSearchClientService {


    public BlogSearchClientType getServiceType();

    BlogSearchResultData searchBlog(String keyword, int size, int page, BlogSort sortCode);
}
