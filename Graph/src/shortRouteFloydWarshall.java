import java.io.*;
import java.util.*;

public class shortRouteFloydWarshall {

    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(System.out);


    static final long INF = (long) 1e18;
    static int n;


    public static void main(String[] args) throws IOException {
        n = nextInt();
        int m = nextInt();
        int q = nextInt();

        long[][] arr = new long[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n ; j++) {
                if(i == j){
                    arr[i][j] = 0;
                }else {
                    arr[i][j] = INF;
                }
            }
        }

        for (int i = 1; i <= m; i++) {
            int a = nextInt();
            int b = nextInt();
            int c = nextInt();

            arr[a][b] = Math.min(arr[a][b],c); // to avoid multiple road having more cost later to not get updated
            arr[b][a] = Math.min(arr[b][a],c);

        }


        // we have A0 now apply flloyd warshal matrix manipulation

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n ; i++) {
                if(arr[i][k] == INF ) continue; // skip here to avoid multiple checks
                for (int j = 1; j <= n ; j++) {
                    if(arr[k][j] == INF) continue;
                    long mini = arr[i][k] + arr[k][j];
                    // going through k if possible (i -> k + k -> j)
                    if(arr[i][j] > mini){
                        arr[i][j] = mini;
                    }
                }
            }

        }


        for (int i = 1; i <= q ; i++) {
            int a = nextInt();
            int b = nextInt();

            long res = arr[a][b];
            if(res >= INF){
                out.println(-1);
            }else{
                out.println(res);
            }
        }

        out.flush();
    }


    static int nextInt() throws IOException{
        in.nextToken();
        return (int ) in.nval;
    }



}
