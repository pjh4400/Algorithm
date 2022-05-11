# [1260] DFS와 BFS

| 시간 제한 | 메모리 제한 | 제출   | 정답  | 맞은 사람 | 정답 비율 |
| :-------- | :---------- | :----- | :---- | :-------- | :-------- |
| 2 초      | 128 MB      | 115111 | 40061 | 23037     | 33.096%   |

## 문제

그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료한다. 정점 번호는 1번부터 N번까지이다.

## 입력

첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다. 다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 입력으로 주어지는 간선은 양방향이다.

## 출력

첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. V부터 방문된 점을 순서대로 출력하면 된다.



## 💡 구현 아이디어

기본적인 DFS & BFS 문제이다. 그래프를 표현할 때에는 인접행렬 또는 인접리스트를 이용할 수 있다.

##### ✅ 체크 포인트

1. 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 **작은 것**을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료한다.

   - 인접리스트의 경우 **sort** 필요!

2. 어떤 두 정점 사이에 **여러 개의 간선이 있을 수 있다.** 

   - 한번 입력된 연결관계가 또 입력될 수 있다. 

3. 입력으로 주어지는 간선은 **양방향**이다.

   - 인접행렬: graph\[i][j] = graph\[j][i] = True

   



## 🙆‍♀️ 내 풀이 (python)

1. 인접행렬 이용

```python
def dfs(v):
  visited[v] = True
  print(v, end=' ')
  for i in range(1,n+1):
    if graph[v][i] and not visited[i]:
      dfs(i)
    
from collections import deque
def bfs(start):
  queue = deque([start])
  visited[start] = True
  while queue:
    v = queue.popleft()
    print(v, end=' ')
    for i in range(1,n+1):
      if graph[v][i] and not visited[i]:
        queue.append(i)
        visited[i] = True
    
n,m,v = map(int,input().split())
graph = [[False]*(n+1) for _ in range(n+1) ]
for _ in range(m):
  x, y = map(int,input().split())
  if not graph[x][y]:
    graph[x][y] = True
  if not graph[y][x]:
    graph[y][x] = True

visited = [False] * (n+1)
dfs(v)
print()
visited = [False] * (n+1)
bfs(v)
```



2. 인접리스트 이용

```python
def dfs(v):
  visited[v] = True
  print(v, end=' ')
  for i in graph[v]:
    if not visited[i]:
      dfs(i)

from collections import deque
def bfs(start):
  queue = deque([start])
  visited[start] = True
  while queue:
    v = queue.popleft()
    print(v, end=' ')
    for i in graph[v]:
      if not visited[i]:
        queue.append(i)
        visited[i] = True
    
n,m,v = map(int,input().split())
graph = [[] for _ in range(n+1)]

for _ in range(m):
  x, y = map(int,input().split())
  if y not in graph[x]:
    graph[x].append(y)
  if x not in graph[y]:
    graph[y].append(x)

# 정점 번호가 작은 것 부터
for line in graph:
  line.sort()


visited = [False] * (n+1)
dfs(v)
print()
visited = [False] * (n+1)
bfs(v)

```

> 1. 인접 행렬 : 메모리 39451KB, 시간 636ms
> 2. 인접 리스트 : 메모리 33296KB, 시간 484ms



## 인접 행렬 / 인접 리스트 한번 더 정리

1. 인접 행렬 

   - 장점

     - 구현이 매우 간단하다.
     - 두 노드(i,j) 간의 연결 여부 확인을 위해서 graph\[i][j] 의 값만 확인하면 된다. = **O(1) **

   - 단점

     - edge 의 수와는 무관하게 **O(V^2)** 의 공간복잡도를 갖는다. 

       - 그래프에 존재하는 모든 edge 를 파악하기 위해서는 **O(V^2)** 의 시간 이 소요된다.

         > 그래프에 간선의 수가 많은 **밀집 그래프(Dense Graph)**의 경우 유리하다.

   

2. 인접 리스트

   - 장점

     - 인접 행렬과 달리 실제로 연결된 노드에 대한 정보만 저장하므로 **O(V+E)** 의 공간복잡도를 갖는다.

       - 그래프에 존재하는 모든 edge 를 파악하기 위해 **O(V+E)** 의 시간 이 소요된다.

         > 그래프에 간선의 수가 적은 **희소 그래프(Sparse Graph)**의 경우 유리하다.

   - 단점
     - 두 노드(i,j) 간의 연결 여부를 확인하고 싶을 경우, graph[i] 의 리스트를 순회하며 j 원소가 존재하는지 확인해야한다. = O(v)

