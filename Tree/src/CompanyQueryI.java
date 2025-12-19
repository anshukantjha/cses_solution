//O(n^2)

import java.io.*;
import java.util.*;

public class CompanyQueryI {


    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(System.out);

    static int[] parent;
    static int n;
    static int q;

    public static void main(String[] args) throws IOException {

        n = nextInt();
        q = nextInt();
        parent = new int[n+1];

        int a;
        for (int i = 2; i <= n ; i++) {
            a = nextInt();
            parent[i] = a;
        }

        parent[1] = -1;

        for (int i = 0; i < q ; i++) {
            int x = nextInt();
            int k = nextInt();

            int ans = parent[x];
            while (k-- > 0){
                if(x == -1) break;
                ans = parent[x];
                x = ans;
            }

            out.println(ans);
        }


        out.flush();
    }

//    private static void getParent(int root){
//        Queue<Integer> q = new ArrayDeque<>();
//        boolean[] vis = new boolean[n+1];
//
//        q.add(root);
//        vis[root] = true;
//        parent[root] = -1;
//
//        while (!q.isEmpty()){
//            int u = q.poll();
//
//            for(int v : adj.get(u)){
//                if(!vis[v]){
//                    vis[v] = true;
//                    parent[v] = u;
//                    q.add(v);
//                }
//            }
//        }
//
//    }

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

}
