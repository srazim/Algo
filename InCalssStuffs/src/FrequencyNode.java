
import java.util.ArrayList;

public class FrequencyNode implements Comparable
{
	int count = 0;
	ArrayList<Byte> bytes = new ArrayList<Byte>();
	FrequencyNode left;
	FrequencyNode right;
	
	public FrequencyNode()
	{
		
	}
	
	public FrequencyNode(FrequencyNode left, FrequencyNode right)
	{
		this.left = left;
		this.right = right;
		count = left.count + right.count;
		bytes.addAll(left.bytes);
		bytes.addAll(right.bytes);
	}
	@Override
	public int compareTo(Object arg0) 
	{
		FrequencyNode temp = (FrequencyNode)arg0;
				
		return new Integer(this.count).compareTo(temp.count);
	}
}
