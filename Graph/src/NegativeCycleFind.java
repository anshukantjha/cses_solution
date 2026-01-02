import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


class Edge{
    int to;
    int weight;

    public Edge(int to , int weight){
        this.to = to;
        this.weight = weight;
    }
}

public class NegativeCycleFind {

    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(System.out);



    static List<List<Edge>> adj;

    public static void main(String[] args) throws IOException {

        int n = nextInt();
        int m = nextInt();
        
        adj = new ArrayList<>();

        for (int i = 0; i <= n ; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 1; i <= m ; i++) {
            int a = nextInt();
            int b = nextInt();
            int c = nextInt();

            adj.get(a).add(new Edge(b,c));
        }

//        System.out.println(adj);


        int[] from = new int[n+1];
        int start = -1;

        // Bellman Ford Algorithm
        long[] dist = new long[n+1];


        // we need the start of cycle
        for (int k = 1; k <= n; k++) {
            start = -1;
            for (int i = 1; i <= n ; i++) {

                for(Edge e : adj.get(i)){
                    int to = e.to;
                    int weight = e.weight;

                    if (dist[i] + weight < dist[to]) {
                        dist[to] = dist[i] + weight;
                        from[to] = i;
                        start = to;
                    }

                }
            }


        }

//        out.println(Arrays.toString(dist));

//        out.println(Arrays.toString(inCycle));
//        out.println(Arrays.toString(from));
//        out.println(start);


        if(start == -1){

            out.println("NO");
            out.flush();
            return;
        }

        // move the start pointer inside the cycle cuz it maybe outside
        for (int i = 1; i <= n; i++) {
            start = from[start];
        }





//        out.println(start); // 4
        List<Integer> cycle = new ArrayList<>();
        int v = from[start];
        cycle.add(start);

        while (v != start){
            cycle.add(v);
            v = from[v];
        }

        cycle.add(start);
        Collections.reverse(cycle);


        out.println("YES");
        for (int x : cycle) {
            out.print(x + " ");
        }
        out.println();
        out.flush();
    }

    private static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

}
