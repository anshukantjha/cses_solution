import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatchingTreeDp {

    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        int n = nextInt();

        List<List<Integer>> adj = new ArrayList<>();

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

        int[] parent = new int[n + 1];
        int[] order = new int[n];
        boolean[] bfsVisited = new boolean[n + 1];

        // manual implementation of queue data structure
        int head = 0;
        int tail = 0;

        // Start BFS
        order[tail++] = 1;
        bfsVisited[1] = true;
        parent[1] = -1;

        while(head < tail) {
            int u = order[head++];
            for(int v : adj.get(u)) {
                if(!bfsVisited[v]) {
                    bfsVisited[v] = true;
                    parent[v] = u;
                    order[tail++] = v;
                }
            }
        }

//        out.println(Arrays.toString(order));
//        out.println(Arrays.toString(parent));

        // 3. DP Calculation (Iterating Backwards: Leaves -> Root)
        // dp[u][0] = Max edges in subtree if u is NOT matched with a child
        // dp[u][1] = Max edges in subtree if u IS matched with a child
        int[][] dp = new int[n + 1][2];

        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];

            // Step A: Calculate dp[u][0]
            // Sum of max(child_0, child_1) for all children
            int sumMax = 0;

            int p = parent[u];

            for (int v : adj.get(u)) {
                if (v == p) continue;
                sumMax += Math.max(dp[v][0], dp[v][1]);
            }

            dp[u][0] = sumMax;

            // Step B: Calculate dp[u][1]
            // Try matching with each child and find the best one
            int maxWithMatch = 0;

            // Note: If 'u' is a leaf, this loop won't run, dp[u][1] stays 0 (correct)
            for (int v : adj.get(u)) {
                if (v == p) continue;

                // The formula:
                // all best sum - remove the chosen child contri ( max(v0 , v1) ) +  contribution as a child with parent above ( dp[v][0] + 1)

                int valWithV = sumMax - Math.max(dp[v][0], dp[v][1]) + dp[v][0] + 1;

                maxWithMatch = Math.max(maxWithMatch, valWithV);
            }

            dp[u][1] = maxWithMatch;
        }

        // Final answer for the root (Node 1)
        // It can either be unmatched or matched, whichever is better

//        out.println(Arrays.deepToString(dp));
        out.println(Math.max(dp[1][0], dp[1][1]));
        out.flush();

    }

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }
}
