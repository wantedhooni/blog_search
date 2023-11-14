package com.revy.api_server.endpoint;

import com.revy.api_server.endpoint.req.BlogSearchReq;
import com.revy.api_server.endpoint.res.BlogSearchResultRes;
import com.revy.api_server.service.BlogRankingService;
import com.revy.api_server.service.BlogSearchService;
import com.revy.api_server.service.data.BlogSearchConditionData;
import com.revy.api_server.service.data.BlogSearchResultData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/blog/search")
@RequiredArgsConstructor
public class BlogSearchApi {

    private final BlogSearchService blogSearchService;
    private final BlogRankingService blogRankingService;

    @GetMapping
    public BlogSearchResultRes searchBlogs(@ModelAttribute @Valid BlogSearchReq req) {
        log.debug("[searchBlogs] req:{}", req);
        blogRankingService.increaseCountAsync(req.getKeyword());
        BlogSearchConditionData condition = BlogSearchConditionData.builder()
                .keyword(req.getKeyword())
                .page(req.getPage())
                .size(req.getSize())
                .sort(req.getSort())
                .build();
        BlogSearchResultData result = blogSearchService.searchBlogs(condition);
        BlogSearchResultRes res = mapBlogSearchResultRes(result);
        return res;
    }

    private BlogSearchResultRes mapBlogSearchResultRes(BlogSearchResultData blogSearchResultData) {
        Assert.notNull(blogSearchResultData, "blogSearchResultData must not be null");
        return BlogSearchResultRes.builder()
                .currentPage(blogSearchResultData.getCurrentPage())
                .totalCount(blogSearchResultData.getTotalCount())
                .pageableCount(blogSearchResultData.getPageableCount())
                .size(blogSearchResultData.getSize())
                .first(blogSearchResultData.isFirst())
                .last(blogSearchResultData.isLast())
                .documents(blogSearchResultData.getDocuments().stream()
                        .map(documentData -> BlogSearchResultRes.DocumentResData.builder()
                                .title(documentData.getTitle())
                                .contents(documentData.getContents())
                                .url(documentData.getUrl())
                                .blogName(documentData.getBlogName())
                                .thumbnail(documentData.getThumbnail())
                                .datetime(documentData.getDatetime()).build()).toList())
                .build();
    }
}
