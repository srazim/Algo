package algo.razim.edu;

import java.util.Random;

public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		/*HeapBasedPriorityQueue<Integer> heapQueue = new HeapBasedPriorityQueue<Integer>(20);
		
		for(int i = 0; i < 40; i++)
		{
			heapQueue.offer(new Random().nextInt(10));
		}
		
		System.out.println("---- Peeks ---");
		
		for(int i = 0; i < 3; i++)
		{
			System.out.println(heapQueue.peek());
		}

		System.out.println("---- Polls ---");
		
		for(int i = 0; i < 40; i++)
		{
			System.out.println(heapQueue.poll());
		}*/

		
		AvlBasedPriorityQueue<Integer> avlQueue = new AvlBasedPriorityQueue<Integer>();
		
		for(int i = 0; i < 16; i++)
		{
			int toInsert = new Random().nextInt(1000);
			avlQueue.offer(toInsert);
			
		}
		avlQueue.poll();
		avlQueue.poll();
		avlQueue.poll();
		avlQueue.poll();
		avlQueue.poll();
		System.out.println(avlQueue);

	}

}
