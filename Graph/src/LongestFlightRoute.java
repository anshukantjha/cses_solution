// Add dist array to maximize distance in the kahn's Algorithm

import java.io.*;
import java.util.*;

public class LongestFlightRoute {

    static final int NEGINF = (int) -1e9;
        static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        static PrintWriter out = new PrintWriter(System.out);

        static List<List<Integer>> adj;

        public static void main(String[] args) throws IOException {

            int n = nextInt();
            int m = nextInt();

            adj = new ArrayList<>();

            int[] inDegree = new int[n+1];
            int[] dist = new int[n+1];
            int[] parent = new int[n+1];
            Arrays.fill(dist,NEGINF);

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
        out.println(Arrays.toString(inDegree));

            Queue<Integer> q = new ArrayDeque<>();
            for(int i=1;i<=n;i++){
                if(inDegree[i] == 0){
                    q.add(i);
                }
            }

            dist[1] = 0;
            parent[1] = 0;


            while (!q.isEmpty()){
                int u = q.poll();

                for(int v : adj.get(u)){
                    inDegree[v]--; // remove one edge then add to queue

                    if(dist[v] <  dist[u] + 1){
                        dist[v] = 1 + dist[u];
                        parent[v] = u;
                    }

                    if(inDegree[v] == 0) {
                        q.add(v);
                    }
                }
            }


//            out.println(Arrays.toString(parent));
//            out.println(Arrays.toString(dist));



            if(dist[n] < 0){
                out.println("IMPOSSIBLE");

            }else{
                out.println(dist[n]+1); // 1 to add for nodes not edges

                int curr = n;
                List<Integer> ans = new ArrayList<>();
                do{
                    ans.add(curr);
                    curr = parent[curr];
                }while(curr != 1);

                ans.add(1);

                for (int i = ans.size()-1; i >= 0; i--) {
                    out.print(ans.get(i) + " ");
                }
            }

            out.flush();


        }


        static int nextInt() throws IOException{
            in.nextToken();
            return (int) in.nval;
        }




    }

