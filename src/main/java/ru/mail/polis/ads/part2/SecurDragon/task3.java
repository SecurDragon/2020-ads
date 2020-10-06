package ru.mail.polis.ads.part2.SecurDragon;

import java.io.*;
import java.util.StringTokenizer;

public class task3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int sizeOfArray = in.nextInt();
        int[] array = new int[sizeOfArray];
        for(int i = 0; i < sizeOfArray; ++i)
            array[i] = in.nextInt();

        int swapCounter = 0;
        for(int i = 0; i < sizeOfArray; ++i){
            for(int j = 0; j < sizeOfArray - 1; ++j){
                if(array[j] > array[j + 1]) {
                    ++swapCounter;
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
        }
        out.println(swapCounter);
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
