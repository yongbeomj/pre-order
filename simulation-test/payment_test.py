import requests
import random
from concurrent.futures import ThreadPoolExecutor

PAYMENT_API_URL = "http://localhost:8085/api/payments"
ORDER_API_URL = "http://localhost:8086/api/orders"


# 주문 생성
def create_order(user_id):
    url = f"{ORDER_API_URL}"
    payload = {
        "userId": user_id,
        "productId": random.randint(1, 5),
        "quantity": random.randint(1, 10)
    }

    try:
        response = requests.post(url, json=payload)
        if response.status_code == 200:
            order_id = response.json()['response']['orderId']
            print(f"주문 생성 Success (orderId: {order_id})")
            return order_id
        else:
            print(f"주문 생성 Fail")
            return None

    except Exception as e:
        print(f"주문 생성 Error sending request to {url}: {e}")
        return None


# 결제 프로세스 진입
def enter_payment(order_id):
    url = f"{PAYMENT_API_URL}/enter"
    payload = {
        "orderId": order_id
    }

    try:
        response = requests.post(url, json=payload)
        if response.status_code == 200:
            payment_id = response.json()['response']['paymentId']
            print(f"결제 프로세스 진입 Success (paymentId: {payment_id})")
            return payment_id
        else:
            print(f"결제 프로세스 진입 Fail")
            return None

    except Exception as e:
        print(f"결제 프로세스 진입 Error sending request to {url}: {e}")
        return None


# 결제 취소
def cancel_payment(payment_id):
    url = f"{PAYMENT_API_URL}/cancel/{payment_id}"

    try:
        response = requests.post(url)
        if response.status_code == 200:
            payment_id = response.json()['response']['paymentId']
            print(f"결제 취소 Success (paymentId: {payment_id})")
            return payment_id
        else:
            print(f"결제 취소 Fail")
            return None

    except Exception as e:
        print(f"결제 취소 Error sending request to {url}: {e}")
        return None


# 결제 처리
def make_payment(payment_id):
    url = f"{PAYMENT_API_URL}/progress/{payment_id}"

    try:
        response = requests.post(url)
        if response.status_code == 200:
            payment_id = response.json()['response']['paymentId']
            payment_type = response.json()['response']['paymentType']

            if payment_type == 'COMPLETED':
                print(f"결제 처리 Success (paymentId: {payment_id})")
            else:
                print(f"결제 처리 Fail")
        else:
            print(f"결제 처리 Fail")

    except Exception as e:
        print(f"결제 처리 Error sending request to {url}: {e}")


# Test Simulation
def test_simulation(user_id):
    # 주문 생성
    order_id = create_order(user_id)

    if order_id is not None:
        # 결제 프로세스 진입
        payment_id = enter_payment(order_id)

        # 결제 취소 - 결제 화면 진입 후 변심 이탈 (20%)
        if random.random() <= 0.2:
            cancel_payment(payment_id)
            return

        # 결제 처리 - 결제 시도 중 결제 실패 (20%)
        make_payment(payment_id)


def main():
    # 결제 테스트 시나리오 request
    user_ids = range(1, 10001)
    num_requests = len(user_ids)

    with ThreadPoolExecutor(max_workers=num_requests) as executor:
        tasks = [executor.submit(test_simulation, user_id) for user_id in user_ids]

        for future in tasks:
            future.result()


if __name__ == "__main__":
    main()
