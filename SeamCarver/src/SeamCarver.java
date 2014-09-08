import java.awt.Color;
import java.util.ArrayList;

import edu.neumont.ui.Picture;


public class SeamCarver 
{
	private Picture pic;
	
	public class Point
	{
		public Point(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		
		public int x;
		public int y;
	}
	
	public class Vertex
	{
		public int parentIndex = -1;
		public double energy = Double.MAX_VALUE;
		public double totalEnergy = Double.MAX_VALUE;
		public int index;
	}
	
	public SeamCarver(Picture pic)
	{
		this.pic = pic;
	}
	
	public int width()
	{
		return pic.width();
	}
	
	public int height()
	{
		return pic.height();
	}
	
	public double energy(int x, int y)
	{
		if(x > pic.width() || y > pic.height() || x < 0 || y < 0)
		{
			throw new IndexOutOfBoundsException();
		}
		int prevX = x-1 < 0 ? pic.width() - 1 : x - 1;
		int nextX = x+1 == pic.width() ? 0 : x+1;
				
		int r1 = 0;
		int g1 = 0;
		int b1 = 0;
		if(prevX >= 0)
		{
			r1 = pic.get(prevX, y).getRed();
			g1 = pic.get(prevX, y).getGreen();
			b1 = pic.get(prevX, y).getBlue();
		}
		
		
		int r2 = 0;
		int g2 = 0;
		int b2 = 0;
		if(nextX < pic.width())
		{
			r2 = pic.get(nextX, y).getRed();
			g2 = pic.get(nextX, y).getGreen();
			b2 = pic.get(nextX, y).getBlue();
		}
		
		int newR = r1-r2;
		int newG = g1-g2;
		int newB = b1-b2;
		
		
		int upY = y-1 > 0 ? y-1 : 0;
		int downY = y+1;
				
		int rUp = 0;
		int gUp = 0;
		int bUp = 0;
		if(upY >= 0)
		{
			rUp = pic.get(x, upY).getRed();
			gUp = pic.get(x, upY).getGreen();
			bUp = pic.get(x, upY).getBlue();
		}
		
		int rDown = 0;
		int gDown = 0;
		int bDown = 0;
		if(downY < pic.height())
		{
			rDown = pic.get(x, downY).getRed();
			gDown = pic.get(x, downY).getGreen();
			bDown = pic.get(x, downY).getBlue();
		}
		
		int newUpR = rUp-rDown;
		int newUpG = gUp-gDown;
		int newUpB = bUp-bDown;
		
		
		double leftRightTotal = (newR * newR) + (newG * newG) + (newB * newB);
		double upDownTotal = (newUpR * newUpR) + (newUpG * newUpG) + (newUpB * newUpB);
		
		return leftRightTotal + upDownTotal;
	}
	
