import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

class Solution {
    Map<String, Set<String>> reportedIdMap = new HashMap<>();
    Map<String, Integer> idIdxMap = new HashMap<>();
    int[] mailCount;

    public int[] solution(String[] id_list, String[] report, int k) {
        mailCount = new int[id_list.length];
        int i =0;
        for (String id : id_list) {
            reportedIdMap.put(id, new HashSet<>());
            idIdxMap.put(id, i++);
        }
        for (String r : report) {
            StringTokenizer st = new StringTokenizer(r);
            String reporterId = st.nextToken();
            String reportedId = st.nextToken();
            reportedIdMap.get(reportedId).add(reporterId);
        }
        checkBlocked(k);
        return mailCount;
    }

    public void checkBlocked(int k) {
        for (String reportedId : reportedIdMap.keySet()) {
            if (reportedIdMap.get(reportedId).size() < k) {
                continue;
            }
            for (String reporter : reportedIdMap.get(reportedId)) {
                mailCount[idIdxMap.get(reporter)]++;
            }
        }
    }
}