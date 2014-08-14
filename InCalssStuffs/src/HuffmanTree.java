import java.util.ArrayList;
import java.util.PriorityQueue;

import edu.neumont.io.Bits;


public class HuffmanTree 
{
	FrequencyNode root = new FrequencyNode();
	
	ArrayList<FrequencyNode> nodes = new ArrayList<FrequencyNode>();
	ArrayList<Byte> bytes = new ArrayList<Byte>();
	PriorityQueue<FrequencyNode> queuedNodes = new PriorityQueue<FrequencyNode>();
	
	Bits daBits = new Bits();
	
	public HuffmanTree(byte[] data)
	{
		getFrequencies(data);
		
		buildTree();
		
	}
	private void getFrequencies(byte[] data)
	{
		for(int i = 0; i< data.length; i++)
		{
			if(!bytes.contains(data[i]))
			{
				bytes.add(data[i]);
				FrequencyNode temp = new FrequencyNode();
				temp.bytes.add(data[i]);
				temp.count++;
				nodes.add(temp);
			}
			else
			{
				int index = bytes.indexOf(data[i]);
				nodes.get(index).count++;
			}
		}
		
		for(int i = 0; i<nodes.size(); i++)
		{
			queuedNodes.add(nodes.get(i));
		}
	}
	private void buildTree()
	{
		while(queuedNodes.size() > 1)
		{
			FrequencyNode leftTemp = queuedNodes.poll();
			FrequencyNode rightTemp = queuedNodes.poll();
			
			FrequencyNode combined = new FrequencyNode(leftTemp, rightTemp);
			
			queuedNodes.add(combined);
		}
		
		root = queuedNodes.peek();
	}
	
	private boolean isLeaf(FrequencyNode node)
	{
		return node.left == null && node.right == null;
	}
	
	
	//decompressing bits into a byte
	public byte toByte(Bits bits)
	{
		byte byteToReturn = 0;
		FrequencyNode temp = root;
		
		while(!isLeaf(temp))
		{
			if(!bits.poll())// 0
			{
				temp = temp.left;
			}
			else// 1
			{
				temp = temp.right;
			}
		}
		
		byteToReturn = temp.bytes.get(0);
		
		return byteToReturn;
		
	}
	
	//compressing a single byte into huffman bits
	public void fromByte(byte b, Bits bits)
	{
		fromByteHelper(b, bits, root);
		//System.out.println(bits);

	}
	
	private void fromByteHelper(byte b, Bits bits, FrequencyNode node)
	{		
		if(!isLeaf(node))
		{
			if(node.left.bytes.contains(b))
			{
				bits.offer(false);
				fromByteHelper(b, bits, node.left);
			}
			if(node.right.bytes.contains(b))
			{
				bits.offer(true);
				fromByteHelper(b, bits, node.right);
			}
		}
	}

	
}
