package ru.mail.polis.ads.part4.SecurDragon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class task2_15 {

    static char[] reconstructPath(int[][] d) {
        int x = d.length - 1;
        int y = d[0].length - 1;
        char[] path = new char[x + y];
        int idx = 0;
        while (x > 0 && y > 0) {
            int d1 = d[x - 1][y];
            int d2 = d[x][y - 1];

            if(Math.max(d1, d2) == d1 && d1 != d2){
                path[idx++] = 'F';
                --x;
            }
            else {
                path[idx++] = 'R';
                --y;
            }
        }
        while(x-- > 0) path[idx++] = 'F';
        while(y-- > 0) path[idx++] = 'R';

        return path;
    }

    static char[] maxCostPath(int[][] field) {
        for(int i = 0; i < field.length; ++i){
            for(int j = 0; j < field[0].length; ++j) {
                int n1 = i - 1 >= 0 ? field[i - 1][j] : 0;
                int n2 = j - 1 >= 0 ? field[i][j - 1] : 0;
                field[i][j] += Math.max(n1, n2);
            }
        }
        return reconstructPath(field);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] field = new int[m][n];
        for (int i = m - 1; i >= 0; --i)
            for (int j = 0; j < n; ++j)
                field[i][j] = in.nextInt();

        char[] reversedPath = maxCostPath(field);
        for (int i = reversedPath.length - 1; i >= 0; --i) {
            if (reversedPath[i] == 0) continue;
            out.print(reversedPath[i]);
        }
        out.println();
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
