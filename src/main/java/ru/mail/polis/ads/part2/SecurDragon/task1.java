package ru.mail.polis.ads.part2.SecurDragon;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class task1 {

    private static void merge(int[] a, int[] l, int[] r, int left, int right){
        int i = 0, j = 0, k = 0;
        while(i < left && j < right){
            if(l[i] <= r[j])
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

    private static void mergeSort(int[] array, int size){
        if(size < 2)
            return;
        int mid = size / 2;
        int[] l = new int[mid];
        int[] r = new int[size - mid];

        System.arraycopy(array, 0, l, 0, mid);
        System.arraycopy(array, mid, r, 0, size - mid);

        mergeSort(l, mid);
        mergeSort(r, size - mid);

        merge(array, l, r, mid, size - mid);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int sizeOfArray = in.nextInt();
        int[] arrayToSort = new int[sizeOfArray];
        for(int i = 0; i < sizeOfArray; ++i){
            arrayToSort[i] = in.nextInt();
        }

        mergeSort(arrayToSort, sizeOfArray);

        for(int a : arrayToSort){
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

