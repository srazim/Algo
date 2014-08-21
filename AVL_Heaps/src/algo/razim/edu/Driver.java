package algo.razim.edu;

public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		/*HeapBasedPriorityQueue<Integer> heapQueue = new HeapBasedPriorityQueue<Integer>(20);
		
		for(int i = 0; i < 20; i++)
		{
			heapQueue.offer(i);
		}
		
		System.out.println("---- Peeks ---");
		
		for(int i = 0; i < 3; i++)
		{
			System.out.println(heapQueue.peek());
		}

		System.out.println("---- Polls ---");
		
		for(int i = 0; i < 20; i++)
		{
			System.out.println(heapQueue.poll());
		}*/

		
		AvlBasedPriorityQueue<Integer> avlQueue = new AvlBasedPriorityQueue<Integer>();
		
		for(int i = 0; i < 20; i++)
		{
			avlQueue.offer(i);
		}
		
		for(int i = 0; i < 20; i++)
		{
			System.out.println(avlQueue.poll());
		}

	}

}
