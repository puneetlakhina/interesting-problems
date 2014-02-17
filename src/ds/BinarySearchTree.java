package ds;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public BinarySearchTree(T val) {
		super(val);
	}

	@Override
	public void addNode(T val) {
		addNodeBst(root, new BinaryTreeNode<>(val), 1);
	}

	
	public void addNode(BinaryTreeNode<T> nodeToStartAt, T val, int height) {
		throw new UnsupportedOperationException("cant add wantonly in BST");
	}

	private void addNodeBst(BinaryTreeNode<T> nodeToStartAt,
			BinaryTreeNode<T> node, int current_height) {
		if(current_height > height) {
			height = current_height;
		}
		if (nodeToStartAt.val.compareTo(node.val) > 0) {
			if (nodeToStartAt.getLeft() == null) {
				nodeToStartAt.setLeft(node);
			} else {
				addNodeBst(nodeToStartAt.getLeft(), node, current_height + 1);
			}
		} else {
			if (nodeToStartAt.getRight() == null) {
				nodeToStartAt.setRight(node);
			} else {
				addNodeBst(nodeToStartAt.getRight(), node, current_height + 1);
			}
		}
	}
}
