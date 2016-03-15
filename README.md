# red-black-tree-Java
A Java HashMap implementation based on Red-Black Tree.
## Usage:
-Import Color.java, TreeNode.java, RedBlackTree.java seperately.
-Declaration: RedBlackTree<K*, V> rbt = new RedBlackTree<>();
-* type K has to implement Comparable interface and override compareTo(T o) method.

return type           | method        | parameters                  
-------------         | ------------- | ---------------------------
V                     | insert        | K key, V value
V (null if not exits) | delete        | K key 
V (null if not exits) | get           | K key 
String (order level)  | toString      | /

