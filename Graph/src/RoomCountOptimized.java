import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RoomCountOptimized {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;


    static final int[] delRow = {-1,0,1,0};
    static final int[] delCol = {0,1,0,-1};
    static int ans;
    static int n;
    static int m;
    static char[][] arr;

    public static void main(String[] args) throws IOException {

        n = nextInt();
        m = nextInt();

        arr = new char[n][m];
        for (int i = 0; i < n; i++) {
            String row = next();
            arr[i] = row.toCharArray();
        }


        ans = 0;
        int[] stack = new int[n * m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if( arr[i][j] == '.'){
                    ans++;
                    // dfs(i,j);
                    iterativeDfs(i,j,stack);
                }
            }
        }

        System.out.println(ans);

    }

    private static void iterativeDfs(int i, int j, int[] stack) {
        int top = 0;

        arr[i][j] = '#';
        stack[top] = i * m + j; // encoding to 1-D

        while(top>=0){
            int curr = stack[top--];

            int r = curr / m; // divide will get us i as it will make j/m = 0
            int c = curr % m; // modulo will make i*m as 0 and give j

            for (int k = 0; k < 4; k++) {
                int nrow = r + delRow[k];
                int ncol = c + delCol[k];

                if((nrow>=0 && nrow<n && ncol >= 0 && ncol < m) && arr[nrow][ncol] == '.' ){
                    stack[++top] = nrow * m + ncol;
                    arr[nrow][ncol] = '#';
                }
            }
        }
    }

    private static void dfs(int i, int j){
        arr[i][j] = '#';

        for (int k = 0; k < 4; k++) {
            int nrow = i + delRow[k];
            int ncol = j + delCol[k];

            if((nrow>=0 && nrow<n && ncol >= 0 && ncol < m) && arr[nrow][ncol] == '.' ){
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