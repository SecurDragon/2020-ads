package ru.mail.polis.ads.part3.SecurDragon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class task5_5149 {

    static boolean isSuitable(int[] coords, int x, int k){
        int cows = 1;
        int last = coords[0];
        for (int coord : coords) {
            if (coord - last >= x) {
                cows++;
                last = coord;
            }
        }
        return cows >= k;
    }

    static int binarySearch(int[] arr, int k){
        int l = -1, r = arr.length;
        while(l < r - 1){
            int med = (l + r) / 2;
            if(isSuitable(arr, arr[med] - arr[0], k))
                l = med;
            else
                r = med;
        }
        return arr[l] - arr[0];
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] coords = new int[n];
        for(int i = 0; i < n; ++i)
            coords[i] = in.nextInt();

        out.print(binarySearch(coords, k));
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
