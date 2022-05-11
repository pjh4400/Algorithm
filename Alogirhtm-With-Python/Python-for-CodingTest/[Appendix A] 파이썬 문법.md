# [Appendix A] 파이썬 문법



# 자료형

정수형, 실수형, 복소수형, 문자열, 리스트, 튜플, 사전

리스트 - arraylist & vector 형 사용 가능



## 정수형

- 양의 정수, 음의 정수, 0



## 실수형

- 소수점 아래의 데이터를 포함하는 수 자료형

```python
# 소수부나 정수부의 0을 생략할 수 있다
a = 5.
print(a)

a = -7.
print(a)
```

> 5.0
>
> -7.0

- 지수 표현 방식: e나 E 다음에 오는 수는 10의 지수부를 의미한다.  임의의 큰 수를 표현하기 위해 자주 사용. 실수형으로 처리 된다.

```python
# 1억= 10^8 지수 표현 방식
a = 1e8
print(a)

# 752.5
a = 75.25e1
print(a)

# 3.954
a = 3954e-3
print(a)
```

> 100000000.0
> 752.5
> 3.954



## 리스트 

- 여러 개의 데이터를 연속적으로 담아 처리하기 위해 사용

- C/Java의 Array & ArrayList 와 유사
- C++의 vector 자료구조와 기능적으로 유사
- 리스트 대신 배열 혹은 테이블로 부르기도 한다.
- 대괄호([])안에 원소를 넣어 초기화
- 비어 있는 리스트 선언 원할 때 list() 혹은 [] 사용 가능
- 리스트 원소 접근 시 [] 안에 숫자 입력

```python
# 직접 데이터를 넣어 초기화
a = [1,2,3,4,5,6]
print(a)

# 네 번째 원소만 출력
print(a[3])

# 뒤에서 두번 째 원소 출력
print(a[-2])

# 크기가 N이고, 모든 값이 0인 1차원 리스트 초기화
n = 10
a = [0] * n
print(a)

# 5번째 원소 값 변경
a[4] = 4
print(a)
```

