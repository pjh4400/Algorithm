public class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        StringBuilder numberStr = new StringBuilder();
        int length = m * t; // 필요한 숫자 수
        for (int i = 0; numberStr.length() <= length; i++) {
            numberStr.append(Integer.toString(i, n)); // i 를 n진법으로 변환
        }
        // 튜브의 순서에 해당하는 글자들만 추출
        for (int i = p - 1; answer.length() < t; i += m) {
            answer.append(numberStr.charAt(i));
        }
        return answer.toString().toUpperCase();
    }
}