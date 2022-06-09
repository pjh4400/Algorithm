class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String nStr = Integer.toString(n,k); // k진수 문자열
        StringBuilder primeSb = new StringBuilder();
        for (int i=0;i<nStr.length();i++){
            if(nStr.charAt(i)=='0'){
                if(primeSb.length() > 0 && isPrimeNumber(Long.parseLong(primeSb.toString()))){
                    answer++;
                }
                primeSb.setLength(0);
                continue;
            }
            primeSb.append(nStr.charAt(i));
        }
        if(primeSb.length() > 0 && isPrimeNumber(Long.parseLong(primeSb.toString()))){
            answer++;
        }
        return answer;
    }
    
    boolean isPrimeNumber(long n){
        if(n < 2){
            return false;
        }
        for(int i=2;i<=Math.sqrt(n);i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }
}