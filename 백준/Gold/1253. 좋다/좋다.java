import java.io.*;
import java.util.*;

public class Main {
    static int n, answer;
    static int[] numbers;
    static Map<Integer, Integer> numberCountMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        numbers = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            numberCountMap.put(numbers[i], numberCountMap.getOrDefault(numbers[i], 0) + 1);
        }
        for (int i = 0; i < n - 1; i++) {
            plus(i);
        }
        System.out.print(answer);
    }

    static void plus(int start) {
        for (int i = start + 1; i < n; i++) {
            int result = numbers[start] + numbers[i];
            if (numberCountMap.containsKey(result)) {
                int count = numberCountMap.get(result);
                if (numbers[start] == 0 && numbers[i] == 0) {
                    if (count >= 3) {
                        answer += count;
                        numberCountMap.remove(result);
                    }
                } else if (numbers[start] == 0 || numbers[i] == 0) {
                    if (count >= 2) {
                        answer += count;
                        numberCountMap.remove(result);
                    }
                } else {
                    answer += count;
                    numberCountMap.remove(result);
                }
            }
        }
    }
}