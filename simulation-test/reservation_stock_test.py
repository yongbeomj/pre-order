import requests
import random
import time
import schedule
from datetime import datetime, timedelta

PRODUCT_API_URL = "http://localhost:8084/api/products"


# 상품 생성
def create_product():
    url = f"{PRODUCT_API_URL}/create"
    product_type = random.choice(["COMMON", "RESERVATION"])
    reserved_at = ""

    if product_type == 'RESERVATION':
        random_minutes = random.randint(6, 200)
        reserved_at = (datetime.now() + timedelta(minutes=random_minutes)).isoformat()

    payload = {
        "title": "title",
        "content": "contents",
        "price": random.randint(100, 10000),
        "stock": random.randint(100000, 1000000),
        "productType": product_type,
        "reservedAt": reserved_at
    }

    try:
        response = requests.post(url, json=payload)
        if response.status_code == 200:
            print(f"상품 생성 Success")
        else:
            print(f"상품 생성 Fail")

    except Exception as e:
        print(f"상품 생성 Error sending request to {url}: {e}")


# 예약 상품 재고 조회
def search_reservation_stock(product_id):
    url = f"{PRODUCT_API_URL}/stock/{product_id}"

    try:
        response = requests.get(url)
        if response.status_code == 200:
            product_id = response.json()['response']['id']
            product_stock = response.json()['response']['stock']

            print(f"예약 상품 재고 조회 Success (productId: {product_id}, stock: {product_stock})")
            return product_stock
        else:
            print(f"예약 상품 재고 조회 Fail")
            return None

    except Exception as e:
        print(f"예약 상품 재고 조회 Error sending request to {url}: {e}")
        return None


# (현재 시간 기준) 특정 시간 전 예약 상품 조회
def search_reservation_product(time_offset):
    url = f"{PRODUCT_API_URL}"

    params = {
        "productType": "RESERVATION",
        "timeOffset": time_offset
    }

    try:
        response = requests.get(url, params=params)
        if response.status_code == 200:
            data = response.json()['response']
            data_count = len(response.json()['response'])

            print(f"예약 상품 조회 Success (개수: {data_count})")
            return data

        else:
            print(f"예약 상품 조회 Fail")
            return None

    except Exception as e:
        print(f"예약 상품 조회 Error sending request to {url}: {e}")
        return None


# Test Simulation
def test_simulation():
    # (현재 시간 기준) 특정 시간 전 예약 상품 조회
    time_offset = 5  # 분 단위
    reservation_products = search_reservation_product(time_offset)

    if reservation_products:
        for product in reservation_products:
            product_id = product.get('id')
            if product_id:
                # 예약 상품 재고 조회
                search_reservation_stock(product_id)


def main():
    # 테스트 상품 생성
    for _ in range(100):
        create_product()

    # 재고 조회 테스트 시나리오 실행
    test_simulation()


if __name__ == "__main__":
    main()

    # 1분 주기 작업 예약
    schedule.every(1).minute.do(test_simulation)

    while True:
        schedule.run_pending()
        time.sleep(1)
