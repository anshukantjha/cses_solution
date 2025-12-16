import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


// State:- dp[x][i] => sum so far = x and from index i (i.e can take coin from i to n-1)
// dp[5][1] => means sum=5 using coins from idx 1 to n-1

public class CoinComb2 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        int n = nextInt(); // number of coins
        int x = nextInt(); // target sum

        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = nextInt();
        }

        int[] curr = new int[x+1];
        int[] prev = new int[x+1];

        for (int i = n-1; i >= 0 ; i--) {
            prev[0] = 1;
            curr[0] = 1;
            for (int j = 1; j <= x ; j++) {
                int ways = prev[j];
                if(j-coins[i] >=0 ){
                    ways =(curr[j-coins[i]] + ways) % MOD;
                }
                curr[j]= ways;
            }
            prev = curr;
        }

        System.out.println(curr[x]); // make sum considering all coins

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
