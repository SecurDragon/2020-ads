package ru.mail.polis.ads.part5.SecurDragon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class task5_2169 {

    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void reverse(int[] arr, int from){
        int n = arr.length - from;
        for(int i = 0; i < n / 2; ++i){
            swap(arr, i + from, n - i - 1 + from);
        }
    }

    private static int[] nextPermutation(int[] prevPermutation){
        int[] next = Arrays.copyOf(prevPermutation, prevPermutation.length);
        int j, l;
        for(j = next.length - 2; j >= 0; --j){
            if(next[j] < next[j+1]) break;
        }
        if(j == -1) return null;
        for(l = next.length - 1; l > j; --l) {
            if (next[l] > next[j]) break;
        }
        swap(next, j, l);
        reverse(next, j + 1);
        return next;
    }

    private static void print(PrintWriter out, int[] arr){
        for(int i : arr){
            out.print(i + " ");
        }
        out.println();
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int num = in.nextInt();
        int[] perm = new int[num];
        Arrays.setAll(perm, idx -> 1 + idx);
        do{
            print(out, perm);
            perm = nextPermutation(perm);
        }while(perm != null);
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
