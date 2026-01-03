import java.io.*;
import java.util.*;

public class CourseSchedulerBFS {

    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(System.out);

    static List<List<Integer>> adj;
    static List<Integer> topo;

    public static void main(String[] args) throws IOException {

        int n = nextInt();
        int m = nextInt();

        adj = new ArrayList<>();
        topo = new ArrayList<>();

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

        // System.out.println(adj);
//        out.println(Arrays.toString(inDegree));

        Queue<Integer> q = new ArrayDeque<>();
        for(int i=1;i<=n;i++){
            if(inDegree[i] == 0){
                q.add(i);
            }
        }


        while (!q.isEmpty()){
            int u = q.poll();
            topo.add(u);

            for(int v : adj.get(u)){
                inDegree[v]--; // remove one edge then add to queue
                if(inDegree[v] == 0) {
                    q.add(v);
                }
            }
        }


//        out.println(topo);

        if(topo.size() < n){
            // cycle is there
            out.println("IMPOSSIBLE");
        }else{
            for (int it : topo) {
                out.print(it + " ");
            }
        }

        out.flush();


    }


    static int nextInt() throws IOException{
        in.nextToken();
        return (int) in.nval;
    }




}
