# 💡 [웹 디자인] 웹 큐레이션 SNS - BodyBuddy 프로젝트 💡
```
BodyBuddy : 내 손안의 운동 친구
```
<p align="center">
  <img src = "https://user-images.githubusercontent.com/33833780/136685150-ea4c5503-1d80-4e16-915e-66d7b3ba91d5.png" width="900px">
</p>


<br><br>

## 🗂 Content

1. [🔈 프로젝트 소개](#-프로젝트-소개)
   <br>
   - [📑 타겟 및 목표](#-타겟-및-목표)
   - [📑 개발 환경](#-개발-환경)
   - [📑 Design Resources](#-디자인)
   - [📑 기술 스택 아키텍쳐](#-기술-스택-아키텍쳐)
   - [📑 ERD](#-erd)
   - [📑 와이어프레임](#-와이어프레임)
   - [📑 핵심 기능](#-핵심-기능)
     <br>
2. [🔈 구현 결과](#-구현-결과)
     <br>
3. [🔈 팀원 소개 및 역할](#-팀원-소개-및-역할)
   <br>
4. [🔈 개발 문서](#-개발-문서)
  
<br><br>

## 🔍 프로젝트 소개

`BodyBuddy` : 내 손안의 운동 친구   

<p align="center">
  <img src = "https://user-images.githubusercontent.com/33833780/136685156-03cf1976-7875-43b9-931b-dd542084398c.png" width="800px">   
</p>

운동과 몸에 대한 **관심**이 증가하고 있고, 소통 및 연결의 **필요성**이 높아지고 있다.     
**다이어트**를 하기 위해서, **바디프로필**을 찍기 위해서, **건강**을 관리하기 위해서 운동을 하고 서로 SNS로 공유를 한다.   
운동 관련 앱들은 운동 튜토리얼 서비스 위주이며, **오늘의 운동을 기록하고 다른 사람들과 공유할 수 있는 운동 관리 플랫폼**은 없다.   

<br>

### 🔔 타겟 및 목표

#### 🧿 타겟

    ☝ 운동.. 다른 사람들과 함께 하고 싶다..! 하는 사람
    ✌ 자신의 몸 상태를 다른 사람들한테 평가 받고 싶은 사람
    🤟 자신의 몸 상태를 꾸준히 기록해서 남겨두고 싶은 사람

#### 🏃‍ 목표

```
몸과 운동에 관심이 있는 사람들이 모여 친구처럼 연결 되는 공간   
나의 곁에서 운동을 돕고 동기부여해주는 친구 같은 서비스    
```

    ✔ 운동을 기록하고 변화를 비교한다.
    ✔ 다른 사용자들과 소통하며 함께 운동 할 수 있다.
    ✔ 챌린지에 도전함으로써 자신의 현재 상태를 다른 사용자들로 부터 피드백을 받을 수 있다.
   
<br><br>

### 🔨 개발 환경

- OS : Windows 10

- Server : AWS EC2

  - Ubuntu 20.04.1
  - Docker 20.10.8
  - Jenkins
  - Nginx
  
- Backend

  - Java : Java 1.8.0
  - Framework : SpringBoot 2.2.2
  - ORM : JPA(Hibernate)
  - Nginx : 1.18.0 (Ubuntu)
  - IDE : Intellij 2021.1.3 / Visual Studio Code 1.59
  - Dependency tool : maven-4.0.0

- Frontend 

    - HTML5, CSS3, Javascript(Es6) 
    - Vue 2.0.0
    - vuetify 2.4.0
    - IDE : Visual Studio Code
  
- Database

    - MariaDB
    - MongoDB

<br><br>

### 🎨 디자인

__외부 템플릿 또는 에셋__
- [Vue Argon Design System](https://www.creative-tim.com/product/vue-argon-design-system?affiliate_id=116187) (무료)
  - __사용 기능__ : 디자인 전반 적용
- [Vue Black Dashboard Pro](https://www.creative-tim.com/product/vue-black-dashboard-pro?affiliate_id=116187) (유료)
  - __사용 기능__ : 캘린더 컴포넌트 사용
  
<br><br>

### 🔨 기술 스택 아키텍쳐

<p align="center">
  <img src = "https://user-images.githubusercontent.com/33833780/136685157-25ae3f07-e708-4446-b118-ded17c548d0c.png" width="800px">
</p>

<br><br>

### 🔍 ERD

<p align="center">
  <img src = "https://user-images.githubusercontent.com/33833780/136686792-961a26fe-df90-4fc2-8a26-74cdfabe5ff0.png" width="800px">
</p>

<br><br>

### 🎨 와이어프레임

<p align="center">
  <img src = "https://user-images.githubusercontent.com/33833780/136686798-8dc73f54-3166-4535-be8c-f7e55a9af9b2.png" width="800px">
</p>

<br><br>

### 💎 핵심 기능

<p align="center">
  <img src = "https://user-images.githubusercontent.com/33833780/136685154-9d60b1af-7dbf-4b73-beab-44927a883ce1.png" width="800px"> 
</p>
    
    🔑 1. 운동 기록   
        - 쉽고 편하게 오늘의 운동을 기록한다.   
        - 인바디, 사진, 파워리프팅을 기록하고 결과를 시각화하여 본다. 
    🔑 2. 소통 
        - 같은 목표와 관심사를 가진 사람들과 소통하며 즐겁게 운동한다.   
    🔑 3. 챌린지
        - 운동을 통해 몸을 만들면서 챌린지에 도전하며 사람들의 평가를 받을 수 있다.   
        - 다른 사람들의 몸을 보며 자극을 받을 수 있다.
            
<br><br>


## 🥜 구현 결과

### 🔗 운동 기록, 통계

<p align="center">
  <img src = "https://user-images.githubusercontent.com/33833780/136688042-7376d86a-63d4-4a71-b681-6d59c6172153.png" width="800px"> 
</p>

<br>

#### 🔗 챌린지

<p align="center">
  <img src = "https://user-images.githubusercontent.com/33833780/136688245-63de7854-1ed7-4bc5-875d-6031d8e22598.jpg" width="800px"> 
</p>

<br><br>

#### 🔗 피드, 기타 화면

<p align="center">
  <img src = "https://user-images.githubusercontent.com/33833780/136688651-e7a93096-f38d-407f-b07f-13adc95d2963.png" width="800px"> 
</p>

<br><br>

------------

## 🥜 실행 가이드

#### 설치 & 실행   
```
$ sudo apt install maven   
$ sudo apt-get install -y nodejs   
$ mvn clean package   
$ npm run serve   
```

#### 배포 환경 구성   
[install Docker]   
```
$ sudo apt update && apt upgrade   
$ sudo apt-get install apt-transport-https ca-certificates curl gnupg-agent software-properties-common   
$ sudo apt-get update && sudo apt-get install docker-ce docker-ce-cli containerd.io   
```

[도커에 MariaDB 컨테이너 올리기]   
```
$ sudo docker pull mariadb:latest
$ sudo docker run --name mariadb -p 3306:3306 -e MYSQL_ROOT_PASSWORD=bodya105@ -d mariadb
$ docker container start mariadb
```

[도커에 Jenkins 컨테이너 올리기]   
```
$ docker pull jenkins/Jenkins   
$ docker run --name jenkins -d -p 8090:8080 -v /home/jenkins:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock v $(which docker):/usr/bin/docker -u root jenkins/jenkins   
```
[install mvn / nodejs in Jenkins]   
```
$ sudo apt install maven   
$ sudo apt-get install -y nodejs   
```

[build]   
```
$ mvn clean package   
$ npm run build   
```

[도커에 백엔드 서버, 웹서버 Deploy]   
-	Spring Boot   
```
$ docker container stop   
$ docker container rm   
$ docker rmi back:0.1   
$ docker build -t back:0.1 .   
$ docker run -d --name backend -p 8080:8080 --network network back:0.1   
```

-	Nginx   
```
$ docker container stop   
$ docker container rm   
$ docker rmi front:0.1   
$ docker build -t front:0.1 .   
$ docker run -d --name frontend -p 80:80 --network network front:0.1   
```

<br><br>

## 🥜 팀원 소개 및 역할

<br><br>
|Name|김선혜|김채은|서정훈|안상훈|
|-----|-----|-----|-----|-----|
|Position|Backend (+ Frontend) & CI/CD|Backend (+ Frontend)|Backend (+ Frontend)|Frontend|
|Git|@wkadnsj|@kce0531|@jhidzz|@ahnsang9|

<br><br>

## 🥜 개발 문서

#### [ 📑 Git 컨벤션 ](https://dandelion-leopon-581.notion.site/1f1fc1c904264a7787663b98ba1f4532)

<br>
