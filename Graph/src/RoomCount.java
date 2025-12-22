import java.io.*;
import java.util.StringTokenizer;

public class RoomCount {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;


    static final int[] delRow = {-1,0,1,0};
    static final int[] delCol = {0,1,0,-1};
    static boolean[][] visited ;
    static int ans;
    static int n;
    static int m;
    static char[][] arr;

    public static void main(String[] args) throws IOException {

        n = nextInt();
        m = nextInt();

         arr = new char[n][m];
        for (int i = 0; i < n; i++) {
            String row = nextLine();
            arr[i] = row.toCharArray();
        }


        visited = new boolean[n][m];
        ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if( arr[i][j] == '.' && !visited[i][j]){
                    ans++;
                    dfs(i,j);
                }
            }
        }

        System.out.println(ans);

    }

    private static void dfs(int i, int j){
        visited[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int nrow = i + delRow[k];
            int ncol = j + delCol[k];

            if((nrow>=0 && nrow<n && ncol >= 0 && ncol < m) && !visited[nrow][ncol] && arr[nrow][ncol] == '.' ){
                dfs(nrow,ncol);
            }
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