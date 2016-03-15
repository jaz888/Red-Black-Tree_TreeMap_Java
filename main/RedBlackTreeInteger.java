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
        if(node.key > id1 && node.key < id2){
            sum += node.val;
            sum += inRangeHelper(node.left, id1, node.key-1);
            sum += inRangeHelper(node.right, node.key+1, id2);
        }else if(node.key >= id2){
            // check node's left child
            if(node.key == id2){
                sum += node.val;
            }
            sum += inRangeHelper(node.left, id1, id2);
        }else if(node.key <= id1){
            // check node's left child
            if(node.key == id1){
                sum += node.val;
            }
            sum += inRangeHelper(node.right, id1, id2);
        }
        return sum;
    }

    public TreeNode<Integer, Integer> next(Integer theId){
        TreeNode<Integer, Integer> x = root;
        if(root == null) return new TreeNode<Integer,Integer>(Color.BLACK, 0, 0);
        while(x != null){
            if(x.key <= theId){
                x = x.right;
            }else{
                if(x.left == null){
                    return new TreeNode<Integer, Integer>(Color.BLACK, x.key, x.val);
                }else{
                    x = x.left;
                }
            }
        }
        return new TreeNode<Integer,Integer>(Color.BLACK, 0, 0);
    }

    public TreeNode<Integer, Integer> previous(Integer theId){
        TreeNode<Integer, Integer> x = root;
        if(root == null) return new TreeNode<Integer,Integer>(Color.BLACK, 0, 0);
        while(x != null){
            if(x.key >= theId){
                x = x.left ;
            }else{
                // x.key < theId
                // now x is a candidate
                if(x.right == null){
                    // select x
                    return new TreeNode<Integer, Integer>(Color.BLACK, x.key, x.val);
                }else{
                    x = x.right;
                }
            }
        }
        return new TreeNode<Integer,Integer>(Color.BLACK, 0, 0);
    }
}
