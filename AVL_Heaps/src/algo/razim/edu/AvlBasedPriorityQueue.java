package algo.razim.edu;

public class AvlBasedPriorityQueue<T extends Comparable<T>> 
{
	AvlTree<T> myTree = new AvlTree<T>();
	
	public boolean offer(T data)
	{
		return myTree.offer(data);
	}
	
	public T poll()
	{
		return myTree.poll();
	}
	
	public T peek()
	{
		return myTree.peek();
	}
	
	public int size()
	{
		return myTree.size();
	}
	
	@Override
	public String toString()
	{
		return myTree.preOrderToString();
	}
}
