package part1.lesson03.task01;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * my realization of HashMap class
 * implements Map interface
 *
 * @author Altynov Mikhail
 * @version 1.0
 */

class Node<K, V> implements Map.Entry<K, V> {
    private K key;
    private V value;
    private int hash;
    private Node<K, V> nextNode;

    Node(K key, V value) {
        this.key = key;
        if (key == null) {
            throw new IllegalArgumentException();
        }
        this.value = value;
        if (value == null) {
            throw new IllegalArgumentException();
        }
        hash = key.hashCode();
        nextNode = null;
    }

    public Node<K, V> getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node<K, V> nextNode) {
        this.nextNode = nextNode;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public V setValue(V value) {
        V oldValue = value;
        this.value = value;
        return oldValue;
    }

    public int getHash() {
        return hash;
    }

    public String toString() {
        return ("\"" + this.key + "=" + this.value + "\"");
    }
}

public class MyMap<K, V> implements Map<K, V> {
    HashMap<Integer, Integer> n = new HashMap<>();
    HashMap nn = new HashMap();
    Set<Map.Entry<K, V>> EntrySet = new HashSet<>();
    private int size = 16;
    private Node<K, V>[] myMap = new Node[size];
    private int bucketIndex;
    private int counter = 0;

    public MyMap() {
    }

    public MyMap(K key, V value) {
        this.put(key, value);
    }

    @Override
    public String toString() {
        String result = "";
        if (this.isEmpty()) {
            return "[]";
        } else {
            for (int i = 0; i < size; i++) {
                if (myMap[i] == null) {
                    continue;
                } else {
                    Node<K, V> curNode = myMap[i];
                    while (curNode != null) {
                        result += curNode.toString() + " ";
                        curNode = curNode.getNextNode();
                    }
                }
            }
            return result;
        }
    }

    /**
     * @return capacity of MyMap
     */
    public int getSize() {
        return size;
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return (counter == 0);
    }

    public Node[] getMyMap() {
        return myMap;
    }

    /**
     * @param key
     * @return index of bucket with elemet key
     */
    private int getBucketIndex(K key) {
        int buckInd;
        try {
            buckInd = key.hashCode() & (size - 1);
        } catch (NullPointerException e) {
            throw e;
        }
        return buckInd;
    }

    /**
     * @param key
     * @param value
     * @return overridden value of Value if it was refresh or null
     */
    public V put(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);
        bucketIndex = getBucketIndex(newNode.getKey());
        if (myMap[bucketIndex] == null) {
            counter++;
            myMap[bucketIndex] = newNode;
            entrySet();
            return null;
        }
        Node<K, V> curNode = myMap[bucketIndex];
        while (curNode.getNextNode() != null) {
            if (curNode.getKey().equals(newNode.getKey())) {
                V val = curNode.setValue(newNode.getValue());
                entrySet();
                return val;
            } else {
                curNode = curNode.getNextNode();
            }
        }
        V val = curNode.getValue();

