package ds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Trie<T> {

    TrieNode root = new TrieNode();

    class TrieNode {
        char c;
        T val;
        Map<Character, TrieNode> children = new HashMap<>();
    }

    public void add(String s, T val) {
        addAt(root, s, val);
    }

    private void addAt(TrieNode nodeToAddAt, String s, T val) {
        if (s.length() == 0) {
            nodeToAddAt.val = val;
            return;
        } else {
            Character c = s.charAt(0);
            if (!nodeToAddAt.children.containsKey(c)) {
                nodeToAddAt.children.put(c, new TrieNode());
            }
            addAt(nodeToAddAt.children.get(c), s.substring(1), val);
        }

    }

    public List<T> lookupKeys(String prefix) {
        return lookupKeys(root, prefix);
    }

    private List<T> lookupKeys(TrieNode node, String prefix) {
        if (node == null) {
            return Collections.emptyList();
        }
        if (prefix.length() == 0) {
            return getAllChildrenKeys(node);
        } else {
            return lookupKeys(node.children.get(prefix.charAt(0)), prefix.substring(1));
        }
    }

    private List<T> getAllChildrenKeys(TrieNode node) {
        List<T> lst = new ArrayList<>();
        Queue<TrieNode> breadthFirstQueue = new LinkedList<>();
        breadthFirstQueue.add(node);
        while(!breadthFirstQueue.isEmpty()) {
            TrieNode n = breadthFirstQueue.poll();
            if(n.val != null) {
                lst.add(n.val);
            }
            breadthFirstQueue.addAll(n.children.values());
        }
        return lst;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Trie<String> trie = new Trie<>();
        trie.add("a", "A word");
        trie.add("amy", "Amy word");
        trie.add("amyuse", "Amyuse word");
        trie.add("ball", "ball word");
        System.out.println(trie.lookupKeys("am"));
    }

}
