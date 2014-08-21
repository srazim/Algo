package algo.razim.edu;

public class HeapBasedPriorityQueue<T extends Comparable<T>>
{
	Heap<T> myHeap;
	
	public HeapBasedPriorityQueue(int initialSize)
	{
		myHeap = new Heap<T>(initialSize);
	}
	
	public boolean offer(T data)
	{
		myHeap.insert(data);
		return true;
	}
	
	public T poll()
	{
		return myHeap.poll();
	}
	
	public T peek()
	{
		return myHeap.peek();
	}
}
