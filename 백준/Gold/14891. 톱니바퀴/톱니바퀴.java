import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] wheels = new int[4][8]; // 바퀴 4개 각각 톱니 8개
    static int k;
    static int[] start = new int[4]; // 12시를 가리키는 인덱스 (바퀴별로)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 톱니 8개
        for (int i = 0; i < 4; i++) {
            String line = br.readLine();
            for (int j = 0; j < 8; j++) {
                wheels[i][j] = line.charAt(j) - '0';
            }
        }
        k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            if (d == -1) {
                d = 7;
            }
            // 시계 : d = +1 , 반시계 : d = +7
            if (w > 0 && wheels[w][(start[w] + 6) % 8] != wheels[w - 1][(start[w - 1] + 2) % 8]) {
                leftSpin(w - 1, 8 - d);
            }
            if (w < 3 && wheels[w][(start[w] + 2) % 8] != wheels[w + 1][(start[w + 1] + 6) % 8]) {
                rightSpin(w + 1, 8 - d);
            }
            start[w] = (start[w] + 8 - d) % 8; // 0번인덱스 위치 : 시계 = +7, 반시계 = +1
        }
        System.out.println(getScore());
    }

    static void leftSpin(int w, int d) {
        if (w > 0 && wheels[w][(start[w] + 6) % 8] != wheels[w - 1][(start[w - 1] + 2) % 8]) {
            leftSpin(w - 1, 8 - d); // 왼쪽 바퀴를 반대로 회전
        }
        start[w] = (start[w] + 8 - d) % 8; // 회전 (돌리는 방향과 반대로 인덱스 이동)
    }

    static void rightSpin(int w, int d) {
        if (w < 3 && wheels[w][(start[w] + 2) % 8] != wheels[w + 1][(start[w + 1] + 6) % 8]) {
            rightSpin(w + 1, 8 - d); // 오른쪽 바퀴를 반대로 회전
        }
        start[w] = (start[w] + 8 - d) % 8; // 회전 (돌리는 방향과 반대로 인덱스 이동)
    }

    static int getScore() {
        int score = 0;
        for (int i = 0; i < 4; i++) {
            if (wheels[i][start[i]] == 1) { // S극은 1
                score += Math.pow(2, i);
            }
        }
        return score;
    }
}
