package ds;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.*;
public class TrieTests {

	@Test
	public void testUncompressedTrie() {
		UncompressedTrie<String> trie = new UncompressedTrie<>();
		trie.add("a", "A word");
		trie.add("amy", "Amy word");
		trie.add("amyuse", "Amyuse word");
		trie.add("ball", "ball word");
		assertEquals(Arrays.asList("Amy word","Amyuse word"), trie.lookupKeys("am"));
	}
	
	@Test
	public void testCompressedTrie() {
		UncompressedTrie<String> trie = new UncompressedTrie<>();
		trie.add("a", "A word");
		trie.add("amy", "Amy word");
		trie.add("amyuse", "Amyuse word");
		trie.add("ball", "ball word");
		CompressedTrie<String> compressedTrie = CompressedTrie.compressTrie(trie);
		assertEquals(Arrays.asList("Amy word","Amyuse word"), compressedTrie.lookupKeys("am"));
		assertEquals(Arrays.asList("Amyuse word"), compressedTrie.lookupKeys("amyuse"));
	}
}
