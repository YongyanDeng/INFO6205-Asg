package edu.neu.coe.info6205.threesum;

import edu.neu.coe.info6205.util.Stopwatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * Implementation of ThreeSum which follows the brute-force approach of
 * testing every candidate in the solution-space.
 * The array provided in the constructor may be randomly ordered.
 * <p>
 * This algorithm runs in O(N^3) time.
 */
class ThreeSumCubic implements ThreeSum {
    /**
     * Construct a ThreeSumCubic on a.
     * @param a an array.
     */
    public ThreeSumCubic(int[] a) {
        this.a = a;
        length = a.length;
    }

    public Triple[] getTriples() {
        List<Triple> triples = new ArrayList<>();
        for (int i = 0; i < length; i++)
            for (int j = i + 1; j < length; j++) {
                for (int k = j + 1; k < length; k++) {
                    if (a[i] + a[j] + a[k] == 0)
                        triples.add(new Triple(a[i], a[j], a[k]));
                }
            }
        Collections.sort(triples);
        return triples.stream().distinct().toArray(Triple[]::new);
    }

    public static void main(String[] args) {
        int N = 1000, M = 1000, T = 20;
        Supplier<int[]> sup = new Source(N, M).intsSupplier(10);
        int[] a = sup.get();
        ThreeSum target = new ThreeSumCubic(a);

        long time = 0;
        for(int i = 0; i < T; i++) {
            try (Stopwatch sw = new Stopwatch()) {
                Triple[] triplesCubic = target.getTriples();
                time += sw.lap();
            }
        }
        System.out.println(time);
        System.out.println((double)time / T);
    }

    private final int[] a;
    private final int length;
}
