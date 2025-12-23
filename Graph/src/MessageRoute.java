// BFS makes sense to reach from A to B to give optimal answer

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MessageRoute {


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static List<List<Integer>> adj;
    static boolean[] visited;
    static int[] path;
    static boolean isFound;

    public static void main(String[] args) throws IOException {

        int n = nextInt();
        int m = nextInt();

        adj = new ArrayList<>();
        visited = new boolean[n+1];
        path = new int[n+1];

        for (int i = 0; i <= n ; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 1; i <= m ; i++) {
            int a = nextInt();
            int b = nextInt();

            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        System.out.println(adj);

        // bfs logic below
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visited[1] = true;

        while (!q.isEmpty()){
            int curr = q.poll();

            for(int v : adj.get(curr)){
                if(!visited[v] ){
                    visited[v] = true;
                    q.add(v);
                    path[v] = curr; // kon kaha se aaya tha

                    if(v == n){
                        isFound = true;
                        break;
                    }
                }
            }
        }

//        System.out.println(Arrays.toString(path));

        int temp = n;


        List<Integer> ans = new ArrayList<>();

        if(isFound){
            int cnt = 0;
            while(temp != 1){
                ans.add(temp);
                cnt++;
                temp = path[temp];
            }
            System.out.println(cnt+1);

            ans.add(1);
            for(int i=ans.size()-1;i>=0;i--){
                System.out.print(ans.get(i) + " ");
            }

        }else{
            System.out.println("IMPOSSIBLE");
        }




    }

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }




}
