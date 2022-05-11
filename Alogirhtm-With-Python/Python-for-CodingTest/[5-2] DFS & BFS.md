## DFS (Depth-First Search)

- 깊이 우선 탐색: 그래프에서 깊은 부분을 우선적으로 탐색하는 알고리즘
- **스택** 자료구조(or 재귀 함수)를 이용
  1. 탐색 시작 노드를 스택에 삽입하고 방문 처리를 한다.
  2. 스택의 최상단 노드에 방문하지 않은 인접한 노드가 하나라도 있으면 그 노드를 스택에 넣고 방문 처리한다. 방문하지 않는 인접 노드가 없으면 스택에서 최상단 노드를 꺼낸다.
  3. 더 이상 2번의 과정을 수행할 수 없을 때까지 반복한다.

![image-20210114145730016](C:\Users\82103\AppData\Roaming\Typora\typora-user-images\image-20210114145730016.png)



```python
# DFS 메서드 정의
def dfs(graph, v, visited):
  # 현재 노드를 방문 처리
  visited[v] = True
  print(v, end=' ')
  # 현재 노드와 연결된 다른 노드를 재귀적으로 방문
  for i in graph[v]:
    if not visited[i]:
      dfs(graph, i, visited)

# 각 노드가 연결된 정보를 표현 (2차원 리스트)
graph =[
  [],
  [2,3,8],
  [1,7],
  [1,4,5],
  [3,5],
  [3,4],
  [7],
  [2,6,8],
  [1,7]
]

# 각 노드가 방문된 정보를 표현 (1차원 리스트)
visited = [False] * 9

# 정의된 DFS 함수 호출
dfs(graph, 1, visited)
```

> 1 2 7 6 8 3 4 5



### BFS(Bread-First Search)

- 너비 우선 탐색: 그래프에서 가까운 노드부터 우선적으로 탐색하는 알고리즘
- **큐** 자료구조 사용
  1. 탐색 시작 노드를 큐에 삽입하고 방문 처리 한다.
  2. 큐에서 노드를 꺼낸 뒤에 해당 노드의 인접 노드 중에서 방문하지 않은 노드를 모두 큐에 삽입하고 방문 처리한다.
  3. 더 이상 2번의 과정을 수행할 수 없을 때까지 반복한다.
- 특정 조건에서의 최단 경로 알고리즘을 구할 때 자주 사용된다.

![image-20210114155417581](C:\Users\82103\AppData\Roaming\Typora\typora-user-images\image-20210114155417581.png)

> - 시작노드 1
> - 시작노드로부터 길이가 1인 노드 먼저 방문 [ 2, 3, 8 ]
> - 시작노드로부터 길이가 2인 노드 방문 [ 7, 4, 5 ]
> - 시작노드로부터 길이가 3인 노드 방문 [ 6 ]

```python
from collections import deque

# BFS 함수 정의
def bfs(graph, start, visited):
  # 큐(Queue) 구현을 위해 deque 라이브러리 사용
  queue = deque([start])
  # 현재 노드를 방문 처리
  visited[start] = True
  # 큐가 빌 때까지 반복
  while queue:
    # 큐에서 하나의 원소를 뽑아 출력
    v = queue.popleft()
    print(v, end=' ')
    # 해당 원소와 연결된, 아직 방문하지 않은 원소들을 큐에 삽입
    for i in graph[v]:
      if not visited[i]:
          queue.append(i)
          visited[i] = True

# 각 노드가 연결된 정보를 리스트 자료형으로 표현(2차원 리스트)
graph = [
  [],
  [2, 3, 8],
  [1, 7],
  [1, 4, 5],
  [3, 5],
  [3, 4],
  [7],
  [2, 6, 8],
  [1, 7]
]

# 각 노드가 방문된 정보를 리스트 자료형으로 표현(1차원 리스트)
visited = [False] * 9

# 정의된 BFS 함수 호출
bfs(graph, 1, visited)
```

> 1 2 3 8 7 4 5 6



> 본 내용은 *이것이 취업을 위한 코딩 테스트다 with 파이썬 - 나동빈* 책을 통하여 학습한 후 정리하였습니다.