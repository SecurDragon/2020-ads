package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;

        public Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    Node root = null;
    int count = 0;

    boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.color = x.color;
        x.color = RED;
        return y;
    }

    Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        x.color = y.color;
        y.color = RED;
        return x;
    }

    Node flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
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

    Node fixUp(Node x) {
        if(x == null)
            return null;
        if (isRed(x.right) && !isRed(x.left))
            x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left))
            x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right))
            flipColors(x);
        return x;
    }

    Node moveRedLeft(Node x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    Node deleteMax(Node x) {
        if (isRed(x.left))
            x = rotateRight(x);
        if (x.right == null)
            return null;
        if (!isRed(x.right) && !isRed(x.right.left))
            x = moveRedRight(x);
        x.right = deleteMax(x.right);
        return fixUp(x);
    }

    Node deleteMin(Node x) {
        if (x.left == null)
            return null;
        if (!isRed(x.left) && !isRed(x.left.left))
            x = moveRedLeft(x);
        x.left = deleteMin(x.left);
        return fixUp(x);
    }

    Node delete(Node x, Key key) {
        if (x == null)
            return null;
        int keyComparison = key.compareTo(x.key);
        if (keyComparison < 0) {
            if (x.left != null) {
                if (!isRed(x.left) && !isRed(x.left.left))
                    x = moveRedLeft(x);
                x.left = delete(x.left, key);
            }
        }
        else {
            if (isRed(x. left ) ) {
                x = rotateRight(x) ;
                x = delete(x. right, key);
            }
            else if (x.key == key && x.right == null)
                return null;
            else {
                if (x. right != null && !isRed(x.right) && !isRed(x.right.left ))
                    x = moveRedRight(x);
                if (x.key == key) {
                    Node min = min(x.right);
                    x.key = min.key;
                    x.value = min.value;
                    x.right = deleteMin(x.right);
                }
                x.right = delete(x.right, key);
            }
        }
        return fixUp(x);
    }

    Node put(Node x, Key key, Value value) {
        if (x == null) {
            ++count;
            return new Node(key, value, RED);
        }
        int keyComparison = key.compareTo(x.key);
        if (keyComparison < 0)
            x.left = put(x.left, key, value);
        else if (keyComparison > 0)
            x.right = put(x.right, key, value);
        else
            x.value = value;
        return fixUp(x);
    }

    private Node ceil(Node x, Key key){
        if(x == null)
            return null;
        int keyComparison = key.compareTo(x.key);
        if(keyComparison == 0)
            return x;
        if(keyComparison > 0)
            return ceil(x.right, key);

        Node leftCeil = ceil(x.left, key);
        if(leftCeil != null && leftCeil.key.compareTo(key) >= 0)
            return leftCeil;

        return x;
    }

    private Node floor(Node x, Key key){
        if(x == null)
            return null;
        int keyComparison = key.compareTo(x.key);
        if(keyComparison == 0)
            return x;
        if(keyComparison < 0)
            return floor(x.left, key);

        Node rightFloor = floor(x.right, key);
        if(rightFloor != null && key.compareTo(rightFloor.key) >= 0)
            return rightFloor;

        return x;
    }

    private Node get(Node x, Key key){
        if(x == null)
            return null;
        int keyComparison = key.compareTo(x.key);
        if(keyComparison < 0)
            return get(x.left, key);
        if(keyComparison > 0)
            return get(x.right, key);

        return x;
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node tmp = get(root, key);
        return tmp == null ? null : tmp.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Node t = get(root, key);
        if(t == null) return null;
        root = delete(root, key);

        if(root != null)
            root.color = BLACK;

        --count;
        return t.value;
    }

    @Nullable
    @Override
    public Key min() {
        Node tmp = min(root);
        return tmp == null ? null : tmp.key;
    }

    @Nullable
    @Override
    public Value minValue() {
        Node tmp = min(root);
        return tmp == null ? null : tmp.value;
    }

    @Nullable
    @Override
    public Key max() {
        Node tmp = max(root);
        return tmp == null ? null : tmp.key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node tmp = max(root);
        return tmp == null ? null : tmp.value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node tmp = floor(root, key);
        return tmp == null ? null : tmp.key;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node tmp = ceil(root, key);
        return tmp == null ? null : tmp.key;
    }

    @Override
    public int size() {
        return count;
    }
}
