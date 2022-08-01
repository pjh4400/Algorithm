import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static Set<String> memoSet = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String[] line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);
        for (int i=0;i<n;i++){
            memoSet.add(br.readLine());
        }
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine(),",");
            while(st.hasMoreTokens()){
                memoSet.remove(st.nextToken());
            }
            System.out.println(memoSet.size());
        }
    }
}