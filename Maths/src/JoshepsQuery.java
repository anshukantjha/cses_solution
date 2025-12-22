import java.io.*;

public class JoshepsQuery {


    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {

        int q = nextInt();

        for (int i = 0; i < q; i++) {
            int n = nextInt();
            int k = nextInt();

            out.println(solve(n,k));


        }

        out.flush();
    }

    private static int solve(int n, int k){
        if(n == 1) return 1;

        if(2*k <= n) return 2*k;

        if(n% 2 == 0){
            return 2 * solve(n/2,k-n/2) - 1 ;
        }else{
            if(n/2 + 1 == k) return 1; // half case
            return 2 * solve(n/2,k-(n/2+1)) + 1;
        }
    }

    static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }


}
