package ds;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestTraversals {

	@Test
	public void testSimple() {
		BinaryTree<Integer> tree = new BinaryTree<Integer>(1);
		tree.root.setLeft(new BinaryTreeNode<Integer>(0));
		tree.root.setRight(new BinaryTreeNode<Integer>(2));
		assertEquals(Arrays.asList(0, 1, 2), TreeTraversals.inorder(tree));
		assertEquals(Arrays.asList(0, 1, 2),
				TreeTraversals.inorder_norecursion(tree));

		assertEquals(Arrays.asList(1, 0, 2), TreeTraversals.preorder(tree));
		assertEquals(Arrays.asList(1, 0, 2),
				TreeTraversals.preorder_norecursion(tree));

		assertEquals(Arrays.asList(0, 2, 1), TreeTraversals.postorder(tree));
		assertEquals(Arrays.asList(0, 2, 1),
				TreeTraversals.postorder_norecursion(tree));
	}

	@Test
	public void testLeftNodesOnly() {
		BinaryTree<Integer> tree = new BinaryTree<Integer>(1);
		tree.root.setLeft(new BinaryTreeNode<Integer>(2));
		tree.root.getLeft().setLeft(new BinaryTreeNode<Integer>(3));
		tree.root.getLeft().getLeft().setLeft(new BinaryTreeNode<Integer>(4));
		assertEquals(Arrays.asList(4, 3, 2, 1), TreeTraversals.inorder(tree));
		assertEquals(Arrays.asList(4, 3, 2, 1),
				TreeTraversals.inorder_norecursion(tree));

		assertEquals(Arrays.asList(1, 2, 3, 4), TreeTraversals.preorder(tree));
		assertEquals(Arrays.asList(1, 2, 3, 4),
				TreeTraversals.preorder_norecursion(tree));
		
		assertEquals(Arrays.asList(4, 3, 2, 1), TreeTraversals.postorder(tree));
		assertEquals(Arrays.asList(4, 3, 2, 1),
				TreeTraversals.postorder_norecursion(tree));
	}

	@Test
	public void testRightNodesOnly() {
		BinaryTree<Integer> tree = new BinaryTree<Integer>(1);
		tree.root.setRight(new BinaryTreeNode<Integer>(2));
		tree.root.getRight().setRight(new BinaryTreeNode<Integer>(3));
		tree.root.getRight().getRight()
				.setRight(new BinaryTreeNode<Integer>(4));
		assertEquals(Arrays.asList(1, 2, 3, 4), TreeTraversals.inorder(tree));
		assertEquals(Arrays.asList(1, 2, 3, 4),
				TreeTraversals.inorder_norecursion(tree));

		assertEquals(Arrays.asList(1, 2, 3, 4), TreeTraversals.preorder(tree));
		assertEquals(Arrays.asList(1, 2, 3, 4),
				TreeTraversals.preorder_norecursion(tree));
		
		assertEquals(Arrays.asList(4, 3, 2, 1), TreeTraversals.postorder(tree));
		assertEquals(Arrays.asList(4, 3, 2, 1),
				TreeTraversals.postorder_norecursion(tree));
	}

	@Test
	public void testMultipleLevels() {
		BinaryTree<Integer> tree = new BinaryTree<Integer>(1);
		tree.root.setLeft(new BinaryTreeNode<Integer>(2));

		tree.root.getLeft().setLeft(new BinaryTreeNode<Integer>(3));
		tree.root.getLeft().setRight(new BinaryTreeNode<Integer>(4));

		tree.root.setRight(new BinaryTreeNode<Integer>(5));
		tree.root.getRight().setRight(new BinaryTreeNode<Integer>(6));

		assertEquals(Arrays.asList(3, 2, 4, 1, 5, 6),
				TreeTraversals.inorder(tree));
		assertEquals(Arrays.asList(3, 2, 4, 1, 5, 6),
				TreeTraversals.inorder_norecursion(tree));

		assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6),
				TreeTraversals.preorder(tree));
		assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6),
				TreeTraversals.preorder_norecursion(tree));
		
		assertEquals(Arrays.asList(3,4,2,6,5,1), TreeTraversals.postorder(tree));
		assertEquals(Arrays.asList(3,4,2,6,5,1),
				TreeTraversals.postorder_norecursion(tree));
	
	}
}
