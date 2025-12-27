import java.io.*;
import java.util.*;

public class FlightDiscount {

    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(System.out);


    static final long MAX = Long.MAX_VALUE;
    static List<List<int[]>> adj;
    static List<List<int[]>> revAdj;
    static int n;
    static int m;


    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();

        adj = new ArrayList<>();
        revAdj = new ArrayList<>();

        for (int i = 0; i <=n ; i++) {
            adj.add(new ArrayList<>());
            revAdj.add(new ArrayList<>());
        }

        for (int i = 0; i < m ; i++) {
            int a = nextInt();
            int b = nextInt();
            int c = nextInt();

            adj.get(a).add(new int[]{b,c});
            revAdj.get(b).add(new int[] {a,c});
        }



        long[] f1 = dijkstra(1);
        long[] fn = reverseDijkstra(n);

//        System.out.println(Arrays.toString(f1));
//        System.out.println(Arrays.toString(fn));

        long ans = f1[n];

        for (int u = 0;  u <= n ;u++) {
            for(int[] e : adj.get(u)){
                int v = e[0];
                int weight = e[1];

                if (f1[u] != MAX && fn[v] != MAX) {
                    // 1 to u + v to n + u to v ==> 1 to n
                    ans = Math.min(ans, f1[u] + fn[v] + Math.floorDiv(weight,2));
                }
            }
        }

        System.out.println(ans);


        out.flush();
    }

    private static long[] dijkstra(int i){

        long[] dist   = new long[n+1];
        boolean[] visited = new boolean[n+1];
        Arrays.fill(dist,MAX);


        // using priority Queue to get min dist
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

    private static long[] reverseDijkstra(int i){

        long[] dist   = new long[n+1];
        boolean[] visited = new boolean[n+1];
        Arrays.fill(dist,MAX);


        // using priority Queue to get min dist
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));

        pq.add(new long[]{i,0}); // node,dist
        dist[i] = 0;

        while (!pq.isEmpty()){
            long[] curr = pq.poll();

            int u = (int) curr[0]; // src node


            if(visited[u]) continue;
            visited[u] = true;

            for(int[] al : revAdj.get(u)){

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
