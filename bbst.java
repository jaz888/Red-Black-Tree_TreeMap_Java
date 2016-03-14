// this is the main entrance of the program
// need to support redirected input from a file "file-name" which specified in terminal
// the file "file-name" contains the initial sorted list

import main.*;

public class bbst {
    public static void main(String[] args) {
        RedBlackTreeInteger rbt = new RedBlackTreeInteger();
        rbt.insert(1, 4);
        rbt.insert(2, 2);
        rbt.insert(5, 1);
        rbt.insert(6, 2);
        rbt.insert(3, 2);
        rbt.insert(1, 2);
        System.out.println(rbt.toString());
        rbt.delete(0);
        System.out.println(rbt.toString());
        rbt.delete(2);
        System.out.println(rbt.toString());
        rbt.increase(2,4);
        System.out.println(rbt.toString());
        rbt.reduce(2,2);
        System.out.println(rbt.toString());
        System.out.println("count 2:"+rbt.count(2));
        System.out.println("inrange 3 5:"+rbt.inRange(3,5));
        System.out.println("inrange 2 7:"+rbt.inRange(2,7));
    }
}
