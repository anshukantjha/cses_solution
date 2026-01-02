import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class FlightRoutes {

    static class Edge{
        int to;
        long weight;

        public Edge(int to , long cost){
            this.to = to;
            this.weight = cost;
        }
    }

    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(System.out);

    static List<List<Edge>> adj;
    static List<PriorityQueue<Long>> dist; // k length, distance ka pq for every node
    static int k;


    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int m = nextInt();
        k = nextInt();

        adj = new ArrayList<>();
        dist = new ArrayList<>();

        for (int i = 0; i <= n ; i++) {
            adj.add(new ArrayList<>());
            dist.add(new PriorityQueue<>(Comparator.comparingLong(a -> -a)));
        }

        for (int i = 1; i <= m ; i++) {
            int a = nextInt();
            int b = nextInt();
            long c = nextInt();

            adj.get(a).add(new Edge(b,c)); // a --> b having weight c

        }


        // conceptually running this k times
        dijkstra();

//        out.println(dist);

        // one-liner using stream
//        out.println(dist.get(n).stream().sorted().map(String :: valueOf).collect(Collectors.joining(" ")));

        List<Long> ans = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            ans.add(dist.get(n).poll());
        }

        Collections.sort(ans);

        for (int i = 0; i < k ; i++) {
            out.print(ans.get(i) + " ");
        }

        out.flush();

    }

    private static void dijkstra(){
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        pq.add(new long[] { 1,0});
        dist.get(1).add(0L);

        while(!pq.isEmpty()){
            long[] al = pq.poll();
            long u = al[0];
            long cost = al[1]; // cost to reach till 1

            // skip paths having more than top-k path's cost
            if(!dist.get((int) u).contains(cost)) continue; // O(k) operation

            for(Edge ch : adj.get((int) u)){
                int v = ch.to;
                long  wt = ch.weight;


                if(dist.get(v).size() < k ){   // if we add at k-1 we will get until k length;
                    // and it will discover first three path
                    dist.get(v).add(cost + wt);
                    pq.add(new long[] {v,cost + wt});
                    continue;
                }

                long maxi = dist.get(v).peek();

                if(cost + wt < maxi){
                    // use maxHeap to get largest of the seen cost instead of function
                    // remove the max of the list and add newCost
                    dist.get(v).poll();
                    dist.get(v).add(cost + wt);
                    pq.add(new long[] {v, cost + wt});
                }
            }
        }
    }

//    this implementation will give tle as takes k operation
//    private static int getMax(List<Integer> list){
//        int max = 0;
//
//        for (int i = 0; i < list.size(); i++) {
//            if(list.get(i) > list.get(max)){
//                max = i;
//            }
//        }
//
//        return max;
//    }

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }
}
