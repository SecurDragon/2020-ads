package ru.mail.polis.ads.part1.SecurDragon;

import java.io.*;
import java.util.StringTokenizer;

public class task3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String bracesStr = in.next();
        char[] braces = bracesStr.toCharArray();
        int bracesWithoutPair = 0;
        for(char ch : braces){
            if(ch == '(')
                ++bracesWithoutPair;
            else{
                if(bracesWithoutPair == 0){
                    bracesWithoutPair = 1;
                    break;
                }
                --bracesWithoutPair;
            }
        }
        if(bracesWithoutPair == 0)
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
