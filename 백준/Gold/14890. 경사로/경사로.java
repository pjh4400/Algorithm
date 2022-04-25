import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, l;
    static int[][] map;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] heights = new int[n];
        int wayCnt = 0;
        for (int i = 0; i < n; i++) {
            if (explore(map[i])) {
                wayCnt++; // 행
            }
            for (int j = 0; j < n; j++) {
                heights[j] = map[j][i];
            }
            if (explore(heights)) {
                wayCnt++; //열
            }
        }
        System.out.println(wayCnt);
    }

    // 각 행, 열 에 대하여 길인지 탐색
    static boolean explore(int[] heights) {
        boolean[] isRunway = new boolean[n];
        boolean isRoad = true;
        for (int i = 1; i < n; i++) {
            if (heights[i - 1] == heights[i]) { // 높이가 같으면 패스
                continue;
            } else if (heights[i] > heights[i - 1] && setRunwayOnPre(heights, isRunway, i - 1,
                heights[i])) { // 높이가 커졌으면 이전에 경사로를 세울 수 있는지 탐색한다.
                continue;
            } else if (heights[i] < heights[i - 1] && setRunwayOnAfter(heights, isRunway, i,
                heights[i - 1])) { // 높이가 작아졌으면 현재부터 이후에 경사로를 세울 수 있는지 탐색한다.
                continue;
            }
            isRoad = false;
            break;
        }
        return isRoad;
    }


    // 높이가 높아졌을 때 현재부터 이전 계단에 경사로 세우기
    static boolean setRunwayOnPre(int[] heights, boolean[] isRunway, int i, int h) {
        boolean flag = true;
        for (int j = i; j > i - l; j--) {
            if (j < 0 || heights[j] != h - 1 || isRunway[j]) { // 높이가 h-1 이 아니거나, 이미 경사로를 세웠으면 불가
                flag = false;
                break;
            }
        }
        if (flag) { // 경사로 세우기
            for (int j = i; j > i - l; j--) {
                isRunway[j] = true;
            }
        }
        return flag;
    }

    // 높이가 낮아졌을 때 현재부터 이후 계단에 경사로 세우기
    static boolean setRunwayOnAfter(int[] heights, boolean[] isRunway, int i, int h) {
        boolean flag = true;
        for (int j = i; j < i + l; j++) {
            if (j > n - 1 || heights[j] != h - 1
                || isRunway[j]) { // 높이가 h-1 이 아니거나, 이미 경사로를 세웠으면 불가
                flag = false;
                break;
            }
        }
        if (flag) {
            for (int j = i; j < i + l; j++) {
                isRunway[j] = true;
            }
        }
        return flag;
    }

}
