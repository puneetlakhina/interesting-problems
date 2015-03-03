package ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeTraversals {

	public static <T> List<T> inorder(BinaryTree<T> tree) {
		List<T> lst = new ArrayList<>();
		inorder_recurse(tree.root, lst);
		return lst;
	}

	private static <T> void inorder_recurse(BinaryTreeNode<T> root,
			List<T> accumulatedList) {
		if (root == null) {
			return;
		}
		if (root.isLeaf()) {
			accumulatedList.add(root.getVal());
			return;
		}
		inorder_recurse(root.getLeft(), accumulatedList);
		accumulatedList.add(root.getVal());
		inorder_recurse(root.getRight(), accumulatedList);
	}

	public static <T> List<T> preorder(BinaryTree<T> tree) {
		List<T> lst = new ArrayList<>();
		preorder_recurse(tree.root, lst);
		return lst;
	}

	private static <T> void preorder_recurse(BinaryTreeNode<T> root,
			List<T> accumulatedList) {
		if (root == null) {
			return;
		}
		if (root.isLeaf()) {
			accumulatedList.add(root.getVal());
			return;
		}
		accumulatedList.add(root.getVal());
		preorder_recurse(root.getLeft(), accumulatedList);
		preorder_recurse(root.getRight(), accumulatedList);
	}

	public static <T> List<T> postorder(BinaryTree<T> tree) {
		List<T> lst = new ArrayList<>();
		postorder_recurse(tree.root, lst);
		return lst;
	}

	private static <T> void postorder_recurse(BinaryTreeNode<T> root,
			List<T> accumulatedList) {
		if (root == null) {
			return;
		}
		if (root.isLeaf()) {
			accumulatedList.add(root.getVal());
			return;
		}
		postorder_recurse(root.getLeft(), accumulatedList);
		postorder_recurse(root.getRight(), accumulatedList);
		accumulatedList.add(root.getVal());
	}

	public static <T> List<T> inorder_norecursion(BinaryTree<T> tree) {
		List<T> lst = new ArrayList<>();
		BinaryTreeNode<T> current = tree.root;
		Stack<BinaryTreeNode<T>> stack = new Stack<>();
		while (true) {
			if (current != null) {
				stack.push(current);
				current = current.getLeft();
			} else {
				if (!stack.isEmpty()) {
					BinaryTreeNode<T> node = stack.pop();
					lst.add(node.getVal());
					current = node.getRight();
				} else {
					break;
				}
			}
		}
		return lst;
	}

	public static <T> List<T> preorder_norecursion(BinaryTree<T> tree) {
		List<T> lst = new ArrayList<>();
		BinaryTreeNode<T> current = tree.root;
		Stack<BinaryTreeNode<T>> stack = new Stack<>();
		while (true) {
			if (current != null) {
				stack.push(current);
				lst.add(current.getVal());
				current = current.getLeft();
			} else if (!stack.isEmpty()) {
				BinaryTreeNode<T> node = stack.pop();
				current = node.getRight();
			} else {
				break;
			}
		}
		return lst;
	}

	public static <T> List<T> postorder_norecursion(BinaryTree<T> tree) {
		List<T> lst = new ArrayList<>();
		Stack<BinaryTreeNode<T>> stack = new Stack<>();
		stack.push(tree.root);
		BinaryTreeNode<T> prev = null;
		while (!stack.isEmpty()) {
			BinaryTreeNode<T> current = stack.peek();
			if (prev == null || prev.getLeft() == current
					|| prev.getRight() == current) {
				if (current.getLeft() != null) {
					stack.push(current.getLeft());
				} else if (current.getRight() != null) {
					stack.push(current.getRight());
				} else {
					stack.pop();
					lst.add(current.getVal());
				}
			}
			else if (current.getLeft() == prev) {
				if (current.getRight() != null) {
					stack.push(current.getRight());
				} else {
					stack.pop();
					lst.add(current.getVal());
				}
			}
			else if (current.getRight() == prev) {
				stack.pop();
				lst.add(current.getVal());
			}
			prev = current;
		}
		return lst;
	}
}
