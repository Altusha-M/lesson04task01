package part1.lesson03.task01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class MyMapTest {
    MyMap<String, String> myMap = new MyMap<>();
    HashMap<String, String> hashMap = new HashMap<>();

    @Before
    public void cleanMaps() {
        hashMap.clear();
        myMap.clear();
    }

    @Test
    public void size() {
        int i = 0;
        for (i = 0; i < 50; i++) {
            myMap.put("nodeNumber" + i, "value");
            hashMap.put("nodeNumber" + i, "value");
        }
        myMap.put("nodeNumber" + 25, "value2");
        assertEquals(i, myMap.size());
        assertEquals(hashMap.size(), myMap.size());
    }

    @Test
    public void put() {
        String a = "1st key";
        assertEquals(hashMap.put(a, "1"), myMap.put(a, "1"));
        assertEquals(hashMap.put(a, "2"), myMap.put(a, "2"));
        assertEquals("2", myMap.get(a));
        assertEquals(hashMap.get(a), myMap.get(a));
    }

    @Test
    public void containsKey() {
        myMap.put("1", "1");
        assertTrue(myMap.containsKey("1"));
        myMap.clear();
        myMap.put("1", "1");
        assertTrue(myMap.containsKey("1"));
    }

    @Test
    public void main() {
    }

    @Test
    public void get() {
        String o = "q";
        myMap.put(o, ""+2);
        hashMap.put(o,""+2);
        assertTrue(myMap.get(o).equals(""+2));
        assertEquals(hashMap.get(o), myMap.get(o));
    }

    @Test
    public void remove() {
        for (int i = 0; i < 500; i++) {
            myMap.put(""+i,i+"");
            hashMap.put(""+i,i+"");
        }
        for (int i = 0; i < 500; i++) {
            assertTrue(myMap.containsKey(""+i));
            assertEquals(hashMap.containsKey(""+i), myMap.containsKey(""+i));
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
        assertTrue(myMap.isEmpty());
        assertEquals(hashMap.isEmpty(), myMap.isEmpty());
    }

    @Test
    void keySet() {
        Set s = new HashSet();
        for (int i = 0; i < 50; i++) {
            myMap.put(i+"",""+i);
            hashMap.put(i+"",""+i);
        }
        assertEquals(hashMap.keySet(), myMap.keySet());
        for (int i = 0; i < 50; i+=2) {
            myMap.put(i+"",""+i);
            hashMap.put(i+"",""+i);
        }
        assertEquals(hashMap.keySet(), myMap.keySet());
    }

    @Test
    void containsValue() {
        myMap.put("1", "1");
        assertTrue(myMap.containsValue("1"));
        hashMap.put("2", "1");
        assertEquals(hashMap.containsValue("1"), myMap.containsValue("1"));
        myMap.clear();
        assertTrue(!myMap.containsValue("1"));
        hashMap.clear();
        assertEquals(hashMap.containsValue("1"), myMap.containsValue("1"));
    }

    @Test
    void values() {
        Collection<String> s = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            myMap.put(i+"",""+i);
            hashMap.put(i+"",""+i);
        }
        s = myMap.values();
        for (String str : s) {
            assertTrue(myMap.containsValue(str));
            assertEquals(hashMap.containsValue(str), myMap.containsValue(str));
        }
    }

    @Test
    void putAll() {
        Map<String, String> puttedMap = new HashMap<>();
        for (int i = 0; i < 50; i++) {
            myMap.put(i+"1",""+i);
            hashMap.put(i+"1",""+i);
            puttedMap.put(i+"22222222222",""+i);
        }
        myMap.putAll(puttedMap);
        hashMap.putAll(puttedMap);
        assertEquals(hashMap.size(), myMap.size());
    }

    @Test
    void entrySet() {

    }

}