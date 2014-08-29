package razim.algo.bin;

import java.util.ArrayList;

public class DotsAndBoxes 
{
	private Graph myGraph;
	private int rows = 0;
	private int columns = 0;
	
	int[] playerScores = new int[2];
	
	public DotsAndBoxes(int width, int height)
	{
		setUpGraph(width, height);
		rows = height+1;
		columns = width+1;
	}
	
	private void setUpGraph(int width, int height)
	{
		myGraph = new Graph((width+1) * (height + 1));
		
		for(int i = 1; i < width+1; i++)
		{
			for(int j = 1; j < height+1; j++)
			{
				if(i + 1 < width+1)
				{
					myGraph.addEdge((i * (height+1)) + j, (i * (height+1)) + j - 1, 1);//left
				}
				if(j + 1 < height+1)
				{
					myGraph.addEdge((i * (width+1)) + j, (i - 1) * (width+1) + j, 1);//up
				}
			}
		}
		
		
	}
	
	private boolean isNotFringeEdge(int index)
	{
		return (index > rows && index < (columns * rows) - rows && index % rows != 0 && (index + 1) % rows != 0);
	}
	
	public int drawLine(int player, int x1, int y1, int x2, int y2)
	{
		int score = 0;
		
		if(y1 == y2) // left-right
		{
			int index = (y1 * rows) + Math.max(x1, x2);
			
			if(myGraph.isEdge(index, index+rows))
			{
				myGraph.removeEdge(index, index+rows);
				
				if(myGraph.degree(index) == 0 && isNotFringeEdge(index))
				{
					playerScores[player - 1] += 1;
					score++;
				}
				if(myGraph.degree(index+rows) == 0 && isNotFringeEdge(index+rows))
				{
					playerScores[player - 1] += 1;
					score++;
				}
			}
		}
		else if(x1 == x2)
		{
			int index = (Math.max(y1, y2) * rows) + x1;			
			
			if(myGraph.isEdge(index, index + 1))
			{
				myGraph.removeEdge(index, index+1);
				
				if(myGraph.degree(index) == 0 && isNotFringeEdge(index))
				{
					playerScores[player - 1] += 1;
					score = 1;
				}
				if(myGraph.degree(index+1) == 0 && isNotFringeEdge(index+1))
				{
					playerScores[player - 1] += 1;
					score = 1;
				}
			}
		}
		
		
		
		return score;
	}
	
	public boolean areMovesLeft()
	{
		BfsGraphTraversal bfs = new BfsGraphTraversal();
		ArrayList<ArrayList<Integer>> traversalTrees =  bfs.traverse(myGraph);
		return traversalTrees.size() != myGraph.vCount();
	}
	
	public int score(int player)
	{
		return playerScores[player - 1];
	}
	
	public int countDoubleCrosses()
	{
		int count = 0;
		DfsGraphTraversal dfs = new DfsGraphTraversal();
		ArrayList<ArrayList<Integer>> traversalTrees =  dfs.traverse(myGraph);
		for(int i = 0; i < traversalTrees.size(); i++)
		{
			if(traversalTrees.get(i).size() == 2)// && isNotFringeEdge(i))
			{
				count ++;
			}
		}
		return count;
	}
	
	public int countCycles()
	{
		int numCycles = 0;
		DfsGraphTraversal dfs = new DfsGraphTraversal();
		ArrayList<ArrayList<Integer>> traversalTrees =  dfs.traverse(myGraph);
		
		for(int i = 0; i < traversalTrees.size(); i++)
		{
			int count = 0;
			for(int j = 0; j < traversalTrees.get(i).size(); j++)
			{
				if(myGraph.degree(traversalTrees.get(i).get(j)) == 2)
				{
					count++;
				}
			}
			if(count == traversalTrees.get(i).size())
			{
				numCycles++;
			}
		}
		return numCycles;
	}
	
	public void mightUseLater()
	{
//		int chainA = -1;
//		int chainB = -1;
		
//		for(int i = 0; i < traversalTrees.size(); i++)
//		{
//			chainA = traversalTrees.get(i).get(1);
//			chainB = traversalTrees.get(i).get(1);
//			
//			for(int j = 0; j < traversalTrees.get(i).size(); j++)
//			{
//				if(traversalTrees.get(i).size() > 2)
//				{ 
//					if(myGraph.degree(traversalTrees.get(i).get(j)) != 2)
//					{
//						
//					}
//				}
//			}
//		}
	}
	
	public int countOpenChains()
	{
		int count = 0;
		BfsGraphTraversal bfs = new BfsGraphTraversal();
		ArrayList<ArrayList<Integer>> traversalTrees =  bfs.traverse(myGraph);
		
		for(int i = 0; i < traversalTrees.size(); i++)
		{
			if(traversalTrees.get(i).size() > 2)
			{
				count += numberOfChains(traversalTrees.get(i));
			}
		}
		
		
		return count;
	}
	
	private int numberOfChains(ArrayList<Integer> list)
	{
		int chainCount = 0;
		ArrayList<Integer> dfsTrees = new ArrayList<Integer>();
		DfsGraphTraversal dfs = new DfsGraphTraversal();
		boolean dfsCreated = false;
		
		for(int i = 0; i < list.size(); i++)
		{
			if(myGraph.degree(list.get(i)) == 2 && myGraph.getMark(list.get(i)) == 0)
			{
				int indexOne = myGraph.first(list.get(i));
				int indexTwo = myGraph.next(list.get(i), indexOne);
				
				if(myGraph.degree(indexOne) > 2 || (myGraph.degree(indexOne) == 1 && !isNotFringeEdge(indexOne)))
				{
					dfsTrees = dfs.traverseFromPoint(myGraph, list.get(i));
					dfsCreated = dfsTrees.size() >= 3;
				}
				else if(myGraph.degree(indexTwo) > 2 || (myGraph.degree(indexTwo) == 1 && !isNotFringeEdge(indexTwo)))
				{
					dfsTrees = dfs.traverseFromPoint(myGraph, list.get(i));
					dfsCreated = dfsTrees.size() >= 3;
				}
			}
			
			if(dfsCreated)
			{
				int chainLength = 0;
				boolean isChain = true;
				
				for(int j = 0; j < dfsTrees.size() && isChain; j++)
				{
					if(myGraph.degree(dfsTrees.get(j)) == 2)
					{
						chainLength++;
						myGraph.setMark(dfsTrees.get(j), 1);
					}
					else
					{
						isChain = false;
						//boolean tempOne = ((myGraph.degree(dfsTrees.get(j)) == 1 && !isNotFringeEdge(dfsTrees.get(j))) && chainLength >= 3);
						//boolean tempTwo = myGraph.degree(dfsTrees.get(j)) > 2 && chainLength >= 3;
						if(((myGraph.degree(dfsTrees.get(j)) == 1 && !isNotFringeEdge(dfsTrees.get(j))) && chainLength >= 3) || myGraph.degree(dfsTrees.get(j)) > 2 && chainLength >= 3)
						{
							chainCount++;
						}
					}
				}
				dfsCreated = false;
			}
		}
		
		return chainCount;
	}
}
