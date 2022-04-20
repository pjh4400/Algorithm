import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int n, k;
  static boolean[] robots; // 로봇 큐
  static int[] belt; // 내구성 큐

  // 1. 벨트가 한 칸 회전한다. = 내구도&로봇 회전
  static void rotate() {
    // 내구도 회전
    int end = belt[n * 2 - 1];
    for (int i = n * 2 - 1; i > 0; i--) {
      belt[i] = belt[i - 1];
    }
    belt[0] = end;
    // 로봇 회전
    for (int i = n - 1; i > 0; i--) {
      robots[i] = robots[i - 1];
    }
    robots[0] = false;
    robots[n - 1] = false;
  }

  // 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
  static void move() {
    for (int i = n - 2; i >= 0; i--) {
      if (robots[i] && !robots[i + 1]
          && belt[i + 1] > 0) {// 현재 로봇이 있고, 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
        robots[i] = false; // 로봇 이동
        robots[i + 1] = true;
        belt[i + 1]--; // 내구도 감소
        if (belt[i + 1] == 0) {
          k--;
        }
      }
    }
    robots[n - 1] = false; // 즉시 내린다.
  }

  // 만약 내구성이 0보다 크면 로봇을 올리고, 내구성을 감소시킨다.
  static void getOn() {
    if (belt[0] > 0) {
      robots[0] = true;
      belt[0]--;
      if (belt[0] == 0) {
        k--;
      }
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());
    // 로봇과 내구도 회전은 별개이므로 따로 선언
    robots = new boolean[n]; // 로봇
    belt = new int[n * 2]; // 내구도
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n * 2; i++) {
      belt[i] = Integer.parseInt(st.nextToken());
    }
    int stage = 0;
    while (k > 0) {
      stage++;
      rotate();
      move();
      getOn();
    }
    System.out.println(stage);
  }

}