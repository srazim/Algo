//package razim.algo.bin;
//
//import java.util.ArrayList;
//
//public class Driver {
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) 
//	{
////		Graph testGraph = new Graph(7);
////		
////		testGraph.addEdge(0, 1, 3);
////		testGraph.addEdge(0, 2, 2);
////		testGraph.addEdge(0, 3, 1);
////		testGraph.addEdge(1, 2, 1);
////		testGraph.addEdge(1, 4, 2);
////		testGraph.addEdge(2, 5, 3);
////		testGraph.addEdge(3, 6, 2);
////		testGraph.addEdge(6, 5, 1);
////		testGraph.addEdge(1, 5, 2);
////		
//////		testGraph.addEdge(0, 1, 1);
//////		testGraph.addEdge(0, 2, 1);
//////		testGraph.addEdge(1, 2, 1);
//////		testGraph.addEdge(1, 3, 1);
//////		//testGraph.addEdge(2, 3, 1);
//////		testGraph.addEdge(3, 4, 1);
//////		testGraph.addEdge(4, 5, 1);
////		
////		System.out.println(testGraph.toString());
////		
////		DfsGraphTraversal dfs = new DfsGraphTraversal();
////		
////		ArrayList<ArrayList<Integer>> dfsTree = dfs.traverse(testGraph);
////		
////		System.out.println("DFS " + dfsTree);
////		
////		BfsGraphTraversal bfs = new BfsGraphTraversal();
////		
////		ArrayList<ArrayList<Integer>> bfsTree = bfs.traverse(testGraph);
////		
////		System.out.println("BFS " + bfsTree);
//		
////		DotsAndBoxes dab = new DotsAndBoxes(3, 3);
////		
////		dab.drawLine(1, 1, 0, 1, 1);
////		//dab.drawLine(2, 0, 0, 1, 0);
////		//dab.drawLine(1, 0, 1, 1, 1);
////		dab.drawLine(2, 0, 0, 0, 1);
////		
////		//dab.drawLine(1, 1, 0, 2, 0);
////		dab.drawLine(2, 2, 0, 2, 1);
////		//dab.drawLine(1, 1, 1, 2, 1);
////		
////		dab.drawLine(2, 0, 1, 0, 2);
////		dab.drawLine(1, 0, 2, 1, 2);
////		dab.drawLine(2, 1, 2, 2, 2);
////		dab.drawLine(1, 2, 2, 2, 1);
////		//dab.drawLine(2, 1, 1, 1, 2);
//		
////		System.out.println(dab.score(1));
////		System.out.println(dab.score(2));
////		
////		System.out.println(dab.areMovesLeft());
////		
////		System.out.println(dab.countDoubleCrosses());
////		
////		System.out.println(dab.countCycles());
////		
////		System.out.println(dab.countOpenChains());
//		
//		testInternalChains();
//		testInternalChain();
//		testTwoChains();
//	}
//	
//	public static void testInternalChains() {
//		
////		.-.-.-.-.
////		|       |
////		. .-.-. .
////		| |     |
////		. . .-. .
////		| |     |
////		. .-.-. .
////		|       |
////		.-.-.-.-.
//		
//		DotsAndBoxes d = new DotsAndBoxes(5, 5);
//		
//		d.drawLine(1, 0, 0, 0, 1);
//		d.drawLine(1, 0, 1, 0, 2);
//		d.drawLine(1, 0, 2, 0, 3); //left
//		d.drawLine(1, 0, 3, 0, 4);
//		
//		d.drawLine(1, 0, 4, 1, 4);
//		d.drawLine(1, 1, 4, 2, 4); // bottom
//		d.drawLine(1, 2, 4, 3, 4);
//		d.drawLine(1, 3, 4, 4, 4);
//		
//		d.drawLine(1, 4, 3, 4, 4);
//		d.drawLine(1, 4, 2, 4, 3);
//		d.drawLine(1, 4, 1, 4, 2); //right
//		d.drawLine(1, 4, 0, 4, 1);
//		
//		d.drawLine(1, 3, 0, 4, 0);
//		d.drawLine(1, 2, 0, 3, 0); // top
//		d.drawLine(1, 1, 0, 2, 0);
//		d.drawLine(1, 0, 0, 1, 0);
//		
//		d.drawLine(1, 1, 1, 1, 2);
//		d.drawLine(1, 1, 2, 1, 3);
//		
//		d.drawLine(1, 1, 3, 2, 3);
//		d.drawLine(1, 2, 3, 3, 3);
//		
//		d.drawLine(1, 2, 2, 3, 2);
//		
//		d.drawLine(1, 2, 1, 3, 1);
//		d.drawLine(1, 1, 1, 2, 1);
//		
//		//Assert.assertEquals(2, d.countOpenChains());	
//		System.out.println("testInternal Chains - Expected: 2 " + "Result: " + d.countOpenChains());
//	}
//
//	public static void testInternalChain() {
//		
////		. . . . .
////		
////		. .-.-. .
////		  |     
////		. . ._. .
////		  |     
////		. .-.-. .
////		       
////		. . . . .
//		
//		DotsAndBoxes d = new DotsAndBoxes(5, 5);
//		
//		d.drawLine(1, 1, 1, 1, 2);
//		d.drawLine(1, 1, 2, 1, 3);
//		
//		d.drawLine(1, 1, 3, 2, 3);
//		d.drawLine(1, 2, 3, 3, 3);
//		
//		d.drawLine(1, 2, 2, 3, 2);
//		//d.drawLine(1, 3, 2, 3, 3);
//		
//		d.drawLine(1, 2, 1, 3, 1);
//		d.drawLine(1, 1, 1, 2, 1);
//		
//		//Assert.assertEquals(1, d.countOpenChains());
//		
//		System.out.println("testInternalChain - Expected: 1 " + "Result: " + d.countOpenChains());
//	}
//	
//public static void testTwoChains() {
//		
////		. . .-.-.
////		| |     |
////		. .-.-. .
////		| |   | |
////		. . . . .
////		| |   | |
////		. .-.-. .
////		|   |   |
////		.-. . .-.
//		
//		DotsAndBoxes d = new DotsAndBoxes(5, 5);
//		
//		d.drawLine(1, 0, 0, 0, 1);
//		d.drawLine(1, 0, 1, 0, 2);
//		d.drawLine(1, 0, 2, 0, 3);
//		d.drawLine(1, 0, 3, 0, 4);
//		
//		d.drawLine(1, 0, 4, 1, 4);
//		d.drawLine(1, 2, 3, 2, 4);
//		d.drawLine(1, 3, 4, 4, 4);
//		
//		d.drawLine(1, 4, 3, 4, 4);
//		d.drawLine(1, 4, 2, 4, 3);
//		d.drawLine(1, 4, 1, 4, 2);
//		d.drawLine(1, 4, 0, 4, 1);
//		
//		d.drawLine(1, 3, 0, 4, 0);
//		d.drawLine(1, 2, 0, 3, 0);
//		d.drawLine(1, 1, 0, 1, 1);
//		
//		d.drawLine(1, 1, 1, 1, 2);
//		d.drawLine(1, 1, 2, 1, 3);
//		
//		d.drawLine(1, 1, 3, 2, 3);
//		d.drawLine(1, 2, 3, 3, 3);
//		
//		d.drawLine(1, 3, 2, 3, 3);
//		d.drawLine(1, 3, 1, 3, 2);
//		
//		d.drawLine(1, 2, 1, 3, 1);
//		d.drawLine(1, 1, 1, 2, 1);
//		
//		//Assert.assertEquals(2, d.countOpenChains());
//		
//		System.out.println("testTwoChains - Expected: 2 " + "Result: " + d.countOpenChains());
//	}
//
//}
