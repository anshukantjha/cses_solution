import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        int n = nextInt(); // reads one integer
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(0);
        dq.offer(0);
        dq.offer(0);
        dq.offer(0);
        dq.offer(0);
        dq.offer(1);
        func(n,dq);
        System.out.println(dq.peekLast());
    }

    private static void func(int n , Deque<Integer> dq){
        for (int i = 0; i < n ; i++) {
            int sum = 0;
            for (int val : dq) {
                sum = (sum + val) % MOD;
            }

            dq.pollFirst();
            dq.offerLast(sum);
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