class Solution {
    // '(' 개수 == ')'개수  균형잡힌 괄호 문자열 p
    public String solution(String p) {
        return changeToTrue(p); // 올바른 괄호 문자열
    }


    // 올바른 문자열로 변환
    String changeToTrue(String w) {
        String u = "", v = "";
        StringBuilder sb = new StringBuilder();
        // 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
        if (w.isEmpty()) return "";
        // 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리
        int vStartIndex = getBalance(w);
        u = w.substring(0, vStartIndex);
        if (vStartIndex < w.length()) {
            v = w.substring(vStartIndex);
        }
        if (checkTrue(u)) { // 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
            //수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
            sb.append(u);
            sb.append(changeToTrue(v));
        } else { //"올바른 괄호 문자열"이 아니라면
            // 4-1 ~ 3
            sb.append("(");
            sb.append(changeToTrue(v));
            sb.append(")");
            // u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
            for (int i = 1; i < u.length() - 1; i++) {
                if (u.charAt(i) == '(') {
                    sb.append(")");
                } else {
                    sb.append("(");
                }
            }
        }
        // 생성된 문자열을 반환합니다.
        return sb.toString();
    }

    // 올바른 문자열인지 체크
    boolean checkTrue(String str) {
        int open = 0; //'(' 개수
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                open++;
            } else {
                open--;
            }
            // 앞에 '(' 없이 ')' 가 먼저 나오면 올바르지 않은 문자열
            if (open < 0) {
                return false;
            }
        }
        return true;
    }

    // 균형 잡힌 문자열 'u' 찾기 : 'v' 시작 인덱스 반환
    int getBalance(String w) {
        int open = 0, close = 0, vStartIndex = w.length();
        for (int i = 0; i < w.length(); i++) {
            if (w.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }
            if (open == close) {
                vStartIndex = i + 1;
                break;
            }
        }
        return vStartIndex;
    }
}