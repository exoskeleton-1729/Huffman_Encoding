import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class HuffmanTester{
	
	public static void main(String[] args) throws IOException
	{
//		HuffmanCodeGenerator h = new HuffmanCodeGenerator("thegaygatsby.txt");
//		h.generateTree();
//		h.makeCodeFile("codeFilecats.txt");
//		HuffmanEncoder e = new HuffmanEncoder("codeFilecats.txt");
//		e.encodeFile("mewcats.txt");
		HuffmanDecoder d = new HuffmanDecoder("codeFilecats.txt");
		d.decodeFile("mewcats.txt.huf");
	}
}
