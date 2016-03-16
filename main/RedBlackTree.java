package main;
import java.util.LinkedList;
public class RedBlackTree<K extends Comparable<? super K>, V> {
    public TreeNode<K, V> root;

    public void leftRotate(TreeNode<K, V> x) { // left rotate x
        /*
            x                         y
          /  \                      /  \
         a    y     -------->      x    c
            /  \                 /  \
           b    c               a    b
        */
        // y = x's right child
        TreeNode<K, V> y = x.right;
        // set x's right child to b
        x.right = y.left;
        // set b's parent to x
        if(y.left != null)y.left.parent = x;
        // move y upward
        y.parent = x.parent;
        if (x.parent == null) {
            // x is root of the tree
            assert this.root == x;
            root = y;
        } else if (x == x.parent.left) {
            // x is a left child of parent
            x.parent.left = y;
        } else {
            //x is a right child of parent
            x.parent.right = y;
        }
        // move x downward
        y.left = x;
        // move x downward
        x.parent = y;
    }

    public void rightRotate(TreeNode<K, V> y) { // right rotate y

        /*
            x                         y
          /  \                      /  \
         a    y     <--------      x    c
            /  \                 /  \
           b    c               a    b
        */
        // x = y's left child
        TreeNode<K, V> x = y.left;
        // set y's left child to b
        y.left = x.right;
        // set b's parent to y
        if(x.right != null)x.right.parent = y;
        // move x upward
        x.parent = y.parent;
        if (y.parent == null) {
            // y is root of tree
            assert this.root == y;
            this.root = x;
        } else if (y == y.parent.right) {
            // y a right child
            y.parent.right = x;
        } else {
            // y a left child
            y.parent.left = x;
        }
        // set x's right child to y
        x.right = y;
        // set y's parent to x
        y.parent = x;
    }


    public V insert(K key, V val) {
        if(key == null) return null;
        TreeNode<K, V> y = null;
        TreeNode<K, V> x = this.root;
        while (x != null) {
            y = x;

            if (key.compareTo(x.key) == -1) {
                // key < x.key
                x = x.left;
            } else if (key.compareTo(x.key) == 1) {
                // key > x.key
                x = x.right;
            } else {
                // key == x.key
                x.val = val;
                return val;
            }
        }
        // new node to store key and value
        TreeNode<K, V> z = new TreeNode<>(Color.RED, key, val);
        // x == null, need to replace x with z
        // y is x's parent
        z.parent = y;

        if (y == null) {
            // the RB tree is empty
            root = z;
        } else if (z.key.compareTo(y.key) == -1) {
            // z < y, z should be y's left child
            y.left = z;
        } else {
            // z > y, z should be y's right child
            y.right = z;
        }
        insertFixup(z);
        return val;
    }

