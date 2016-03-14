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
        System.out.println("next 2:"+rbt.next(2).key+","+rbt.next(2).val);
        System.out.println("next 4:"+rbt.next(4).key+","+rbt.next(4).val);
        System.out.println("next 6:"+rbt.next(6).key+","+rbt.next(6).val);

        System.out.println("previous 3:"+rbt.previous(3).key+","+rbt.previous(3).val);
        System.out.println("previous 1:"+rbt.previous(1).key+","+rbt.previous(1).val);
        System.out.println("previous 6:"+rbt.previous(6).key+","+rbt.previous(6).val);
    }
}
