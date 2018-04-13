import java.io.File;
import java.io.FileNotFoundException;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class Tree {
    public TreeNode root;

    public Tree() {
    }

    public Tree(TreeNode root) {
        this.root = root;
    }

    // 创建二叉树
    public void buildTree(String filePath) {
        Scanner scn = null;
        try {
            scn = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        root = createTree(root, scn);
    }

    // 先序遍历创建二叉树
    private TreeNode createTree(TreeNode root, Scanner scn) {
        String temp = scn.next();
        if (temp.trim().equals("#")) {
            return null;
        } else {
            root = new TreeNode(temp);
            root.setLeft(createTree(root.getLeft(), scn));
            root.setRight(createTree(root.getRight(), scn));
            return root;
        }
    }

    // 中序遍历（递归） —— 左、根、右
    public void inOrderTraverse() {
        inOrderTraverse(root);
    }

    public void inOrderTraverse(TreeNode root) {
        if (root != null) {
            inOrderTraverse(root.getLeft());
            System.out.print(root.getVal() + " ");
            inOrderTraverse(root.getRight());
        }
    }

    // 中序遍历（非递归）
    public void nrInorderTraverse() {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) { // 右子树不为空，或者栈不为空
            while (node != null) { // 从根或右子树开始，把所有的左孩子的左孩子全部入栈，直到找到最里面的左孩子
                stack.push(node);
                node = node.getLeft();
            }
            node = stack.pop(); // 出栈左孩子或者根节点 本质上都是从栈上输出元素
            System.out.print(node.getVal() + " ");
            node = node.getRight(); // 遍历之后，处理右子树内容。循环遍历右子树的左孩子等等
        }
    }

    // 先序遍历（递归） —— 根、左、右
    public void preOrderTraverse() {
        preOrderTraverse(root);
    }

    public void preOrderTraverse(TreeNode root) {
        if (root != null) {
            System.out.print(root.getVal() + " ");
            preOrderTraverse(root.getLeft());
            preOrderTraverse(root.getRight());
        }
    }

    // 先序遍历（非递归）
    public void nrPreOrderTraverse() {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) { // 右子树不为空，或者栈不为空
            while (node != null) {
                System.out.print(node.getVal() + " "); // 遍历根节点和左子树的一系列的“根”，并将它们全部入栈
                stack.push(node);
                node = node.getLeft();
            }
            node = stack.pop(); // 弹出此时最里面的左子树根节点，判断其右子树情况
            node = node.getRight(); // 处理右子树情况
        }
    }

    // 后序遍历（递归） —— 左、右、根
    public void postOrderTraverse() {
        postOrderTraverse(root);
    }

    public void postOrderTraverse(TreeNode root) {
        if (root != null) {
            postOrderTraverse(root.getLeft());
            postOrderTraverse(root.getRight());
            System.out.print(root.getVal() + " ");
        }
    }

    // 后序遍历（非递归）
    public void nrPostOrderTraverse() {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        TreeNode preNode = null; // 表示最近一次访问的节点
        while (node != null || !stack.isEmpty()) { // 右子树不为空，或者栈不为空（其子树已经处理过）
            while (node != null) { // 将根节点和左子树的根节点入栈，直到左子树为空
                stack.push(node);
                node = node.getLeft();
            }

            node = stack.peek(); // stack.peek()查找栈顶对象，但不移除它 查看栈顶元素
            if (node.getRight() == null || node.getRight() == preNode) { // 该节点的右子树为空，或者其右子树已经被访问过
                System.out.print(node.getVal() + " "); // 访问该节点
                node = stack.pop();
                preNode = node;
                node = null; // 将node置为空，用于判断栈中的下一元素
            } else {
                node = node.getRight();
            }
        }
    }

    // 按层次遍历
    public void levelTraverse() {
        levelTraverse(root);
    }

    public void levelTraverse(TreeNode root) {
        Queue<TreeNode> queue = new LinkedBlockingQueue<TreeNode>(); // Queue为接口
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll(); // queue.poll()获取并移除队头元素
            if (temp != null) {
                System.out.print(temp.getVal() + " ");
                if (temp.getLeft() != null) {
                    queue.add(temp.getLeft());
                }
                if (temp.getRight() != null) {
                    queue.add(temp.getRight());
                }
            }
        }
    }


    // 判断二叉树是否是完全二叉树
    public boolean isCompleteBinaryTree(){
        return isCompleteBinaryTree(root);
    }
    public boolean isCompleteBinaryTree(TreeNode root){
        if(root==null){
            return false;
        }
        Queue<TreeNode> queue=new LinkedBlockingQueue<>();
        boolean mustHaveNoChild=false;
        queue.add(root);
        boolean result=true;
        while(!queue.isEmpty()){
            TreeNode node=queue.poll();
            if(mustHaveNoChild){
                if(node.getLeft()!=null&&node.getRight()!=null){
                    result=false;
                    break;
                }
            }else {
                if(node.getLeft()!=null&&node.getRight()!=null){
                    queue.add(node.getLeft());
                    queue.add(node.getRight());
                }else if(node.getLeft()!=null&&node.getRight()==null){
//                    一旦某个元素没有右儿子，那么后面的节点必须没有左右儿子
                    mustHaveNoChild=false;
                    queue.add(node.getLeft());
                }else if(node.getLeft()==null&&node.getRight()!=null){
                    result=false;
                    break;
                }else {
                    mustHaveNoChild=true;
                }
            }
        }
        return result;
    }

