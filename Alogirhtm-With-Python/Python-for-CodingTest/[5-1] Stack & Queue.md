# 스택(Stack)

- **선입 후출**: 먼저 들어 온 데이터가 나중에 나간다.
- 입구와 출구가 동일한 형태로 스택을 시각화



## 스택 구현 (Python)

> List 를 이용한다.

```python
stack = []

stack.append(5)
stack.append(2)
stack.append(3)
stack.append(7)
stack.pop()
stack.append(1)
stack.append(4)
stack.pop()


# 최상단 원소부터 출력 = 먼저 들어온 것부터
print(stack[::-1])
# 최하단 원소부터 출력 = 나중에 들어온 것부터
print(stack)
```

> [1, 3, 2, 5]
> [5, 2, 3, 1]





# 큐(Queue)

- **선입선출**: 먼저 들어 온 데이터가 먼저 나간다.
- 입구와 출구가 모두 뚫려 있는 터널 형태 / 대기열



## 큐 구현(Python)

- collections 모듈의 deque 클래스를 이용한다.

  > list 로 큐를 구현하는 경우 시간복잡도가 더 크다.

- append(), popleft() 는 O(1)  의 시간복잡도를 가진다.

```python
from collections import deque

queue = deque()

# 삽입(5) - 삽입(2) - 삽입(3) - 삽입(7) - 삭제() - 삽입(1) - 삽입(4) - 삭제() 
queue.append(5)
queue.append(2)
queue.append(3)
queue.append(7)
queue.popleft()
queue.append(1)
queue.append(4)
queue.popleft()

# 먼저 들어온 순서대로 출력
print(queue)

# 나중에 들어온 원소부터 출력
queue.reverse()
print(queue)
```

> deque([3, 7, 1, 4])
> deque([4, 1, 7, 3])





## 재귀 함수(Recursive Function)

- 자기 자신을 다시 호출하는 함수
- 복잡한 알고리즘을 간결하게 작성할 수 있으나, 다른 사람이 이해하기 어려운 코드가 될 수 있으므로 신중히 사용한다.
- 모든 재귀함수는 반복문을 이용하여 동일한 기능을 구현할 수도 있다.
  - 재귀 함수가 반복문보다 유리한 경우도, 불리한 경우도 있다.
- 함수 호출 시 컴퓨터 메모리 내부 Stack 에 함수가 쌓이게 된다. 
  - stack 자료구조이용 시 스택 대신 사용되기도 한다.
    - DFS 구현 시 자주 사용된다.
  - 최대 재귀 깊이가 정해져있어 일정 횟수 초과 시 오류가 뜬다.
    - 종료 조건을 제대로 명시해야 한다.

```python
def recursive_function(i):
  if i == 10:
    return
  print(i,'번째 재귀함수',i+1,'번째 재귀함수 호출')
  recursive_function(i+1)

recursive_function(1)
```

> 1 번째 재귀함수 2 번째 재귀함수 호출
> 2 번째 재귀함수 3 번째 재귀함수 호출
> 3 번째 재귀함수 4 번째 재귀함수 호출
> 4 번째 재귀함수 5 번째 재귀함수 호출
> 5 번째 재귀함수 6 번째 재귀함수 호출
> 6 번째 재귀함수 7 번째 재귀함수 호출
> 7 번째 재귀함수 8 번째 재귀함수 호출
> 8 번째 재귀함수 9 번째 재귀함수 호출
> 9 번째 재귀함수 10 번째 재귀함수 호출



### 팩토리얼 구현 예제

```python
# 반복적으로 구현한 n!
def factorial_iterative(n):
  result = 1
  # 1 부터 n까지의 수를 차례대로 곱하기
  for i in range(1,n+1):
    result *= 1
  return result

# 재귀적으로 구현한 n!
def factorial_recursive(n):
  # 종료 조건 : n이 1 이하인 경우 1을 반환
  if n <= 1:
    return 1
  # n! = n * (n-1)!
  return n * factorial_recursive(n - 1)
```



### 최대공약수 계산 (유클리드 호제법) 예제

- 유클리드 호제법: 두 개의 자연수에 대한 최대공약수를 구하는 알고리즘
  - 두 자연수 A, B에 대하여 (A>B) A를 B로 나눈 나머지가 R
  - A와 B 의 최대공약수는 B와 R의 최대공약수와 같다.

```python
def gcd(a, b):
  if a%b == 0:
    return b
  else:
   return gcd(b, a%b)

print(gcd(192, 162))
```

> 6



> 본 내용은 *이것이 취업을 위한 코딩 테스트다 with 파이썬 - 나동빈* 책을 통하여 학습한 후 정리하였습니다.