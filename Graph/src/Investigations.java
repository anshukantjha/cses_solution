import java.io.*;
import java.util.*;

public class Investigations {

    static class Edge{
        int to;
        int weight ;

        public Edge(int to ,int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    static final long INF = (long) 1e18;
    static final int MOD = 1000000007;

    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(System.out);

    static List<List<Edge>> adj ;
    static  int n;

    static long[] minFlights;
    static long[] maxFlights;
    static long[] ways;
    static long[] dist;




    public static void main(String[] args) throws IOException {
         n = nextInt();
        int m = nextInt();

        adj = new ArrayList<>();
//        int[] inDegree = new int[n+1];


        for (int i = 0; i <= n ; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 1; i <= m ; i++) {
            int a = nextInt();
            int b = nextInt();
            int c = nextInt();

            adj.get(a).add(new Edge(b,c));
//            inDegree[b]++;
        }

         dist = new long[n+1];
         ways = new long[n+1];
         minFlights = new long[n+1];
         maxFlights = new long[n+1];

         Arrays.fill(minFlights,INF);
        Arrays.fill(dist,INF);

        dist[1] = 0;
        minFlights[1] = 0;
        maxFlights[1] = 0;
        ways[1] = 1;

        PriorityQueue<long[]> q = new PriorityQueue<>(Comparator.comparingLong(a->a[1]));
        q.add(new long[ ]{ 1,0});



        while (!q.isEmpty()){
            long[] al = q.poll();
            int u = (int)al[0];
            long dis = al[1];

            if(dis > dist[u]) continue; // since all are positive weights so just skip


            for(Edge e : adj.get(u)){
                int v = e.to;
                int cost = e.weight;


                if(dist[u] + cost < dist[v] ){
                    dist[v] = dist[u] + cost;
                    ways[v] = ways[u];
                    minFlights[v] = minFlights[u] + 1;
                    maxFlights[v] = maxFlights[u] + 1;
                    q.add(new long[] {v,dist[v]});

                } else if (dist[u] + cost == dist[v]) {
                    // another path
                    ways[v] = (ways[u] + ways[v] ) % MOD;
                    minFlights[v] = Math.min(minFlights[v], minFlights[u] + 1);
                    maxFlights[v] = Math.max(maxFlights[v], maxFlights[u] + 1);
                }



            }
        }


//        System.out.println(Arrays.toString(dist));
//        System.out.println(Arrays.toString(minFlights));
//        System.out.println(Arrays.toString(maxFlights));
//        System.out.println(Arrays.toString(inDegree));
//
        // Well no need for another dfs as it can be countered by storing in dp array



        out.print(dist[n] + " "
                + ways[n] % MOD + " "
                + minFlights[n]+ " "
                +maxFlights[n]);

        out.flush();


    }


    static int nextInt() throws IOException{
        in.nextToken();
        return (int) in.nval;
    }

}
