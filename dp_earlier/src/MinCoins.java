import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MinCoins {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {

        int n = nextInt(); // number of coins
        int x = nextInt(); // target sum

        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = nextInt();
        }
        int[] dp = new int[x + 1];

        dp[0] = 0;

        for (int i = 1; i <= x ; i++) {
            dp[i] = INF;
            for (int j = 0; j < n; j++) {
                if(i - coins[j] >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        System.out.println(dp[x]>=INF?-1:dp[x]);
    }


    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}