//    求二叉树的深度
    public int getDepth(){
        return getDepth(root);
    }

    public int getDepth(TreeNode root){
        if(root==null){
            return 0;
        }
        int leftDepth=getDepth(root.getLeft());
        int rightDepth=getDepth(root.getRight());
        return Math.max(leftDepth+1,rightDepth+1);
    }




//    求二叉树节点个数
    public int getNodeNums(){
        return getNodeNums(root);
    }

    public int getNodeNums(TreeNode root){
        if(root==null){
            return 0;
        }else {
            return 1+getNodeNums(root.getLeft())+getNodeNums(root.getRight());
        }
    }

    //    判断是否是满二叉树
    public boolean isFullBinaryTree(){
        return isFullBinaryTree(root);
    }

    public boolean isFullBinaryTree(TreeNode treeNode){
        int depth=getDepth();
        int nodeNum=getNodeNums();
        if(Math.pow(2,depth)-1==nodeNum){
            return true;
        }else
            return false;
    }
    // 求二叉树第ｋ层的节点个数
    public int getNodeNumKthLevel(int k){
        return getNodeNumKthLevel(root,k);
    }

    public int getNodeNumKthLevel(TreeNode root,int k){
        if(root==null){
            return 0;
        }
        if(k==1){
            return 1;
        }
        return getNodeNumKthLevel(root.getLeft(),k-1)+getNodeNumKthLevel(root.getRight(),k-1);
    }
    // 判断两棵二叉树的结构是否相同，不考虑节点内容
    public boolean structureCmp(TreeNode root1,TreeNode root2){
        if(root1==null&&root2==null){
            return true;
        }
        if(root1==null&&root2!=null){
            return false;
        }
        if(root1!=null&&root2==null){
            return false;
        }
        boolean b1=structureCmp(root1.getLeft(),root2.getLeft());
        boolean b2=structureCmp(root1.getRight(),root2.getRight());
        if(b1==true&&b2==true){
            return true;
        }else
            return false;
    }

    public boolean isAVL(){
        return isAVL(root);
    }

    private boolean isAVL(TreeNode root){
        if(root==null){
            return true;
        }
        int leftHeight=getDepth(root.getLeft());
        int rightHeight=getDepth(root.getRight());
        boolean leftAVL=isAVL(root.getLeft());
        boolean rightAVL=isAVL(root.getRight());
        if(leftAVL&&rightAVL&&Math.abs(leftHeight-rightHeight)<=1){
            return true;
        }else
            return false;
    }

    //获取二叉树的镜像
    public TreeNode mirror(){
        return mirror(root);
    }

    private TreeNode mirror(TreeNode root){
        if(root==null){
            return null;
        }
        TreeNode left=mirror(root.getLeft());
        TreeNode right=mirror(root.getRight());
        root.setLeft(left);
        root.setRight(right);
        return root;
    }

    // 在树中查找某个节点
    public boolean findNode(TreeNode node){
        return findNode(root,node);
    }

    private boolean findNode(TreeNode root,TreeNode node){
        if(root==null||node==null){
            return false;
        }
        if(root==node){
            return true;
        }
        boolean find=findNode(root.getLeft(),node);
        if(!find){
            find=findNode(root.getRight(),node);
        }
        return find;
    }

    // 获取两个节点的最近祖先节点
    public TreeNode getCommonNode(TreeNode root,TreeNode node1,TreeNode node2){
        if(node1==node2){
            // 两个节点相同
            return node1;
        }
        if(findNode(root.getLeft(),node1)){
            if(findNode(root.getRight(),node2)){
                return root;
            }else {
                return getCommonNode(root.getLeft(),node1,node2);
            }
        }else{
            if(findNode(root.getLeft(),node2)){
                return root;
            }else
                return getCommonNode(root.getRight(),node1,node2);
        }
    }
}

