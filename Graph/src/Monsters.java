import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Monsters {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int INF = (int) 1e9;
    static final int[] delRow = {-1,0,1,0};
    static final int[] delCol = {0,1,0,-1};
    static final char[] way = {'U','R','D','L'};
    static char[][] arr ;
    static int startX , startY;
    static int[][] dist; // stores dist. of monsters
    static int[][] dista; // stores dist. of a
    static char[][] parent;
    static boolean[][] visited;
    static boolean notPossible;

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int m = nextInt();

        arr = new char[n][m];
        parent = new char[n][m];
        dist = new int[n][m];
        dista = new int[n][m];
        visited = new boolean[n][m];
        notPossible = true;

        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            String row = next();
            for (int j = 0; j < m; j++) {

                dist[i][j] = INF;
                dista[i][j] = INF;

                if(row.charAt(j) == 'A'){
                    startX = i;
                    startY = j;
                }

                if(row.charAt(j) == 'M'){
                    q.add(new int[] { i,j});
                    dist[i][j] = 0;
                    visited[i][j] = true;
                }
                arr[i][j] = row.charAt(j);

            }
        }

//        System.out.println(Arrays.deepToString(arr));


        while (!q.isEmpty()){
            int[] curr = q.poll();
            int row = curr[0];
            int col = curr[1];

            for (int i = 0; i < 4; i++) {
                int nrow = row + delRow[i];
                int ncol = col + delCol[i];

                if((nrow>=0 && nrow<n && ncol >=0 && ncol <m) && !visited[nrow][ncol] && arr[nrow][ncol] != '#'){
                    dist[nrow][ncol] = dist[row][col] + 1;
                    visited[nrow][ncol] = true;
                    q.add(new int[]{nrow,ncol});
                }
            }
        }

        q.add(new int[]{startX,startY});
        dista[startX][startY] = 0;
        visited[startX][startY] = true;

        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i],false);
        }

        // second bfs to check for "A"

        while (!q.isEmpty()){
            int[] curr = q.poll();
            int row = curr[0];
            int col = curr[1];

            for (int i = 0; i < 4; i++) {
                int nrow = row + delRow[i];
                int ncol = col + delCol[i];

                if((nrow>=0 && nrow<n && ncol >=0 && ncol <m) && !visited[nrow][ncol] && arr[nrow][ncol] != '#'){
                    dista[nrow][ncol] = dista[row][col] + 1;
                    parent[nrow][ncol] = way[i];
                    visited[nrow][ncol] = true;
                    q.add(new int[]{nrow,ncol});
                }
            }
        }

//        System.out.println(Arrays.deepToString(parent));
//        System.out.println(Arrays.deepToString(dist));
//        System.out.println(Arrays.deepToString(dista));


        // now escape so check from boundary


        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if((i==0 || j== 0 || i == n-1 || j == m-1)){
                    if(dista[i][j] < dist[i][j]){
                        // make the path
//                        System.out.println("INside");
                       int curx = i;
                       int  cury = j;

//                        System.out.println(curx + " " + cury);
                        while( curx != startX || cury!= startY ){
                            char ch = parent[curx][cury];
                            ans.append(ch);

                            if(ch == 'U') curx++;
                            if(ch == 'D') curx--;
                            if(ch == 'R') cury--;
                            if(ch == 'L') cury++;
                        }
                        notPossible = false;
                        break;
                    }
                }
            }

            if(!notPossible) break;
        }

        if(notPossible){
            System.out.println("NO");
        }else{
            System.out.println("YES");

            System.out.println(ans.length());

            System.out.println(ans.reverse());
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
