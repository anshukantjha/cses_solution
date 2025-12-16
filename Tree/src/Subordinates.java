import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Subordinates {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st ;

    static ArrayList<Integer>[] adj;
    static int[] ans ;

    public static void main(String[] args) throws IOException {
        int n = nextInt();

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }


        for (int employee = 2; employee <= n; employee++) {
            int boss = nextInt();
            adj[boss].add(employee);
        }


//        System.out.println(Arrays.toString(adj));

        ans = new int[n+1];
        dfs(1);


        for (int i = 1; i <= n; i++) {
            System.out.print(ans[i] - 1 + " ");
        }

    }

    static void dfs(int node) {
        ans[node] = 1; // cuz 0 wont add up

        for (int child : adj[node]) {
            dfs(child); // pehle child ko check
            ans[node] += ans[child];
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