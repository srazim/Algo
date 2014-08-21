package algo.razim.edu;

public class Heap<T extends Comparable<T>>
{
	T[] myHeap;
	int tailIndex;
	
	public Heap(int initialSize)
	{
		int size = 2;
		while(size < initialSize)
		{
			size *= 2;
		}
		
		myHeap = (T[]) (new Comparable[size]);
		tailIndex = 1;
	}
	
	public T poll()
	{
		T byteToReturn = myHeap[1];
		tailIndex--;
		myHeap[1] = myHeap[tailIndex];
		heapify(1);
		
		return byteToReturn;
	}
	

	
	public T peek()
	{
		return myHeap[1];
	}
	
	public void insert(T value)
	{
		/*if(tailIndex + 1 >= 2 * (maBytes.length/3))
		{
			byte[] copy = new byte[maBytes.length];
			for(int i = 0; i < maBytes.length; i++)
			{
				copy[i] = maBytes[i];
			}
			
			maBytes = new byte[maBytes.length * 2];
			
			for(int i = 0; i < copy.length; i++)
			{
				maBytes[i] = copy[i];
			}
		}*/
		
		myHeap[tailIndex] = value;
		heapify(tailIndex);
		tailIndex++;
	}
	
	private void heapify(int indexOfValue)
	{
		if(isLessThanParent(indexOfValue))
		{
			int indexOfParent = indexOfParent(indexOfValue);
			
			T temp = myHeap[indexOfValue];
			myHeap[indexOfValue] = myHeap[indexOfParent];
			myHeap[indexOfParent] = temp;

			heapify(indexOfParent);
		}
		
		if(isLessThanChildren(indexOfValue))
		{
			int indexOfGreaterChild = getIndexOfGreaterChild(indexOfValue);
			
			T temp = myHeap[indexOfValue];
			myHeap[indexOfValue] = myHeap[indexOfGreaterChild];
			myHeap[indexOfGreaterChild] = temp;

			heapify(indexOfGreaterChild);
		}
			
	}
	
	private int getIndexOfGreaterChild(int indexOfValue)
	{
		int indexToReturn = -1;
		
		if((indexOfValue * 2) + 1 < tailIndex)
		{
			if(myHeap[indexOfValue * 2].compareTo(myHeap[(indexOfValue * 2) + 1]) < 0)
			{
				indexToReturn = indexOfValue * 2;
			}
			else
			{
				indexToReturn = (indexOfValue * 2) + 1;
			}
		}
		else
		{
			indexToReturn = indexOfValue * 2;
		}
		
		return indexToReturn;
	}
	
	private boolean isLessThanChildren(int indexOfValue)
	{
		boolean isGreater = false;
		
		if(indexOfValue * 2 < tailIndex && (indexOfValue * 2) + 1 < tailIndex)
		{
			isGreater = myHeap[indexOfValue].compareTo(myHeap[indexOfValue * 2]) > 0 || myHeap[indexOfValue].compareTo(myHeap[(indexOfValue * 2) + 1]) > 0;
		}
		else if(indexOfValue * 2 < tailIndex)
		{
			isGreater = myHeap[indexOfValue].compareTo(myHeap[indexOfValue * 2]) > 0;
		}
		
		return isGreater;
	}
	
	private boolean isLessThanParent(int indexOfValue)
	{
		return indexOfValue > 1 ? myHeap[indexOfValue].compareTo(myHeap[indexOfValue / 2]) < 0 : false;
	}
	
	private int indexOfParent(int indexOfValue)
	{
		return indexOfValue/2;
	}
}
