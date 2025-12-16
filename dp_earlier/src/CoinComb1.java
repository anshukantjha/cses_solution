import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// State: dp[i] no. of ways to make sum = i;



public class CoinComb1 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static  final  int MOD = 1000000007;

    public static void main(String[] args) throws IOException {

        int n = nextInt(); // number of coins
        int x = nextInt(); // target sum

        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = nextInt();
        }
        int[] dp = new int[x + 1];

        dp[0] = 1;

        for (int i = 1; i <= x; i++) {
           dp[i] = 0;
            for (int j = 0; j < n; j++) {
                if (i - coins[j] >= 0) {
                    dp[i] = (dp[i] + dp[i - coins[j]]) % MOD;
                }
            }
        }

        System.out.println(dp[x]);
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