    public void insertFixup(TreeNode<K, V> z) {
        while (z.parent != null && z.parent.parent != null && z.parent.color == Color.RED) {
            if (z.parent == z.parent.parent.left) {
                // z's parent is left child of grandparent
                TreeNode<K, V> y = z.parent.parent.right;
                if (y != null && y.color == Color.RED) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    // y == null || y.color == black
                    if (z == z.parent.right) {
                        z = z.parent;
                        leftRotate(z);
                    }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    rightRotate(z.parent.parent);
                }
            } else {
                // z's parent is right child of grandparent
                TreeNode<K, V> y = z.parent.parent.left;
                if (y != null && y.color == Color.RED) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    // y == null || y.color == black
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    leftRotate(z.parent.parent);
                }
            }
        }
        // root has to be black
        this.root.color = Color.BLACK;
    }

    public TreeNode<K, V> higherEntry(K key){
        TreeNode<K, V> x = this.find(key);
        if(x == null) return null;
        if(x.right != null){
            // find the leftmost child of x.right
            x = x.right;
            while(x.left != null){
                x = x.left;
            }
            return x;
        }else{
            // find find x's ancestor of which x is in the left portion
            while(x.parent != null){
                if(x.parent.left == x)return x;
                x = x.parent;
            }
            return null;
        }
    }

    public TreeNode<K, V> find(K key){
        TreeNode<K, V> x = this.root;
        while(x != null){
            if(key.compareTo(x.key) == 0){
                // key == x.key
                return x;
            }else if(key.compareTo(x.key) == -1){
                // key < x.key
                x = x.left;
            }else{
                // key > x.key
                x = x.right;
            }
        }
        return null;
    }

    public V get(K key){
        TreeNode<K,V> res = find(key);
        if(res == null) return null;
        return res.val;
    }

    public TreeNode<K, V> getMinimum(TreeNode<K, V> x){
        if(x == null) return null;
        while(x.left != null) x = x.left;
        return x;
    }

    public TreeNode<K, V> getSuccessor(TreeNode<K, V> x){
        if(x == null) return null;
        if(x.right != null){
            return getMinimum(x.right);
        }
        TreeNode<K, V> y = x.parent;
        while(y != null && x == y.right){
            x = y;
            y = y.parent;
        }
        return y;
    }

    public V delete(K key){
        if(key == null)return null;
        // need to remove z
        TreeNode<K, V> z = this.find(key);
        if(z == null){
            // key is not found
            return null;
        }
        //deleting z
        V removedVal = z.val;
        TreeNode<K, V> y = null;
        if(z.left == null || z.right == null){
            // if z does not have left child or right child
            // then y = z
            y = z;
        }else{
            y = getSuccessor(z);
        }
        TreeNode<K, V> x = null;
        if(y.left != null){
            x = y.left;
        }else{
            x = y.right;
        }
        if(x != null)x.parent = y.parent;

        if(y.parent == null){
            this.root = x;
        }else if(y == y.parent.left){
            y.parent.left = x;
        }else{
            y.parent.right = x;
        }

        if(y != z){
            z.key = y.key;
            z.val = y.val;
        }

        if(y.color == Color.BLACK){
            deleteFixup(x);
        }
        return removedVal;
    }

    public void deleteFixup(TreeNode<K, V> x){
        TreeNode<K, V> w = null;
        while(x != this.root && x.color == Color.BLACK){
            if(x == x.parent.left){
                // x is left child of parent
                w = x.parent.right;
                if(w.color == Color.RED){
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                if((w.left == null || w.left.color == Color.BLACK) && (w.right == null || w.right.color == Color.BLACK)){
                    w.color = Color.RED;
                    x = x.parent;
                }else if(w.right == null || w.right.color == Color.BLACK){
                    if(w.left != null)w.left.color = Color.BLACK;
                    w.color = Color.RED;
                    rightRotate(w);
                    w = x.parent.right;
                    x.parent.color = Color.BLACK;
                    if(w.right != null)w.right.color = Color.BLACK;
                    leftRotate(x.parent);
                    x = this.root;
                }
            }else{
                // x is right child of parent
                w = x.parent.left;
                if(w.color == Color.RED){
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                if((w.right == null ||w.right.color == Color.BLACK) && (w.left == null || w.left.color == Color.BLACK)){
                    w.color = Color.RED;
                    x = x.parent;
                }else if(w.left == null || w.left.color == Color.BLACK){
                    if(w.right != null)w.right.color = Color.BLACK;
                    w.color = Color.RED;
                    leftRotate(w);
                    w = x.parent.left;
                    x.parent.color = Color.BLACK;
                    if(w.left != null)w.left.color = Color.BLACK;
                    rightRotate(x.parent);
                    x = this.root;
                }
            }
        }
        x.color = Color.BLACK;
    }




    @Override
    public String toString(){
        // traverse tree in level order
        LinkedList<TreeNode<K, V>> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        queue.addLast(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size != 0){
                TreeNode<K, V> node = queue.removeFirst();
                size--;
                if(node == null){
                    sb.append("#,");
                }else{
                    sb.append(node.key.toString());
                    sb.append("(");
                    sb.append(node.val.toString());
                    sb.append(",");
                    sb.append(node.color);
                    sb.append(")");
                    sb.append(",");
                    queue.addLast(node.left);
                    queue.addLast(node.right);
                }
            }
        }
        return sb.toString();
    }
}
