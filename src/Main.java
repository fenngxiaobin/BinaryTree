public class Main {

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.buildTree("completeBinaryTree.txt");
        Tree tree1=new Tree();
        tree1.buildTree("completeBinaryTree1.txt");
        System.out.println("中序遍历");
        tree.inOrderTraverse();
        System.out.println("\n中序遍历（非递归）");
        tree.nrInorderTraverse();
        System.out.println("\n先序遍历");
        tree.preOrderTraverse();
        System.out.println("\n先序遍历（非递归）");
        tree.nrPreOrderTraverse();
        System.out.println("\n后序遍历");
        tree.postOrderTraverse();
        System.out.println("\n后序遍历（非递归）");
        tree.nrPostOrderTraverse();
        System.out.println("\n层次遍历");
        tree.levelTraverse();
        System.out.println("\n求二叉树的深度");
        System.out.print(tree.getDepth());
        System.out.println("\n总结点个数");
        System.out.print(tree.getNodeNums());
        System.out.println("\n判断是否是完全二叉树");
        System.out.print(tree.isCompleteBinaryTree());
        System.out.println("\n是否是满二叉树");
        System.out.println(tree.isFullBinaryTree());
        System.out.println("\n二叉树第ｋ层节点的个数");
        System.out.println("k=3");
        System.out.println(tree.getNodeNumKthLevel(3));
        System.out.println("\n判断两棵树的结构是否相同");
        System.out.println(tree.structureCmp(tree.root,tree1.root));
        System.out.println("\n判断二叉树是否是平衡二叉树");
        System.out.println(tree.isAVL());
    }
}
