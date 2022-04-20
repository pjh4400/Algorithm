import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

  static int n; //(4 ≤ N ≤ 20, N은 짝수)
  static int[][] s;
  static boolean[] team; // true 면 start 팀
  static int minDifference; // 최소 차이

  // 팀나누기 (n 개중 n/2 뽑기 : 조합 -> 능력치 계산만 하면 되므로 팀 구분할필요 X )
  public static void split(int depth, int v) {
    if (depth == n / 2) {
      calculate();
    }
    for (int i = v+1; i < n; i++) {
      if (!team[i]) { // 팀 배정 안됐으면
        team[i] = true;
        split(depth + 1, i);
        team[i] = false;
      }
    }
  }

  // 능력치 계산
  public static void calculate() {
    int start = 0, link = 0;
    for (int i = 0; i < n-1; i++) {
      for (int j = i+1; j < n; j++) {
        if (team[i] && team[j]) { // 둘다 start 팀이면
          start += s[i][j] + s[j][i];
        } else if (!team[i] && !team[j]) { // 둘다 link 팀이면
          link += s[i][j] + s[j][i];
        }
      }
    }
    minDifference = Math.min(Math.abs(start - link), minDifference);
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    n = Integer.parseInt(br.readLine());
    s = new int[n][n];
    team = new boolean[n];
    minDifference = 100 * n * n;
    StringTokenizer st;
    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        s[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    split(0,0);
    bw.write(String.valueOf(minDifference));
    bw.flush();
    bw.close();
  }
}