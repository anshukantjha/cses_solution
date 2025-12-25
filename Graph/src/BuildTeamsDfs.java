import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BuildTeamsDfs {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static List<List<Integer>> adj;
    static boolean[] visited;
    static int[] color;
    static boolean notPossible;

    public static void main(String[] args) throws IOException {

        int n = nextInt();
        int m = nextInt();

        adj = new ArrayList<>();
        visited = new boolean[n+1];
        color = new int[n+1];

        for (int i = 0; i <= n ; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 1; i <= m ; i++) {
            int a = nextInt();
            int b = nextInt();

            adj.get(a).add(b);
            adj.get(b).add(a);
        }

//        System.out.println(adj);


        for (int i = 1; i <= n ; i++) {
            if(!visited[i]){
                dfs(i,1);
                // if found once it is not posible no need to check furthur
                if(notPossible) break;
            }
        }


//        System.out.println(Arrays.toString(color));
//        System.out.println(Arrays.toString(visited));

        if(notPossible){
            System.out.println("IMPOSSIBLE");
        }else{
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <=n ; i++) {
                sb.append(color[i]).append(" ");
            }

            System.out.println(sb);
        }




    }

    private static void dfs(int u,int currCol){
        visited[u] = true;
        color[u] = currCol;

        for(int v : adj.get(u)){
            if(color[u] == color[v]){
                notPossible = true;
                return;
            }
            if(!visited[v]){
                dfs(v,3-currCol);
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
