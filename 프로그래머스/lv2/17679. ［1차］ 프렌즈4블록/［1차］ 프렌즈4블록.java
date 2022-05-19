import java.util.ArrayList;
import java.util.List;

public class Solution {

    char[][] boardCopy;
    List<int[]> removeList = new ArrayList<>();// 삭제될 x,y 좌표

    // 판의 높이 m, 폭 n, 2 ≦ n, m ≦ 30, 블록의 첫 배치 대문자 A에서 Z
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        boardCopy = new char[m][n];
        for (int i = 0; i < m; i++) {
            boardCopy[i] = board[i].toCharArray();
        }
        // dfs
        while (true) {
            findRemoveBlocks(m, n);
            if (removeList.size() == 0) { // 지워지는 블록 개수가 0이면 끝
                break;
            }
            removeBlocks();
            answer += removeList.size();
            removeList.clear();
            dropBlock(m, n);
        }
        return answer;
    }

    void findRemoveBlocks(int m, int n) {
        boolean[][] removes = new boolean[m][n];
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                char cur = boardCopy[i][j];
                if (cur == '0') { // 빈 블록
                    continue;
                }
                // 우, 우하, 하 확인
                if (cur == boardCopy[i][j + 1] && cur == boardCopy[i + 1][j + 1] && cur == boardCopy[i + 1][j]) {
                    removes[i][j] = true;
                    removes[i][j + 1] = true;
                    removes[i + 1][j] = true;
                    removes[i + 1][j + 1] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (removes[i][j]) {
                    removeList.add(new int[]{i, j});
                }
            }
        }
    }


    // 블록 제거
    void removeBlocks() {
        for (int[] xy : removeList) {
            boardCopy[xy[0]][xy[1]] = '0';
        }
    }

    // 블록 아래로 떨어뜨리기
    void dropBlock(int m, int n) {
        for (int x = m - 1; x > 0; x--) {
            for (int y = 0; y < n; y++) {
                if (boardCopy[x][y] == '0') {
                    for (int z = x - 1; z >= 0; z--) { // 빈 칸일 경우 가장 가까운 프렌즈가 떨어진다.
                        if (boardCopy[z][y] != '0') {
                            boardCopy[x][y] = boardCopy[z][y];
                            boardCopy[z][y] = '0';
                            break;
                        }
                    }
                }
            }
        }
    }
    
}