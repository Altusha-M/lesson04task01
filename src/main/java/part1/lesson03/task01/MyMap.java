package part1.lesson03.task01;

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
    private int size = 16;
    private Node<K, V>[] myMap = new Node[size];
    private int bucketIndex;
    private int counter = 0;
    Set<Map.Entry<K,V>> EntrySet = new HashSet<>();

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
            buckInd = key.hashCode() & (size-1);
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
            System.out.println(myMap[bucketIndex]);
            myMap[bucketIndex] = newNode;
            System.out.println(myMap[bucketIndex]);
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

    public static void main(String[] args) {
        MyMap<String, Integer> myMap = new MyMap<>();
        HashMap<String, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < 25; i++) {
            myMap.put(i + "23" + "", i);
            hashMap.put(i + "23" + "", i);
        }
        for (int i = 0; i < 25; i++) {
            myMap.put(i + "12" + i, i);
            hashMap.put(i + "12" + i, i);
        }
        System.out.println(myMap.keySet());
        System.out.println(hashMap.keySet());
        Collection<Integer> a = myMap.values();
        System.out.println(a);
    }
}
