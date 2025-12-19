// O(nlogn)

import java.io.*;
import java.util.Arrays;

public class CompanyQueriesIBinaryLift {


    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(System.out);

    static final int LOG = 20;
    static int n;
    static int q;
    static int[][] up;

    public static void main(String[] args) throws IOException {

        n = nextInt();
        q = nextInt();

        up = new int[n+1][LOG];

        for(int[] row : up){
            Arrays.fill(row,-1);
        }

        // first parent
        int a;
        for (int i = 2; i <=n ; i++) {
            a = nextInt();
            up[i][0] = a;
        }

        for (int j = 1; j < LOG ; j++) {
            for (int i = 1; i <= n; i++) {
                int prevPar = up[i][j - 1];
                // just filled if prevPar not exist
                if (prevPar != -1) {
                    up[i][j] = up[ prevPar ][j - 1];
                } else {
                    up[i][j] = -1;
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            out.println(Arrays.toString(up[i]));
        }

        for (int i = 0; i < q; i++) {
            int x = nextInt();
            int k = nextInt();

            out.println(getAncestor(x,k));

        }



        out.flush();
    }

    private static int getAncestor(int x, int k) {

        for (int i = 0; i < LOG; i++) {

            // if ith bit is set or not
            if((k & (1<<i) )!= 0){
                x = up[x][i];
                if(x == -1) return -1;
            }
        }

        return x;

    }


//    private static void getParent(int root){
//        Queue<Integer> q = new ArrayDeque<>();
//        boolean[] vis = new boolean[n+1];
//
//        q.add(root);
//        vis[root] = true;
//        parent[root] = -1;
//
//        while (!q.isEmpty()){
//            int u = q.poll();
//
//            for(int v : adj.get(u)){
//                if(!vis[v]){
//                    vis[v] = true;
//                    parent[v] = u;
//                    q.add(v);
//                }
//            }
//        }
//
//    }

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

}
