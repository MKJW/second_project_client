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
- Login click → loading → 1. correct : receive token 2. not correct : deny 3. yet certification : change email page : 로그인 성공시 토큰 부여 받고 실패시 거부, 인증이 아직 완료되지 않으면 이메일 변경 페이지로 넘어감
- join click → join page

### join page
> 회원 가입 페이지
<img src="https://s3.us-west-2.amazonaws.com/secure.notion-static.com/d37e9813-3da2-47ed-97af-97744b52fc38/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=ASIAT73L2G45LMYOAWV4%2F20190917%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20190917T041213Z&X-Amz-Expires=86400&X-Amz-Security-Token=AgoJb3JpZ2luX2VjEKH%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLXdlc3QtMiJIMEYCIQDCl2MBxEsXIYw3uXVNBe3Nx%2FXo1ja5BGzqXl66zxoWpQIhAJgvrs%2BdS6SSUhICZFRVr07GOZd6ggHVYSZ3RuVh%2BbsuKtoDCGkQABoMMjc0NTY3MTQ5MzcwIgwDbj3P%2BJ1mHczirI8qtwOuj4yfdiSsCynWYI5%2FoW9gR04cBJ8yBRUTId6FZMADlXvYZZYhiC4hQ5Q1zRI54y2SYEQdVKlIeor5Aq3I6jrzfntjXEh%2BSTTECoRzrUvR2OXupwsjTYG15erkHARijnNvbdexqZ2EyzLY10k3pY3qIl5YxF69xTfC4qXJeh5o0u7ymqcmofGb%2BbwtCOWlmrwE6EuTCQaPclovv582puHSb5hTHgDm4d5Y4tLed7A1HTiHfxchIY50b1NrH8UZK49ZJWy%2BKq%2FWmehguvzqoGnzetR2fqrwMalLx4WWFSK5j2XpzgxoSN874hUBY6DWKBS09%2FZqgWQkDnH8LftccKOhlUk%2Fxwm%2B88TEtGBZeB2mVyeCRgKvWQhIvh8IJx6mZ9Pmh3n1yg4n1xx8l%2FYufV7iuJqKy5H2P0%2FGwGEkXFyMBwsz8Ua6yhJwPVEVKqi%2BFW5hZa4jf28UeWOSd88AN4r3E6jgD9cXhs5plib%2FNaQrn3zShDRE5l0r5RzR8OT8HIfgc4VpqW%2BpOzQuW2d6l169i%2Fvnm%2F77wZyuUvORw31Jj%2Ft%2BmaEeTA1aqxf8QzaA5sN7ImCLcaOfMNjMgOwFOrMBv1pBHVGwhQpScAEHbJqWPGfUx2czrwtVQvRlNwcZzDCpramC98FeBs7N5C5pXDJl8FSL6LS2SdLYEc1W73ViwxihGgxL5k9U%2FcALwuDxF1YzvCZ%2B7wXUB33NGARN8IqcaWgT1tTeGYk1UStCWFamZgyhhJoClzSTmiDs%2BZfs0v5hXTnAEvgQbSEBH3cQhMWYcfdaSM7Dhk3FmEoPTDf8DqksqpZ6cuQpXCAcJrTpATVJA20%3D&X-Amz-Signature=80ce511bc6f5591d1b24da7df819606f16e69cbcaad237909bf5bd5469d7fb2b&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22" alt="alt text" width="250px" height="500px">

### Logic
- ID, PW  → Correct state check: rx java 이용하여 옳은 인풋값인지 검수
- join click → loading → Login page : 서버에 가입 요청을 하고 성공 실패 여부 토스트 창으로 출력

### Register courses page
> 수강신청 페이지
<img src="https://s3.us-west-2.amazonaws.com/secure.notion-static.com/db7064b9-2145-455a-999c-f4273d718c1d/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=ASIAT73L2G45K3FXFIPA%2F20190917%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20190917T041318Z&X-Amz-Expires=86400&X-Amz-Security-Token=AgoJb3JpZ2luX2VjEKD%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLXdlc3QtMiJIMEYCIQCvJ81GiUmnpPfmntlyHtXiyyaRx6ZKnhFAbXDwtOXGRQIhALSkEmfqQW4S7DMzUE8HS%2B1bT5zJyldng5xu9OYgWgO6KtoDCGkQABoMMjc0NTY3MTQ5MzcwIgytu4YgiPrZe2OI9gAqtwM8zUa9ItMHD2iMVUg0ztnECTBzrDk8hDTHvaYjDIBNVWc6aAE3B1jZy5O3bcXwvwk6Sh9ipvyF9jgcqw6Cw%2BgRPxtASUov06Pnsn1aNgl9Jl8jFrVb4o%2Bq0grMPvWJFta5jf53eg9DvMuiLBpsTIlwEg8A3NENpTMI%2B75q%2BJXzVwI%2BXbXL1BZJgVEsxdo6qm2c1Qe%2FWXcw6wHOZdQSS3Q9yOv%2B%2BoY%2BcIVvv4I6FSkWpErjggzqN4mJ0jPObceWkfqIcxGfc%2BD2er%2Bz6dihYPHTyee3NXUSm2vbNMCRD%2Bm8wKEmBgr7S5jJQHRZENzo816VQI7xrAI8B0bmqhwGhDPnfDAs5io3IgIpkMManUN4aeECmS%2B%2FrQe5EOOMWiCo9xxfY1mtvxhx0FVfGSLw%2FjWCs8jEwxgkD2gqDL36oV0D9MlaK1756Ysqy8BxgtK%2Bn5ZSNB6SGGZ8U4ryk%2FgfdBgMZN47f7duXsNgJHTfcDbomkZfR4RQKK7ttptyBleuB3LxCSaWaB%2BhXcPmTKiAtV1cI%2Bx5kbmPtQdAVCNkse4rZcU8n1ZqrOEBO%2B0DKqUBcdBNBQ4QBKcUMK3BgOwFOrMBSZtS%2BuU17LXrRqmBwele0yMWgdiYv1apIVd%2BW8VDuOI93zxgMTOroNA0w%2BCChH38SdVUqZUS%2B6CMZwFtkypyXRZSf2ZNntSImjG03%2BR1DItVCyVY4fxT0iPiOCO5hZKvgOIaBPpVMM7lEesI19unG9WLgKzpd0cbACJbnKBiAHdYdSeEQe3YhZ4SiVsCRW9PSpnPgIhzga3DaPFt%2Ba8MWGKIf4g8HtLTqc1AgrQ1nR9mapY%3D&X-Amz-Signature=3019e3a6ba00f142d96f040a7473b6af5ff38a6de378e7991c3e4e1134b8af16&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22" alt="alt text" width="250px" height="500px">

