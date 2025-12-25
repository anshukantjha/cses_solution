// Multi Source shortest Path using Dijkstra n * O(n^2) => O(n^3)

import java.io.*;
import java.util.*;

public class MultiShortRoute {

    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(System.out);


    static final long MAX = Long.MAX_VALUE;
    static List<List<int[]>> adj;
    static int n;
    

    public static void main(String[] args) throws IOException {
        n = nextInt();
        int m = nextInt();
        int q = nextInt();

        adj = new ArrayList<>();
        

        for (int i = 0; i <=n ; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m ; i++) {
            int a = nextInt();
            int b = nextInt();
            int c = nextInt();

            adj.get(a).add(new int[]{b,c});
            adj.get(b).add(new int[]{a,c});
        }

        long[][] allDist = new long[n+1][n+1];
        
        for (int i = 1; i <= n ; i++) {
            allDist[i] = bfs(i);
        }


        for (int i = 0; i < q; i++) {
            int a = nextInt();
            int b = nextInt();

            long res = allDist[a][b];
            if(res < Long.MAX_VALUE){
                out.println(res);
            }else{
                out.println(-1);
            }
        }

        out.flush();
    }
    
    // dijkstra's algo to find single source shortest path;
    private static long[] bfs(int i){

        long[] dist = new long[n+1];
        boolean[] visited = new boolean[n+1];
        Arrays.fill(dist,MAX);
        
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));

        pq.add(new long[]{i,0}); // node,dist
        dist[i] = 0;

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
        
        return dist;
    }

    static int nextInt() throws IOException{
        in.nextToken();
        return (int ) in.nval;
    }



}
