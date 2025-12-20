import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DistanceQuery {


    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(System.out);


    static final int LOG = 20;
    static int n;
    static int q;
    static List<List<Integer>> adj;
    static int[] depth;
    static int[][] up;

    public static void main(String[] args) throws IOException {

        n = nextInt();
        q = nextInt();

        adj = new ArrayList<>();
        depth = new int[n+1];
        up = new int[n+1][LOG];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(up[i] , -1);
            adj.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            int a = nextInt();
            int  b = nextInt();
            adj.get(a).add(b);
            adj.get(b).add(a);
        }


//        out.println(adj);

        // dfs to get depth and parnent
        // dfs(1,0,0);
        bfs(1);

        // create the jump 2D array

        for (int j = 1; j < LOG ; j++) {
            for (int i = 1; i <= n; i++) {
                int prev = up[i][j-1];
                if(prev != -1){
                    up[i][j] = up[prev][j-1];
                }else{
                    up[i][j] = -1;
                }
            }
        }

//        out.println(Arrays.deepToString(up));
//        out.println(Arrays.toString(depth));


        for (int i = 0; i < q; i++) {
            int a = nextInt();
            int b = nextInt();

            int lca = getAncestor(a,b);

            // distance = (left) + right;
            // left is d[u] - d[lca] and right is d[v] - d[lca]

            out.println(depth[a] + depth[b] - 2*depth[lca]);

        }



        out.flush();
        
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

//    private static void dfs(int u,int par,int d){
//        up[u][0] = par;
//        depth[u] = d;
//        for(int v : adj.get(u)){
//            if(v == par) continue;
//            dfs(v,u,d+1);
//        }
//    }

    static void bfs(int root) {
        int[] q = new int[n + 5]; // array as queue
        int front = 0, rear = 0;

        q[rear++] = root;
        depth[root] = 0;
        up[root][0] = -1;

        while (front < rear) {
            int u = q[front++];

            for (int v : adj.get(u)) {
                if (v != up[u][0]) {
                    depth[v] = depth[u] + 1;
                    up[v][0] = u; // immediate parent
                    q[rear++] = v;
                }
            }
        }
    }

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }


}
