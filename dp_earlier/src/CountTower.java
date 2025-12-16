import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CountTower {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        int t = nextInt();
        while (t-- != 0){
            int n = nextInt();
            System.out.println(count(n));
        }
    }

    private static int count(int n){

        long prev_0 = 1;
        long prev_1 = 1;

        for (int i = n-2; i >= 0; i--) {
                long curr_0 = (4 * prev_0 + prev_1) % MOD;
                long curr_1 = (prev_0 + 2 * prev_1) % MOD;
                prev_0 = curr_0;
                prev_1 = curr_1;
        }

        return (int)(prev_0 + prev_1) % MOD;


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
