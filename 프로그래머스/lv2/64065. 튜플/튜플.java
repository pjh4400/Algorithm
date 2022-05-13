import java.util.HashSet;
import java.util.Set;

class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        Set<Integer>[] tuples = new Set[500];
        Set<Integer> currentTuple = new HashSet<>();
        int maxCount = 0, count = 0; // 원소 수
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < s.length() - 1; i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                sb.append(ch);
            } else if (sb.length() > 0 && ch == ',' || ch == '}') {
                currentTuple.add(Integer.parseInt(sb.toString()));
                sb.setLength(0);
                if (ch == '}') {
                    count = currentTuple.size();
                    maxCount = Math.max(count, maxCount);
                    tuples[count - 1] = currentTuple;
                    currentTuple = new HashSet<>();
                }
            }
        }
        answer = new int[maxCount];
        Set<Integer> answerSet = new HashSet<>();
        for (int i = 0; i < maxCount; i++) {
            if (tuples[i].isEmpty()) {
                continue;
            }
            tuples[i].removeAll(answerSet);
            answer[i] = tuples[i].iterator().next();
            answerSet.add(answer[i]);
        }
        return answer;
    }
}