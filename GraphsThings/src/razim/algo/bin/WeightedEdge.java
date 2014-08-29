package razim.algo.bin;

public class WeightedEdge implements Comparable<WeightedEdge>
{
	int weight = -1;
	int index = -1;
	
	public WeightedEdge(int w, int i)
	{
		weight = w;
		index = i;
	}
	
	@Override
	public int compareTo(WeightedEdge arg0) 
	{
		return this.weight - arg0.weight;
	}

}
