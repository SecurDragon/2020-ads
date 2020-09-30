package ru.mail.polis.ads.part1.SecurDragon;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class task5{
    private static void solve(final FastScanner in, final PrintWriter out) {
        ArrayList<Integer> queue = new ArrayList<Integer>();
        while(true){
            String cmd = in.next();
            switch(cmd){
                case "push":{
                    int pushme = in.nextInt();
                    queue.add(0,pushme);
                    out.println("ok");
                    break;
                }
                case "pop":{
                    if(queue.size() == 0){
                        out.println("error");
                        continue;
                    }
                    int last = queue.get(queue.size() - 1);
                    queue.remove(queue.size() - 1);
                    out.println(last);
                    break;
                }
                case "front":{
                    if(queue.size() == 0){
                        out.println("error");
                        continue;
                    }
                    int last = queue.get(queue.size() - 1);
                    out.println(last);
                    break;
                }
                case "size":{
                    out.println(queue.size());
                    break;
                }
                case "clear":{
                    queue.clear();
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
