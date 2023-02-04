package edu.neu.coe.info6205.threesum;

import edu.neu.coe.info6205.util.Stopwatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Implementation of ThreeSum which follows the approach of dividing the solution-space into
 * N sub-spaces where each sub-space corresponds to a fixed value for the middle index of the three values.
 * Each sub-space is then solved by expanding the scope of the other two indices outwards from the starting point.
 * Since each sub-space can be solved in O(N) time, the overall complexity is O(N^2).
 * <p>
 * The array provided in the constructor MUST be ordered.
 */
public class ThreeSumQuadraticWithCalipers implements ThreeSum {
    /**
     * Construct a ThreeSumQuadratic on a.
     *
     * @param a a sorted array.
     */
    public ThreeSumQuadraticWithCalipers(int[] a) {
        this.a = a;
        length = a.length;
    }

    /**
     * Get an array or Triple containing all of those triples for which sum is zero.
     *
     * @return a Triple[].
     */
    public Triple[] getTriples() {
        List<Triple> triples = new ArrayList<>();
        Collections.sort(triples); // ???
        for (int i = 0; i < length - 2; i++)
            triples.addAll(calipers(a, i, Triple::sum));
        return triples.stream().distinct().toArray(Triple[]::new);
    }

    /**
     * Get a set of candidate Triples such that the first index is the given value i.
     * Any candidate triple is added to the result if it yields zero when passed into function.
     *
     * @param a        a sorted array of ints.
     * @param i        the index of the first element of resulting triples.
     * @param function a function which takes a triple and returns a value which will be compared with zero.
     * @return a List of Triples.
     */
    public static List<Triple> calipers(int[] a, int i, Function<Triple, Integer> function) {
        List<Triple> triples = new ArrayList<>();
        // FIXME : use function to qualify triples and to navigate otherwise.
        int l = i + 1, r = a.length - 1;
        while(l < r) {
            Triple t = new Triple(a[i], a[l], a[r]);
            if(function.apply(t) < 0) l++;
            else if(function.apply(t) > 0) r--;
            else {
                triples.add(t);
                l++;
                r--;
            }
        }
        // END 
        return triples;
    }

    public static void main(String[] args) {
        int N = 16000, M = 16000, T = 2;
        Supplier<int[]> sup = new Source(N, M).intsSupplier(10);
        int[] a = sup.get();
        ThreeSum target = new ThreeSumQuadraticWithCalipers(a);

        long time = 0;
        for(int i = 0; i < T; i++) {
            try(Stopwatch sw = new Stopwatch()) {
                Triple[] triples = target.getTriples();
                time += sw.lap();
            }
        }
        System.out.println(time / T);
    }

    private final int[] a;
    private final int length;
}
