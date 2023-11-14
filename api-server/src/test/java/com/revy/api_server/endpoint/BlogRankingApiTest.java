package com.revy.api_server.endpoint;

import com.revy.api_server.service.BlogRankingService;
import com.revy.api_server.service.data.PopulaSearchesResultData;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by Revy on 2023.11.14
 */

@WebMvcTest({BlogRankingApi.class})
@AutoConfigureMockMvc
class BlogRankingApiTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BlogRankingService blogRankingService;

    private List<PopulaSearchesResultData> blogSearchResultData;

    private int size = 10;

    @BeforeEach
    void setUp() {
        List<PopulaSearchesResultData> populaSearchesResultDataList = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            populaSearchesResultDataList.add(new PopulaSearchesResultData(i, "Test " + i, 100 - i));
        }
        blogSearchResultData = populaSearchesResultDataList;
    }

    @Test
    void 실시간_검색_순위_OK() throws Exception {
        // given
        Mockito.when(blogRankingService.getPopularSearches(size))
                .thenReturn(blogSearchResultData);
        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/blog/ranking/popular-searches"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void 실시간_검색_순위_사이즈_오버() throws Exception {
        // given
        Mockito.when(blogRankingService.getPopularSearches(100))
                .thenReturn(blogSearchResultData);
        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/blog/ranking/popular-searches")
                        .param("size", String.valueOf(100)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

}