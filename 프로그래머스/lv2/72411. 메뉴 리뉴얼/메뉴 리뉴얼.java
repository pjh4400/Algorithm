import java.util.*;

class Solution {
    
    Map<Integer, Map<String, Integer>> menuMap = new HashMap<>(); // key=글자수, value={key = 문자열,  value = 개수}
    char[] orderArray;

    /**
     * @param orders 크기 2 이상 20 이하, 알파벳 대문자, 중복 X
     * @param course 크기 1 이상 10 이하, 2 ~ 10 자연수가 오름차순으로 정렬, 중복 X
     * @return 각 코스요리 메뉴의 구성을 문자열 형식으로 배열에 담아 사전 순으로 오름차순 정렬
     * 각 원소에 저장된 문자열 또한 알파벳 오름차순으로 정렬
     * 배열의 길이가 1 이상이 되도록 주어집니다.
     */
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        for (int n : course) {
            menuMap.put(n, new HashMap<>());
        }
        for (String order : orders) {
            orderArray = order.toCharArray();
            Arrays.sort(orderArray);
            dfs(0, 0, "");
        }
        List<String> menuSortedList = new ArrayList<>();
        for (int n : course) {
            List<String> currentMaxMenu = new ArrayList<>();
            int currentMax = 0;
            Map<String, Integer> menuCountMap = menuMap.get(n);
            for (String menu : menuCountMap.keySet()) {
                //최소 2명 이상의 손님으로부터 주문된 단품메뉴 조합에 대해서만 코스요리 메뉴 후보에 포함
                if(menuCountMap.get(menu) < 2){
                    continue;
                }
                if (menuCountMap.get(menu) == currentMax) {
                    currentMaxMenu.add(menu);
                } else if (menuCountMap.get(menu) > currentMax) {
                    currentMax = menuCountMap.get(menu);
                    currentMaxMenu.clear();
                    currentMaxMenu.add(menu);
                }
            }
            menuSortedList.addAll(currentMaxMenu);
        }
        Collections.sort(menuSortedList);
        int size = menuSortedList.size();
        answer = new String[size];
        for (int i = 0; i < size; i++) {
            answer[i] = menuSortedList.get(i);
        }
        return answer;
    }


    void dfs(int depth, int idx, String currentOrder) {
        if (menuMap.containsKey(depth)) {
            Map<String, Integer> menuCountMap = menuMap.get(depth);
            menuCountMap.put(currentOrder, menuCountMap.getOrDefault(currentOrder, 0) + 1);
        }
        // 한 손님 당 나오는 단품메뉴 조합 저장
        for (int i = idx; i < orderArray.length; i++) {
            dfs(depth + 1, i + 1, currentOrder + orderArray[i]);
        }
    }
    
}