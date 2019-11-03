package part1.lesson03.task01;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class MyMapTest {
    MyMap<Object, Object> myMap = new MyMap<>();
    HashMap<Object, Object> hashMap = new HashMap<>();

    @Test
    public void size() {
        int i = 0;
        for (i = 0; i < 50; i++) {
            myMap.put("nodeNumber" + i, "value");
            hashMap.put("nodeNumber" + i, "value");
        }
        assertEquals(i, myMap.size());
        assertEquals(hashMap.size(), myMap.size());
    }

    @Test
    public void put() {
        myMap.clear();
        hashMap.clear();
        String a = "1st key";
        myMap.put(a, 1);
        hashMap.put(a, 1);
        assertEquals(myMap.get(a), 1);
        assertEquals(hashMap.get(a), myMap.get(a));
        try {
            myMap.put(null);
            Assert.fail("Expected NullPointerException");
        }catch (Exception e){ //NullPointerException e) {
            assertEquals(e.getClass(), NullPointerException.class);
            // Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void containsKey() {
        myMap.clear();
        myMap.put("1", "1");
        assertTrue(myMap.containsKey("1"));
        try {
            myMap.containsKey(null);
            Assert.fail("Expected NullPointerException");
        }catch (NullPointerException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    void containsValue() {

    }

    @Test
    public void main() {
    }

    @Test
    public void get() {
        Object o = "q";
        myMap.put(o, 2);
        assertTrue(myMap.get(o).equals(2));
        try {
            myMap.get(null);
            Assert.fail("Expected NullPointerException");
        }catch (NullPointerException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void remove() {
        for (int i = 0; i < 50; i++) {
            myMap.put(i,i);
        }
        for (int i = 0; i < 50; i++) {
            assertTrue(myMap.containsKey(i));
        }
        for (int i = 0; i < 50; i+=2) {
            myMap.remove(i);
        }
        for (int i = 0; i < 50; i+=2) {
            assertTrue(!myMap.containsKey("1"));
        }
        try {
            myMap.remove(null);
            Assert.fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void clear() {
        myMap.put("2","3");
        myMap.put("4","3");
        myMap.put("5","3");
        myMap.put("6","3");
        myMap.put("27","3");
        hashMap.put("2","3");
        hashMap.put("4","3");
        hashMap.put("5","3");
        hashMap.put("6","3");
        hashMap.put("27","3");
        myMap.clear();
        hashMap.clear();
        assertEquals(hashMap.size(), myMap.size());
    }

    @Test
    void isEmpty() {
        hashMap.clear();
        myMap.clear();
        assertTrue(myMap.isEmpty());
        assertEquals(hashMap.isEmpty(), myMap.isEmpty());
    }

    @Test
    void putAll() {
    }

    @Test
    void keySet() {
        MyMap<Object, Object> myMap = new MyMap<>();
        HashMap<Object, Object> n = new HashMap<>();
        Set s = new HashSet();
        for (int i = 0; i < 50; i++) {
            myMap.put(i,i);
            n.put(i,i);
        }
        assertEquals(n.keySet(), myMap.keySet());
        for (int i = 0; i < 50; i+=2) {
            myMap.put(i,i);
            n.put(i,i);
        }
        assertEquals(n.keySet(), myMap.keySet());
    }

    @Test
    void values() {

    }

    @Test
    void entrySet() {

    }

}