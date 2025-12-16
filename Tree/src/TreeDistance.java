import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeDistance {

    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(System.out);

    static List<List<Integer>> adj;
    static int[] ans;
    static int[] depth;

    public static void main(String[] args) throws IOException {
        int n = nextInt();

        adj = new ArrayList<>();
        ans = new int[n+1];
        depth = new int[n+1];

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

        out.println(adj);
        out.println("Anshu");
        //Pass :- 1
        height(1,0);

        // Pass :- 2
        solve(1,0,-1);

//        out.println(Arrays.toString(depth));

        for(int i=1;i<ans.length;i++){
            out.print(ans[i] + " ");
        }

        out.flush();

    }

    private static int height(int u ,int parent ){
        int maxi = -1;

        for(int v : adj.get(u)){
            if(v == parent) continue;

            int a = height(v,u);

            maxi = Math.max(maxi,a);

        }

        return depth[u] =  1 + maxi;
    }

    private static void solve(int u ,int parent ,int par_ans){


        ans[u] = Math.max(depth[u],par_ans );

        int maxifirst = -1;
        int maxiSec = -1;

        for(int v : adj.get(u)){
            if(v == parent) continue;

            int a = depth[v];

            if(a > maxifirst){
                maxiSec = maxifirst;
                maxifirst = a;
            }else{
                maxiSec = Math.max(maxiSec,a);
            }

        }

        // Push values down to children TODO:
        for (int v : adj.get(u)) {
            if (v == parent) continue;

            // If v contributes to max1, it must use max2 sibling for its upward path.
            // If v does not contribute to max1, it can use max1 sibling.
            int longest_sibling_path = (depth[v] == maxifirst) ? maxiSec : maxifirst;

            // The path passed to v comes from u.
            // It is 1 + max(path coming into u from u's parent, path from u to best sibling)
            int new_par_ans = 1 + Math.max(par_ans, 1 + longest_sibling_path);

            solve(v, u, new_par_ans);
        }
    }

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }


}
