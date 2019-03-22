package search;

public class BinarySearch {
    // pre: n >= 0 && x >= 0 && forall i: i >= 0 && i < a.length - 1: a[i] >= a[i + 1]
    // post: r == min i: a[i] <= x
    public static int binSearchIt(int[] a, int x, int n) {
        //pre: b == - 1
        //post: l == b
        int l = -1;
        //pre: b == n
        //post: r == b
        int r = n;
        int m;
        //pre: l == -1 ^ r == n
        //post: r == min i: a[i] <= x
        while (l < r - 1) {
            //inv: l < r - 1
            //pre: b == (l + r) / 2
            //post: m == b
            m = (l + r) / 2;
            //pre: a[m] > x
            //post: l == m
            if (a[m] > x) {
                //pre: b == m
                //post: l == b
                l = m;
                //l' == m, r' == r
                //a[m] > x -> a[l'] > x
            }
            //pre: a[m] <= x
            //post: r == m
            else {
                //pre: b == m
                //post: r == b
                r = m;
                //l' == l, r' == m
                //a[m] <= x -> a[r'] <= x
            }
        }
        //((a[r] <= x && r >= 1 && a[r - 1] > x) || (r == 0 && a[r] <= x)) && l >= r - 1
        return r;
    }

    // pre: x >= 0 && l == [-1; a.length - 1] && r == [0; a.length - 1] && ((a[l..r] > x && r == a.length - 1) || (a[l] >= x >= a[r])) &&
    // && forall i: i >= 0 && i < a.length - 1: a[i] >= a[i + 1]
    // post: r == min i: a[i] <= x
    public static int binSearchRec(int[] a, int x, int l, int r) {
        //pre: b == l + (r - l) / 2
        //post: m == b
        int m = l + (r - l) / 2;
        if (l >= r - 1) {
            //pre: l >= r - 1
            //(a[r] <= x && r >= 1 && a[r - 1] > x) || (r == 0 && a[r] <= x)
            return r;
        }
        //pre: l < r - 1 && a[m] > x
        //post: l' == m
        //a[m] > x -> a[l'] > x
        else if (a[m] > x) {
            return binSearchRec(a, x, m, r);
        }
        //pre: l < r - 1 && a[m] <= x
        //post: r' == m
        //a[m] <= x -> a[r'] <= x
        else {
            return binSearchRec(a, x, l, m);
        }
    }

    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int[] a = new int[args.length];
        for (int i = 1; i < args.length; i++){
            //inv: i < args.length && i >= 1
            a[i - 1] = Integer.parseInt(args[i]);
        }
        int ans = binSearchIt(a, x, a.length - 1);
        //int ans = binSearchRec(a, x, -1, a.length - 1);
        System.out.println(ans);
    }
}
