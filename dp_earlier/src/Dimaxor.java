import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Dimaxor {

    static class Helper {
        boolean first;
        int second;
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int m = nextInt();

        int[][] matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = nextInt();
            }
        }

        // now we have input matrix ready
        Helper[][] dp = new Helper[n+1][1024];
        dp[0][0].first = true;
        for (int i = 1; i < 1024; i++) {
            dp[0][i].first = false;
        }

        int x = 0;
        for (int i = 1; i <= n ; i++) {
            int j;
            for (j = 0; j < m ; j++) {
                if(dp[i-1][x ^ matrix[i-1][j]].first){
                    dp[i][x].first = true;
                    x = x ^ matrix[i-1][j];
                    break;
                }
            }
            dp[i][x].second = j;
        }

//        TODO:-
        int k = 5;
        boolean ans = dp[n][k].first;

        if(ans){
            List<Integer> choices = new ArrayList<>();
            int row= n;
            int xor_value = k;
            while(row != 1){
                int choice = dp[row][xor_value].second;
                choices.add(choice);
                int new_xor = xor_value ^ matrix[row-1][choice];
                row--;
                xor_value = new_xor;
            }

            System.out.println(choices);
        }
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
