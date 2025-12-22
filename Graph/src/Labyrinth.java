import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Labyrinth {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;


    static final int[] delRow = {-1,0,1,0};
    static final int[] delCol = {0,1,0,-1};
    static final char[] way = {'U','R','D','L'};
    static int n,m;
    static char[][] arr;
    static boolean[][] visited;
    static boolean isFound;
    static String bestPath = "";



    public static void main(String[] args) throws IOException {


        n = nextInt();
        m = nextInt();

        int aX = 0;
        int aY = 0;

        arr = new char[n][m];
        for (int i = 0; i < n; i++) {
            String row = next();
            for (int j = 0; j < row.length(); j++) {
                if(row.charAt(j) == 'A'){
                    aX = i;
                    aY = j;
                }

                arr[i][j] = row.charAt(j);
            }
        }

        StringBuilder path = new StringBuilder();
        visited = new boolean[n][m];
        isFound = false;


        dfs(aX,aY,path);

        if(isFound) {
            System.out.println("YES");
            System.out.println(bestPath.length());
            System.out.println(bestPath);
        }else{
            System.out.println("NO");
        }


    }

    private static void dfs(int i,int j,StringBuilder path){
        visited[i][j] = true;

        for (int k = 0; k < 4; k++) {
            int nrow = i + delRow[k];
            int ncol = j + delCol[k];

            if((nrow>=0 && nrow<n && ncol >= 0 && ncol < m) && !visited[nrow][ncol] ){

                path.append(way[k]);

                if(arr[nrow][ncol] == 'B'){

                    if (bestPath.isEmpty() || path.length() < bestPath.length()) {
                        bestPath = path.toString();
                        isFound = true;
                    }

                }else if (arr[nrow][ncol] == '.' ) {
                    dfs(nrow,ncol,path);
                }

                path.deleteCharAt(path.length()-1);

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

}
