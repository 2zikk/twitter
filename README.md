# Twitter 
 > 트위터와 유사한 소셜네트워크 서비스를 구현하는 프로젝트 🐦

# G**it**

## **커밋 규칙**

```
<타입> <제목>

<내용>

```

### **타입**

- ✨ - 새로운 기능 추가
- 🐛 - 버그 픽스
- ♻️ - 리팩터링
- 📝 - 문서 추가/수정
- 🚚 - 리소스 이동 또는 이름 변경
- 🔧 - 설정 파일 추가/수정

그 밖에 다른 타입이 필요한 경우 [gitmoji](https://gitmoji.dev/)를 참고해 목록에 추가 바랍니다.

## **브랜치 전략**

### **github-flow**

- 배포가 필요없는 사이드프로젝트이기 때문에 가장 단순한 전략을 사용합니다.
- release 브랜치 없이 하나의 버전이 만들어졌으면 배포될 수 있다는 개념으로 hotfix가 사소한 기능 변화라고 생각합니다.
    - master: 안정적인 코드가 저장되는 브랜치로 여기서부터 새로운 브랜치가 생성됩니다. 항상 stable하며 production에 배포되는 브랜치입니다.
    - feature branches: 각 기능이 개발되는 브랜치로, master 브랜치에서 파생되고 완료되면 다시 병합됩니다. Pull Request를 통해 코드 리뷰가 이루어집니다.
- git-flow와 달리 브랜치별 구분이 없기 때문에 새로운 기능을 추가하거나 버그를 해결하기 위한 브랜치 이름은 자세하게 어떤 일을 하고 있는지에 대해서 작성해야합니다.
<img width="1293" alt="스크린샷 2023-12-28 13 55 03" src="https://github.com/2zikk/twitter/assets/25236852/7bc2aad8-7aa7-408c-bb6c-2bdfb968f4a5">    

# **프로젝트**

### 패키지 구조

이 아키텍처는 '만들면서 배우는 클린 아키텍처'에서 제안된 표현력 있는 패키지 구조를 기반으로 합니다. 그러나 애플리케이션의 규모를 고려할 때, 여러 인커밍 어댑터가 필요할 가능성이 낮습니다. 이는 인커밍 포트 없이도 프로세스 흐름을 쉽게 이해할 수 있음을 의미합니다. 따라서, 불필요한 추상화 계층을 제거하기 위해 인커밍 포트를 사용하지 않기로 결정했습니다.

<img width="632" alt="스크린샷 2023-12-28 14 00 01" src="https://github.com/2zikk/twitter/assets/25236852/bd8211c3-7df9-4a9b-9319-03ee03b6fb4f">

- adapter : 애플리케이션 계층의 인커밍 포트를 호출하는 인커밍 어댑터와 애플리케이션 계층의 아웃고잉 포트에 대한 구현을 제공하는 아웃고잉 어댑터를 포함합니다.
- domain : 도메인 모델이 속한 패키지입니다.
- application : 도메인 모델을 둘러싼 서비스 계층을 포함합니다. 인커밍 포트, 아웃고잉 포트 인터페이스를 포함합니다.

### **매핑전략**

![Untitled](https://github.com/2zikk/twitter/assets/25236852/8665f3df-82cb-443f-9e4f-8c7a2ef00cd2)

이 매핑 전략은 '만들면서 배우는 클린 아키텍처'에서 제안된 방식을 기반으로 하되, 매핑 오버헤드를 최소화하기 위해 프로젝트의 요구사항에 맞게 일부 수정하였습니다.

우선, 영속성 모델과 도메인 모델을 통합하는 접근법을 채택했습니다. 이 방식은 도메인 모델이 스프링과 JPA에 의존하게 되지만, 이러한 의존성이 매핑 오버헤드보다 더 큰 부담을 주지 않을 것으로 예상됩니다.

변경과 관련된 유스케이스에서는 '완전 매핑 전략'과 유사한 방식을 사용하되, 특정 상황을 제외하고는 웹 모델이 commander를 대체합니다. 이 웹 모델은 usecase 계층에서 생성되며, 서비스 계층에서 도메인 모델로 매핑해 비즈니스 로직을 처리합니다. 이 과정에서 usecase 계층에 웹 의존성이 생기지만, 이는 웹 모델과 command 간의 매핑 비용보다 크지 않다고 봅니다.

Commander의 추가 기준은 모델의 복잡도에 따라 달라집니다. 단일 테이블에 매핑되는 간단한 모델의 경우 웹 모델을 그대로 사용합니다. 반면, 다수의 테이블로 구성된 복잡한 모델의 경우에는 commander를 추가하여 사용합니다.

```
buckpal
└── account
    ├── adapter
    │   ├── in
    │   │   └── web
    │   │       └── AccountController
    │   └── out
    │       └── persistence
    │           ├── AccountPersistenceAdapter
    │           └── SpringDataAccountRepository
    ├── domain
    │   ├── Account
    │   └── Activity
    └── application
        ├── DTO
        ├── query
        │   └── ReadMoneyUseCase
        ├── command
        │   └── SendMoneyUseCase
        └── port
            └── out //15개를 기준으로 DBMS별로 패키지 분리
                ├── LoadAccountPort
                └── UpdateAccountStatePort
```

### 명명규칙

- **웹 모델**
    - 요청 모델: **`request`**
    - 응답 모델: **`response`**
- **서비스** **`usecase`**
- **포트 `port`**
- **어댑터**
    - 인커밍 어댑터 : **`controller`**
    - 아웃고잉 어댑터 : **`PersistenceAdapter`**
- **JPA 레포지토리 `repository`**
- **읽기전용모델 `queryResult`**
