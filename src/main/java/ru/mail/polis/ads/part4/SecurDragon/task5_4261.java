package ru.mail.polis.ads.part4.SecurDragon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class task5_4261 {
    private static int countInv(int[] a){
        if(a.length == 1)
            return 0;

        int mid = (a.length + 1) / 2;
        int[] l = Arrays.copyOfRange(a, 0, mid);
        int[] r = Arrays.copyOfRange(a, mid, a.length);
        return countInv(l) + countInv(r) + merge(a, l, r);
    }

    private static int merge(int[] a, int[] l, int[] r){
        int i = 0, j = 0, k = 0;
        int cnt = 0;
        while(i < l.length && j < r.length){
            if(l[i] <= r[j])
                a[k++] = l[i++];
            else {
                a[k++] = r[j++];
                cnt += l.length - i;
            }
        }
        while(i < l.length){
            a[k++] = l[i++];
        }
        while(j < r.length){
            a[k++] = r[j++];
        }
        return cnt;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int num = in.nextInt();
        int[] arr = new int[num];
        for(int i = 0; i < num; ++i)
            arr[i] = in.nextInt();

        out.print(countInv(arr));
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
