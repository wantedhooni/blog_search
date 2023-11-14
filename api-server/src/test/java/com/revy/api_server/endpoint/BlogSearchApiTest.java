package com.revy.api_server.endpoint;

import com.revy.api_server.service.BlogRankingService;
import com.revy.api_server.service.BlogSearchService;
import com.revy.api_server.service.data.BlogSearchResultData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by Revy on 2023.11.14
 */

@WebMvcTest({BlogSearchApi.class})
@AutoConfigureMockMvc
class BlogSearchApiTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BlogSearchService blogSearchService;
    @MockBean
    private BlogRankingService blogRankingService;

    private BlogSearchResultData blogSearchResultData;
    private final int size = 10;
    private final int page = 1;

    @BeforeEach
    void setUp() {
        List<BlogSearchResultData.DocumentData> documentData = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            documentData.add(BlogSearchResultData.DocumentData
                    .builder()
                    .title("title " + i)
                    .contents("contents" + i)
                    .url("url" + i)
                    .blogName("blogName" + i)
                    .thumbnail("thumbnail" + i)
                    .datetime(LocalDateTime.now())
                    .build());
        }
        blogSearchResultData = BlogSearchResultData
                .builder()
                .currentPage(page)
                .totalCount(size)
                .pageableCount(1)
                .size(documentData.size())
                .first(true)
                .last(true)
                .documents(documentData)
                .build();
    }


    @Test
    void 블로그_조회_OK() throws Exception {
        // given
        Mockito.when(blogSearchService.searchBlogs((any()))).thenReturn(blogSearchResultData);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/blog/search")
                        .param("keyword", "test")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size)))
                .andDo(print())
                // then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("currentPage").value(page))
                .andExpect(MockMvcResultMatchers.jsonPath("totalCount").value(size));
    }

    @Test
    void 블로그_조회_키워드_NULL() throws Exception {
        // given
        Mockito.when(blogSearchService.searchBlogs((any()))).thenReturn(blogSearchResultData);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/blog/search")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(100)))
                .andDo(print())
                //then
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void 블로그_조회_SIZE_오버() throws Exception {
        // given
        Mockito.when(blogSearchService.searchBlogs((any()))).thenReturn(blogSearchResultData);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/blog/search")
                        .param("keyword", "test")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(100)))
                .andDo(print())
                //then
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void 블로그_조회_페이지_오버() throws Exception {
        // given
        Mockito.when(blogSearchService.searchBlogs((any()))).thenReturn(blogSearchResultData);
        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/blog/search")
                        .param("page", String.valueOf(100))
                        .param("size", String.valueOf(size)))
                .andDo(print())
                //then
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void 블로그_조회_정렬조건_지원하지_않는_조건() throws Exception {
        // given
        Mockito.when(blogSearchService.searchBlogs((any()))).thenReturn(blogSearchResultData);
        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/blog/search")
                        .param("keyword", "test")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .param("sort", "TEST"))
                .andDo(print())
                //then
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}