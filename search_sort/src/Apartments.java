import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Apartments {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int n = nextInt();
        int m = nextInt();
        long k = nextLong();

        long[] size = new  long[n];
        for (int i = 0; i < n; i++) {
            size[i] = nextLong();
        }

        long[] apart = new  long[m];

        for (int i = 0; i < m; i++) {
           apart[i] = nextLong();
        }

        size = mergeSort(size);
        apart = mergeSort(apart);


        int cnt = 0;

        int j = 0;
        int i =0;

        while(i<n && j<m){
            long low = size[i] - k;
            long high = size[i] + k;

            if(apart[j] < low){
                j++;
            }else if(apart[j] > high){
                i++;
            }else{
                i++;
                j++;
                cnt++;
            }
        }

        System.out.println(cnt);


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

