# Spring Boot를 이용한 RESTful Web Services 개발

##### 강의: https://www.inflearn.com/course/spring-boot-restful-web-services/lecture/39073?tab=note
##### 정리(Notion): https://94jingyu.notion.site/Spring-Boot-RESTful-Web-Services-c9baae01c5a9426b9ce7bf85a40aa5ac


## Section 0: Web Service & Application
### 1. Web Service 개요
네트워크 상에서 서로 다른 종류의 컴퓨터들 간의 상호작용하기 위한 소프트웨어 시스템


### 2. Web Application 개요
인터넷이나 인트라넷을 통해 웹 브라우저에서 이용할 수 있는 응용 소프트웨어


### 3. REST (REpresentational State Transfer)
- Resouce의 Represenation에 의한 상태 전달
- HTTP Method를 통해 Resource를 처리하기 위한 아키텍처\
- RESTful: REST API를 제공하는 웹 서비스 


## Section 1: Spring Boot로 개발하는 RESTful Service
### 1. Spring Boot 개요
단독 실행가능한 어플리케이션을 개발하기 위한 플랫폼

### 2. application.properties vs appication.yml
#### 2.1. application.properties
설정이름=값
``` 
logging.level.org.springframework=debug
``` 

#### 2.2. application.yml
설정이름:값
```
logging:
  level:
    org.springframework:debug
``` 

### 3. DispatcherServlet → '/'
- 클라이언트의 모든 요청을 한 곳으로 받아서 처리
- 요청에 맞는 Handler로 요청을 전달
- Handler의 실행 결과를 HTTP REsponse 형태로 만들어서 반환

### 4. RestController
- Spring4부터 @RestController 지원
- @Controller + @ResponseBody
- View를 갖지 않는 REST Data(JSON/XML)를 반환 
