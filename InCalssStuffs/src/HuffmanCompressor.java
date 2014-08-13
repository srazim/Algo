import edu.neumont.io.Bits;


public class HuffmanCompressor 
{
	public byte[] compress(HuffmanTree tree, byte[] b)
	{
		byte[] bytesToReturn;
		Bits bits = new Bits();
		
		for(int i = 0; i < b.length; i++)
		{
			Bits tempBits = new Bits();
			tree.fromByte(b[i], tempBits);
			bits.addAll(tempBits);
		}
		while(bits.size() % 8 != 0)
		{
			bits.offer(false);
		}
		
		bytesToReturn = new byte[bits.size() / 8];
		
		String strang = "";
		
		while(bits.size() > 0)
		{
			strang += bits.poll() ? 1 : 0;
		}
		
		for(int i = 0; i < bytesToReturn.length; i++)
		{
			String test = strang.substring(i * 8, (i * 8) + 8);
			System.out.println(test);
			
			bytesToReturn[i] = Byte.valueOf( (byte)Integer.parseInt( test, 2) );
		}
			
		return bytesToReturn;
		
	}
	public byte[] decompress(HuffmanTree tree, int uncompressedLength, byte[] b)
	{
		byte[] messageToReturn = new byte[uncompressedLength];
		
		//convert b to Bits
		Bits data = new Bits();
		for (byte myByte : b){
			String strang = Integer.toBinaryString(myByte & 255 | 256).substring(1);
			
			System.out.println(strang);
			
			for(int i = 0; i < strang.length(); i++)
			{
				data.offer((strang.charAt(i) == '0' ? false : true));
			}
		}
		
		for (int i = 0; i < messageToReturn.length; i++)
		{
			messageToReturn[i] = tree.toByte(data);
		}
		
		return messageToReturn;
	}
}
