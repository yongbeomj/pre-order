# 예약 구매 서비스
MSA 구조로 설계한 뉴스피드 서비스 + 실시간 재고 관리 서비스 입니다.</br>
백엔드의 API 엔드포인트 구현 및 MSA 및 대규모 트래픽을 다뤄보기 위한 학습용 프로젝트입니다.</br>
프론트엔드는 별도 구현되지 않은 상태입니다.

### Skills
Java, Spring boot, Spring Data JPA, Feign Client, JWT, MySQL, Redis, Docker, Gradle

### 주요 기능
- 뉴스피드 : 본인이 팔로우 한 유저 활동 확인
- 실시간 재고 관리 : 제품 구매 과정에서 발생되는 결제 상태 추적 및 재고 계산

### Docker run
```bash
$ docker-compose up -d
```

### ERD
<details>
<summary>여기를 눌러주세요</summary>
<div markdown="1">       

![erd final](https://github.com/yongbeomj/pre-order/assets/87436495/4b1b0f58-825d-4a6a-8420-c6e3184c56e0)
	
</div>
</details>

### API 명세서 - [바로가기](https://documenter.getpostman.com/view/19637355/2sA2r3b73R)

