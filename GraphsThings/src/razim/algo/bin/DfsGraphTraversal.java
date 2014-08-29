package razim.algo.bin;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

public class DfsGraphTraversal 
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
		if(g.getMark(startingIndex) == 0)
		{
			path.add(startingIndex);
			g.setMark(startingIndex, 1);
		}
		
		PriorityQueue<WeightedEdge> queue = new PriorityQueue<WeightedEdge>();
		
		int lastVisitedIndex = g.first(startingIndex);
		
		
		queue.clear();
		
		
		while(lastVisitedIndex != g.vCount())
		{
			queue.offer(new WeightedEdge(g.getWeight(startingIndex, lastVisitedIndex), lastVisitedIndex));					
			lastVisitedIndex = g.next(startingIndex, lastVisitedIndex);
		}
		
		while(!queue.isEmpty())
		{
			WeightedEdge edge = queue.poll();
			if(g.getMark(edge.index) == 0)
			{
				path.add(edge.index);
				g.setMark(edge.index, 1);
				path.addAll(getPath(g, edge.index));
			}
		}
		
		return path;
	}
	
	public ArrayList<Integer> traverseFromPoint(Graph g, int index)
	{
		int edge = g.first(index);
		if(g.degree(edge) !=2)
		{
			g.removeEdge(index, edge);
		}
		else
		{
			edge = g.next(index,  edge);
		}
		ArrayList<Integer> list = getPath(g, index);
		
		g.addEdge(index,  edge,  1);
		
		for(int i = 0; i < list.size(); i++)
		{
			g.setMark(list.get(i), 0);
		}
		
		return list;
	}
	
}
