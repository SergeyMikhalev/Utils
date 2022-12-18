package utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class SimpleArrayListTest {

    @Test
    public void whenAddOne() {
        List<Integer> list = new SimpleArrayList<>();
        Assert.assertTrue(list.add(11));
        Assert.assertTrue(list.contains(11));
        Assert.assertEquals(list.size(), 1);
    }

    @Test
    public void whenAddMany() {
        List<Integer> list = new SimpleArrayList<>();
        Assert.assertTrue(list.add(1));
        Assert.assertTrue(list.add(2));
        Assert.assertTrue(list.add(3));
        Assert.assertEquals(3, list.size());
        Assert.assertTrue(list.contains(1));
        Assert.assertTrue(list.contains(2));
        Assert.assertTrue(list.contains(3));
        Assert.assertFalse(list.contains(4));
    }

    @Test
    public void whenAddAndCheckIndexOf() {
        List<Integer> list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Assert.assertEquals(0, list.indexOf(1));
        Assert.assertEquals(1, list.indexOf(2));
        Assert.assertEquals(2, list.indexOf(3));
        Assert.assertEquals(-1, list.indexOf(7));
    }

    @Test
    public void whenAddAndGet() {
        List<String> list = new SimpleArrayList<>();
        list.add("Kate");
        list.add("Leo");
        list.add("Tom");
        list.add("Karl");
        Assert.assertEquals("Kate", list.get(0));
        Assert.assertEquals("Leo", list.get(1));
        Assert.assertEquals("Tom", list.get(2));
        Assert.assertEquals("Karl", list.get(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutOfRange() {
        List<String> list = new SimpleArrayList<>();
        list.add("Kate");
        list.add("Leo");
        list.add("Tom");
        list.get(3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutOfRangeNegativeIndex() {
        List<String> list = new SimpleArrayList<>();
        list.add("Kate");
        list.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutOfRangeOnEmpty() {
        List<String> list = new SimpleArrayList<>();
        list.get(0);
    }

    @Test
    public void whenAddAll() {
        List<String> list = new SimpleArrayList<>();
        list.add("Kate");
        list.add("Leo");
        list.add("Tom");
        List<String> additionalList = List.of("Jack", "Jerrie");
        list.addAll(additionalList);

        Assert.assertEquals("Kate", list.get(0));
        Assert.assertEquals("Leo", list.get(1));
        Assert.assertEquals("Tom", list.get(2));
        Assert.assertEquals("Jack", list.get(3));
        Assert.assertEquals("Jerrie", list.get(4));
        Assert.assertEquals(5, list.size());
    }

    @Test
    public void whenAddAllWithIndexInTheMiddle() {
        List<String> list = new SimpleArrayList<>();
        list.add("Kate");
        list.add("Leo");
        list.add("Tom");
        List<String> additionalList = List.of("Jack", "Jerrie");
        list.addAll(2, additionalList);

        Assert.assertEquals("Kate", list.get(0));
        Assert.assertEquals("Leo", list.get(1));
        Assert.assertEquals("Jack", list.get(2));
        Assert.assertEquals("Jerrie", list.get(3));
        Assert.assertEquals("Tom", list.get(4));
        Assert.assertEquals(5, list.size());
    }

    @Test
    public void whenAddAllWithIndexInTheBegining() {
        List<String> list = new SimpleArrayList<>();
        list.add("Kate");
        list.add("Leo");
        list.add("Tom");
        List<String> additionalList = List.of("Jack", "Jerrie");
        list.addAll(0, additionalList);

        Assert.assertEquals("Jack", list.get(0));
        Assert.assertEquals("Jerrie", list.get(1));
        Assert.assertEquals("Kate", list.get(2));
        Assert.assertEquals("Leo", list.get(3));
        Assert.assertEquals("Tom", list.get(4));
        Assert.assertEquals(5, list.size());
    }

    @Test
    public void whenAddAllWithIndexAfterLast(){
        List<Integer> list = new SimpleArrayList<>();
        list.addAll(0,List.of(1,2,3));
        list.add(4);
        list.addAll(4,List.of(5,6));

        Assert.assertEquals(6, list.size());
        Assert.assertEquals(1, list.get(0).intValue());
        Assert.assertEquals(2, list.get(1).intValue());
        Assert.assertEquals(3, list.get(2).intValue());
        Assert.assertEquals(4, list.get(3).intValue());
        Assert.assertEquals(5, list.get(4).intValue());
        Assert.assertEquals(6, list.get(5).intValue());

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAllWithIndexLessThan0() {
        List<String> list = new SimpleArrayList<>();
        list.add("Kate");
        List<String> additionalList = List.of("Jack", "Jerrie");
        list.addAll(-2, additionalList);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAllWithIndexTooBig() {
        List<String> list = new SimpleArrayList<>();
        list.add("Kate");
        list.add("Leo");
        List<String> additionalList = List.of("Jack", "Jerrie");
        list.addAll(3, additionalList);
    }

    @Test
    public void whenAddAndRemove() {
        List<String> list = new SimpleArrayList<>();
        list.add("Kate");
        Assert.assertTrue(list.remove("Kate"));
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void whenAddTwoAndRemoveProper() {
        List<Long> list = new SimpleArrayList<>();
        list.add(11L);
        list.add(22L);
        Assert.assertTrue(list.remove(22L));
        Assert.assertTrue(list.contains(11L));
        Assert.assertFalse(list.contains(22L));
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void whenRemoveFromEmpty() {
        List<Long> list = new SimpleArrayList<>();
        Assert.assertFalse(list.remove(33L));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveWithWrongIndex() {
        List<Long> list = new ArrayList<>(5);
        list.remove(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveWithNegativeIndex() {
        List<Long> list = new ArrayList<>(5);
        list.remove(1);
    }

    @Test
    public  void whenAddAndRemove100000at0(){
        SimpleArrayList<Long> objects = new SimpleArrayList<>();
        for (int i = 0; i < 100000; i++) {
            objects.add(0, Long.valueOf(i));
        }
        Assert.assertEquals(100000, objects.size());
        for (int i = 0; i < 100000; i++) {
            objects.remove(0);
        }
        Assert.assertEquals(0, objects.size());
    }

    @Test
    public  void whenAddAndRemove100000(){
        SimpleArrayList<Long> objects = new SimpleArrayList<>();
        for (int i = 0; i < 100000; i++) {
            objects.add(Long.valueOf(i));
        }
        Assert.assertEquals(100000, objects.size());
        for (int i = 0; i < 100000; i++) {
            objects.remove(Long.valueOf(i));
        }
        Assert.assertEquals(0, objects.size());
    }

    /* Тесты на метод public boolean retainAll(Collection<?> c) */
    @Test
    public void whenUsualRetain() {
        List<Long> list = new SimpleArrayList<>();
        list.add(11L);
        list.add(22L);
        list.add(33L);
        List<Long> retainList = List.of(22L, 33L, 44L);
        Assert.assertTrue(list.retainAll(retainList));
        Assert.assertFalse(list.contains(11L));
        Assert.assertTrue(list.contains(22L));
        Assert.assertTrue(list.contains(33L));
        Assert.assertFalse(list.contains(44L));
        Assert.assertEquals(2, list.size());
    }

    @Test(expected = NullPointerException.class)
    public void whenRetainWithNull() {
        new SimpleArrayList<>().retainAll(null);
    }

    @Test
    public void whenRetainDoesNotChangeList() {
        List<String> list = new SimpleArrayList<>();
        list.add("Hector");
        list.add("Gustavo");
        list.add("Walter");

        List<String> retainList = List.of("Gustavo", "Walter", "Hector", "Tuco");
        Assert.assertFalse(list.retainAll(retainList));

        Assert.assertEquals(3, list.size());
        Assert.assertEquals("Hector", list.get(0));
        Assert.assertEquals("Gustavo", list.get(1));
        Assert.assertEquals("Walter", list.get(2));
    }

    /* Тесты на итератор*/

    @Test
    public void usualIterUsage() {
        List<String> list = new SimpleArrayList<>();
        list.add("Hector");
        list.add("Gustavo");
        list.add("Walter");

        Iterator<String> it = list.iterator();

        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(it.next(), "Hector");
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(it.next(), "Gustavo");
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(it.next(), "Walter");
        Assert.assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterNextOnEmpty() {
        List<String> list = new SimpleArrayList<>();
        Iterator<String> it = list.iterator();
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterNextMoreThanElements() {

        List<String> list = new SimpleArrayList<>();
        list.add("Hector");
        list.add("Gustavo");
        list.add("Walter");

        Iterator<String> it = list.iterator();

        it.next();
        it.next();
        Assert.assertEquals("Walter", it.next());
        it.next();
    }

    @Test
    public void whenIterHasNextMultipleTimes() {
        List<String> list = new SimpleArrayList<>();
        list.add("Hector");
        list.add("Gustavo");

        Iterator<String> it = list.iterator();

        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(it.hasNext());
        Assert.assertTrue(it.hasNext());

        Assert.assertEquals("Hector", it.next());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenModifyListAfterGetIter() {
        List<String> list = new SimpleArrayList<>();
        list.add("Hector");
        list.add("Gustavo");

        Iterator<String> it = list.iterator();

        list.add("Walter");
        it.next();
    }

    /* Тесты для sort */
    @Test
    public void whenUsualSorting() {
        List<String> initial = List.of("Eagle", "Pig", "Boar", "Wolf", "Tiger");
        List<String> list = new SimpleArrayList<>();
        list.addAll(initial);

        list.sort(Comparator.naturalOrder());

        Assert.assertEquals(list.get(0), "Boar");
        Assert.assertEquals(list.get(1), "Eagle");
        Assert.assertEquals(list.get(2), "Pig");
        Assert.assertEquals(list.get(3), "Tiger");
        Assert.assertEquals(list.get(4), "Wolf");
    }

    @Test
    public void whenSortingEmptyDontCrush() {
        List<String> list = new SimpleArrayList<>();
        list.sort(Comparator.naturalOrder());
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void whenSortingOne() {
        List<String> list = new SimpleArrayList<>();
        list.add("Dog");
        list.sort(Comparator.naturalOrder());
        Assert.assertEquals(1, list.size());
        Assert.assertEquals("Dog", list.get(0));
    }

}
