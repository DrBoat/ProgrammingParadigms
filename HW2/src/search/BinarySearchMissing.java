package search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchMissing {
    // PRE: i < j => a[i] >= a[j]
    // l < r
    // a is const
    // POST: INV  ∧  l >= r
    //       =>  a[r-1] > x >= a[r]
    //       =>  ^ r < a.length  ?  a[r] == x  => R = r :  a[r] != x => R = -r-1
    static private int iterativeBinarySearch(List<Integer> a, int x, int left, int right) {
        // INV: a[0]...a[l] > x >= a[r]..a[a.size()]
        while (left < right) {
            // INV: ^ l < r
            int M = (left + (right - left) / 2);
            // l <= m < r

            // PRE: ∧  a[m] > x
            // a[l]..a[m] > x >= a[m+1]..a[r]
            // POST: l -> m+1
            if (a.get(M) > x) {
                left = M + 1;
            } else {
                // PRE: ∧  a[m] <= x
                // a[0]..a[m] <= x < a[m+1]..a[a.size()]
                //POST: r -> m
                right = M;
            }
        }
        if (right == a.size() || a.get(right) != x) {
            right = -right - 1;
        }
        return right;
    }

    // PRE: i < j => a[i] >= a[j]
    // l < r
    // POST: INV  ∧  l >= r
    //       =>  a[r-1] > x >= a[r]
    //       =>  ^ r < a.length  ?  a[r] == x  => R = r :  a[r] != x => R = -r-1
    // INV: a[0]..a[l-1] > x >= a[r]..a[a.length]
    // a is const
    static private int recursiveBinarySearch(List<Integer> a, int x, int left, int right) {
        if (left >= right) {
            if (right == a.size() || a.get(right) != x) {
                right = -right - 1;
            }
            return right;
        }
        // INV: ^ l < r
        int M = (left + (right - left) / 2);
        // l <= m < r

        // PRE: ^ a[m] > x
        // POST: l -> m+1
        if (a.get(M) > x) {
            return recursiveBinarySearch(a, x, M + 1, right);
        }
        // PRE: ^ a[m] <= x
        // POST: r -> m
        return recursiveBinarySearch(a, x, left, M);
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            System.out.println("-1");
            return;
        }

        int x = 0;
        List<Integer> a = new ArrayList<>();
        try {
            x = Integer.parseInt(args[0]);
            for (int i = 1; i < args.length; ++i) {
                a.add(Integer.parseInt(args[i]));
            }
        } catch (NumberFormatException e) {
            System.out.println("Incorrect input!");
        }

        int iterativeResult = iterativeBinarySearch(a, x, 0, a.size());
        int recursiveResult = recursiveBinarySearch(a, x, 0, a.size());

        System.out.println((iterativeResult == recursiveResult) ? iterativeResult : "Error!");
    }
}