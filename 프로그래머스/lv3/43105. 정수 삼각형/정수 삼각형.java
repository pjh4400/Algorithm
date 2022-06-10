class Solution {
    int maxLow, maxSum;
    int[][] dp;

    public int solution(int[][] triangle) {
        maxLow = triangle.length - 1;
        dp = new int[triangle.length][triangle.length+1];
        for(int i=0;i<triangle.length;i++){
            for(int j=0;j<=i;j++){
                dp[i][j] = triangle[i][j];
            }
        }
        for(int i=0;i<triangle.length-1;i++){
            for(int j=0;j<=i;j++){
                dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j] + triangle[i+1][j]);
                dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i][j] + triangle[i+1][j+1]);
            }
        }
        for(int j=0;j<=triangle.length;j++){
            maxSum = Math.max(maxSum,dp[maxLow][j]);
        }
        return maxSum;
    }
    
}