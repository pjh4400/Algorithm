import java.util.Arrays;

public class Solution {
    int[] maxLionBoard = new int[11];
    int[] lion = new int[11];
    int maxGap = -1;
    int maxIndex = 11;
    
    // info: 어피치가 맞힌 과녁 점수의 개수 10점부터 0점까지 순서대로
    public int[] solution(int n, int[] info) {
        permutation(0, n, info);
        if (maxGap == -1) { // 라이언이 우승할 수 없는 경우(무조건 지거나 비기는 경우)는 [-1]
            return new int[]{-1};
        }
        return maxLionBoard;
    }

    // 라이언이 점수 따거나(어피치+1) 안따거나(0)
    void permutation(int depth, int n, int[] apeach) {
        if (n == 0 || depth == 11) { // 가장 큰 라이언 점수 계산
            lion[10] += n;
            checkLionWin(apeach);
            return;
        }

        for (int i = depth; i < 11; i++) { // 10점 ~ 0점
            if (n >= apeach[i] + 1) { // 화살이 충분해야 점수 딸 수 있음
                lion[i] = apeach[i] + 1;
            }
            permutation(i + 1, n - lion[i], apeach);
            lion[i] = 0;
        }
    }

    private void checkLionWin(int[] apeach) {
        int lionScore = 0, apeachScore = 0;
        for (int i = 0; i < 10; i++) {
            if (lion[i] > apeach[i]) {
                lionScore += 10 - i;
                continue;
            } 
            if (lion[i] == 0 && apeach[i] == 0){
                continue;
            }
            apeachScore += 10 - i; 
        }
        if (lionScore <= apeachScore) { // 지거나 비긴 경우
            return;
        }
        // 점수 차 계산
        int gap = lionScore - apeachScore;
        if (gap < maxGap) { // 최대 점수 차이보다 작은 경우
            return;
        }
        int lastIndex = getMaxIndex(lion);
        if (gap == maxGap && lastIndex <= maxIndex) { // 점수차가 같은데 낮은 점수(큰 인덱스)를 더 적게 맞힌 경우
            return;
        }
        // 점수차가 최대 경우
        maxGap = gap;
        maxIndex = lastIndex;
        maxLionBoard = lion.clone();
    }

    private int getMaxIndex(int[] lion) {
        for (int i = 9; i >= 0; i--) {
            if (lion[i] != 0) {
                return i;
            }
        }
        return 11;
    }


}