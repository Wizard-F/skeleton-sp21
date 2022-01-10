package hashmap;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    @Override
    public void clear() {
        for (int i=0; i<numBucket; i+=1) {
            buckets[i] = createBucket();
        }
        keySet.clear();
        numItem = 0;
    }

    @Override
    public boolean containsKey(K key) {
        int bucketIndex = ((key.hashCode()%numBucket) + numBucket) % numBucket;
        for (Node node : buckets[bucketIndex]) {
            if (node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int bucketIndex = ((key.hashCode()%numBucket) + numBucket) % numBucket;
        for (Node node : buckets[bucketIndex]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return numItem;
    }

    @Override
    public void put(K key, V value) {
        int bucketIndex = ((key.hashCode()%numBucket) + numBucket) % numBucket;
        // Check if key already exists. If so, update value and return.
        for (Node node : buckets[bucketIndex]) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        // If key does not exist, add new node.
        keySet.add(key);
        buckets[bucketIndex].add(new Node(key, value));
        numItem += 1;
        if (((double) numItem/numBucket) > maxLoad) {
            resize((int)(numBucket*ENLARGE_FACTOR));
        }
    }

    private void resize(int newNumBucket) {
        Collection<Node>[] newBuckets = new Collection[newNumBucket];
        for (int i=0; i<newNumBucket; i+=1) {
            newBuckets[i] = createBucket();
        }
        for (int i=0; i<numBucket; i+=1) {
            for (Node node : buckets[i]) {
                int newBucketIndex = ((node.key.hashCode()%newNumBucket) + newNumBucket) % newNumBucket;
                newBuckets[newBucketIndex].add(node);
            }
        }
        buckets = newBuckets;
        numBucket = newNumBucket;
    }

    @Override
    public Set<K> keySet() {
        return keySet;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keySet.iterator();
    }

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private HashSet<K> keySet;
    private int numBucket = 16;
    private int numItem = 0;
    private double maxLoad = 0.75;
    private final double ENLARGE_FACTOR = 2;

    /** Constructors */
    public MyHashMap() {
        buckets = new Collection[numBucket];
        for (int i=0; i<numBucket; i+=1) {
            buckets[i] = createBucket();
        }
        keySet = new HashSet<>();
    }

    public MyHashMap(int initialSize) {
        numBucket = initialSize;
        buckets = new Collection[numBucket];
        for (int i=0; i<numBucket; i+=1) {
            buckets[i] = createBucket();
        }
        keySet = new HashSet<>();
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        numBucket = initialSize;
        this.maxLoad = maxLoad;
        buckets = new Collection[numBucket];
        for (int i=0; i<numBucket; i+=1) {
            buckets[i] = createBucket();
        }
        keySet = new HashSet<>();
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new HashSet<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return null;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

}
