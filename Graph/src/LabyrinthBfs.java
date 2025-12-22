import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class LabyrinthBfs {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;


    static final int[] delRow = {-1,0,1,0};
    static final int[] delCol = {0,1,0,-1};
    static final char[] way = {'U','R','D','L'};


    public static void main(String[] args) throws IOException {


        int n = nextInt();
        int m = nextInt();

        int aX = 0;
        int aY = 0;
        int bX = 0;
        int bY = 0;

        char[][] arr = new char[n][m];
        char[][] pathFrom = new char[n][m];

        boolean[][] visited = new boolean[n][m];
        boolean isFound = false;


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

//        System.out.println(Arrays.deepToString(arr));


        // BFS logic below
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {aX,aY});
        visited[aX][aY] = true;


        while(!q.isEmpty()){
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];

            if(arr[r][c] == 'B'){
                // first path we will find will be smallest as it goes 1 unit in all possible path
                bX = r;
                bY = c;
                isFound = true;
                break;
            }

            for (int k = 0; k < 4; k++) {
                int nrow = r + delRow[k];
                int ncol = c + delCol[k];

                if ((nrow >= 0 && nrow < n && ncol >= 0 && ncol < m ) && arr[nrow][ncol] != '#' && !visited[nrow][ncol]) {

                    visited[nrow][ncol] = true;
                    pathFrom[nrow][ncol] = way[k]; // Remember the move: "I went 'U' to get here"
                    q.add(new int[]{nrow, ncol});
                }
            }
        }

        System.out.println(Arrays.deepToString(pathFrom));


        // create path string from A to B

        if(isFound){
            StringBuilder ans = new StringBuilder();

            int currX = bX;
            int currY = bY;

            while (currX != aX || currY != aY){ // dono violate hona chaiye
                char ch = pathFrom[currX][currY];
                ans.append(ch);
                if(ch == 'U') {
                    currX++;
                } else if (ch == 'R') {
                    currY--;
                } else if (ch == 'D') {
                    currX--;
                } else if (ch == 'L') {
                    currY++;
                }
            }

//            System.out.println(currX+ " " +  currY);
            System.out.println("YES");
            System.out.println(ans.length());
            System.out.println(ans.reverse());


        }else{
            System.out.println("NO");
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
