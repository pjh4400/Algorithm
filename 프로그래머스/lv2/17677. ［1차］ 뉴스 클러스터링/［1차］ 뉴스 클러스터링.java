import java.util.List;
import java.util.ArrayList;

public class Solution {

    List<String> pairs1 = new ArrayList<>();
    List<String> pairs2 = new ArrayList<>();

    public int solution(String str1, String str2) {
        int answer = 0;
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        for(int i=0;i<str1.length()-1;i++){
          String strPair = str1.substring(i,i+2);
          if (makePair(strPair)){
            pairs1.add(strPair);
          }
        }
        for(int i=0;i<str2.length()-1;i++){
          String strPair = str2.substring(i,i+2);
          if (makePair(strPair)){
            pairs2.add(strPair);
          }
        }
        answer = calculateJ();
        return answer;
    }

    boolean makePair(String str){
        for(int i=0;i<2;i++){
          char ch = str.charAt(i);
          if(ch < 'a' || ch > 'z'){
            return false;
          }
        }
        return true;
    }

    int calculateJ() {
        int union = 0;
        List<String> intersection = new ArrayList<>();

        //pair2 에 pair1 과 같은 문자열이 있으면 교집합에 추가, 합집합에서 제거
        for (String str : pairs1) {
            if (pairs2.remove(str)) {
                intersection.add(str);
            }
        }

        union = pairs1.size() + pairs2.size();
        if (union == 0) return 65536;
        return (int) ((double) intersection.size() / (double) union * 65536);
    }
  
}