package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V> {

    BSTNode root;
    int size;

    public BSTMap() {
        this.root = null;
        this.size = 0;
    }

    private class BSTNode {
        K key;
        V value;
        BSTNode left;
        BSTNode right;
        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKeyInTree(this.root, key);
    }

    /**
     * Helper function for containsKey.
     */
    private boolean containsKeyInTree(BSTNode node, K key) {
        if (node == null) {
            return false;
        } else if (node.key.equals(key)) {
            return true;
        } else if (node.key.compareTo(key) < 0) {
            return containsKeyInTree(node.left, key);
        } else {
            return containsKeyInTree(node.right, key);
        }
    }

    @Override
    public V get(K key) {
        return getFromTree(this.root, key);
    }

    /**
     * Helper function for get.
     */
    private V getFromTree(BSTNode node, K key) {
        if (node == null) {
            return null;
        } else if (node.key.equals(key)) {
            return node.value;
        } else if (node.key.compareTo(key) < 0) {
            return getFromTree(node.left, key);
        } else {
            return getFromTree(node.right, key);
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void put(K key, V value) {
        if (containsKey(key) == false) {
            this.root = putToTree(this.root, key, value);
            this.size += 1;
        }
    }

    /**
     * Helper function for put.
     */
    private BSTNode putToTree(BSTNode node, K key, V value) {
        if (node == null) {
            return new BSTNode(key, value);
        } else if (node.key.compareTo(key) < 0) {
            node.left = putToTree(node.left, key, value);
        } else {
            node.right = putToTree(node.right, key, value);
        }
        return node;
    }

    /**
     * Print out BSTMap in order of increasing Key.
     */
    public void printInOrder() {

    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }
}
