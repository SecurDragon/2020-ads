package ru.mail.polis.ads.part3.SecurDragon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class task1_3737 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();
        int[] numbers = new int[size + 1];
        for(int i = 1; i <= size; ++i){
            numbers[i] = in.nextInt();
        }

        boolean isHeap = true;
        for(int i = 1; i <= size; i++){
            if(2*i <= size && !(numbers[i] <= numbers[2*i])){
                isHeap = false; break;
            }
            if(2*i + 1 <= size && !(numbers[i] <= numbers[2*i + 1])){
                isHeap = false; break;
            }
        }

        if(isHeap)
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
