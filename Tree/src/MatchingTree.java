import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class MatchingTree {

    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static List<List<Integer>> adj;
    static int ans;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new MatchingTree().solve(); // Call a new solve method
                } catch (Exception e) {
                    System.out.println(Arrays.toString(e.getStackTrace()));
                }
            }
        }, "1", 1 << 26).start();
    }

    public void solve() throws IOException{
        int n = nextInt();
        adj = new ArrayList<>();


        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            // n-1 edges
            int a = nextInt();
            int b = nextInt();

            adj.get(a).add(b);
            adj.get(b).add(a);
        }


//        System.out.println(adj);

        visited = new boolean[n+1];
        ans = 0;

        dfs(1,-1);

        System.out.println(ans);
    }

    private static void dfs(int curr,int parent){

        for(int neigh:adj.get(curr)){
            if(neigh == parent ) continue;

            dfs(neigh,curr);

        }

        for(int neigh:adj.get(curr)){

            if(neigh == parent) continue;

            if(!visited[curr] && !visited[neigh]){
                visited[curr] = true;
                visited[neigh] = true;
                ans++;
                break;
            }
        }
    }

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

}
