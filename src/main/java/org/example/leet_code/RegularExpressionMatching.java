package org.example.leet_code;

public class RegularExpressionMatching {

    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char currentS = s.charAt(i - 1);
                char currentP = p.charAt(j - 1);

                if (currentP == '.' || currentP == currentS) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (currentP == '*') {
                    char prevP = p.charAt(j - 2);
                    dp[i][j] = dp[i][j - 2];
                    if (prevP == '.' || prevP == currentS) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a"));
    }

}
