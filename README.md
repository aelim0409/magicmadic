# magicmadic
  
  월경 주기 기록 캘린더

## 1. 프로젝트 설치하는 방법

  깃허브에 현재 올려져 있는 파일을 다운 받아 실행시키면 된다.

## 2. 프로젝트 사용법

  ### 서버 연동
      
      서버 연동 AsyncTask를 상속받은 클래스를 만들어서 doInBackground 메소드를 작성한다.
      앱에서 서버로 보낼 값을 sendMsg에 String 형식으로 담아 보내 서버에 값을 요청한다.
     
     API16(젤리빈) 미만 버전에서는 AsyncTask 선언을 UI Thread에서 해주지 않으면 오류가 발생한다. (API 16 이상(JELLY BEAN)의 버전에서는 자유롭게 사용할 수 있다.
     excutes(Params)는 UI 스레드에서 직접 호출해야 한다.
     수동으로 onPreExecute(), onPostExecute(Result), doInBackground(Params...), onProgressUpdate(Progress...) 호출하면 안된다.
     
 ### 회원가입
 sign in을 통해 회원가입을 진행한 후 log in 을 통해 본인의 id , pw 에 맞게 로그인을 실행한다. <br>
 
 ### main 화면
 사용자의 가임기/비가임기/생리기간 을 보여주며 배란 예정일, 생리 예정일, 마지막 생리일, 가임기 시작, 평균 생리주기를 한눈에 확인할 수 있다. <br>
 
<img src= "https://user-images.githubusercontent.com/72659915/120214314-43d31f00-c26f-11eb-82d2-5890c732c8dc.png" width = 300 height=500>

 
 ### calendar 화면
 ( 빨간색 점 : 생리 주기: / 파란색 점 : 예상 가임기 / 초록색 점 : 예상 배란기<br>
: 생리 시작 버튼을 누르고 저장을 누르면 생리 시작일이 저장이 된다. 예상 주기일을 통해 예상 생리 주기와 생리 기간 가임기를 예상하여 달력에 표시한다.   <br>
: 생리 종료 버튼을 누르고 저장을 누르면 생리 종료일이 저장이 되며, 저장된 기간을 바탕으로 예상 가임기를 달력에 표시된다. <br>
: symptom 화면을 통해 자신의 컨디션을 체크할 수 있으며 사용자의 생리전 증후군을 체크할 수 있다. <br>
: mucus 화면을 통해 자신의 질 분비물을 체크하여 부인과에 갔을 때 자신의 증상에 대해 더 자세히 나타낼 수 있다. <br>

<img src= "https://user-images.githubusercontent.com/72659915/120213248-ee4a4280-c26d-11eb-97a3-2d126492d473.png" width=300 height =500>


 
 ### 리마인더
 
 리마인더의 종류는 다음과 같다 ( / 경구 피임약 알림/ 생리 시작 알림/ 병원 검진 알림 / 목표 수분 섭취량 달성 알림/ 목표 운동량 달성 알림/ 목표 수면시간 달성 알림 )
 
 <img src= "https://user-images.githubusercontent.com/72659915/120213671-6ca6e480-c26e-11eb-89b8-db5bc0576610.png" width=300 height =500>
 
 #### 목표 수분 섭취량 달성 알림/ 목표 운동량 달성 알림/ 목표 수면시간 달성 알림
 : 사용자가 목표량을 설정하고 확인을 누른 다음 해당 알람에 대해 스위치를 켜야 알림을 받을 수 있다.
 #### 경구 피임약 알림
 : 사용자에게 알람이 울릴 시간과 기간, 시작일을 입력을 받고 스위치를 켜야 알림을 받을 수 있다.
 #### 병원 검진 알림
 : 사용자에게 검진일을 입력을 받고 스위치를 키면 설정일 3일 전부터 저녁 6시에 알람을 받을 수 있다. 
 
 
 ### SELFTEST
 
 사용자가 원하는 검사를 리스트에서 고른 다음 자신의 증상에 맞게 체크한 후 DONE 버튼을 누르게 되면 결과가 나온다. 

<img src = "https://user-images.githubusercontent.com/72659915/120213823-a37cfa80-c26e-11eb-9c68-35e8f036d5b5.png" width=300 height=500>
<img src = "https://user-images.githubusercontent.com/72659915/120213902-bc85ab80-c26e-11eb-98ae-c8694ae855c9.png" width = 300 height=500>


### GRAPH

사용자의 월경주기, 일일 수분 섭취량, 운동량, 수면시간을 그래프로 보여준다. 

<img src ="https://user-images.githubusercontent.com/72659915/120214583-a1676b80-c26f-11eb-8293-40a3974ab05b.png" width= 300 height=700>




## 3. 프로젝트 기능 설명

  #### 캘린더
입력 받은 생리 주기를 캘린더 화면에 표시하여 자신의 주기를 한눈에 알아보기 쉽게 했다. <br>
생리 시작 날짜에 맞춰 주기와, 배란일, 가임기를 예상하여 캘린더에 표시해주었다. <br>
사용자의 수면시간, 물섭취량, 운동시간, 생리 전 증후군에 해당되는 증상들 입력 받는다. <br>

#### 그래프
입력된 사용자의 생리 주기, 수면시간, 물섭취량, 운동시간을 바탕으로 이를 그래프화 하여 보여준다. <br>
사용자는 자신의 생리 주기를 시각적으로 확인하여 본인의 컨디션을 확인할 수 있다. <br>

#### 리마인더
리마인더 탭에서는 경구 피임약 알림, 생리 시작 알림, 병원 검진 알림, 목표 수분 섭취량 달성 알림, 목표 운동량 달성 알림, 목표 수면시간 달성 알림 이렇게 총 6가지가 있다. <br>
** 경구 피임약 알림 : 피임약 복용을 잊지 않게 알람을 설정할 수 있다. <br>
** 생리 시작 알림 : 예상 생리 시작일 3일전에 알람을 울린다. <br>
** 병원 검진 알림 : 사용자가 설정한 날짜를 기준으로 3일 전부터 저녁 6시에 알람이 울린다. <br>
** 목표 수분 섭취량 달성 알림 : 사용자가 설정한 목표 섭취량을 달성하면 알람이 울린다. <br>
** 목표 운동량 달성 알림: 사용자가 설정한 목표 운동량을 달성하면 알람이 울린다. <br>
** 목표 수면시간 달성 알림: 사용자가 설정한 목표 수면시간을 달성하면 알람이 울린다. <br>

#### 자가 진단 test
여성들에게서 흔하게 나타나는 질병들에 대해 자가진단 할 수 있다. <br>
Test 를 통해 도출된 결과로 본인의 몸 상태를 간단히 확인할 수 있다. <br>
테스트 결과 점수가 일정 수준 이상이면 병원 방문을 추천한다.



## 4. 저작권 및 사용권 정보

  ### 그래프 화면에서 사용한 오픈소스  GraphView github 
주소 : https://github.com/jjoe64/GraphView
### 캘린더 화면에서 사용한 오픈소스 CalanderView-material github 
주소 : https://github.com/prolificinteractive/material-calendarview



## 5. 버그


## 6. 프로그램 작성자 및 도움을 준 사람
 신애림 : https://github.com/aelim0409 <br>
 김한슬 : https://github.com/slcloe <br>
 김수라 : https://github.com/srsw000521 <br>
 박수민 : https://github.com/moong2 <br>
 <br><br>
 magicmadic BACKEND : https://github.com/moong2/MedicMagic-BACKEND 
## 7. 버전 ( 업데이트 소식 )



