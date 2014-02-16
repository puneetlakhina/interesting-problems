package ds;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class TestIsBst {
	private BinaryTree<Integer> bt;
	@Before
	public void setup() {
		bt = new BinaryTree<Integer>(0);
	}
	
	@Test
	public void testNoChildren() {
		assertTrue(bt.isBst());
	}
	
	@Test
	public void testOnly1Child() {
		bt.addNode(-1);
		assertTrue(bt.isBst());
	}
	
	@Test
	public void testBothChildren() {
		bt.addNode(-1);
		bt.addNode(2);
		assertTrue(bt.isBst());
	}
	
	@Test
	public void testNegativeCase() {
		bt.addNode(-1);
		bt.addNode(-5);
		assertFalse(bt.isBst());
	}
	
	@Test
	public void testMultipleLevelsNegativeCase() {
		bt.addNode(-1);
		bt.addNode(2); //Upto first level we are good
		bt.addNode(-2);
		bt.addNode(2); //This makes the substree a bst but given the root is 0, the whole tree is not a bst
		assertFalse(bt.isBst());
	}
	
	@Test
	public void testMultipleLevelsPositiveCase() {
		bt.addNode(-3);
		bt.addNode(2); //Upto first level we are good
		bt.addNode(-4);
		bt.addNode(-1); 
		assertTrue(bt.isBst());
	}
}
