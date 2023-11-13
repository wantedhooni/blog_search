# blog_search

# 인프라정보
- swagger-ui 사용할지 아직 미정
- - swagger-ui 주소 : http://localhost:8080/swagger-ui/index.html
- H2 DB URL: http://localhost:8080/h2-console


## 필요내용
- Redis 
- - 분산락 필요 - 동시성 방지
- - 캐싱 필요할까?

- 대용량 트래픽 처리 방안
- JAVA 21이니 가상 쓰레드 사용 - 관리힘든 WebFlux 우회
- -  https://spring.io/blog/2022/10/11/embracing-virtual-threads

