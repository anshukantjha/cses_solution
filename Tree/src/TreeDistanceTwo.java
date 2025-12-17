import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TreeDistanceTwo {


    static StreamTokenizer in = new StreamTokenizer (new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(System.out);

    static long[] below; // below
    static long[] up; // above i.e parent
    static long[] sub; // subtree is necessary as to remember how many subtree from src
    static List<List<Integer>> adj;


    static int n;

    public static void main(String[] args) throws IOException {
        n = nextInt();

        adj = new ArrayList<>();
        below = new long[n+1];
        up = new long[n+1];
        sub = new long[n+1];

        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        int a,b;
        for (int i = 1; i < n; i++) {
            // n-1 edges
            a = nextInt();
            b = nextInt();

            adj.get(a).add(b);
            adj.get(b).add(a);
        }

//        out.println(adj);
//        out.println("Anshu");


        dfs_in(1,0);
        dfs_out(1,0);

//        System.out.println(Arrays.toString(below));
//        System.out.println(Arrays.toString(up));
//        System.out.println(Arrays.toString(sub));

        for (int i = 1; i <= n; i++) {
            out.print(below[i] + up[i] + " ");
        }

        out.flush();
    }

    private static void dfs_in(int src, int par){
        sub[src] = 1;

        for(int v : adj.get(src)){
            if(v == par) continue;
            dfs_in(v,src);
            sub[src] += sub[v];
            below[src] += below[v] + sub[v];
        }
    }

    private static void dfs_out(int src, int par){

        // do only for par > 0 i.e not for 1 as it will be 0 already
        // in[par] + out[par] + n - sub[src] - ( in[src] + sub[src]);
        if(par > 0 ) up[src] = below[par] + up[par] + n - below[src] - 2*sub[src];

        for(int v : adj.get(src)){
            if(v == par) continue;

            dfs_out(v,src);

        }
    }

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

}
