package com.revy.api_server.service.provider;

import com.revy.api_server.service.BlogSearchClientService;
import com.revy.api_server.service.impl.KakaoBlogSearchClientServiceImpl;
import com.revy.api_server.service.impl.NaverBlogSearchClientServiceImpl;
import com.revy.core.enums.blog.BlogSearchClientType;
import com.revy.core.exception.NotFoundServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;


/**
 * Created by Revy on 2023.11.15
 */
@ExtendWith(MockitoExtension.class)
class BlogSearchClientServiceProviderTest {

    private BlogSearchClientServiceProvider blogSearchClientServiceProvider;

    @InjectMocks
    private KakaoBlogSearchClientServiceImpl kakaoBlogSearchClientService;

    @InjectMocks
    private NaverBlogSearchClientServiceImpl naverBlogSearchClientService;

    @Nested
    class 서비스_정상_등록_테스트 {

        @BeforeEach
        void setUp() {
            blogSearchClientServiceProvider = new BlogSearchClientServiceProvider(Set.of(kakaoBlogSearchClientService, naverBlogSearchClientService));
        }

        @Test
        void 카카오_서비스_획득_성공() {
            // when
            BlogSearchClientService blogSearchClientService = blogSearchClientServiceProvider.getBlogSearchClientService(BlogSearchClientType.KAKAO);
            // then
            assertInstanceOf(KakaoBlogSearchClientServiceImpl.class, blogSearchClientService);
        }

        @Test
        void 네이버_서비스_획득_성공() {
            // when
            BlogSearchClientService blogSearchClientService = blogSearchClientServiceProvider.getBlogSearchClientService(BlogSearchClientType.NAVER);
            // then
            assertInstanceOf(NaverBlogSearchClientServiceImpl.class, blogSearchClientService);
        }
    }

    @Nested
    class 서비스_미등록_테스트 {
        @Test
        void 카카오_서비스_획득_실패() {
            // given
            blogSearchClientServiceProvider = new BlogSearchClientServiceProvider(Set.of(naverBlogSearchClientService));
            // when
            assertThrowsExactly(NotFoundServiceException.class, () -> blogSearchClientServiceProvider.getBlogSearchClientService(BlogSearchClientType.KAKAO));
        }

        @Test
        void 네이버_서비스_획득_실패() {
            // given
            blogSearchClientServiceProvider = new BlogSearchClientServiceProvider(Set.of(kakaoBlogSearchClientService));
            // when
            assertThrowsExactly(NotFoundServiceException.class, () -> blogSearchClientServiceProvider.getBlogSearchClientService(BlogSearchClientType.NAVER));
        }
    }


}