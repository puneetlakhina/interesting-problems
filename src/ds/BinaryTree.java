package ds;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<T> {
	protected int height = 1;

	protected BinaryTreeNode<T> root;
	public BinaryTree(BinaryTreeNode<T> root) {
	    this.root = root;
	}
	public BinaryTree(T val) {
		this(new BinaryTreeNode<>(val));
	}

	public void addNode(T val) {
		addNode(root, val, 1);
	}

	public BinaryTreeNode<T> getRoot() {
	    return root;
	}
	public void addNode(BinaryTreeNode<T> nodeToStartAt, T val,
			int current_height) {
		if (current_height > height) {
			height = current_height;
		}
		if (nodeToStartAt.getLeft() == null) {
			nodeToStartAt.setLeft(new BinaryTreeNode<>(val));
		} else if (nodeToStartAt.getRight() == null) {
			nodeToStartAt.setRight(new BinaryTreeNode<>(val));
		} else {
			addNode(nodeToStartAt.getLeft(), val, current_height + 1);
		}
	}

	public BinaryTreeNode<T> find(T valToFind) {
		Queue<BinaryTreeNode<T>> nodesToLookAt = new LinkedList<>(); // BFS
		nodesToLookAt.add(root);
		while (!nodesToLookAt.isEmpty()) {
			BinaryTreeNode<T> node = nodesToLookAt.poll();
			if (node.getVal().equals(valToFind)) {
				return node;
			}
			if (node.getLeft() != null) {
				nodesToLookAt.add(node.getLeft());
			}
			if (node.getRight() != null) {
				nodesToLookAt.add(node.getRight());
			}
		}
		return null;
	}

	public boolean isBst() {
		return isBst(root, null, null);
	}

	@SuppressWarnings("unchecked")
	private static <T> boolean isBst(BinaryTreeNode<T> node, T maxValue,
			T minValue) {

		Comparable<T> comparableNode = ((Comparable<T>) node.getVal());
		if ((minValue == null || comparableNode.compareTo(minValue) >= 0)
				&& (maxValue == null || comparableNode.compareTo(maxValue) <= 0)) {
			if (node.isLeaf()) {
				return true;
			}
			boolean isLeftBst = true;
			if (node.getLeft() != null) {
				isLeftBst = ((Comparable<T>) node.getVal()).compareTo(node
						.getLeft().getVal()) > 0
						&& isBst(node.getLeft(),
								lesser(node.getVal(), maxValue), minValue);
			}
			if (isLeftBst) {
				boolean isRightBst = true;
				if (node.getRight() != null) {
					isRightBst = ((Comparable<T>) node.getVal()).compareTo(node
							.getRight().getVal()) < 0
							&& isBst(node.getRight(), maxValue,
									greater(node.getVal(), minValue));
				}
				return isRightBst;
			}
		}
		return false;
	}

	/**
	 * Return lesser of the two values
	 *
	 * @param val1
	 * @param val2
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T lesser(T val1, T val2) {
		if (val1 == null) {
			return val2;
		}
		if (val2 == null) {
			return val1;
		}
		return ((Comparable<T>) val1).compareTo(val2) > 0 ? val2 : val1;
	}

	@SuppressWarnings("unchecked")
	public static <T> T greater(T val1, T val2) {
		if (val1 == null) {
			return val2;
		}
		if (val2 == null) {
			return val1;
		}
		return ((Comparable<T>) val1).compareTo(val2) > 0 ? val1 : val2;
	}

	@Override
    public String toString() {
		StringBuilder sb = new StringBuilder();
		Queue<BinaryTreeNode<T>> nextHeightQueue = new LinkedList<>();
		nextHeightQueue.offer(root);
		int current_height = 0;
		while (!nextHeightQueue.isEmpty()) {
			sb.append(repeat("\t",
					(int) Math.pow(2, height - current_height + 1)));
			int size = nextHeightQueue.size();
			for (int i = 0; i < size; i++) {
				BinaryTreeNode<T> node = nextHeightQueue.poll();
				sb.append(node.getVal().toString() + "\t");
				if (node.getLeft() != null) {
					nextHeightQueue.offer(node.getLeft());
				}
				if (node.getRight() != null) {
					nextHeightQueue.offer(node.getRight());
				}
			}
			sb.append("\n");
			current_height++;
		}
		return sb.toString();
	}

	private static String repeat(String str, int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(str);
		}
		return sb.toString();
	}
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((root == null) ? 0 : root.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BinaryTree other = (BinaryTree) obj;
        if (root == null) {
            if (other.root != null) {
                return false;
            }
        } else if (!root.equals(other.root)) {
            return false;
        }
        return true;
    }

}
