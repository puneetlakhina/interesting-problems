package ds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import ds.UncompressedTrie.TrieNode;

public class CompressedTrie<T> implements Trie<T> {
	/**
	 * A node of a compressed trie
	 * 
	 * @author puneet
	 * 
	 */
	private static class CompressedTrieNode<T> {
		T val;
		Map<String, CompressedTrieNode<T>> children = new HashMap<>();

		@Override
		public String toString() {
			return "TrieNode [val=" + val + ", children=" + children + "]";
		}
	}

	private final CompressedTrieNode<T> root;

	public CompressedTrie() {
		root = new CompressedTrieNode<T>();
	}

	public CompressedTrie(CompressedTrieNode<T> root) {
		this.root = root;
	}

	@Override
	public List<T> lookupKeys(String prefix) {
		return lookupKeys(prefix, root);
	}

	private List<T> lookupKeys(String prefix, CompressedTrieNode<T> node) {
		if (prefix.length() == 0) {
			return Collections.emptyList();
		}
		for (Map.Entry<String, CompressedTrieNode<T>> childEntry : node.children
				.entrySet()) {
			String childPrefix = childEntry.getKey();
			if (childPrefix.length() >= prefix.length()
					&& childPrefix.startsWith(prefix)) {
				return findTerminals(childEntry.getValue());
			} else if (prefix.startsWith(childPrefix)) {
				String remainingToMatch = prefix
						.substring(childPrefix.length());
				if (remainingToMatch.length() == 0) {
					return findTerminals(childEntry.getValue());
				} else {
					return lookupKeys(remainingToMatch, childEntry.getValue());
				}
			}
		}
		return Collections.emptyList();
	}

	public List<T> findTerminals(CompressedTrieNode<T> node) {
		Queue<CompressedTrieNode<T>> queue = new LinkedList<>();
		queue.add(node);
		List<T> lst = new ArrayList<>();
		while (!queue.isEmpty()) {
			CompressedTrieNode<T> candidate = queue.poll();
			if (candidate.val != null) {
				lst.add(candidate.val);
			}
			queue.addAll(candidate.children.values());
		}
		return lst;
	}

	/**
	 * Compress this trie. compression is the act grouping together a sequence
	 * of non terminal nodes into a single node so that we need fewer nodes
	 */
	public static <T> CompressedTrie<T> compressTrie(UncompressedTrie<T> trie) {
		CompressedTrie<T> compressedTrie = new CompressedTrie<T>(compressNode(
				Character.valueOf(' '), trie.root).node);
		return compressedTrie;
	}

	public static <T> CompressedTrieNodeResult<T> compressNode(Character c,
			TrieNode<T> trieNode) {
		CompressedTrieNodeResult<T> result = new CompressedTrieNodeResult<>();
		result.prefix = c.toString();
		CompressedTrieNode<T> resultNode = new CompressedTrieNode<>();
		if (trieNode.val != null || trieNode.children.size() > 1) {
			for (Map.Entry<Character, TrieNode<T>> nodeEntry : trieNode.children
					.entrySet()) {
				CompressedTrieNodeResult<T> compressedChildResult = compressNode(
						nodeEntry.getKey(), nodeEntry.getValue());
				resultNode.children.put(compressedChildResult.prefix,
						compressedChildResult.node);
			}
			resultNode.val = trieNode.val;
		} else {
			// Non terminal node with only one child
			CompressedTrieNodeResult<T> compressedChildResult = compressNode(
					trieNode.children.keySet().iterator().next(),
					trieNode.children.values().iterator().next());
			result.prefix = result.prefix
					+ (compressedChildResult.prefix != null ? compressedChildResult.prefix
							: "");
			resultNode = compressedChildResult.node;
			resultNode.val = compressedChildResult.node.val;
		}
		result.node = resultNode;
		return result;
	}

	public static class CompressedTrieNodeResult<T> {
		String prefix;
		CompressedTrieNode<T> node;
	}

	@Override
	public void add(String s, T val) {
		throw new UnsupportedOperationException(
				"adding to a compressed trie not supported. Only lookup is supported");
	}
}
