### Spec

---

- JAVA 21
- Spring Boot 3.1.5
- lombok

### 링크 정보

---

- 과제 Github : https://github.com/wantedhooni/blog_search.git
- 다운로드 링크: https://github.com/wantedhooni/blog_search/blob/develop/deploy/api-server-0.0.1.jar
    - 다운로드 링크 클릭 이후 → 우측의 Download raw file 클릭

### 실행정보

---

```
#java 21 버전 확인해주세요.
java -jar api-server-0.0.1.jar
```

### 내부 인프라 정보

---

- swagger-ui URL : http://localhost:8080/swagger-ui/index.html
- H2 DB URL: http://localhost:8080/h2-console

### API 설명

---

Postman Doc: https://documenter.getpostman.com/view/31149172/2s9YXnzeN6#5db1f8cc-e345-4afc-92d2-d0544ef50e26

1. 블로그 검색 - GET - /blog/search
    1. Parameters 정보

   | 이름 | 설명 |
       | --- | --- |
   | keyword | 블로그 검색 키워드 |
   | size | 페이지 사이즈 |
   | page | 요청 페이지 |
   | sort | 정렬 기준 -ACCURACY(최신순), RECENCY(인기순) |

    ```
    curl --location 'http://localhost:8080/blog/search?keyword=kakaobank&size=1&page=1&sort=ACCURACY' \
    --header 'Accept: */*'
    ```

2. 인기 검색어 검색 - GET - /blog/ranking/popular-searches
    1. Parameters 정보

   | 이름 | 설명 |
       | --- | --- |
   | size | 사이즈 |

    ```
    curl -X 'GET' \
      'http://localhost:8080/blog/ranking/popular-searches?size=10' \
      -H 'accept: */*'
    ```


### 외부  라이브러리 /  오픈소스 정보

---

```
- Swagger
implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2'

- embedded-redis
implementation 'com.github.codemonstur:embedded-redis:1.0.0'

- redisson
implementation 'org.redisson:redisson-spring-boot-starter:3.24.3'
```

### 프로젝트 구조

---

