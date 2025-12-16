import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Editdist {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        String a = nextLine();
        String b = nextLine();

        int i = 0;
        int j = 0;
        int[][] dp = new int[a.length()][b.length()];

        for (int k = 0; k < a.length(); k++) {
            Arrays.fill(dp[k],-1);
        }

        System.out.println(func(a,b,i,j,dp));
    }

    private static int func(String a, String b, int i , int j,int[][] dp){
        if (i == a.length()) return b.length() - j; // insert rem. of b
        if (j == b.length()) return a.length() - i; // vice versa

        if(dp[i][j] != -1) return dp[i][j];

        if(a.charAt(i) == b.charAt(j)) return dp[i][j] = func(a,b,i+1,j+1,dp);
        else{
            int replace = 1 + func(a,b,i+1,j+1,dp);
            int delete = 1 + func(a,b,i+1,j,dp);
            int insert = 1 + func(a,b,i,j+1,dp);

            return dp[i][j] = Math.min(insert,Math.min(replace,delete));
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

    static String nextLine() throws IOException {
        return br.readLine();
    }


}
