package main;

public class TreeNode<K extends Comparable, V> {
    public Color color;
    public K key;
    public V val;
    public TreeNode<K, V> parent;
    public TreeNode<K, V> left;
    public TreeNode<K, V> right;

    public TreeNode(){
        // set RED as default
        this.color = Color.RED;
        this.key = null;
        this.val = null;
        this.left = null;
        this.right = null;
    }
    public TreeNode(Color color, K key, V val) {
        this.color = color;
        this.key = key;
        this.val = val;
        this.left = null;
        this.right = null;
    }


}
