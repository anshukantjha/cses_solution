import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class CourseScheduler {

    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(System.out);

    static List<List<Integer>> adj;
    static boolean[] visited;
    static boolean[] pathVisited;
    static List<Integer> topo;

    public static void main(String[] args) throws IOException {

        int n = nextInt();
        int m = nextInt();

        adj = new ArrayList<>();
        topo = new ArrayList<>();
        visited = new boolean[n+1];
        pathVisited = new boolean[n+1];

        for (int i = 0; i <= n ; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 1; i <= m ; i++) {
            int a = nextInt();
            int b = nextInt();

            adj.get(a).add(b);
        }

        // System.out.println(adj);

        for (int i = 1; i <= n; i++) {
            if(!visited[i]){
                if (dfs(i)) {
                    out.println("IMPOSSIBLE");
                    out.flush();
                    return;
                }
            }
        }


//        System.out.println(Arrays.toString(visited));

        // reached here so it must not have cycle
        for (int i = topo.size()-1; i >=0 ; i--) {
            out.print(topo.get(i) + " ");
        }

        out.flush();


    }

    private static boolean dfs(int u ){

        visited[u] = true;
        pathVisited[u] = true;

        for(int v : adj.get(u)){

            if(!visited[v]) {
                if (dfs(v)) return true;
            } else if(pathVisited[v]){ // visited
                return true;
            }
        }

        pathVisited[u] = false; // backtrack then empty again
        topo.add(u);
        return false;
    }

    static int nextInt() throws IOException{
        in.nextToken();
        return (int) in.nval;
    }




}
