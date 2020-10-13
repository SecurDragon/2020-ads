package ru.mail.polis.ads.part3.SecurDragon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class task2_4039 {

    static void swim(List<Integer> heap, int k){
        while (k > 1 && heap.get(k) > heap.get(k / 2)){
            Collections.swap(heap, k, k / 2);
            k /= 2;
        }
    }

    static void sink(List<Integer> heap, int k){
        while(2*k <= heap.size() - 1){
            int j = 2 * k;
            if( j < heap.size() - 1 && heap.get(j) < heap.get(j + 1)) j++;
            if(heap.get(k) > heap.get(j)) break;
            Collections.swap(heap, k, j);
            k = j;
        }
    }

    static void insert(List<Integer> heap, int v){
        heap.add(v);
        swim(heap, heap.size() - 1);
    }

    static int delMax(List<Integer> heap){
        int max = heap.get(1);
        Collections.swap(heap, 1, heap.size() - 1);
        heap.remove(heap.size() - 1);
        sink(heap, 1);
        return max;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        List<Integer> heap= new ArrayList<>();
        heap.add(0);

        int cmdNum = in.nextInt();
        for(int i = 0; i < cmdNum; ++i){
            int cmd = in.nextInt();
            if(cmd == 1) {
                out.print(delMax(heap));
                if(i < cmdNum - 1) out.println();
            }
            else
                insert(heap, in.nextInt());
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
