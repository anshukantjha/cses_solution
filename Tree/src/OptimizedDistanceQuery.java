import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptimizedDistanceQuery {

    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(System.out);


    static final int LOG = 20;
    static int n;
    static int q;
    static int[] depth;
    static int[][] up;

    // storage of graph instead of adj list
    static int[] head;      // Points to the first edge index for a node
    static int[] next;      // Points to the next edge index
    static int[] to;        // Stores the destination node of the edge
    static int edgeCount = 0;


    public static void main(String[] args) throws IOException {

        n = nextInt();
        q = nextInt();

        // 1-based indexing for nodes, so size is N + 1
        head = new int[n + 1];
        Arrays.fill(head, -1); // -1 indicates end of list

        // Size is 2 * N because graph is undirected (2 edges per connection)
        next = new int[2 * n + 2];
        to = new int[2 * n + 2];

        depth = new int[n+1];
        up = new int[n+1][LOG];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(up[i] , -1);
        }

        for (int i = 1; i < n; i++) {
            int a = nextInt();
            int  b = nextInt();

            addEdge(a, b);
            addEdge(b, a);

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

    static void addEdge(int u, int v) {
        to[edgeCount] = v;
        next[edgeCount] = head[u]; // Point to current head
        head[u] = edgeCount++;     // Update head to new edge
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

            for (int e = head[u]; e != -1; e = next[e]) {
                int v = to[e];
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
