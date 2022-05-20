import java.util.*;

public class Solution {

    Queue<String> cache = new LinkedList<>();  // (Least Recently Used)


    /**
     * @param cacheSize // 0 ≦ cacheSize ≦ 30
     * @param cities    // 최대 100,000개, 대소문자 구분을 하지 않는다.
     * @return 총 실행 시간
     */
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        for (String c : cities) {
            String city = c.toLowerCase();
            if (cache.remove(city)) {
                cache.offer(city);
                answer++;
                continue;
            }
            // cache miss
            cache.add(city);
            if (cache.size() > cacheSize) {
                cache.poll();
            }
            answer += 5;
        }
        // cities
        return answer; // 총 실행 시간
    }

}