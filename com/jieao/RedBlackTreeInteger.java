package com.jieao;
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
            return value < 0 ? 0 : value;
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
        if(node.key <= id2 && node.key >= id1){
            // check if node is in range
            sum += node.val;
        }
        if(node.key > id1 && node.key < id2){
            sum += inRangeHelper(node.left, id1, id2);
            sum += inRangeHelper(node.right, id1, id2);
        }else if(node.key >= id2){
            // check node's left child
            sum += inRangeHelper(node.left, id1, id2);
        }else if(node.key <= id1){
            // check node's left child
            sum += inRangeHelper(node.right, id1, id2);
        }
        return sum;
    }

    public TreeNode<Integer, Integer> next(Integer theId){
        TreeNode<Integer, Integer> res = nextHelper(this.root, theId, null);
        if(res == null) return new TreeNode<Integer,Integer>(Color.BLACK, 0, 0);
        else return res;
    }

    public TreeNode<Integer, Integer> nextHelper(TreeNode<Integer, Integer> node, Integer target, TreeNode<Integer, Integer> candidate){
        if(node == null) return candidate;
        if(node.key > target) candidate = node;
        if(node.key <= target) return nextHelper(node.right, target, candidate);
        else return nextHelper(node.left, target, candidate);
    }

    public TreeNode<Integer, Integer> previous(Integer theId){
        TreeNode<Integer, Integer> res = previousHelper(this.root, theId, null);
        if(res == null) return new TreeNode<Integer,Integer>(Color.BLACK, 0, 0);
        else return res;
    }

    public TreeNode<Integer, Integer> previousHelper(TreeNode<Integer, Integer> node, Integer target, TreeNode<Integer, Integer> candidate){
        if(node == null) return candidate;
        if(node.key < target) candidate = node;
        if(node.key < target) return previousHelper(node.right, target, candidate);
        else return previousHelper(node.left, target, candidate);
    }
}
