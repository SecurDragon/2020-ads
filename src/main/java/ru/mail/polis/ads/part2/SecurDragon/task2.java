package ru.mail.polis.ads.part2.SecurDragon;

import java.io.*;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class task2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int sizeOfArray = in.nextInt();
        ArrayList<Integer> array = new ArrayList<>();
        for(int i = 0; i < sizeOfArray; ++i)
            array.add(in.nextInt());

        array.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int comp = o1 % 10 - o2 % 10;
                if(comp != 0) return comp;
                return o1 - o2;
            }
        });

        for(int a : array){
            out.format("%d ", a);
        }
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
