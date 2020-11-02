package ru.mail.polis.ads.part5.SecurDragon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class task3_264 {

    private static int maxSeq(int[] arr){
        int[] d = new int[arr.length];
        d[0] = 1;
        for(int i = 1; i < arr.length; ++i){
            for(int j = 0; j < i; ++j){
                if(arr[j] != 0 &&
                        arr[i] % arr[j] == 0 && d[j] > d[i])
                    d[i] = d[j];
            }
            d[i]++;
        }

        int max = 0;
        for (int j : d) {
            max = Math.max(max, j);
        }
        return max;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int num = in.nextInt();
        int[] arr = new int[num];
        for(int i = 0; i < num; ++i)
            arr[i] = in.nextInt();

        out.println(maxSeq(arr));
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
