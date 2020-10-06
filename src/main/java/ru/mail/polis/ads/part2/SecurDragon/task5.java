package ru.mail.polis.ads.part2.SecurDragon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class task5 {
    private static void merge(int[][] a, int[][] l, int[][] r, int left, int right){
        int i = 0, j = 0, k = 0;
        while(i < left && j < right){
            if(l[i][0] <= r[j][0])
                a[k++] = l[i++];
            else
                a[k++] = r[j++];
        }
        while(i < left){
            a[k++] = l[i++];
        }
        while(j < right){
            a[k++] = r[j++];
        }
    }

    private static void mergeSort(int[][] array, int size){
        if(size < 2)
            return;
        int mid = size / 2;
        int[][] l = new int[mid][2];
        int[][] r = new int[size - mid][2];

        System.arraycopy(array, 0, l, 0, mid);
        System.arraycopy(array, mid, r, 0, size - mid);

        mergeSort(l, mid);
        mergeSort(r, size - mid);

        merge(array, l, r, mid, size - mid);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
    int sizeOfArray = in.nextInt();
    int[][] array = new int[sizeOfArray][2];
    for(int i = 0; i < sizeOfArray; ++i) {
        array[i][0] = in.nextInt();
        array[i][1] = in.nextInt();
    }

    mergeSort(array, sizeOfArray);
    for(int i = 0; i < sizeOfArray; i++)
        out.format("%d %d\n", array[i][0], array[i][1]);
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
