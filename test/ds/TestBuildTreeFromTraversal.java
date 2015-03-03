package ds;
import static org.junit.Assert.*;

import org.junit.Test;
public class TestBuildTreeFromTraversal {

    @Test
    public void testSingleElement() {
        BinaryTree<String> expected = new BinaryTree<String>("A");
        String[] inOrderTraversal = new String[]{"A"};
        String[] preOrderTraversal = new String[]{"A"};
        assertEquals(expected, TreeFromTraversal.fromTraversal(inOrderTraversal, preOrderTraversal));
    }

    @Test
    public void testSingleLevel() {
        BinaryTree<String> expected = new BinaryTree<String>("A");
        expected.getRoot().setLeft(new BinaryTreeNode<String>("B"));
        expected.getRoot().setRight(new BinaryTreeNode<String>("C"));
        String[] inOrderTraversal = "B,A,C".split(",");
        String[] preOrderTraversal = "A,B,C".split(",");
        assertEquals(expected, TreeFromTraversal.fromTraversal(inOrderTraversal, preOrderTraversal));
    }

    @Test
    public void testMultiLevelHierarchy() {
        BinaryTree<String> expected = new BinaryTree<String>("A");
        expected.getRoot().setLeft(new BinaryTreeNode<String>("B"));
        expected.getRoot().getLeft().setLeft(new BinaryTreeNode<String>("D"));
        expected.getRoot().getLeft().setRight(new BinaryTreeNode<String>("E"));
        expected.getRoot().setRight(new BinaryTreeNode<String>("C"));
        expected.getRoot().getRight().setRight(new BinaryTreeNode<String>("F"));
        String[] inOrderTraversal = "D,B,E,A,C,F".split(",");
        String[] preOrderTraversal = "A,B,D,E,C,F".split(",");
        assertEquals(expected, TreeFromTraversal.fromTraversal(inOrderTraversal, preOrderTraversal));
    }

    @Test
    public void testLeftChildrenOnly() {
        BinaryTree<String> expected = new BinaryTree<String>("A");
        expected.getRoot().setLeft(new BinaryTreeNode<String>("B"));
        expected.getRoot().getLeft().setLeft(new BinaryTreeNode<String>("C"));
        expected.getRoot().setRight(new BinaryTreeNode<String>("D"));
        String[] inOrderTraversal = "C,B,A,D".split(",");
        String[] preOrderTraversal = "A,B,C,D".split(",");
        assertEquals(expected, TreeFromTraversal.fromTraversal(inOrderTraversal, preOrderTraversal));
    }
}
