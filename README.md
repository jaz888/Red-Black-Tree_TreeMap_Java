# Red-Black-Tree_TreeMap_Java
A Java TreeMap implementation based on Red-Black Tree.
## Usage:
- Import Color.java, TreeNode.java, RedBlackTree.java seperately.
- Declaration: RedBlackTree<K*, V> rbt = new RedBlackTree<>();
- * type K has to implement Comparable interface and override compareTo(T o) method.

return type           | method        | parameters      | Time complexity
-------------         | ------------- | ----------------|-----------
V                     | insert        | K key, V value  |O(log n)
V (null if not exits) | delete        | K key           |O(log n)
V (null if not exits) | get           | K key           |O(log n)
String (order level)  | toString      | /               |O(n*log n)
