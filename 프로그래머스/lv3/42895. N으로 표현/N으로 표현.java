import java.util.*;

class Solution {
    List<Set<Integer>> numbers = new ArrayList<>(); // 1,2,3... 개로 만든 숫자를 담은 List
    public int solution(int N, int number) {
        int answer = 0;
        for(int i=0;i<9;i++){ // N 을 i 개 써서 만든 숫자
            numbers.add(new HashSet<>());
        }
        if(N == number){
            return 1;
        }
        numbers.get(1).add(N); // N 1개로 만든 수는 N 뿐 
        for(int i=2;i<9;i++){
            Set<Integer> currentSet = numbers.get(i);
            for(int j=1;j<i;j++){
                Set<Integer> preSetA = numbers.get(j);
                Set<Integer> preSetB = numbers.get(i-j);
                for(int a : preSetA) {
                    for(int b : preSetB){
                        currentSet.add(a+b);
                        currentSet.add(a-b);
                        currentSet.add(a*b);
                        if(b!=0){
                          currentSet.add(a/b);
                        }
                    }
                }
                currentSet.add(Integer.parseInt(String.valueOf(N).repeat(i)));
            }
            if (currentSet.contains(number)){
                return i;
            }
        }
        return -1;   // 최솟값이 8보다 크면 -1을 리턴
    }
    
    
}