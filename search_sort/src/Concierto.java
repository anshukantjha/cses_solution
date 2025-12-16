import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Collections.sort;

public class Concierto{

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int n = nextInt();
        int m = nextInt();

        TreeMap<Long, Integer> tickets = new TreeMap<>();

        // Input ticket prices
        for (int i = 0; i < n; i++) {
            long price = nextLong();
            tickets.put(price, tickets.getOrDefault(price, 0) + 1);
        }


        for (int i = 0; i < m; i++) {
            long t = nextLong();

            Long key = tickets.floorKey(t);

            if(key == null){
                System.out.println(-1);
            }else{
                System.out.println(key);
                if(tickets.get(key) == 1){
                    tickets.remove(key);
                }else{
                    tickets.put(key,tickets.get(key)-1);
                }
            }

        }
    }

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    static long nextLong() throws IOException {
        return Long.parseLong(next());
    }

}