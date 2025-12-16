import java.io.*;
import java.util.*;


//For each node, we want to calculate maximum distance to another node. Previously we saw that that if we start a BFS from any
// node, we end up on either of the diametric end. We can use this fact to efficiently compute the answer. Let's calculate
// distances of each node from both the ends of the diameter. Then maximum distance of each node can be calculated as:

//max_distance[u] = max(distance_from_diametric_end1[u], distance_from_diametric_end2[u])

public class TreeDistanceBfs {

    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(System.out);

    static List<List<Integer>> adj;
    static int[] ans;
    static int n;

    public static void main(String[] args) throws IOException {
        n = nextInt();

        adj = new ArrayList<>();
        ans = new int[n+1];

        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        int a,b;
        for (int i = 1; i < n; i++) {
            // n-1 edges
             a = nextInt();
             b = nextInt();

            adj.get(a).add(b);
            adj.get(b).add(a);
        }

//        out.println(adj);
//        out.println("Anshu");


        int end1 = bfs(1,false);
//        out.println(Arrays.toString(ans));
        int end2 = bfs(end1,true);
//        out.println(Arrays.toString(ans));
        bfs(end2,true);
//        out.println(Arrays.toString(ans));


        for(int i=1;i<ans.length;i++){
            out.print(ans[i] + " ");
        }

        out.flush();

    }

    private static int bfs(int src,boolean update){
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        int[] depth = new int[n+1];
        Arrays.fill(depth , -1);
        depth[src] = 0;
        if(update) ans[src] = Math.max(ans[src],depth[src]);

        int top = 0;

        while(!q.isEmpty()){
            top = q.poll();

            for(int v : adj.get(top) ){
                if(depth[v] == -1){ // obviously this will avoid parent
                    q.add(v);
                    depth[v] = 1 + depth[top];
                    if(update) ans[v] = Math.max(ans[v], depth[v]);
                }
            }
        }

        return top;
    }

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }


}
