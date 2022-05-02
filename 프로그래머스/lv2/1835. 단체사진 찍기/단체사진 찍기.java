import java.util.HashMap;
import java.util.Map;

class Solution {
    public Map<Character, Integer> friendsMap = new HashMap<>();
    public int[] orders = new int[8];
    public boolean[] visited = new boolean[8];
    public int count = 0;

    public int solution(int n, String[] data) {
        friendsMap.put('A', 0);
        friendsMap.put('C', 1);
        friendsMap.put('F', 2);
        friendsMap.put('J', 3);
        friendsMap.put('M', 4);
        friendsMap.put('N', 5);
        friendsMap.put('R', 6);
        friendsMap.put('T', 7);
        permutation(n, data, 0);
        return count;
    }

    public void permutation(int n, String[] data, int depth) {
        if (depth == 8) {
            if (checkConditions(data)) {
                count++;
            }
            return;
        }

        for (int i = 0; i < 8; i++) {
            if (!visited[i]) {
                visited[i] = true;
                orders[i] = depth; // 순서 저장
                permutation(n, data, depth + 1);
                visited[i] = false;
            }
        }
    }

    public boolean checkConditions(String[] data) {
        for (String condition : data) {
            int a = orders[friendsMap.get(condition.charAt(0))];
            int b = orders[friendsMap.get(condition.charAt(2))];
            int c = condition.charAt(4) - '0' + 1;
            switch (condition.charAt(3)) {
                case '=':
                    if (Math.abs(a - b) != c) {
                        return false;
                    }
                    break;
                case '<':
                    if (Math.abs(a - b) >= c) {
                        return false;
                    }
                    break;
                case '>':
                    if (Math.abs(a - b) <= c) {
                        return false;
                    }
                    break;
            }
        }
        return true;
    }
}