        if (curNode.getKey().equals(newNode.getKey())) {
            V val1 = curNode.setValue(newNode.getValue());
            entrySet();
            return val;
        } else {
            counter++;
            curNode.setNextNode(newNode);
            entrySet();
            return val;
        }
    }

    public V get(Object key) {
        bucketIndex = getBucketIndex((K) key);
        if (myMap[bucketIndex] == null) {
            return null;
        }
        Node<K, V> curNode = myMap[bucketIndex];
        while (curNode.getNextNode() != null) {
            if (curNode.getKey().equals(key)) {
                return curNode.getValue();
            } else {
                curNode = curNode.getNextNode();
            }
        }
        if (curNode.getKey().equals(key)) {
            return curNode.getValue();
        } else {
            return null;
        }
    }

    /**
     * @param key
     * @return deleted value of Value if it was delete or null
     */
    public V remove(Object key) {
        bucketIndex = getBucketIndex((K) key);
        if (myMap[bucketIndex] == null) {
            entrySet();
            return null;
        }
        Node<K, V> curNode = myMap[bucketIndex];
        Node<K, V> nextNode = curNode.getNextNode();
        while (curNode.getNextNode() != null) {
            if (curNode.getKey().equals(key)) {
                Node<K, V> tmp = curNode;
                curNode = nextNode;
                counter--;
                entrySet();
                return tmp.getValue();
            } else {
                curNode = curNode.getNextNode();
                try {
                    nextNode = nextNode.getNextNode();
                } catch (NullPointerException e) {
                    break;
                }
            }
        }
        if (curNode.getKey().equals(key)) {
            Node<K, V> tmp = curNode;
            myMap[bucketIndex] = null;
            counter--;
            entrySet();
            return tmp.getValue();
        }
        entrySet();
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        Set<? extends Entry<? extends K, ? extends V>> es = m.entrySet();
        for (Entry<? extends K, ? extends V> e : es) {
            this.put(e.getKey(), e.getValue());
        }
    }

    public void clear() {
        for (bucketIndex = 0; bucketIndex < size; bucketIndex++) {
            myMap[bucketIndex] = null;
            counter = 0;
        }
        entrySet();
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        Set<Entry<K, V>> se = this.entrySet();
        for (Entry<K, V> entry : se) {
            keys.add(entry.getKey());
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        Collection<V> vals = new ArrayList<>();
        Set<K> keys = this.keySet();
        for (K k : keys) {
            vals.add(this.get(k));
        }
        return vals;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        if (EntrySet != null) {
            EntrySet = null;
            EntrySet = new HashSet<>();
        }
        if (this.isEmpty()) {
            EntrySet.clear();
            return EntrySet;
        } else {
            for (int i = 0; i < size; i++) {
                Node<K, V> curNode = myMap[i];
                if (myMap[i] == null) {
                    continue;
                } else {
                    while (curNode != null) {
                        EntrySet.add(curNode);
                        curNode = curNode.getNextNode();
                    }
                }
            }
            return EntrySet;
        }
    }

    public boolean containsKey(Object key) {
        bucketIndex = getBucketIndex((K) key);
        if (myMap[bucketIndex] == null) {
            return false;
        }
        Node<K, V> curNode = myMap[bucketIndex];
        while (curNode.getNextNode() != null) {
            if (curNode.getKey().equals(key)) {
                return true;
            } else {
                curNode = curNode.getNextNode();
            }
        }
        return curNode.getKey().equals(key);
    }

    @Override
    public boolean containsValue(Object value) {
        if (this.isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < size; i++) {
                if (myMap[i] == null) {
                    continue;
                } else {
                    Node<K, V> curNode = myMap[i];
                    while (curNode != null) {
                        if (curNode.getValue().equals(value)) {
                            return true;
                        }
                        curNode = curNode.getNextNode();
                    }
                }
            }
            return false;
        }
    }

    static class intClazz {
        static private int[] i = {1, 3, 5, 62, 3, 3};
        static private String s = "private String";

        static public int[] getI() {
            return i;
        }

        static public String getS() {
            return s;
        }
    }
    public static void main(String[] args) throws
            IllegalAccessException, InstantiationException,
            ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException {

        MyMap<String, Integer> myMap = new MyMap<>();
        HashMap<String, Integer> hashMap = new HashMap<>();

        System.out.println(myMap instanceof Map);
//        myMap.put(123, 1);
//        myMap.put(33333, 1);
//        myMap.put(22222, 1);
        myMap.put("strin312g", 1);
        myMap.put("stri2ng", 1);
        myMap.put("str112323ing", 1);

        System.out.println(myMap);

        cleanup(myMap, new HashSet<String>() {{
            add("stri2ng");
        }}, new HashSet<String>() {{
            add("str112323ing");
        }});

        System.out.println(myMap);
        System.out.println("_________________________________________");
        intClazz cl = new intClazz();
        System.out.println(cl.getS());
        cleanup(cl, new HashSet<String>(){{add("s");}},
                new HashSet<String>(){{add("s");}});
        System.out.println(cl.getS());
        System.exit(0);

        Class myMapClass = myMap.getClass();
        myMapClass.getGenericInterfaces();

        intClazz intClas = new intClazz();
        Class intClass = intClas.getClass();
        Field[] fi = intClass.getDeclaredFields();
        for (Field f : fi) {
            System.out.println(f.getType().getName());
        }
        //Test test = (Test) obj;
        /*Method[] methods = c.getMethods();
        for (Method method : methods) {
            System.out.println("Имя: " + method.getName());
            System.out.println("Возвращаемый тип: " + method.getReturnType().getName());
            Class[] paramTypes = method.getParameterTypes();
            System.out.print("Типы параметров: ");
            for (Class paramType : paramTypes) {
                System.out.print(" " + paramType.getName());
            }
            System.out.println();
        }*/

        Field[] fields = myMapClass.getDeclaredFields();
        Field[] fields1 = myMapClass.getSuperclass().getDeclaredFields();
//        for (Field f: fields) {
//            System.out.println(f.getName());
//            System.out.println(f.getType());
//        }
//        System.out.println("*******************************");
//        for (Field f: fields1) {
//            System.out.println(f.getName());
//            System.out.println(f.getType());
//        }
//        Method[] methods = myMapClass.getMethods();
//        for (Method method : methods) {
//            System.out.println("Имя: " + method.getName());
//            System.out.println("Возвращаемый тип: " + method.getReturnType().getName());
//            Class[] paramTypes = method.getParameterTypes();
//            System.out.print("Типы параметров: ");
//            for (Class paramType : paramTypes) {
//                System.out.print(" " + paramType.getName());
//            }
//            System.out.println();
//        }
        System.out.println(">>>" + myMapClass.getGenericInterfaces());
        Method method = myMapClass.getMethod("get", Object.class);
        System.out.println(method.invoke(myMap, "stri1ng"));
        Method keyMeth = myMapClass.getMethod("keySet");

        for (Field f : fields) {
            System.out.println(f.getType().getName());
//            if (f.getType().getName().equals("int")) {
//                f.set(myMap, 0);
//            } else {
//                f.set(myMap, null);
//            }
//            hs.add((Integer) method.invoke(myMap, f.get()));
            System.out.println(f.get(myMap));
        System.out.println(myMap);
        }


    }

    static void cleanup(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (fieldsToCleanup.isEmpty() && fieldsToOutput.isEmpty()) {
            return;
        }
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        ArrayList<String> fieldsList = new ArrayList<>();

        if (!(object instanceof Map)) {
            for (Field f : fields) {
                fieldsList.add(f.getName());
            }
            if (!fieldsList.containsAll(fieldsToCleanup)
                    || !fieldsList.containsAll(fieldsToOutput)) {
                throw new IllegalArgumentException();
            }
            for (Field f : fields) {
                if (fieldsToOutput.contains(f.getName())) {
                    f.setAccessible(true);
                    System.out.println((String) f.get(object));
                    f.setAccessible(false);
                }

                if (fieldsToCleanup.contains(f.getName())) {
                    f.setAccessible(true);
                    switch (f.getType().getName()) {
                        case ("int"): {
                            f.setInt(object, 0);
                            f.setAccessible(false);
                            break;
                        }
                        case ("char"): {
                            f.setChar(object, '\u0000');
                            f.setAccessible(false);
                            break;
                        }
                        case ("byte"): {
                            f.setByte(object, (byte) 0);
                            f.setAccessible(false);
                            break;
                        }
                        case ("short"): {
                            f.setShort(object, (short) 0);
                            f.setAccessible(false);
                            break;
                        }
                        case ("long"): {
                            f.setLong(object, 0L);
                            f.setAccessible(false);
                            break;
                        }
                        case ("float"): {
                            f.setFloat(object, 0.0f);
                            f.setAccessible(false);
                            break;
                        }
                        case ("double"): {
                            f.setDouble(object, 0.0d);
                            f.setAccessible(false);
                            break;
                        }
                        case ("boolean"): {
                            f.setBoolean(object, false);
                            f.setAccessible(false);
                            break;
                        }
                        default: {
                            f.set(object, null);
                            f.setAccessible(false);
                            break;
                        }
                    }
                }
            }
        } else {
            Method getMeth = clazz.getMethod("get", Object.class);
            Method removeMeth = clazz.getMethod("remove", Object.class);
            Set<String> ks = (Set<String>) clazz.getMethod("keySet").invoke(object);
            if (!ks.containsAll(fieldsToCleanup)
                    || !ks.containsAll(fieldsToOutput)) {
                throw new IllegalArgumentException();
            }
            for (String outField : fieldsToOutput) {
                System.out.println(getMeth.invoke(object, outField));
            }
            for (String clearField : fieldsToCleanup) {
                removeMeth.invoke(object, clearField);
            }
        }
    }
}
