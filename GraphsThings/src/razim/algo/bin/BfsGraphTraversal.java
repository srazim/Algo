package razim.algo.bin;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class BfsGraphTraversal 
{
	ArrayList<ArrayList<Integer>> traverse(Graph g)
	{
		ArrayList<ArrayList<Integer>> forest = new ArrayList<ArrayList<Integer>>();		
		
		for(int i = 0; i < g.vCount(); i++)
		{
			if(g.getMark(i) == 0)
			{
				ArrayList<Integer> list = getPath(g, i);
				forest.add(list);
				
			}
		}
		
		g.resetMarks();
		
		return forest;
	}
	
	private ArrayList<Integer> getPath(Graph g, int startingIndex)
	{
		ArrayList<Integer> path = new ArrayList<Integer>();
		
		PriorityQueue<WeightedEdge> queue = new PriorityQueue<WeightedEdge>();
		
		path.add(startingIndex);
		g.setMark(startingIndex, 1);
		
		ArrayList<Integer> neighbors = g.getAllNeighbors(startingIndex);
		
		for(int i = 0; i < neighbors.size(); i++)
		{
			queue.offer(new WeightedEdge(g.getWeight(startingIndex, neighbors.get(i)), neighbors.get(i)));
		}
		
		int currentIndex;
		
		while(!queue.isEmpty())
		{
			currentIndex = queue.poll().index;
			if(g.getMark(currentIndex) == 0)
			{
				path.add(currentIndex);
				ArrayList<Integer> newNeighbors = g.getAllNeighbors(currentIndex);
				for(int i = 0; i < newNeighbors.size(); i++)
				{
					if(g.getMark(newNeighbors.get(i)) == 0)
					{
						queue.offer(new WeightedEdge(g.getWeight(currentIndex, newNeighbors.get(i)), newNeighbors.get(i)));
					}
				}
			}
			g.setMark(currentIndex, 1);
		}
		
		
		return path;
	}
	
	

}
