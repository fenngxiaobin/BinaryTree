package com.fxb.BST;

import java.util.Queue;

public class BST<Key extends Comparable<Key>,Value> {
    private Node root;
    class Node{
        private Key key;
        private Node left,right;
        private Value val;
        private int N;

        public Node(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            this.N = n;
        }
    }

    public int size(){
        return size(root);
    }

    private int size(Node x){
        if(x==null)
            return 0;
        else
            return x.N;
    }

    /**
     * 根据key从BST中查找元素
     * @param key
     * @return
     */
    public Value get(Key key){
        return get(root,key);
    }

    private Value get(Node x,Key key){
        if(x==null){
            return null;
        }
        int cmp=key.compareTo(x.key);
        if(cmp<0){
            return get(x.left,key);
        }else if(cmp>0){
            return get(x.right,key);
        }else
            return x.val;
    }

    /**
     * 将key-value放入BST中
     * @param key
     * @param value
     */
    public void put(Key key,Value value){
        root=put(root,key,value);
    }

    private Node put(Node x,Key key,Value value){
        if(x==null){
            return new Node(key,value,1);
        }
        int cmp=key.compareTo(x.key);
        if(cmp<0){
            x.left=put(x.left,key,value);
        }else if(cmp>0){
            x.right=put(x.right,key,value);
        }else {
            x.val=value;
        }
        //更新节点数
        x.N=size(x.left)+size(x.right)+1;
        return x;
    }

    public Key min(){

    }

    private Node min(Node x){

    }

    public Key floor(){

    }

    private Node floor(Node x,Key key){

    }

    /**
     * 排名为k 是指树中有k个小于它的键　即第ｋ+１大的键
     * @param k
     * @return
     */
    public Key select(int k){
        //判断空
        if(select(root,k)==null){
            return null;
        }else
            return select(root,k).key;
    }

    private Node select(Node x,int k){
        if(x==null)
            return null;
        int t=size(x.left);
        if(k<t){
            return select(x.left,k);
        }else if(k>t){
            return select(x.right,k-t-1);
        }else
            return x;
    }

    /**
     * rank()是select()的逆方法
     * @param key
     * @return
     */
    public int rank(Key key){
        try {
            return rank(root,key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int rank(Node x,Key key) throws Exception {
        if(x==null){
            throw new Exception("root can not be null!");
        }
        if(x.key.compareTo(key)==0){
            return size(x.left);
        }else if(x.key.compareTo(key)>0){
            return rank(x.left,key);
        }else
            return size(x.left)+1+rank(x.right,key);
    }

    public void deleteMin(){

    }

    private Node deleteMin(Node x){

    }

    public void delete(Key key){

    }

    public Iterable<Key> keys(){

    }

    public Iterable<Key> keys(Key lo,Key hi){

    }

    private void keys(Node x, Queue<Key> queue,Key lo,Key hi){

    }
}
