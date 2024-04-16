# 예약 구매 서비스 - [Docs](https://github.com/yongbeomj/pre-order/wiki)
MSA 구조로 설계한 뉴스피드 서비스 + 실시간 재고 관리 서비스 입니다.</br>
백엔드의 API 엔드포인트 구현 및 MSA 및 대규모 트래픽을 다뤄보기 위한 학습용 프로젝트입니다.

### Skills
Java, Spring boot, Spring Data JPA, MySQL, Redis, Docker, Gradle

### 주요 기능 - [상세보기](https://github.com/yongbeomj/pre-order/wiki/Features)
- 뉴스피드 : 본인이 팔로우 한 유저 활동 확인
- 실시간 재고 관리 : 제품 구매 과정에서 발생되는 결제 상태 추적 및 재고 계산 <br>


### 시나리오 설정
- 예약 상품 재고 10개, 동시 결제 시도자 10000명
- 오픈 시간 이후에만 결제 시도 가능
- 결제 화면에서 20% 이탈 (ex. 고객 변심)
- 결제 진행 중 20% 결제 실패 (ex. 카드사에 의한 결제 취소 - 잔액 부족 등)

### Docker run
```bash
$ docker-compose up -d
```

### API 명세서 - [바로가기](https://documenter.getpostman.com/view/19637355/2sA2r3b73R)

### ERD
<details>
<summary>여기를 눌러주세요</summary>
<div markdown="1">       

![erd final](https://github.com/yongbeomj/pre-order/assets/87436495/4b1b0f58-825d-4a6a-8420-c6e3184c56e0)
	
</div>
</details>

### Trouble Shooting
- 재고 동시성 이슈 : [바로가기](https://github.com/yongbeomj/pre-order/wiki/TroubleShooting)

### 보완할 점 및 추가 학습 방향 - [바로가기](https://github.com/yongbeomj/pre-order/wiki/%EB%B3%B4%EC%99%84%ED%95%A0-%EC%A0%90-%EB%B0%8F-%EC%B6%94%EA%B0%80-%ED%95%99%EC%8A%B5-%EB%B0%A9%ED%96%A5)

