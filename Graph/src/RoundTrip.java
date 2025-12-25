import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class RoundTrip {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static List<List<Integer>> adj;
    static boolean[] visited;
    static int[] parent;
    static int start;
    static int end;

    public static void main(String[] args) throws IOException {

        int n = nextInt();
        int m = nextInt();

        adj = new ArrayList<>();
        visited = new boolean[n+1];
        parent = new int[n+1];

        for (int i = 0; i <= n ; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 1; i <= m ; i++) {
            int a = nextInt();
            int b = nextInt();

            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        // System.out.println(adj);

       boolean isCycle = false;

        for (int i = 1; i <= n; i++) {
            if(!visited[i]){
                if (dfs(i, -1)) {
                    isCycle = true;
                    break;
                }
            }
        }

//        System.out.println(Arrays.toString(parent));
//
//        System.out.println(start + " st end " + end);
//        System.out.println(Arrays.toString(visited));

        if(!isCycle){
            System.out.println("IMPOSSIBLE");
        }else{


            List<Integer> cycle = new ArrayList<>();
            cycle.add(start);
            int curr = end;

            while(curr != start){
                cycle.add(curr);
                curr = parent[curr];
            }

            cycle.add(start);

            System.out.println(cycle.size());
            for (int x : cycle ) {
                System.out.print(x + " ");
            }
        }



    }

    private static boolean dfs(int u , int par){

        visited[u] = true;
        parent[u] = par;

        for(int v : adj.get(u)){
            if(v == par) continue;

            if(!visited[v]) {
                if (dfs(v, u)) return true;
            } else { // visited
                start = v;
                end = u;
                return true;
            }
        }

        return false;
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
