package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private class Node {
        public Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }

        Key key;
        Value value;
        Node left;
        Node right;
        int height;
    }

    private Node root = null;
    private int count = 0;

    private Node put(Node x, Key key, Value value){
        if(x == null) {
            ++count;
            return new Node(key, value, 1);
        }
        if(key.compareTo(x.key) < 0)
            x.left = put(x.left, key, value);
        else if(key.compareTo(x.key) > 0)
            x.right = put(x.right, key, value);
        else
            x.value = value;

        fixHeight(x);
        x = balance(x);
        return x;
    }

    private Node get(Node x, Key key){
        if(x == null)
            return null;
        if(key.compareTo(x.key) < 0)
            return get(x.left, key);
        if(key.compareTo(x.key) > 0)
            return get(x.right, key);

        return x;
    }

    private int height(Node x){
        return x == null ? 0 : x.height;
    }

    private int factor(Node x){
        return height(x.left) - height(x.right);
    }

    private void fixHeight(Node x){
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }

    private Node rotateRight(Node y){
        Node x = y.left;
        y.left = x.right;
        x.right = y;

        fixHeight(y);
        fixHeight(x);
        return x;
    }

    private Node rotateLeft(Node x){
        Node y = x.right;
        x.right = y.left;
        y.left = x;

        fixHeight(x);
        fixHeight(y);
        return y;
    }

    private Node balance(Node x){
        if(factor(x) == 2){
            if(factor(x.left) < 0)
                x.left = rotateLeft(x.left);
            return rotateRight(x);
        }
        if(factor(x) == -2){
            if(factor(x.right) > 0)
                x.right = rotateRight(x.right);
            return rotateLeft(x);
        }
        return x;
    }

    private Node deleteMin(Node x){
        if(x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;
    }

    private Node min(Node x){
        if(x == null) return null;
        if(x.left == null)
            return x;
        return min(x.left);
    }

    private Node max(Node x){
        if(x == null) return null;
        if(x.right == null)
            return x;
        return max(x.right);
    }

    private Node ceil(Node x, Key key){
        if(x == null)
            return null;
        if(key.compareTo(x.key) == 0)
            return x;
        if(key.compareTo(x.key) > 0)
            return ceil(x.right, key);

        Node leftCeil = ceil(x.left, key);
        if(leftCeil != null && leftCeil.key.compareTo(key) >= 0)
            return leftCeil;

        return x;
    }

    private Node floor(Node x, Key key){
        if(x == null)
            return null;
        if(key.compareTo(x.key) == 0)
            return x;
        if(key.compareTo(x.key) < 0)
            return floor(x.left, key);

        Node rightFloor = floor(x.right, key);
        if(rightFloor != null && key.compareTo(rightFloor.key) >= 0)
            return rightFloor;

        return x;
    }

    private Node innerDelete(Node x){
        if(x.right == null)
            return x.left;
        if(x.left == null)
            return x.right;
        Node t = x;
        x = min(t.right);
        x.right = deleteMin(t.right);
        x.left = t.left;
        return x;
    }

    private Node delete(Node x, Key key){
        if(x == null)
            return null;
        if(key.compareTo(x.key) < 0)
            x.left = delete(x.left, key);
        if(key.compareTo(x.key) > 0)
            x.right = delete(x.right, key);
        if(key == x.key)
            x = innerDelete(x);
        return x;
    }

    @Override
    public Value get(@NotNull Key key) {
        Node tmp = get(root, key);
        return tmp == null ? null : tmp.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    @Override
    public Value remove(@NotNull Key key) {
        Node t = get(root, key);
        if(t == null) return null;
        root = delete(root, key);
        --count;
        return t.value;
    }

    @Override
    public Key min() {
        Node tmp = min(root);
        return tmp == null ? null : tmp.key;
    }

    @Override
    public Value minValue() {
        Node tmp = min(root);
        return tmp == null ? null : tmp.value;
    }

    @Override
    public Key max() {
        Node tmp = max(root);
        return tmp == null ? null : tmp.key;
    }

    @Override
    public Value maxValue() {
        Node tmp = max(root);
        return tmp == null ? null : tmp.value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node tmp = floor(root, key);
        return tmp == null ? null : tmp.key;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node tmp = ceil(root, key);
        return tmp == null ? null : tmp.key;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public int height() {
        return height(root);
    }
}
