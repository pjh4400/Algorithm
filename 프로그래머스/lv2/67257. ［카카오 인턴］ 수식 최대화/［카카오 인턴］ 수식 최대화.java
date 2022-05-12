import java.util.*;

class Solution {

    List<Long> numbers = new ArrayList<>();
    List<Character> operators = new ArrayList<>();
    Set<Character> operatorSet = new HashSet<>(); // 사용된 연산자 세트
    long answer = 0;

    public long solution(String expression) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            if (Character.isDigit(expression.charAt(i))) {
                sb.append(expression.charAt(i));
            } else {
                numbers.add(Long.parseLong(sb.toString()));
                sb.setLength(0);
                operatorSet.add(expression.charAt(i));
                operators.add(expression.charAt(i));
            }
        }
        numbers.add(Long.parseLong(sb.toString())); // 마지막 숫자 넣어준다.
        sb.setLength(0);
        List<Character> operatorList = new ArrayList<>(operatorSet); // 순서가 지켜져야하므로 List 로 변환한다.
        boolean[] visited = new boolean[operatorList.size()];
        makePriority(operatorList, new LinkedList<>(), visited);
        return answer;
    }

    // 연산자 우선순위 순열 구하기
    void makePriority(List<Character> operatorList, List<Character> priority, boolean[] visited) {
        if (visited.length == priority.size()) {
            answer = Math.max(answer, calculate(priority));
            return;
        }

        for (int i = 0; i < operatorList.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                priority.add(operatorList.get(i));
                makePriority(operatorList, priority, visited);
                priority.remove(priority.size()-1);
                visited[i] = false;
            }
        }
    }

    // 계산
    Long calculate(List<Character> priority) {
        List<Character> copyOperators = new ArrayList<>(List.copyOf(operators));
        List<Long> copyNumbers = new ArrayList<>(List.copyOf(numbers));
        for(char currentOperator : priority) {
            for (int i = 0; i < copyOperators.size(); i++) {
                if (copyOperators.get(i) != currentOperator) {
                    continue;
                }
                long result = getCalculateResult(copyNumbers.get(i), copyNumbers.get(i + 1), currentOperator);
                copyNumbers.remove(i + 1); // 계산 완료 된 뒤 피연산자 제거
                copyNumbers.set(i, result); // 계산 완료 된 앞 피연산자를 계산 결과로 대체
                copyOperators.remove(i); // 계산 완료 후 연산자 제거
                i--; // 연산자 줄었으므로 인덱스도 줄여준다.
            }
        }
        return Math.abs(copyNumbers.get(0)); // 절댓값
    }

    long getCalculateResult(long a, long b, char currentOperator) {
        switch (currentOperator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }
        return 0L;
    }

}