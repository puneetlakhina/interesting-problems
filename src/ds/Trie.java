package ds;

import java.util.List;

public interface Trie<T> {
	/**
	 * Lookup a prefix in this trie
	 * @param prefix
	 * @return
	 */
	public List<T> lookupKeys(String prefix);
	/**
	 * Add a string into this trie
	 * @param s
	 * @param val
	 */
	public void add(String s, T val);
}
