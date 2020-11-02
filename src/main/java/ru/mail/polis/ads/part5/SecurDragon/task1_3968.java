package ru.mail.polis.ads.part5.SecurDragon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class task1_3968 {

    private static boolean checkFunc(double x, double c){
        return x * x + Math.sqrt(x) < c;
    }

    private static double binarySearch(double c){
        final double step = 0.000001;
        double left = 1.0;
        double right = c;
        while(Math.abs(left - right) > step){
            double mid = (left + right) / 2;
            if(checkFunc(mid, c))
                left = mid;
            else
                right = mid;
        }
        return left;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        double c = Double.parseDouble(in.next());
        out.println(Math.round(binarySearch(c) * 1000000) / 1000000.);
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
