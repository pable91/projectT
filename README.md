# projectT

## 스택
- java11
- SpringBoot
- JPA
- maven
- h2 embeded (sqlite로 작성하라는걸 잘못 봤습니다ㅠㅠ)
- jwt
- Spring Security

## Swagger URL
http://localhost:8080/swagger-ui.html#/
- 주의사항 :   
```
회원가입, 로그인순으로 진행하고 응답으로 반환된 token을 Swagger Authorization에 설정해줘야함.   
적용은 ((( Bearer token ))) 라고 설정해야한다. 참고 : https://lemontia.tistory.com/1027
```

## 완료 구현기능
- 회원가입
- 로그인
- 유저 프로필 조회
- 유저 포인트 조회
- 글 등록
- 글 수정
- 글 조회
- 글 삭제
- 댓글 등록
- 댓글 삭제

## 테스트 목록
- 도메인 단위 테스트
- Controller 테스트
- DTO 검증 테스트
- 예외 테스트

