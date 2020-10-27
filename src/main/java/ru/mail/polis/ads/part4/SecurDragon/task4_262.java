package ru.mail.polis.ads.part4.SecurDragon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class task4_262 {
    static int max(int[] arr, int l, int r){
        int max = Integer.MIN_VALUE;
        for(int i = l; i < r; i++){
            max = Math.max(max, arr[i]);
        }
        return max;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] stairsCosts = new int[n + 2];
        for(int i = 1; i < n + 1; ++i)
            stairsCosts[i] = in.nextInt();

        int maxSteps = in.nextInt();

        int[] d = new int[n + 2];
        for(int i = 1; i < n + 2; ++i){
            d[i] = max(d, Math.max(0, i - maxSteps), i) + stairsCosts[i];
        }

        out.println(d[n+1]);
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