package razim.algo.bin;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import org.junit.Assert;
import org.junit.Test;


public class DotsAndBoxesTest {

	@Test
	public void testDFS()
	{
		Graph g = new Graph(8);
		
		g.addEdge(0,1,1);
		g.addEdge(0,2,3);
		g.addEdge(1,2,2);
		g.addEdge(0,3,4);
		g.addEdge(3,4,5);
		g.addEdge(3,5,7);
		g.addEdge(4,5,6);
		g.addEdge(6,7,1);
		
		BfsGraphTraversal bfs = new BfsGraphTraversal();
		
		ArrayList<ArrayList<Integer>> trees = bfs.traverse(g);
		
		System.out.println(trees);
	}
	
	@Test
	public void testBFS()
	{
		Graph g = new Graph(8);
	}
	
	@Test
	public void testOwner() {
		DotsAndBoxes d = new DotsAndBoxes(5, 5);
		
		d.drawLine(1, 1, 0, 2, 0);
		d.drawLine(1, 2, 0, 3, 0);
		d.drawLine(1, 1, 1, 2, 1);
		d.drawLine(1, 2, 1, 3, 1);
		d.drawLine(1, 1, 0, 1, 1);
		d.drawLine(1, 3, 0, 3, 1);
		
		d.drawLine(1, 0, 2, 0, 3);
		d.drawLine(1, 0, 3, 0, 4);
		d.drawLine(1, 1, 2, 1, 3);
		d.drawLine(1, 1, 3, 1, 4);
		d.drawLine(1, 0, 2, 1, 2);
		d.drawLine(1, 0, 4, 1, 4);

		d.drawLine(1, 2, 0, 2, 1); // draw line in between a double-cross
		
		Assert.assertEquals(2, d.score(1));
	}
	
	@Test
	public void testDoubleCrosses() {
		DotsAndBoxes d = new DotsAndBoxes(5, 5);
		d.drawLine(1, 1, 0, 2, 0);
		d.drawLine(1, 2, 0, 3, 0);
		d.drawLine(1, 1, 1, 2, 1);
		d.drawLine(1, 2, 1, 3, 1);
		d.drawLine(1, 1, 0, 1, 1);
		d.drawLine(1, 3, 0, 3, 1);
		
		d.drawLine(1, 0, 2, 0, 3);
		d.drawLine(1, 0, 3, 0, 4);
		d.drawLine(1, 1, 2, 1, 3);
		d.drawLine(1, 1, 3, 1, 4);
		d.drawLine(1, 0, 2, 1, 2);
		d.drawLine(1, 0, 4, 1, 4);
		
		Assert.assertEquals(2, d.countDoubleCrosses());
	}
	
	@Test
	public void testCycle() {
		DotsAndBoxes d = new DotsAndBoxes(5, 5);
		
//		.-.-.-.-.
//		|       |
//		. .-.-. .
//		| |   | |
//		. . . . .
//		| |   | |
//		. .-.-. .
//		|       |
//		.-.-.-.-.
		
		d.drawLine(1, 0, 0, 0, 1);
		d.drawLine(1, 0, 1, 0, 2);
		d.drawLine(1, 0, 2, 0, 3);
		d.drawLine(1, 0, 3, 0, 4);
		
		d.drawLine(1, 0, 4, 1, 4);
		d.drawLine(1, 1, 4, 2, 4);
		d.drawLine(1, 2, 4, 3, 4);
		d.drawLine(1, 3, 4, 4, 4);
		
		d.drawLine(1, 4, 3, 4, 4);
		d.drawLine(1, 4, 2, 4, 3);
		d.drawLine(1, 4, 1, 4, 2);
		d.drawLine(1, 4, 0, 4, 1);
		
		d.drawLine(1, 3, 0, 4, 0);
		d.drawLine(1, 2, 0, 3, 0);
		d.drawLine(1, 1, 0, 2, 0);
		d.drawLine(1, 0, 0, 1, 0);
		
		d.drawLine(1, 1, 1, 1, 2);
		d.drawLine(1, 1, 2, 1, 3);
		
		d.drawLine(1, 1, 3, 2, 3);
		d.drawLine(1, 2, 3, 3, 3);
		
		d.drawLine(1, 3, 2, 3, 3);
		d.drawLine(1, 3, 1, 3, 2);
		
		d.drawLine(1, 2, 1, 3, 1);
		d.drawLine(1, 1, 1, 2, 1);
		
		Assert.assertEquals(2, d.countCycles());
	}

	@Test
	public void testChains() {
		
//		. . .-.-.
//		| |     |
//		. .-.-. .
//		| |   | |
//		. . . . .
//		| |   | |
//		. .-.-. .
//		|       |
//		.-.-.-.-.
		
		DotsAndBoxes d = new DotsAndBoxes(5, 5);
		
		d.drawLine(1, 0, 0, 0, 1);
		d.drawLine(1, 0, 1, 0, 2);
		d.drawLine(1, 0, 2, 0, 3);
		d.drawLine(1, 0, 3, 0, 4);
		
		d.drawLine(1, 0, 4, 1, 4);
		d.drawLine(1, 1, 4, 2, 4);
		d.drawLine(1, 2, 4, 3, 4);
		d.drawLine(1, 3, 4, 4, 4);
		
		d.drawLine(1, 4, 3, 4, 4);
		d.drawLine(1, 4, 2, 4, 3);
		d.drawLine(1, 4, 1, 4, 2);
		d.drawLine(1, 4, 0, 4, 1);
		
		d.drawLine(1, 3, 0, 4, 0);
		d.drawLine(1, 2, 0, 3, 0);
		d.drawLine(1, 1, 0, 1, 1);
		
		d.drawLine(1, 1, 1, 1, 2);
		d.drawLine(1, 1, 2, 1, 3);
		
		d.drawLine(1, 1, 3, 2, 3);
		d.drawLine(1, 2, 3, 3, 3);
		
		d.drawLine(1, 3, 2, 3, 3);
		d.drawLine(1, 3, 1, 3, 2);
		
		d.drawLine(1, 2, 1, 3, 1);
		d.drawLine(1, 1, 1, 2, 1);
		
		Assert.assertEquals(1, d.countOpenChains());		
	}
	
