Make With Android Studio
# **인버서스**
---
   ### **1.게임 컨셉**
 ---
 > #### High Concept: 2D 슈팅 액션 게임
 > #### 핵심 메카닉 간단한 규칙과 간단한 조작을 통해 다가오는 적을 섬멸하고 살아남는 게임


 ### **2.개발 범위**
 ---
 >   #### **게임 규모**
 >>  ##### **1.맵**
 >>> ###### 1개(셀 50 X 50 사이즈)

 >>  ##### **2.캐릭터** 
 >>> ###### **크기** : 1셀 크기
 >>> ###### **총알** : 최대총알6알
 >>>> ###### **총알발사쿨타임** :0.2초
 >>>> ######  **한발당 데미지** : 적 사살, 벽 뿌시기

 >> ##### **3.적**
 >>> ###### **크기:** 1셀 크기
 >>> ###### **특징:** 지나간 길은 플레이어가 지나갈수없는 벽으로 만듬 

>> ##### **4.게임 최종 목적**
>>> ###### **최고 점수 만들기 (적 한명당 score 50추가, 1초 이내에 사살시 콤보 점수 부여)**

 ### **3.예상 게임 실행 흐름**
 ---
 > #### **로비 -> 게임 플레이 -> 종료 OR 로비로 이동**

### **4.시중 판매중인 게임 화면 스크린샷**
---
> ### **게임 로비화면**
> <img width="1277" alt="로비화면" src="https://github.com/DoHanK/SmartPhoneTermProject/assets/94627795/e0753071-f335-4152-842d-fbd059d52eba">
>
> ### **게임 플레이 화면**
>
><img width="1038" alt="플레이화면" src="https://github.com/DoHanK/SmartPhoneTermProject/assets/94627795/807146de-6c50-498c-8cb2-a4008f7b62eb">
>
> ### **게임 타격 화면**
> 
><img width="1279" alt="타격화면" src="https://github.com/DoHanK/SmartPhoneTermProject/assets/94627795/f2d6a60a-c205-4571-aae6-a618f6dc77e3">
>
> ### **게임 오버화면**
>
><img width="1279" alt="게임오버화면" src="https://github.com/DoHanK/SmartPhoneTermProject/assets/94627795/204c7d91-a74f-4d6a-a3d4-0ba1d58e1f49">
>

### **5.  개발 일정**
---
주차 | 개발 일정|
:---|:---
1주차| 프레임워크 설계
2주차| 프레임워크 제작
3주차| UI 제작
4주차| 게임 로직 설계
5주차| 게임 로직 제작
6주차| 디버깅
7주차| 게임 컨텐츠 제작
8주차| 디버깅
9주자| 게임 최종 검수

### **6.현재 진행상황**
>5주차 진행상황
>>로비화면 구현
>>><img width="253" alt="로비화면" src="https://github.com/DoHanK/SmartPhoneTermProject/assets/94627795/09f8b6de-8120-4d01-867c-5171b5d88895">
>>플레이 화면
>>> 종횡스크롤 이동, 플레이어 이동 시 잔상, 총알 발사, 맵 바운드 예외처리
>>><img width="263" alt="게임화면" src="https://github.com/DoHanK/SmartPhoneTermProject/assets/94627795/24715f80-923c-411f-9bda-4fb98dd4747d">
