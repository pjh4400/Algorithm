import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

  static class Position {
    int x, y;

    Position(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  static int n, m;
  static int[][] board, virusBoard;
  static List<Position> virus = new ArrayList<>();
  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};
  static int maxSafe = 0;


  // 벽세우기: 백트래킹을 이용한 조합 구현 ( 0인 칸 중에 3개 뽑기 )
  static void buildWall(int depth) {
    if (depth == 3) { // 벽 3개 다 세웠으면 바이러스를 퍼뜨린다.
      copyBoard();
      spreadVirus();
      countSafe();
      return;
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (board[i][j] == 0) { // 빈 칸이면 벽 세우기
          board[i][j] = 1;
          buildWall(depth + 1);
          board[i][j] = 0; // 돌려 놓기
        }
      }
    }
  }

  // 바이러스 퍼뜨릴 때 연구소 원본을 보호하기 위한 Deep Copy
  static void copyBoard(){
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        virusBoard[i][j] = board[i][j];
      }
    }
  }

  // 바이러스 퍼뜨리기: bfs 로 2에 인접한 0들을 2로 바꿔나간다.
  static void spreadVirus() {
    Queue<Position> virusQ = new LinkedList<>(virus);
    int nx, ny;
    while (!virusQ.isEmpty()) {
      Position cur = virusQ.poll();
      for (int i = 0; i < 4; i++) {
        nx = cur.x + dx[i];
        ny = cur.y + dy[i];
        if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
          continue;
        }
        if (virusBoard[nx][ny] == 0) { // 빈 칸이면 바이러스 퍼뜨리기
          virusBoard[nx][ny] = 2;
          virusQ.offer(new Position(nx, ny));
        }
      }
    }
  }

  static void countSafe() {
    int cnt = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (virusBoard[i][j] == 0) {
          cnt++;
        }
      }
    }
    maxSafe = Math.max(maxSafe, cnt);
  }


  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    board = new int[n][m];
    virusBoard = new int[n][m];

    // 입력
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < m; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
        if (board[i][j] == 2) {
          virus.add(new Position(i, j));
        }
      }
    }
    buildWall(0);
    bw.write(String.valueOf(maxSafe));
    bw.flush();
    bw.close();
  }
}