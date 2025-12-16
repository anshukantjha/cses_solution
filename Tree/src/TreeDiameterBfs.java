import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//One easy to implement solution is using 2 Breadth First Searches (BFS). Start a BFS with a random node and store the last node
// encountered before search ends. This last node will definitely be one of the ends of the diameter (Why?). Now run a second BFS
// from this node and you will end on the other end of the diameter.

public class TreeDiameterBfs {

    static class Pair {
        int first;
        int second;

        public Pair(int f,int s){
            this.first = f;
            this.second = s;
        }
    }

    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(System.out);

    static int maxD;
    static List<List<Integer>> adj;
    static int[] parent;
    static int n;

    public static void main(String[] args) throws IOException {
        n = nextInt();

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
//
//        out.println(adj);
//        out.println("Alpha");

        Pair end1 = bfs(1); // this end1 will be end of diameter sure
        Pair end2 = bfs(end1.first);  // from that end1 , bfs() will get us last from this which will be length of diameter

        out.println(end2.second);

        out.flush();

    }


    private static Pair bfs(int src){

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(src,0));
        boolean[] vis = new boolean[n+1];

        Pair u = null;

        while(!q.isEmpty()){
            u = q.peek();
            vis[u.first] = true;
            q.poll();

            for(int v : adj.get(u.first) ){
                if(!vis[v]){
                    vis[v] = true;
                    q.add(new Pair(v,u.second+1));
                }
            }
        }

        return u;

    }


    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }
}
