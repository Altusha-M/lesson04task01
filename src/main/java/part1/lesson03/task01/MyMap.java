package part1.lesson03.task01;

import java.util.*;

/**
 * my realization of HashMap class
 * implements Map interface
 */


class Node<K, V> {
    private K key;
    private V value;
    private int hash;

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
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public int getHash() {
        return hash;
    }
}

public class MyMap<K, V> implements Map {
    private int size = 100;
    private int bucketSize = 10;
    public Node[][] myMap = new Node[size][bucketSize];
    private int bucketIndex;
    private int counter = 0;

    public MyMap() {
    }

    public MyMap(K key, V value) {
        Node newNode = new Node(key, value);
        this.put(newNode);
        counter++;
    }

    public static void main(String[] args) {
        MyMap myMap = new MyMap();
        myMap.put("10", "10");
        myMap.put("11", "10");
        myMap.put("12", "10");
        myMap.put(1, "10");
        myMap.put(2, "10");
        System.out.println(myMap.containsKey("1"));
        System.out.println(myMap.size());
        System.out.println(myMap.get("12"));
        myMap.remove(1);
        System.out.println(myMap.get(1));
        myMap.clear();
        System.out.println(myMap.get("12"));
    }

    public int getSize() {
        return size;
    }

    @Override
    public int size() { // tested
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return (counter == 0);
    }

    public Node[][] getMyMap() {
        return myMap;
    }

    private int getBucketIndex(K key) {
        int buckInd;
        try {
            buckInd = key.hashCode() & (size - 1);
        } catch (NullPointerException e) {
            throw e;
        }
        return buckInd;
    }
    HashMap n = new HashMap();

    public V put(K key, V value) {
        Node newNode = new Node(key, value);
        return this.put(newNode);
 /*       try {
            bucketIndex = newNode.getHash() & (size - 1);
        } catch (NullPointerException e) {
            throw e;
        }
        boolean emptyBucketCondition = myMap[bucketIndex][0] == null;
        if (emptyBucketCondition) {
            myMap[bucketIndex][0] = newNode;
            counter++;
            return null;
        } else {
            int i = 0;
            do {
                if (!myMap[bucketIndex][i].getKey().equals(key)) {
                    Node tmp = myMap[bucketIndex][i];
                    myMap[bucketIndex][i] = newNode;
                    counter++;
                    return tmp;
                }
                i++;
            } while (myMap[bucketIndex][i + 1] != null);
        }
        return null;
  */
    }

    public V put(Node newNode) {
        bucketIndex = getBucketIndex(newNode.getKey());
        boolean emptyBucketCondition = myMap[bucketIndex][0] == null;
        if (emptyBucketCondition) {
            myMap[bucketIndex][0] = newNode;
            counter++;
            return null;
        } else {
            int i = 0;
            do {
                if (!myMap[bucketIndex][i].getKey().equals(newNode.getKey())) {
                    Node tmp = myMap[bucketIndex][i];
                    myMap[bucketIndex][i] = newNode;
                    counter++;
                    return tmp;
                }
                i++;
            } while (myMap[bucketIndex][i + 1] != null);
        }
        return null;
    }

    public Object get(Object key) {
        bucketIndex = getBucketIndex(key);
        boolean emptyBucketCondition = myMap[bucketIndex][0] == null;
        if (emptyBucketCondition) {
            return null;
        } else {
            int i = 0;
            do {
                if (myMap[bucketIndex][i].getKey().equals(key)) {
                    return myMap[bucketIndex][i].getKey();
                }
                if (i < bucketSize - 1) {
                    i++;
                } else {
                    return null;
                }
            } while (myMap[bucketIndex][i + 1] != null);
            return null;
        }
    }

    public Object remove(Object key) {
        bucketIndex = getBucketIndex(key);
        boolean emptyBucketCondition = myMap[bucketIndex][0] == null;
        if (emptyBucketCondition) {
            throw new RuntimeException();
        } else {
            int i = 0;
            do {
                if (myMap[bucketIndex][i].getKey().equals(key)) {
                    Node tmp = myMap[bucketIndex][i];
                    myMap[bucketIndex][i] = myMap[bucketIndex][i + 1];
                    myMap[bucketIndex][i + 1] = null;
                    counter--;
                    return tmp;
                }
                i++;
            } while (i < bucketSize);
            throw new RuntimeException();
        }
    }

    @Override
    public void putAll(Map m) {

    }

    public void clear() {
        for (bucketIndex = 0; bucketIndex < size; bucketIndex++) {
            for (int i = 0; i < bucketSize; i++) {
                myMap[bucketIndex][i] = null;
                counter = 0;
            }
        }
    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry> entrySet() {
        return null;
    }

    public boolean containsKey(Object key) {
        bucketIndex = getBucketIndex(key);
        boolean emptyBucketCondition = myMap[bucketIndex][0] == null;
        if (emptyBucketCondition) {
            return false;
        } else {
            int i = 0;
            do {
                if (myMap[bucketIndex][i].getKey().equals(key)) {
                    return true;
                }
                i++;
            } while (myMap[bucketIndex][i + 1] != null);
            return false;
        }
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }
}
