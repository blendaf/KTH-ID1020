public class MergeSort {

/**
 * Adapted code from powerpoint 'ID1020: Mergesort', Dr. Per Brand, KTH, 2017.09.18
**/


    private static int merge(int[] a, int[] aux, int lo, int mid, int hi)
    {
        int inversions = 0;

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid ) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (aux[j] < aux[i]) {
                a[k] = aux[j++];
                inversions += mid-i+1;
            } else {
                a[k] = aux[i++];
            }
        }

        return inversions;
    }

    private static int sort(int[] a, int[] aux, int lo, int hi) {
        int inversions = 0;
        if (hi <= lo) return 0;
        int mid = lo + (hi - lo) / 2;
        inversions += sort(a, aux, lo, mid);
        inversions += sort(a, aux, mid+1, hi);
        inversions += merge(a, aux, lo, mid, hi);
        return inversions;
    }



    public static int sort(int[] a) {
        int[] aux = new int[a.length];
        return sort(a, aux, 0, a.length - 1);
    }
}
