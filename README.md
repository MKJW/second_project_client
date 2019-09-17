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

### Logic
- 로그인 버튼 클릭 → 서버로부터 응답(대기 시 프로그레스 바) → 1. 옳은 경우 토큰 2. 옳지 못한 경우 거부 3. 인증이 아직 완료되지 못했을 경우 이메일 변경 화면
- 회원가입 버튼 클릭 → join page로 이동
### join page

### Register courses page

### Timetable page 








