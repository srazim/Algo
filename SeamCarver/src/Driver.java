import java.io.File;

import edu.neumont.ui.Picture;


public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Picture myPic = new Picture(new File("overlayimagewithhiddenmessage.png"));
		//Picture myPic = new Picture(new File("test.png"));
		
		for(int i = 0; i < 175; i++)
		{
			SeamCarver carver = new SeamCarver(myPic);
			
			int[] path = carver.findVerticalSeam();
			
			carver.removeVerticalSeam(path);
			
			myPic = new Picture(new File("newImage.png"));
		}
		
		for(int i = 0; i < 150; i++)
		{
			SeamCarver carver = new SeamCarver(myPic);
			
			int[] path = carver.findHorizontalSeam();
			
			carver.removeHorizontalSeam(path);
			
			myPic = new Picture(new File("newImage.png"));
		}
		
		System.out.println("Done.");

	}

}
