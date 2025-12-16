import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FerrisWhell {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int n = nextInt();

        long x = nextLong();


        long[] weights = new long[n];

        for (int i = 0; i < n ; i++) {
            weights[i] = nextLong();
        }

        long[] arr = mergeSort(weights);


        int i=0;
        int max = n-1;
        long ans = 0;

        while (i<=max){
            if(i != max && arr[i] + arr[max] <= x){
                // we can take both
                ans++;
                i++;
                max--;
            }else{
                ans++;
                max--;
            }



        }

        System.out.println(ans);

    }

    private static long[] mergeSort(long[] arr){
        if(arr.length == 1) return arr;

       int  mid = arr.length/2;

       long[] left = mergeSort(Arrays.copyOfRange(arr,0,mid));
       long[] right = mergeSort(Arrays.copyOfRange(arr,mid,arr.length));

       return merge(left,right);
    }

    private static long[] merge(long[] first , long[] second){
        long[] mix = new long[first.length + second.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i<first.length && j< second.length){
           if(first[i] < second[j]){
               mix[k] = first[i];
               i++;
           }else{
               mix[k] = second[j];
               j++;
           }
           k++;
        }
        // maybe few left now

        while(i<first.length){
            mix[k++] = first[i++];
        }
        while(j<second.length){
            mix[k++] = second[j++];
        }

        return mix;
    }

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    static long nextLong() throws IOException {
        return Long.parseLong(next());
    }

}

