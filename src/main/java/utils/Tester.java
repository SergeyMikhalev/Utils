package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Tester {
    public static void main(String[] args) {

        System.out.println(Objects.equals(null, null));
        List<Integer> list1 = List.of(1,2,3,4);
        List<Integer> list2 = List.of(1,2,2);
        System.out.println(list1.containsAll(list2));
        ArrayList<Integer> arrayList = new ArrayList<>(10);


    }
}
