class Solution {
    
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int currentSizeOfOneArea;
    static boolean[][] visited; // 방문 여부

    //리턴 타입은 원소가 두 개인 정수 배열이다. 그림에 몇 개의 영역이 있는지와 가장 큰 영역은 몇 칸으로 이루어져 있는지를 리턴한다.
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] != 0 && !visited[i][j]) { //0이 아니고 방문 안했으면 방문
                    numberOfArea++;
                    dfs(m, n, i, j, picture[i][j], picture);
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, currentSizeOfOneArea);
                    currentSizeOfOneArea = 0;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    public void dfs(int m, int n, int x, int y, int num, int[][] picture) {
        visited[x][y] = true; // 방문 처리
        currentSizeOfOneArea++; // 영역 수 증가
        int nx, ny;
        for (int i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];
            if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                continue;
            }

            // 다른 숫자면 리턴
            if (picture[nx][ny] == num && !visited[nx][ny]) { // 같은 숫자면 같은 영역, 방문안했으면 방문
                dfs(m, n, nx, ny, num, picture);
            }
        }
    }
}