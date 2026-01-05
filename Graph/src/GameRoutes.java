import java.io.*;
import java.util.*;

public class GameRoutes {

    static final int NEGINF = (int) -1e9;
    static final int MOD = 1000000007;
    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(System.out);

    static List<List<Integer>> adj;

    public static void main(String[] args) throws IOException {

        int n = nextInt();
        int m = nextInt();

        adj = new ArrayList<>();

        int[] inDegree = new int[n+1];


        for (int i = 0; i <= n ; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 1; i <= m ; i++) {
            int a = nextInt();
            int b = nextInt();

            adj.get(a).add(b);
            inDegree[b]++;
        }

//         out.println(adj);
//        out.println(Arrays.toString(inDegree));

        Queue<Integer> q = new ArrayDeque<>();
        for(int i=1;i<=n;i++){
            if(inDegree[i] == 0){
                q.add(i);
            }
        }

        long[] ways = new long[n+1];

        ways[1] = 1;

        while (!q.isEmpty()){
            int u = q.poll();

            for(int v : adj.get(u)){
                inDegree[v]--; // remove one edge then add to queue


                ways[v] = (ways[v] + ways[u]) % MOD;

                if(inDegree[v] == 0) {
                    q.add(v);
                }
            }
        }


//            out.println(Arrays.toString(parent));
//            out.println(Arrays.toString(dist));


//        out.println(Arrays.toString(ways));


        out.println(ways[n] % MOD);

        out.flush();


    }


    static int nextInt() throws IOException{
        in.nextToken();
        return (int) in.nval;
    }




}

