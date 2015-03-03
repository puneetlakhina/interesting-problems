package ds;

import java.util.Arrays;

public class TreeFromTraversal {
    public static <T> BinaryTree<T> fromTraversal(T[] inOrderTraversal, T[] preOrderTraversal) {
        if (inOrderTraversal.length == 0) {
            return null;
        }
        if (inOrderTraversal.length == 1) {
            return new BinaryTree<T>(inOrderTraversal[0]);
        }
        BinaryTreeNode<T> root = new BinaryTreeNode<T>(preOrderTraversal[0]);
        int inOrderIndex = indexOf(inOrderTraversal, preOrderTraversal[0]);
        int numLeftElements = inOrderIndex;
        // Everything les than inOrderIndex in inOrderTraversal is the left
        // subtree, everything greater than is the right subtree
        BinaryTree<T> leftTree = null;
        BinaryTree<T> rightTree = null;
        if (inOrderIndex > 0) {
            leftTree = fromTraversal(Arrays.copyOfRange(inOrderTraversal, 0, inOrderIndex), Arrays.copyOfRange(preOrderTraversal, 1, numLeftElements + 1));
        }
        if(inOrderIndex < inOrderTraversal.length) {
            rightTree = fromTraversal(Arrays.copyOfRange(inOrderTraversal, inOrderIndex + 1, inOrderTraversal.length), Arrays.copyOfRange(preOrderTraversal, numLeftElements + 1, preOrderTraversal.length));
        }

        root.setLeft(leftTree != null ? leftTree.getRoot():null);
        root.setRight(rightTree != null ? rightTree.getRoot():null);
        return new BinaryTree<>(root);
    }

    private static <T> int indexOf(T[] arr, T el) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(el)) {
                return i;
            }
        }
        throw new IllegalArgumentException(el.toString() + " not found");
    }

}