package main;
public class BlackRedTree{
    public TreeNode root;
    public void leftRotate(TreeNode x){ // left rotate x
        /*
            x                         y
          /  \                      /  \
         a    y     -------->      x    c
            /  \                 /  \
           b    c               a    b
        */
        TreeNode y = x.right; // y = x's right child
        x.right = y.left; // set x's right child to b
        y.left.parent = x; // set b's parent to x
        y.parent = x.parent; // move y upward
        if(x.parent == null){ // x is root of the tree
            assert this.root == x;
            root = y;
        }else if(x == x.parent.left){ // x is on the left of x's parent
            x.parent.left = y;
        }else{ // x is on the right of x's parent
            x.parent.right = y;
        }
        y.left = x; // move x downward
        x.parent = y; // move x downward
    }
    public void rightRotate(TreeNode y){ // right rotate y

        /*
            x                         y
          /  \                      /  \
         a    y     <--------      x    c
            /  \                 /  \
           b    c               a    b
        */
        TreeNode x = y.left; // x = y's left child
        y.left = x.right; // set y's left child to b
        x.right.parent = y; // set b's parent to y
        x.parent = y.parent; // move x upward
        if(y.parent == null){ // y is the root of the Red Black Tree
            assert this.root == y;
            this.root = x;
        }else if(y == y.parent.right){ // y is on the right of y's parent
            y.parent.right = x;
        }else{ // y is on the left of y's parent
            y.parent.left = x;
        }
        x.right = y; // set x's right child to y
        y.parent =x; // set y's parent to x
    }
}
