// Cycle detection in directed graph

import java.io.*;
import java.util.*;

public class RoundTripII {


    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(System.out);



    static List<List<Integer>> adj;
    static boolean[] visited;
    static boolean[] pathVisited;
    static int[] parent; // to create path

    static int origin;

    public static void main(String[] args) throws IOException {

        int n = nextInt();
        int m = nextInt();

        adj = new ArrayList<>();
        visited = new boolean[n+1];
        pathVisited = new boolean[n+1];
        parent = new int[n+1];

        for (int i = 0; i <= n ; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 1; i <= m ; i++) {
            int a = nextInt();
            int b = nextInt();

            adj.get(a).add(b); // a --> b

        }

        boolean isCycle = false;

        for (int i = 1; i <= n ; i++) {
            if(!visited[i]){
                isCycle = dfs(i,0);
                if(isCycle) break;
            }
        }

//        out.println(origin);
//        out.println(Arrays.toString(parent));


//

        if(!isCycle) {
            out.println("IMPOSSIBLE");
        }else{
            List<Integer> cycle = new ArrayList<>();

            int curr = origin;

            do{
                cycle.add(curr);
                curr = parent[curr];
            }while (curr != origin);

            cycle.add(origin);

            out.println(cycle.size());
            for(int i=cycle.size()-1;i>=0;i--){
                out.print(cycle.get(i) + " ");
            }


        }


        out.flush();

    }

    private static boolean dfs(int u,int par){
        visited[u] = true;
        pathVisited[u] = true;
        parent[u] = par;

        for(int v : adj.get(u)){

            if(!visited[v]) {
                if(dfs(v,u)) return true;
            }else if(pathVisited[v]){
                // both vis and pathVis i.e cycle detected
                origin = v;
                parent[v] = u;
                return true;
            }
        }

        pathVisited[u] = false;
        return false;
    }

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }
}
