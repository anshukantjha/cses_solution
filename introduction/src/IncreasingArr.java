import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IncreasingArr {

        static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        static StringTokenizer st;

        public static void main(String[] args) throws IOException {

            int n = nextInt();
            long[] arr = new long[n];

            for (int i = 0; i < n; i++) {
                arr[i] = nextLong();
            }

            long prevMin = arr[0];
            long cnt = 0;
            for (int i = 1; i < n; i++) {
                if(arr[i] <= prevMin) cnt += prevMin - arr[i];
                else{
                    prevMin = arr[i];
                }
            }

            System.out.println(cnt);


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
