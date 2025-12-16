import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Edidistiterative {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        String a = nextLine();
        String b = nextLine();

        int[] curr = new int[b.length()+1];
        int[] prev = new int[b.length()+1];

        for (int i = 0; i < prev.length; i++) {
            prev[i] = b.length() - i;
        }

        for (int i = a.length()-1; i >=0; i--) {
            curr[b.length()] = a.length()-i; // might get used in curr[j+1]

            for (int j = b.length()-1; j >= 0; j--) {
                if(a.charAt(i) == b.charAt(j)) {
                    curr[j] =prev[j+1];
                }else{
                    int replace = 1+prev[j+1];
                    int insert = 1+curr[j+1];
                    int delete = 1+prev[j];
                    curr[j] = Math.min(replace,Math.min(insert,delete));
                }
            }
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        System.out.println(prev[0]);
    }



    static String next() throws IOException{
        while (st == null || !st.hasMoreTokens()){
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    static String nextLine() throws IOException{
        return br.readLine();
    }
}
