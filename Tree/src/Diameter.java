import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Diameter {

    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(System.out);

    static int maxD;
    static List<List<Integer>> adj;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        int n = nextInt();

        maxD = 0;
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

//        out.println(adj);
//        out.println("Anshu");
        height(1,-1);
        out.println(maxD);
        out.flush();

    }

    private static int height(int u ,int parent ){
        int maxifirst = 0;
        int maxiSec = 0;

        for(int v : adj.get(u)){
            if(v == parent) continue;

            int a = height(v,u);

            if(a > maxifirst){
                maxiSec = maxifirst;
                maxifirst = a;
            }else{
                maxiSec = Math.max(maxiSec,a);
            }

        }

        maxD = Math.max(maxD,maxifirst + maxiSec);
        return 1 + maxifirst;
    }

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }
}
