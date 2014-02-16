package ds;

public class BinaryTreeNode<T> extends Node<T> {

	private BinaryTreeNode<T> left;
	private BinaryTreeNode<T> right;
	public BinaryTreeNode(T val) {
		super(val);
	}
	public BinaryTreeNode<T> getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeNode<T> left) {
		this.left = left;
	}

	public BinaryTreeNode<T> getRight() {
		return right;
	}

	public void setRight(BinaryTreeNode<T> right) {
		this.right = right;
	}
	
	public boolean isLeaf() {
		return this.left == null && this.right == null;
	}
}
