# Judger-Java

교내 시험의 채점 시스템은 사용자가 직접 채점하는 방식입니다. 하지만 이는 다음과 같은 문제가 있었습니다:

1. 다양하지 않은 테스트 케이스
2. 실행 시간 / 메모리 사용량 확인 불가
3. 동일한 실행 환경에서 채점하지 않음
4. 일정하지 않은 테스트 케이스

 이러한 문제를 해결하기 위해 교내 시험을 위한 채점 서비스를 만들고자 했습니다. Java을 이용해 솔루션 코드를 제출하고 Docker Container 내부에서 채점하는 프로그램입니다.

 이 프로그램을 학내 알고리즘 스터디 모임에서 동료들의 코드를 채점하는 데 활용하고 있습니다.

 GUI는 Java에서 기본적으로 제공되는 Swing을 이용해 제작하였으며, Back-End는 데이터를 불러오는 로직과 Handling하는 로직, 채점을 하는 로직을 분리해 유지보수가 용이하도록 설계하였습니다. 그리고, 채점이 완료될 경우 채점 로직에서 Event를 발생시켜 Event를 Handling하는 로직을 별도로 작성해 이를 실행함으로써 채점 로직이 GUI를 직접 건드리지 않도록 하였습니다.

## 동작 원리
[Notion Page](https://whip-pickle-c55.notion.site/Judge-System-534e0ab542304d8990fb66648998ed7b)
