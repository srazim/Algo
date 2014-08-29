package razim.algo.bin;

import java.util.ArrayList;

public class Graph 
{
	private int[][] matrix;
	private int[] marks;
	
	public Graph(int n)
	{
		matrix = new int[n][n];
		marks = new int[n];
	}
	
	//1. itterate through neighbors
	public int vCount()
	{
		return marks.length;		
	}
	
	public int eCount()
	{
		int count = 0;
		for(int i = 0; i < vCount(); i++)
		{
			for(int j = i; j < vCount(); j++)
			{
				if(matrix[i][j] != 0)
				{
					count++;
				}
			}
		}
		return count;
	}
	
	public int first(int vertex)
	{
		for(int i = 0; i < vCount(); i++)
		{
			if(matrix[vertex][i] != 0)
			{
				return i;
			}
		}
		return vCount();
	}
	
	public int next(int vertex, int lastVisitedNeighbor)
	{
		for(int curr = lastVisitedNeighbor + 1; curr < vCount(); curr++)
		{
			if(matrix[vertex][curr] != 0)
			{
				return curr;
			}
		}
		return vCount();
	}
	
	//2. CRUD Edges
	public void addEdge(int fromVertex, int toVertex, int weight)
	{
		matrix[fromVertex][toVertex] = weight;
		matrix[toVertex][fromVertex] = weight;
	}
	
	public void removeEdge(int fromVertex, int toVertex)
	{
		matrix[fromVertex][toVertex] = 0;
		matrix[toVertex][fromVertex] = 0;
	}
	
	public boolean isEdge(int fromVertex, int toVertex)
	{
		return matrix[fromVertex][toVertex] != 0;
	}
	
	public int degree(int vertex)
	{
		int count = 0;
		for(int i = 0; i < vCount(); i++)
		{
			if(matrix[vertex][i] != 0)
			{
				count++;
			}
		}
		return count;
	}
	
	//3. color the graph
	public int getMark(int vertex)
	{
		return marks[vertex];
	}
	
	public void setMark(int vertex, int color)
	{
		marks[vertex] = color;
	}
	
	public int getWeight(int from, int to)
	{
		return matrix[from][to];
	}
	
	public String toString()
	{
		String toReturn = "";
		for(int i = 0; i < vCount(); i++)
		{
			toReturn += i + " -> ";
			for(int j = 0; j < vCount(); j++)
			{
				if(matrix[i][j] != 0)
				{
					toReturn += j + ",";
				}
			}
			toReturn = toReturn.substring(0, toReturn.length() - 1);
			toReturn += "\n";
		}
		
		return toReturn;
	}
	
	public void resetMarks()
	{
		for(int i = 0; i < vCount(); i++)
		{
			setMark(i, 0);
		}
	}
	
	public ArrayList<Integer> getAllNeighbors(int index)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i = 0; i < vCount(); i++)
		{
			if(matrix[index][i] != 0)
			{
				list.add(i);
			}
		}
		return list;
	}
	
	//cycle throught all neighbors
	// for ( int curr = first(vertex); curr < vCount(); curr = next(vertex, curr) )
}