	//an array of all of the x values through every y
	public int[] findVerticalSeam()
	{
		int[] path = new int[pic.height()];
		
		Vertex[] graph = new Vertex[pic.width() * pic.height()];
		double minWeight = Double.MAX_VALUE;
		Vertex minVert = null;
		
		for(int i = 0; i < pic.width() * pic.height(); i++)
		{				
			int x = 0;
			int y = 0;
			
			y = i / pic.width();
			x = i - (pic.width() * y);
			
			graph[i] = new Vertex();
			graph[i].index = i;
			
			if(y == 0)
			{
				graph[i].totalEnergy = 0;
			}
		}
		
		for(int i = 0; i < pic.width() * pic.height(); i++)
		{	
			int x = 0;
			int y = 0;
			
			y = i / pic.width();
			x = i - (pic.width() * y);
			
			double energyOfCurrentVertex = energy(x, y);
			graph[i].energy = energyOfCurrentVertex;
			
			if(y < pic.height() - 1)
			{
				//check left bottom
				if(x>0)
				{
					int indexOfBottomLeft = i + pic.width() - 1;
					
					if(graph[indexOfBottomLeft].totalEnergy > graph[i].totalEnergy + graph[i].energy)
					{
						graph[indexOfBottomLeft].totalEnergy = graph[i].totalEnergy + graph[i].energy;
						graph[indexOfBottomLeft].parentIndex = i;
					}
				}
				
				//check middle bottom
				int indexOfBottomMiddle = i + pic.width();
				
				if(graph[indexOfBottomMiddle].totalEnergy > graph[i].totalEnergy + graph[i].energy)
				{
					graph[indexOfBottomMiddle].totalEnergy = graph[i].totalEnergy + graph[i].energy;
					graph[indexOfBottomMiddle].parentIndex = i;
				}
				
				//check right bottom
				if(x<pic.width()-1)
				{
					int indexOfBottomRight = i + pic.width() + 1;
					
					if(graph[indexOfBottomRight].totalEnergy > graph[i].totalEnergy + graph[i].energy)
					{
						graph[indexOfBottomRight].totalEnergy = graph[i].totalEnergy + graph[i].energy;
						graph[indexOfBottomRight].parentIndex = i;
					}
				}
			}
			else
			{
				if(minWeight > graph[i].totalEnergy + graph[i].energy)
				{
					minWeight = graph[i].totalEnergy + graph[i].energy;
					minVert = graph[i];
				}
			}
				
		}
		
		int index = pic.height() - 1;
		while(minVert.parentIndex != -1)
		{
			path[index] = minVert.index;
			minVert = graph[minVert.parentIndex];
			
			index--;
		}
		path[index] = minVert.index;
		
		
		return path;
	}
	
	
	//array of y index through every x
	public int[] findHorizontalSeam()
	{
		int[] path = new int[pic.width()];
		
		Vertex[] graph = new Vertex[pic.width() * pic.height()];
		double minWeight = Double.MAX_VALUE;
		Vertex minVert = null;
		
		for(int i = 0; i < pic.width() * pic.height(); i++)
		{				
			int x = 0;
			int y = 0;
			
			y = i / pic.width();
			x = i - (pic.width() * y);
			
			graph[i] = new Vertex();
			graph[i].index = i;
			
			if(x == 0)
			{
				graph[i].totalEnergy = 0;
			}
		}
		
		for(int i = 0; i < pic.width() * pic.height(); i++)
		{	
			int x = 0;
			int y = 0;
			
			y = i / pic.width();
			x = i - (pic.width() * y);
			
			double energyOfCurrentVertex = energy(x, y);
			graph[i].energy = energyOfCurrentVertex;
			
			if(x < pic.width() - 1)
			{
				//check up right
				if(y > 0)
				{
					int indexOfBottomLeft = i - pic.width() + 1;
					
					if(graph[indexOfBottomLeft].totalEnergy > graph[i].totalEnergy + graph[i].energy)
					{
						graph[indexOfBottomLeft].totalEnergy = graph[i].totalEnergy + graph[i].energy;
						graph[indexOfBottomLeft].parentIndex = i;
					}
				}
				
				//check middle right
				int indexOfBottomMiddle = i + 1;
				
				if(graph[indexOfBottomMiddle].totalEnergy > graph[i].totalEnergy + graph[i].energy)
				{
					graph[indexOfBottomMiddle].totalEnergy = graph[i].totalEnergy + graph[i].energy;
					graph[indexOfBottomMiddle].parentIndex = i;
				}
				
				//check bottom right
				if(y < pic.height() - 1)
				{
					int indexOfBottomRight = i + pic.width() + 1;
					
					if(graph[indexOfBottomRight].totalEnergy > graph[i].totalEnergy + graph[i].energy)
					{
						graph[indexOfBottomRight].totalEnergy = graph[i].totalEnergy + graph[i].energy;
						graph[indexOfBottomRight].parentIndex = i;
					}
				}
			}
			else
			{
				if(minWeight > graph[i].totalEnergy + graph[i].energy)
				{
					minWeight = graph[i].totalEnergy + graph[i].energy;
					minVert = graph[i];
				}
			}
				
		}
		
		int index = pic.width() - 1;
		while(minVert.parentIndex != -1)
		{
			path[index] = minVert.index;
			minVert = graph[minVert.parentIndex];
			
			index--;
		}
		path[index] = minVert.index;
		
		
		return path;
	}
	
	public void removeHorizontalSeam(int[] indicies)
	{
		if(indicies.length != pic.width() || pic.height() <= 1)
		{
			throw new IllegalArgumentException();
		}
		int x = 0;
		int y = 0;
		int previousX = -1;
		int previousY = -1;
		
		Picture newPic = new Picture(pic.width(), pic.height() - 1);
		
		
		for(int i = 0; i < newPic.width(); i++)
		{
			y = indicies[i] / pic.width();
			x = indicies[i] - (pic.width() * y);
			
			if(x - previousX != 1)
			{
				throw new IllegalArgumentException();
			}
			if(previousY != -1 && (y - previousY > 1 || y - previousY < -1))
			{
				throw new IllegalArgumentException();
			}
			
			previousX = x;
			previousY = y;
			
			if(x > pic.width() || y > pic.height() || x < 0 || y < 0)
			{
				throw new IndexOutOfBoundsException();
			}
			
			for(int j = 0; j < newPic.height(); j++)
			{
				if(j > y)
				{
					Color tempColor = pic.get(i, j + 1);
					newPic.set(i, j, tempColor);
				}
				else
				{
					newPic.set(i, j, pic.get(i, j));
				}
				
			}
		}
		
		newPic.save("newImage.png");
	}
	
	public void removeVerticalSeam(int[] indicies)
	{
		if(indicies.length != pic.height() || pic.width() <= 1)
		{
			throw new IllegalArgumentException();
		}
		int x = 0;
		int y = 0;
		int previousX = -1;
		int previousY = -1;
		
		Picture newPic = new Picture(pic.width() - 1, pic.height());
		
		for(int i = 0; i < newPic.height(); i++)
		{
			y = indicies[i] / pic.width();
			x = indicies[i] - (pic.width() * y);
			
			if(y - previousY != 1)
			{
				throw new IllegalArgumentException();
			}
			if(previousX != -1 && (x - previousX > 1 || x - previousX < -1))
			{
				throw new IllegalArgumentException();
			}
			
			previousX = x;
			previousY = y;
			
			if(x > pic.width() || y > pic.height() || x < 0 || y < 0)
			{
				throw new IndexOutOfBoundsException();
			}
			
			for(int j = 0; j < newPic.width(); j++)
			{
				if(j > x)
				{
					Color tempColor = pic.get(j + 1, i);
					newPic.set(j, i, tempColor);
				}
				else
				{
					newPic.set(j, i, pic.get(j, i));
				}
				
			}
		}
		
		newPic.save("newImage.png");
	}
}
