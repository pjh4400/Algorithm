import java.util.*;

class Solution {
    Map<String, Integer> dict = new HashMap<>();
    List<Integer> numbers = new ArrayList<>();

    public int[] solution(String msg) {
        initDict(); // 1. 길이가 1인 모든 단어를 포함하도록 사전을 초기화한다.
        int idx = 0;
        int len = msg.length();
        while (idx < len) {
            idx = compress(msg, len, idx);
        }
        return numbers.stream().mapToInt(Integer::intValue).toArray();
    }

    void initDict() {
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            dict.put(String.valueOf(ch), ch - 'A' + 1);
        }
    }

    // 압축
    // 2. 사전에서 현재 입력과 일치하는 가장 긴 문자열 w를 찾는다.
    // 3. w에 해당하는 사전의 색인 번호를 출력하고, 입력에서 w를 제거한다.
    // 4. 입력에서 처리되지 않은 다음 글자가 남아있다면(c), w+c에 해당하는 단어를 사전에 등록한다.
    int compress(String str, int len, int start) {
        System.out.println(start);
        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(start));
        for (int i = start + 1; i < len; i++) {
            sb.append(str.charAt(i));
            if (!dict.containsKey(sb.toString())) {
                System.out.println(sb);
                dict.put(sb.toString(), dict.size() + 1);
                numbers.add(dict.get(sb.deleteCharAt(sb.length() - 1).toString()));
                return i;
            }
        }
        numbers.add(dict.get(sb.toString()));
        return len;
    }

}