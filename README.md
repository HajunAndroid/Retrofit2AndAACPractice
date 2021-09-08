# Retrofit2AndAACPractice
###### ('깡샘의 안드로이드 프로그래밍-강성윤' 도서의 실습 코드를 참고한 것임을 밝힙니다.)

<img src = "https://user-images.githubusercontent.com/87768226/132446563-71f977e9-2ac9-4dd7-9ef5-9ea26294cd83.PNG" width="35%" height="35%">

### 실습 설명
##### > 'https://newsapi.org' 에서 제공하는 뉴스를 Retrofit을 사용해 받습니다.
##### > 화면에 출력할 때 RecyclerView를 사용하고, 페이징(Paging)으로 데이터 3개씩 받아와 추가합니다.
##### > 네트워킹이 불가능한 경우 룸(Room)을 통해 DB에서 데이터를 획득합니다. (DB에는 서버에서 받은 최신 데이터 3개만 저장합니다.)
##### > 룸 또는 서버로부터 데이터를 획득하는 작업은 뷰모델(ViewModel)에 작성합니다.
##### > 데이터를 획득해 화면에 출력하는 부분을 소스코드에서 하지 않고 데이터 바인딩(Data Binding)을 이용합니다.
