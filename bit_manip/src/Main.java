import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;


    public static void main(String[] args) throws IOException {
        long n = nextLong();

        System.out.println(solve(n));

    }

    private static long solve(long n){
        if(n == 0){
            return 0;
        }

        long k =(long) Math.floor(Math.log(n)/Math.log(2));

        long t = (long) Math.pow(2,k);
        long R =  n - t ;

        return k * t/2 + (R+1) + solve(R);
    }



    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    static String nextLine() throws IOException {
        return br.readLine();
    }
    static long nextLong() throws IOException {
        return Long.parseLong(next());
    }
}