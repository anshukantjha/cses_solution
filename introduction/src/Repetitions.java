import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Repetitions {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {


        String str = next();
        int n = str.length();

        int l = 0;
        int r = 0;
        int maxlen = 0;

        while(r<n){

            while(str.charAt(l) != str.charAt(r)){
                l++;
            }
            if(str.charAt(l) == str.charAt(r)){
                maxlen = Math.max(maxlen,r-l+1);
            }
            r++;
        }

        System.out.println(maxlen);




    }

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
    static long nextLong() throws IOException {
        return Long.parseLong(next());
    }

}
