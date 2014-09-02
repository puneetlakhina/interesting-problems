package ds;

public class SearchInACirclarSortedList {

    public static int search(int[] arr, int valToSearchFor) {
        // Try to find the virtual 0 of the array
        return binarySearchWithVirtualStartEnds(arr, 0, arr.length, valToSearchFor, findVirtualZeroIndex(arr, 0, arr.length-1));
    }

    private static int binarySearchWithVirtualStartEnds(int arr[], int start, int end, int valToSearchFor, int realIndexForVirtualZero) {
        if(start >= arr.length || start > end || start < 0) {
            return -1;
        }
        if (start == end) {
            int realIndex = virtualToRealIndex(start, realIndexForVirtualZero, arr.length);
            return arr[realIndex] == valToSearchFor ? realIndex : -1;
        }
        int mid = (start + end) / 2;
        int realIndexForMid = virtualToRealIndex(mid, realIndexForVirtualZero, arr.length);
        if (arr[realIndexForMid] == valToSearchFor) {
            return realIndexForMid;
        }
        if (arr[realIndexForMid] > valToSearchFor) {
            return binarySearchWithVirtualStartEnds(arr, start, mid-1, valToSearchFor, realIndexForVirtualZero);
        } else {
            return binarySearchWithVirtualStartEnds(arr, mid+1, end, valToSearchFor, realIndexForVirtualZero);
        }
    }

    private static int virtualToRealIndex(int virtualIndex, int realIndexForVirtualZero, int realLength) {
        int realIndex = realIndexForVirtualZero + virtualIndex;
        if (realIndex >= realLength) {
            return realLength - realIndex;
        }
        return realIndex;
    }

    private static int findVirtualZeroIndex(int[] arr, int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("Should not happen");
        }
        if ((end - start) == 1) {
            return arr[end] > arr[start] ? start : end;
        }
        if (start == end) {
            return start;
        }
        int mid = (start + end) / 2;
        if (arr[mid] > arr[end]) {
            return findVirtualZeroIndex(arr, mid, end);
        } else {
            return findVirtualZeroIndex(arr, start, mid);
        }
    }
}
