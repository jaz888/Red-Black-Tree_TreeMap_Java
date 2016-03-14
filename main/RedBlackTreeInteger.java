package main;
public class RedBlackTreeInteger extends RedBlackTree<Integer, Integer>{

    public Integer increase(Integer key, Integer addition){
        TreeNode<Integer,Integer> node = find(key);
        if(node == null){
            // key does not exist
            if(addition <= 0){
                return 0;
            }else{
                insert(key, addition);
            }
            return addition;
        }else{
            // key exists
            node.val = node.val + addition;
            if(node.val <= 0){
                delete(key);
            }
            return node.val;
        }
    }

    public Integer reduce(Integer key, Integer substraction){
        TreeNode<Integer,Integer> node = find(key);
        if(node == null){
            // key does not exist
            return 0;
        }else{
            // key exists
            node.val = node.val - substraction;
            Integer value = node.val;
            if(node.val <= 0){
                delete(key);
            }
            return value;
        }
    }

    public Integer count(Integer key){
        TreeNode<Integer,Integer> node = find(key);
        if(node == null) return 0;
        else return node.val;
    }

    public Integer inRange(Integer id1, Integer id2){
        return inRangeHelper(root, id1, id2);
    }

    public Integer inRangeHelper(TreeNode<Integer, Integer> node, Integer id1, Integer id2){
        if(id1 > id2 || node == null) return 0;
        Integer sum = 0;
        System.out.println("node.key="+node.key);
        System.out.println("id1="+id1);
        System.out.println("id2="+id2);
        System.out.println();
        if(node.key > id1 && node.key < id2){
            System.out.println("add");
            sum += node.val;
            sum += inRangeHelper(node.left, id1, node.key-1);
            sum += inRangeHelper(node.right, node.key+1, id2);
        }else if(node.key >= id2){
            // check node's left child
            if(node.key == id2){
                System.out.println("add");
                sum += node.val;
            }
            sum += inRangeHelper(node.left, id1, id2);
        }else if(node.key <= id1){
            // check node's left child
            if(node.key == id1){
                System.out.println("add");
                sum += node.val;
            }
            sum += inRangeHelper(node.right, id1, id2);
        }
        System.out.println("sum="+sum);
        return sum;
    }
}
