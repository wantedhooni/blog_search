package com.revy.api_server.service.impl;

import com.revy.api_server.service.data.BlogSearchConditionData;
import com.revy.api_server.service.data.BlogSearchResultData;
import com.revy.api_server.service.provider.BlogSearchClientServiceProvider;
import com.revy.core.enums.blog.BlogSearchClientType;
import com.revy.core.enums.blog.BlogSort;
import com.revy.external_api.kakao.exception.KakaoApi5xxException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

/**
 * Created by Revy on 2023.11.15
 */
@ExtendWith(MockitoExtension.class)
class BlogSearchServiceImplTest {

    @InjectMocks
    private BlogSearchServiceImpl blogSearchService;

    @Mock
    private BlogSearchClientServiceProvider blogSearchClientServiceProvider;
    @Mock
    private KakaoBlogSearchClientServiceImpl kakaoBlogSearchClientService;
    @Mock
    private NaverBlogSearchClientServiceImpl naverBlogSearchClientService;

    private BlogSearchResultData blogSearchResultData;

    private final String keyword = "Test";
    private final int size = 10;
    private final int page = 1;

    @BeforeEach
    void setUp() {

        List<BlogSearchResultData.DocumentData> documentDataList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            documentDataList.add(BlogSearchResultData.DocumentData.
                    builder()
                    .title(keyword + i)
                    .contents(keyword + i)
                    .url(keyword + i)
                    .blogName(keyword + i)
                    .thumbnail(keyword + i)
                    .datetime(LocalDateTime.now())
                    .build());
        }

        int pageableCount = page;

        blogSearchResultData = BlogSearchResultData
                .builder()
                .currentPage(page)
                .pageableCount(page)
                .totalCount(size)
                .size(size)
                .first(page == 1)
                .last(page == pageableCount)
                .documents(documentDataList)
                .build();
    }


    @Test
    void 카카오_블로그_검색() {
        //given
        Mockito.when(blogSearchClientServiceProvider.getBlogSearchClientService(BlogSearchClientType.KAKAO))
                .thenReturn(kakaoBlogSearchClientService);

        Mockito.when(blogSearchClientServiceProvider.getBlogSearchClientService(BlogSearchClientType.KAKAO)
                        .searchBlog(any(), anyInt(), anyInt(), any(BlogSort.class)))
                .thenReturn(blogSearchResultData);

        // when
        BlogSearchConditionData conditionData = BlogSearchConditionData.builder().keyword(keyword).size(size).page(page).sort(BlogSort.ACCURACY).build();
        BlogSearchResultData blogSearchResultData = blogSearchService.searchBlogs(conditionData);
        // then
        assertNotNull(blogSearchResultData);
        assertNotNull(blogSearchResultData.getDocuments());
        assertEquals(blogSearchResultData.getCurrentPage(), page);
        assertEquals(blogSearchResultData.getSize(), size);
        assertEquals(blogSearchResultData.getTotalCount(), size);
    }

    @Test
    void 네이버_블로그_검색() {
        //given
        Mockito.when(blogSearchClientServiceProvider.getBlogSearchClientService(BlogSearchClientType.NAVER))
                .thenReturn(naverBlogSearchClientService);

        Mockito.when(blogSearchClientServiceProvider.getBlogSearchClientService(BlogSearchClientType.NAVER)
                        .searchBlog(any(), anyInt(), anyInt(), any(BlogSort.class)))
                .thenReturn(blogSearchResultData);

        // when
        BlogSearchConditionData conditionData = BlogSearchConditionData.builder().keyword(keyword).size(size).page(page).sort(BlogSort.ACCURACY).build();
        BlogSearchResultData blogSearchResultData = blogSearchService.searchBlogs(conditionData);
        // then
        assertNotNull(blogSearchResultData);
        assertNotNull(blogSearchResultData.getDocuments());
        assertEquals(blogSearchResultData.getCurrentPage(), page);
        assertEquals(blogSearchResultData.getSize(), size);
        assertEquals(blogSearchResultData.getTotalCount(), size);
    }

    @Test
    void 카카오실패_네이버_검색() {
        //given
        // 카카오 500 오류 발생
        Mockito.when(blogSearchClientServiceProvider.getBlogSearchClientService(BlogSearchClientType.KAKAO))
                .thenReturn(kakaoBlogSearchClientService);
        Mockito.when(kakaoBlogSearchClientService.searchBlog(any(), anyInt(), anyInt(), any(BlogSort.class)))
                .thenThrow(KakaoApi5xxException.class);

        // 네이버 검색
        Mockito.when(blogSearchClientServiceProvider.getBlogSearchClientService(BlogSearchClientType.NAVER))
                .thenReturn(naverBlogSearchClientService);
        Mockito.when(blogSearchClientServiceProvider.getBlogSearchClientService(BlogSearchClientType.NAVER)
                        .searchBlog(any(), anyInt(), anyInt(), any(BlogSort.class)))
                .thenReturn(blogSearchResultData);

        // when
        BlogSearchConditionData conditionData = BlogSearchConditionData.builder().keyword(keyword).size(size).page(page).sort(BlogSort.ACCURACY).build();
        BlogSearchResultData blogSearchResultData = blogSearchService.searchBlogs(conditionData);
        // then
        assertNotNull(blogSearchResultData);
        assertNotNull(blogSearchResultData.getDocuments());
        assertEquals(blogSearchResultData.getCurrentPage(), page);
        assertEquals(blogSearchResultData.getSize(), size);
        assertEquals(blogSearchResultData.getTotalCount(), size);
    }


}