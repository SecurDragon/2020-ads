package ru.mail.polis.ads.part2.SecurDragon;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;
import java.util.Random;

public class task4 {
    private static void swap(BigInteger[] arr, int i, int j){
        BigInteger tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    static Random rnd = new Random();
    private static int partition(BigInteger[] arr, int l, int r){
        int p = l + rnd.nextInt(r - l);
        swap(arr, p, l);
        BigInteger pivot = arr[l];
        int i = l;
        for(int j = l + 1; j < r; j++){
            if(arr[j].compareTo(pivot) >= 0){
                swap(arr, ++i, j);
            }
        }
        swap(arr, l, i);
        return i;
    }

    private static BigInteger findStat(BigInteger[] arr, int k){
        int left = 0, right = arr.length;
        while(true){
            int mid = partition(arr, left, right);
            if(mid == k)
                return arr[mid];
            else if(k < mid)
                right = mid;
            else
                left = mid + 1;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int k = in.nextInt();
        String seq = in.nextLine();
        String[] numsStrings = seq.split("\\s+");
        BigInteger[] nums = new BigInteger[numsStrings.length];
        for(int i = 0; i < numsStrings.length; i++)
            nums[i] = new BigInteger(numsStrings[i]);
        out.println(findStat(nums, k - 1));
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

        String nextLine() {
            String line;
            try {
                line = reader.readLine();
                return line;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
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
