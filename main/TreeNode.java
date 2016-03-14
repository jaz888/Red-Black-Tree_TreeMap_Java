package main;

public class TreeNode<K extends Comparable, V>{
    public Color color;
    public K key;
    public V val;
    public TreeNode parent;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(Color color, K key, V val){
        this.color = color;
        this.key = key;
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
