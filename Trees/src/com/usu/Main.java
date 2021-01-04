package com.usu;

import javax.xml.transform.Source;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        BinaryTree tree = new BinaryTree(10);
//        tree.insert(15);
//        tree.insert(7);
//        tree.insert(9);
//        tree.insert(17);
//        tree.insert(12);
//        tree.insert(2);
//        System.out.println(tree.find(15));
//        System.out.println(tree.find(22));
//        tree.print();
        Tree tree = new Tree();
        tree.generate(5);
        tree.print();
    }
}