```
.
├── HELP.md
├── README.md
├── api-server
│   ├── build
│   │   ├── classes
│   │   │   └── java
│   │   │       ├── main
│   │   │       │   └── com
│   │   │       │       └── revy
│   │   │       │           └── api_server
│   │   │       │               ├── ApiServerApplication.class
│   │   │       │               ├── endpoint
│   │   │       │               │   ├── BlogRankingApi.class
│   │   │       │               │   ├── BlogSearchApi.class
│   │   │       │               │   ├── req
│   │   │       │               │   │   ├── BlogRankingReq.class
│   │   │       │               │   │   └── BlogSearchReq.class
│   │   │       │               │   └── res
│   │   │       │               │       ├── BlogPopularSearchesRes$BlogPopularSearchesResData.class
│   │   │       │               │       ├── BlogPopularSearchesRes.class
│   │   │       │               │       ├── BlogSearchResultRes$BlogSearchResultResBuilder.class
│   │   │       │               │       ├── BlogSearchResultRes$DocumentResData$DocumentResDataBuilder.class
│   │   │       │               │       ├── BlogSearchResultRes$DocumentResData.class
│   │   │       │               │       └── BlogSearchResultRes.class
│   │   │       │               ├── handler
│   │   │       │               │   ├── ApiExceptionHandler.class
│   │   │       │               │   └── res
│   │   │       │               │       └── ErrorResponse.class
│   │   │       │               ├── infra
│   │   │       │               │   └── config
│   │   │       │               │       ├── AsyncConfig.class
│   │   │       │               │       └── SwaggerConfig.class
│   │   │       │               └── service
│   │   │       │                   ├── BlogRankingService.class
│   │   │       │                   ├── BlogSearchClientService.class
│   │   │       │                   ├── BlogSearchService.class
│   │   │       │                   ├── data
│   │   │       │                   │   ├── BlogSearchConditionData$BlogSearchConditionDataBuilder.class
│   │   │       │                   │   ├── BlogSearchConditionData.class
│   │   │       │                   │   ├── BlogSearchResultData$BlogSearchResultDataBuilder.class
│   │   │       │                   │   ├── BlogSearchResultData$DocumentData$DocumentDataBuilder.class
│   │   │       │                   │   ├── BlogSearchResultData$DocumentData.class
│   │   │       │                   │   ├── BlogSearchResultData.class
│   │   │       │                   │   └── PopularSearchesResultData.class
│   │   │       │                   ├── impl
│   │   │       │                   │   ├── BlogRankingServiceImpl.class
│   │   │       │                   │   ├── BlogSearchServiceImpl.class
│   │   │       │                   │   ├── KakaoBlogSearchClientServiceImpl.class
│   │   │       │                   │   └── NaverBlogSearchClientServiceImpl.class
│   │   │       │                   └── provider
│   │   │       │                       └── BlogSearchClientServiceProvider.class
│   │   │       └── test
│   │   │           ├── com
│   │   │           │   └── revy
│   │   │           │       └── api_server
│   │   │           │           ├── ApiServerApplicationTests.class
│   │   │           │           ├── endpoint
│   │   │           │           │   ├── BlogRankingApiTest.class
│   │   │           │           │   └── BlogSearchApiTest.class
│   │   │           │           └── service
│   │   │           │               ├── impl
│   │   │           │               │   ├── BlogRankingServiceImplTest.class
│   │   │           │               │   └── BlogSearchServiceImplTest.class
│   │   │           │               └── provider
│   │   │           │                   ├── BlogSearchClientServiceProviderTest$서비스_미등록_테스트.class
│   │   │           │                   ├── BlogSearchClientServiceProviderTest$서비스_정상_등록_테스트.class
│   │   │           │                   └── BlogSearchClientServiceProviderTest.class
│   │   │           └── generated_tests
│   │   └── generated
│   │       └── sources
│   │           └── annotationProcessor
│   │               └── java
│   │                   └── main
│   │                       └── generated
│   ├── build.gradle
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── revy
│       │   │           └── api_server
│       │   │               ├── ApiServerApplication.java
│       │   │               ├── endpoint
│       │   │               │   ├── BlogRankingApi.java
│       │   │               │   ├── BlogSearchApi.java
│       │   │               │   ├── req
│       │   │               │   │   ├── BlogRankingReq.java
│       │   │               │   │   └── BlogSearchReq.java
│       │   │               │   └── res
│       │   │               │       ├── BlogPopularSearchesRes.java
│       │   │               │       └── BlogSearchResultRes.java
│       │   │               ├── handler
│       │   │               │   ├── ApiExceptionHandler.java
│       │   │               │   └── res
│       │   │               │       └── ErrorResponse.java
│       │   │               ├── infra
│       │   │               │   └── config
│       │   │               │       ├── AsyncConfig.java
│       │   │               │       └── SwaggerConfig.java
│       │   │               └── service
│       │   │                   ├── BlogRankingService.java
│       │   │                   ├── BlogSearchClientService.java
│       │   │                   ├── BlogSearchService.java
│       │   │                   ├── data
│       │   │                   │   ├── BlogSearchConditionData.java
│       │   │                   │   ├── BlogSearchResultData.java
│       │   │                   │   └── PopularSearchesResultData.java
│       │   │                   ├── impl
│       │   │                   │   ├── BlogRankingServiceImpl.java
│       │   │                   │   ├── BlogSearchServiceImpl.java
│       │   │                   │   ├── KakaoBlogSearchClientServiceImpl.java
│       │   │                   │   └── NaverBlogSearchClientServiceImpl.java
│       │   │                   └── provider
│       │   │                       └── BlogSearchClientServiceProvider.java
│       │   └── resources
│       │       └── application.yml
│       └── test
│           └── java
│               └── com
│                   └── revy
│                       └── api_server
│                           ├── ApiServerApplicationTests.java
│                           ├── endpoint
│                           │   ├── BlogRankingApiTest.java
│                           │   └── BlogSearchApiTest.java
│                           └── service
│                               ├── impl
│                               │   ├── BlogRankingServiceImplTest.java
│                               │   └── BlogSearchServiceImplTest.java
│                               └── provider
│                                   └── BlogSearchClientServiceProviderTest.java
├── build.gradle
├── core
│   ├── build
│   │   ├── classes
│   │   │   └── java
│   │   │       └── main
│   │   │           └── com
│   │   │               └── revy
│   │   │                   └── core
│   │   │                       ├── enums
│   │   │                       │   ├── blog
│   │   │                       │   │   ├── BlogSearchClientType.class
│   │   │                       │   │   └── BlogSort.class
│   │   │                       │   └── common
│   │   │                       │       └── ErrorCode.class
│   │   │                       └── exception
│   │   │                           ├── InternalServerErrorException.class
│   │   │                           ├── NotFoundServiceException.class
│   │   │                           └── common
│   │   │                               └── CommonException.class
│   │   └── generated
│   │       └── sources
│   │           └── annotationProcessor
│   │               └── java
│   │                   └── main
│   │                       └── generated
│   ├── build.gradle
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── revy
│       │   │           └── core
│       │   │               ├── enums
│       │   │               │   ├── blog
│       │   │               │   │   ├── BlogSearchClientType.java
│       │   │               │   │   └── BlogSort.java
│       │   │               │   └── common
│       │   │               │       └── ErrorCode.java
│       │   │               └── exception
│       │   │                   ├── InternalServerErrorException.java
│       │   │                   ├── NotFoundServiceException.java
│       │   │                   └── common
│       │   │                       └── CommonException.java
│       │   └── resources
│       └── test
│           ├── java
│           └── resources
├── deploy
│   └── api-server-0.0.1.jar
├── domain
│   ├── build
│   │   ├── classes
│   │   │   └── java
│   │   │       └── main
│   │   │           └── com
│   │   │               └── revy
│   │   │                   └── domain
│   │   │                       ├── blog_search_statistics
│   │   │                       │   ├── entity
│   │   │                       │   │   ├── BlogSearchStatistics.class
│   │   │                       │   │   └── QBlogSearchStatistics.class
│   │   │                       │   ├── repository
│   │   │                       │   │   ├── BlogSearchStatisticsCustomRepository.class
│   │   │                       │   │   └── BlogSearchStatisticsRepository.class
│   │   │                       │   └── service
│   │   │                       │       ├── BlogSearchStatisticsManager.class
│   │   │                       │       ├── BlogSearchStatisticsReader.class
│   │   │                       │       └── impl
│   │   │                       │           ├── BlogSearchStatisticsManagerImpl.class
│   │   │                       │           └── BlogSearchStatisticsReaderImpl.class
│   │   │                       └── infra
│   │   │                           └── config
│   │   │                               ├── JpaConfig.class
│   │   │                               └── QueryDslConfig.class
│   │   └── generated
│   │       └── sources
│   │           └── annotationProcessor
│   │               └── java
│   │                   └── main
│   │                       └── generated
│   │                           └── com
│   │                               └── revy
│   │                                   └── domain
│   │                                       └── blog_search_statistics
│   │                                           └── entity
│   │                                               └── QBlogSearchStatistics.java
│   ├── build.gradle
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── revy
│       │   │           └── domain
│       │   │               ├── blog_search_statistics
│       │   │               │   ├── entity
│       │   │               │   │   └── BlogSearchStatistics.java
│       │   │               │   ├── repository
│       │   │               │   │   ├── BlogSearchStatisticsCustomRepository.java
│       │   │               │   │   └── BlogSearchStatisticsRepository.java
│       │   │               │   └── service
│       │   │               │       ├── BlogSearchStatisticsManager.java
│       │   │               │       ├── BlogSearchStatisticsReader.java
│       │   │               │       └── impl
│       │   │               │           ├── BlogSearchStatisticsManagerImpl.java
│       │   │               │           └── BlogSearchStatisticsReaderImpl.java
│       │   │               └── infra
│       │   │                   └── config
│       │   │                       ├── JpaConfig.java
│       │   │                       └── QueryDslConfig.java
│       │   └── resources
│       │       └── application-domain.yml
│       └── test
│           └── java
├── external-api
│   ├── build
│   │   ├── classes
│   │   │   └── java
│   │   │       └── main
│   │   │           ├── META-INF
│   │   │           │   └── spring-configuration-metadata.json
│   │   │           └── com
│   │   │               └── revy
│   │   │                   └── external_api
│   │   │                       ├── kakao
│   │   │                       │   ├── KakaoApiClient.class
│   │   │                       │   ├── exception
│   │   │                       │   │   ├── KakaoApi4xxException.class
│   │   │                       │   │   ├── KakaoApi5xxException.class
│   │   │                       │   │   └── KakaoApiClientException.class
│   │   │                       │   ├── properties
│   │   │                       │   │   └── KakaoApiProperties.class
│   │   │                       │   └── res
│   │   │                       │       ├── KakaoApiErrorRes.class
│   │   │                       │       ├── KakaoBlogSearchRes$Doucment.class
│   │   │                       │       ├── KakaoBlogSearchRes$meta.class
│   │   │                       │       └── KakaoBlogSearchRes.class
│   │   │                       └── naver
│   │   │                           ├── NaverApiClient.class
│   │   │                           ├── exception
│   │   │                           │   ├── NaverApi4xxException.class
│   │   │                           │   ├── NaverApi5xxException.class
│   │   │                           │   └── NaverApiClientException.class
│   │   │                           ├── properties
│   │   │                           │   └── NaverApiProperties.class
│   │   │                           └── res
│   │   │                               ├── NaverApiErrorRes.class
│   │   │                               ├── NaverBlogSearchRes$Item.class
│   │   │                               └── NaverBlogSearchRes.class
│   │   └── generated
│   │       └── sources
│   │           └── annotationProcessor
│   │               └── java
│   │                   └── main
│   │                       └── generated
│   ├── build.gradle
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── revy
│       │   │           └── external_api
│       │   │               ├── kakao
│       │   │               │   ├── KakaoApiClient.java
│       │   │               │   ├── exception
│       │   │               │   │   ├── KakaoApi4xxException.java
│       │   │               │   │   ├── KakaoApi5xxException.java
│       │   │               │   │   └── KakaoApiClientException.java
│       │   │               │   ├── properties
│       │   │               │   │   └── KakaoApiProperties.java
│       │   │               │   └── res
│       │   │               │       ├── KakaoApiErrorRes.java
│       │   │               │       └── KakaoBlogSearchRes.java
│       │   │               └── naver
│       │   │                   ├── NaverApiClient.java
│       │   │                   ├── exception
│       │   │                   │   ├── NaverApi4xxException.java
│       │   │                   │   ├── NaverApi5xxException.java
│       │   │                   │   └── NaverApiClientException.java
│       │   │                   ├── properties
│       │   │                   │   └── NaverApiProperties.java
│       │   │                   └── res
│       │   │                       ├── NaverApiErrorRes.java
│       │   │                       └── NaverBlogSearchRes.java
│       │   └── resources
│       │       └── application-blog-search-client.yml
│       └── test
│           ├── java
│           └── resources
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── redis
│   ├── build
│   │   ├── classes
│   │   │   └── java
│   │   │       └── main
│   │   │           ├── META-INF
│   │   │           │   └── spring-configuration-metadata.json
│   │   │           └── com
│   │   │               └── revy
│   │   │                   └── redis
│   │   │                       ├── aop
│   │   │                       │   ├── DistributedLockAop.class
│   │   │                       │   ├── RedisLockTransaction.class
│   │   │                       │   └── annotation
│   │   │                       │       └── DistributedLock.class
│   │   │                       ├── config
│   │   │                       │   └── RedisConfig.class
│   │   │                       └── properties
│   │   │                           └── RedisProperties.class
│   │   └── generated
│   │       └── sources
│   │           └── annotationProcessor
│   │               └── java
│   │                   └── main
│   │                       └── generated
│   ├── build.gradle
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── revy
│       │   │           └── redis
│       │   │               ├── aop
│       │   │               │   ├── DistributedLockAop.java
│       │   │               │   ├── RedisLockTransaction.java
│       │   │               │   └── annotation
│       │   │               │       └── DistributedLock.java
│       │   │               ├── config
│       │   │               │   └── RedisConfig.java
│       │   │               └── properties
│       │   │                   └── RedisProperties.java
│       │   └── resources
│       │       └── application-redis.yml
│       └── test
│           ├── java
│           └── resources
└── settings.gradle
```