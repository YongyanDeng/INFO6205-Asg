package edu.neu.coe.info6205.sort.elementary;

import java.util.Collections;
import java.util.Random;
import java.util.Arrays;

import edu.neu.coe.info6205.util.Benchmark_Timer;

public class InsertionSortDriver {
    public static void main(String[] args) {
        int n = 16000, m = 3;
        Integer[] arr = arrayCreate(n);

        // Random Array
        Benchmark_Timer<Integer[]> bm = new Benchmark_Timer<Integer[]>(
                "Random Array",
                null,
                (Integer[] a) -> new InsertionSort().sort(a, true),
                null
        );
        double x = bm.run(arr, m);
        System.out.println("Random Array: " + x + "ms");

        // Ordered Array
        bm = new Benchmark_Timer<Integer[]>(
                "Ordered Array",
                (Integer[] a) -> {
                    Integer[] b = Arrays.copyOf(a, a.length);
                    Arrays.sort(b);
                    return b;
                },
                (Integer[] a) -> new InsertionSort().sort(a, true),
                null
        );
        x = bm.run(arr, m);
        System.out.println("Ordered Array: " + x + "ms");

        // Partially-ordered Array
        bm = new Benchmark_Timer<Integer[]>(
                "Partially-ordered Array",
                (Integer[] a) -> {
                    Integer[] b = Arrays.copyOf(a, n);
                    int l = new Random().nextInt(n);
                    int r = new Random().nextInt(n);
                    Arrays.sort(b, Math.min(l, r), Math.max(l, r));
                    return b;
                },
                (Integer[] b) -> new InsertionSort().sort(b, true),
                null
        );
        x = bm.run(arr, m);
        System.out.println("Partially-ordered Array: " + x + "ms");

        // Reverse-ordered Array
        bm = new Benchmark_Timer<Integer[]>(
                "Reverse-ordered Array",
                (Integer[] a) -> {
                    Integer[] b = Arrays.copyOf(a, n);
                    Arrays.sort(b, Collections.reverseOrder());
                    return b;
                },
                (Integer[] b) -> new InsertionSort().sort(b, true),
                null
        );
        x = bm.run(arr, m);
        System.out.println("Reverse-ordered Array: " + x + "ms");
    }

    private static Integer[] arrayCreate(int n) {
        Random rd = new Random();
        Integer[] res = new Integer[n];
        for(int i = 0; i < n; i++) {
            res[i] = rd.nextInt() % n;
        }
        return res;
    }
}
