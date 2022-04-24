import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m, r, c, d; //(3 ≤ N, M ≤ 50)
    static int[][] map;
    // 북 동 남 서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int cnt = 0;

    // 로봇 청소기 작동
    public static void clean() {
        int x = r, y = c;
        int nx = x, ny = y, nd = d;
        boolean b;
        while (true) {
            b = true;
            if (map[x][y] == 0) {
                map[x][y] = 2;
                cnt++;
            }

            // 2. 현재 위치의 왼쪽을 돌면서 살핀다.
            for (int i = 0; i < 4; i++) {
                nd = (d + 3) % 4;
                nx = x + dx[nd];
                ny = y + dy[nd];
                // 2a. 왼쪽이 청소하지 않은 빈 공간이라면, 한 칸을 전진한 다음 1번으로 돌아간다.
                //nx > 0 && nx < n && ny > 0 && ny < m &&
                if (map[nx][ny] == 0) {
                    x = nx;
                    y = ny;
                    d = nd;
                    b = false;
                    break;
                }
                d = nd; //아니면 회전
            }

            // 2b. 회전(2a)만 4번 했을 경우
            if (b) {
                nd = (d + 2) % 4; // 뒤쪽
                nx = x + dx[nd];
                ny = y + dy[nd];
                // 바로 뒤쪽이 벽이라면 작동을 멈춘다
                if (map[nx][ny] == 1) {
                    return;
                }
                // 그렇지 않다면 한 칸 후진한다
                x = nx;
                y = ny;
            }
        }

    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        clean();
        System.out.println(cnt);
    }

}