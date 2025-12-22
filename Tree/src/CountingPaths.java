// Prerequisite : LCA done previously in ComapnyQueryII.java

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CountingPaths {


    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(System.out);


    static final int LOG = 20;
    static int n;
    static int m;
    static List<List<Integer>> adj;
    static int[] ans;
    static int[] depth;
    static int[][] up;
    static int[] order;



    public static void main(String[] args) throws IOException {

        n = nextInt();
        m = nextInt();

        adj = new ArrayList<>();
        ans = new int[n+1];
        depth = new int[n+1];
        up = new int[n+1][LOG];
        order = new int[n];


        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            int a = nextInt();
            int b = nextInt();

            adj.get(a).add(b);
            adj.get(b).add(a);
        }

//        out.println(adj);

        // create depth array
        bfs(1);

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


        for (int i = 0; i < m; i++) {
            int u = nextInt();
            int v = nextInt();

            int lca = getAncestor(u,v);
            // dfs_in(u,v,0);

            // making a difference array
            ans[u] += 1;
            ans[v] += 1;

            ans[lca] -= 1;

            int lca_parent = up[lca][0];
            if(lca_parent != -1){
                ans[lca_parent] -= 1;
            }

        }

        // dfs to convert the differnce array into legit answer
        // or we need to use order array

        for (int i = n-1; i >= 0 ; i--) {
            int u = order[i];
            int p = up[u][0] ; // immediate parent

            if(p != -1){
                ans[p] += ans[u];
            }
        }




        for (int i = 1; i <= n ; i++) {
            out.print(ans[i] + " ");
        }

        out.flush();
    }

    private static void dfs_conversion(int u, int par) {
        int x = ans[u];

        for(int v : adj.get(u)){
            if(v == par) continue;

            dfs_conversion(v,u);
            x += ans[v];
        }

        ans[u] = x;
    }


    private static int getAncestor(int a,int b){

        // ensure a is deeper
        if(depth[a] < depth[b]){
            int temp = a;
            a = b;
            b = temp;
        }

        // lift the lower
        for (int i = LOG-1; i >=0 ; i--) {
            if(depth[a] - (1<<i) >= depth[b]){
                a = up[a][i];
            }
        }

        if(a == b) return a;

        // lift both

        for (int i = LOG-1; i >= 0 ; i--) {
            if(up[a][i] != -1 && up[a][i] != up[b][i]){
                a = up[a][i];
                b = up[b][i];
            }
        }

        // lca will be just above both of them
        return up[a][0];
    }

    static void bfs(int root) {

        int idx = 0;
        order[idx++] = root;

        int[] q = new int[n + 5]; // array as queue
        int front = 0, rear = 0;

        q[rear++] = root;
        depth[root] = 0;
        up[root][0] = -1;

        while (front < rear) {
            int u = q[front++];

            for (int v : adj.get(u)) {
                if (v == up[u][0]) continue;
                depth[v] = depth[u] + 1;
                up[v][0] = u; // immediate parent
                q[rear++] = v;
                order[idx++] = v;
            }
        }
    }


    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }
}

