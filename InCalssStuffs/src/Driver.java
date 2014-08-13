import edu.neumont.io.Bits;


public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{				
		String message = "Hello World";
		byte[] data = message.getBytes();

		HuffmanTree tree = new HuffmanTree(data);

		
		HuffmanCompressor compressor = new HuffmanCompressor();
		
		byte[] newData = compressor.compress(tree, data);
		
		byte[] newerData = compressor.decompress(tree, message.length(), newData);
		
		
		String decompressedMessage = "";
		for(int i = 0; i < newerData.length; i++)
		{
			decompressedMessage += ((char)newerData[i]);
		}
		
		System.out.println("Original: " + message);
		System.out.println("After Decompression: " + decompressedMessage);
		

		//byte[] compressedMessage = tree.compress(data);

		//		for(int j = 0; j < compressedMessage.length; j++)
		//		{
		//			System.out.println((int)compressedMessage[j]);
		//		}


	}

}
