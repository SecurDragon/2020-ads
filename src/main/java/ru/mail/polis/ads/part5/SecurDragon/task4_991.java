package ru.mail.polis.ads.part5.SecurDragon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class task4_991 {

    private static boolean matches(String str, String pattern){

        boolean[][] d = new boolean[pattern.length() + 1][str.length() + 1];
        d[0][0] = true;
        for(int i = 1; i <= pattern.length(); ++i){
            for(int j = 1; j <= str.length(); ++j){
                if(str.charAt(j - 1) == pattern.charAt(i - 1) || pattern.charAt(i - 1) == '?' || str.charAt(j - 1) == '?')
                    d[i][j] = d[i - 1][j - 1];
                if(pattern.charAt(i - 1) == '*' || str.charAt(j - 1) == '*')
                    d[i][j] = d[i-1][j] || d[i][j-1] || d[i - 1][j - 1];
            }
        }

        return d[pattern.length()][str.length()];
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        String pattern = in.reader.readLine();
        String str = in.reader.readLine();

        if(matches(str, pattern))
            out.println("YES");
        else
            out.println("NO");
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

    public static void main(final String[] arg) throws IOException {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
