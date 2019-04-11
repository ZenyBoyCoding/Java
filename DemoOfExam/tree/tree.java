package DemoOfExam.tree;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.Stack;

/**
 * @ClassName tree
 * @Description 一种非递归前序遍历、非递归中序、以及两种非递归后序遍历
 * @Auto 宗坚
 * @Date 2019/4/11 15:59
 * @Version 1.0
 */
public class tree {
    static PrintStream out = System.out; //打印

    static class Node{
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    static Node buildTree(int[] arr, int i){
        if(i >= arr.length || arr[i] == -1) return null;
        Node root = new Node(arr[i]);
        root.left = buildTree(arr, i * 2 + 1);
        root.right = buildTree(arr, i * 2 + 2);
        return root;
    }

    static Node buildTree(Scanner in){
        Node root = null;
        int val = in.nextInt();
        if(val != -1){
            root = new Node(val);
            root.left = buildTree(in);
            root.right = buildTree(in);
        }
        return root;
    }

    //前序
    static void preOrder(Node root){
        if(root == null) return;
        Stack<Node> stack = new Stack<>();
        Node p = root;
//        stack.push(root); // wrong
        while(p != null || !stack.isEmpty()){
            while(p != null){
                out.print(p.val + " ");
                stack.push(p); // 注意先推入
                p = p.left;
            }
            p = stack.pop();
            p = p.right;
        }
        out.println();
    }

    //中序
    static void inOrder(Node root){
        if(root == null) return;
        Stack<Node> stack = new Stack<>();
        Node p = root;
        while(p != null || !stack.isEmpty()){
            while(p != null){
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            out.print(p.val + " ");
            p = p.right;
        }
        out.println();
    }


    //后序第一种: 双栈: 可以实现 中-> 右-> 左, 然后再用一个栈逆转即可
    static void postOrder(Node root){
        if(root == null) return;
        Node p = root;
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        s1.push(root);
        while(!s1.isEmpty()){
            Node cur = s1.pop();
            s2.push(cur);
            if(cur.left != null ) s1.push(cur.left);
            if(cur.right != null ) s1.push(cur.right);
        }
        while(!s2.isEmpty()) out.print(s2.pop().val + " ");
        out.println();
    }


    // 后序第二种pre
    static void postOrder2(Node root){
        Stack<Node> s = new Stack<>();
        s.push(root);
        Node pre = null;
        while(!s.isEmpty()){
            Node cur = s.peek();
            if((cur.left == null && cur.right == null) ||
                    (pre != null && (pre == cur.left || pre == cur.right))){
                out.print(cur.val + " ");
                s.pop();
                pre = cur;
            }else {
                if(cur.right != null) s.push(cur.right);
                if(cur.left != null) s.push(cur.left);
            }
        }
        out.println();
    }

    public static void main(String[] args){
        //int[] arr = {1,2,3,4,5,6,7,8,-1,9,-1,10,-1,11,-1, -1,-1,-1,-1,-1,-1,-1,-1}; // 和下面一样
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, -1, 9, -1, 10, -1, 11, -1};
        Node root = buildTree(arr, 0);

        preOrder(root);
        inOrder(root);
        postOrder(root);
        postOrder2(root);

//      Scanner in = new Scanner(new BufferedInputStream(System.in));
//      树结构和上面相同,输入: 1 2 4 8 -1 -1 -1 5 9 -1 -1 -1 3 6 10 -1 -1 -1 7 11 -1 -1 -1
//      Node root2 = buildTree(in);
    }
}
