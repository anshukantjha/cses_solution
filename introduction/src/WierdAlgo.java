import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class WierdAlgo {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {


            long n = nextLong();
            System.out.print(n + " ");
            while(n!=1){
                if(n%2 != 0){
                    n = 3*n +1;
                    System.out.print(n + " ");
                }else {
                    n /= 2;
                    System.out.print(n + " ");
                }
            }
    }

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }

    static long nextLong() throws IOException {
        return Long.parseLong(next());
    }

}
