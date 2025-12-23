import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BuildRoads {


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static List<List<Integer>> adj;
    static boolean[] visited;


    public static void main(String[] args) throws IOException {

        int n = nextInt();
        int m = nextInt();

        adj = new ArrayList<>();
        visited = new boolean[n+1];

        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
             int a = nextInt();
             int b = nextInt();
             adj.get(a).add(b);
             adj.get(b).add(a);
        }

//        System.out.println(adj);



        int ans = 0;
        // stringbuilder will not work as 12 will look like 1 and 2 sepereately without delimeter
        List<Integer> bridges = new ArrayList<>();

        for (int i = 1; i <= n ; i++) {
            if(!visited[i]){
                ans++;
                dfs(i);
                bridges.add(i);
            }
        }

        System.out.println(ans-1);
//        System.out.println(sb);

        for (int i = 0; i < bridges.size() - 1; i++) {
            System.out.println(bridges.get(i) + " " + bridges.get(i+1));
        }

    }

    private static void dfs(int i){

        visited[i] = true;

        for(int v : adj.get(i)){
            if(!visited[v]){
                dfs(v);
            }
        }

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
