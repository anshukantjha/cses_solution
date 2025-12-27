import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HighScore {

    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(System.out);


    static final long NEGINF = (long) -1e18;
    static List<List<int[]>> adj;


    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int m = nextInt();

        adj = new ArrayList<>();

        long[] score = new long[n+1];
        Arrays.fill(score,NEGINF);
        score[1] = 0;


        for (int i = 0; i <=n ; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m ; i++) {
            int a = nextInt();
            int b = nextInt();
            int x = nextInt();

            adj.get(a).add(new int[]{b,x}); // one-way tunnels
        }


        // now relax the edges n-1 times i.e Bellman Ford Algorithm

        for (int k = 1; k < n ; k++){

            for (int i = 1; i <= n ; i++) {

                // i is the source node
                if(score[i] == NEGINF) continue;

                for (int[] al : adj.get(i)) {
                    int v = al[0];
                    long cost = al[1];

                    long nextScore = score[i] + cost;

                    if (nextScore > score[v]) {
                        score[v] = nextScore;
                    }

                }
            }
        }

        // one more pass to check for cycle i.e arbitrarily unacceptable value

        boolean[] inCycle = new boolean[n+1];

        for (int i = 1; i <= n ; i++) {

            // i is the source node
            if(score[i] == NEGINF) continue;

            for (int[] edge : adj.get(i)) {
                int v = edge[0];
                long cost = edge[1];

                long nextScore = score[i] + cost;

                if (nextScore > score[v]) {
                    inCycle[v] = true; // break sense nhi banata as side wala agar update hoke dusre raaste gya toh miss hojayega
                }

            }
        }

        // propogate cycle affect , if reachable from nth relaxed node then it is also bad

        for (int k = 0; k < n; k++) {
            // first loop to account for furthur update after changing nth neighbour
            for (int i = 1; i <= n ; i++) {
                if(!inCycle[i]) continue;
                for(int[] edge : adj.get(i)){
                    inCycle[edge[0]] = true;
                }
            }
        }





//        out.println(Arrays.toString(score));
//        out.println(Arrays.toString(inCycle));


        if(inCycle[n]){
            out.println(-1);
        }else{
            out.println(score[n]);
        }

        out.flush();
    }


    static int nextInt() throws IOException{
        in.nextToken();
        return (int ) in.nval;
    }



}
