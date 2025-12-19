import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompanyQueryII {

    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(System.out);

    static final int LOG = 20;
    static int n;
    static int q;
    static int[][] up;
    static List<List<Integer>> adj;
    static int[] depth;

    public static void main(String[] args) throws IOException {

        n = nextInt();
        q = nextInt();
        adj = new ArrayList<>();
        depth = new int[n+1];

        up = new int[n+1][LOG];

        for(int[] row : up){
            adj.add(new ArrayList<>());
            Arrays.fill(row,-1);
        }


        int x;
        for (int i = 2; i <=n ; i++) {
            x = nextInt();
            up[i][0] = x;
            adj.get(x).add(i);
        }

        // to get the depth array
        dfs(1,0);


        // making up(binary jump) 2D array which is
        // 2^jth ancestor of i =  2^j-1 th  of ( 2^j-1 th of i )
        for (int j = 1; j < LOG ; j++) {
            for (int i = 1; i <= n; i++) {
                int prevPar = up[i][j - 1];
                // just filled if prevPar not exist
                if (prevPar != -1) {
                    up[i][j] = up[ prevPar ][j - 1];
                } else {
                    up[i][j] = -1;
                }
            }
        }

//        for (int i = 0; i <= n; i++) {
//            out.println(Arrays.toString(up[i]));
//        }



        // get queries
        for (int i = 0; i < q; i++) {
            int a = nextInt();
            int b = nextInt();

            out.println(getAncestor(a,b));

        }




        out.flush();
    }

    private static void dfs(int u,int d){
        depth[u] = d; // d is depth
        for(int v : adj.get(u)){
            dfs(v,d+1);
        }
    }

    private static int getAncestor(int a ,int b){

        // ensure one of them is depper say a
        if(depth[a] < depth[b]){
            int temp = a;
            a = b;
            b=temp;
        }

        // uplift the deeper node in our case "a"
        for (int j = LOG - 1; j >= 0; j--) {
            if(depth[a] - (1<<j) >= depth[b]){
                a = up[a][j];
            }
        }

        // check if they are same after lifting one
        if(a == b) return a;

        // lifting both of them
        for (int j = LOG - 1; j >= 0; j--) {
            if (up[a][j] != -1 && up[a][j] != up[b][j]) {
                a = up[a][j];
                b = up[b][j];
            }
        }

        // now both of them will be below LCA

        return up[b][0];



    }

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

}
