import static java.lang.Math.max;
import static java.lang.Math.min;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

  static int n;
  static int[] a;
  static int[] operator = new int[4]; // 연산자
  static int maxValue = -1000000001;
  static int minValue = 1000000001;

  public static void calculator(int i, int result) { // i번째 결과 result
    if (i == n - 1) {
      maxValue = max(maxValue, result);
      minValue = min(minValue, result);
    } else {
      i += 1;
      for (int j=0;j<4;j++){
        if (operator[j] > 0){
          operator[j]--;
          switch(j){
            case 0:
              calculator(i,result+a[i]);
              break;
            case 1:
              calculator(i, result-a[i]);
              break;
            case 2:
              calculator(i, result*a[i]);
              break;
            case 3:
              calculator(i, result/a[i]);
              break;
          }
          operator[j]++;
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    n = Integer.parseInt(br.readLine());
    a = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    for (int i = 0; i < n; i++) {
      a[i] = Integer.parseInt(st.nextToken());
    }

    st = new StringTokenizer(br.readLine(), " ");
    for (int i = 0; i < 4; i++) {
      operator[i] = Integer.parseInt(st.nextToken());
    }
    calculator(0, a[0]);
    bw.write(maxValue + "\n");
    bw.write(String.valueOf(minValue));
    bw.flush();
    bw.close();
  }

}