> [1, 2, 3, 4, 5, 6]
> 4
> 5
> [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
> [0, 0, 0, 0, 4, 0, 0, 0, 0, 0]



- 리스트에서 **연속적인 위치를 갖는 원소를 가져와야 할 때**는 **슬라이싱(Slicing)**을 이용한다.

  - 대괄호 안에 콜론(:)을 넣어서 시작 인덱스와 끝 인덱스를 설정할 수 있다.
    - 끝 인덱스는 실제 인덱스보다 1 크게 설정한다.

  ```python
  a = [1, 2, 3, 4, 5, 6, 7, 8, 9]
  
  # 두 번째 원소부터 네 번째 원소까지
  print(a[1 : 4])
  
  # 처음부터 끝까지 -1칸 간격으로 == 역순으로
  print(a[::-1])
  ```

  > [2, 3, 4]
  > [9, 8, 7, 6, 5, 4, 3, 2, 1]

  

- 리스트 컴프리헨션 : 리스트를 초기화하는 방법 중 하나.

  - 대괄호 안에 조건문과 반복문을 적용하여 리스트를 초기화 할 수 있다.

  - 2차원 리스트를 초기화 할 때 효과적으로 사용할 수 있다.

    - 예시 : array = [[0] * m for _ in range(n)]

  - 반복을 수행하되 반복을 위한 변수의 값을 무시하고자 할 때 **언더바(_)**를 자주 사용한다.

    - 예시: "Hello World" 를 5번 출력하기

      ```python
      for _ in range(5):
          print("Hello World")
      ```

      

  ```python
  # 0부터 9까지의 수를 포함하는 리스트
  array = [i for i in range(10)]
  print(array)
  
  # 0부터 19까지의 수 중에서 홀수만 포함하는 리스트
  array = [i for i in range(20) if i % 2 == 1]
  print(array)
  
  # 1부터 9까지의 수들의 제곱 값을 포함하는 리스트
  array = [i*i for i in range(1, 10)]
  print(array)
  
  # N*M 크기의 2차원 리스트 초기화
  n = 4
  m = 3
  array = [[0] * m for _ in range(n)]
  print(array)
  ```

  > [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
  > [1, 3, 5, 7, 9, 11, 13, 15, 17, 19]
  > [1, 4, 9, 16, 25, 36, 49, 64, 81]
  > [[0, 0, 0], [0, 0, 0], [0, 0, 0], [0, 0, 0]]

- 리스트 관련 기타 메소드

![image-20210113102824337](https://user-images.githubusercontent.com/54929552/104815823-5ae21900-585a-11eb-964f-c831ec108d81.png)

```python
a = [1,4,3]
print("기본 리스트: ",a)

# 리스트에 원소 삽입
a.append(2)
print("삽입: ",a)

# 오름차순 정렬
a.sort()
print("오름차순 정렬: ",a)

# 내림차순 정렬
a.sort(reverse = True)
print("내림차순 정렬: ",a)

a = [4,3,2,1]
print("기본 리스트: ",a)

# 리스트 원소 뒤집기
a.reverse()
print("원소 뒤집기: ",a)

# 특정 인덱스에 데이터 추가
a.insert(2,3)
print("인덱스 2에 3추가: ",a)

# 특정 값인 데이터 개수 세기
print("값이 3인 데이터 개수: ",a.count(3))

# 특정 값 데이터 삭제
a.remove(1)
print("값이 1인 데이터 삭제: ",a)
```

> 기본 리스트:  [1, 4, 3]
> 삽입:  [1, 4, 3, 2]
> 오름차순 정렬:  [1, 2, 3, 4]
> 내림차순 정렬:  [4, 3, 2, 1]
> 기본 리스트:  [4, 3, 2, 1]
> 원소 뒤집기:  [1, 2, 3, 4]
> 인덱스 2에 3추가:  [1, 2, 3, 3, 4]
> 값이 3인 데이터 개수:  2
> 값이 1인 데이터 삭제:  [2, 3, 3, 4]

- 리스트에서 특정 값을 가지는 원소를 모두 제거하기(:= removeAll)

  ```python
  # 리스트에서 특정 값을 가지는 원소를 모두 제거하기(:= removeAll)
  a = [1,2,3,4,5,5,5]
  remove_set = {3,5} # 집합 자료형
  result = [i for i in a if i not in remove_set]
  print("특정값 원소 모두 제거: ",result)
  ```

  > 특정값 원소 모두 제거:  [1, 2, 4]





## 문자열(Str)

- 문자열 변수 초기화 시 큰 따옴표("), 작은 따옴표('), 모두 가능

- 전체 문자열을 큰따옴표/작은따옴표로 구성 시, 반대로 각각 작은따옴표/큰따옴표를 사용할 수 있다.
- 같은 문자 사용 원할 때는 이스케이프문자 형태로 작성한다.
- 덧셈을 통해 연결, 양의정수와의 곱셈을 통해 반복이 가능하다.
- 문자열에 대해서도 인덱싱과 슬라이싱을 이용할 수 있다.
  - 특정 인덱스의 값을 **변경할 수는 없다.** (Immutable)

```python
a = "Hello"
b = "World"
print(a + " " + b)

a = "String"
print(a*3)

a = "ABCDEF"
print(a[2:4])
```

> Hello World
> StringStringString
> CD





## 튜플

- 리스트와 유사하지만 다음과 같은 차이가 있다.
  - **한번 선언된 값을 변경할 수 없다.**
  - 소괄호 () 를 사용한다. (리스트는 대괄호 [])
  - 리스트에 비해 상대적으로 공간 효율적이다.

```python
a = (1,2,3,4,5,6,7,8,9)

# 네 번째 원소만 출력
print(a[3])

# 두 번째 원소부터 네 번째 원소까지
print(a[1 : 4])

a = (1, 2, 3, 4)
print(a)
```

> 4
> (2, 3, 4)
> (1, 2, 3, 4)

- 튜플을 사용하면 좋은 경우
  - 서로 다른 성질의 데이터를 묶어서 관리해야할 때
    - **최단 경로 알고리즘**에서는 **(비용, 노드 번호)**의  형태로 튜플 자료형을 자주 사용한다.
  - 데이터 나열을 **해싱(Hashing)**의 키 값으로 사용해야할 때
    - 튜플은 변경이 불가능하므로 리스트와 다르게 키 값으로 사용할 수 잇다.
  - 리스트보다 **메모리를 효율적**으로 사용해야 할 때





## 사전(Dictionary)

- 키(Key)와 값(Value)의 쌍을 데이터로 가진다.
- 순서 X, 키 값 중복 X
- 키 값의 경우 '변경 불가능한(Immutable) 자료형'을 키로 사용할 수 있다.
- **해시 테이블(Hash Table)**을 이용하므로 데이터의 조회 및 수정에 있어서 **O(1)**의 시간에 처리할 수 있다.

```python
data = dict()
data['사과'] = 'Apple'
data['바나나'] = 'Banana'
data['코코넛'] = 'Coconut'

print(data)

if '사과' in data:
  print("'사과'를 키로 가지는 데이터가 존재합니다.")

# 키 데이터만 담은 리스트
key_list = data.keys();
# 값 데이터만 담은 리스트
value_list = data.values();
print(key_list)
print(value_list)

# 각 키에 따른 값을 하나씩 출력
for key in key_list:
  print(data[key])

b = {
  '홍길동': 97,
  '이순신': 94
}

print(b)

# 키를 리스트 형태로 변환
key_list = list(b.keys())
print(key_list)
```

> {'사과': 'Apple', '바나나': 'Banana', '코코넛': 'Coconut'}
> '사과'를 키로 가지는 데이터가 존재합니다.
> dict_keys(['사과', '바나나', '코코넛'])
> dict_values(['Apple', 'Banana', 'Coconut'])
> Apple
> Banana
> Coconut
> {'홍길동': 97, '이순신': 94}
> ['홍길동', '이순신']



## 집합(Set)

- 중복X, 순서X
- 리스트 혹은 문자열을 이용해서 초기화할 수 있다.
  - 이때 **set() 함수**를 이용한다.
- 혹은 **중괄호 {}** 안에 각 원소를 콤마(,)를 기준으로 구분하여 삽입합으로써 초기화 할 수 있다.
- **데이터의 조회 및 수정에 있어서 O(1)의 시간에 처리**할 수 있다.

```python
# 집합 자료형 초기화 방법 1 - 리스트 이용, 중복 원소 자동 제거
data = set([1, 1, 2, 3, 4, 4, 5])
print(data)

# 집합 자료형 초기화 방법 2
data = {1, 1, 2, 3, 4, 4, 5}
print(data)
```

> {1, 2, 3, 4, 5}
> {1, 2, 3, 4, 5}



- 기본적인 집합 연산 제공

  - 합집합: |
  - 교집합:  &
  - 차집합: -

  ```python
  a = set([1,2,3,4,5])
  b = set([3,4,5,6,7])
  
  # 합집합
  print(a | b)
  
  # 교집합
  print(a & b)
  
  # 차집합
  print(a - b)
  ```

> {1, 2, 3, 4, 5, 6, 7}
> {3, 4, 5}
> {1, 2}

- 집합 자료형 관련 함수

  ```python
  data = set([1, 2, 3])
  print(data)
  
  # 새로운 원소 추가
  data.add(4)
  print(data)
  
  # 새로운 원소 여러 개 추가
  data.add([5, 6])
  print(data)
  
  # 특정한 값을 갖는 원소 삭제
  data.remove(3)
  print(data)
  ```

  

### 사전 자료형과 집합 자료형의 특징

- 리스트나 튜플은 순서가 있기 때문에 인덱싱을 통해 자료형의 값을 얻을 수 있다.
- 사전 자료형과 집합 자료형은 순서가 없기 때문에 인덱싱으로 값을 얻을 수 없다.
  - 사전의 키(Key) 혹은 집합의 원소를 통해 **O(1)**의 시간 복잡도로 조회한다.
    - 키의 값으로는 변경불가능한 자료형인 문자열이나, 튜플과 같은 객체를 사용해야 한다.  





## 입출력

- 자주 사용되는 표준 입력 방법
  - `input()` : 한 줄의 문자열을 입력 받는 함수
  - `map()` : 리스트의 모든 원소에 각각 특정한 함수를 적용할 때 사용
  - ex) 공백을 기준으로 구분된 데이터를 입력 받을 때
    - `list(map(int, input().split()))`
  - ex) 공백을 기준으로 구분된 데이터의 개수가 정해져 있을 때
    - `a, b, c = map(int, input().split())`

```python
# 데이터의 개수 입력, 문자열을 int 형으로 변환
n = int(input())

# 각 데이터를 공백을 기준으로 구분하여 입력
data = input()

# 문자열 리스트
data1 = data.split()
print(data1)

# 정수형 리스트
data2 = list(map(int, data.split()))
print(data2)

data2.sort(reverse=True)
print(data2)
```

> 입력
>
> 5
> 11 22 33 44 55

> 출력
>
> ['11', '22', '33', '44', '55']
> [11, 22, 33, 44, 55]
> [55, 44, 33, 22, 11]



```python
a, b, c = map(int, input().split())
```



- 사용자로부터 입력을 최대한 빠르게 받아야 하는 경우

  - sys 라이브러리에 정의 되어 있는 `sys.stdin.readline()` 사용
    - 입력 후 엔터(Enter)가 '\n'으로 입력되므로 `rstrip()` 메소드 함께 사용
    - 이진탐색, 그래프, 트리 등의 문제에서 자주 사용된다.

  ```python
  import sys
  
  # 문자열 입력받기
  data = sys.stdin.readline().rstrip()
  ```



- 자주 사용되는 표준 출력 방법
  - print() : 기본 출력
    - 변수를 콤마(,)를 이용하여 여러 개 출력 가능
    - 기본적으로 줄 바꿈 수행
    - 줄 바꿈 원치 않을 경우 'end' 속성 이용 가능

```python
# 출력할 변수들
a = 1
b = 2
print(a, b)
print(7, end=" ")
print(8, end=" ")

# 파이썬은 문자열과 정수형 연산 시 자동형변환을 하지 않는다. 
answer = 7
print("정답은" + str(answer) + "입니다.")
```



- f-string : 강제 형변환 필요 X

  - 파이썬 3.6부터 사용 가능, 문자열 앞에 접두사 'f'를 붙여 사용
  - 중괄호 안에 변수명을 기입하여 간단히 문자열과 정수를 함께 넣을 수 있다.

  ```python
  answer = 7
  print(f"정답은 {answer}입니다.")
  ```

  



## 조건문

- **if ~ elif ~ else**
- 들여쓰기: 파이썬에서는 블록을 들여쓰기로 구분한다. 파이썬 스타일 가이드라인에서는 4개의 공백 문자를 사용하는 것을 표준으로 설정한다.



### 논리연산자

- and 
- or 
- not



### 기타 연산자

- 리스트, 튜플, 문자열, 딕셔너리 모두에서 사용가능하다.

- x **in** 리스트 : 리스트 안에 x가 들어가 있을 때 True

- x **not in** 문자열 : 문자열 안에 x가 들어가 있지 않을 때 True



### pass 키워드

- 아무것도 처리하고 싶지 않을 때 pass 키워드 사용
- ex) 디버깅 과정에서 일단 조건문 형태만 만들어 놓고 처리하는 부분은 비워놓고 싶은 경우

```python
a = 5
b = 7

if a > b:
  pass
else:
  print("a <= b")

if a < 10 and a > 0:
  print("Yes")
```



### 조건문 간소화, 수학 부등식

```python
score = 85

# 조건문 간소화
if score >= 80: result = "success"
else: result = "fail"

# 조건문 표현식
result = "success" if score >= 80 else "fail"

# 파이썬에서는 수학 부등식 그대로 사용 가능
x = 15
if 0 < x < 20:
  print("true")
```





## 반복문

파이썬에서는 while 문, for 문이 있다. 

>  코테에서는 for 문이 간결한 경우가 더 많다.



### while 문

```python
i = 1
result = 0

while i <= 9:
    if i % 2 == 1:
        result += 1
    i += 1
print(result)
```



### for 문

- 특정한 변수를 이용하여 'in' 뒤에 오는 데이터(리스트, 튜플 등)에 포함되어 있는 원소를 첫 번째 인덱스부터 차례대로 하나씩 방문한다.

```python
# range, continue 사용
scores = [90, 85, 77, 65, 97]
cheating_student_list = {2,4}

for i in range(5):
  if i + 1 in cheating_student_list:
    continue
  if scores[i] >= 80:
    print(i+1,"번 학생: 합격")

# 구구단
for i in range(2,10):
  for j in range(1,10):
    print(i,"X",j,"=",i*j)
  print()
```





### 함수

- 함수 정의하기

```python
def 함수명(매개변수):
    실행할 소스코드
    return 변환 값
```

```python
def add(a,b):
    return a * b

print(add(3, 7))

# 호출 시 파라미터 직접 지정 가능
print(add(b=3,a=7))
```



### global 키워드

- 함수 바깥에 선언된 변수를 참조하고, 지역변수로 생성하지 않기 위해 사용

```python
array = [1,2,3,4,5]

def func():
  global array
  array.append(6)

func()
print(array)
```



- 함수 외부의 변수 값을 수정하지 않을 때는 오류없이 동작한다.

```python
a = 10

def func():
    print(a)
```



### 여러 개의 반환 값

- 패킹 : 여러 개의 값을 반환하는 것
- 언패킹 : 반환 된 값을 변수에 담는 것

```python
def operator(a,b):
  add_var = a + b
  subtract_var = a - b
  multiply_var = a * b
  divide_var = a / b
  return add_var, subtract_var, multiply_var, divide_var

print(operator(7,3))
a, b, c, d = operator(7,3)
print(a,b,c,d)
```

> (10, 4, 21, 2.3333333333333335)
> 10 4 21 2.3333333333333335





### 람다 표현식

```python
print((lambda a, b: a + b)(3,7))
```



- 내장 함수에서 자주 사용된다.

  - value 기준으로 리스트를 정렬하고자 하는 경우

  ````python
  array = [('A',89),('B',95),('C',78)]
  
  def my_key(x):
    return x[1]
  
  print(sorted(array, key=my_key))
  
  # 람다표현식
  print(sorted(array, key=lambda x: x[1])) 
  ````

  > [('C', 78), ('A', 89), ('B', 95)]
  > [('C', 78), ('A', 89), ('B', 95)]

  - 여러 개의 리스트에 적용하고자 하는 경우

  ```python
  list1 = [1, 2, 3, 4, 5]
  list2 = [6, 7, 8, 9, 10]
  
  result = map(lambda a, b: a+b, list1, list2)
  print(list(result))
  ```

  > [7, 9, 11, 13, 15]





# 실전에서 유용한 표준 라이브러리

![image-20210114113544729](C:\Users\82103\AppData\Roaming\Typora\typora-user-images\image-20210114113544729.png)



- sum()
- min()
- max()
- eval()
- sorted() ( with key )



## 순열과 조합

- 모든 경우의 수를 고려해야할 때
- 순열: 서로 다른 n개에서 서로 다른 r개를 선택하여 일렬로 나열하는 것
- 조합: 서로 다른 n개에서 순서에 상관 없이 서로 다른 r개를 선택하는 것

![image-20210114113803439](C:\Users\82103\AppData\Roaming\Typora\typora-user-images\image-20210114113803439.png)

- 모든 경우의 수를 점검할 때 순열, 조합 수를 계산하여 판단

```python
from itertools import permutations, combinations, product, combinations_with_replacement

data = ['A', 'B', 'C']

# 2개를 뽑아 나열하는 순열
print(list(permutations(data, 2)))

# 2개를 뽑는 모든 조합
print(list(combinations(data,2)))

# 중복 순열
print(list(product(data,repeat=2)))

# 중복 조합
print(list(combinations_with_replacement(data,2)))
```

> [('A', 'B'), ('A', 'C'), ('B', 'A'), ('B', 'C'), ('C', 'A'), ('C', 'B')]
> [('A', 'B'), ('A', 'C'), ('B', 'C')]
> [('A', 'A'), ('A', 'B'), ('A', 'C'), ('B', 'A'), ('B', 'B'), ('B', 'C'), ('C', 'A'), ('C', 'B'), ('C', 'C')]
> [('A', 'A'), ('A', 'B'), ('A', 'C'), ('B', 'B'), ('B', 'C'), ('C', 'C')]



## Counter

- collections 모듈의 Counter 클래스는 파이썬의 기본 자료구조인 사전(dictionary) 를 확장한 형태이다. 
  - 사전에서 제공하는 API 를 모두 사용할 수 있다.

- 리스트와 같은 반복 가능한(iterable) 객체에 대하여 사용가능 하다.

``` python
from collections import Counter

# Counter 객체 생성
counter = Counter(['red', 'blue', 'red', 'green', 'blue', 'blue'])

print(counter['blue'])
print(counter['green'])
print(dict(counter))
```





## 최대 공약수와 최소 공배수

- 최대 공약수 : math 라이브러리의 gcd() 함수 이용
- 최소 공배수 : 두 수를 곱한 값을 최대공약수로 나눈다.

```python
import math

# 최소 공배수(LCM)를 구하는 함수
def lcm(a, b):
  return a * b // math.gcd(a,b) # 나누기 연산 후 정수형만 취함

a = 21
b = 14

# 최대 공약수(GCD) 계산
print(math.gcd(21, 14))

# 최소 공배수(LCM) 계산
print(lcm(21,14))
```



> 본 내용은 *이것이 취업을 위한 코딩 테스트다 with 파이썬 - 나동빈* 책을 통하여 학습한 후 정리하였습니다.