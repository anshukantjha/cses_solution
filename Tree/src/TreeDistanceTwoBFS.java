import java.io.*;
import java.util.*;

public class TreeDistanceTwoBFS {

    static StreamTokenizer in = new StreamTokenizer (new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(System.out);

    static long[] below; // below
    static long[] up; // above i.e parent
    static long[] sub; // subtree is necessary as to remember how many subtree from src
    static List<List<Integer>> adj;
    static int[] parent;
    static int[] order;


    static int n;

    public static void main(String[] args) throws IOException {
        n = nextInt();

        adj = new ArrayList<>();
        below = new long[n+1];
        up = new long[n+1];
        sub = new long[n+1];

        parent = new int[n+1];
        order = new int[n]; // n as we have n nodes only or order is independent of index


        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
            sub[i] = 1; // initialize with 1 as done in dfs approach in function
        }

        int a,b;
        for (int i = 1; i < n; i++) {
            // n-1 edges
            a = nextInt();
            b = nextInt();

            adj.get(a).add(b);
            adj.get(b).add(a);
        }

//        out.println(adj);
//        out.println("Anshu");

        getParentOrder(1); // to get parent and order

        solveBottomUp(); // reverse LeverOrder fro dfs_in()
        solveTopDown(); // level order for dfs_out()

//        out.println(Arrays.toString(below));
//        out.println(Arrays.toString(up));
//        out.println(Arrays.toString(sub));
//
//        out.println(Arrays.toString(order));
//        out.println(Arrays.toString(parent));

        for (int i = 1; i <= n; i++) {
            out.print(below[i] + up[i] + " ");
        }

        out.flush();
    }

    private static void solveTopDown() {
        for (int i = 0; i < n; i++) {
            int u = order[i];

            for(int v : adj.get(u)){
                if(v == parent[u]) continue;

                //  in[par] + out[par] + n - sub[child] - ( in[child] + sub[child]);
                up[v] = below[u] + up[u] + n - below[v] - 2 * sub[v];
             }
        }
    }

    private static void solveBottomUp() {
        // similar to dfs_in() 
        for (int i = n-1; i > 0 ; i--) {
            int u = order[i];
            int p = parent[u];

            sub[p]  += sub[u];
            below[p] += below[u] + sub[u] ; 
        }
    }

    private static void getParentOrder(int root){
        int idx = 0;

        Queue<Integer> q = new ArrayDeque<>();
        boolean[] vis = new boolean[n+1];

        q.add(root);
        vis[root] = true;
        parent[root] = 0;

        while (!q.isEmpty()){
            int u = q.poll();
            order[idx++] = u;  // pehle parent isi main hojayega as q mai added hai

            for(int v : adj.get(u)){
                if(!vis[v]){
                    vis[v] = true;
                    parent[v] = u;
                    q.add(v);
                }
            }
        }

    }







    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

}

