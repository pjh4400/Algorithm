import java.util.*;

class Solution {
    
    List<Long> numbers = new ArrayList<>();
    List<Character> operators = new ArrayList<>();
    Set<Character> operatorSet = new HashSet<>();
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
        List<Character> operatorList = new ArrayList<>(operatorSet);
        boolean[] visited = new boolean[operatorList.size()];
        makePriority(operatorList, new LinkedList<>(), visited);
        return answer; // 절댓값
    }

    // 연산자 우선순위
    void makePriority(List<Character> operatorList, Queue<Character> priority, boolean[] visited) {
        if (operatorList.size() == priority.size()) {
            answer = Math.max(answer, calculate(priority));
            return;
        }

        for (int i = 0; i < operatorList.size(); i++) {
            if (!visited[i]) {
                priority.add(operatorList.get(i));
                makePriority(operatorList, priority, visited);
                visited[i] = false;
            }
        }
    }

    // 계산
    Long calculate(Queue<Character> priority) {
        List<Character> copyOperators = new ArrayList<>(List.copyOf(operators));
        List<Long> copyNumbers = new ArrayList<>(List.copyOf(numbers));

        while (!priority.isEmpty()) {
            char currentOperator = priority.poll();
            for (int i = 0; i < copyOperators.size(); i++) {
                if (copyOperators.get(i) != currentOperator) {
                    continue;
                }
                long result = getCalculateResult(copyNumbers.get(i), copyNumbers.get(i + 1), currentOperator);
                copyNumbers.remove(i + 1); // 계산 완료 된 뒤 피연산자 제거
                copyNumbers.remove(i); // 계산 완료 된 앞 피연산자를 계산 결과로 대체
                copyNumbers.add(i, result);
                copyOperators.remove(i); // 계산 완료 후 연산자 제거
                i--; // 연산자 삭제
            }
        }
        return Math.abs(copyNumbers.get(0));
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