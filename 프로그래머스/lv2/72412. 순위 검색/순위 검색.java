import java.util.*;

public class Solution {
    Map<String, List<Integer>> map = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        for (String oneInfo : info) {
            String[] oneInfoArr = oneInfo.split(" ");
            combination(0, new String(),oneInfoArr);
        }
        for (String key : map.keySet()) {
            Collections.sort(map.get(key)); // 이분 탐색 위한 정렬
        }
        for (int i = 0; i < query.length; i++) {
            String oneQuery = query[i];
            int scoreIndex = oneQuery.lastIndexOf(" ");
            String condition = oneQuery.substring(0, scoreIndex).replaceAll(" and ", "");
            int score = Integer.parseInt(oneQuery.substring(scoreIndex + 1));
            if (map.containsKey(condition)) {
                List<Integer> scores = map.get(condition);
                answer[i] = binarySearch(scores, score, 0, scores.size() - 1);
            }
        }
        return answer;
    }

    void combination(int depth, String key, String[] oneInfoArr){
        if (depth == 4) {
            if (map.containsKey(key.toString())) {
                map.get(key.toString()).add(Integer.parseInt(oneInfoArr[4]));
                return;
            }
            List<Integer> scores = new ArrayList<>();
            scores.add(Integer.parseInt(oneInfoArr[4]));
            map.put(key.toString(), scores);
            return;
        }
        combination(depth+1, key+"-", oneInfoArr);
        combination(depth+1, key+oneInfoArr[depth], oneInfoArr);
    }

    int binarySearch(List<Integer> scores, int key, int low, int high) {
        int mid;
        int result = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (scores.get(mid) < key) {
                low = mid + 1;
            } else { // key 중에 가장 첫번째 key 찾아야함!
                high = mid - 1;
                result = mid;
            }
        }
        if(result == -1){ // 없음
            return 0;
        }
        return scores.size() - result;
    }
}