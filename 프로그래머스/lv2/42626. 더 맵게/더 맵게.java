import java.util.PriorityQueue;

class Solution {
    public static int solution(int[] scoville, int K) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int cnt = 0;
        for (int s : scoville) {
            queue.offer(s);
        }
        while (queue.size() > 1) {
            int x = queue.poll() + queue.poll() * 2;
            queue.offer(x);
            cnt++;

            if (queue.peek() >= K) {
                return cnt;
            }
        }
        return -1;
    }
}