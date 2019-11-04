package part1.lesson03.task01;

import java.sql.SQLOutput;
import java.util.*;

/**
 * my realization of HashMap class
 * implements Map interface
 * @author Altynov Mikhail
 * @version 1.0
 */

class Node<K, V> implements Map.Entry<K, V>{
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
        return (this.key + "=" + this.value);
    }
}

public class MyMap<K, V> implements Map<K, V> {
    HashMap<Integer, Integer> n = new HashMap<>();
    HashMap nn = new HashMap();
    private int size = 2 ^ 3;
    private Node<K, V>[] myMap = new Node[size];
    private int bucketIndex;
    private int counter = 0;

    public MyMap() {
    }

    public MyMap(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);
        this.put(newNode);
        counter++;
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
            buckInd = key.hashCode() % size;
        } catch (NullPointerException e) {
            throw e;
        }
        return buckInd;
    }

    /**
     *
     * @param key
     * @param value
     * @return overridden value of Value if it was refresh or null
     */
    public V put(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);
        return this.put(newNode);
    }

    public V put(Node<K, V> newNode) {
        bucketIndex = getBucketIndex(newNode.getKey());
        if (myMap[bucketIndex] == null) {
            myMap[bucketIndex] = newNode;
            counter++;
            return null;
        }

        Node<K, V> curNode = myMap[bucketIndex];
        while (curNode.getNextNode() != null) {
            if (curNode.getKey().equals(newNode.getKey())) {
                V val = curNode.setValue(newNode.getValue());

                return val;
            } else {
                curNode = curNode.getNextNode();
            }
        }
        V val = curNode.getValue();

        if (curNode.getKey().equals(newNode.getKey())) {
            V val1 = curNode.setValue(newNode.getValue());
        }
        if (!curNode.getKey().equals(newNode.getKey())){
            counter++;
        }
        curNode.setNextNode(newNode);
        return val;
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
     *
     * @param key
     * @return deleted value of Value if it was delete or null
     */
    public V remove(Object key) {
        bucketIndex = getBucketIndex((K) key);
        if (myMap[bucketIndex] == null) {
            return null;
        } else if(myMap[bucketIndex].getKey().equals(key)) {
            Node<K, V> tmp = myMap[bucketIndex];
            myMap[bucketIndex] = null;
            return tmp.getValue();//tmp.getValue();
        }
        Node<K, V> curNode = myMap[bucketIndex];
        Node<K, V> nextNode = curNode.getNextNode();
        while (curNode.getNextNode() != null) {
            if (nextNode.getKey().equals(key)) {
                curNode.setNextNode(nextNode.getNextNode());
                counter--;
                return nextNode.getValue();
            } else {
                curNode = curNode.getNextNode();
                nextNode = nextNode.getNextNode();
            }
        }
        if (curNode.getKey().equals(key)) {
            Node<K, V> tmp = curNode;
            curNode = null;
            counter--;
            return tmp.getValue();
        }
        return null;
    }

    @Override
    public void putAll(Map m) {
        Set<Entry<K,V>> es = m.entrySet();
        for (Entry<K, V> e : es) {
            this.put(e.getKey(), e.getValue());
        }
    }

    public void clear() {
        for (bucketIndex = 0; bucketIndex < size; bucketIndex++) {
            myMap[bucketIndex] = null;
            counter = 0;
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        Set<Entry<K,V>> se = this.entrySet();
        for (Entry<K,V> entry : se) {
            //System.out.println(this.get(k));
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
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> ks = new HashSet<>();
        if (this.isEmpty()){
            return ks;
        } else {
            for (int i = 0; i < size; i++) {
                Node<K, V> curNode = myMap[i];
                if (myMap[i] == null) {
                    myMap[i] = curNode;
                } else {
                    while (curNode != null){
                        ks.add(curNode);
                        curNode = curNode.getNextNode();
                    }
                    return ks;
                }
            }
            return ks;
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
        if (this.isEmpty()){
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
                    return false;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        MyMap<String, Integer> myMap = new MyMap<>();
        HashMap<String, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < 25; i++) {
            myMap.put(i+"23"+"",i);
            hashMap.put(i+"23"+"",i);
        }
        for (int i = 0; i < 25; i++) {
            myMap.put(i+"12"+i,i);
            hashMap.put(i+"12"+i,i);
        }
        System.out.println(myMap.keySet());
        System.out.println(hashMap.keySet());
        Collection<Integer> a = myMap.values();
     //   Collection<String> a1 = n.values();
        System.out.println(a);
    }
}
