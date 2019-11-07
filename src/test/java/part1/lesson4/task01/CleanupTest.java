package part1.lesson4.task01;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CleanupTest {

    class TestClass {
        int i = 0;
        String s = "Sting";
        char ch = 's';
    }
    @Test
    void cleanup() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Set<String> toClear = new HashSet<String>(){{
            add("isEmpty");
            add("iterator");
        }};
        Set<String> toOutput = new HashSet<String>(){{
            add("map");
        }};
        TestClass tc = new TestClass();

        Cleanup.cleanup(tc, toClear, toOutput);
    }
}