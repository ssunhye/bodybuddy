# 💡 [웹 디자인] 웹 큐레이션 SNS - BodyBuddy 프로젝트 💡
```
BodyBuddy : 내 손안의 운동 친구
```

<img src = "/images/logo_login.png" width="600px">

## 목차

- [프로젝트 소개](#프로젝트-소개)     
- [프로젝트 명세](#프로젝트-명세)
  - [배포 환경](#배포-환경)
  - [개발 환경](#개발-환경)
  - [Design Resources](#design-resources)
  - [핵심 라이브러리](#핵심-라이브러리)
  <br>   

   
## 🔍 프로젝트 소개

`BodyBuddy` : 내 손안의 운동 친구   

<img src = "/images/need.png" width="600px">   

운동과 몸에 대한 **관심**이 증가하고 있고, 소통 및 연결의 **필요성**이 높아지고 있다.     
**다이어트**를 하기 위해서, **바디프로필**을 찍기 위해서, **건강**을 관리하기 위해서 운동을 하고 서로 SNS로 공유를 한다.   
운동 관련 앱들은 운동 튜토리얼 서비스 위주이며, **오늘의 운동을 기록하고 다른 사람들과 공유할 수 있는 운동 관리 플랫폼**은 없다.   

```
BodyBuddy는
몸과 운동에 관심이 있는 사람들이 모여 친구처럼 연결 되는 공간   
나의 곁에서 운동을 돕고 동기부여해주는 친구 같은 서비스    
```

<img src = "/images/function.png" width="600px"> 

BodyBuddy는 운동을 기록하고 변화를 비교할 수 있다.   
그리고 다른사람들과 소통하면서 자극도 받고 좀 더 즐겁게 운동 할 수 있다.   
또한 챌린지에 도전함으로써 자신의 현재 몸 상태를 파악하고 다른 사람들의 몸을 평가하면서 동기도 부여 받을 수 있다.     
따라서 BodyBuddy를 통해 즐겁게 운동할 수 있고 자극을통해 동기 부여를 받는 효과를 기대할 수 있다.   

<img src = "/images/effect.png" width="600px"> 

BodyBuddy의 핵심 기능은 크게 3가지이다.   
**1. 운동 기록**   
  - 쉽고 편하게 오늘의 운동을 기록한다.   
  - 인바디, 사진, 파워리프팅을 기록하고 결과를 시각화하여 본다. 
**2. 소통**   
  - 같은 목표와 관심사를 가진 사람들과 소통하며 즐겁게 운동한다.   
**3. 챌린지**   
  - 운동을 통해 몸을 만들면서 챌린지에 도전하며 사람들의 평가를 받을 수 있다.   
  - 다른 사람들의 몸을 보며 자극을 받을 수 있다.   


<br>

## 🔍 프로젝트 명세
### 배포 환경
- __URL__ : http://i5a105.p.ssafy.io/
- __배포 여부__ : O
- __접속 가능__ : 접속 가능
- __HTTPS 적용__ : X
- __PORT__ :
젠킨스: http://i5a105.p.ssafy.io:8090/
스웨거: http://i5a105.p.ssafy.io:8080/swagger-ui.html

<br>

### 🔍 개발 환경

<img src = "/images/techstack.png" width="500px">

#### Front-end / Web App
- __Framework__ : Vue.js
- __지원 환경__ : Web browser
- __담당자__ : 안상훈, 신해민
<br>

#### Back-end
- __Framework__ : Spring boot
- __Database__ : MariaDB, MongoDB
- __담당자__ : 서정훈, 김선혜, 김채은 
<br>

#### Design
- __Framework 사용__ : O
  - [Vuetify](https://vuetifyjs.com/)
  - [Element Plus](https://element-plus.org/)
- __Design Tool 사용__ : 
- __담당자__ : 안상훈, 김채은 ,김선혜
<br>

### 🔍 Design Resources
__외부 템플릿 또는 에셋__
- [Vue Argon Design System](https://www.creative-tim.com/product/vue-argon-design-system?affiliate_id=116187) (무료)
  - __사용 기능__ : 디자인 전반 적용
- [Vue Black Dashboard Pro](https://www.creative-tim.com/product/vue-black-dashboard-pro?affiliate_id=116187) (유료)
  - __사용 기능__ : 캘린더 컴포넌트 사용
  <br>

__자체 제작 산출물__ (필요시 이미지 또는 설명 첨부)
- LOGO
- CardView
- Button
- Calendar
<br>

### 🔍 핵심 라이브러리
- __WebDesignrankings__
  - __링크__ : https://www.webdesignrankings.com/resources/lolcolors/
  - __소개__ : 색상 조합을 추천해주는 사이트
  - __사용 기능__ : 여러가지 색상 조합 추천
  - __담당자__ : 신해민, 김선혜, 김채은, 안상훈, 서정훈

- __Animate.css__
  - __링크__ : https://animate.style/
  - __소개__ : css 애니메이션 지원 라이브러리
  - __사용 기능__ : 메인 페이지 진입 시 애니메이션 사용
  - __담당자__ : 안상훈,

## 🔍 Git
### Git Convention
#### Default
  - type : subject
  - Body
  - footer
#### Types
  - feat : 기획과 관련한 기능 추가 및 삭제
  - fix  : 버그 수정 및 기존 기능 수정
  - refactor : 리팩토링 (기능 수정 X) * 기능 수정과 리팩토링 구분해서 올려주세요.
  - perf : 성능 향상을 위한 리팩토링
  - style : code style 변경 (white-space, formatting, missing semi-colons, etc)
  - test : 테스트 추가
  - docs : 문서 추가/변경 (ReadMe.md)
  - build: 빌드 관련 변경 (build tool, ci pipeline, dependencies, project version)
  - chore : 그외 변경 ( .gitignore )
#### subject
  - required
  - 59자 이하로 작성해주세요.
  - 추가, 변경, 수정, 삭제, 개선 등 명사형 문장으로 작성
  - 영문의 경우 명령형으로 작성
  - 끝에 온점(.) 생략
  - 파일명이나 함수명은 사용 X (코드에서 보여줄것임)
  - ex) oo 기능 추가, 불필요한 코드 삭제
#### Body
  - optional
  - 한 줄에 59자 이하
  - 추가, 변경, 수정, 삭제, 개선 등 명사형 문장으로 작성
  - subject 외 자세한 내용이 필요하다면 작성
#### Examples
  - feat: 엄청난 버튼 추가
  - fix: <어떤 이유>인한 oo 컴포넌트 버그 수정
  - fix: 오타 수정
  - build: package.json dependency 업데이트
  - refactor: oo 컴포넌트 구조 리팩토링
  - style: white space 제거

