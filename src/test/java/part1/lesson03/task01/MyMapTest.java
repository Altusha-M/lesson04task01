package part1.lesson03.task01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class MyMapTest {
    MyMap<Integer, String> myMap = new MyMap<>();
    HashMap<Integer, String> hashMap = new HashMap<>();

    @Before
    public void cleanMaps() {
        hashMap.clear();
        myMap.clear();
    }

    @Test
    public void size() {
        int i = 0;
        for (i = 0; i < 50; i++) {
            myMap.put(i, "value");
            hashMap.put(i, "value");
        }
        myMap.put(25, "value2");
        assertEquals(i, myMap.size());
        assertEquals(hashMap.size(), myMap.size());
    }

    @Test
    public void put() {
        for (int i = 1; i < 5; i++) {
            hashMap.put(i, ""+i);
            myMap.put(i, ""+i);
            System.out.println(myMap);
            System.out.println(hashMap);
        }
        hashMap.put(0, ""+1);
        myMap.put(0, ""+1);

        System.out.println(myMap);
        System.out.println(hashMap);
        myMap.put(5, ""+5);
        hashMap.put(5, ""+5);

        System.out.println(myMap);
        System.out.println(hashMap);
        myMap.put(20, ""+5);
        hashMap.put(20, ""+5);

        System.out.println(myMap);
        System.out.println(hashMap);
        for (int i = 0; i < 15; i++) {
            assertEquals(hashMap.get(i), myMap.get(i));
        }
        assertEquals(hashMap.put(0, "1"), myMap.put(0, "1"));
       // assertEquals("2", myMap.get(a));
    }

    @Test
    public void containsKey() {
        myMap.put(1, "1");
        assertTrue(myMap.containsKey(1));
        myMap.clear();
        myMap.put(1, "1");
        assertTrue(myMap.containsKey(1));
    }

    @Test
    public void main() {
    }

    @Test
    public void get() {
        Integer o = 1;
        myMap.put(o, ""+2);
        hashMap.put(o,""+2);
        assertTrue(myMap.get(o).equals(""+2));
        assertEquals(hashMap.get(o), myMap.get(o));
    }

    @Test
    public void remove() {
        MyMap<Integer, String> myMap = new MyMap<>();
        HashMap<Integer, String> hashMap = new HashMap<>();

        for (int i = 0; i < 5; i++) {
            myMap.put(i,i+"");
            hashMap.put(i,i+"");
        }

        System.out.println(myMap);
        myMap.remove(0);
        System.out.println(myMap);

        System.out.println(myMap);
        myMap.remove(2);
        System.out.println(myMap);

        System.out.println(myMap);
        myMap.remove(4);
        System.out.println(myMap);

        for (int i = 0; i < 150; i++) {
//            assertTrue(!myMap.containsKey(""+i));
//            assertEquals(hashMap.containsKey(""+i), myMap.containsKey(""+i));
        }
    }

    @Test
    public void clear() {
        myMap.put(2,"3");
        myMap.put(4,"3");
        myMap.put(5,"3");
        myMap.put(6,"3");
        myMap.put(28,"3");
        hashMap.put(2,"3");
        hashMap.put(4,"3");
        hashMap.put(5,"3");
        hashMap.put(6,"3");
        hashMap.put(28,"3");
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
            myMap.put(i,""+i);
            hashMap.put(i,""+i);
        }
        assertEquals(hashMap.keySet(), myMap.keySet());
        for (int i = 0; i < 50; i+=2) {
            myMap.put(i,""+i);
            hashMap.put(i,""+i);
        }
        assertEquals(hashMap.keySet(), myMap.keySet());
    }

    @Test
    void containsValue() {
        myMap.put(1, "1");
        assertTrue(myMap.containsValue("1"));
        hashMap.put(2, "1");
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
            myMap.put(i,""+i);
            hashMap.put(i,""+i);
        }
        s = myMap.values();
        for (String str : s) {
            assertTrue(myMap.containsValue(str));
            assertEquals(hashMap.containsValue(str), myMap.containsValue(str));
        }
    }

    @Test
    void putAll() {
        Map<Integer, String> puttedMap = new HashMap<>();
        for (int i = 0; i < 50; i++) {
            myMap.put(i+1,""+i);
            hashMap.put(i+1,""+i);
            puttedMap.put(i+1111,""+i);
        }
        myMap.putAll(puttedMap);
        hashMap.putAll(puttedMap);
        assertEquals(hashMap.size(), myMap.size());
        assertEquals(hashMap.keySet(), myMap.keySet());
    }

    @Test
    void entrySet() {
        Set<Map.Entry<Integer, String>> standartEntrySet = hashMap.entrySet();
        Set<Map.Entry<Integer, String>> newEntrySet = myMap.entrySet();
        for (int i = 0; i < 10000; i++) {
            hashMap.put(i, ""+5);
            myMap.put(i, ""+5);
        }

        for (int i = 0; i < 1985; i+=3) {
            hashMap.remove(i);
            myMap.remove(i);
        }

        myMap.put(500, ""+5);
        hashMap.put(500, ""+5);
        boolean containAll = true;
        containAll = myMap.size() == hashMap.size();
        assertTrue(containAll);
        for (Map.Entry<Integer, String> newEntry : newEntrySet) {
            Integer nek = newEntry.getKey();
            String nev = newEntry.getValue();
            boolean pairEquals = false;
            for (Map.Entry<Integer, String> stEntry : standartEntrySet) {
                boolean keyEquals = stEntry.getKey().equals(nek);
                boolean valueEquals = stEntry.getValue().equals(nev);
                if(keyEquals && valueEquals){
                    pairEquals = true;
                    break;
                }
            }
            containAll &= pairEquals;
            assertTrue(containAll);
        }
        assertTrue(containAll);
    }

}