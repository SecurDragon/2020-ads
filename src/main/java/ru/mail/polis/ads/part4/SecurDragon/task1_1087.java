package ru.mail.polis.ads.part4.SecurDragon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class task1_1087 {
    static class BacktrackNode{
        public int cost;
        public int i1, j1, i2, j2;
        public BacktrackNode(){
            i1 = i2 = j1 = j2 = -1;
        }
        public BacktrackNode(int cost, int i1, int j1, int i2, int j2) {
            this.cost = cost;
            this.i1 = i1;
            this.j1 = j1;
            this.i2 = i2;
            this.j2 = j2;
        }
    }

    private static String findBrackets(char ch){
        if(ch == '(' || ch == ')')
            return "()";
        else
            return "[]";
    }
    private static void resolveBacktrack(BacktrackNode[][] arr, BacktrackNode d, List<Integer> idx){
        boolean stop = false;
        if(d.i1 == d.j1 && d.i1 != -1) {
            idx.add(d.i1);
        }
        else if(d.i1 != d.j1){
            resolveBacktrack(arr, arr[d.i1][d.j1], idx);
        }

        if(d.i2 == d.j2 && d.i2 != -1){
            idx.add(d.i2);
        }
        else if(d.i2 != d.j2 ){
            resolveBacktrack(arr, arr[d.i2][d.j2], idx);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String input = in.next();
        char[] arr = input.toCharArray();
        StringBuilder fixed = new StringBuilder();
        BacktrackNode[][] d = new BacktrackNode[arr.length][arr.length];
        for(int i = 0; i < arr.length; ++i) {
            d[i][i] = new BacktrackNode(1, -1, -1, -1, -1);
        }

        for(int i = 0; i < arr.length; ++i){
            int newi = 0;
            for(int j = i + 1; j < arr.length; ++j){
                int ii = newi, jj = j;
                while(arr[ii] == '(' && arr[jj] == ')' ||
                      arr[ii] == '[' && arr[jj] == ']'){
                    ++ii; --jj;
                }
                if(ii != newi) {
                    if(ii <= jj)
                        d[newi][j] = new BacktrackNode(d[ii][jj].cost, ii, jj, -1, -1);
                    else
                        d[newi][j] = new BacktrackNode(0, -1, -1, -1, -1);
                    ++newi;
                }
                else {
                    int min = Integer.MAX_VALUE;
                    int mink = arr.length;
                    for(int k = newi; k < j; ++k){
                        if(d[newi][k].cost + d[k+1][j].cost < min){
                            min = d[newi][k].cost + d[k+1][j].cost;
                            mink = k;
                        }
                    }
                    d[newi][j] = new BacktrackNode(min, newi, mink, mink + 1, j);
                    ++newi;
                }
            }
        }
        List<Integer> idx = new ArrayList<>();
        resolveBacktrack(d, d[0][arr.length - 1], idx);

        for(int i = 0; i < arr.length; ++i){
            if(idx.contains(i))
                fixed.append(findBrackets(arr[i]));
            else
                fixed.append(arr[i]);
        }
        out.print(fixed.toString());
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
