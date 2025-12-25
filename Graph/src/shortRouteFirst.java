// This is ditto Dijkstra's Algorithm

import java.io.*;
import java.util.*;

public class shortRouteFirst {

    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(System.out);


    static final long MAX = Long.MAX_VALUE;
    static List<List<int[]>> adj;
    static long[] dist ;
    static boolean[] visited;


    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int m = nextInt();

        adj = new ArrayList<>();
        dist = new long[n+1];
        visited = new boolean[n+1];
        Arrays.fill(dist,MAX);

        for (int i = 0; i <=n ; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m ; i++) {
            int a = nextInt();
            int b = nextInt();
            int c = nextInt();

            adj.get(a).add(new int[]{b,c});
        }


        // using priority Queue to get min dist
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));

        pq.add(new long[]{1,0}); // node,dist
        dist[1] = 0;

        while (!pq.isEmpty()){
            long[] curr = pq.poll();

            int u = (int) curr[0]; // src node


            if(visited[u]) continue;
            visited[u] = true;

            for(int[] al : adj.get(u)){

                int v = al[0];
                long cost = al[1];

                if(dist[u] + cost < dist[v]){
                    dist[v] = dist[u] + cost;
                    pq.add(new long[] {v,dist[v] });
                }


            }
        }

        for (int i = 1; i <= n ; i++) {
            out.print(dist[i] + " ");
        }


        out.flush();
    }

    static int nextInt() throws IOException{
        in.nextToken();
        return (int ) in.nval;
    }



}
