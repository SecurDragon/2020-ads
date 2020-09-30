package ru.mail.polis.ads.part1.SecurDragon;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class task4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        ArrayList<Integer> stack = new ArrayList<Integer>();
        while(true){
            String cmd = in.next();
                switch(cmd){
                    case "push":{
                        int pushme = in.nextInt();
                        stack.add(0,pushme);
                        out.println("ok");
                        break;
                    }
                    case "pop":{
                        if(stack.size() == 0){
                            out.println("error");
                            continue;
                        }
                        int last = stack.get(0);
                        stack.remove(0);
                        out.println(last);
                        break;
                    }
                    case "back":{
                        if(stack.size() == 0){
                            out.println("error");
                            continue;
                        }
                        int last = stack.get(0);
                        out.println(last);
                        break;
                    }
                    case "size":{
                        out.println(stack.size());
                        break;
                    }
                    case "clear":{
                        stack.clear();
                        out.println("ok");
                        break;
                    }
                    case "exit":{
                        out.println("bye");
                        return;
                    }
                }
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
