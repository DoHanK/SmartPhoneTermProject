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

주차 | 개발 일정|진행여부|
:---:|:---:|:---:
1주차| 프레임워크 설계| o
2주차| 프레임워크 제작 | o
3주차| UI 제작 | o 
4주차| 게임 로직 설계 | o
5주차| 게임 로직 제작 | o 
6주차| 디버깅 | o 
7주차| 게임 컨텐츠 제작 | o 
8주차| 디버깅 | o 
9주자| 게임 최종 검수 | o

### **6.현재 진행상황**
---
>**중간주차 진행상황**
>> *변경 사항*
>>> *총알 개수* 8-> 6개로 변경 , 사유: 미적인 이유로 수
>>>
>>> 
>>> *총알 발사 쿨타임* 0.1(s) -> 0.2(s) 로 변경
>>>
>>> 
>>> *총알 리젠쿨탐 추가* 1(s)
---
>> *게임 오브젝트 설명*
>>> Camera -> Update(): 플레이어의 위치를 선형 보간하여 점차 따라감 , Draw():x
>>>
>>> 
>>> JoyStick -> Update(): x ,Draw() : 조이스틱을 그려줌 , OnTouch() :터치해서 움직였을 때 움직인 방향을 계산해서 각도로 가지고 있음
>>>
>>> 
>>> Bullet , Player, GameWorld -> Update(): 각자 dx, dy의 위치를 계산함,  Draw(): 원하는 도형을 그림
>>
---
>>*인게임 화면*
>>>로비화면 구현
>>>><img width="253" alt="로비화면" src="https://github.com/DoHanK/SmartPhoneTermProject/assets/94627795/09f8b6de-8120-4d01-867c-5171b5d88895">
>>>플레이 화면
>>> 종횡스크롤 이동, 플레이어 이동 시 잔상, 총알 발사, 맵 바운드 예외처리
>>>>><img width="263" alt="게임화면" src="https://github.com/DoHanK/SmartPhoneTermProject/assets/94627795/24715f80-923c-411f-9bda-4fb98dd4747d">
---
>> *주당 커밋수*
>>> 2주차 , 1 커밋
>>>> ![커밋2주](https://github.com/DoHanK/SmartPhoneTermProject/assets/94627795/c9a939b8-7d10-4e61-86c0-63c10ff3893e)

>>> 3주차 , 8커밋
>>>> ![커밋3주](https://github.com/DoHanK/SmartPhoneTermProject/assets/94627795/125a9107-ec30-4365-85e1-09d7f9854ac4)

>>> 4주차, 8커밋
>>>>![커밋4주](https://github.com/DoHanK/SmartPhoneTermProject/assets/94627795/728de3ad-df1a-48e1-aa3b-68efb70fb415)
---
>> *어려웠던 부분: 카메라 구현 부분, 익숙치 않은 언어, 익숙하지 않은 플랫폼개발에서의 개발 *

===    
>**최종주차 진행상황**
>> *변경 사항*
>>> 적 한명당 score 50점  -> 1점으로 변경 , ~~1초 이내에 사살시 콤보 점수 부여~~
---
>>*인게임 캡쳐본*
>>>  플레이 이동했을 때
>>>>![이동 및 체력 감소](https://github.com/DoHanK/SmartPhoneTermProject/assets/94627795/59bdfbb1-dd8c-4d69-83f9-5a7015d9ce49)

>>>  플레이 피격당했을 때
>>>>![Player타격시](https://github.com/DoHanK/SmartPhoneTermProject/assets/94627795/a1b153e2-381b-4ebc-98ba-768b406b9b3c)

>>> 적 공격했을 때
>>>>![타격시화면](https://github.com/DoHanK/SmartPhoneTermProject/assets/94627795/1d71e92a-f045-434b-abad-e3895cf9b769)

>>> 일시정지 화면
>>>>![Paused화면](https://github.com/DoHanK/SmartPhoneTermProject/assets/94627795/c4feaecc-9e45-400b-a4d6-d3e9f9862374)

>>> 게임 오버 화면
>>>>![종료화면](https://github.com/DoHanK/SmartPhoneTermProject/assets/94627795/c8cba378-2fa1-4bcc-83b3-18d3d880f46d)

>>> 나가기버튼눌렀을때 메세지창
>>>>![나가기버튼 눌렀을때](https://github.com/DoHanK/SmartPhoneTermProject/assets/94627795/7a5a1e08-793f-4bf8-be31-5f05fd2ad54e)
---
>> *주당 커밋수*
>>> 5주차 , 9번
>>>>![커밋5주](https://github.com/DoHanK/SmartPhoneTermProject/assets/94627795/0e9ca329-91f7-4ff4-a8c2-6643196852a3)
>>>
>>> 
>>>6,7주차 , 0번
>>>
>>> 
>>> 8주차 , 2번
>>>
>>> 
>>>> ![커밋8주](https://github.com/DoHanK/SmartPhoneTermProject/assets/94627795/2d801218-dc3d-4a02-a305-09bddcd0500c)
>>>
>>>9주차 , 7번
>>>
>>>> ![커밋9주](https://github.com/DoHanK/SmartPhoneTermProject/assets/94627795/5dfb277c-060c-453c-944b-2072a62d2e42)
---
>> *개발시 참고사항들*
>>> 사용된 기술: x
>>>
>>> 참고한 것들: 웹서핑 , chatgpt
>>>
>>> 
>>> 수업내용에서 차용 한것: 프레임워크, 인터페이스, 오브젝트, 씬 , 메트릭스 , 리소스매니저등 전반적 프레임워크 차용
>>>
>>> 
>>> 직접 개발한것: 게임내의 이펙트들을 거의 다 기본도형을 이용해서 구현, Camera 구현, ToastingImage클래스 구현
>>>
>>> 
---
>>*아쉬웠던 것*
>>> 하고싶은데 못했던 것: 기획했던 개발 범위보다 더 하고싶었지만, 취업준비와 졸작과 알바, 다른과제 등의 이슈로 어느정도의 타협하게 되었습니다.ㅜ
>>>
>>> 
>>> 팔기 위해 보충할 것들: 이펙트도 쫌 더 다듬고, 사운드도 더 찾아보고, 하이 스코어를 기록할수있고, 전세계 유저들과 겨룰 수 있는 시스템을 보충해야 할것 같습니다.
>>>
>>> 
>>> 결국 해결하지 못한 문제/버그: 아직까지 플레이하면서 버그를 발견하지 못했습니다. 하지만 애뮬레이터로 디버깅을 계속하다보면, 성능이 떨어져 스마트폰이 느려지는 현상이 발생했는데, 이건 안드로이드 고유의 문제 같습니다.
>>>
>>> 
>>> 기말 프로젝트를 하면서 겪었던 어려움은 중간발표때도 말씀드렸던 갈비지컬렉터로 인해서 오히려 자원관리가 어려웠습니다. 지금도 잘한건지 모르겠습니다..
>>> 이번수업에서 기대했던 것과 얻은 점은 항상 다루어왓던 개발툴 visualstudio와  c++언어가 아닌 다른 플랫폼과 다른 언어에서의 개발입니다.하지만 얻지 못한 점은 교수님 코드를 참고를 많이 해서 개발을 해서 수박 겉핥기식으로 개발 한 점입니다.
>>>
>>> 
>>> 더 좋은 수업이 되기 위해 변화 할 점: 수업이 진행되는 동안 이해를 돕기 위한 자료 같은게 같이 제공되면 이해하는데 더 도움이 될 것 같습니다. 예를 들면, 프레임워크의 구조를 시각화한 자료, 안드로이드에서 사용된 함수들의 역할을 간략하게 설명하는 자료


