package utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SimpleArrayListTest {
    @Test
    public void whenAddAndContains() {
        List<Integer> list = new SimpleArrayList<>();
        Assert.assertTrue(list.add(11));
        Assert.assertTrue(list.contains(11));
    }

}
