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

## Test Input File and Interactive Commands
- cd folder/that/contains/bbst.java
- javac bbst.javac
- java bbst test_1000000.txt
- support commands in terminal:
    - inrease ID Count
    - reduce ID Count
    - count ID
    - inrange ID1 ID2
    - next ID
    - previous ID
