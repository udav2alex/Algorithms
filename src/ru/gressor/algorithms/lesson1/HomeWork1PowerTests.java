package ru.gressor.algorithms.lesson1;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class HomeWork1PowerTests {
    private final long arg;
    private final long power;
    private final long result;

    @Test
    public void checkPowerFunction() {
        String pattern = "%d ^ %d = %d";
        System.out.println(String.format(pattern, arg, power, HomeWork1.power(arg,power)));
        Assert.assertEquals(HomeWork1.power(arg, power), result);
    }

    public HomeWork1PowerTests(long arg, long power, long result) {
        this.arg = arg;
        this.power = power;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection<Long[]> testData() {
        return Arrays.asList(new Long[][]{
                {2L, 1L, 2L}, // основание, степень, результат
                {2L, 0L, 1L},
                {2L, 2L, 4L},
                {2L, 3L, 8L},
                {2L, 4L, 16L},
                {2L, 5L, 32L},
                {3L, 2L, 9L},
                {3L, 3L, 27L},
                {3L, 4L, 81L}
        });
    }
}
