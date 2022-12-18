package utils;

import java.util.Arrays;

public class QSortInt {

    public static void quickSort(int arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(int arr[], int begin, int end) {
        int pivotPos= end;
        int pivot = arr[pivotPos];

        int i = (begin - 1);
        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                if (i != j) {
                    swap(arr,i,j);
                }
            }
        }
        swap(arr,i+1, pivotPos);
        return i + 1;
    }

    private static void swap(int[] arr,int i, int j) {
        int swapTemp = arr[i];
        arr[i] = arr[j];
        arr[j] = swapTemp;
    }



    public static void main(String[] args) {
        int[] data = {1,2,7,2,10,6};
        System.out.println(Arrays.toString(data));
        quickSort(data,0,5);
        System.out.println(Arrays.toString(data));
    }

}