	@Test
	public void testTwoChains() {
		
//		. . .-.-.
//		| |     |
//		. .-.-. .
//		| |   | |
//		. . . . .
//		| |   | |
//		. .-.-. .
//		|   |   |
//		.-. . .-.
		
		DotsAndBoxes d = new DotsAndBoxes(5, 5);
		
		d.drawLine(1, 0, 0, 0, 1);
		d.drawLine(1, 0, 1, 0, 2);
		d.drawLine(1, 0, 2, 0, 3);
		d.drawLine(1, 0, 3, 0, 4);
		
		d.drawLine(1, 0, 4, 1, 4);
		d.drawLine(1, 2, 3, 2, 4);
		d.drawLine(1, 3, 4, 4, 4);
		
		d.drawLine(1, 4, 3, 4, 4);
		d.drawLine(1, 4, 2, 4, 3);
		d.drawLine(1, 4, 1, 4, 2);
		d.drawLine(1, 4, 0, 4, 1);
		
		d.drawLine(1, 3, 0, 4, 0);
		d.drawLine(1, 2, 0, 3, 0);
		d.drawLine(1, 1, 0, 1, 1);
		
		d.drawLine(1, 1, 1, 1, 2);
		d.drawLine(1, 1, 2, 1, 3);
		
		d.drawLine(1, 1, 3, 2, 3);
		d.drawLine(1, 2, 3, 3, 3);
		
		d.drawLine(1, 3, 2, 3, 3);
		d.drawLine(1, 3, 1, 3, 2);
		
		d.drawLine(1, 2, 1, 3, 1);
		d.drawLine(1, 1, 1, 2, 1);
		
		Assert.assertEquals(2, d.countOpenChains());		
	}

	@Test
	public void testInternalChains() {
		
//		.-.-.-.-.
//		|       |
//		. .-.-. .
//		| |     |
//		. . .-. .
//		| |     |
//		. .-.-. .
//		|       |
//		.-.-.-.-.
		
		DotsAndBoxes d = new DotsAndBoxes(5, 5);
		
		d.drawLine(1, 0, 0, 0, 1);
		d.drawLine(1, 0, 1, 0, 2);
		d.drawLine(1, 0, 2, 0, 3);
		d.drawLine(1, 0, 3, 0, 4);
		
		d.drawLine(1, 0, 4, 1, 4);
		d.drawLine(1, 1, 4, 2, 4);
		d.drawLine(1, 2, 4, 3, 4);
		d.drawLine(1, 3, 4, 4, 4);
		
		d.drawLine(1, 4, 3, 4, 4);
		d.drawLine(1, 4, 2, 4, 3);
		d.drawLine(1, 4, 1, 4, 2);
		d.drawLine(1, 4, 0, 4, 1);
		
		d.drawLine(1, 3, 0, 4, 0);
		d.drawLine(1, 2, 0, 3, 0);
		d.drawLine(1, 1, 0, 2, 0);
		d.drawLine(1, 0, 0, 1, 0);
		
		d.drawLine(1, 1, 1, 1, 2);
		d.drawLine(1, 1, 2, 1, 3);
		
		d.drawLine(1, 1, 3, 2, 3);
		d.drawLine(1, 2, 3, 3, 3);
		
		d.drawLine(1, 2, 2, 3, 2);
		
		d.drawLine(1, 2, 1, 3, 1);
		d.drawLine(1, 1, 1, 2, 1);
		
		Assert.assertEquals(2, d.countOpenChains());		
	}

	@Test
	public void testInternalChain() {
		
//		. . . . .
//		
//		. .-.-. .
//		  |     
//		. . ._. .
//		  |     
//		. .-.-. .
//		       
//		. . . . .
		
		DotsAndBoxes d = new DotsAndBoxes(5, 5);
		
		d.drawLine(1, 1, 1, 1, 2);
		d.drawLine(1, 1, 2, 1, 3);
		
		d.drawLine(1, 1, 3, 2, 3);
		d.drawLine(1, 2, 3, 3, 3);
		
		d.drawLine(1, 2, 2, 3, 2);
		//d.drawLine(1, 3, 2, 3, 3);
		
		d.drawLine(1, 2, 1, 3, 1);
		d.drawLine(1, 1, 1, 2, 1);
		
		Assert.assertEquals(1, d.countOpenChains());		
	}

	@Test
	public void testNotAChain() {
		
//		. . . . .
//		
//		. .-.-. .
//		       
//		. ._._. .
//		      
//		. . . . .
//		       
//		. . . . .
		
		DotsAndBoxes d = new DotsAndBoxes(5, 5);

		d.drawLine(1, 1, 2, 2, 2);
		d.drawLine(1, 2, 2, 3, 2);
		
		d.drawLine(1, 2, 1, 3, 1);
		d.drawLine(1, 1, 1, 2, 1);
		
		Assert.assertEquals(0, d.countOpenChains());		
		Assert.assertEquals(0, d.countCycles());
		Assert.assertEquals(0, d.countDoubleCrosses());
	}
}
