import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class HuffmanDecoder {
	
	private HashMap<String, Character> decodes = new HashMap<String, Character> ();
	
	public HuffmanDecoder(String codeFile) throws FileNotFoundException
	{
		File file = new File(codeFile);
		Scanner s = new Scanner(file);
		int lineNumber = 0;
		while(s.hasNextLine())
		{
			decodes.put(s.nextLine(), (char)lineNumber);
			lineNumber++;
		}
		s.close();
	}

	public boolean isCode(String binary)
	{
		return (decodes.containsKey(binary));
	}
	
	public char decodeChar(String binary)
	{
		return decodes.get(binary);
	}
	
	public void decodeLong(String encodedFile, String decodedFile) throws IOException
	{
		PrintWriter out = new PrintWriter(new FileWriter(decodedFile));
		BufferedReader reader = new BufferedReader(new FileReader(encodedFile));
		StringBuilder sb = new StringBuilder("");
		
		while(reader.ready())
		{
			sb.append((char)reader.read());
			if(isCode(sb.toString()))
			{
				out.write(decodeChar(sb.toString()));
				sb.setLength(0);
			}
		}
		
		reader.close();
		out.close();
	}
	
	public String spookyBinary(String unSpooky)
	{
		StringBuilder spooky = new StringBuilder("");
		for(int i = 0; i < 8-unSpooky.length(); i++)
		{
			spooky.append(0);
		}
		spooky.append(unSpooky);
		return spooky.toString();
	}
	
	public void decodeFile(String encodedFile) throws IOException
	{
		if(!encodedFile.substring(encodedFile.length()-4, encodedFile.length()).equals(".huf"))
		{
			throw new IllegalArgumentException();
		}
		else
		{
			PrintWriter out = new PrintWriter(new FileWriter("uncoded.txt"));
			BufferedReader reader = new BufferedReader(new FileReader(encodedFile));
			int numChars = 0;
			int counter = 0;
			char nextLetter = (char)reader.read();
			
			while(reader.ready())
			{
				out.write(spookyBinary(Integer.toBinaryString(nextLetter)));
				numChars += 8;
				nextLetter = (char)reader.read();
			}
			
			numChars -= (int)nextLetter;
			PrintWriter pw = new PrintWriter(new FileWriter(encodedFile.substring(0, encodedFile.length()-4)));
			BufferedReader rdr = new BufferedReader(new FileReader("uncoded.txt"));
			StringBuilder sb = new StringBuilder("");
			
			out.close();
			reader.close();
			
			while(rdr.ready() && counter < numChars)
			{
				sb.append((char)rdr.read());
				if(isCode(sb.toString())){
					pw.write(decodeChar(sb.toString()));
					sb.setLength(0);
				}
				counter++;
			}
			
			
			pw.close();
			rdr.close();
			
		}
	}

}