### Logic
- {school_id, grade} send to server→{course(과목) List} receive : 등록 가능한 과목 리스트 보여주기
- course(과목) list → student_course OK → success button(관계가 없을 시 default button) : 이미 등록을 한 과목이면 신청 완료된 버튼 그렇지 않으면 기본 버튼
- course(과목) button click →  {user_id, course_id} send to server →server request : 과목 신청
- registered course button click → {course_id, user_id} send to server → unregister request : 등록 취소


### Timetable page 
> 사용자가 수강 중인 시간표 페이지
<img src="https://s3.us-west-2.amazonaws.com/secure.notion-static.com/8246f022-3f0d-4915-a1ba-27e835fd683c/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=ASIAT73L2G45NYVN5TP3%2F20190917%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20190917T042628Z&X-Amz-Expires=86400&X-Amz-Security-Token=AgoJb3JpZ2luX2VjEKD%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLXdlc3QtMiJHMEUCIQC1RBHjvo0axk88rjsAlOPBCcxc4S%2B8Xw%2Bvj2GydlIdTQIgAMbQDZeQ3eO357YD4fWfx68mQw%2FEbMCb8igys2nr9kcq2gMIaRAAGgwyNzQ1NjcxNDkzNzAiDGiC0oscIRJnTMQgzSq3A7xuJUBMUR05%2FzXPE39S2%2FhAGwLs7Xuz27qRjZ6OGMJWHsmxOQq3IJZ1Q14mhDC9BQGg7ENKTqFLpjTwGxNxYY26r4mwBfATdTlSiXjruCqAkqWCgK5GmoPBeHWApeKAm1aAhqg10HtVeiRvaVYZtRavljjueWekwpSa63L7HoC%2B%2FZwQZA7DNARfjIvsKeLWZ2rAhe4q3xhnDS%2BFWf85z4C6JCnvGq9AAZXRstutNDHZIrpFKLUSeI972WNNCTXFNX9g6fN5d93BLOC%2FDy71eS41dt2h4miRTxBJqwqhWkPUx9zFJO82SiMa9t27iGZ%2BkKjaxg24NBgFCD%2Bmzq%2FlOued7OVeQyJUtr6HmnvGYtLs6MyuKr1BJZuZAx2fj79Z6PEbDQeyq4x61dZNWphv%2BWGBLMyTfV6lECE6pL3Uao6K3Wf1KLB81Um8cyR4PwdHgzp58Dvzr7YqRmt9iLSrICr7Pi3sdolvfTpTZ8m1%2BaZ2t0t4ic4dninWPByBak12l7YPvKIW7b%2Fzc3GgeNvFquduyYkXnkw5UR4B9qOjrrK3xPcFw9L0B31OlJSCihdD6ZNabDJN2VMwtsqA7AU6tAFuUOdCikHqHLl3Rd%2Bp9O7r3F6R7eDBs91CAevYD27dtihDSEZhEUjBoIrBKvnQOm%2FwWDD3cIDLF6pKwTLyAuAs4aeFcPHkC9r4Z0BxE1IIRQCVbhmNyHSKcOI8rh%2BZz76cWIRfbiWG6tfkPQ2LjA2OY%2FZt7y4vAQTH4gddKbJioy4FN1FUYGzQ6IEkh58A2Qh7%2FuI5CdU3bPlSf95Vl7ELK6gQqbMqcBHPBzQG2zmLOJNfbFQ%3D&X-Amz-Signature=10a9e13e9148b5d66915381331ddf65322d378e3197161f4a1b698968e600895&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22" alt="alt text" width="250px" height="500px">

### Logic
- {student_id} → {section list} →{time_location} : 학생 아이디를 통하여 사용자가 수강하는 수업 및 시간 반환 받음
- time_location → make time table : 분반 별로의 수업시간을 받고 이를 이용하여 화면에 뿌려줄 시간표 만들기





