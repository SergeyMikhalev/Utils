package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ListSorting {
    public static <T> void quickSort(List<T> list, Comparator<T> comparator) {
        innerQuickSort(list, 0, list.size() - 1, comparator);
    }

    public static <T extends Comparable<? super T>> void quickSort(List<T> list) {
        innerQuickSort(list, 0, list.size() - 1, Comparator.naturalOrder());
    }

    private static <T> void innerQuickSort(List<T> list, int begin, int end, Comparator<T> comparator) {
        if (begin < end) {
            int partitionIndex = partition(list, begin, end, comparator);

            innerQuickSort(list, begin, partitionIndex - 1, comparator);
            innerQuickSort(list, partitionIndex + 1, end, comparator);
        }
    }

    private static <T> int partition(List<T> list, int begin, int end, Comparator<T> comparator) {
        int pivotPos = end;
        T pivot = list.get(pivotPos);

        int i = (begin - 1);
        for (int j = begin; j < end; j++) {
            if (comparator.compare(list.get(j), pivot) <= 0) {
                i++;
                if (i != j) {
                    swap(list, i, j);
                }
            }
        }
        swap(list, i + 1, pivotPos);
        return i + 1;
    }

    private static <T> void swap(List<T> list, int i, int j) {
        T swapTemp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, swapTemp);
    }

    public static void main(String[] args) {
        List<String> initial = List.of("Eagle","Pig","Boar","Wolf","Tiger");
        List<String> initial2 = List.of("Eagle","Pig","Boar","Wolf","Tiger", null, null);

        List<String> list = new ArrayList<>(initial);
        System.out.println(list);
        Collections.sort(list,Comparator.naturalOrder());
        System.out.println(list);

        System.out.println();

        List<String> list2 = new ArrayList<>(initial);
        System.out.println(list2);
        ListSorting.quickSort(list2, Comparator.naturalOrder());
        System.out.println(list2);

        System.out.println();

        List<String> list3 = new SimpleArrayList<>();
        list3.addAll(initial);
        System.out.println(list3);
        ListSorting.quickSort(list3, Comparator.naturalOrder());
        System.out.println(list3);

        System.out.println();

        List<String> list4 = new SimpleArrayList<>();
        list4.addAll(initial);
        System.out.println(list4);
        list4.sort(Comparator.reverseOrder());
        System.out.println(list4);

        System.out.println();

        List<String> listN = new ArrayList<>(initial2);
        System.out.println(listN);
        listN.sort(Comparator.naturalOrder());
        System.out.println(listN);

    }

}
