package main;
public class BlackRedTree<K extends Comparable, V>{
    public TreeNode<V> root;
    private void leftRotate(TreeNode<K, V> x){ // left rotate x
        /*
            x                         y
          /  \                      /  \
         a    y     -------->      x    c
            /  \                 /  \
           b    c               a    b
        */
        TreeNode<K, V> y = x.right; // y = x's right child
        x.right = y.left; // set x's right child to b
        y.left.parent = x; // set b's parent to x
        y.parent = x.parent; // move y upward
        if(x.parent == null){ // x is root of the tree
            assert this.root == x;
            root = y;
        }else if(x == x.parent.left){ // x is a left child of parent
            x.parent.left = y;
        }else{ //x is a right child of parent
            x.parent.right = y;
        }
        y.left = x; // move x downward
        x.parent = y; // move x downward
    }
    private void rightRotate(TreeNode<K, V> y){ // right rotate y

        /*
            x                         y
          /  \                      /  \
         a    y     <--------      x    c
            /  \                 /  \
           b    c               a    b
        */
        TreeNode<K, V> x = y.left; // x = y's left child
        y.left = x.right; // set y's left child to b
        x.right.parent = y; // set b's parent to y
        x.parent = y.parent; // move x upward
        if(y.parent == null){ // y is root of tree
            assert this.root == y;
            this.root = x;
        }else if(y == y.parent.right){ // y a right child
            y.parent.right = x;
        }else{ // y a left child
            y.parent.left = x;
        }
        x.right = y; // set x's right child to y
        y.parent =x; // set y's parent to x
    }



    public V insert(K key, V val){
        TreeNode<K, V> y;
        TreeNode<K, V> x = this.root;
        while(x != null){
            y = x;
            if(key.compareTo(x.key) == -1){ // key < x.key
                x = x.left;
            }else if(key.compareTo(x.key) == 1){ // key > x.key
                x = x.right;
            }else{ // key == x.key
                x.val = val;
                return val;
            }
        }
        // new node to store key and value
        TreeNode<K, V> z = new TreeNode<>(RED, key, val);
        // x == null, need to replace x with z
        // y is x's parent
        z.parent = y;
        if(y == null){ // the RB tree is empty
            root = z;
        }else if(z.key.compareTo(y.key) == -1){ // z < y, z should be y's left child
            y.left = z;
        }else{ // z > y, z should be y's right child
            y.right = z;
        }
        if(insertFixup(z)){
            return val;
        }else{
            return null;
        }
    }
    private boolean insertFixup(TreeNode<K, V> z){
        while(z.parent != null && z.parent.parent!= null && z.parent.color == RED){
            if(z.parent == z.parent.parent.left){ // z's parent is left child of grandparent
                TreeNode<K, V> y = z.parent.parent.right;
                if(y != null){
                    if(y.color == RED){
                        z.parent.color = BLACK;
                        y.color = BLACK;
                        z.parent.parent.color = RED;
                        z = z.parent.parent;
                    }else{
                        if(z == z.parent.right){
                            z = z.parent;
                            leftRotate(z);
                        }
                        z.parent.color = BLACK;
                        z.parent.parent.color = RED;
                        rightRotate(z.parent.parent);
                    }
                }
            }else{ // z's parent is right child of grandparent
                TreeNode<K, V> y = z.parent.parent.left;
                if(y != null){
                    if(y.color == RED){
                        z.parent.color = BLACK;
                        y.color = BLACK;
                        z.parent.parent.color = RED;
                        z = z.parent.parent;
                    }else{
                        if(z == z.parent.left){
                            z = z.parent;
                            rightRotate(z);
                        }
                        z.parent.color = BLACK;
                        z.parent.parent.color = RED;
                        leftRotate(z.parent.parent);
                    }
                }
            }
        }
        this.root.color = BLACK; // root has to be black
    }
}
