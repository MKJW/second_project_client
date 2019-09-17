# School_Network(가제)




## 소개
> 교육부에서 25년 목표로 진행 예정인 고교학점제 제도에 맞춰서 고등학생용 수강 신청 시스템을 구축하고 이 시스템을 기반으로 학생들의 학교생활을 도와줄 수 있는 앱을 목표로 하고 있습니다.




## 시스템
> client : https://github.com/MKJW/second_project_client
 
>  server : https://github.com/MKJW/second_project_server




### 참여자
> 최재원qwebnm7788(Server) / 조명기 ChoMK(Client) 




# School_Network Client




## 개발환경
> Android Studio 3.4.2




## 개발 언어
> Kotlin




## 사용 라이브러리
> LiveData, ViewModel, Room, Retrofit, Dagger 2, RxJava 




## App Architecture
 디자인 패턴은 MVVM으로 진행.

 전반적인 구조는 BaseViewModel을 정의하고 BaseBiewModel 에서 각각 필요한 Module들을 초기화하여 주입시킴.

 ViewModel에서 UI업데이트와 데이터 송수신을 처리해주고, UI업데이트를 위하여 DataBinding 이용.

## 기능

### Login page
> 로그인 페이지
<img src="https://s3.us-west-2.amazonaws.com/secure.notion-static.com/a75a9129-5b70-443c-837e-0e4d0b88f9f7/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=ASIAT73L2G45CD3DBSWS%2F20190917%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20190917T040937Z&X-Amz-Expires=86400&X-Amz-Security-Token=AgoJb3JpZ2luX2VjEKH%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLXdlc3QtMiJGMEQCIErMU245u9VZypOtGrKEIYjAYU0BeaCpFs0brkuIQfGPAiAWmK3bTBzNNqExnYiY2NEEssbg%2BR59zFQinZ1Q%2Bfjk8CraAwhpEAAaDDI3NDU2NzE0OTM3MCIMZZg%2FdUg5RbZEffePKrcDXkpE%2FrpKZlGZMZhNmB9MU0rxvHTuY6tjTpfkVdwnkarbp0PEmB5V1ObP4sZonAryRGV%2BrjokraVoqJi7fjJHIm9tEFxFSghHzgbLiDJcNHJ%2F4ZHHHJjhZv2YglbcNe5mZsoL%2BagwsiyraJYTTdsA4H8b%2Fc03JZ33sXpcv%2BxRPdUAYhMNYX23cVM%2BJh0%2BjgD9pqhOHhRTk1WxeRydE%2FN%2FxYI3bpzSbipHPnJPeT%2BBUS4J8QKeI2nNlbZWXK3c15jvRQs5TxX7Vo0KP%2BgjURxSmFbebwdek0WmHbMGw5%2F2iW8JIHEFrx%2F30x8CQRiyu9URbWIb16jDJIns53BXWtGQShzK4Tal6WAhgrEu3MJdP43OwxEL5XGCpW9Mr3wKmfaRF%2B9%2F%2BBroT4HcAgFhhScJZ3X1THU8O12wf12hGaDL5WHWTmVQMlEL6WXzkXfBWhO5u%2FJnRG3RK76G%2BW419GaPMQMfb3kpPmnEaNiL%2B2F1nJ8XMjJdyZqSsFQrTqYA7Z0WhtSTP3j2zQpbqRoqZrFwo7KlPHTt5xj1Jl3BBuu9k0r2hZR0sazFBL2%2Fef0GSVGmvr2nJyYNATDdzIDsBTq1Adbh%2BULqlzZK3O2UJ%2BE96vkdSCcJpkLtVPDCPBIl1C4w3jIDvu%2B2WqtE3zN2ReoUeukaF51Ac8khSS4HqphOzGmJXIlLZ2uQvdncabdheVChc2DhOQhfN4K9%2Bh2yycCGZxsb7Q7MSPmCqoPliSprYYlepTOsZvBPmRB%2F3fOTbHn0rnvJT9QrcL%2FQGh2uhLwesZpalf%2FCB13vIYP%2Fd2wiShK%2FiQU2X5pyXaRVoYhDZakVwh5Cgtc%3D&X-Amz-Signature=2119ea8f549f45d03323be15e6af632088b2d10f44941a84f2e5f39da42d3ac8&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22" alt="alt text" width="250px" height="500px">


### Logic
- 로그인 버튼 클릭 → 서버로부터 응답(대기 시 프로그레스 바) → 1. 옳은 경우 토큰 2. 옳지 못한 경우 거부 3. 인증이 아직 완료되지 못했을 경우 이메일 변경 화면
- 회원가입 버튼 클릭 → join page로 이동
### join page

### Register courses page

### Timetable page 








