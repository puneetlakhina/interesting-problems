package ds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class UncompressedTrie<T> implements Trie<T> {

	public static class TrieNode<T> {
		T val;
		Map<Character, TrieNode<T>> children = new HashMap<>();

		@Override
		public String toString() {
			return "TrieNode [val=" + val + ", children=" + children + "]";
		}
	}

	protected TrieNode<T> root = new TrieNode<T>();

	public void add(String s, T val) {
		addAt(root, s, val);
	}

	private void addAt(TrieNode<T> nodeToAddAt, String s, T val) {
		if (s.length() == 0) {
			nodeToAddAt.val = val;
			return;
		} else {
			Character c = s.charAt(0);
			if (!nodeToAddAt.children.containsKey(c)) {
				nodeToAddAt.children.put(c, new TrieNode<T>());
			}
			addAt(nodeToAddAt.children.get(c), s.substring(1), val);
		}

	}

	public List<T> lookupKeys(String prefix) {
		return lookupKeys(root, prefix);
	}

	private List<T> lookupKeys(TrieNode<T> node, String prefix) {
		if (node == null) {
			return Collections.emptyList();
		}
		if (prefix.length() == 0) {
			return getAllChildrenKeys(node);
		} else {
			return lookupKeys(node.children.get(prefix.charAt(0)),
					prefix.substring(1));
		}
	}

	private List<T> getAllChildrenKeys(TrieNode<T> node) {
		List<T> lst = new ArrayList<>();
		Queue<TrieNode<T>> breadthFirstQueue = new LinkedList<>();
		breadthFirstQueue.add(node);
		while (!breadthFirstQueue.isEmpty()) {
			TrieNode<T> n = breadthFirstQueue.poll();
			if (n.val != null) {
				lst.add(n.val);
			}
			breadthFirstQueue.addAll(n.children.values());
		}
		return lst;
	}

}
