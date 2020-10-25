package ru.mail.polis.ads.part4.SecurDragon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class task3_1618 {
    private static int lcs(int[] a1, int[] a2, int n1, int n2){
//        if(n1 == -1 || n2 == -1)
//            return 0;
//        if(a1[n1] == a2[n2])
//            return lcs(a1, a2, n1 - 1, n2 - 1) + 1;
//        else
//            return Math.max(lcs(a1, a2, n1 - 1, n2), lcs(a1,a2, n1, n2 - 1));
        int[][] d = new int[n1 + 1][n2 + 1];
        for(int i = 1; i < n1 + 1; ++i){
            for(int j = 1; j < n2 + 1; ++j){
                if(a1[i - 1] == a2[j - 1])
                    d[i][j] = d[i-1][j - 1] + 1;
                else
                    d[i][j] = Math.max(d[i-1][j], d[i][j-1]);
            }
        }
        return d[n1][n2];
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n1 = in.nextInt();
        int[] a1 = new int[n1];
        for(int i = 0; i < n1; ++i)
            a1[i] = in.nextInt();
        
        int n2 = in.nextInt();
        int[] a2 = new int[n2];
        for(int i = 0; i < n2; ++i)
            a2[i] = in.nextInt();

        out.println(lcs(a1, a2, n1, n2));
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
