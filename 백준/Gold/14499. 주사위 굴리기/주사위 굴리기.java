import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int[] dice = new int[6];
    static int n, m, x, y, k;
    static int[][] map;
    // 동, 서, 북, 남
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static void rollEast() {
        int[] copyDice = new int[6];
        copyDice[0] = dice[3];
        copyDice[1] = dice[1];
        copyDice[2] = dice[0];
        copyDice[3] = dice[5];
        copyDice[4] = dice[4];
        copyDice[5] = dice[2];
        dice = copyDice;
    }

    static void rollWest() {
        int[] copyDice = new int[6];
        copyDice[0] = dice[2];
        copyDice[1] = dice[1];
        copyDice[2] = dice[5];
        copyDice[3] = dice[0];
        copyDice[4] = dice[4];
        copyDice[5] = dice[3];
        dice = copyDice;
    }

    static void rollNorth() {
        int[] copyDice = new int[6];
        copyDice[0] = dice[4];
        copyDice[1] = dice[0];
        copyDice[2] = dice[2];
        copyDice[3] = dice[3];
        copyDice[4] = dice[5];
        copyDice[5] = dice[1];
        dice = copyDice;
    }

    static void rollSouth() {
        int[] copyDice = new int[6];
        copyDice[0] = dice[1];
        copyDice[1] = dice[5];
        copyDice[2] = dice[2];
        copyDice[3] = dice[3];
        copyDice[4] = dice[0];
        copyDice[5] = dice[4];
        dice = copyDice;
    }

    static void copy() {
        if (map[x][y] == 0) { // 이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다.
            map[x][y] = dice[5];
        } else { // 0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.
            dice[5] = map[x][y];
            map[x][y] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int d, nx, ny;
        for (int i = 0; i < k; i++) {
            d = Integer.parseInt(st.nextToken()) - 1; // 이동할 방향
            nx = x + dx[d];
            ny = y + dy[d];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {// 바깥으로 이동시키려고 하는 경우에는 해당 명령을 무시
                continue;
            }
            //동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4 인데 -1 해줬음
            switch (d) {
                case 0:
                    rollEast();
                    break;
                case 1:
                    rollWest();
                    break;
                case 2:
                    rollNorth();
                    break;
                case 3:
                    rollSouth();
                    break;
            }
            x = nx;
            y = ny;
            copy();
            bw.write(String.valueOf(dice[0]));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}